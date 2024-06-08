package com.example.demo;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
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
    @FXML
    private Label time;
    @FXML
    private Label date;
    private volatile boolean stop = false;

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

    public void showTime(){
        Thread thread = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
            while (!stop){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                final String timenow = simpleDateFormat.format(new Date());
                Platform.runLater(() -> {
                    time.setText(timenow);
                });
            }
        });
        thread.start();
    }
    private void showDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE , MMM dd/yyyy");
        String datenow = simpleDateFormat.format(new Date());
        date.setText(datenow);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showTime();
        showDate();
    }
}
