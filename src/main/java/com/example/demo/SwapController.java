package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.sql.*;
import java.text.DecimalFormat;
import java.time.format.DecimalStyle;

public class SwapController {
    @FXML
    private TextField fromvalue;
    @FXML
    private Label tovalue;
    @FXML
    private MenuButton frommenu;
    @FXML
    private MenuButton tomenu;
    @FXML
    private ImageView swapbtn;
    @FXML
    private Button confirmbtn;
    @FXML
    private MenuItem USDitem;
    @FXML
    private MenuItem TMNitem;
    @FXML
    private MenuItem YENitem;
    @FXML
    private MenuItem GBTitem;
    @FXML
    private MenuItem EURitem;
    @FXML
    private Label warn;
    @FXML
    private Label swapcom;
    @FXML
    private Label commision;
    @FXML
    private Label losemoney;
    @FXML
    private Label gainmoney;

    public SwapController() throws SQLException {
    }
    public void setmenutexttoUSD(){
        frommenu.setText("USD");
        double convertedAmount = convertCurrency(Double.parseDouble(fromvalue.getText()),frommenu.getText(),tomenu.getText()); // Implement your currency conversion logic here
        tovalue.setText(String.valueOf(convertedAmount));
        setCOM();
        setCommision();
        setLoseandGain();
    }
    public void setmenutexttoTMN(){
        frommenu.setText("Toman");
        double convertedAmount = convertCurrency(Double.parseDouble(fromvalue.getText()),frommenu.getText(),tomenu.getText()); // Implement your currency conversion logic here
        tovalue.setText(String.valueOf(convertedAmount));
        setCOM();
        setCommision();
        setLoseandGain();
    }
    public void setmenutexttoEUR(){
        frommenu.setText("EUR");
        double convertedAmount = convertCurrency(Double.parseDouble(fromvalue.getText()),frommenu.getText(),tomenu.getText()); // Implement your currency conversion logic here
        tovalue.setText(String.valueOf(convertedAmount));
        setCOM();
        setCommision();
        setLoseandGain();
    }
    public void setmenutexttoYEN(){
        frommenu.setText("YEN");
        double convertedAmount = convertCurrency(Double.parseDouble(fromvalue.getText()),frommenu.getText(),tomenu.getText()); // Implement your currency conversion logic here
        tovalue.setText(String.valueOf(convertedAmount));
        setCOM();
        setCommision();
        setLoseandGain();
    }
    public void setmenutexttoGBT(){
        frommenu.setText("GBT");
        double convertedAmount = convertCurrency(Double.parseDouble(fromvalue.getText()),frommenu.getText(),tomenu.getText()); // Implement your currency conversion logic here
        tovalue.setText(String.valueOf(convertedAmount));
        setCOM();
        setCommision();
        setLoseandGain();
    }

    public void setmenutextto2USD(){
        tomenu.setText("USD");
        double convertedAmount = convertCurrency(Double.parseDouble(fromvalue.getText()),frommenu.getText(),tomenu.getText()); // Implement your currency conversion logic here
        tovalue.setText(String.valueOf(convertedAmount));
        setCOM();
        setCommision();
        setLoseandGain();
    }
    public void setmenutextto2TMN(){
        tomenu.setText("Toman");
        double convertedAmount = convertCurrency(Double.parseDouble(fromvalue.getText()),frommenu.getText(),tomenu.getText()); // Implement your currency conversion logic here
        tovalue.setText(String.valueOf(convertedAmount));
        setCOM();
        setCommision();
        setLoseandGain();
    }
    public void setmenutextto2EUR(){
        tomenu.setText("EUR");
        double convertedAmount = convertCurrency(Double.parseDouble(fromvalue.getText()),frommenu.getText(),tomenu.getText()); // Implement your currency conversion logic here
        tovalue.setText(String.valueOf(convertedAmount));
        setCOM();
        setCommision();
        setLoseandGain();
    }
    public void setmenutextto2YEN(){
        tomenu.setText("YEN");
        double convertedAmount = convertCurrency(Double.parseDouble(fromvalue.getText()),frommenu.getText(),tomenu.getText()); // Implement your currency conversion logic here
        tovalue.setText(String.valueOf(convertedAmount));
        setCOM();
        setCommision();
        setLoseandGain();
    }
    public void setmenutextto2GBT(){
        tomenu.setText("GBT");
        double convertedAmount = convertCurrency(Double.parseDouble(fromvalue.getText()),frommenu.getText(),tomenu.getText()); // Implement your currency conversion logic here
        tovalue.setText(String.valueOf(convertedAmount));
        setCOM();
        setCommision();
        setLoseandGain();
    }

    public void SwapFT(){
        String temp = frommenu.getText();
        frommenu.setText(tomenu.getText());
        tomenu.setText(temp);
        temp = fromvalue.getText();
        fromvalue.setText(tovalue.getText());
        tovalue.setText(temp);
    }

    @FXML
    private void initialize(){
        fromvalue.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.isEmpty()){
                double amount = Double.parseDouble(newValue); // Assuming the input is a valid number
                double convertedAmount = convertCurrency(amount,frommenu.getText(),tomenu.getText()); // Implement your currency conversion logic here
                tovalue.setText(String.valueOf(convertedAmount));
                setCOM();
                setCommision();
                setLoseandGain();
            }
        });
    }

    public void setCOM(){
        String s;
        if(frommenu.getText().equals("USD")){
            s = "1 USD = ";
            if(tomenu.getText().equals("USD")){
                s+="1 USD";
            }
            else if(tomenu.getText().equals("Toman")){
                s+=String.valueOf(datas.TMNPrice) + " Toman";
            }
            else if(tomenu.getText().equals("EUR")){
                s+=String.valueOf(datas.EURPrice) + " EUR";
            }
            else if(tomenu.getText().equals("YEN")){
                s+=String.valueOf(datas.YENPrice) + " YEN";
            }
            else{
                s+=String.valueOf(datas.GBPPrice) + " GBP";
            }
            swapcom.setText(s);
        }
        else if(frommenu.getText().equals("Toman")){
            s = "1 Toman = ";
            if(tomenu.getText().equals("Toman")){
                s+="1 Toman";
            }
            else if(tomenu.getText().equals("USD")){
                s+=String.valueOf(datas.USDPrice) + " USD";
            }
            else if(tomenu.getText().equals("EUR")){
                s+=String.valueOf(datas.EURPrice) + " EUR";
            }
            else if(tomenu.getText().equals("YEN")){
                s+=String.valueOf(datas.YENPrice) + " YEN";
            }
            else{
                s+=String.valueOf(datas.GBPPrice) + " GBP";
            }
            swapcom.setText(s);
        }
        else if(frommenu.getText().equals("EUR")){
            s = "1 EUR = ";
            if(tomenu.getText().equals("EUR")){
                s+="1 EUR";
            }
            else if(tomenu.getText().equals("Toman")){
                s+=String.valueOf(datas.TMNPrice) + " Toman";
            }
            else if(tomenu.getText().equals("USD")){
                s+=String.valueOf(datas.USDPrice) + " USD";
            }
            else if(tomenu.getText().equals("YEN")){
                s+=String.valueOf(datas.YENPrice) + " YEN";
            }
            else{
                s+=String.valueOf(datas.GBPPrice) + " GBP";
            }
            swapcom.setText(s);
        }
        else if(frommenu.getText().equals("YEN")){
            s = "1 YEN = ";
            if(tomenu.getText().equals("YEN")){
                s+="1 YEN";
            }
            else if(tomenu.getText().equals("Toman")){
                s+=String.valueOf(datas.TMNPrice) + " Toman";
            }
            else if(tomenu.getText().equals("EUR")){
                s+=String.valueOf(datas.EURPrice) + " EUR";
            }
            else if(tomenu.getText().equals("USD")){
                s+=String.valueOf(datas.USDPrice) + " USD";
            }
            else{
                s+=String.valueOf(datas.GBPPrice) + " GBP";
            }
            swapcom.setText(s);
        }
        else{
            s = "1 GBP = ";
            if(tomenu.getText().equals("GPP")){
                s+="1 GBP";
            }
            else if(tomenu.getText().equals("Toman")){
                s+=String.valueOf(datas.TMNPrice) + " Toman";
            }
            else if(tomenu.getText().equals("EUR")){
                s+=String.valueOf(datas.EURPrice) + " EUR";
            }
            else if(tomenu.getText().equals("YEN")){
                s+=String.valueOf(datas.YENPrice) + " YEN";
            }
            else{
                s+=String.valueOf(datas.USDPrice) + " USD";
            }
            swapcom.setText(s);
        }
    }

    public void setCommision(){
        if(frommenu.getText().equals("USD")){
            commision.setText(String.valueOf(datas.USDPrice/100) + '$');
        }
        else if(frommenu.getText().equals("YEN")){
            commision.setText(String.valueOf(datas.YENPrice/100) + '$');
        }
        else if(frommenu.getText().equals("Toman")){
            commision.setText(String.valueOf(datas.TMNPrice/100) + '$');
        }
        else if(frommenu.getText().equals("EUR")){
            commision.setText(String.valueOf(datas.EURPrice/100) + '$');
        }
        else{
            commision.setText(String.valueOf(datas.GBPPrice/100) + '$');
        }
    }

    public void setLoseandGain(){
        if(frommenu.getText().equals("USD")){
            losemoney.setText(String.valueOf(datas.USDPrice * Double.parseDouble(fromvalue.getText())));
            if(tomenu.getText().equals("USD")){
                gainmoney.setText(String.valueOf(datas.USDPrice * Double.parseDouble(tovalue.getText())));
            }
            else if(tomenu.getText().equals("Toman")){
                gainmoney.setText(String.valueOf(datas.TMNPrice * Double.parseDouble(tovalue.getText())));
            }
            else if(tomenu.getText().equals("EUR")){
                gainmoney.setText(String.valueOf(datas.EURPrice * Double.parseDouble(tovalue.getText())));
            }
            else if(tomenu.getText().equals("YEN")){
                gainmoney.setText(String.valueOf(datas.YENPrice * Double.parseDouble(tovalue.getText())));
            }
            else{
                gainmoney.setText(String.valueOf(datas.GBPPrice * Double.parseDouble(tovalue.getText())));
            }
        }
        else if(frommenu.getText().equals("Toman")){
            losemoney.setText(String.valueOf(datas.TMNPrice * Double.parseDouble(fromvalue.getText())));
            if(tomenu.getText().equals("Toman")){
                gainmoney.setText(String.valueOf(datas.TMNPrice * Double.parseDouble(tovalue.getText())));
            }
            else if(tomenu.getText().equals("USD")){
                gainmoney.setText(String.valueOf(datas.USDPrice * Double.parseDouble(tovalue.getText())));
            }
            else if(tomenu.getText().equals("EUR")){
                gainmoney.setText(String.valueOf(datas.EURPrice * Double.parseDouble(tovalue.getText())));
            }
            else if(tomenu.getText().equals("YEN")){
                gainmoney.setText(String.valueOf(datas.YENPrice * Double.parseDouble(tovalue.getText())));
            }
            else{
                gainmoney.setText(String.valueOf(datas.GBPPrice * Double.parseDouble(tovalue.getText())));
            }
        }
        else if(frommenu.getText().equals("EUR")){
            losemoney.setText(String.valueOf(datas.EURPrice * Double.parseDouble(fromvalue.getText())));
            if(tomenu.getText().equals("Toman")){
                gainmoney.setText(String.valueOf(datas.TMNPrice * Double.parseDouble(tovalue.getText())));
            }
            else if(tomenu.getText().equals("USD")){
                gainmoney.setText(String.valueOf(datas.USDPrice * Double.parseDouble(tovalue.getText())));
            }
            else if(tomenu.getText().equals("EUR")){
                gainmoney.setText(String.valueOf(datas.EURPrice * Double.parseDouble(tovalue.getText())));
            }
            else if(tomenu.getText().equals("YEN")){
                gainmoney.setText(String.valueOf(datas.YENPrice * Double.parseDouble(tovalue.getText())));
            }
            else{
                gainmoney.setText(String.valueOf(datas.GBPPrice * Double.parseDouble(tovalue.getText())));
            }
        }
        else if(frommenu.getText().equals("YEN")){
            losemoney.setText(String.valueOf(datas.YENPrice * Double.parseDouble(fromvalue.getText())));
            if(tomenu.getText().equals("Toman")){
                gainmoney.setText(String.valueOf(datas.TMNPrice * Double.parseDouble(tovalue.getText())));
            }
            else if(tomenu.getText().equals("USD")){
                gainmoney.setText(String.valueOf(datas.USDPrice * Double.parseDouble(tovalue.getText())));
            }
            else if(tomenu.getText().equals("EUR")){
                gainmoney.setText(String.valueOf(datas.EURPrice * Double.parseDouble(tovalue.getText())));
            }
            else if(tomenu.getText().equals("YEN")){
                gainmoney.setText(String.valueOf(datas.YENPrice * Double.parseDouble(tovalue.getText())));
            }
            else{
                gainmoney.setText(String.valueOf(datas.GBPPrice * Double.parseDouble(tovalue.getText())));
            }
        }
        else{
            losemoney.setText(String.valueOf(datas.GBPPrice * Double.parseDouble(fromvalue.getText())));
            if(tomenu.getText().equals("Toman")){
                gainmoney.setText(String.valueOf(datas.TMNPrice * Double.parseDouble(tovalue.getText())));
            }
            else if(tomenu.getText().equals("USD")){
                gainmoney.setText(String.valueOf(datas.USDPrice * Double.parseDouble(tovalue.getText())));
            }
            else if(tomenu.getText().equals("EUR")){
                gainmoney.setText(String.valueOf(datas.EURPrice * Double.parseDouble(tovalue.getText())));
            }
            else if(tomenu.getText().equals("YEN")){
                gainmoney.setText(String.valueOf(datas.YENPrice * Double.parseDouble(tovalue.getText())));
            }
            else{
                gainmoney.setText(String.valueOf(datas.GBPPrice * Double.parseDouble(tovalue.getText())));
            }
        }
    }

    private double convertCurrency(double amount, String t1, String t2) {
        double a = 0;
        DecimalFormat df = new DecimalFormat("#.##");
        if(t1.equals("USD")){
            a = amount/datas.USDPrice;
            if(t2.equals("Toman")){
                a*=datas.TMNPrice;
            }
            else if(t2.equals("YEN")){
                a*=datas.YENPrice;
            }
            else if(t2.equals("GBT")){
                a*=datas.GBPPrice;
            }
            else if(t2.equals("EUR")){
                a*=datas.EURPrice;
            }
            else if(t2.equals("USD")){
                a*=datas.USDPrice;
            }
        }
        else if(t1.equals("Toman")){
            a = amount/datas.TMNPrice;
            if(t2.equals("Toman")){
                a*=datas.TMNPrice;
            }
            else if(t2.equals("YEN")){
                a*=datas.YENPrice;
            }
            else if(t2.equals("GBT")){
                a*=datas.GBPPrice;
            }
            else if(t2.equals("EUR")){
                a*=datas.EURPrice;
            }
            else if(t2.equals("USD")){
                a*=datas.USDPrice;
            }
        }
        else if(t1.equals("GBT")){
            a = amount/datas.GBPPrice;
            if(t2.equals("Toman")){
                a*=datas.TMNPrice;
            }
            else if(t2.equals("YEN")){
                a*=datas.YENPrice;
            }
            else if(t2.equals("GBT")){
                a*=datas.GBPPrice;
            }
            else if(t2.equals("EUR")){
                a*=datas.EURPrice;
            }
            else if(t2.equals("USD")){
                a*=datas.USDPrice;
            }
        }
        else if(t1.equals("EUR")){
            a = amount/datas.EURPrice;
            if(t2.equals("Toman")){
                a*=datas.TMNPrice;
            }
            else if(t2.equals("YEN")){
                a*=datas.YENPrice;
            }
            else if(t2.equals("GBT")){
                a*=datas.GBPPrice;
            }
            else if(t2.equals("EUR")){
                a*=datas.EURPrice;
            }
            else if(t2.equals("USD")){
                a*=datas.USDPrice;
            }
        }
        else if(t1.equals("YEN")){
            a = amount/datas.YENPrice;
            if(t2.equals("Toman")){
                a*=datas.TMNPrice;
            }
            else if(t2.equals("YEN")){
                a*=datas.YENPrice;
            }
            else if(t2.equals("GBT")){
                a*=datas.GBPPrice;
            }
            else if(t2.equals("EUR")){
                a*=datas.EURPrice;
            }
            else if(t2.equals("USD")){
                a*=datas.USDPrice;
            }
        }
        a = Double.parseDouble(df.format(a));
        return a;
    }

    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");

    public void Confirm() throws SQLException {
        double adminmoney = 0;
        boolean flag = false;
        Alert alert = new Alert(Alert.AlertType.NONE);
        double status = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        while (resultSet.next()){
            if (resultSet.getString("role").equals("admin")){
                status = resultSet.getDouble("USD");
            }
        }
        if (status == 0) {
            if (frommenu.getText().equals("USD")) {
                if (datas.USD >= Double.parseDouble(fromvalue.getText()) && datas.total >= (Double.parseDouble(fromvalue.getText()) * datas.USDPrice) / 100) {
                    if (tomenu.getText().equals("Toman")) {
                        datas.USD -= Double.parseDouble(fromvalue.getText());
                        datas.TMN += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET USD = USD - ? , TMN = TMN + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                        updateHistory();
                    } else if (tomenu.getText().equals("YEN")) {
                        datas.USD -= Double.parseDouble(fromvalue.getText());
                        datas.YEN += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET USD = USD - ? , YEN = YEN + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                        updateHistory();
                    } else if (tomenu.getText().equals("GBT")) {
                        datas.USD -= Double.parseDouble(fromvalue.getText());
                        datas.GBT += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET USD = USD - ? , GBT = GBT + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                        updateHistory();
                    } else if (tomenu.getText().equals("EUR")) {
                        datas.USD -= Double.parseDouble(fromvalue.getText());
                        datas.EUR += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET USD = USD - ? , EUR = EUR + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                        updateHistory();
                    } else if (tomenu.getText().equals("USD")) {
                        warn.setText("ارزها یکسان است");
                    }
                    adminmoney = datas.USDPrice / 100;
                    String query2 = "UPDATE users SET Money = Money + ? WHERE role = ?";
                    PreparedStatement updateAdminMoney = connection.prepareStatement(query2);
                    updateAdminMoney.setDouble(1, adminmoney);
                    updateAdminMoney.setString(2, "admin");
                    updateAdminMoney.executeUpdate();
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirm");
                    alert.setHeaderText("تبادل موفقیت آمیز ");
                    alert.setContentText("تبادل موفقیت آمیز بود");
                    alert.showAndWait();
                }
            }
            else if (frommenu.getText().equals("Toman")) {
                if (datas.TMN >= Double.parseDouble(fromvalue.getText()) && datas.total >= (Double.parseDouble(fromvalue.getText()) * datas.TMNPrice) / 100) {
                    if (tomenu.getText().equals("USD")) {
                        datas.TMN -= Double.parseDouble(fromvalue.getText());
                        datas.USD += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET TMN = TMN - ? , USD = USD + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                        updateHistory();
                    } else if (tomenu.getText().equals("YEN")) {
                        datas.TMN -= Double.parseDouble(fromvalue.getText());
                        datas.YEN += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET TMN = TMN - ? , YEN = YEN + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                        updateHistory();
                    } else if (tomenu.getText().equals("GBT")) {
                        datas.TMN -= Double.parseDouble(fromvalue.getText());
                        datas.GBT += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET TMN = TMN - ? , GBT = GBT + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                        updateHistory();
                    } else if (tomenu.getText().equals("EUR")) {
                        datas.TMN -= Double.parseDouble(fromvalue.getText());
                        datas.EUR += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET TMN = TMN - ? , EUR = EUR + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                        updateHistory();
                    } else if (tomenu.getText().equals("Toman")) {
                        warn.setText("ارزها یکسان است");
                    }
                    adminmoney = datas.TMNPrice / 100;
                    String query2 = "UPDATE users SET Money = Money + ? WHERE role = ?";
                    PreparedStatement updateAdminMoney = connection.prepareStatement(query2);
                    updateAdminMoney.setDouble(1, adminmoney);
                    updateAdminMoney.setString(2, "admin");
                    updateAdminMoney.executeUpdate();
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirm");
                    alert.setHeaderText("تبادل موفقیت آمیز ");
                    alert.setContentText("تبادل موفقیت آمیز بود");
                    alert.showAndWait();
                }
            }
            else if (frommenu.getText().equals("YEN")) {
                if (datas.YEN >= Double.parseDouble(fromvalue.getText()) && datas.total >= (Double.parseDouble(fromvalue.getText()) * datas.YENPrice) / 100) {
                    if (tomenu.getText().equals("Toman")) {
                        datas.YEN -= Double.parseDouble(fromvalue.getText());
                        datas.TMN += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET YEN = YEN - ? , TMN = TMN + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                        updateHistory();
                    } else if (tomenu.getText().equals("USD")) {
                        datas.YEN -= Double.parseDouble(fromvalue.getText());
                        datas.USD += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET YEN = YEN - ? , USD = USD + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                        updateHistory();
                    } else if (tomenu.getText().equals("GBT")) {
                        datas.YEN -= Double.parseDouble(fromvalue.getText());
                        datas.GBT += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET YEN = YEN - ? , GBT = GBT + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                        updateHistory();
                    } else if (tomenu.getText().equals("EUR")) {
                        datas.YEN -= Double.parseDouble(fromvalue.getText());
                        datas.EUR += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET YEN = YEN - ? , EUR = EUR + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                        updateHistory();
                    } else if (tomenu.getText().equals("YEN")) {
                        warn.setText("ارزها یکسان است");
                    }
                    adminmoney = datas.YENPrice / 100;
                    String query2 = "UPDATE users SET Money = Money + ? WHERE role = ?";
                    PreparedStatement updateAdminMoney = connection.prepareStatement(query2);
                    updateAdminMoney.setDouble(1, adminmoney);
                    updateAdminMoney.setString(2, "admin");
                    updateAdminMoney.executeUpdate();
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirm");
                    alert.setHeaderText("تبادل موفقیت آمیز ");
                    alert.setContentText("تبادل موفقیت آمیز بود");
                    alert.showAndWait();
                }
            }
            else if (frommenu.getText().equals("GBT")) {
                if (datas.GBT >= Double.parseDouble(fromvalue.getText()) && datas.total >= (Double.parseDouble(fromvalue.getText()) * datas.GBPPrice) / 100) {
                    if (tomenu.getText().equals("Toman")) {
                        datas.GBT -= Double.parseDouble(fromvalue.getText());
                        datas.TMN += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET GBT = GBT - ? , TMN = TMN + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                        updateHistory();
                    } else if (tomenu.getText().equals("YEN")) {
                        datas.GBT -= Double.parseDouble(fromvalue.getText());
                        datas.YEN += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET GBT = GBT - ? , YEN = YEN + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                        updateHistory();
                    } else if (tomenu.getText().equals("USD")) {
                        datas.GBT -= Double.parseDouble(fromvalue.getText());
                        datas.USD += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET GBT = GBT - ? , USD = USD + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                        updateHistory();
                    } else if (tomenu.getText().equals("EUR")) {
                        datas.GBT -= Double.parseDouble(fromvalue.getText());
                        datas.EUR += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET GBT = GBT - ? , EUR = EUR + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                        updateHistory();
                    } else if (tomenu.getText().equals("GBT")) {
                        warn.setText("ارزها یکسان است");
                    }
                    adminmoney = datas.GBPPrice / 100;
                    String query2 = "UPDATE users SET Money = Money + ? WHERE role = ?";
                    PreparedStatement updateAdminMoney = connection.prepareStatement(query2);
                    updateAdminMoney.setDouble(1, adminmoney);
                    updateAdminMoney.setString(2, "admin");
                    updateAdminMoney.executeUpdate();
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirm");
                    alert.setHeaderText("تبادل موفقیت آمیز ");
                    alert.setContentText("تبادل موفقیت آمیز بود");
                    alert.showAndWait();
                }
            }
            else if (frommenu.getText().equals("EUR")) {
                if (datas.EUR >= Double.parseDouble(fromvalue.getText()) && datas.total >= (Double.parseDouble(fromvalue.getText()) * datas.EURPrice) / 100) {
                    if (tomenu.getText().equals("Toman")) {
                        datas.EUR -= Double.parseDouble(fromvalue.getText());
                        datas.TMN += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET EUR = EUR - ? , TMN = TMN + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                        updateHistory();
                    } else if (tomenu.getText().equals("YEN")) {
                        datas.EUR -= Double.parseDouble(fromvalue.getText());
                        datas.YEN += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET EUR = EUR - ? , YEN = YEN + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                        updateHistory();
                    } else if (tomenu.getText().equals("GBT")) {
                        datas.EUR -= Double.parseDouble(fromvalue.getText());
                        datas.GBT += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET EUR = EUR - ? , GBT = GBT + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                        updateHistory();
                    } else if (tomenu.getText().equals("USD")) {
                        datas.EUR -= Double.parseDouble(fromvalue.getText());
                        datas.USD += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET EUR = EUR - ? , USD = USD + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                        updateHistory();
                    } else if (tomenu.getText().equals("EUR")) {
                        warn.setText("ارزها یکسان است");
                    }
                    adminmoney = datas.EURPrice / 100;
                    String query2 = "UPDATE users SET Money = Money + ? WHERE role = ?";
                    PreparedStatement updateAdminMoney = connection.prepareStatement(query2);
                    updateAdminMoney.setDouble(1, adminmoney);
                    updateAdminMoney.setString(2, "admin");
                    updateAdminMoney.executeUpdate();
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirm");
                    alert.setHeaderText("تبادل موفقیت آمیز ");
                    alert.setContentText("تبادل موفقیت آمیز بود");
                    alert.showAndWait();
                }
            }
        }
        else {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("بازار بسته ");
            alert.setContentText("بازار توسط ادمین بسته شده است!");
            alert.showAndWait();
        }
    }

    public void updateHistory() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO swaphistory (fromType , toType , Amount , dude) VALUES ('" + frommenu.getText() + "','" +  tomenu.getText() + "','" + fromvalue.getText() + "','" + datas.username +"')");
    }

}
