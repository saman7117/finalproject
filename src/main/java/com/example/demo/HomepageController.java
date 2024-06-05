package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

    ObservableList <Stock> stocks = FXCollections.observableArrayList(
         new Stock("Market1", 100.0, 10.0, 120.0, 90.0),
        new Stock("Market2", 150.0, -5.0, 160.0, 140.0)
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

}
