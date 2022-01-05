package com.example.moovies;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class HelloController {

    @FXML
    private MFXTextField login;

    @FXML
    private MFXPasswordField password;

    @FXML
    private MFXButton zaloguj;

    @FXML
    void printHelloWorld(MouseEvent event) {
        event.consume();

        String loginTxt = login.getText();
        System.out.println(loginTxt);
    }

}
