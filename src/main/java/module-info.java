module com.example.moovies {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires MaterialFX;
    requires mysql.connector.java;


    opens com.example.moovies to javafx.fxml;
    exports com.example.moovies;
}