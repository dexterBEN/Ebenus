package com.cours.ebenus.maven.ebenus.dao.connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConnectionHelper {

	private static final Log log = LogFactory.getLog(ConnectionHelper.class);
	public final static String className = ConnectionHelper.class.getName();

	public static void closeSqlResources(PreparedStatement preparedStatement, ResultSet result) {
		try {
			 if (preparedStatement != null) {
				 preparedStatement.close();
			 }
			 
			 if (result != null) {
				result.close();
			}
		} catch (SQLException e) {
			e.getMessage();
			log.warn("Sql error: " + e.getMessage());
		}
	}

}
