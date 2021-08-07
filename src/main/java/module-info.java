module MIDAPROJECT {
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires javafx.controls;
    requires mysql.connector.java;
    requires itextpdf;
    requires javafx.swing;
    requires jasperreports;
    opens sample;
    opens controller;
    opens model;
}