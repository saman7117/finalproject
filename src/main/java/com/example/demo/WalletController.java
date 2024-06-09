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
    private volatile boolean stop = false;
    String timenow;

    public void toProfile() throws IOException {//:(
        Stage stage = new Stage();
//        stage.close();
        FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("Transfer.fxml"));
        Scene registerScene = new Scene(registerLoader.load(), 800, 600);
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
