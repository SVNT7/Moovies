package com.example.moovies;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Mysql {

	public static boolean check;

	@FXML
	private AnchorPane anchorX;

	@FXML
	private MFXTextField login;

	@FXML
	private MFXPasswordField password;

	@FXML
	private MFXButton zaloguj;

	@FXML
	void printHelloWorld(MouseEvent event) throws Exception {
		event.consume();

		Mysql baza = new Mysql();

		String loginTxt = login.getText();
		String passwordTxt = password.getPassword();
		baza.readDataBase(loginTxt, passwordTxt);


	}

	/* polaczyc sie do bazy danych i zapamietac to polaczenie
	 * przygotowac dane
	 * wstawic dane
	 * przeczytac dane
	 * zamknac polaczenie z baza
	 */
	private Connection connect=null;//uchwyt do polaczenia z baza
	private Statement statement=null;//tu bedzie trzymane zapytanie SELECT
	private ResultSet resultSet=null;//tu bedzie trzymany wynik tego zapytania
	private PreparedStatement preparedStatement=null;//do przygotowania INSERTA

	private String host="192.166.219.220";//host czyli adres serwera bazy
	private String user="moovies";//user bazy domyslnie root
	private String pass="kDm.2iIez2.";//brak domyslnego hasla w XAMPP

	public Mysql() {

	}

	public void readDataBase(String loginTxt, String passwordTxt) throws Exception {

		try {
			//blok try trzyma w sobie wszystkie potencjalnie niebezpieczne instrukcje
			//zaladowanie sterownika do mysql
			Class.forName("com.mysql.jdbc.Driver");
			//lacze sie do bazy danych
			connect=DriverManager.getConnection("jdbc:mysql://192.166.219.220/moovies", user, pass);
			//jdbc:mysql://localhost/firma?user=root&password=
			//firma to nazwa bazy danych

			statement=connect.createStatement();
			String query = "SELECT submitter, report FROM bugs_log ORDER BY id DESC limit ?";

			resultSet=statement.executeQuery("SELECT login, password FROM users where login='"+loginTxt+"' and password='"+passwordTxt+"'");
			writeResultSet(resultSet, loginTxt, passwordTxt);

			////INSERT//////////////////////////////////////
//			preparedStatement =connect.prepareStatement("INSERT INTO users VALUES(default, ?, ?, ?, ?)");
//
//			preparedStatement.setString(1, "Piotr");
//			preparedStatement.setString(2, "tajne");
//
//			preparedStatement.setInt(3, 32323);
//			preparedStatement.setString(4, "Stargard");
//			preparedStatement.executeUpdate();

		}catch (Exception e) {
			System.out.println("SQLException: " + e.getMessage());
			//System.out.println("SQLState: " + e.getSQLState());
			//System.out.println("VendorError: " + e.getErrorCode());
		}
	}

	private void writeResultSet(ResultSet resultSet, String loginTxt, String passwordTxt) throws SQLException, IOException {

		while (resultSet.next()) {
			String loginDb=resultSet.getString("login");
			String passwordDb=resultSet.getString("password");

			//System.out.println(loginDb);
			//System.out.println(passwordDb);
			if (loginTxt.equals(loginDb) && passwordTxt.equals(passwordDb)) {
				HelloApplication.mainLoginScene.close();
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("main_info.fxml"));
				/*
				 * if "fx:controller" is not set in fxml
				 * fxmlLoader.setController(NewWindowController);
				 */
				Scene scene = new Scene(fxmlLoader.load(), 600, 400);
				Stage stage = new Stage();
				stage.setTitle("New Window");
				stage.setScene(scene);
				stage.show();
			} else {
				System.out.println("Incorrect Password\n----");
			}

		}
	}
}


