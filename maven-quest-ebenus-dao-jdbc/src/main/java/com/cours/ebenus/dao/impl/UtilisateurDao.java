/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.dao.ConnectionHelper;
import com.cours.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.utils.Constants;

/**
 *
 * @author ElHadji
 */
public class UtilisateurDao /* extends AbstractDao<Utilisateur> */ implements IUtilisateurDao {

	private static final Log log = LogFactory.getLog(UtilisateurDao.class);

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";
	// static final String DB_EBENUS_URL =
	// "jdbc:mysql://localhost/base_quest_ebenus";
	static final String DB_EBENUS_URL = Constants.DATABASE_URL;

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";

	Connection conn = null;
	PreparedStatement statement = null;
	ResultSet result = null;
	final RoleDao roleDao = new RoleDao();

	public UtilisateurDao() {
		// super(Utilisateur.class);
		try {
			conn = DriverManagerSingleton.getConnectionInstance().getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Utilisateur> findAllUtilisateurs() {

		List<Utilisateur> users = new ArrayList<Utilisateur>();

		try {

			/*
			 * TimeZone timezone = TimeZone.getTimeZone("Europe/Paris");
			 * TimeZone.setDefault(timezone);
			 * 
			 * // STEP 2: Register JDBC driver Class.forName("com.mysql.jdbc.Driver");
			 * 
			 * // STEP 3: Open a connection System.out.println("Connecting to database...");
			 * 
			 * // STEP 4: Connect to specific DB
			 * System.out.println("Connecting to DB base_quest_ebenus..."); conn =
			 * DriverManager.getConnection(DB_EBENUS_URL, USER, PASS);
			 */
			statement = conn.prepareStatement("SELECT * FROM utilisateur");
			// SQL request for table user
			ResultSet rs = statement.executeQuery();

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

				int idRole = rs.getInt("idRole");
				Role role = roleDao.findRoleById(idRole);

				Utilisateur user = new Utilisateur(idUser, gender, firstName, name, mail, password, birthDate,
						createDate, updateDate, activityState, markAsErased, version, role);

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
		Utilisateur user = null;
		try {

			/*
			 * TimeZone timezone = TimeZone.getTimeZone("Europe/Paris");
			 * TimeZone.setDefault(timezone);
			 * 
			 * // STEP 2: Register JDBC driver Class.forName("com.mysql.jdbc.Driver");
			 * 
			 * // STEP 3: Open a connection System.out.println("Connecting to database...");
			 * conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 * 
			 * // STEP 4: Connect to specific DB
			 * System.out.println("Connecting to DB base_quest_ebenus..."); conn =
			 * DriverManager.getConnection(DB_EBENUS_URL, USER, PASS);
			 */
			statement = conn.prepareStatement("SELECT * FROM utilisateur WHERE idUtilisateur = " + idUtilisateur);

			ResultSet rs = statement.executeQuery();

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
				int idRole = rs.getInt("idRole");
				Role role = roleDao.findRoleById(idRole);

				user = new Utilisateur(idUser, gender, firstName, name, mail, password, birthDate, createDate,
						updateDate, activityState, markAsErased, version, role);

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
		List<Utilisateur> users = new ArrayList<Utilisateur>();

		try {

			/*
			 * TimeZone timezone = TimeZone.getTimeZone("Europe/Paris");
			 * TimeZone.setDefault(timezone);
			 * 
			 * // STEP 2: Register JDBC driver Class.forName("com.mysql.jdbc.Driver");
			 * 
			 * // STEP 3: Open a connection System.out.println("Connecting to database...");
			 * conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 * 
			 * // STEP 4: Connect to specific DB
			 * System.out.println("Connecting to DB base_quest_ebenus..."); conn =
			 * DriverManager.getConnection(DB_EBENUS_URL, USER, PASS);
			 */
			statement = conn.prepareStatement("SELECT * FROM utilisateur WHERE prenom = '" + prenom + "'");

			ResultSet rs = statement.executeQuery();

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
				int idRole = rs.getInt("idRole");
				Role role = roleDao.findRoleById(idRole);

				Utilisateur user = new Utilisateur(idUser, gender, firstName, name, mail, password, birthDate,
						createDate, updateDate, activityState, markAsErased, version, role);

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
		List<Utilisateur> users = new ArrayList<Utilisateur>();

		try {

			/*
			 * TimeZone timezone = TimeZone.getTimeZone("Europe/Paris");
			 * TimeZone.setDefault(timezone);
			 * 
			 * // STEP 2: Register JDBC driver Class.forName("com.mysql.jdbc.Driver");
			 * 
			 * // STEP 3: Open a connection System.out.println("Connecting to database...");
			 * conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 * 
			 * // STEP 4: Connect to specific DB
			 * System.out.println("Connecting to DB base_quest_ebenus..."); conn =
			 * DriverManager.getConnection(DB_EBENUS_URL, USER, PASS);
			 */
			statement = conn.prepareStatement("SELECT * FROM utilisateur WHERE nom = '" + nom + "'");

			ResultSet rs = statement.executeQuery();

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
				int idRole = rs.getInt("idRole");
				Role role = roleDao.findRoleById(idRole);

				Utilisateur user = new Utilisateur(idUser, gender, firstName, name, mail, password, birthDate,
						createDate, updateDate, activityState, markAsErased, version, role);

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
		List<Utilisateur> users = new ArrayList<Utilisateur>();

		try {

			/*
			 * TimeZone timezone = TimeZone.getTimeZone("Europe/Paris");
			 * TimeZone.setDefault(timezone);
			 * 
			 * // STEP 2: Register JDBC driver Class.forName("com.mysql.jdbc.Driver");
			 * 
			 * // STEP 3: Open a connection System.out.println("Connecting to database...");
			 * conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 * 
			 * // STEP 4: Connect to specific DB
			 * System.out.println("Connecting to DB base_quest_ebenus..."); conn =
			 * DriverManager.getConnection(DB_EBENUS_URL, USER, PASS);
			 */
			statement = conn.prepareStatement("SELECT * FROM utilisateur WHERE identifiant = '" + identifiant + "'");

			ResultSet rs = statement.executeQuery();

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
				int idRole = rs.getInt("idRole");
				Role role = roleDao.findRoleById(idRole);

				Utilisateur user = new Utilisateur(idUser, gender, firstName, name, mail, password, birthDate,
						createDate, updateDate, activityState, markAsErased, version, role);

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
		ResultSet result = null;

		List<Utilisateur> users = new ArrayList<Utilisateur>();

		try {

			/*
			 * TimeZone timezone = TimeZone.getTimeZone("Europe/Paris");
			 * TimeZone.setDefault(timezone);
			 * 
			 * // STEP 2: Register JDBC driver Class.forName("com.mysql.jdbc.Driver");
			 * 
			 * // STEP 3: Open a connection System.out.println("Connecting to database...");
			 * 
			 * // STEP 4: Connect to specific DB
			 * System.out.println("Connecting to DB base_quest_ebenus..."); conn =
			 * DriverManager.getConnection(DB_EBENUS_URL, USER, PASS);
			 */

			statement = conn.prepareStatement(
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
		List<Utilisateur> users = new ArrayList<>();

		try {

			statement = conn.prepareStatement(
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

		try {
			int isErased = user.isMarquerEffacer() ? 1 : 0;
			int isActif = user.isActif() ? 1 : 0;

			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			statement = conn.prepareStatement(
					"INSERT INTO `utilisateur` (`idRole`, `civilite`, `prenom`, `nom`, `identifiant`, `motPasse`, `dateNaissance`, `dateCreation`, `dateModification`, `actif`, `marquerEffacer`, `version`) VALUES ('1', '"
							+ user.getCivilite() + "', '" + user.getPrenom() + "', '" + user.getNom() + "', '"
							+ user.getIdentifiant() + "', '" + user.getMotPasse()
							+ "', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, " + isActif + ", " + isErased
							+ ", '" + user.getVersion() + "');");

			int rs = statement.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public Utilisateur updateUtilisateur(Utilisateur user) {
		String requete = "UPDATE Utilisateur SET idRole = " + user.getRole().getIdRole() + ", civilite = '"
				+ user.getCivilite() + "', prenom = '" + user.getPrenom() + "', " + "nom = '" + user.getNom()
				+ "', identifiant = '" + user.getIdentifiant() + "', motPasse = '" + user.getMotPasse() + "', actif = "
				+ parseBooleanToInteger(user.isActif()) + ", marquerEffacer = "
				+ parseBooleanToInteger(user.isMarquerEffacer()) + ", version = " + user.getVersion()
				+ " WHERE idUtilisateur = " + user.getIdUtilisateur() + ";";
		try {
			statement = conn.prepareStatement(requete);

			if (statement.executeUpdate() > 0) {
				return findUtilisateurByIdentifiant(user.getIdentifiant()).get(0);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Integer parseBooleanToInteger(Boolean isActif) {
		return (isActif) ? 1 : 0;
	}

	@Override
	public boolean deleteUtilisateur(Utilisateur user) {

		try {
			/*
			 * // STEP 2: Register JDBC driver Class.forName("com.mysql.jdbc.Driver");
			 * 
			 * // STEP 3: Open a connection System.out.println("Connecting to database...");
			 * conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 * 
			 * // STEP 4: Connect to specific DB
			 * System.out.println("Connecting to DB base_quest_ebenus..."); conn =
			 * DriverManager.getConnection(DB_EBENUS_URL, USER, PASS);
			 */
			statement = conn.prepareStatement(
					"DELETE FROM `utilisateur` WHERE `idUtilisateur`=" + user.getIdUtilisateur() + "");

			if (statement.executeUpdate() > 0) {
				return true;
			}
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
