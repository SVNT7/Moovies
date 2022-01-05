package com.example.moovies;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Mysql {

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

	public void readDataBase() throws Exception {
		try {
			//blok try trzyma w sobie wszystkie potencjalnie niebezpieczne instrukcje
			//zaladowanie sterownika do mysql
			Class.forName("com.mysql.jdbc.Driver");
			//lacze sie do bazy danych
			connect=DriverManager.getConnection("jdbc:mysql://192.166.219.220/moovies", "moovies", "kDm.2iIez2.");
			//jdbc:mysql://localhost/firma?user=root&password=
			//firma to nazwa bazy danych

			statement=connect.createStatement();
			resultSet=statement.executeQuery("SELECT login, password FROM users");
			writeResultSet(resultSet);

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

	private void writeResultSet(ResultSet resultSet) throws SQLException
	{
		while (resultSet.next()) {
			String login=resultSet.getString("login");
			String city=resultSet.getString("password");

			System.out.println(login);
		}
	}


}
