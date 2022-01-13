package com.example.moovies;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import io.github.palexdev.materialfx.controls.MFXTextField;

import java.io.IOException;


public class HelloApplication extends Application {
    public static Stage mainLoginScene;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 400);
        mainLoginScene = stage;
        stage.setTitle("Moovies");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }



    public static void main(String[] args) throws Exception {
        launch();
        if(Mysql.check==true){

        }
    }


}