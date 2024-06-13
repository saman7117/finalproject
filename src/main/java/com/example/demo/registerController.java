package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.sql.DriverManager;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class registerController {

    @FXML
    private TextField user;
    @FXML
    private PasswordField pass;
    @FXML
    private PasswordField Cpass;
    @FXML
    private TextField mail;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField mob;

    @FXML
    private Label userlable;

    @FXML
    private Label passlable;

    @FXML
    private Label Cpasslable;

    @FXML
    private Label mlable;

    @FXML
    private Label flable;

    @FXML
    private Label llable;

    @FXML
    private Label maillable;


    @FXML
    protected void Register() throws IOException {
        int a = checkUsername(user.getText());
        int b = checkPassword(pass.getText());
        int c,d,ee,f,g;
        if(pass.getText().equals(Cpass.getText())) {
            Cpasslable.setText("");
            c = 0;
        }
        else if(Cpass.getText().isEmpty()){
            Cpasslable.setText("Confirm your password .");
            Cpasslable.setTextFill(Color.RED);
            c=1;
        }
        else{
            Cpasslable.setText("Confirm password doesn't match password .");
            Cpasslable.setTextFill(Color.RED);
            c=1;
        }
        if(mob.getText().length()<11) {
            mlable.setText("invalid mobile number");
            mlable.setTextFill(Color.RED);
            d=1;
        }
        else if(mob.getText().isEmpty()){
            mlable.setText("mobile is required .");
            mlable.setTextFill(Color.RED);
            d=1;
        }
        else{
            mlable.setText("");
            d=0;
        }
        if(fname.getText().isEmpty()){
            flable.setText("first name is required .");
            flable.setTextFill(Color.RED);
            ee=1;
        }
        else{
            flable.setText("");
            ee=0;
        }
        if(lname.getText().isEmpty()){
            llable.setText("last name is required .");
            llable.setTextFill(Color.RED);
            f=1;
        }
        else{
            llable.setText("");
            f=0;
        }
        if(mail.getText().isEmpty()){
            maillable.setText("Email is required .");
            maillable.setTextFill(Color.RED);
            f=1;
        }
        else{
            maillable.setText("");
            f=0;
        }
        if(a==0){
            userlable.setText("");
        }
        if(b==0){
            passlable.setText("");
        }
        if(a+b+c+d+ee+f == 0) {
            String n = fname.getText();
            n = n.substring(0,1).toUpperCase() + n.substring(1);
            String l = lname.getText();
            l = l.substring(0,1).toUpperCase() + l.substring(1);
            String full = n + " " + l;
            java.sql.Connection connection;
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
                Statement statement = connection.createStatement();
                statement.executeUpdate("INSERT INTO users (username,password,email,fullname,mobile,USD,EUR,GBT,TMN,YEN,Money) VALUES ('" + user.getText() + "','" + pass.getText() + "','" + mail.getText() + "','" + full + "','" + mob.getText() + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 +"')");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) userlable.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load() , 1000 , 600);
            stage.setTitle("RAZE Exchange");
            stage.setScene(scene);
            stage.show();
        }
    }
    private int checkUsername(String s){
        if(s.isEmpty()){
            userlable.setText("Username is required .");
            userlable.setTextFill(Color.RED);
            return 1;
        }
        else if(s.length()<6 || s.length()>20){
            userlable.setText("Username length must be between 6 and 20 character .");
            userlable.setTextFill(Color.RED);
            return 1;
        }
        else{
            for(int i=0;i<s.length();i++){
                if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
                    continue;
                if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')
                    continue;
                if(s.charAt(i) >= '0' && s.charAt(i) <= '9')
                    continue;
                if(s.charAt(i) == '_')
                    continue;
                userlable.setText("invalid character in username.");
                userlable.setTextFill(Color.RED);
                return 1;
            }
        }
        return 0;
    }
    private int checkPassword(String s){
        char[] VC = {'!','@','#','$','%','^','&','*','(',')','-','+'};
        if(s.isEmpty()){
            passlable.setText("Password is required .");
            passlable.setTextFill(Color.RED);
            return 1;
        }
        else if(s.length()<8 || s.length()>20){
            passlable.setText("password length must be between 6 and 20 character .");
            passlable.setTextFill(Color.RED);
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
                passlable.setText("Password must contain at least one capital letter");
                passlable.setTextFill(Color.RED);
                return 1;
            }
            if(!flag[1]) {
                passlable.setText("Password must contain at least one small letter");
                passlable.setTextFill(Color.RED);
                return 1;
            }
            if(!flag[2]){
                passlable.setText("Password must contain at least one number");
                passlable.setTextFill(Color.RED);
                return 1;
            }
            if(!flag[3]){
                passlable.setText("Password must contain at least one character (!@#$%^&*()-+)");
                passlable.setTextFill(Color.RED);
                return 1;
            }
        }
        return 0;
    }

    public void goSignIn() throws IOException{
        Stage stage = (Stage) userlable.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load() , 1000 , 600);
        stage.setTitle("RAZE Exchange");
        stage.setScene(scene);
        stage.show();
    }
}
