package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class resetPassController {
    @FXML
    private PasswordField Cpass;
    @FXML
    private TextField pass;
    public void checkPass() throws SQLException, IOException {
        if(pass.getText().equals(Cpass.getText())){
            datas.Password = pass.getText();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            String s1 = "UPDATE users SET password='" + pass.getText() + "' WHERE fullname='" + datas.username + "'";
            statement.executeUpdate(s1);
            Stage stage = (Stage) pass.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));
            Scene scene = new Scene(fxmlLoader.load() , 1536 , 864);
            stage.setTitle("RAZE Exchange");
            stage.setScene(scene);
            stage.show();
        }
    }
}
