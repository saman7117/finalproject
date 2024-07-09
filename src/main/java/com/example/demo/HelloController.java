package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField userfield;
    @FXML
    private PasswordField passfield;
    @FXML
    private Label warn;
    @FXML
    private Label captchalabel;
    @FXML
    private TextField captchafield;
    @FXML
    private ImageView refreshCaptcha;
    private String currentCaptcha;


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

    public void RefreshCaptcha(){
        setCAPTCHAlabel();
    }
    public void setCAPTCHAlabel(){
        captchalabel.setText(generateCaptcha());
    }

    public void goHomePage() throws IOException{
        String u = userfield.getText();
        String p = passfield.getText();
        boolean flag = false;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()){
                if(u.equals(resultSet.getString("username")) && p.equals(resultSet.getString("password")) && captchafield.getText().equals(captchalabel.getText())){
                    flag = true;
                    Stage stage = (Stage)userfield.getScene().getWindow();
                    stage.close();

                    datas.username = resultSet.getString("fullname");
                    datas.Gmail = resultSet.getString("email");
                    datas.Password = p;
                    datas.phoneNumber = resultSet.getString("mobile");
                    datas.role = resultSet.getString("role");
                    if (datas.role.equals("admin")){
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 1536, 800);
                        stage.setTitle("Raze Exchange");
                        stage.setScene(scene);
                    }
                    else if (datas.role.equals("trader")){
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 1536, 800);
                        stage.setTitle("Raze Exchange");
                        stage.setScene(scene);
                    }
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

    public void demologin() throws SQLException, IOException {
        String s = "";
        Random rnd = new Random();
        int g = rnd.nextInt(10000);
        int gg = rnd.nextInt(100);
        datas.username = "user"+String.valueOf(g)+"_"+String.valueOf(gg);
        datas.firstName = "demo";
        datas.lastName = "user";
        datas.USD = 0;
        datas.YEN = 0;
        datas.EUR = 0;
        datas.TMN = 0;
        datas.GBT = 0;
        datas.total = 5000;
        datas.Gmail = "";
        datas.Password = "123456789";
        datas.phoneNumber = "";
        datas.role = "demo";
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO users (username,password,email,fullname,mobile,USD,EUR,GBT,TMN,YEN,Money , role) VALUES ('" + datas.username + "','" + datas.Password + "','" + datas.Gmail + "','" + datas.username + "','" + datas.phoneNumber + "','" + datas.USD + "','" + datas.EUR + "','" + datas.TMN + "','" + datas.GBT + "','" + datas.YEN + "','" + datas.total + "','" + datas.role +"')");
        Stage stage = (Stage)userfield.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1536, 800);
        stage.setTitle("Raze Exchange");
        stage.setScene(scene);
        stage.show();
    }


    private String generateCaptcha() {
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            captcha.append(characters.charAt(index));
        }
        currentCaptcha = captcha.toString();
        return currentCaptcha;
    }
    public void gotoforgetPass() throws IOException {
        Stage stage = (Stage)welcomeText.getScene().getWindow();
        stage.close();
        FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("forgetPass.fxml"));
        Scene registerScene = new Scene(registerLoader.load(), 1000, 600);
        stage.setTitle("Raze Exchange");
        stage.setScene(registerScene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCAPTCHAlabel();
    }
}