package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.sql.*;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class paymentController {
    @FXML
    private TextField cardNum;
    @FXML
    private Label imgNum;
    @FXML
    private ImageView cardImage;
    @FXML
    private TextField CVV2;
    @FXML
    private TextField year;
    @FXML
    private TextField month;
    @FXML
    private TextField value;

    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");

    public paymentController() throws SQLException {
    }

    @FXML
    private void initialize(){
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
        CVV2.setTextFormatter(textFormatter3);
        TextFormatter<String> textFormatter4 = new TextFormatter<>(filter);
        year.setTextFormatter(textFormatter4);
        TextFormatter<String> textFormatter5 = new TextFormatter<>(filter);
        month.setTextFormatter(textFormatter5);



        cardNum.textProperty().addListener((observable, oldValue, newValue) -> {
//            if(newValue.length()>4 && newValue.length()<=8){
//                newValue = newValue.substring(0, 4) + " " + newValue.substring(4, x.length());
//            }
            imgNum.setText(newValue);
//            newValue = newValue.replaceAll("\\s+","");
            if(newValue.length()<6){
                setFlagImage("src/main/resources/com/example/demo/cardds.jpg");
            }
            if(newValue.length()==6){
                if(newValue.equals("610433")){
                    setFlagImage("src/main/resources/com/example/demo/melat.jpg");
                }
            }
        });
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
        /*if (CVV2.getText().length() != 3 || CVV2.getText().length() != 4){
            flag = false;
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("CVV2 نامعتبر");
            alert.setContentText("لطفا CVV2 کارت خود را درست وارد کنید");
            alert.showAndWait();
        }*/
        if (year.getText().length() != 2){
            flag = false;
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("سال نامعتبر");
            alert.setContentText("لطفا سال را درست وارد کنید");
            alert.showAndWait();
        }
        if (month.getText().length() != 2){
            flag = false;
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ماه نامعتبر");
            alert.setContentText("لطفا ماه را درست وارد کنید");
            alert.showAndWait();
        }
        if (Integer.parseInt(value.getText()) > 1000000){
            flag = false;
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("مقدار نامعتبر");
            alert.setContentText("لطفا سقف واریز را رعایت کنید");
            alert.showAndWait();
        }
        if (flag){
            String query = "UPDATE users SET Money = Money + ? WHERE fullname = ?";
            PreparedStatement updateUserMoney = connection.prepareStatement(query);
            updateUserMoney.setDouble(1,Double.parseDouble(value.getText()));
            updateUserMoney.setString(2, datas.username);
            updateUserMoney.executeUpdate();
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm");
            alert.setHeaderText("تراکنش موفقیت آمیز ");
            alert.setContentText("تراکنش موفقیت آمیز بود");
            alert.showAndWait();
            updateHistory();
        }

    }
    public void updateHistory() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO transfer (Type , Value , dude) VALUES ('" + "deposit" + "','" +  value.getText() + "','" + datas.username + "')");
    }

    public void setFlagImage(String string){
        File file = new File(string);
        Image image = new Image(file.toURI().toString());
        cardImage.setImage(image);
    }

}
