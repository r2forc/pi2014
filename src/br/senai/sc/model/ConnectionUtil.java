package br.senai.sc.model;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	private static java.sql.Connection con;

	public static java.sql.Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/r2forc";
			con = DriverManager.getConnection(url, "root", "26051993");
			con.setAutoCommit(false);
			return con;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return null;
	}

	public static void closeConnection() {
		try {
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
