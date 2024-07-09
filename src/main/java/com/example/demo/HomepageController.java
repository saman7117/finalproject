package com.example.demo;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.DecimalFormat;
import java.util.Date;

import org.apache.commons.math3.stat.regression.SimpleRegression;


public class HomepageController implements Initializable{
    @FXML
    public TableView<Stock> tableView;

    @FXML
    private TableColumn<Stock, String> marketColumn;
    @FXML
    private TableColumn<Stock, Double> priceColumn;
    @FXML
    private TableColumn<Stock, Double> changesColumn;
    @FXML
    private TableColumn<Stock, Double> maxPriceColumn;
    @FXML
    private TableColumn<Stock, Double> minPriceColumn;
    @FXML
    private ImageView flag = new ImageView();
    @FXML
    private Label time;
    @FXML
    private Label date;
    @FXML
    private Button profile;
    @FXML
    private Label tag;
    @FXML
    private Button logout;
    private static int eee = 1;
    private static int rrr = 1;
    ObservableList <Stock> stocks = FXCollections.observableArrayList(
            new Stock(),
            new Stock(),
            new Stock(),
            new Stock(),
            new Stock()
    );
    private volatile boolean stop = false;
    String[] parts = new String[6];
    String[] pastparts = new String[6];
    int lastMinute = -1;

    public HomepageController() throws FileNotFoundException, SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        marketColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("market"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Stock, Double>("price"));
        changesColumn.setCellValueFactory(new PropertyValueFactory<Stock, Double>("changes"));
        maxPriceColumn.setCellValueFactory(new PropertyValueFactory<Stock, Double>("maxPrice"));
        minPriceColumn.setCellValueFactory(new PropertyValueFactory<Stock, Double>("minPrice"));

        tableView.setItems(stocks);
        tag.setText(datas.username);
        setData();
        showTime();
        showDate();
    }

    @FXML
    private void handleRowClick(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) {
            Object selectedItem = tableView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Token.fxml"));
                    Parent root = loader.load();

                    if (((Stock) selectedItem).getMarket().equals("USD")) {
                        TokenController tokenController = loader.getController();
                        tokenController.setFlagImage("src/main/resources/com/example/demo/usa.png");
                        tokenController.setTokenname("USD");
                    } else if (((Stock) selectedItem).getMarket().equals("YEN")) {
                        TokenController tokenController = loader.getController();
                        tokenController.setFlagImage("src/main/resources/com/example/demo/japan.png");
                        tokenController.setTokenname("YEN");
                    }else if (((Stock) selectedItem).getMarket().equals("TMN")) {
                        TokenController tokenController = loader.getController();
                        tokenController.setFlagImage("src/main/resources/com/example/demo/iran.png");
                        tokenController.setTokenname("Toman");
                    }else if (((Stock) selectedItem).getMarket().equals("GBP")) {
                        TokenController tokenController = loader.getController();
                        tokenController.setFlagImage("src/main/resources/com/example/demo/gbt.png");
                        tokenController.setTokenname("GBP");
                    }else if (((Stock) selectedItem).getMarket().equals("EUR")) {
                        TokenController tokenController = loader.getController();
                        tokenController.setFlagImage("src/main/resources/com/example/demo/eur.png");
                        tokenController.setTokenname("EUR");
                    }

                    Stage stage = new Stage();
                    stage.setTitle("Raze Exchange");
                    stage.setScene(new Scene(root, 1536, 800));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
          }
        }

        public void toWallet() throws IOException {//:)
            Stage stage = new Stage();
            FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("walet.fxml"));
            Scene registerScene = new Scene(registerLoader.load(), 1536, 800);
            stage.setTitle("Raze Exchange");
            stage.setScene(registerScene);
            stage.show();
        }

        public void toProfile() throws IOException {//:(
            Stage stage = new Stage();
            FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("Profile.fxml"));
            Scene registerScene = new Scene(registerLoader.load(), 800, 600);
            stage.setTitle("Raze Exchange");
            stage.setScene(registerScene);
            stage.show();
        }
        public void toSwap() throws IOException {//:(
            Stage stage = new Stage();
            FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("Swap.fxml"));
            Scene registerScene = new Scene(registerLoader.load(), 600, 600);
            stage.setTitle("Raze Exchange");
            stage.setScene(registerScene);
            stage.show();
        }
        public void toTransfer() throws IOException {//:(
            Stage stage = new Stage();
            FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("Transfer.fxml"));
            Scene registerScene = new Scene(registerLoader.load(), 600, 600);
            stage.setTitle("Raze Exchange");
            stage.setScene(registerScene);
            stage.show();
        }
        public void toHistory() throws IOException {//:(
            Stage stage = new Stage();
            FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("History.fxml"));
            Scene registerScene = new Scene(registerLoader.load(), 1536, 800);
            stage.setTitle("Raze Exchange");
            stage.setScene(registerScene);
            stage.show();
        }
        public void toBuy() throws IOException {//:(
            Stage stage = new Stage();
            FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("buy.fxml"));
            Scene registerScene = new Scene(registerLoader.load(), 600, 600);
            stage.setTitle("Raze Exchange");
            stage.setScene(registerScene);
            stage.show();
        }
        public void toDeposit() throws IOException {//:)
            Stage stage = new Stage();
            FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("payment.fxml"));
            Scene registerScene = new Scene(registerLoader.load(), 1536, 800);
            stage.setTitle("Raze Exchange");
            stage.setScene(registerScene);
            stage.show();
        }
    public void toWithdraw() throws IOException {//:(
        Stage stage = new Stage();
        FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("withdraw.fxml"));
        Scene registerScene = new Scene(registerLoader.load(), 1000, 600);
        stage.setTitle("Raze Exchange");
        stage.setScene(registerScene);
        stage.show();
    }

        public void showTime(){
            Thread thread = new Thread(() -> {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
                while (!stop){
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

                        double status = -0;
                        Statement statement = null;
                        try {
                            statement = connection.createStatement();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                            while (resultSet.next()){
                            if (resultSet.getString("role").equals("admin")){
                                    status = resultSet.getDouble("USD");
                                }
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        if (currentMinute != lastMinute && status == 0) {
                            // A new minute has passed, call your update function here

                            setData();
                            try {
                                matchOrders("USD");
                                matchOrders("Toman");
                                matchOrders("EUR");
                                matchOrders("GBP");
                                matchOrders("YEN");
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            lastMinute = currentMinute;
                        }
                    });
                }
            });
            thread.start();
        }
    private void showDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE , MMM dd/yyyy");
        String datenow = simpleDateFormat.format(new Date());
        date.setText(datenow);
    }
    double [] max = new double[5];
    double [] min = {100000 , 100000 , 100000 , 100000 , 100000};
    public void setData() {
        try {
            File file = new File("src/main/resources/com/example/demo/currency_prices.csv");
            Scanner scanner = new Scanner(file);
            String pastline = null;
            for (int i = 0; i < eee; i++) {
                if (i == eee - 1) {
                    pastline = scanner.nextLine();
                    scanner.nextLine();
                }
                scanner.nextLine();
                scanner.nextLine();
            }
            String line = scanner.nextLine();
            eee++;
            parts = line.split("\\s+");
            pastparts = pastline.split("\\s+");
            updatetable();
            scanner.close();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing data: " + e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatetable(){
            double usdPrice = Double.parseDouble(parts[2]);
            double eurPrice = Double.parseDouble(parts[3]);
            double tomanPrice = Double.parseDouble(parts[4]);
            double yenPrice = Double.parseDouble(parts[5]);
            double gbpPrice = Double.parseDouble(parts[6]);

            datas.USDPrice = usdPrice;
            datas.TMNPrice = tomanPrice;
            datas.YENPrice = yenPrice;
            datas.GBPPrice = gbpPrice;
            datas.EURPrice = eurPrice;

            DecimalFormat df = new DecimalFormat("#.##");

            double UT = Double.parseDouble(pastparts[2]) - Double.parseDouble(parts[2]);
            UT = UT / Double.parseDouble(pastparts[2]) * 100;
            UT = Double.parseDouble(df.format(UT));
            double TT = Double.parseDouble(pastparts[4]) - Double.parseDouble(parts[4]);
            TT = TT / Double.parseDouble(pastparts[4]) * 100;
            TT = Double.parseDouble(df.format(TT));
            double ET = Double.parseDouble(pastparts[3]) - Double.parseDouble(parts[3]);
            ET = ET / Double.parseDouble(pastparts[3]) * 100;
            ET = Double.parseDouble(df.format(ET));
            double YT = Double.parseDouble(pastparts[5]) - Double.parseDouble(parts[5]);
            YT = YT / Double.parseDouble(pastparts[5]) * 100;
            YT = Double.parseDouble(df.format(YT));
            double GT = Double.parseDouble(pastparts[6]) - Double.parseDouble(parts[6]);
            GT = GT / Double.parseDouble(pastparts[6]) * 100;
            GT = Double.parseDouble(df.format(GT));

            for (int i = 0; i < min.length; i++) {
                min[i] = Math.min(min[i] , Double.parseDouble(parts[i + 2]));
            }

            for (int i = 0; i < min.length; i++) {
                max[i] = Math.max(max[i] , Double.parseDouble(parts[i + 2]));
            }
            datas.USDMIN = min[0];
            datas.EURMIN = min[1];
            datas.TMNMIN = min[2];
            datas.YENMIN = min[3];
            datas.GBPMIN = min[4];

            datas.USDMAX = max[0];
            datas.EURMAX = max[1];
            datas.TMNMAX = max[2];
            datas.YENMAX = max[3];
            datas.GBPMAX = max[4];


            Stock stock1 = new Stock("USD", usdPrice, UT,max[0] , min[0]);
            Stock stock2 = new Stock("TMN", tomanPrice, TT, max[2], min[2]);
            Stock stock3 = new Stock("EUR", eurPrice, ET, max[1], min[1]);
            Stock stock4 = new Stock("YEN", yenPrice, YT, max[3], min[3]);
            Stock stock5 = new Stock("GBP", gbpPrice, GT, max[4], min[4]);


            stocks.add(stock1);
            stocks.add(stock2);
            stocks.add(stock3);
            stocks.add(stock4);
            stocks.add(stock5);
            tableView.setItems(stocks);
            tableView.getItems().remove(0,5);
    }


    public Double linearRegression (Double[] currency){
        SimpleRegression regression = new SimpleRegression();

        for (int i = 0; i < currency.length; i++) {
            regression.addData(i + 1, currency[i]);
        }
        double nextX = currency.length + 1;
        Double predictedY = regression.predict(nextX);

        return predictedY;
    }

    public Double[] linearRegressionData (Double[] currency){
        SimpleRegression regression = new SimpleRegression();

        for (int i = 0; i < currency.length; i++) {
            regression.addData(i + 1, currency[i]);
        }
        Double[] slopeANDintercept = new Double[2];
        slopeANDintercept[0] = regression.getSlope();
        slopeANDintercept[1] = regression.getIntercept();
        return slopeANDintercept;
    }

    public void frf(){
        datas.stg = (Stage) tableView.getScene().getWindow();
    }

    //###################################### SQL Match ################################3
    Connection connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
    public void matchOrders(String types) throws SQLException {
        Statement statement = connection.createStatement();
        double [] Sprice = new double[5];
        double [] Samount = new double[5];
        String [] Sname = new String[5];
        String [] Sdate = new String[5];
        String [] Bdate = new String[5];
        double [] Bprice = new double[5];
        double [] Bamount = new double[5];
        String [] Bname = new String[5];
        ResultSet resultSet = statement.executeQuery("SELECT * FROM selltable");
        int i = 0;
        while (resultSet.next()){
            if(i==5){
                break;
            }
            else{
                if(resultSet.getString("Type").equals(types)){
                    Sprice[i] = resultSet.getDouble("Price");
                    Samount[i] = resultSet.getDouble("Amount");
                    Sname[i] = resultSet.getString("Trader");
                    Sdate[i] = resultSet.getString("date");
                    i++;
                }
            }
        }
        resultSet = statement.executeQuery("SELECT * FROM buytable");
        i = 0;
        while (resultSet.next()){
            if(i==5){
                break;
            }
            else{
                if(resultSet.getString("Type").equals(types)){
                    Bprice[i] = resultSet.getDouble("Price");
                    Bamount[i] = resultSet.getDouble("Amount");
                    Bname[i] = resultSet.getString("Trader");
                    Bdate[i] = resultSet.getString("date");
                    i++;
                }
            }
        }
        double maxBuy = -1, minSell = 10000000;
        int maxBuyIndex = 0,minSellIndex = 0;
        if(!(Bname[0]==null) && !(Sname[0]==null)) {
            for (int j = 0; j < 5; j++) {
                if (Bprice[j] > maxBuy && Bprice[j] != 0) {
                    maxBuy = Bprice[j];
                    maxBuyIndex = j;
                }
                if (Sprice[j] < minSell && Sprice[j] != 0) {
                    minSell = Sprice[j];
                    minSellIndex = j;
                }
            }
            if (Bprice[maxBuyIndex] >= Sprice[minSellIndex]) {
                if (Bamount[maxBuyIndex] >= Samount[minSellIndex]) {
                    updateUserSql(Sname[minSellIndex], Samount[minSellIndex] * Sprice[minSellIndex]);// پول فروشنده را انداختم جلوش
                    deleteSellSql(Sname[minSellIndex], Sdate[minSellIndex]);
                    updateBuySql(Bname[maxBuyIndex], Samount[minSellIndex], Bdate[maxBuyIndex]);
                    if (Bname[maxBuyIndex].equals(datas.username)) {
                        statement.executeUpdate("INSERT INTO history (Buyer , Amount , Price , Seller) VALUES ('" + datas.username + "','" + Samount[minSellIndex] + "','" + Bprice[maxBuyIndex] + "','" + Sname[minSellIndex] + "')");
                    } else {
                        statement.executeUpdate("INSERT INTO history (Buyer , Amount , Price , Seller) VALUES ('" + Bname[maxBuyIndex] + "','" + Samount[minSellIndex] + "','" + Sprice[minSellIndex] + "','" + Bname[maxBuyIndex] + "')");
                    }
//                matchOrders(types);
                } else {
                    updateUserSql(Sname[minSellIndex], Bamount[maxBuyIndex] * Bprice[maxBuyIndex]);
                    deleteBuySql(Bname[maxBuyIndex], Bdate[maxBuyIndex]);
                    updateSellSql(Sname[minSellIndex], Bamount[maxBuyIndex], Sdate[minSellIndex]);
                    if (Bname[maxBuyIndex].equals(datas.username)) {
                        statement.executeUpdate("INSERT INTO history (Buyer , Amount , Price , Seller) VALUES ('" + datas.username + "','" + Bamount[maxBuyIndex] + "','" + Bprice[maxBuyIndex] + "','" + Sname[minSellIndex] + "')");
                    } else {
                        statement.executeUpdate("INSERT INTO history (Buyer , Amount , Price , Seller) VALUES ('" + Bname[maxBuyIndex] + "','" + Bamount[maxBuyIndex] + "','" + Sprice[minSellIndex] + "','" + Bname[maxBuyIndex] + "')");
                    }
//                matchOrders(types);
                }
            }
        }
    }

    public void updateUserSql(String name, double money) throws SQLException {
        PreparedStatement updateUserMoney = connection.prepareStatement("UPDATE users SET Money = Money + ? WHERE fullname = ?");
        updateUserMoney.setDouble(1,money);
        updateUserMoney.setString(2,name);
        updateUserMoney.executeUpdate();
    }
    public void deleteSellSql(String name , String date) throws SQLException {
        PreparedStatement deleteOrder = connection.prepareStatement("DELETE FROM selltable"+  " WHERE Trader = ? AND date  = ?");
        deleteOrder.setString(1 , name);
        deleteOrder.setString(2 , date);
        deleteOrder.executeUpdate();
    }
    public void updateBuySql(String name, double money, String Date) throws SQLException {
        PreparedStatement updateUserMoney = connection.prepareStatement("UPDATE buytable SET Amount = Amount - ? WHERE Trader = ? AND date = ?");
        updateUserMoney.setDouble(1,money);
        updateUserMoney.setString(2,name);
        updateUserMoney.setString(3,Date);
        updateUserMoney.executeUpdate();
    }
    public void deleteBuySql(String name , String date) throws SQLException {
        PreparedStatement deleteOrder = connection.prepareStatement("DELETE FROM buytable"+  " WHERE Trader = ? AND date  = ?");
        deleteOrder.setString(1 , name);
        deleteOrder.setString(2 , date);
        deleteOrder.executeUpdate();
    }
    public void updateSellSql(String name, double money, String Date) throws SQLException {
        PreparedStatement updateUserMoney = connection.prepareStatement("UPDATE selltable SET Amount = Amount - ? WHERE Trader = ? AND date = ?");
        updateUserMoney.setDouble(1,money);
        updateUserMoney.setString(2,name);
        updateUserMoney.setString(3,Date);
        updateUserMoney.executeUpdate();
    }

}


