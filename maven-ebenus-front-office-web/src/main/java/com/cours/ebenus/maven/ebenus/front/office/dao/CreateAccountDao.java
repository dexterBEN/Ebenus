package com.cours.ebenus.maven.ebenus.front.office.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.TimeZone;

public class CreateAccountDao {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";
	static final String DB_STUDENT_URL = "jdbc:mysql://localhost/base_site_commerce_ebenus";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";

	public static boolean createNewUser(String civilite, String prenom, String nom, String identifiant,
			String motPasse) {
		Connection conn = null;
		Statement stmt = null;
		boolean status = false;

		try {
			TimeZone timezone = TimeZone.getTimeZone("Europe/Paris");
			TimeZone.setDefault(timezone);
			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Connect to db_students:
			System.out.println("Connecting to database user...");
			conn = DriverManager.getConnection(DB_STUDENT_URL, USER, PASS);
			stmt = conn.createStatement();

			int queryState = stmt.executeUpdate(
					"INSERT INTO utilisateur (idRole, civilite, prenom, nom, identifiant, motPasse) VALUES(5, '"
							+ civilite + "','"
							+ prenom + "', '"
					+ nom + "', '" + identifiant + "', '" + motPasse + "') ");
			// ps.setString(1, mail);
			// ps.setString(2, password);

			// ResultSet rs = ps.executeQuery();
			if (queryState == 1) {
				status = true;
			} else {
				status = false;
				System.out.println("Cannot create the user !");
			}

			// status = rs.next();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

}
