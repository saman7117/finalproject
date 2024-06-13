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
import java.text.SimpleDateFormat;
import java.util.*;

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
    private Label warning;

    private static int sss = 0;
    private static int uuu = 43;
    public String [] parts = new String[6];


    private volatile boolean stop = false;
    public void setFlagImage(String string){
        File file = new File(string);
        Image image = new Image(file.toURI().toString());
        flag.setImage(image);
    }
    public void setTokenname(String s){
        Tokenname.setText(s);
    }
    int lastMinute = -1;
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
                    if (currentMinute != lastMinute) {
                        // A new minute has passed, call your update function here
                        setLabelsdata();
                        setchartdata();
                        try {
                            updateOrders();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        chart.getData().add(new XYChart.Series<>());
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
        else if (Tokenname.getText().equals("TMN")) {
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
        Scene registerScene = new Scene(registerLoader.load(), 1536, 864);
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

        public void updateOrders() throws IOException, ClassNotFoundException {
            buyprice1.setText("");
            buyprice2.setText("");
            buyprice3.setText("");
            buyprice4.setText("");
            buyprice5.setText("");
            sellprice1.setText("");
            sellprice2.setText("");
            sellprice3.setText("");
            sellprice4.setText("");
            sellprice5.setText("");
            buyvalue1.setText("");
            buyvalue2.setText("");
            buyvalue3.setText("");
            buyvalue4.setText("");
            buyvalue5.setText("");
            sellvalue1.setText("");
            sellvalue2.setText("");
            sellvalue3.setText("");
            sellvalue4.setText("");
            sellvalue5.setText("");
            datas.MainWriter.println("s");
            datas.MainWriter.println(Tokenname.getText());
            ArrayList<StockQueue> buyorder = new ArrayList<>();
            ArrayList<StockQueue> sellorder = new ArrayList<>();
            String line = datas.MainReader.readLine();
            if (line.equals("buy")) {
                buyorder = (ArrayList<StockQueue>) datas.objectMainReader.readObject();
                Collections.sort(buyorder);
            }
            line = datas.MainReader.readLine();
           if (line.equals("sell")){
                sellorder = (ArrayList<StockQueue>) datas.objectMainReader.readObject();
                Collections.sort(sellorder);
            }
            if(buyorder.size()==0 && sellorder.size()==0)
                warning.setText("هیچ سفارشی ثبت نشده");
            else {
                warning.setText("");
                System.out.println(buyorder.size());
                System.out.println(sellorder.size());
                if(buyorder.size() == 1){
                    buyvalue1.setText(String.valueOf(buyorder.get(0).value));
                    buyprice1.setText(String.valueOf(buyorder.get(0).price));
                }
                else if(buyorder.size() == 2){
                    buyvalue2.setText(String.valueOf(buyorder.get(1).value));
                    buyprice2.setText(String.valueOf(buyorder.get(1).price));
                    buyvalue1.setText(String.valueOf(buyorder.get(0).value));
                    buyprice1.setText(String.valueOf(buyorder.get(0).price));
                }
                else if(buyorder.size() == 3){
                    buyvalue3.setText(String.valueOf(buyorder.get(2).value));
                    buyprice3.setText(String.valueOf(buyorder.get(2).price));
                    buyvalue2.setText(String.valueOf(buyorder.get(1).value));
                    buyprice2.setText(String.valueOf(buyorder.get(1).price));
                    buyvalue1.setText(String.valueOf(buyorder.get(0).value));
                    buyprice1.setText(String.valueOf(buyorder.get(0).price));
                }
                else if(buyorder.size() == 4){
                    buyvalue4.setText(String.valueOf(buyorder.get(3).value));
                    buyprice4.setText(String.valueOf(buyorder.get(3).price));
                    buyvalue3.setText(String.valueOf(buyorder.get(2).value));
                    buyprice3.setText(String.valueOf(buyorder.get(2).price));
                    buyvalue2.setText(String.valueOf(buyorder.get(1).value));
                    buyprice2.setText(String.valueOf(buyorder.get(1).price));
                    buyvalue1.setText(String.valueOf(buyorder.get(0).value));
                    buyprice1.setText(String.valueOf(buyorder.get(0).price));
                }
                else if(buyorder.size() >= 5){
                    buyvalue5.setText(String.valueOf(buyorder.get(4).value));
                    buyprice5.setText(String.valueOf(buyorder.get(4).price));
                    buyvalue4.setText(String.valueOf(buyorder.get(3).value));
                    buyprice4.setText(String.valueOf(buyorder.get(3).price));
                    buyvalue3.setText(String.valueOf(buyorder.get(2).value));
                    buyprice3.setText(String.valueOf(buyorder.get(2).price));
                    buyvalue2.setText(String.valueOf(buyorder.get(1).value));
                    buyprice2.setText(String.valueOf(buyorder.get(1).price));
                    buyvalue1.setText(String.valueOf(buyorder.get(0).value));
                    buyprice1.setText(String.valueOf(buyorder.get(0).price));
                }
                if(sellorder.size() == 1){
                    sellvalue5.setText(String.valueOf(sellorder.get(0).value));
                    sellprice5.setText(String.valueOf(sellorder.get(0).price));
                }
                else if(sellorder.size() == 2){
                    sellvalue5.setText(String.valueOf(sellorder.get(1).value));
                    sellprice5.setText(String.valueOf(sellorder.get(1).price));
                    sellvalue4.setText(String.valueOf(sellorder.get(0).value));
                    sellprice4.setText(String.valueOf(sellorder.get(0).price));
                }
                else if(sellorder.size() == 3){
                    sellvalue5.setText(String.valueOf(sellorder.get(2).value));
                    sellprice5.setText(String.valueOf(sellorder.get(2).price));
                    sellvalue4.setText(String.valueOf(sellorder.get(1).value));
                    sellprice4.setText(String.valueOf(sellorder.get(1).price));
                    sellvalue3.setText(String.valueOf(sellorder.get(0).value));
                    sellprice3.setText(String.valueOf(sellorder.get(0).price));
                }
                else if(sellorder.size() == 4){
                    sellvalue5.setText(String.valueOf(sellorder.get(3).value));
                    sellprice5.setText(String.valueOf(sellorder.get(3).price));
                    sellvalue4.setText(String.valueOf(sellorder.get(2).value));
                    sellprice4.setText(String.valueOf(sellorder.get(2).price));
                    sellvalue3.setText(String.valueOf(sellorder.get(1).value));
                    sellprice3.setText(String.valueOf(sellorder.get(1).price));
                    sellvalue2.setText(String.valueOf(sellorder.get(0).value));
                    sellprice2.setText(String.valueOf(sellorder.get(0).price));
                }
                else if(sellorder.size() >= 5){
                    sellvalue5.setText(String.valueOf(sellorder.get(4).value));
                    sellprice5.setText(String.valueOf(sellorder.get(4).price));
                    sellvalue4.setText(String.valueOf(sellorder.get(3).value));
                    sellprice4.setText(String.valueOf(sellorder.get(3).price));
                    sellvalue3.setText(String.valueOf(sellorder.get(2).value));
                    sellprice3.setText(String.valueOf(sellorder.get(2).price));
                    sellvalue2.setText(String.valueOf(sellorder.get(1).value));
                    sellprice2.setText(String.valueOf(sellorder.get(1).price));
                    sellvalue1.setText(String.valueOf(sellorder.get(0).value));
                    sellprice1.setText(String.valueOf(sellorder.get(0).price));
                }
            }
        }
}
