package com.example.moovies;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXLabel;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class HelloController {

    @FXML
    private MFXTextField login;

    @FXML
    private MFXPasswordField password;

    @FXML
    private MFXButton zaloguj;

    @FXML
    private MFXButton odswiez;

    @FXML
    private TableView<Mysql> tabela;

    @FXML
    private TableColumn<Mysql, SimpleStringProperty> autor;

    @FXML
    private TableColumn<Mysql, SimpleStringProperty> nazwa;

    @FXML
    private MFXButton dodaj;

    @FXML
    private MFXTextField txtAutor;

    @FXML

    private MFXTextField  txtNazwa;
    @FXML
    private MFXTextField  nowaWartosc;

    @FXML
    private MFXLabel admin;

    String nowaWartoscString = null;

    @FXML
    private MFXButton zapiszWartosc;

    @FXML
    void oknoDodajFilm(MouseEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Dodaj film");
        stage.setScene(new Scene(root1));
        stage.show();
    }



    public ObservableList<Mysql> filmy() throws Exception {
        HelloApplication baza = new HelloApplication();
        ObservableList<Mysql> filmy = FXCollections.observableArrayList();
        baza.readDataBase("x","x",3);

        String tabNazwa;
        String tabAutor;
        for(int i=0; i<baza.tabFilmy.size();i++){
            tabNazwa = baza.tabFilmy.get(i);
            i++;
            tabAutor = baza.tabFilmy.get(i);
            filmy.add(new Mysql(tabNazwa,tabAutor));
        }
        return filmy;
    }

    @FXML
    void odswiezFilmy(MouseEvent event) throws Exception {
        HelloApplication baza = new HelloApplication();

        nazwa.setCellValueFactory(new PropertyValueFactory<Mysql, SimpleStringProperty>("nazwa"));
        autor.setCellValueFactory(new PropertyValueFactory<Mysql, SimpleStringProperty>("autor"));

        tabela.setItems(filmy());

        tabela.setEditable(true);
    }

    String tempNazwa = null;
    @FXML
    public void handle(MouseEvent event) throws Exception {
        TableView.TableViewSelectionModel<Mysql> p = tabela.getSelectionModel();
        TablePosition pos = p.getSelectedCells().get(0);
        int row = pos.getRow();
        TableColumn col = pos.getTableColumn();
        tempNazwa = col.getCellObservableValue(tabela.getItems().get(row)).getValue().toString();
        System.out.println(tempNazwa);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("change.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Dodaj film");
        stage.setScene(new Scene(root1));
        stage.show();

    }


    @FXML
    public void zmien() throws Exception {
        HelloApplication controller = new HelloApplication();

        nowaWartoscString = nowaWartosc.getText();
        Stage stage = (Stage) zapiszWartosc.getScene().getWindow();
        stage.close();

        //System.out.println(nowaWartoscString);
       // System.out.println(tempNazwa);
        //controller.readDataBase(nowaWartoscString, tempNazwa, 5);

        //zmienTabele();
    }

    @FXML
    public String zmienTabele() throws Exception {
        HelloApplication controller = new HelloApplication();

        TableView.TableViewSelectionModel<Mysql> p = tabela.getSelectionModel();
        TablePosition pos = p.getSelectedCells().get(0);
        int row = pos.getRow();
        TableColumn col = pos.getTableColumn();

        tempNazwa = col.getCellObservableValue(tabela.getItems().get(row)).getValue().toString();

        /*String tempNazwa;
        if (Objects.equals(col.getText(), "Nazwa")) {
            tempNazwa = col.getCellObservableValue(tabela.getItems().get(row)).toString();
            System.out.print(tempNazwa);
            controller.readDataBase(nowaWartoscString, tempNazwa, 5);
            //col.getCellObservableValue(tabela.getItems().get(row).setNazwa(nowaWartosc));
        } else {
            //col.getCellObservableValue(tabela.getItems().get(row).setAutor(nowaWartosc));
        }*/
        return tempNazwa;
    }
}
