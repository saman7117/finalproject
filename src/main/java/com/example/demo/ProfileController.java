package com.example.demo;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;

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
    @FXML
    private Label role;
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
        role.setText(datas.role);
    }

    public void logout() throws IOException {
        Stage stage = (Stage) name.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("خروج");
        alert.setHeaderText("شما در حال خروج از صرافی انلاین هستید");
        alert.setContentText("آیا مطمئنید که میخواهید از صرافی خراج شوید؟");
        if (alert.showAndWait().get() == ButtonType.OK){
            stage.close();
            FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
            Scene registerScene = new Scene(registerLoader.load(), 1000, 600);
            stage.setTitle("Raze Exchange");
            stage.setScene(registerScene);
            stage.show();
        }
    }

    public void toWallet() throws IOException {//:)
        Stage stage = (Stage)name.getScene().getWindow();
        stage.close();
        FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("walet.fxml"));
        Scene registerScene = new Scene(registerLoader.load(), 1536, 800);
        stage.setTitle("Raze Exchange");
        stage.setScene(registerScene);
        stage.show();
    }

    public void toHomePage() throws IOException{
        Stage stage = (Stage)name.getScene().getWindow();
        stage.close();
        FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));
        Scene registerScene = new Scene(registerLoader.load(), 1536, 800);
        stage.setTitle("Raze Exchange");
        stage.setScene(registerScene);
        stage.show();
    }
    public void toDeposit() throws IOException {//:(
        Stage stage = new Stage();
        FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("payment.fxml"));
        Scene registerScene = new Scene(registerLoader.load(), 600, 600);
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
