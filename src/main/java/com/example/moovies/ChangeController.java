package com.example.moovies;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class ChangeController {

    String wartoscZmienna;

    @FXML
    private MFXTextField nowaWartosc;

    @FXML
    private MFXButton zapiszWartosc;

    @FXML
    private TableView<Mysql> tabela;

    /*@FXML
    public void zmien() throws Exception {
        HelloController controller = new HelloController();
        controller.nowaWartosc = nowaWartosc.getText();
        Stage stage = (Stage) zapiszWartosc.getScene().getWindow();
        stage.close();
        controller.zmienTabele();
    }*/

}
