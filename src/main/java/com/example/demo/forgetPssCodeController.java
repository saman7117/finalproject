package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class forgetPssCodeController {
    @FXML
    private TextField code1;
    @FXML
    private TextField code2;
    @FXML
    private TextField code3;
    @FXML
    private TextField code4;
    public void checkCode() throws SQLException, IOException {
        int a = 0;
        a = (Integer.parseInt(code1.getText()) * 1000) + (Integer.parseInt(code2.getText()) * 100) + (Integer.parseInt(code3.getText()) * 10) + (Integer.parseInt(code4.getText()) * 1);
        System.out.println(a + " " + datas.forgetcode);
        if(a == datas.forgetcode){
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()){
                if(resultSet.getString("email").equals(datas.Gmail)){
                    datas.username = resultSet.getString("fullname");
                    datas.Gmail = resultSet.getString("email");
                    datas.Password = "";
                    datas.phoneNumber = resultSet.getString("mobile");
                    datas.role = resultSet.getString("role");
                    String name = "";
                    String lastname = "";
                    int i = 0;
                    for (; i < datas.username.length(); i++) {
                        if (datas.username.charAt(i) == ' ')
                            break;
                        else
                            name += datas.username.charAt(i);
                    }
                    for (; i < datas.username.length(); i++) {
                        lastname += datas.username.charAt(i);
                    }
                    datas.firstName = name;
                    datas.lastName = lastname;
                    datas.USD = resultSet.getDouble("USD");
                    datas.YEN = resultSet.getDouble("YEN");
                    datas.EUR = resultSet.getDouble("EUR");
                    datas.TMN = resultSet.getDouble("TMN");
                    datas.GBT = resultSet.getDouble("GBT");
                    datas.total = resultSet.getDouble("Money");
                    Stage stage = (Stage) code1.getScene().getWindow();
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("resetPass.fxml"));
                    Scene scene = new Scene(fxmlLoader.load() , 1000 , 600);
                    stage.setTitle("RAZE Exchange");
                    stage.setScene(scene);
                    stage.show();
                    break;
                }
            }
        }
    }

    @FXML
    private void initialize(){
        code1.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=""){
                code2.requestFocus();
            }
        });
        code2.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=""){
                code3.requestFocus();
            }
            else{
                code1.requestFocus();
            }
        });
        code3.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=""){
                code4.requestFocus();
            }
            else{
                code2.requestFocus();
            }
        });
        code4.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=""){
                try {
                    checkCode();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                code3.requestFocus();
            }
        });
    }
}
