// package
package com.mycompany.frogsandtoads;

// imports
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;



// Main Screen Controller
public class MainScreenController implements Initializable {

    @FXML
    private ImageView pond1;
    @FXML
    private Label lblMovesCount;
    @FXML
    private Label lblWellDone;
    @FXML
    private ImageView pond2;
    @FXML
    private ImageView pond3;
    @FXML
    private ImageView pond4;
    @FXML
    private ImageView pond5;
    @FXML
    private ImageView pond6;
    @FXML
    private ImageView pond7;

    @FXML
    private ImageView amphibian2;
    @FXML
    private ImageView amphibian5;
    @FXML
    private ImageView amphibian6;
    @FXML
    private ImageView amphibian7;
    @FXML
    private ImageView amphibian1;
    @FXML
    private ImageView amphibian3;
    @FXML
    private ImageView amphibian4;
    private ImageView selectedAmphibian;// variable to keep track of selected toad or frog
    private ImageView selectedPond;// variable to keep track of target pond where to move amphibian to
    private HashMap<ImageView, ImageView> pondsAmphibiansMap;// list of ponds with their corresponding amphibians
    private HashMap<ImageView, ImageView> amphibiansPondsMap;
    private ImageView currentEmptyPond; // Variable to maintain current empty pod.
    private ImageView previousEmptyPond; // Variable to maintain previous empty pod.
    private int moveCount; // Variable to maintain moves count.
    private ArrayList<ImageView> validAmphibiansList; // Arraylist to maintain valid amphibians after each move.
    private ArrayList<ImageView> allPonds;
    private HashMap<ImageView, Boolean> pondsStatus;
    private HashMap<ImageView, Integer> pondRanks;
    
    @FXML
    private Button replayButton;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        pondsAmphibiansMap = new HashMap<>();// create pondsAmphibiansMap Hashmap   
        amphibiansPondsMap = new HashMap<>();
        pondsStatus = new HashMap<>();
        pondRanks = new HashMap<>();
        this.allPonds = new ArrayList<>();
        
        amphibiansPondsMap.put(amphibian1, pond1);// initialize amphibiansPondsMap Hashmap
        amphibiansPondsMap.put(amphibian2, pond2);
        amphibiansPondsMap.put(amphibian3, pond3);
        amphibiansPondsMap.put(amphibian4, pond4);
        amphibiansPondsMap.put(amphibian5, pond5);
        amphibiansPondsMap.put(amphibian6, pond6);
        amphibiansPondsMap.put(amphibian7, pond7);

        pondsAmphibiansMap.put(pond1, amphibian1); // initialize pondsAmphibiansMap Hashmap
        pondsAmphibiansMap.put(pond2, amphibian2);
        pondsAmphibiansMap.put(pond3, amphibian3);
        pondsAmphibiansMap.put(pond4, amphibian4);
        pondsAmphibiansMap.put(pond5, amphibian5);
        pondsAmphibiansMap.put(pond6, amphibian6);
        pondsAmphibiansMap.put(pond7, amphibian7);
        
        this.allPonds.add(pond1);
        this.allPonds.add(pond2);
        this.allPonds.add(pond3);
        this.allPonds.add(pond4);
        this.allPonds.add(pond5);
        this.allPonds.add(pond6);
        this.allPonds.add(pond7);
        
        this.pondsStatus.put(pond1, true);
        this.pondsStatus.put(pond2, true);
        this.pondsStatus.put(pond3, true);
        this.pondsStatus.put(pond4, null);
        this.pondsStatus.put(pond5, false);
        this.pondsStatus.put(pond6, false);
        this.pondsStatus.put(pond7, false);
        
        this.pondRanks.put(pond1, 1);
        this.pondRanks.put(pond2, 2);
        this.pondRanks.put(pond3, 3);
        this.pondRanks.put(pond4, 4);
        this.pondRanks.put(pond5, 5);
        this.pondRanks.put(pond6, 6);
        this.pondRanks.put(pond7, 7);
        
        this.validAmphibiansList = new ArrayList<ImageView>();
        this.currentEmptyPond = this.pond4;
        this.previousEmptyPond = this.pond4;
        this.replayButton.setVisible(false);
        
        this.updateValidAmphibians(this.currentEmptyPond);
    }

    
    
    //------------------ Mouse events to select amphibians.
    @FXML
    private void selectAmphibian1(MouseEvent event) {
        selectAmphibian(amphibian1, event);
    }
    @FXML
    private void selectAmphibian2(MouseEvent event) {
        selectAmphibian(amphibian2, event);
    }

    @FXML
    private void selectAmphibian5(MouseEvent event) {
        selectAmphibian(amphibian5, event);
    }

    @FXML
    private void selectAmphibian6(MouseEvent event) {
        selectAmphibian(amphibian6, event);
    }

    @FXML
    private void selectAmphibian7(MouseEvent event) {
        selectAmphibian(amphibian7, event);
    }

    @FXML
    private void selectAmphibian3(MouseEvent event) {
        selectAmphibian(amphibian3, event);
    }

    @FXML
    private void selectAmphibian4(MouseEvent event) {
        selectAmphibian(amphibian4, event);
    }

      
    // Highlight or dehighlight amphibians/pods and update previous and current empty pods 
    private void selectAmphibian(ImageView amphibian, MouseEvent event) {
        this.updateValidAmphibians(this.currentEmptyPond);
        if (this.validAmphibiansList.contains(amphibian) && !gameWon()) {
            this.selectedAmphibian = amphibian;
           // this.selectedAmphibian.setEffect(new DropShadow(30, Color.BLUE));
            if(this.currentEmptyPond != null)
                this.currentEmptyPond.setEffect(null);
            System.out.println("Selected Amphibian: " +this.selectedAmphibian);
            this.jumpAmphibion(amphibian, event);
            this.previousEmptyPond = this.currentEmptyPond;
            this.currentEmptyPond = this.amphibiansPondsMap.get(amphibian);
            this.currentEmptyPond.setEffect(new DropShadow(30, Color.RED));

        }
        else if (this.validAmphibiansList.isEmpty() && !gameWon()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("It's a deadlock!");
            alert.setContentText("You cannot make any move further! Press ok and replay the game");
            alert.show();
            this.replayButton.setVisible(true);
        }
        else{
            System.out.println(this.validAmphibiansList.isEmpty());
            System.out.println("Sorry, you cannot make a move with this amphibian");
        }
        
    }
    
    
    // Make amphibians jump
    private void jumpAmphibion(ImageView amphibion, MouseEvent event){
        this.pondsAmphibiansMap.get(this.currentEmptyPond).setImage(
                                                                    this.selectedAmphibian.getImage());
        this.selectedAmphibian.setImage(null);
        this.pondsStatus.replace(this.currentEmptyPond, this.pondsStatus.get(this.amphibiansPondsMap.get(amphibion)));
        this.pondsStatus.replace(this.amphibiansPondsMap.get(amphibion), null);
        if(gameWon()){
            lblWellDone.setText("Well Done, You Won!");
            lblWellDone.setVisible(true);
            replayButton.setVisible(true);
        }
    }
    
    private boolean gameWon() {

        if (pondsStatus.get(pond4) == null) {
            if (pondsStatus.get(pond1) == false) {
                if (pondsStatus.get(pond2) == false) {
                    if (pondsStatus.get(pond3) == false) {
                        return true;
                    }
                }
            }

        }
        return false;
    }
    
    private void updateValidAmphibians(ImageView currentEmptyPond) {
        ArrayList<ImageView> pondKeys = new ArrayList<>();
        int indexKey;
        this.validAmphibiansList.clear();
        for (int i = 0; i < this.allPonds.size(); i++) {
            if (currentEmptyPond.equals(this.allPonds.get(i))){
                indexKey = i;
                if (i-1 >= 0 && this.isLegal(i-1))
                    pondKeys.add(this.allPonds.get(i-1));
                if (i-2 >= 0 && this.isLegal(i-2))
                    pondKeys.add(this.allPonds.get(i-2));
                if (i+1 <= this.allPonds.size() - 1 && this.isLegal(i + 1))
                    pondKeys.add(this.allPonds.get(i+1));
                if (i+2 <= this.allPonds.size() - 1 && this.isLegal(i + 2))
                    pondKeys.add(this.allPonds.get(i+2));
            }
        }
        
        pondKeys.forEach((item) -> {
            this.validAmphibiansList.add(this.pondsAmphibiansMap.get(item));
        });
           
        System.out.println("Eligible Ponds:" +this.validAmphibiansList.toString());
    }

    private boolean isLegal(int index) {
        int emptyPondRank = this.pondRanks.get(this.currentEmptyPond);
        boolean nominatedPondStatus = this.pondsStatus.get(this.allPonds.get(index));
        int nominatedPondRank = this.pondRanks.get(this.allPonds.get(index));
        if ((nominatedPondStatus == true && nominatedPondRank < emptyPondRank)
                            || (nominatedPondStatus == false) && nominatedPondRank > emptyPondRank) {
            return true;
        }
            
    return false;
    }
    
    @FXML
    private void restartGame(ActionEvent event) throws IOException {
        App.setRoot("MainScreen");
    }
 
}
