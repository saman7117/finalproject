module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.scripting;
    requires java.mail;
    requires commons.math3;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}