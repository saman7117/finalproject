package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class TokenController {
    @FXML
    private ImageView flag = new ImageView();
    @FXML
    private Label Tokenname =  new Label();
    public void setFlagImage(String string){
        File file = new File(string);
        Image image = new Image(file.toURI().toString());
        flag.setImage(image);
    }
    public void setTokenname(String s){
        Tokenname.setText(s);
    }
}
