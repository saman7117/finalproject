package com.example.demo;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class TokenController implements Initializable {
    @FXML
    private ImageView flag = new ImageView();
    @FXML
    private Label Tokenname =  new Label();
    @FXML
    private Label time;
    @FXML
    private Label date;
    @FXML
    private NumberAxis number;
    @FXML
    private CategoryAxis category;
    @FXML
    private LineChart chart;
    @FXML
    private NumberAxis Hournumber;

    @FXML
    private LineChart charthour;
    @FXML
    private NumberAxis daynumber;
    @FXML
    private LineChart chartday;
    @FXML
    private Label minprice;
    @FXML
    private Label maxprice;
    @FXML
    private Label currentprice;
    @FXML
    private Label sellprice5;
    @FXML
    private Label sellprice4;
    @FXML
    private Label sellprice3;
    @FXML
    private Label sellprice2;
    @FXML
    private Label sellprice1;
    @FXML
    private Label buyprice5;
    @FXML
    private Label buyprice4;
    @FXML
    private Label buyprice3;
    @FXML
    private Label buyprice2;
    @FXML
    private Label buyprice1;
    @FXML
    private Label sellvalue5;
    @FXML
    private Label sellvalue4;
    @FXML
    private Label sellvalue3;
    @FXML
    private Label sellvalue2;
    @FXML
    private Label sellvalue1;
    @FXML
    private Label buyvalue5;
    @FXML
    private Label buyvalue4;
    @FXML
    private Label buyvalue3;
    @FXML
    private Label buyvalue2;
    @FXML
    private Label buyvalue1;
    @FXML
    private Label buyNum5;
    @FXML
    private Label buyNum4;
    @FXML
    private Label buyNum3;
    @FXML
    private Label buyNum2;
    @FXML
    private Label buyNum1;
    @FXML
    private Label sellNum5;
    @FXML
    private Label sellNum4;
    @FXML
    private Label sellNum3;
    @FXML
    private Label sellNum2;
    @FXML
    private Label sellNum1;
    @FXML
    private Label warning;

    private static int sss = 0;
    private static int sss2 = 0;
    private static int uuu = 43;
    private static int rrr = 243;
    public String [] parts = new String[6];


    private volatile boolean stop = false;

    public TokenController() throws SQLException {
    }

    public void setFlagImage(String string){
        File file = new File(string);
        Image image = new Image(file.toURI().toString());
        flag.setImage(image);
    }
    public void setTokenname(String s){
        Tokenname.setText(s);
    }
    int lastMinute = -1;
    int lastHour = -1;
    public void showTime() {
        Thread thread = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
            while (!stop) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                final Date[] now = {new Date()};
                final String timenow = simpleDateFormat.format(now[0]);

                Platform.runLater(() -> {
                    time.setText(timenow);

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(now[0]);
                    int currentMinute = calendar.get(Calendar.MINUTE);
                    int currentHour = calendar.get(Calendar.HOUR_OF_DAY);

                    double status = -0;
                    Statement statement = null;
                    try {
                        statement = connection.createStatement();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                        while (resultSet.next()) {
                            if (resultSet.getString("role").equals("admin")) {
                                status = resultSet.getDouble("USD");
                            }
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    if (currentMinute != lastMinute && status == 0) {
                        // A new minute has passed, call your update function here
                        setchartdata();
                        try {
                            updateOrders();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        lastMinute = currentMinute;
                    }

//                    if (currentHour != lastHour) {
//                        System.out.println(currentHour + "   " + lastHour);
//                        // An hour has passed, call your hourly update function here
//                        setHourlyData();
//                        lastHour = currentHour;
//                    }
                });
            }
        });
        thread.start();
    }

//    public void setHourlyData() {
//        XYChart.Series series2 = new XYChart.Series();
//        HourlyupdateChart(series2);
//        chart.getData().add(series2);
//        series2.getNode().setStyle("-fx-stroke:  #f6b716;");
//    }
//
//    public void HourlyupdateChart(XYChart.Series series){
//        try {
//            File file = new File("src/main/resources/com/example/demo/currency_prices2.csv");
//            Scanner scanner = new Scanner(file);
//            for(int i=0;i<sss2;i++){
//                String s = scanner.nextLine();
//                s = scanner.nextLine();
//            }
//            for(int i = 0 ; i<rrr ;i+=2){
//                String s = scanner.nextLine();
//                System.out.println(s);
//                String line = scanner.nextLine();
//                System.out.println(line);
//                parts = line.split("\\s+");
//                if (Tokenname.getText().equals("USD")) {
//                    series.getData().add(new XYChart.Data(String.valueOf(parts[1]), Double.parseDouble(parts[2])));
//                    Hournumber.setUpperBound(datas.USDMAX + 0.3);
//                    Hournumber.setLowerBound(datas.USDMIN - 0.1);
//                    Hournumber.setTickUnit(0.01);
//                }
//                else if (Tokenname.getText().equals("TMN")) {
//                    series.getData().add(new XYChart.Data(String.valueOf(parts[1]), Double.parseDouble(parts[4])));
//                    Hournumber.setUpperBound(datas.TMNMAX + 1000);
//                    Hournumber.setLowerBound(datas.TMNMIN - 1000);
//                    Hournumber.setTickUnit(500);
//                }
//                else if (Tokenname.getText().equals("EUR")) {
//                    series.getData().add(new XYChart.Data(String.valueOf(parts[1]), Double.parseDouble(parts[3])));
//                    Hournumber.setUpperBound(datas.EURMAX + 0.1);
//                    Hournumber.setLowerBound(datas.EURMIN - 0.1);
//                    Hournumber.setTickUnit(0.01);
//                }
//                else if (Tokenname.getText().equals("YEN")) {
//                    series.getData().add(new XYChart.Data(String.valueOf(parts[1]), Double.parseDouble(parts[5])));
//                    Hournumber.setUpperBound(datas.YENMAX + 10);
//                    Hournumber.setLowerBound(datas.YENMIN - 10);
//                    Hournumber.setTickUnit(5);
//                }
//                else if (Tokenname.getText().equals("GBP")) {
//                    series.getData().add(new XYChart.Data(String.valueOf(parts[1]), Double.parseDouble(parts[6])));
//                    Hournumber.setUpperBound(datas.GBPMAX + 0.1);
//                    Hournumber.setLowerBound(datas.GBPMIN - 0.1);
//                    Hournumber.setTickUnit(0.01);
//                }
//
//            }
//            sss2++;
//            scanner.close();
//            chartday.setVisible(false);
//            chart.setVisible(false);
//            charthour.setVisible(true);
//        } catch (NumberFormatException e) {
//            System.out.println("Error parsing data: " + e.getMessage());
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }


    private void showDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE , MMM dd/yyyy");
        String datenow = simpleDateFormat.format(new Date());
        date.setText(datenow);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showTime();
        showDate();
        setLabelsdata();
        setchartdata();


    }

    public void setLabelsdata(){
        if (Tokenname.getText().equals("USD")) {
            maxprice.setText(String.valueOf(datas.USDMAX));
            minprice.setText(String.valueOf(datas.USDMIN));
            currentprice.setText(String.valueOf(datas.USDPrice));
        }
        else if (Tokenname.getText().equals("Toman")) {
            maxprice.setText(String.valueOf(datas.TMNMAX));
            minprice.setText(String.valueOf(datas.TMNMIN));
            currentprice.setText(String.valueOf(datas.TMNPrice));
        }
        else if (Tokenname.getText().equals("YEN")) {
            maxprice.setText(String.valueOf(datas.YENMAX));
            minprice.setText(String.valueOf(datas.YENMIN));
            currentprice.setText(String.valueOf(datas.YENPrice));
        }
        else if (Tokenname.getText().equals("EUR")) {
            maxprice.setText(String.valueOf(datas.EURMAX));
            minprice.setText(String.valueOf(datas.EURMIN));
            currentprice.setText(String.valueOf(datas.EURPrice));
        }
        else if (Tokenname.getText().equals("GBP")) {
            maxprice.setText(String.valueOf(datas.GBPMAX));
            minprice.setText(String.valueOf(datas.GBPMIN));
            currentprice.setText(String.valueOf(datas.GBPPrice));
        }

    }
    public void setchartdata(){
        XYChart.Series series = new XYChart.Series();
        updateChart(series);
        chart.getData().add(series);
        series.getNode().setStyle("-fx-stroke:  #f6b716;");
    }

    public void updateChart(XYChart.Series series){
        try {
            File file = new File("src/main/resources/com/example/demo/currency_prices2.csv");
            Scanner scanner = new Scanner(file);
            for(int i=0;i<sss;i++){
                scanner.nextLine();
                scanner.nextLine();
            }
            for(int i = 0 ; i<uuu ;i+=2){
                scanner.nextLine();
                String line = scanner.nextLine();
                parts = line.split("\\s+");
                if (Tokenname.getText().equals("USD")) {
                    series.getData().add(new XYChart.Data(String.valueOf(parts[1]), Double.parseDouble(parts[2])));
                    number.setUpperBound(datas.USDMAX + 0.3);
                    number.setLowerBound(datas.USDMIN - 0.1);
                    number.setTickUnit(0.01);
                }
                else if (Tokenname.getText().equals("TMN")) {
                    series.getData().add(new XYChart.Data(String.valueOf(parts[1]), Double.parseDouble(parts[4])));
                    number.setUpperBound(datas.TMNMAX + 1000);
                    number.setLowerBound(datas.TMNMIN - 1000);
                    number.setTickUnit(500);
                }
                else if (Tokenname.getText().equals("EUR")) {
                    series.getData().add(new XYChart.Data(String.valueOf(parts[1]), Double.parseDouble(parts[3])));
                    number.setUpperBound(datas.EURMAX + 0.1);
                    number.setLowerBound(datas.EURMIN - 0.1);
                    number.setTickUnit(0.01);
                }
                else if (Tokenname.getText().equals("YEN")) {
                    series.getData().add(new XYChart.Data(String.valueOf(parts[1]), Double.parseDouble(parts[5])));
                    number.setUpperBound(datas.YENMAX + 10);
                    number.setLowerBound(datas.YENMIN - 10);
                    number.setTickUnit(5);
                }
                else if (Tokenname.getText().equals("GBP")) {
                    series.getData().add(new XYChart.Data(String.valueOf(parts[1]), Double.parseDouble(parts[6])));
                    number.setUpperBound(datas.GBPMAX + 0.1);
                    number.setLowerBound(datas.GBPMIN - 0.1);
                    number.setTickUnit(0.01);
                }

            }
            sss++;
            scanner.close();
            charthour.setVisible(false);
            chartday.setVisible(false);
            chart.setVisible(true);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing data: " + e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void toWallet() throws IOException {//:)
        Stage stage = (Stage)Tokenname.getScene().getWindow();
        stage.close();
        FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("walet.fxml"));
        Scene registerScene = new Scene(registerLoader.load(), 1536, 800);
        stage.setTitle("Raze Exchange");
        stage.setScene(registerScene);
        stage.show();
    }

        public void toProfile() throws IOException {//:(
            Stage stage = (Stage)Tokenname.getScene().getWindow();
            stage.close();
            FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("Profile.fxml"));
            Scene registerScene = new Scene(registerLoader.load(), 800, 600);
            stage.setTitle("Raze Exchange");
            stage.setScene(registerScene);
            stage.show();
        }
        public void toSwap() throws IOException {//:(
            Stage stage = new Stage();
            FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("Swap.fxml"));
            Scene registerScene = new Scene(registerLoader.load(), 800, 600);
            stage.setTitle("Raze Exchange");
            stage.setScene(registerScene);
            stage.show();
        }
        public void toTransfer() throws IOException {//:(
            Stage stage = new Stage();
            FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("Transfer.fxml"));
            Scene registerScene = new Scene(registerLoader.load(), 800, 600);
            stage.setTitle("Raze Exchange");
            stage.setScene(registerScene);
            stage.show();
        }

        Connection connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
        String [][] currentsells = new String[5][4];
        String [][] currentbuys = new String[5][4];
        public void updateOrders() throws IOException, ClassNotFoundException, SQLException {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM selltable");
            int checknum = 0;
            while (resultSet.next()){
                if (checknum == 5)
                    break;
                if(resultSet.getString("Type").equals(Tokenname.getText())) {
                    String amount = (resultSet.getString("Amount"));
                    String price = (resultSet.getString("Price"));
                    if (checknum == 0) {
                        sellNum1.setText("1");
                        sellvalue1.setText(amount);
                        sellprice1.setText(price);
                        checknum ++;
                    } else if (checknum == 1) {
                        if (price.equals(sellprice1.getText())){
                            sellvalue1.setText(String.valueOf(Double.parseDouble(amount) + Double.parseDouble(sellvalue1.getText())));
                            sellNum1.setText(String.valueOf(Double.parseDouble(sellNum1.getText())+1));
                        }
                        else {
                            sellNum2.setText("1");
                            sellvalue2.setText(amount);
                            sellprice2.setText(price);
                            checknum ++;
                        }
                    } else if (checknum == 2) {
                        if (price.equals(sellprice1.getText())){
                            sellvalue1.setText(String.valueOf(Double.parseDouble(amount) + Double.parseDouble(sellvalue1.getText())));
                            sellNum1.setText(String.valueOf(Double.parseDouble(sellNum1.getText())+1));
                        }
                        else if (price.equals(sellprice2.getText())){
                            sellvalue2.setText(String.valueOf(Double.parseDouble(amount) + Double.parseDouble(sellvalue2.getText())));
                            sellNum2.setText(String.valueOf(Double.parseDouble(sellNum2.getText())+1));
                        }
                        else {
                            sellNum3.setText("1");
                            sellvalue3.setText(amount);
                            sellprice3.setText(price);
                            checknum ++;
                        }
                    } else if (checknum == 3) {
                        if (price.equals(sellprice1.getText())){
                            sellvalue1.setText(String.valueOf(Double.parseDouble(amount) + Double.parseDouble(sellvalue1.getText())));
                            sellNum1.setText(String.valueOf(Double.parseDouble(sellNum1.getText())+1));
                        }
                        else if (price.equals(sellprice2.getText())){
                            sellvalue2.setText(String.valueOf(Double.parseDouble(amount) + Double.parseDouble(sellvalue2.getText())));
                            sellNum2.setText(String.valueOf(Double.parseDouble(sellNum2.getText())+1));
                        }
                        else if (price.equals(sellprice3.getText())){
                            sellvalue3.setText(String.valueOf(Double.parseDouble(amount) + Double.parseDouble(sellvalue3.getText())));
                            sellNum3.setText(String.valueOf(Double.parseDouble(sellNum3.getText())+1));
                        }
                        else {
                            sellNum4.setText("1");
                            sellvalue4.setText(amount);
                            sellprice4.setText(price);
                        }
                    } else if (checknum == 4) {
                        if (price.equals(sellprice1.getText())){
                            sellvalue1.setText(String.valueOf(Double.parseDouble(amount) + Double.parseDouble(sellvalue1.getText())));
                            sellNum1.setText(String.valueOf(Double.parseDouble(sellNum1.getText())+1));
                        }
                        else if (price.equals(sellprice2.getText())){
                            sellvalue2.setText(String.valueOf(Double.parseDouble(amount) + Double.parseDouble(sellvalue2.getText())));
                            sellNum2.setText(String.valueOf(Double.parseDouble(sellNum2.getText())+1));
                        }
                        else if (price.equals(sellprice3.getText())){
                            sellvalue3.setText(String.valueOf(Double.parseDouble(amount) + Double.parseDouble(sellvalue3.getText())));
                            sellNum3.setText(String.valueOf(Double.parseDouble(sellNum3.getText())+1));
                        }
                        else if (price.equals(sellprice4.getText())){
                            sellvalue4.setText(String.valueOf(Double.parseDouble(amount) + Double.parseDouble(sellvalue4.getText())));
                            sellNum4.setText(String.valueOf(Double.parseDouble(sellNum4.getText())+1));
                        }
                        else {
                            sellNum5.setText("1");
                            sellvalue5.setText(amount);
                            sellprice5.setText(price);
                        }
                    }
                }
            }
            checknum = 0;
            resultSet = statement.executeQuery("SELECT * FROM buytable");
            while (resultSet.next()){
                if (checknum == 5)
                    break;
                if(resultSet.getString("Type").equals(Tokenname.getText())) {
                    String amount = (resultSet.getString("Amount"));
                    String price = (resultSet.getString("Price"));
                    if (checknum == 0) {
                        buyNum1.setText("1");
                        buyvalue1.setText(amount);
                        buyprice1.setText(price);
                        checknum ++;
                    } else if (checknum == 1) {
                        if (price.equals(buyprice1.getText())){
                            buyvalue1.setText(String.valueOf(Double.parseDouble(amount) + Double.parseDouble(buyvalue1.getText())));
                            buyNum1.setText(String.valueOf(Double.parseDouble(buyNum1.getText())+1));
                        }
                        else {
                            buyNum2.setText("1");
                            buyvalue2.setText(amount);
                            buyprice2.setText(price);
                            checknum ++;
                        }
                    } else if (checknum == 2) {
                        if (price.equals(buyprice1.getText())){
                            buyvalue1.setText(String.valueOf(Double.parseDouble(amount) + Double.parseDouble(buyvalue1.getText())));
                            buyNum1.setText(String.valueOf(Double.parseDouble(buyNum1.getText())+1));
                        }
                        else if (price.equals(buyprice2.getText())){
                            buyvalue2.setText(String.valueOf(Double.parseDouble(amount) + Double.parseDouble(buyvalue2.getText())));
                            buyNum2.setText(String.valueOf(Double.parseDouble(buyNum2.getText())+1));
                        }
                        else {
                            buyNum3.setText("1");
                            buyvalue3.setText(amount);
                            buyprice3.setText(price);
                            checknum ++;
                        }
                    } else if (checknum == 3) {
                        if (price.equals(buyprice1.getText())){
                            buyvalue1.setText(String.valueOf(Double.parseDouble(amount) + Double.parseDouble(buyvalue1.getText())));
                            buyNum1.setText(String.valueOf(Double.parseDouble(buyNum1.getText())+1));
                        }
                        else if (price.equals(buyprice2.getText())){
                            buyvalue2.setText(String.valueOf(Double.parseDouble(amount) + Double.parseDouble(buyvalue2.getText())));
                            buyNum2.setText(String.valueOf(Double.parseDouble(buyNum2.getText())+1));
                        }
                        else if (price.equals(buyprice3.getText())){
                            buyvalue3.setText(String.valueOf(Double.parseDouble(amount) + Double.parseDouble(buyvalue3.getText())));
                            buyNum3.setText(String.valueOf(Double.parseDouble(buyNum3.getText())+1));
                        }
                        else {
                            buyNum4.setText("1");
                            buyvalue4.setText(amount);
                            buyprice4.setText(price);
                        }
                    } else if (checknum == 4) {
                        if (price.equals(buyprice1.getText())){
                            buyvalue1.setText(String.valueOf(Double.parseDouble(amount) + Double.parseDouble(buyvalue1.getText())));
                            buyNum1.setText(String.valueOf(Double.parseDouble(buyNum1.getText())+1));
                        }
                        else if (price.equals(buyprice2.getText())){
                            buyvalue2.setText(String.valueOf(Double.parseDouble(amount) + Double.parseDouble(buyvalue2.getText())));
                            buyNum2.setText(String.valueOf(Double.parseDouble(buyNum2.getText())+1));
                        }
                        else if (price.equals(buyprice3.getText())){
                            buyvalue3.setText(String.valueOf(Double.parseDouble(amount) + Double.parseDouble(buyvalue3.getText())));
                            buyNum3.setText(String.valueOf(Double.parseDouble(buyNum3.getText())+1));
                        }
                        else if (price.equals(buyprice4.getText())){
                            buyvalue4.setText(String.valueOf(Double.parseDouble(amount) + Double.parseDouble(buyvalue4.getText())));
                            buyNum4.setText(String.valueOf(Double.parseDouble(buyNum4.getText())+1));
                        }
                        else {
                            buyNum5.setText("1");
                            buyvalue5.setText(amount);
                            buyprice5.setText(price);
                        }
                    }
                }
            }

        }
}
