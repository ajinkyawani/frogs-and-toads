// package declaration
package com.mycompany.frogsandtoads;

// necessary imports
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import javafx.scene.shape.Circle;
import javafx.animation.PathTransition;
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
    private HashMap<ImageView, ImageView> pondsList;// list of ponds with their corresponding amphibians
    private HashMap<ImageView, Boolean> pondAmphibians;
    private int moveCount;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        pondsList = new HashMap<>();// create pondsList Hashmap   
        pondsList.put(pond1, amphibian1);// initialize pondsList Hashmap
        pondsList.put(pond2, amphibian2);
        pondsList.put(pond3, amphibian3);
        pondsList.put(pond4, amphibian4);
        pondsList.put(pond5, amphibian5);
        pondsList.put(pond6, amphibian6);
        pondsList.put(pond7, amphibian7);

    }

    
        
// -------------- 7 Selections of pods on mouse event
    @FXML
    private void selectPond1(MouseEvent event) {
        selectPond(pond1);
    }

    private void selectPond2(MouseEvent event) {
        selectPond(pond2);
    }

    @FXML
    private void selectPond3(MouseEvent event) {
        selectPond(pond3);
    }

    @FXML
    private void selectPond4(MouseEvent event) {
        selectPond(pond4);
    }

    private void selectPond5(MouseEvent event) {
        selectPond(pond5);
    }

    private void selectPond6(MouseEvent event) {
        selectPond(pond6);
    }

    private void selectPond7(MouseEvent event) {
        selectPond(pond7);
    }
  
    
    
    //------------------  7 Selection of Amphobian on mouse event
    @FXML
    private void selectAmphibian1(MouseEvent event) {
        selectAmphibian(amphibian1);
    }
    @FXML
    private void selectAmphibian2(MouseEvent event) {
        Circle pathCircle = new Circle(100);
      
        PathTransition transition = new PathTransition();
        transition.setNode(amphibian2);
        transition.setDuration(Duration.seconds(3));
        transition.setPath(pathCircle);
        transition.setCycleCount(PathTransition.INDEFINITE);
        transition.play();
        //selectAmphibian(amphibian2);
    }

    @FXML
    private void selectAmphibian5(MouseEvent event) {
        selectAmphibian(amphibian5);
    }

    @FXML
    private void selectAmphibian6(MouseEvent event) {
        selectAmphibian(amphibian6);
    }

    @FXML
    private void selectAmphibian7(MouseEvent event) {
        selectAmphibian(amphibian7);
    }

    @FXML
    private void selectAmphibian3(MouseEvent event) {
        selectAmphibian(amphibian3);
    }

    @FXML
    private void selectAmphibian4(MouseEvent event) {
        selectAmphibian(amphibian4);
    }


    //------------ 7 selection of highlited pons on mouse events
    private void removePondHighlight1(MouseEvent event) {
        if (pond1 != selectedPond) {
            pond1.setEffect(null);
        }
    }

    private void highlightPond1(MouseEvent event) {
        if (pond1 != selectedPond) {
            pond1.setEffect(new DropShadow());
        }
    }

    @FXML
    private void removePondHighlight2(MouseEvent event) {
        if (pond2 != selectedPond) {
            pond2.setEffect(null);
        }
    }

    @FXML
    private void highlightPond2(MouseEvent event) {
        if (pond2 != selectedPond) {
            pond2.setEffect(new DropShadow());
        }
    }

    @FXML
    private void removePondHighlight3(MouseEvent event) {
        if (pond3 != selectedPond) {
            pond3.setEffect(null);
        }
    }

    @FXML
    private void highlightPond3(MouseEvent event) {
        if (pond3 != selectedPond) {
            pond3.setEffect(new DropShadow());
        }
    }

    private void removePondHighlight4(MouseEvent event) {
        if (pond4 != selectedPond) {
            pond4.setEffect(null);
        }
    }

    private void highlightPond4(MouseEvent event) {
        if (pond4 != selectedPond) {
            pond4.setEffect(new DropShadow());
        }
    }

    private void removePondHighlight5(MouseEvent event) {
        if (pond5 != selectedPond) {
            pond5.setEffect(null);
        }
    }

    private void highlightPond5(MouseEvent event) {
        if (pond5 != selectedPond) {
            pond5.setEffect(new DropShadow());
        }
    }

    private void removePondHighlight6(MouseEvent event) {
        if (pond6 != selectedPond) {
            pond6.setEffect(null);
        }
    }

    private void highlightPond6(MouseEvent event) {
        if (pond6 != selectedPond) {
            pond6.setEffect(new DropShadow());
        }
    }

    private void removePondHighlight7(MouseEvent event) {
        if (pond7 != selectedPond) {
            pond7.setEffect(null);
        }

    }

    private void highlightPond7(MouseEvent event) {
        if (pond7 != selectedPond) {
            pond7.setEffect(new DropShadow());
        }

    }
    
    
    private void selectAmphibian(ImageView amphibian) {
        if (selectedAmphibian != null) {
            selectedAmphibian.setEffect(null);
        }
        selectedAmphibian = amphibian;
        selectedAmphibian.setEffect(new DropShadow(30, Color.RED));
    }

    private void selectPond(ImageView pond1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}
