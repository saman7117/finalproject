package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.sql.*;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class WithdrawController {
    @FXML
    private TextField cardNum;
    @FXML
    private TextField pass;
    @FXML
    private TextField value;
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");

    public WithdrawController() throws SQLException {
    }

    @FXML
    private void initialize() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (Pattern.matches("\\d*", newText)) {
                return change;
            }
            return null;
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        value.setTextFormatter(textFormatter);
        TextFormatter<String> textFormatter2 = new TextFormatter<>(filter);
        cardNum.setTextFormatter(textFormatter2);
        TextFormatter<String> textFormatter3 = new TextFormatter<>(filter);
        pass.setTextFormatter(textFormatter3);

    }

    public void Confirm() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.NONE);
        boolean flag = true;
        if (cardNum.getText().length() != 16){
            flag = false;
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("شماره کارت نامعتبر");
            alert.setContentText("لطفا شماره کارت خود را درست وارد کنید");
            alert.showAndWait();
        }
        if (Integer.parseInt(value.getText()) > 1000000){
            flag = false;
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("مقدار نامعتبر");
            alert.setContentText("لطفا سقف برداشت را رعایت کنید");
            alert.showAndWait();
        }
        if (!(datas.Password.equals(pass.getText()))){
            flag = false;
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("رمز اشتباه است");
            alert.setContentText("لطفا رمز خود را درست وارد کنید");
            alert.showAndWait();
        }
        if (flag){
            String query = "UPDATE users SET Money = Money - ? WHERE fullname = ?";
            PreparedStatement updateUserMoney = connection.prepareStatement(query);
            updateUserMoney.setDouble(1,Double.parseDouble(value.getText()));
            updateUserMoney.setString(2, datas.username);
            updateUserMoney.executeUpdate();
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm");
            alert.setHeaderText("برداشت موفقیت آمیز ");
            alert.setContentText("برداشت موفقیت آمیز بود");
            alert.showAndWait();
            updateHistory();
        }
    }

    public void updateHistory() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO transfer (Type , Value , dude, CardNum) VALUES ('" + "withdraw" + "','" +  value.getText() + "','" + datas.username + "','" + cardNum.getText() + "')");
    }

}
