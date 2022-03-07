package com.example.moovies;

import io.github.palexdev.materialfx.controls.*;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


public class HelloApplication extends Application {
    public static Stage mainLoginScene;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 400);
        mainLoginScene = stage;
        stage.setTitle("Moovies");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

    }


    public static void main(String[] args) throws Exception {
        launch();
        //if(Mysql.check==true){}
    }

    public boolean isAdmin;
    public String nazwaFirmy;

    @FXML
    private AnchorPane anchorX;

    @FXML
    private MFXTextField login;

    @FXML
    private MFXPasswordField password;

    @FXML
    private MFXLabel komunikat;

    @FXML
    private MFXButton zaloguj;

    @FXML
    private MFXLabel admin;

    @FXML
    private MFXButton zarejestruj;

    @FXML
    private MFXButton dodaj;

    @FXML
    private MFXButton powrot;

    @FXML
    private MFXTextField txtAutor;

    @FXML
    private MFXTextField txtNazwa;



    @FXML
    void printHelloWorld(MouseEvent event) throws Exception {
        event.consume();

        String loginTxt = login.getText();
        String passwordTxt = password.getPassword();
        readDataBase(loginTxt, passwordTxt, 1);
    }

    @FXML
    void oknoRejestracji(MouseEvent event) throws Exception {
        Stage stage = (Stage) zaloguj.getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registration.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Rejestracja");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void dodajFilm(MouseEvent event) throws Exception {
        readDataBase(txtNazwa.getText(), txtAutor.getText(), 4);
        Stage stage = (Stage) dodaj.getScene().getWindow();
        stage.close();
    }

    @FXML
    void zarejestrujSie(MouseEvent event) throws Exception {
        String loginTxt = login.getText();
        String passwordTxt = password.getPassword();

        readDataBase(loginTxt, passwordTxt, 2);
    }
    @FXML
    void powrot(MouseEvent event) throws Exception {
        Stage stage = (Stage) zarejestruj.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Rejestracja");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void powrot2(MouseEvent event) throws Exception {
        Stage stage = (Stage) powrot.getScene().getWindow();
        stage.close();

    }



    private Connection connect=null;//uchwyt do polaczenia z baza
    private Statement statement=null;//tu bedzie trzymane zapytanie SELECT
    private ResultSet resultSet=null;//tu bedzie trzymany wynik tego zapytania
    private PreparedStatement preparedStatement=null;//do przygotowania INSERTA

    /*private String host="192.166.219.220";//host czyli adres serwera bazy
    private String user="moovies";//user bazy domyslnie root
    private String pass="kDm.2iIez2.";//brak domyslnego hasla w XAMPP*/

    private String host="127.0.0.1";//host czyli adres serwera bazy
    private String user="moovies";//user bazy domyslnie root
    private String pass="kDm.2iIez2.";//brak domyslnego hasla w XAMPP


    public void readDataBase(String loginTxt, String passwordTxt, int type) throws Exception {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect= DriverManager.getConnection("jdbc:mysql://127.0.0.1/moovies", user, pass);

            statement=connect.createStatement();

            if(type == 1) {
                resultSet = statement.executeQuery("SELECT login, password, isAdmin FROM users where login='" + loginTxt + "' and password='" + passwordTxt + "'");
                writeResultSet(resultSet, loginTxt, passwordTxt, 1);
            }
            if(type == 2) {
                statement.executeUpdate("INSERT into users(login, password, isAdmin) values('"+loginTxt+"','"+passwordTxt+"', true)");
                writeResultSet(resultSet, loginTxt, passwordTxt, 2);
                komunikat.setTextFill(Color.GREEN);
                komunikat.setText("Pomyslnie dodano konto");
            }
            if(type == 3) {
                HelloController controller = new HelloController();
                resultSet = statement.executeQuery("SELECT nazwa, autor FROM movies");
                writeResultSet2(resultSet);
            }
            if(type == 4) {
                statement.executeUpdate("INSERT into movies(nazwa, autor) values('"+loginTxt+"','"+passwordTxt+"')");
                //writeResultSet(resultSet, loginTxt, passwordTxt, 2);
            }
            if(type == 5) {
                statement.executeUpdate("UPDATE movies SET nazwa ='"+loginTxt+"' WHERE nazwa ='"+passwordTxt+"'");
                //writeResultSet(resultSet, loginTxt, passwordTxt, 2);
            }

        }catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            //System.out.println("SQLState: " + e.getSQLState());
            //System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    private void writeResultSet(ResultSet resultSet, String loginTxt, String passwordTxt, int type) throws SQLException, IOException, ClassNotFoundException {
        while (resultSet.next()) {
            String loginDb=resultSet.getString("login");

            if(type == 1) {
                String passwordDb=resultSet.getString("password");
                boolean isAdminDb=resultSet.getBoolean("isAdmin");
                if (loginTxt.equals(loginDb) && passwordTxt.equals(passwordDb)) {
                    isAdmin = isAdminDb;

                    Stage stage = (Stage) zaloguj.getScene().getWindow();
                    stage.close();
                    Parent tableViewParent = FXMLLoader.load(getClass().getResource("main_info.fxml"));
                    Scene tableScene = new Scene(tableViewParent);
                    stage = new Stage();
                    stage.setResizable(false);
                    stage.setScene(tableScene);
                    stage.show();

                } else {
                    System.out.println("Incorrect Password\n----");
                }
            }
            if(type == 2){
                if (loginTxt.equals(loginDb)) {
                    komunikat.setTextFill(Color.RED);
                    komunikat.setText("Taki login juz istnieje w bazie");
                }
            }
        }
    }

    public ArrayList<String> tabFilmy = new ArrayList<String>();
    private void writeResultSet2(ResultSet resultSet) throws SQLException, IOException, ClassNotFoundException {
        HelloController tab = new HelloController();

        while (resultSet.next()) {
            String nazwaDb=resultSet.getString("nazwa");
            String autorDb=resultSet.getString("autor");

            tabFilmy.add(nazwaDb);
            tabFilmy.add(autorDb);

        }
        //System.out.println(tabFilmy);
    }

}