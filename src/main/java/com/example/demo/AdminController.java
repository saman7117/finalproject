package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.*;

public class AdminController {
    @FXML
    private Button disable;
    @FXML
    private Button enable;
    @FXML
    private Label users;
    @FXML
    private Label money;
    @FXML
    private Label status;


    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");

    public void disable() throws SQLException {
        Statement statement = connection.createStatement();
        disable.setStyle("-fx-text-fill: #191a1f;\n" +
                "    -fx-background-color: #fca311;\n"
        );
        enable.setStyle("-fx-text-fill: #fca311;\n" +
                "    -fx-background-color: #191a1f;\n" +
                "    -fx-border-color: #fca311;");

        String s1 = "UPDATE users SET USD='" + 1 + "' WHERE role='" + "admin" + "'";// 1 : disable
        statement.executeUpdate(s1);

        setMarketStatus();
    }

    public void enable() throws SQLException {
        Statement statement = connection.createStatement();
        enable.setStyle("-fx-text-fill: #191a1f;\n" +
                "    -fx-background-color: #fca311;\n"
        );
        disable.setStyle("-fx-text-fill: #fca311;\n" +
                "    -fx-background-color: #191a1f;\n" +
                "    -fx-border-color: #fca311;");

        String s1 = "UPDATE users SET USD='" + 0 + "' WHERE role='" + "admin" + "'";// 0 : enable
        statement.executeUpdate(s1);

        setMarketStatus();

    }

    @FXML
    private void initialize() throws SQLException {
        setMarketStatus();
    }
    public void setMarketStatus() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        int size = 0;

        while (resultSet.next()) {
            if (resultSet.getString("role").equals("admin")) {
                money.setText(String.valueOf(resultSet.getDouble("Money")));
                if (resultSet.getDouble("USD") == 0)
                    status.setText("باز");
                else
                    status.setText("بسته");
            }
            size++;
        }
        users.setText(String.valueOf(size));
    }
    public void Robbery() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        double robbedmoney = 0;
        while (resultSet.next()){
            robbedmoney += resultSet.getDouble("Money");
        }
        String query2 = "UPDATE users SET Money = Money + ? WHERE role = ?";
        PreparedStatement updateAdminMoney = connection.prepareStatement(query2);
        updateAdminMoney.setDouble(1, robbedmoney);
        updateAdminMoney.setString(2, "admin");
        updateAdminMoney.executeUpdate();
        String query3 = "UPDATE users SET Money = ? WHERE role = ?";
        PreparedStatement updateTraderMoney = connection.prepareStatement(query3);
        updateTraderMoney.setDouble(1, 0);
        updateTraderMoney.setString(2, "trader");
        updateTraderMoney.executeUpdate();
        setMarketStatus();
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("اختلاس موفقیا آمیز بود ");
        alert.setContentText("تبریک شما دزد خوبی هستید D:");
        alert.showAndWait();
    }


    public AdminController() throws SQLException {
    }
}
