package com.example.moovies;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;

public class loginAction {

    @FXML
    public MFXTextField login;

    @FXML
    private MFXPasswordField password;

    @FXML
    private MFXButton zaloguj;

    @FXML
    private MFXButton zarejestruj;

    static void wypisz(){
            System.out.print("test");
        }
}

