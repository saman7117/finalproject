package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField userfield;
    @FXML
    private PasswordField passfield;
    @FXML
    private Label warn;


    @FXML
    protected void onHelloButtonClick() throws IOException {
        Stage stage = (Stage)welcomeText.getScene().getWindow();
        stage.close();
        FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
        Scene registerScene = new Scene(registerLoader.load(), 1000, 600);
        stage.setTitle("Raze Exchange");
        stage.setScene(registerScene);
        stage.show();
    }

    public void goHomePage() throws IOException{
        /*Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1536, 864);
        stage.setTitle("Raze Exchange");
        stage.setScene(scene);
        stage.show();*/
        String u = userfield.getText();
        String p = passfield.getText();
        boolean flag = false;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()){
//                System.out.println(u + " ::: " + resultSet.getString("username"));
//                System.out.println(p + " ::: " + resultSet.getString("password"));
                if(u.equals(resultSet.getString("username")) && p.equals(resultSet.getString("password"))){
                    flag = true;
                    Stage stage = (Stage)userfield.getScene().getWindow();
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1536, 864);
                    stage.setTitle("Raze Exchange");
                    stage.setScene(scene);
                    stage.show();
                    break;
                }
            }
            if(!flag){
                warn.setText("not found in the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}