// package
package com.mycompany.frogsandtoads;

// imports
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.animation.PathTransition;
import javafx.scene.shape.Arc;
import javafx.util.Duration;


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
    private ArrayList<ImageView> validSpots; // Arraylist to maintain valid amphibians after each move.

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        pondsAmphibiansMap = new HashMap<>();// create pondsAmphibiansMap Hashmap   
        amphibiansPondsMap = new HashMap<>();
        
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
        
        this.validSpots = new ArrayList<ImageView>();
        this.currentEmptyPond = this.pond4;
        this.previousEmptyPond = this.pond4;
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


      
    // Highlight or dehighlight amphibians/pods and update previous and current empty pods 
    private void selectAmphibian(ImageView amphibian, MouseEvent event) {
        if (this.selectedAmphibian != null) {
            this.selectedAmphibian.setEffect(null);
        }
        this.selectedAmphibian = amphibian;
        this.selectedAmphibian.setEffect(new DropShadow(30, Color.RED));
        this.jumpAmphibion(amphibian, event);
        if(this.currentEmptyPond != null)
            this.currentEmptyPond.setEffect(null);
        this.previousEmptyPond = this.currentEmptyPond;
        this.currentEmptyPond = this.amphibiansPondsMap.get(amphibian);
        this.currentEmptyPond.setEffect(new DropShadow(30, Color.RED));
       // System.out.println(this.currentEmptyPond + " " + event.getSceneX() + " " + event.getSceneY());
       // System.out.println(this.previousEmptyPond + " " + event.getSceneX() + " " + event.getSceneY());
        this.updatePondsAmphibiansMapping();
        this.updateAmphibiansPondsMapping();
    }
    
    // Update ponds to amphibians mapping after each move.
    private void updatePondsAmphibiansMapping() {
        ImageView previousAmphibian =  this.pondsAmphibiansMap.get(previousEmptyPond); 
        this.pondsAmphibiansMap.replace(this.previousEmptyPond, this.selectedAmphibian);
        this.pondsAmphibiansMap.replace(this.currentEmptyPond, previousAmphibian);
        
        for (Map.Entry<ImageView, ImageView> entry : pondsAmphibiansMap.entrySet()) {
            ImageView key = entry.getKey();
            ImageView value = entry.getValue();
            System.out.println(key + " " + value);
        }
        
    }
    
    // Update amphibians to ponds mapping after each move.
    private void updateAmphibiansPondsMapping() {
      ImageView previousAmphibian =  this.pondsAmphibiansMap.get(currentEmptyPond);
        this.amphibiansPondsMap.replace(this.selectedAmphibian, this.previousEmptyPond);
        this.amphibiansPondsMap.replace(previousAmphibian, this.currentEmptyPond);
        
        for (Map.Entry<ImageView, ImageView> entry : amphibiansPondsMap.entrySet()) {
            ImageView key = entry.getKey();
            ImageView value = entry.getValue();
            System.out.println(key + " " + value);
        }
    }
    
    // Make amphibians jump
    private void jumpAmphibion(ImageView amphibion, MouseEvent event){
      Arc arc = new Arc();
      arc.setCenterX(event.getX() - 50.0f); 
      arc.setCenterY(event.getY()); 
      arc.setRadiusX(90.0f); 
      arc.setRadiusY(150.0f); 
      arc.setStartAngle(0.0f); 
      arc.setLength(180.0f); 
      PathTransition transition = new PathTransition();
      transition.setNode(amphibion);
      transition.setDuration(Duration.seconds(2));
      transition.setPath(arc);
      transition.setCycleCount(1);
      transition.play();
    }


 
}
