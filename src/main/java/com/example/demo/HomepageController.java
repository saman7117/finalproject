package com.example.demo;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.DecimalFormat;


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


    private static int eee = 1;
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

    public HomepageController() throws FileNotFoundException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        marketColumn.setCellValueFactory(new PropertyValueFactory<Stock , String>("market"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Stock , Double>("price"));
        changesColumn.setCellValueFactory(new PropertyValueFactory<Stock , Double>("changes"));
        maxPriceColumn.setCellValueFactory(new PropertyValueFactory<Stock , Double>("maxPrice"));
        minPriceColumn.setCellValueFactory(new PropertyValueFactory<Stock , Double>("minPrice"));

        tableView.setItems(stocks);
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
                        tokenController.setTokenname("TMN");
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
                    stage.setScene(new Scene(root, 1536, 864));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
          }
        }

        public void toWallet() throws IOException {//:)
            Stage stage = (Stage)tableView.getScene().getWindow();
            stage.close();
            FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("walet.fxml"));
            Scene registerScene = new Scene(registerLoader.load(), 1536, 864);
            stage.setTitle("Raze Exchange");
            stage.setScene(registerScene);
            stage.show();
        }

        public void toProfile() throws IOException {//:(
            Stage stage = (Stage)tableView.getScene().getWindow();
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
        public void toBuy() throws IOException {//:(
            Stage stage = new Stage();
            FXMLLoader registerLoader = new FXMLLoader(HelloApplication.class.getResource("buy.fxml"));
            Scene registerScene = new Scene(registerLoader.load(), 600, 600);
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


                        if (currentMinute != lastMinute) {
                            // A new minute has passed, call your update function here

                            setData();
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
            for(int i=0;i<eee;i++){
                if(i==eee-1){
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
        //System.out.println(parts);
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
            datas.MINPrice = Arrays.copyOf(min , min.length);

            for (int i = 0; i < min.length; i++) {
                max[i] = Math.max(max[i] , Double.parseDouble(parts[i + 2]));
            }
            datas.MAXPrice = Arrays.copyOf(max , max.length);


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

}


