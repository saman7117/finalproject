package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BuyController {
    @FXML
    private MenuButton frommenu;
    @FXML
    private Button Buybtn;
    @FXML
    private Button Sellbtn;
    String status = "sell";

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
        frommenu.setText("GBP");
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

    public void setServerArraylist() throws IOException, ClassNotFoundException, SQLException {
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
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmed");
            alert.setHeaderText("موفقیت ");
            java.sql.Connection connection;
            if (status.equals("sell")) {
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
                    Statement statement = connection.createStatement();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
                    Date date = Calendar.getInstance().getTime();
                    statement.executeUpdate("INSERT INTO selltable (Type , Amount , Price , Trader , date) VALUES ('"+ frommenu.getText() +"','" + Double.parseDouble(value.getText()) + "','" + Double.parseDouble(price.getText()) + "','" + datas.username + "','" + simpleDateFormat.format(date) +"')");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                if (frommenu.getText().equals("USD")){
                    datas.USD -= Double.parseDouble(value.getText());
                    String s1 = "UPDATE users SET USD='" + String.valueOf(datas.USD) + "' WHERE fullname='" + datas.username + "'";
                    statement.executeUpdate(s1);
                }
                else if (frommenu.getText().equals("YEN")){
                    datas.YEN -= Double.parseDouble(value.getText());

                    String s1 = "UPDATE users SET YEN='" + String.valueOf(datas.YEN) + "' WHERE fullname='" + datas.username + "'";
                    statement.executeUpdate(s1);
                } else if (frommenu.getText().equals("GBP")) {
                    datas.GBT -= Double.parseDouble(value.getText());
                    String s1 = "UPDATE users SET GBT='" + String.valueOf(datas.GBT) + "' WHERE fullname='" + datas.username + "'";
                    statement.executeUpdate(s1);
                } else if (frommenu.getText().equals("EUR")) {
                    datas.EUR -= Double.parseDouble(value.getText());
                    String s1 = "UPDATE users SET EUR='" + String.valueOf(datas.EUR) + "' WHERE fullname='" + datas.username + "'";
                    statement.executeUpdate(s1);
                }
                else if (frommenu.getText().equals("Toman")) {
                    datas.TMN -= Double.parseDouble(value.getText());
                    String s1 = "UPDATE users SET TMN='" + String.valueOf(datas.TMN) + "' WHERE fullname='" + datas.username + "'";
                    statement.executeUpdate(s1);
                }
                alert.setContentText("سفارش فروش با موفقیت ثبت شد");
            }
            else {
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
                    Statement statement = connection.createStatement();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
                    Date date = Calendar.getInstance().getTime();
                    statement.executeUpdate("INSERT INTO buytable (Type , Amount , Price , Trader , date) VALUES ('"+ frommenu.getText() +"','" + Double.parseDouble(value.getText()) + "','" + Double.parseDouble(price.getText()) + "','" + datas.username + "','" + simpleDateFormat.format(date) +"')");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                datas.total -= Double.parseDouble(value.getText()) * Double.parseDouble(price.getText());
                String s1 = "UPDATE users SET Money='" + String.valueOf(datas.total) + "' WHERE fullname='" + datas.username + "'";
                statement.executeUpdate(s1);
                alert.setContentText("سفارش خرید با موفقیت ثبت شد");
            }
            alert.show();
            Stage stage = (Stage)frommenu.getScene().getWindow();
            stage.close();
        }

    }
}
