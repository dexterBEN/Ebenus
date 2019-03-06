/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao;

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

	// constructor:
	private DriverManagerSingleton() {

	}

	synchronized public static DriverManagerSingleton getConnectionInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DriverManagerSingleton();
		}
		return uniqueInstance;
	}
}
