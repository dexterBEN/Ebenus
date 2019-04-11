package com.cours.ebenus.maven.ebenus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cours.ebenus.maven.ebenus.dao.connection.DriverManagerSingleton;

public class LoginDao {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";
	static final String DB_STUDENT_URL = "jdbc:mysql://localhost/base_site_commerce_ebenus";
	// Database credentials
	static final String USER = "root";
	static final String PASS = "";

	static Connection conn = null;
	static PreparedStatement statement = null;
	static ResultSet result = null;

	public static boolean validate(String email, String password) {
		boolean state = false;

		try {

			conn = DriverManagerSingleton.getConnectionInstance().getConnection();

			statement = conn.prepareStatement(
					"SELECT * FROM utilisateur WHERE identifiant='" + email + "'  AND motPasse='" + password + "'");

			statement.setString(1, email);
			statement.setString(2, password);

			ResultSet rs = statement.executeQuery();
			state = rs.next();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return state;
	}

}
