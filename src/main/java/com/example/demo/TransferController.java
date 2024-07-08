package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.sql.*;

public class TransferController {
    @FXML
    private TextField usernamee;
    @FXML
    private TextField value;
    @FXML
    private MenuButton menu;

    public TransferController() throws SQLException {
    }

    public void setmenutexttoUSD(){
        menu.setText("USD");
    }
    public void setmenutexttoTMN(){
        menu.setText("Toman");
    }
    public void setmenutexttoEUR(){
        menu.setText("EUR");
    }
    public void setmenutexttoYEN(){
        menu.setText("YEN");
    }
    public void setmenutexttoGBT(){
        menu.setText("GBT");
    }

    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");

    public void Transfer() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        double status = 0;
        Statement statement2 = connection.createStatement();
        ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM users");
        while (resultSet2.next()){
            if (resultSet2.getString("role").equals("admin")){
                status = resultSet2.getDouble("USD");
            }
        }
        if (status == 0) {
            if(!findusername()){
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("عدم تطابق ");
                alert.setContentText("نام کاربری در بانک اطلاعاتی موجود نیست!");
                alert.showAndWait();
            }
            else {
                if (menu.getText().equals("USD") && datas.USD >= Double.parseDouble(value.getText())) {
                    datas.USD -= Double.parseDouble(value.getText());

                    PreparedStatement updateUserMoney = connection.prepareStatement("UPDATE users SET USD = USD + ? WHERE username = ? ");
                    updateUserMoney.setDouble(1, Double.parseDouble(value.getText()));
                    updateUserMoney.setString(2, usernamee.getText());
                    updateUserMoney.executeUpdate();

                    String s1 = "UPDATE users SET USD='" + String.valueOf(datas.USD) + "' WHERE fullname='" + datas.username + "'";
                    statement.executeUpdate(s1);

                    updateHistory();
                }
                if (menu.getText().equals("Toman") && datas.TMN >= Double.parseDouble(value.getText())) {
                    datas.TMN -= Double.parseDouble(value.getText());

                    PreparedStatement updateUserMoney = connection.prepareStatement("UPDATE users SET TMN = TMN + ? WHERE username = ? ");
                    updateUserMoney.setDouble(1, Double.parseDouble(value.getText()));
                    updateUserMoney.setString(2, usernamee.getText());
                    updateUserMoney.executeUpdate();

                    String s1 = "UPDATE users SET TMN='" + String.valueOf(datas.TMN) + "' WHERE fullname='" + datas.username + "'";
                    statement.executeUpdate(s1);

                    updateHistory();
                }
                if (menu.getText().equals("EUR") && datas.EUR >= Double.parseDouble(value.getText())) {
                    datas.EUR -= Double.parseDouble(value.getText());

                    PreparedStatement updateUserMoney = connection.prepareStatement("UPDATE users SET EUR = EUR + ? WHERE username = ? ");
                    updateUserMoney.setDouble(1, Double.parseDouble(value.getText()));
                    updateUserMoney.setString(2, usernamee.getText());
                    updateUserMoney.executeUpdate();

                    String s1 = "UPDATE users SET EUR='" + String.valueOf(datas.EUR) + "' WHERE fullname='" + datas.username + "'";
                    statement.executeUpdate(s1);

                    updateHistory();
                }
                if (menu.getText().equals("YEN") && datas.YEN >= Double.parseDouble(value.getText())) {
                    datas.YEN -= Double.parseDouble(value.getText());

                    PreparedStatement updateUserMoney = connection.prepareStatement("UPDATE users SET YEN = YEN + ? WHERE username = ? ");
                    updateUserMoney.setDouble(1, Double.parseDouble(value.getText()));
                    updateUserMoney.setString(2, usernamee.getText());
                    updateUserMoney.executeUpdate();

                    String s1 = "UPDATE users SET YEN='" + String.valueOf(datas.YEN) + "' WHERE fullname='" + datas.username + "'";
                    statement.executeUpdate(s1);

                    updateHistory();
                }
                if (menu.getText().equals("GBT") && datas.GBT >= Double.parseDouble(value.getText())) {
                    datas.GBT -= Double.parseDouble(value.getText());

                    PreparedStatement updateUserMoney = connection.prepareStatement("UPDATE users SET GBT = GBT + ? WHERE username = ? ");
                    updateUserMoney.setDouble(1, Double.parseDouble(value.getText()));
                    updateUserMoney.setString(2, usernamee.getText());
                    updateUserMoney.executeUpdate();

                    String s1 = "UPDATE users SET GBT='" + String.valueOf(datas.GBT) + "' WHERE fullname='" + datas.username + "'";
                    statement.executeUpdate(s1);

                    updateHistory();
                }
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("بازار بسته ");
            alert.setContentText("بازار توسط ادمین بسته شده است!");
            alert.showAndWait();
        }
    }
    public void updateHistory() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO transfer (Amount , Type , fromdude , todude) VALUES ('" + value.getText() + "','" +  menu.getText() + "','" + datas.username + "','" + usernamee.getText() +"')");
    }

    public boolean findusername() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        while (resultSet.next()){
            if(resultSet.getString("username").equals(usernamee.getText()))
                return true;
        }
        return false;
    }

}
