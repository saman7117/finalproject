package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.sql.*;

public class SwapController {
    @FXML
    private TextField fromvalue;
    @FXML
    private Label tovalue;
    @FXML
    private MenuButton frommenu;
    @FXML
    private MenuButton tomenu;
    @FXML
    private ImageView swapbtn;
    @FXML
    private Button confirmbtn;
    @FXML
    private MenuItem USDitem;
    @FXML
    private MenuItem TMNitem;
    @FXML
    private MenuItem YENitem;
    @FXML
    private MenuItem GBTitem;
    @FXML
    private MenuItem EURitem;
    @FXML
    private Label warn;

    double USDprice = datas.USDPrice;
    double Tomanprince = datas.TMNPrice;
    public void setmenutexttoUSD(){
        frommenu.setText("USD");
    }
    public void setmenutexttoTMN(){
        frommenu.setText("Toman");
    }
    public void setmenutexttoEUR(){
        frommenu.setText("EUR");
    }
    public void setmenutexttoYEN(){
        frommenu.setText("YEN");
    }
    public void setmenutexttoGBT(){
        frommenu.setText("GBT");
    }

    public void setmenutextto2USD(){
        tomenu.setText("USD");
    }
    public void setmenutextto2TMN(){
        tomenu.setText("Toman");
    }
    public void setmenutextto2EUR(){
        tomenu.setText("EUR");
    }
    public void setmenutextto2YEN(){
        tomenu.setText("YEN");
    }
    public void setmenutextto2GBT(){
        tomenu.setText("GBT");
    }

    public void SwapFT(){
        String temp = frommenu.getText();
        frommenu.setText(tomenu.getText());
        tomenu.setText(temp);
        temp = fromvalue.getText();
        fromvalue.setText(tovalue.getText());
        tovalue.setText(temp);
    }

    @FXML
    private void initialize(){
        fromvalue.textProperty().addListener((observable, oldValue, newValue) -> {
            // Perform the conversion logic here
            double amount = Double.parseDouble(newValue); // Assuming the input is a valid number
            double convertedAmount = convertCurrency(amount); // Implement your currency conversion logic here
            tovalue.setText(String.valueOf(convertedAmount));
        });
    }

    private double convertCurrency(double amount) {
        double a = amount/USDprice;
        a*=Tomanprince;
        return a;
    }

    public void Confirm() throws SQLException {
        double adminmoney = 0;
        boolean flag = false;
        Alert alert = new Alert(Alert.AlertType.NONE);
        Connection connection;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");

        double status = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        while (resultSet.next()){
            if (resultSet.getString("role").equals("admin")){
                status = resultSet.getDouble("USD");
            }
        }

        if (status == 0) {
            if (frommenu.getText().equals("USD")) {
                if (datas.USD >= Double.parseDouble(fromvalue.getText()) && datas.total >= (Double.parseDouble(fromvalue.getText()) * datas.USDPrice) / 100) {
                    if (tomenu.getText().equals("Toman")) {
                        datas.USD -= Double.parseDouble(fromvalue.getText());
                        datas.TMN += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET USD = USD - ? , TMN = TMN + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                    } else if (tomenu.getText().equals("YEN")) {
                        datas.USD -= Double.parseDouble(fromvalue.getText());
                        datas.YEN += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET USD = USD - ? , YEN = YEN + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                    } else if (tomenu.getText().equals("GBT")) {
                        datas.USD -= Double.parseDouble(fromvalue.getText());
                        datas.GBT += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET USD = USD - ? , GBT = GBT + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                    } else if (tomenu.getText().equals("EUR")) {
                        datas.USD -= Double.parseDouble(fromvalue.getText());
                        datas.EUR += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET USD = USD - ? , EUR = EUR + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                    } else if (tomenu.getText().equals("USD")) {
                        warn.setText("ارزها یکسان است");
                    }
                    adminmoney = datas.USDPrice / 100;
                    String query2 = "UPDATE users SET Money = Money + ? WHERE role = ?";
                    PreparedStatement updateAdminMoney = connection.prepareStatement(query2);
                    updateAdminMoney.setDouble(1, adminmoney);
                    updateAdminMoney.setString(2, "admin");
                    updateAdminMoney.executeUpdate();
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirm");
                    alert.setHeaderText("تبادل موفقیت آمیز ");
                    alert.setContentText("تبادل موفقیت آمیز بود");
                    alert.showAndWait();
                }
            } else if (frommenu.getText().equals("Toman")) {
                if (datas.TMN >= Double.parseDouble(fromvalue.getText()) && datas.total >= (Double.parseDouble(fromvalue.getText()) * datas.TMNPrice) / 100) {
                    if (tomenu.getText().equals("USD")) {
                        datas.TMN -= Double.parseDouble(fromvalue.getText());
                        datas.USD += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET TMN = TMN - ? , USD = USD + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                    } else if (tomenu.getText().equals("YEN")) {
                        datas.TMN -= Double.parseDouble(fromvalue.getText());
                        datas.YEN += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET TMN = TMN - ? , YEN = YEN + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                    } else if (tomenu.getText().equals("GBT")) {
                        datas.TMN -= Double.parseDouble(fromvalue.getText());
                        datas.GBT += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET TMN = TMN - ? , GBT = GBT + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                    } else if (tomenu.getText().equals("EUR")) {
                        datas.TMN -= Double.parseDouble(fromvalue.getText());
                        datas.EUR += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET TMN = TMN - ? , EUR = EUR + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                    } else if (tomenu.getText().equals("Toman")) {
                        warn.setText("ارزها یکسان است");
                    }
                    adminmoney = datas.TMNPrice / 100;
                    String query2 = "UPDATE users SET Money = Money + ? WHERE role = ?";
                    PreparedStatement updateAdminMoney = connection.prepareStatement(query2);
                    updateAdminMoney.setDouble(1, adminmoney);
                    updateAdminMoney.setString(2, "admin");
                    updateAdminMoney.executeUpdate();
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirm");
                    alert.setHeaderText("تبادل موفقیت آمیز ");
                    alert.setContentText("تبادل موفقیت آمیز بود");
                    alert.showAndWait();
                }
            } else if (frommenu.getText().equals("YEN")) {
                if (datas.YEN >= Double.parseDouble(fromvalue.getText()) && datas.total >= (Double.parseDouble(fromvalue.getText()) * datas.YENPrice) / 100) {
                    if (tomenu.getText().equals("Toman")) {
                        datas.YEN -= Double.parseDouble(fromvalue.getText());
                        datas.TMN += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET YEN = YEN - ? , TMN = TMN + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                    } else if (tomenu.getText().equals("USD")) {
                        datas.YEN -= Double.parseDouble(fromvalue.getText());
                        datas.USD += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET YEN = YEN - ? , USD = USD + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                    } else if (tomenu.getText().equals("GBT")) {
                        datas.YEN -= Double.parseDouble(fromvalue.getText());
                        datas.GBT += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET YEN = YEN - ? , GBT = GBT + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                    } else if (tomenu.getText().equals("EUR")) {
                        datas.YEN -= Double.parseDouble(fromvalue.getText());
                        datas.EUR += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET YEN = YEN - ? , EUR = EUR + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                    } else if (tomenu.getText().equals("YEN")) {
                        warn.setText("ارزها یکسان است");
                    }
                    adminmoney = datas.YENPrice / 100;
                    String query2 = "UPDATE users SET Money = Money + ? WHERE role = ?";
                    PreparedStatement updateAdminMoney = connection.prepareStatement(query2);
                    updateAdminMoney.setDouble(1, adminmoney);
                    updateAdminMoney.setString(2, "admin");
                    updateAdminMoney.executeUpdate();
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirm");
                    alert.setHeaderText("تبادل موفقیت آمیز ");
                    alert.setContentText("تبادل موفقیت آمیز بود");
                    alert.showAndWait();
                }
            } else if (frommenu.getText().equals("GBT")) {
                if (datas.GBT >= Double.parseDouble(fromvalue.getText()) && datas.total >= (Double.parseDouble(fromvalue.getText()) * datas.GBPPrice) / 100) {
                    if (tomenu.getText().equals("Toman")) {
                        datas.GBT -= Double.parseDouble(fromvalue.getText());
                        datas.TMN += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET GBT = GBT - ? , TMN = TMN + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                    } else if (tomenu.getText().equals("YEN")) {
                        datas.GBT -= Double.parseDouble(fromvalue.getText());
                        datas.YEN += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET GBT = GBT - ? , YEN = YEN + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                    } else if (tomenu.getText().equals("USD")) {
                        datas.GBT -= Double.parseDouble(fromvalue.getText());
                        datas.USD += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET GBT = GBT - ? , USD = USD + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                    } else if (tomenu.getText().equals("EUR")) {
                        datas.GBT -= Double.parseDouble(fromvalue.getText());
                        datas.EUR += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET GBT = GBT - ? , EUR = EUR + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                    } else if (tomenu.getText().equals("GBT")) {
                        warn.setText("ارزها یکسان است");
                    }
                    adminmoney = datas.GBPPrice / 100;
                    String query2 = "UPDATE users SET Money = Money + ? WHERE role = ?";
                    PreparedStatement updateAdminMoney = connection.prepareStatement(query2);
                    updateAdminMoney.setDouble(1, adminmoney);
                    updateAdminMoney.setString(2, "admin");
                    updateAdminMoney.executeUpdate();
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirm");
                    alert.setHeaderText("تبادل موفقیت آمیز ");
                    alert.setContentText("تبادل موفقیت آمیز بود");
                    alert.showAndWait();
                }
            } else if (frommenu.getText().equals("EUR")) {
                if (datas.EUR >= Double.parseDouble(fromvalue.getText()) && datas.total >= (Double.parseDouble(fromvalue.getText()) * datas.EURPrice) / 100) {
                    if (tomenu.getText().equals("Toman")) {
                        datas.EUR -= Double.parseDouble(fromvalue.getText());
                        datas.TMN += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET EUR = EUR - ? , TMN = TMN + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                    } else if (tomenu.getText().equals("YEN")) {
                        datas.EUR -= Double.parseDouble(fromvalue.getText());
                        datas.YEN += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET EUR = EUR - ? , YEN = YEN + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                    } else if (tomenu.getText().equals("GBT")) {
                        datas.EUR -= Double.parseDouble(fromvalue.getText());
                        datas.GBT += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET EUR = EUR - ? , GBT = GBT + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                    } else if (tomenu.getText().equals("USD")) {
                        datas.EUR -= Double.parseDouble(fromvalue.getText());
                        datas.USD += Double.parseDouble(tovalue.getText());
                        String query = "UPDATE users SET EUR = EUR - ? , USD = USD + ? WHERE fullname =  ?";
                        PreparedStatement updateUserMoney = connection.prepareStatement(query);
                        updateUserMoney.setDouble(1, Double.parseDouble(fromvalue.getText()));
                        updateUserMoney.setDouble(2, Double.parseDouble(tovalue.getText()));
                        updateUserMoney.setString(3, datas.username);
                        updateUserMoney.executeUpdate();
                    } else if (tomenu.getText().equals("EUR")) {
                        warn.setText("ارزها یکسان است");
                    }
                    adminmoney = datas.EURPrice / 100;
                    String query2 = "UPDATE users SET Money = Money + ? WHERE role = ?";
                    PreparedStatement updateAdminMoney = connection.prepareStatement(query2);
                    updateAdminMoney.setDouble(1, adminmoney);
                    updateAdminMoney.setString(2, "admin");
                    updateAdminMoney.executeUpdate();
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirm");
                    alert.setHeaderText("تبادل موفقیت آمیز ");
                    alert.setContentText("تبادل موفقیت آمیز بود");
                    alert.showAndWait();
                }
            }
        }
        else {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("بازار بسته ");
            alert.setContentText("بازار توسط ادمین بسته شده است!");
            alert.showAndWait();
        }
    }

}
