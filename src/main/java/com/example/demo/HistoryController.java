package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {
    @FXML
    private TableView <Hisrow> SwapTable;
    @FXML
    private TableView <Hisrow> TransferTable;
    @FXML
    private TableView <Hisrow> DepositWithdraw;
    @FXML
    private TableView <buysellrow> SellBuyTable;
    @FXML
    private TableColumn<Hisrow, String> SWTC1;
    @FXML
    private TableColumn<Hisrow, Double> SWTC2;
    @FXML
    private TableColumn<Hisrow, String> SWTC3;
    @FXML
    private TableColumn<Hisrow, String> TRTC1;
    @FXML
    private TableColumn<Hisrow, Double> TRTC2;
    @FXML
    private TableColumn<Hisrow, String> TRTC3;
    @FXML
    private TableColumn<Hisrow, String> DWTC1;
    @FXML
    private TableColumn<Hisrow, Double> DWTC2;
    @FXML
    private TableColumn<Hisrow, String> DWTC3;
    @FXML
    private TableColumn<buysellrow, String> SBTC1;
    @FXML
    private TableColumn<buysellrow, Double> SBTC2;
    @FXML
    private TableColumn<buysellrow, Double> SBTC3;
    @FXML
    private TableColumn<buysellrow, String> SBTC4;
    @FXML
    private Label tag;
    ObservableList<Hisrow> SWHisrows = FXCollections.observableArrayList(
            new Hisrow(),
            new Hisrow(),
            new Hisrow()
    );
    ObservableList<Hisrow> DWHisrows = FXCollections.observableArrayList(
            new Hisrow(),
            new Hisrow(),
            new Hisrow()
    );
    ObservableList<Hisrow> TRHisrows = FXCollections.observableArrayList(
            new Hisrow(),
            new Hisrow(),
            new Hisrow()
    );
    ObservableList<buysellrow> buysellrows = FXCollections.observableArrayList(
            new buysellrow(),
            new buysellrow(),
            new buysellrow(),
            new buysellrow()
    );

    public HistoryController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        SWTC1.setCellValueFactory(new PropertyValueFactory<Hisrow , String>("c1"));
        SWTC2.setCellValueFactory(new PropertyValueFactory<Hisrow , Double>("c2"));
        SWTC3.setCellValueFactory(new PropertyValueFactory<Hisrow , String>("c3"));

        TRTC1.setCellValueFactory(new PropertyValueFactory<Hisrow , String>("c1"));
        TRTC2.setCellValueFactory(new PropertyValueFactory<Hisrow , Double>("c2"));
        TRTC3.setCellValueFactory(new PropertyValueFactory<Hisrow , String>("c3"));

        DWTC1.setCellValueFactory(new PropertyValueFactory<Hisrow , String>("c1"));
        DWTC2.setCellValueFactory(new PropertyValueFactory<Hisrow , Double>("c2"));
        DWTC3.setCellValueFactory(new PropertyValueFactory<Hisrow , String>("c3"));

        SBTC1.setCellValueFactory(new PropertyValueFactory<buysellrow , String>("buyer"));
        SBTC2.setCellValueFactory(new PropertyValueFactory<buysellrow , Double>("price"));
        SBTC3.setCellValueFactory(new PropertyValueFactory<buysellrow , Double>("amount"));
        SBTC4.setCellValueFactory(new PropertyValueFactory<buysellrow , String>("seller"));

        try {
            getSwapHis();
            getDWHis();
            getTransferHis();
            getSBHis();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        SwapTable.setItems(SWHisrows);
        TransferTable.setItems(TRHisrows);
        DepositWithdraw.setItems(DWHisrows);
        SellBuyTable.setItems(buysellrows);
    }

    Connection connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
    Statement statement = connection.createStatement();

    public void getSwapHis() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM swaphistory");
        while (resultSet.next()){
            Hisrow h = new Hisrow(resultSet.getString("fromType"),resultSet.getDouble("Amount"),resultSet.getString("toType"));
            SWHisrows.add(h);
        }
    }
    public void getTransferHis() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM transfer");
        while (resultSet.next()){
            Hisrow h = new Hisrow(resultSet.getString("todude"),resultSet.getDouble("Amount"),resultSet.getString("Type"));
            TRHisrows.add(h);
        }
    }

    public void getDWHis() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM depositwithdraw");
        while (resultSet.next()){
            Hisrow h = new Hisrow(resultSet.getString("Type"),resultSet.getDouble("Value"),resultSet.getString("CardNum"));
            DWHisrows.add(h);
        }
    }

    public void getSBHis() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM history");
        while (resultSet.next()){
            buysellrow h = new buysellrow(resultSet.getString("Buyer"),resultSet.getDouble("Amount"),resultSet.getDouble("Price"),resultSet.getString("Seller"));
            buysellrows.add(h);
        }
    }

    public void showSwapTable(){
        SellBuyTable.setVisible(false);
        TransferTable.setVisible(false);
        DepositWithdraw.setVisible(false);
        SwapTable.setVisible(true);
        tag.setText("تبادل");
    }

    public void showTransferTable(){
        SellBuyTable.setVisible(false);
        TransferTable.setVisible(true);
        DepositWithdraw.setVisible(false);
        SwapTable.setVisible(false);
        tag.setText("انتقال");
    }

    public void showDWTable(){
        SellBuyTable.setVisible(false);
        TransferTable.setVisible(false);
        DepositWithdraw.setVisible(true);
        SwapTable.setVisible(false);
        tag.setText("تراکنش ها");
    }

    public void showBSTable(){
        SellBuyTable.setVisible(true);
        TransferTable.setVisible(false);
        DepositWithdraw.setVisible(false);
        SwapTable.setVisible(false);
        tag.setText("خرید و فروش");
    }

}
