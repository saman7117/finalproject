package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class paymentController {
    @FXML
    private TextField cardNum;
    @FXML
    private Label imgNum;
    @FXML
    private ImageView cardImage;

    @FXML
    private void initialize(){
        cardNum.textProperty().addListener((observable, oldValue, newValue) -> {
//            if(newValue.length()>4 && newValue.length()<=8){
//                newValue = newValue.substring(0, 4) + " " + newValue.substring(4, x.length());
//            }
            imgNum.setText(newValue);
//            newValue = newValue.replaceAll("\\s+","");
            if(newValue.length()<6){
                setFlagImage("src/main/resources/com/example/demo/cardds.jpg");
            }
            if(newValue.length()==6){
                if(newValue.equals("610433")){
                    setFlagImage("src/main/resources/com/example/demo/melat.jpg");
                }
            }
        });
    }
    public void setFlagImage(String string){
        File file = new File(string);
        Image image = new Image(file.toURI().toString());
        cardImage.setImage(image);
    }

}
