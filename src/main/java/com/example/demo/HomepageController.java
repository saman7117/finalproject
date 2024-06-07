package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



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
    ObservableList <Stock> stocks = FXCollections.observableArrayList(
            new Stock("USD", 100.0, 10.0, 120.0, 90.0),
            new Stock("YEN", 150.0, -5.0, 160.0, 140.0),
            new Stock("TMN", 150.0, -5.0, 160.0, 140.0),
            new Stock("GBT", 150.0, -5.0, 160.0, 140.0),
            new Stock("EUR", 150.0, -5.0, 160.0, 140.0)
    );


    public void newPage() {
        System.out.println("da");
        /*Stage stage = new Stage();
        stage.show();*/
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        marketColumn.setCellValueFactory(new PropertyValueFactory<Stock , String>("market"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Stock , Double>("price"));
        changesColumn.setCellValueFactory(new PropertyValueFactory<Stock , Double>("changes"));
        maxPriceColumn.setCellValueFactory(new PropertyValueFactory<Stock , Double>("maxPrice"));
        minPriceColumn.setCellValueFactory(new PropertyValueFactory<Stock , Double>("minPrice"));

        tableView.setItems(stocks);
    }

    public void Swap(){
        System.out.println("swap");
    }

    public void Exchange(){
        System.out.println("exchange");
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
                    }else if (((Stock) selectedItem).getMarket().equals("GBT")) {
                        TokenController tokenController = loader.getController();
                        tokenController.setFlagImage("src/main/resources/com/example/demo/gbt.png");
                        tokenController.setTokenname("GBT");
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
    }


