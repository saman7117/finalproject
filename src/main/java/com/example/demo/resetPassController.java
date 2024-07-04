package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class resetPassController {
    @FXML
    private PasswordField Cpass;
    @FXML
    private TextField pass;
    public void checkPass() throws SQLException, IOException {
        if(pass.getText().equals(Cpass.getText()) && checkPassword(pass.getText()) == 0){
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
    private int checkPassword(String s){
        char[] VC = {'!','@','#','$','%','^','&','*','(',')','-','+'};
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("خطا در وارد کردن");
        if(s.isEmpty()){
            alert.setContentText("لطفا رمز عبور را وارد کنید");
            alert.showAndWait();
            return 1;
        }
        else if(s.length()<8 || s.length()>20){
            alert.setContentText("طول رمز عبور نامعتبر است");
            alert.showAndWait();
            return 1;
        }
        else{
            Boolean[] flag = new Boolean[4];
            flag[0] = false;
            flag[1] = false;
            flag[2] = false;
            flag[3] = false;
            for(int i=0;i<s.length();i++){
                if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'){
                    flag[0] = true;
                }
                else if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z'){
                    flag[1] = true;
                }
                else if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                    flag[2] = true;
                }
                else{
                    for(int j = 0 ;j<12;j++){
                        if(s.charAt(i)==VC[j]) {
                            flag[3]=true;
                            break;
                        }
                    }
                }
            }
            if(!flag[0]){
                alert.setContentText("رمز عبور باید حداقل یک حرف بزرگ داشته باشد");
                alert.showAndWait();
                return 1;
            }
            if(!flag[1]) {
                alert.setContentText("رمز عبور باید حداقل یک حرف کوچک داشته باشد");
                alert.showAndWait();
                return 1;
            }
            if(!flag[2]){
                alert.setContentText("رمز عبور باید حداقل یک عدد داشته باشد");
                alert.showAndWait();
                return 1;
            }
            if(!flag[3]){
                alert.setContentText("رمز عبور باید حداقل یک کاراکتر داشته باشد(!@#$%^&*()-+)");
                alert.showAndWait();
                return 1;
            }
        }
        return 0;
    }
}
