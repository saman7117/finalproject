package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.demo.datas.username;

public class HistoryController implements Initializable {
    @FXML
    private TableView<Hisrow> SwapTable;
    @FXML
    private TableView<Hisrow> TransferTable;
    @FXML
    private TableView<Hisrow> DepositWithdraw;
    @FXML
    private TableView<buysellrow> SellBuyTable;
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
    );
    ObservableList<Hisrow> DWHisrows = FXCollections.observableArrayList(
    );
    ObservableList<Hisrow> TRHisrows = FXCollections.observableArrayList(
    );
    ObservableList<buysellrow> buysellrows = FXCollections.observableArrayList(
    );

    public HistoryController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SWTC1.setCellValueFactory(new PropertyValueFactory<Hisrow, String>("c1"));
        SWTC2.setCellValueFactory(new PropertyValueFactory<Hisrow, Double>("c2"));
        SWTC3.setCellValueFactory(new PropertyValueFactory<Hisrow, String>("c3"));

        TRTC1.setCellValueFactory(new PropertyValueFactory<Hisrow, String>("c1"));
        TRTC2.setCellValueFactory(new PropertyValueFactory<Hisrow, Double>("c2"));
        TRTC3.setCellValueFactory(new PropertyValueFactory<Hisrow, String>("c3"));

        DWTC1.setCellValueFactory(new PropertyValueFactory<Hisrow, String>("c1"));
        DWTC2.setCellValueFactory(new PropertyValueFactory<Hisrow, Double>("c2"));
        DWTC3.setCellValueFactory(new PropertyValueFactory<Hisrow, String>("c3"));

        SBTC1.setCellValueFactory(new PropertyValueFactory<buysellrow, String>("buyer"));
        SBTC2.setCellValueFactory(new PropertyValueFactory<buysellrow, Double>("price"));
        SBTC3.setCellValueFactory(new PropertyValueFactory<buysellrow, Double>("amount"));
        SBTC4.setCellValueFactory(new PropertyValueFactory<buysellrow, String>("seller"));

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

    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
    Statement statement = connection.createStatement();

    public void getSwapHis() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM swaphistory");
        while (resultSet.next()) {
            Hisrow h = new Hisrow(resultSet.getString("fromType"), resultSet.getDouble("Amount"), resultSet.getString("toType"));
            SWHisrows.add(h);
        }
    }

    public void getTransferHis() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM transfer");
        while (resultSet.next()) {
            Hisrow h = new Hisrow(resultSet.getString("todude"), resultSet.getDouble("Amount"), resultSet.getString("Type"));
            TRHisrows.add(h);
        }
    }

    public void getDWHis() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM depositwithdraw");
        while (resultSet.next()) {
            Hisrow h = new Hisrow(resultSet.getString("Type"), resultSet.getDouble("Value"), resultSet.getString("CardNum"));
            DWHisrows.add(h);
        }
    }

    public void getSBHis() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM history");
        while (resultSet.next()) {
            buysellrow h = new buysellrow(resultSet.getString("Buyer"), resultSet.getDouble("Amount"), resultSet.getDouble("Price"), resultSet.getString("Seller"));
            buysellrows.add(h);
        }
    }

    public void showSwapTable() {
        SellBuyTable.setVisible(false);
        TransferTable.setVisible(false);
        DepositWithdraw.setVisible(false);
        SwapTable.setVisible(true);
        tag.setText("تبادل");
    }

    public void showTransferTable() {
        SellBuyTable.setVisible(false);
        TransferTable.setVisible(true);
        DepositWithdraw.setVisible(false);
        SwapTable.setVisible(false);
        tag.setText("انتقال");
    }

    public void showDWTable() {
        SellBuyTable.setVisible(false);
        TransferTable.setVisible(false);
        DepositWithdraw.setVisible(true);
        SwapTable.setVisible(false);
        tag.setText("تراکنش ها");
    }

    public void showBSTable() {
        SellBuyTable.setVisible(true);
        TransferTable.setVisible(false);
        DepositWithdraw.setVisible(false);
        SwapTable.setVisible(false);
        tag.setText("خرید و فروش");
    }

    public void ExportSwap(String outputPath) throws SQLException {
        String query;
        query = "SELECT 'swaphistory' AS TableName, id , fromType , toType , Amount, dude FROM swaphistory WHERE dude = ? ";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, datas.username);


        try (ResultSet resultSet = statement.executeQuery();
             FileWriter writer = new FileWriter(outputPath)) {

            writer.append("id,fromType,toType,Amount,dude \n");

            while (resultSet.next()) {
                writer.append(resultSet.getString("id")).append(",");
                writer.append(resultSet.getString("fromType")).append(",");
                writer.append(resultSet.getString("toType")).append(",");
                writer.append(resultSet.getString("Amount")).append(",");
                writer.append(resultSet.getString("dude")).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void ExportTransfer(String outputPath) throws SQLException {
        String query;
        query = "SELECT 'transfer' AS TableName, id , Amount , Type , fromdude, todude FROM transfer WHERE fromdude = ? ";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, datas.username);


        try (ResultSet resultSet = statement.executeQuery();
             FileWriter writer = new FileWriter(outputPath)) {

            writer.append("id,Amount,Type,fromdude,todude \n");

            while (resultSet.next()) {
                writer.append(resultSet.getString("id")).append(",");
                writer.append(resultSet.getString("Amount")).append(",");
                writer.append(resultSet.getString("Type")).append(",");
                writer.append(resultSet.getString("fromdude")).append(",");
                writer.append(resultSet.getString("todude")).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void ExportDW(String outputPath) throws SQLException {
        String query;
        query = "SELECT 'depositwithdraw' AS TableName, id  , Type , Value , dude FROM transfer WHERE dude = ? ";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, datas.username);


        try (ResultSet resultSet = statement.executeQuery();
             FileWriter writer = new FileWriter(outputPath)) {

            writer.append("id,Type,Value,dude \n");

            while (resultSet.next()) {
                writer.append(resultSet.getString("id")).append(",");
                writer.append(resultSet.getString("Type")).append(",");
                writer.append(resultSet.getString("Value")).append(",");
                writer.append(resultSet.getString("dude")).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ClickOnCVSSwap() throws SQLException {
        if (SwapTable.isVisible()) {
            String path = null;
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File");
            fileChooser.setInitialFileName("swap.csv");
            File userDirectory = new File(System.getProperty("user.home"));
            fileChooser.setInitialDirectory(userDirectory);
            File selectedFile = fileChooser.showSaveDialog(tag.getScene().getWindow());
            if (selectedFile != null) {
                path = selectedFile.getParent();
                path = path + "\\" + selectedFile.getName();
                ExportSwap(path);
            }
        } else if (TransferTable.isVisible()) {
            String path = null;
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File");
            fileChooser.setInitialFileName("transfer.csv");
            File userDirectory = new File(System.getProperty("user.home"));
            fileChooser.setInitialDirectory(userDirectory);
            File selectedFile = fileChooser.showSaveDialog(tag.getScene().getWindow());
            if (selectedFile != null) {
                path = selectedFile.getParent();
                path = path + "\\" + selectedFile.getName();
                ExportTransfer(path);
            }
        } else if (DepositWithdraw.isVisible()) {
            String path = null;
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File");
            fileChooser.setInitialFileName("depositwithdraw.csv");
            File userDirectory = new File(System.getProperty("user.home"));
            fileChooser.setInitialDirectory(userDirectory);
            File selectedFile = fileChooser.showSaveDialog(tag.getScene().getWindow());
            if (selectedFile != null) {
                path = selectedFile.getParent();
                path = path + "\\" + selectedFile.getName();
                ExportDW(path);
            }
        }
    }


}
//    public static void exportTransactionsToCSV(String username, String outputPath) {
//        String query;
//        if(Main.demoState.equals("true")) {
//            query = "SELECT 'tomantransaction' AS TableName, Transaction, amount, SellOrBuy, date FROM tomantransaction WHERE username = ? "
//                    + "UNION ALL SELECT 'eurtransaction' AS TableName, Transaction, amount, SellOrBuy, date FROM eurtransaction WHERE username = ? "
//                    + "UNION ALL SELECT 'usdtransaction' AS TableName, Transaction, amount, SellOrBuy, date FROM usdtransaction WHERE username = ?"
//                    + "UNION ALL SELECT 'yentransaction' AS TableName, Transaction, amount, SellOrBuy, date FROM usdtransaction WHERE username = ?"
//                    + "UNION ALL SELECT 'gbptransaction' AS TableName, Transaction, amount, SellOrBuy, date FROM usdtransaction WHERE username = ?";
//        }
//        else{
//            query = "SELECT 'tomantransaction' AS TableName, Transaction, amount, SellOrBuy, date FROM tomantransaction WHERE username = ?, demo=false "
//                    + "UNION ALL SELECT 'eurtransaction' AS TableName, Transaction, amount, SellOrBuy, date FROM eurtransaction WHERE username = ?, demo=false "
//                    + "UNION ALL SELECT 'usdtransaction' AS TableName, Transaction, amount, SellOrBuy, date FROM usdtransaction WHERE username = ?, demo=false "
//                    + "UNION ALL SELECT 'yentransaction' AS TableName, Transaction, amount, SellOrBuy, date FROM usdtransaction WHERE username = ?, demo=false "
//                    + "UNION ALL SELECT 'gbptransaction' AS TableName, Transaction, amount, SellOrBuy, date FROM usdtransaction WHERE username = ?, demo=false";
//        }
//
//        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/users_personal_data", "root", "13832220");
//             PreparedStatement statement = connection.prepareStatement(query)) {
//
//            statement.setString(1, username);
//            statement.setString(2, username);
//            statement.setString(3, username);
//            statement.setString(4, username);
//            statement.setString(5, username);
//
//            try (ResultSet resultSet = statement.executeQuery();
//                 FileWriter writer = new FileWriter(outputPath)) {
//
//                writer.append("currency name,price,amount,SellOrBuy,date\n");
//
//                while (resultSet.next()) {
//                    writer.append(resultSet.getString("TableName")).append(",");
//                    writer.append(resultSet.getString("Transaction")).append(",");
//                    writer.append(resultSet.getString("amount")).append(",");
//                    writer.append(resultSet.getString("SellOrBuy")).append(",");
//                    writer.append(resultSet.getString("date")).append("\n");
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }



