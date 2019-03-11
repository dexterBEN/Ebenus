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
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.entities.Role;
import com.mysql.jdbc.Statement;

/**
 *
 * @author ElHadji
 */
public class RoleDao /* extends AbstractDao<Role> */ implements IRoleDao {

	private static final Log log = LogFactory.getLog(RoleDao.class);
	Connection conn = null;
	PreparedStatement statement = null;
	ResultSet result = null;

	public RoleDao() {
		// super(Role.class);
		try {
			conn = DriverManagerSingleton.getConnectionInstance().getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Role> findAllRoles() {
		List<Role> roles = new ArrayList<Role>();

		try {

			statement = conn.prepareStatement("SELECT * FROM Role");
			// SQL request for table user
			ResultSet rs = statement.executeQuery();
			int idRole;
			int version;
			String identifiant;
			String description;

			while (rs.next()) {
				idRole = rs.getInt("idRole");
				identifiant = rs.getString("identifiant");
				version = rs.getInt("version");
				description = rs.getString("description");
				Role role = new Role(idRole, identifiant, description, version);

				roles.add(role);
			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}

		return roles;
	}

	@Override
	public Role findRoleById(int idRole) {
		Role role = null;
		try {

			statement = conn.prepareStatement("SELECT * FROM Role WHERE idRole = " + idRole);

			ResultSet rs = statement.executeQuery();
			int version;
			String identifiant;
			String description;

			if (rs.next()) {
				idRole = rs.getInt("idRole");
				identifiant = rs.getString("identifiant");
				version = rs.getInt("version");
				description = rs.getString("description");
				role = new Role(idRole, identifiant, description, version);
			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}

		return role;
	}

	@Override
	public List<Role> findRoleByIdentifiant(String identifiantRole) {
		List<Role> roles = new ArrayList<Role>();

		try {
			statement = conn.prepareStatement("SELECT * FROM Role WHERE identifiant = '" + identifiantRole + "'");
			ResultSet rs = statement.executeQuery();

			int idRole;
			int version;
			String identifiant;
			String description;

			while (rs.next()) {
				idRole = rs.getInt("idRole");
				identifiant = rs.getString("identifiant");
				version = rs.getInt("version");
				description = rs.getString("description");
				Role role = new Role(idRole, identifiant, description, version);

				roles.add(role);
			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}

		return roles;
	}

	@Override
	public Role createRole(Role role) {

		try {

			System.out.print("INSERT INTO `Role` (identifiant, description, version) VALUES ('" + role.getIdentifiant()
					+ "', '" + role.getDescription() + "', " + role.getVersion() + ")");

			statement = conn
					.prepareStatement(
							"INSERT INTO `Role` (identifiant, description, version) VALUES ('" + role.getIdentifiant()
									+ "', '" + role.getDescription() + "', " + role.getVersion() + ")",
							Statement.RETURN_GENERATED_KEYS);

			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				System.out.println("Inserted ID " + id); // display inserted record
				role.setIdRole(id);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return role;
	}

	@Override
	public Role updateRole(Role role) {
		try {

			statement = conn.prepareStatement("UPDATE `Role` SET `identifiant`='" + role.getIdentifiant()
					+ "', `description`='" + role.getDescription() + "' WHERE idRole=" + role.getIdRole());

			statement.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return role;
	}

	@Override
	public boolean deleteRole(Role role) {
		try {

			statement = conn.prepareStatement("DELETE FROM `Role` WHERE `idRole`=" + role.getIdRole());
			int rs = statement.executeUpdate();
			return (rs >= 1);// return boolean of comparaison

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
