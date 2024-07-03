package com.example.demo;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class WalletController implements Initializable{
    @FXML
    private Label title;
    @FXML
    private Label TMNasset;
    @FXML
    private Label GBTasset;
    @FXML
    private Label YENasset;
    @FXML
    private Label EURasset;
    @FXML
    private Label USDasset;
    @FXML
    private Label TTasset;
    @FXML
    private Label time;
    @FXML
    private Label date;
    @FXML
    private Label Tomannerkh;
    @FXML
    private Label GBTnerkh;
    @FXML
    private Label USDnerkh;
    @FXML
    private Label EURnerkh;
    @FXML
    private Label YENnerkh;
    @FXML
    private Label Tomanval;
    @FXML
    private Label GBTval;
    @FXML
    private Label USDval;
    @FXML
    private Label EURval;
    @FXML
    private Label YENval;
    private volatile boolean stop = false;
    String timenow;
    public void toHomePage() throws IOException{
        Stage stage = (Stage)Tomanval.getScene().getWindow();
        stage.close();
        FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));
        Scene registerScene = new Scene(registerLoader.load(), 1536, 864);
        stage.setTitle("Raze Exchange");
        stage.setScene(registerScene);
        stage.show();
    }
    public void toProfile() throws IOException {//:(
        Stage stage = (Stage)Tomanval.getScene().getWindow();
        stage.close();
        FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("Profile.fxml"));
        Scene registerScene = new Scene(registerLoader.load(), 1536, 864);
        stage.setTitle("Raze Exchange");
        stage.setScene(registerScene);
        stage.show();
    }
    public void toSwap() throws IOException {//:(
        Stage stage = new Stage();
        FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("Swap.fxml"));
        Scene registerScene = new Scene(registerLoader.load(), 600, 600);
        stage.setTitle("Raze Exchange");
        stage.setScene(registerScene);
        stage.show();
    }
    public void toTransfer() throws IOException {//:(
        Stage stage = new Stage();
        FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("Transfer.fxml"));
        Scene registerScene = new Scene(registerLoader.load(), 600, 600);
        stage.setTitle("Raze Exchange");
        stage.setScene(registerScene);
        stage.show();
    }
    public void clicked(){
        TMNasset.setText(String.valueOf(datas.TMN));
        USDasset.setText(String.valueOf(datas.USD));
        EURasset.setText(String.valueOf(datas.EUR));
        YENasset.setText(String.valueOf(datas.YEN));
        GBTasset.setText(String.valueOf(datas.GBT));
        TTasset.setText(String.valueOf(datas.total));
        Tomannerkh.setText(String.valueOf(datas.TMNPrice));
        GBTnerkh.setText(String.valueOf(datas.GBPPrice));
        USDnerkh.setText(String.valueOf(datas.USDPrice));
        EURnerkh.setText(String.valueOf(datas.EURPrice));
        YENnerkh.setText(String.valueOf(datas.YENPrice));
        Tomanval.setText(String.valueOf(datas.TMNPrice * datas.TMN));
        GBTval.setText(String.valueOf(datas.GBPPrice * datas.GBT));
        USDval.setText(String.valueOf(datas.USDPrice * datas.USD));
        EURval.setText(String.valueOf(datas.EURPrice * datas.EUR));
        YENval.setText(String.valueOf(datas.YENPrice * datas.YEN));
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
                timenow = simpleDateFormat.format(new Date());
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
