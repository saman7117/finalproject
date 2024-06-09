package com.example.demo;

import javafx.fxml.FXML;
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

    public void Transfer() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        if(menu.getText().equals("USD") && datas.USD >= Integer.parseInt(value.getText())){
            datas.USD -= Integer.parseInt(value.getText());
            double d = 0;
            while(resultSet.next()){
                if(usernamee.getText().equals(resultSet.getString("username"))){
                    d+=Double.parseDouble(resultSet.getString("USD"));
                }
            }
            d+=Double.parseDouble(value.getText());
            String s1 = "UPDATE users SET USD='" + String.valueOf(datas.USD) + "' WHERE fullname='" + datas.username + "'";
            statement.executeUpdate(s1);
            String s2 = "UPDATE users SET USD='" + String.valueOf(d) + "' WHERE username='" + usernamee.getText() + "'";
            statement.executeUpdate(s2);
        }
        if(menu.getText().equals("Toman") && datas.TMN >= Integer.parseInt(value.getText())){
            datas.TMN -= Integer.parseInt(value.getText());
            double d = 0;
            while(resultSet.next()){
                if(usernamee.getText().equals(resultSet.getString("username"))){
                    d+=Double.parseDouble(resultSet.getString("Toman"));
                }
            }
            d+=Double.parseDouble(value.getText());
            String s1 = "UPDATE users SET TMN='" + String.valueOf(datas.TMN) + "' WHERE fullname='" + datas.username + "'";
            statement.executeUpdate(s1);
            String s2 = "UPDATE users SET TMN='" + String.valueOf(d) + "' WHERE username='" + usernamee.getText() + "'";
            statement.executeUpdate(s2);
        }
        if(menu.getText().equals("EUR") && datas.EUR >= Integer.parseInt(value.getText())){
            datas.EUR -= Integer.parseInt(value.getText());
            double d = 0;
            while(resultSet.next()){
                if(usernamee.getText().equals(resultSet.getString("username"))){
                    d+=Double.parseDouble(resultSet.getString("EUR"));
                }
            }
            d+=Double.parseDouble(value.getText());
            String s1 = "UPDATE users SET EUR='" + String.valueOf(datas.USD) + "' WHERE fullname='" + datas.username + "'";
            statement.executeUpdate(s1);
            String s2 = "UPDATE users SET EUR='" + String.valueOf(d) + "' WHERE username='" + usernamee.getText() + "'";
            statement.executeUpdate(s2);
        }
        if(menu.getText().equals("YEN") && datas.YEN >= Integer.parseInt(value.getText())){
            datas.YEN -= Integer.parseInt(value.getText());
            double d = 0;
            while(resultSet.next()){
                if(usernamee.getText().equals(resultSet.getString("username"))){
                    d+=Double.parseDouble(resultSet.getString("YEN"));
                }
            }
            d+=Double.parseDouble(value.getText());
            String s1 = "UPDATE users SET YEN='" + String.valueOf(datas.USD) + "' WHERE fullname='" + datas.username + "'";
            statement.executeUpdate(s1);
            String s2 = "UPDATE users SET YEN='" + String.valueOf(d) + "' WHERE username='" + usernamee.getText() + "'";
            statement.executeUpdate(s2);
        }
        if(menu.getText().equals("GBT") && datas.GBT >= Integer.parseInt(value.getText())){
            datas.GBT -= Integer.parseInt(value.getText());
            double d = 0;
            while(resultSet.next()){
                if(usernamee.getText().equals(resultSet.getString("username"))){
                    d+=Double.parseDouble(resultSet.getString("GBT"));
                }
            }
            d+=Double.parseDouble(value.getText());
            String s1 = "UPDATE users SET GBT='" + String.valueOf(datas.GBT) + "' WHERE fullname='" + datas.username + "'";
            statement.executeUpdate(s1);
            String s2 = "UPDATE users SET GBT='" + String.valueOf(d) + "' WHERE username='" + usernamee.getText() + "'";
            statement.executeUpdate(s2);
        }
    }
}
