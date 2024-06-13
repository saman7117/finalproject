package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        Alert alert = new Alert(Alert.AlertType.NONE);
        if (value.getText().equals(null) || price.getText().equals(null)){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("خطا در وارد کردن");
            alert.setContentText("لطفا مقدار و قیمت را وارد کنید");
            alert.showAndWait();
        } else if (frommenu.getText().equals("USD") && Double.parseDouble(value.getText()) > datas.USD && status.equals("sell")) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("خطا در وارد کردن");
            alert.setContentText("مقدار ناکافی");
            alert.showAndWait();
        }
        else if (frommenu.getText().equals("TMN") && Double.parseDouble(value.getText()) > datas.TMN && status.equals("sell")) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("خطا در وارد کردن");
            alert.setContentText("مقدار ناکافی");
            alert.showAndWait();
        }
        else if (frommenu.getText().equals("YEN") && Double.parseDouble(value.getText()) > datas.YEN && status.equals("sell")) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("خطا در وارد کردن");
            alert.setContentText("مقدار ناکافی");
            alert.showAndWait();
        }
        else if (frommenu.getText().equals("EUR") && Double.parseDouble(value.getText()) > datas.EUR && status.equals("sell")) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("خطا در وارد کردن");
            alert.setContentText("مقدار ناکافی");
            alert.showAndWait();
        }
        else if (frommenu.getText().equals("GBP") && Double.parseDouble(value.getText()) > datas.GBT && status.equals("sell")) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("خطا در وارد کردن");
            alert.setContentText("مقدار ناکافی");
            alert.showAndWait();
        }
        else if (Double.parseDouble(value.getText()) * Double.parseDouble(price.getText()) > datas.total && status.equals("buy")) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("خطا در وارد کردن");
            alert.setContentText("مقدار ناکافی");
            alert.showAndWait();
        }
        else {
            datas.MainWriter.println("e");
            datas.MainWriter.println("f");
            datas.MainWriter.println(frommenu.getText());
            datas.MainWriter.println(status);
            datas.MainWriter.println(value.getText());
            datas.MainWriter.println(price.getText());
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmed");
            alert.setHeaderText("موفقیت ");
            if (status.equals("sell"))
                alert.setContentText("سفارش فروش با موفقیت ثبت شد");
            else
                alert.setContentText("سفارش خرید با موفقیت ثبت شد");
            alert.show();
            Stage stage = (Stage)frommenu.getScene().getWindow();
            stage.close();
        }

    }
}
