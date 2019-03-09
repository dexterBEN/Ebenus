/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.dao.ConnectionHelper;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;

/**
 *
 * @author ElHadji
 */
public class UtilisateurDao /* extends AbstractDao<Utilisateur> */ implements IUtilisateurDao {

	private static final Log log = LogFactory.getLog(UtilisateurDao.class);

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";
	static final String DB_EBENUS_URL = "jdbc:mysql://localhost/base_quest_ebenus";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";

	// public UtilisateurDao() {
	// super(Utilisateur.class);
	// }
	@Override
	public List<Utilisateur> findAllUtilisateurs() {
		Connection conn = null;
		Statement stmt = null;

		List<Utilisateur> users = new ArrayList<Utilisateur>();

		try {

			TimeZone timezone = TimeZone.getTimeZone("Europe/Paris");
			TimeZone.setDefault(timezone);

			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Connect to specific DB
			System.out.println("Connecting to DB base_quest_ebenus...");
			conn = DriverManager.getConnection(DB_EBENUS_URL, USER, PASS);
			stmt = conn.createStatement();
			// SQL request for table user
			ResultSet rs = stmt.executeQuery("SELECT * FROM utilisateur");

			while (rs.next()) {

				String name = rs.getString("nom");
				String firstName = rs.getString("prenom");
				String gender = rs.getString("civilite");
				String mail = rs.getString("identifiant");
				String password = rs.getString("motPasse");
				Date birthDate = rs.getDate("dateNaissance");
				Date createDate = rs.getDate("dateCreation");
				Date updateDate = rs.getDate("dateModification");
				Boolean activityState = rs.getBoolean("actif");
				Boolean markAsErased = rs.getBoolean("marquerEffacer");
				int idUser = rs.getInt("idUtilisateur");
				int version = rs.getInt("version");

				Utilisateur user = new Utilisateur(idUser, gender, firstName, name, mail, password, birthDate,
						createDate, updateDate, activityState, markAsErased, version, null);

				users.add(user);
			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}

		return users;
	}

	@Override
	public Utilisateur findUtilisateurById(int idUtilisateur) {
		Connection conn = null;
		Statement stmt = null;
		Utilisateur user = null;
		try {

			TimeZone timezone = TimeZone.getTimeZone("Europe/Paris");
			TimeZone.setDefault(timezone);

			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Connect to specific DB
			System.out.println("Connecting to DB base_quest_ebenus...");
			conn = DriverManager.getConnection(DB_EBENUS_URL, USER, PASS);
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM utilisateur WHERE idUtilisateur = " + idUtilisateur);

			if (rs.next()) {

				String name = rs.getString("nom");
				String firstName = rs.getString("prenom");
				String gender = rs.getString("civilite");
				String mail = rs.getString("identifiant");
				String password = rs.getString("motPasse");
				Date birthDate = rs.getDate("dateNaissance");
				Date createDate = rs.getDate("dateCreation");
				Date updateDate = rs.getDate("dateModification");
				Boolean activityState = rs.getBoolean("actif");
				Boolean markAsErased = rs.getBoolean("marquerEffacer");
				int idUser = rs.getInt("idUtilisateur");
				int version = rs.getInt("version");

				user = new Utilisateur(idUser, gender, firstName, name, mail, password, birthDate, createDate,
						updateDate, activityState, markAsErased, version, null);

				System.out.print("id: " + user.getIdUtilisateur() + "\n civilité: " + user.getCivilite() + "\n prénom: "
						+ user.getPrenom());
			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
		Connection conn = null;
		Statement stmt = null;
		List<Utilisateur> users = new ArrayList<Utilisateur>();

		try {

			TimeZone timezone = TimeZone.getTimeZone("Europe/Paris");
			TimeZone.setDefault(timezone);

			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Connect to specific DB
			System.out.println("Connecting to DB base_quest_ebenus...");
			conn = DriverManager.getConnection(DB_EBENUS_URL, USER, PASS);
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM utilisateur WHERE prenom = '" + prenom + "'");

			while (rs.next()) {
				String name = rs.getString("nom");
				String firstName = rs.getString("prenom");
				String gender = rs.getString("civilite");
				String mail = rs.getString("identifiant");
				String password = rs.getString("motPasse");
				Date birthDate = rs.getDate("dateNaissance");
				Date createDate = rs.getDate("dateCreation");
				Date updateDate = rs.getDate("dateModification");
				Boolean activityState = rs.getBoolean("actif");
				Boolean markAsErased = rs.getBoolean("marquerEffacer");
				int idUser = rs.getInt("idUtilisateur");
				int version = rs.getInt("version");

				Utilisateur user = new Utilisateur(idUser, gender, firstName, name, mail, password, birthDate,
						createDate, updateDate, activityState, markAsErased, version, null);

				users.add(user);

			}

			for (Utilisateur user : users) {
				System.out.println(user);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
	}

	@Override
	public List<Utilisateur> findUtilisateursByNom(String nom) {
		Connection conn = null;
		Statement stmt = null;
		List<Utilisateur> users = new ArrayList<Utilisateur>();

		try {

			TimeZone timezone = TimeZone.getTimeZone("Europe/Paris");
			TimeZone.setDefault(timezone);

			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Connect to specific DB
			System.out.println("Connecting to DB base_quest_ebenus...");
			conn = DriverManager.getConnection(DB_EBENUS_URL, USER, PASS);
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM utilisateur WHERE nom = '" + nom + "'");

			while (rs.next()) {
				String name = rs.getString("nom");
				String firstName = rs.getString("prenom");
				String gender = rs.getString("civilite");
				String mail = rs.getString("identifiant");
				String password = rs.getString("motPasse");
				Date birthDate = rs.getDate("dateNaissance");
				Date createDate = rs.getDate("dateCreation");
				Date updateDate = rs.getDate("dateModification");
				Boolean activityState = rs.getBoolean("actif");
				Boolean markAsErased = rs.getBoolean("marquerEffacer");
				int idUser = rs.getInt("idUtilisateur");
				int version = rs.getInt("version");

				Utilisateur user = new Utilisateur(idUser, gender, firstName, name, mail, password, birthDate,
						createDate, updateDate, activityState, markAsErased, version, null);

				users.add(user);
			}

			for (Utilisateur user : users) {
				System.out.println(user);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public List<Utilisateur> findUtilisateurByIdentifiant(String identifiant) {
		Connection conn = null;
		Statement stmt = null;
		List<Utilisateur> users = new ArrayList<Utilisateur>();

		try {

			TimeZone timezone = TimeZone.getTimeZone("Europe/Paris");
			TimeZone.setDefault(timezone);

			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Connect to specific DB
			System.out.println("Connecting to DB base_quest_ebenus...");
			conn = DriverManager.getConnection(DB_EBENUS_URL, USER, PASS);
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM utilisateur WHERE identifiant = '" + identifiant + "'");

			while (rs.next()) {
				String name = rs.getString("nom");
				String firstName = rs.getString("prenom");
				String gender = rs.getString("civilite");
				String mail = rs.getString("identifiant");
				String password = rs.getString("motPasse");
				Date birthDate = rs.getDate("dateNaissance");
				Date createDate = rs.getDate("dateCreation");
				Date updateDate = rs.getDate("dateModification");
				Boolean activityState = rs.getBoolean("actif");
				Boolean markAsErased = rs.getBoolean("marquerEffacer");
				int idUser = rs.getInt("idUtilisateur");
				int version = rs.getInt("version");

				Utilisateur user = new Utilisateur(idUser, gender, firstName, name, mail, password, birthDate,
						createDate, updateDate, activityState, markAsErased, version, null);

				users.add(user);
			}

			for (Utilisateur user : users) {
				System.out.println(user);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
	}

	@Override
	public List<Utilisateur> findUtilisateursByIdRole(int idRole) {
		PreparedStatement statement = null;
		Connection connection = null;
		ResultSet result = null;

		List<Utilisateur> users = new ArrayList<Utilisateur>();

		try {

			statement = connection.prepareStatement(
					"SELECT * FROM utilisateur INNER JOIN role r ON r.idRole = u.idRole WHERE u.idRole = ? ");
			statement.setInt(1, idRole);

			result = statement.executeQuery();

			while (result.next()) {

				Utilisateur user = new Utilisateur(result.getInt("u.idUtilisateur"), result.getString("u.civilite"),
						result.getString("u.prenom"), result.getString("u.nom"), result.getString("u.identifiant"),
						result.getString("u.motPasse"), result.getDate("u.dateNaissance"),
						result.getDate("u.dateCreation"), result.getDate("u.dateModification"),
						result.getBoolean("u.actif"), result.getBoolean("u.marquerEffacer"), result.getInt("u.version"),
						new Role(result.getInt("r.idRole"), result.getString("r.identifiant"),
								result.getString("r.description"), result.getInt("r.version")));

				users.add(user);

			}

		} catch (Throwable e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.closeSqlResources(statement, result);
		}

		return users;
	}

	@Override
	public List<Utilisateur> findUtilisateursByIdentifiantRole(String identifiantRole) {
		PreparedStatement statement = null;
		Connection connection = null;
		ResultSet result = null;
		List<Utilisateur> users = new ArrayList<>();

		try {

			statement = connection.prepareStatement(
					"SELECT * FROM utilisateur u INNER JOIN role r ON r.idRole = u.idRole WHERE r.identifiant = ?");
			statement.setString(1, identifiantRole);

			result = statement.executeQuery();

			while (result.next()) {

				Utilisateur user = new Utilisateur(result.getInt("u.idUtilisateur"), result.getString("u.civilite"),
						result.getString("u.prenom"), result.getString("u.nom"), result.getString("u.identifiant"),
						result.getString("u.motPasse"), result.getDate("u.dateNaissance"),
						result.getDate("u.dateCreation"), result.getDate("u.dateModification"),
						result.getBoolean("u.actif"), result.getBoolean("u.marquerEffacer"), result.getInt("u.version"),
						new Role(result.getInt("r.idRole"), result.getString("r.identifiant"),
								result.getString("r.description"), result.getInt("r.version")));

				users.add(user);

			}

		} catch (Throwable e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.closeSqlResources(statement, null);
		}

		return users;
	}

	@Override
	public Utilisateur createUtilisateur(Utilisateur user) {
		Connection conn = null;
		Statement stmt = null;

		try {

			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Connect to specific DB
			System.out.println("Connecting to DB base_quest_ebenus...");
			conn = DriverManager.getConnection(DB_EBENUS_URL, USER, PASS);
			stmt = conn.createStatement();
			String isActif = user.isActif() ? "1" : "0";
			String isErased = user.isMarquerEffacer() ? "1" : "0";

			int rs = stmt.executeUpdate(
					"INSERT INTO `utilisateur` (`idRole`, `civilite`, `prenom`, `nom`, `identifiant`, `motPasse`, `dateNaissance`, `dateCreation`, `dateModification`, `actif`, `marquerEffacer`, `version`) VALUES ('1', '"
							+ user.getCivilite() + "', '" + user.getPrenom() + "', '" + user.getNom() + "', '"
							+ user.getIdentifiant() + "', '" + user.getMotPasse()
							+ "', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '" + isActif + "', '"
							+ isErased + "', '" + user.getVersion() + "');");

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public Utilisateur updateUtilisateur(Utilisateur user) {
		return null;
	}

	@Override
	public boolean deleteUtilisateur(Utilisateur user) {
		Connection conn = null;
		Statement stmt = null;

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Connect to specific DB
			System.out.println("Connecting to DB base_quest_ebenus...");
			conn = DriverManager.getConnection(DB_EBENUS_URL, USER, PASS);
			stmt = conn.createStatement();

			int rs = stmt
					.executeUpdate("DELETE FROM `utilisateur` WHERE `idUtilisateur`=" + user.getIdUtilisateur() + "");
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Méthode qui vérifie les logs email / password d'un utilisateur dans la base
	 * de données
	 *
	 * @param email    L'email de l'utilisateur
	 * @param password Le password de l'utilisateur
	 * @return L'utilisateur qui tente de se logger si trouvé, null sinon
	 */
	@Override
	public Utilisateur authenticate(String email, String password) {
		return null;
	}
}
