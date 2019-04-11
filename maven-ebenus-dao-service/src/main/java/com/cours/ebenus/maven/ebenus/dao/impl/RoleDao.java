package com.cours.ebenus.maven.ebenus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cours.ebenus.maven.ebenus.dao.IRoleDao.IRoleDao;
import com.cours.ebenus.maven.ebenus.dao.connection.ConnectionHelper;
import com.cours.ebenus.maven.ebenus.dao.connection.DriverManagerSingleton;
import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.utils.Constants;
import com.cours.ebenus.maven.ebenus.utils.RoleUtils;

public class RoleDao /* extends AbstractDao<Role> */ implements IRoleDao {

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
		// TODO Auto-generated method stub
		return getRoleByCriteria(null);
	}

	@Override
	public Role findRoleById(int idRole) {
		// TODO Auto-generated method stub

		return (!getRoleByCriteria(idRole).isEmpty() ? getRoleByCriteria(idRole).get(0) : null);
	}

	@Override
	public List<Role> findRoleByIdentifiant(String identifiantRole) {
		// TODO Auto-generated method stub
		return getRoleByCriteria(identifiantRole);
	}

	@Override
	public Role createRole(Role role) {
		if (role != null) {
            try {
                statement = conn.prepareStatement(RoleUtils.createRoleQuery + Constants.qote + role.getIdentifiant()
                                + Constants.comaString + role.getDescription() + Constants.qote + Constants.coma + role.getVersion() + ")",
                        statement.RETURN_GENERATED_KEYS);

                statement.executeUpdate();
                result = statement.getGeneratedKeys();
                if (result.next()) {
                    int id = result.getInt(1);
                    role.setIdRole(id);
                }

            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ConnectionHelper.closeSqlResources(statement, result);
            }
        }

        return role;
	}

	@Override
	public Role updateRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteRole(Role role) {
		// TODO Auto-generated method stub
		 try {

	            statement = conn.prepareStatement(RoleUtils.deleteRoleQuery + role.getIdRole());
	            int rs = statement.executeUpdate();
	            return (rs >= 1);

	        } catch (SQLException se) {
	            se.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            ConnectionHelper.closeSqlResources(statement, result);
	        }
	        return false;
	}
	
	private ArrayList<Role> getRoles(ResultSet rs) {
        ArrayList<Role> roles = new ArrayList<>();
        try {

            int idRole;
            int version;
            String identifiant;
            String description;

            while (rs.next()) {
                idRole = rs.getInt(RoleUtils.RoleLib.ID.getField());
                identifiant = rs.getString(RoleUtils.RoleLib.IDENTIFIANT.getField());
                version = rs.getInt(RoleUtils.RoleLib.VERSION.getField());
                description = rs.getString(RoleUtils.RoleLib.DESCRIPTION.getField());
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

	 private List<Role> getRoleByCriteria(Object value) {
	        List<Role> roles = null;
	        try {
	            if (value != null) {
	                if (value instanceof Integer) {
	                    statement = conn.prepareStatement(RoleUtils.getRoleByIdQuery + value);
	                } else if (value instanceof String) {
	                    statement = conn.prepareStatement(RoleUtils.getRoleByIdentifantQuery + Constants.qote + value + Constants.qote);
	                }
	            } else {
	                statement = conn.prepareStatement(RoleUtils.getAllRoleQuery);
	            }
	            result = statement.executeQuery();
	            if (result != null)
	                roles = new ArrayList<>(getRoles(result));
	        } catch (SQLException se) {
	            // Handle errors for JDBC
	            se.printStackTrace();
	        } catch (Exception e) {
	            // Handle errors for Class.forName
	            e.printStackTrace();
	        } finally {
	            ConnectionHelper.closeSqlResources(statement, result);
	        }

	        return roles;
	    }


}
