package com.example.demo;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class TokenController implements Initializable {
    @FXML
    private ImageView flag = new ImageView();
    @FXML
    private Label Tokenname =  new Label();
    @FXML
    private Label time;
    @FXML
    private Label date;
    private volatile boolean stop = false;
    public void setFlagImage(String string){
        File file = new File(string);
        Image image = new Image(file.toURI().toString());
        flag.setImage(image);
    }
    public void setTokenname(String s){
        Tokenname.setText(s);
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
