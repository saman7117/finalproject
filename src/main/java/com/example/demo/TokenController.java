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
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;

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
            maxprice.setText(String.valueOf(datas.MAXPrice[0]));
            minprice.setText(String.valueOf(datas.MINPrice[0]));
            currentprice.setText(String.valueOf(datas.USDPrice));
        }
        else if (Tokenname.getText().equals("TMN")) {
            maxprice.setText(String.valueOf(datas.MAXPrice[1]));
            minprice.setText(String.valueOf(datas.MINPrice[1]));
            currentprice.setText(String.valueOf(datas.TMNPrice));
        }
        else if (Tokenname.getText().equals("YEN")) {
            maxprice.setText(String.valueOf(datas.MAXPrice[2]));
            minprice.setText(String.valueOf(datas.MINPrice[2]));
            currentprice.setText(String.valueOf(datas.YENPrice));
        }
        else if (Tokenname.getText().equals("EUR")) {
            maxprice.setText(String.valueOf(datas.MAXPrice[4]));
            minprice.setText(String.valueOf(datas.MINPrice[4]));
            currentprice.setText(String.valueOf(datas.EURPrice));
        }
        else if (Tokenname.getText().equals("GBP")) {
            maxprice.setText(String.valueOf(datas.MAXPrice[3]));
            minprice.setText(String.valueOf(datas.MINPrice[3]));
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
                    number.setUpperBound(datas.MAXPrice[0] + 0.1);
                    number.setLowerBound(datas.MINPrice[0] - 0.1);
                    number.setTickUnit(0.01);
                }
                else if (Tokenname.getText().equals("TMN")) {
                    series.getData().add(new XYChart.Data(String.valueOf(parts[1]), Double.parseDouble(parts[4])));
                    number.setUpperBound(datas.MAXPrice[1] + 1000);
                    number.setLowerBound(datas.MINPrice[1] - 1000);
                    number.setTickUnit(500);
                }
                else if (Tokenname.getText().equals("EUR")) {
                    series.getData().add(new XYChart.Data(String.valueOf(parts[1]), Double.parseDouble(parts[3])));
                    number.setUpperBound(datas.MAXPrice[4] + 0.1);
                    number.setLowerBound(datas.MINPrice[4] - 0.1);
                    number.setTickUnit(0.01);
                }
                else if (Tokenname.getText().equals("YEN")) {
                    series.getData().add(new XYChart.Data(String.valueOf(parts[1]), Double.parseDouble(parts[5])));
                    number.setUpperBound(datas.MAXPrice[2] + 1000);
                    number.setLowerBound(datas.MINPrice[2] - 1000);
                    number.setTickUnit(500);
                }
                else if (Tokenname.getText().equals("GBP")) {
                    series.getData().add(new XYChart.Data(String.valueOf(parts[1]), Double.parseDouble(parts[6])));
                    number.setUpperBound(datas.MAXPrice[3] + 0.1);
                    number.setLowerBound(datas.MINPrice[3] - 0.1);
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
}
