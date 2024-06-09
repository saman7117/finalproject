package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class SwapController {
    @FXML
    private TextField fromvalue;
    @FXML
    private Label tovalue;
    @FXML
    private MenuButton frommenu;
    @FXML
    private MenuButton tomenu;
    @FXML
    private ImageView swapbtn;
    @FXML
    private Button confirmbtn;
    @FXML
    private MenuItem USDitem;
    @FXML
    private MenuItem TMNitem;
    @FXML
    private MenuItem YENitem;
    @FXML
    private MenuItem GBTitem;
    @FXML
    private MenuItem EURitem;
    @FXML
    private Label warn;

    double USDprice = datas.USDPrice;
    double Tomanprince = datas.TMNPrice;
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

    public void setmenutextto2USD(){
        tomenu.setText("USD");
    }
    public void setmenutextto2TMN(){
        tomenu.setText("Toman");
    }
    public void setmenutextto2EUR(){
        tomenu.setText("EUR");
    }
    public void setmenutextto2YEN(){
        tomenu.setText("YEN");
    }
    public void setmenutextto2GBT(){
        tomenu.setText("GBT");
    }

    public void SwapFT(){
        String temp = frommenu.getText();
        frommenu.setText(tomenu.getText());
        tomenu.setText(temp);
        temp = fromvalue.getText();
        fromvalue.setText(tovalue.getText());
        tovalue.setText(temp);
    }

    @FXML
    private void initialize(){
        fromvalue.textProperty().addListener((observable, oldValue, newValue) -> {
            // Perform the conversion logic here
            double amount = Double.parseDouble(newValue); // Assuming the input is a valid number
            double convertedAmount = convertCurrency(amount); // Implement your currency conversion logic here
            tovalue.setText(String.valueOf(convertedAmount));
        });
    }

    private double convertCurrency(double amount) {
        double a = amount/USDprice;
        a*=Tomanprince;
        return a;
    }

    public void Confirm(){
        boolean flag = false;
        if (frommenu.getText().equals("USD")){
            if (datas.USD >= Double.parseDouble(fromvalue.getText())){
                if(tomenu.getText().equals("Toman")){
                    datas.USD -= Double.parseDouble(fromvalue.getText());
                    datas.TMN += Double.parseDouble(tovalue.getText());
                }
                else if(tomenu.getText().equals("YEN")){
                    datas.USD -= Double.parseDouble(fromvalue.getText());
                    datas.YEN += Double.parseDouble(tovalue.getText());
                }
                else if(tomenu.getText().equals("GBT")){
                    datas.USD -= Double.parseDouble(fromvalue.getText());
                    datas.GBT += Double.parseDouble(tovalue.getText());
                }
                else if(tomenu.getText().equals("EUR")){
                    datas.USD -= Double.parseDouble(fromvalue.getText());
                    datas.EUR += Double.parseDouble(tovalue.getText());
                }
                else if(tomenu.getText().equals("USD")){
                    warn.setText("ارزها یکسان است");
                }
            }
        }

    }

}
