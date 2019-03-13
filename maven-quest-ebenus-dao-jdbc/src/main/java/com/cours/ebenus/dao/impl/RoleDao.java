/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.utils.RoleUtils;
import com.mysql.jdbc.Statement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.cours.ebenus.utils.Constants.*;
import static com.cours.ebenus.utils.RoleUtils.*;

/**
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
        return getRoleByCriteria(null);
    }

    @Override
    public Role findRoleById(int idRole) {
        return getRoleByCriteria(idRole).get(0);
    }

    @Override
    public List<Role> findRoleByIdentifiant(String identifiantRole) {
        return getRoleByCriteria(identifiantRole);
    }

    @Override
    public Role createRole(Role role) {

        if (role != null) {
            try {
                statement = conn.prepareStatement(createRoleQuery + qote + role.getIdentifiant()
                                + comaString + role.getDescription() + qote + coma + role.getVersion() + ")",
                        Statement.RETURN_GENERATED_KEYS);

                statement.executeUpdate();
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    role.setIdRole(id);
                }

            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return role;
    }

    @Override
    public Role updateRole(Role role) {
        try {

            statement = conn.prepareStatement(updateRoleQuery + qote + role.getIdentifiant() + qote
                    + coma + qote + RoleLib.DESCRIPTION + qote + equal + qote + role.getDescription() + qote + " WHERE "
                    + RoleLib.ID.getField() + equal + role.getIdRole());

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

            statement = conn.prepareStatement(deleteRoleQuery + role.getIdRole());
            int rs = statement.executeUpdate();
            return (rs >= 1);

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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
                    statement = conn.prepareStatement(getRoleByIdQuery + value);
                } else if (value instanceof String) {
                    statement = conn.prepareStatement(getRoleByIdentifantQuery + qote + value + qote);
                }
            } else {
                statement = conn.prepareStatement(getAllRoleQuery);
            }
            ResultSet rs = statement.executeQuery();
            if (rs != null)
                roles = new ArrayList<>(getRoles(rs));
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }

        return roles;
    }


}
