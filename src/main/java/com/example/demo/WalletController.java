package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WalletController{
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

    public void toProfile() throws IOException {//:(
        Stage stage = new Stage();
//        stage.close();
        FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("Swap.fxml"));
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
}
