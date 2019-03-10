/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.utils.Constants;

/**
 *
 * @author ElHadji
 */
public class DriverManagerSingleton {

	private static final Log log = LogFactory.getLog(DriverManagerSingleton.class);

	public final static String className = DriverManagerSingleton.class.getName();
	// Url de connexion en base de donnée
	private static final String url = Constants.DATABASE_URL;

	// Utilisateur de la base de données
	private static final String user = Constants.DATABASE_USER;

	// Mot de passe de la base de données
	private static final String password = Constants.DATABASE_PASSWORD;

	// Drivers Jdbc
	private static final String jdbcDriver = Constants.JDBC_DRIVER;

	private static DriverManagerSingleton uniqueInstance = null;

	private Connection conn = null;

	// constructor:
	private DriverManagerSingleton() {

		// PreparedStatement stmt = null;
		try {

			TimeZone timezone = TimeZone.getTimeZone("Europe/Paris");
			TimeZone.setDefault(timezone);

			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 4: Connect to DB
			System.out.println("Connecting to DB base_quest_ebenus...");
			conn = DriverManager.getConnection(url, user, password);
			// stmt = conn.prepareStatement(sql);

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return conn;
	}

	synchronized public static DriverManagerSingleton getConnectionInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DriverManagerSingleton();
		}
		return uniqueInstance;
	}
}
