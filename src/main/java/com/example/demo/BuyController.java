package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;

import java.io.IOException;
import java.util.ArrayList;

public class BuyController {
    @FXML
    private MenuButton frommenu;
    @FXML
    private Button Buybtn;
    @FXML
    private Button Sellbtn;
    String status;
    @FXML
    private Button confirmbtn;
    @FXML
    private TextField price;
    @FXML
    private TextField value;

    public void setmenutexttoUSD(){
        frommenu.setText("USD");
    }
    public void setmenutexttoTMN(){
        frommenu.setText("Toman");
    }
    public void setmenutexttoEUR(){
        frommenu.setText("EUR");
    }
    public void setmenutexttoYEN(){
        frommenu.setText("YEN");
    }
    public void setmenutexttoGBT(){
        frommenu.setText("GBT");
    }

    public void setButtontoSell(){
        Sellbtn.setStyle("-fx-text-fill: #191a1f;\n" +
                "    -fx-background-color: #fca311;\n"
                );
        Buybtn.setStyle("-fx-text-fill: #fca311;\n" +
                "    -fx-background-color: #191a1f;\n" +
                "    -fx-border-color: #fca311;");
        status = "sell";
    }

    public void setButtontoBuy(){
        Buybtn.setStyle("-fx-text-fill: #191a1f;\n" +
                "    -fx-background-color: #fca311;\n"
        );
        Sellbtn.setStyle("-fx-text-fill: #fca311;\n" +
                "    -fx-background-color: #191a1f;\n" +
                "    -fx-border-color: #fca311;");
        status = "buy";
    }

    public void setServerArraylist() throws IOException, ClassNotFoundException {
        datas.MainWriter.println("e");
        ArrayList<String> stockQueues = (ArrayList<String>) datas.objectMainReader.readObject();
        stockQueues.add("na");
        stockQueues.add("da");
        datas.objectOutputStream.writeObject(stockQueues);
    }
}
