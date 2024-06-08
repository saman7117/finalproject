package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController  {
    @FXML
    private Label name;
    @FXML
    private Label firstName;
    @FXML
    private Label lastName;
    @FXML
    private Label Password;
    @FXML
    private Label Gmail;
    @FXML
    private Label phoneNumber;
    @FXML
    private Label tag;
    @FXML
    private Label username;

    public void clicked(){
        name.setText(datas.username);
        firstName.setText(datas.firstName);
        lastName.setText(datas.lastName);
        Password.setText(datas.Password);
        Gmail.setText(datas.Gmail);
        phoneNumber.setText(datas.phoneNumber);
        tag.setText(datas.username);
        username.setText(datas.username);
    }

    public void toWallet() throws IOException {//:)
        Stage stage = (Stage)name.getScene().getWindow();
        stage.close();
        FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("walet.fxml"));
        Scene registerScene = new Scene(registerLoader.load(), 1536, 864);
        stage.setTitle("Raze Exchange");
        stage.setScene(registerScene);
        stage.show();
    }


}
