/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.manual.map.impl;

import com.cours.ebenus.dao.DataSource;
import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.entities.Role;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import com.cours.ebenus.dao.manual.array.impl.ManualArrayRoleDao;
import com.cours.ebenus.dao.manual.list.impl.ManualListRoleDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ManualMapRoleDao /*extends AbstractMapDao<Role>*/ implements IRoleDao {

    private static final Log log = LogFactory.getLog(ManualMapRoleDao.class);
    private Map<Integer, Role> rolesListDataSource = null;
    private DataSource dataSource = DataSource.getInstance();
    private static final String GETID = "getIdRole";
    private static final String GETIDENTIFICATION = "getIdentifiant";
    private static final String GETDESCRIPTION = "getDescription";

    //public ManualMapRoleDao() {
    //    super(Role.class, DataSource.getInstance().getRolesMapDataSource());
    //}
    /**
     * Méthode qui retourne la liste de tous les rôles de la database (ici
     * rolesListDataSource)
     *
     * @return La liste de tous les rôles de la database
     */
    @Override
    public List<Role> findAllRoles() {
        rolesListDataSource = dataSource.getRolesMapDataSource();
        return (List<Role>) rolesListDataSource.values();
    }

    /**
     * Méthode qui retourne le rôle d'id passé en paramètre de la database (ici
     * rolesListDataSource)
     *
     * @param idRole L'id du rôle à récupérer
     * @return Le rôle d'id passé en paramètre, null si non trouvé
     */
    @Override
    public Role findRoleById(int idRole) {
        return getRoleByCriteria(getCriteria.ID.field, idRole);
    }

    /**
     * Méthode qui retourne une liste de tous les rôles de la database (ici
     * rolesListDataSource) dont l'identifiant est égal au paramètre passé
     *
     * @param identifiantRole L'identifiant des rôles à récupérer
     * @return Une liste de tous les rôles dont l'identifiant est égal au
     * paramètre passé
     */
    @Override
    public List<Role> findRoleByIdentifiant(String identifiantRole) {
        return getRolesByCriteria(getCriteria.IDENTIFIANT.field, identifiantRole);
    }

    /**
     * Méthode qui permet d'ajouter à rôle dans la database (ici
     * rolesListDataSource)
     *
     * @param role Le rôle à ajouter
     * @return Le rôle ajouté ou null si échec
     */
    @Override
    public Role createRole(Role role) {
        rolesListDataSource = dataSource.getRolesMapDataSource();
        int maxId = rolesListDataSource.size();
        for (Role rol : rolesListDataSource.values()) {
            if (rol.getIdRole() >= maxId) {
                maxId = rol.getIdRole() + 1;
            }
        }
        role.setIdRole(maxId);
        dataSource.getRolesMapDataSource().put(maxId,role);
        return role;
    }

    /**
     * Méthode qui permet d'update un rôle existant dans la database (ici
     * rolesListDataSource)
     *
     * @param role Le rôle à modifier
     * @return Le rôle modifié ou null si ce dernier n'existe pas dans la
     * database
     */
    @Override
    public Role updateRole(Role role) {
        boolean isFound = false;
        rolesListDataSource = dataSource.getRolesMapDataSource();
        int cursor = 0;
        Role current = null;
        while (!isFound && cursor < rolesListDataSource.size()) {
            current = rolesListDataSource.get(cursor);
            isFound = current.equals(role);
            if (isFound) {
                dataSource.getRolesMapDataSource().remove(cursor);
                dataSource.getRolesMapDataSource().put(cursor, role);
            }
            cursor++;
        }
        return role;
    }

    /**
     * Méthode qui permet de supprimer un rôle existant dans la database (ici
     * rolesListDataSource)
     *
     * @param role Le rôle à supprimer
     * @return True si suppression effectuée, false sinon
     */
    @Override
    public boolean deleteRole(Role role) {
        boolean isFound = false;
        rolesListDataSource = dataSource.getRolesMapDataSource();
        int cursor = 0;
        Role current = null;
        while (!isFound && cursor < rolesListDataSource.size()) {
            current = rolesListDataSource.get(cursor);
            isFound = current.equals(role);
            if (isFound) {
                dataSource.getRolesMapDataSource().remove(current);
            }
            cursor++;
        }
        return isFound;
    }

    private enum getCriteria {
        ID("id"),
        IDENTIFIANT("identifiant"),
        DESCRIPTION("description");

        String field;


        getCriteria(String field) {
            this.field = field;
        }

        public String getField() {
            return field;
        }
    }

    private Method getMethodToInvoke(String field) {

        Method method = null;
        if (field.equals(getCriteria.ID.field)) {
            try {
                method = Role.class.getMethod(GETID);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } else if (field.equals(getCriteria.DESCRIPTION.field)) {
            try {
                method = Role.class.getMethod(GETDESCRIPTION);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } else if (field.equals(getCriteria.IDENTIFIANT.field)) {
            try {
                method = Role.class.getMethod(GETIDENTIFICATION);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return method;
    }

    private Role getRoleByCriteria(String criteria, Object value) {
        rolesListDataSource = dataSource.getRolesMapDataSource();
        Role roleToFound = null;
        boolean isFound = false;
        int cursor = 0;
        while (!isFound && cursor < rolesListDataSource.size()) {
            Role current = rolesListDataSource.get(cursor);
            isFound = isget(criteria, value, current);
            if (isFound) {
                roleToFound = current;
            }
            cursor++;
        }
        return roleToFound;
    }

    private boolean isget(String criteria, Object value, Role current) {
        boolean isFound = false;
        if (value instanceof String) {
            Method method = getMethodToInvoke(criteria);
            try {
                isFound = method.invoke(current.getClass()).equals(criteria);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else if (value instanceof Integer) {
            Method method = getMethodToInvoke(criteria);
            try {
                isFound = method.invoke(current.getClass()) == value;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return isFound;
    }

    private List<Role> getRolesByCriteria(String criteria, Object value) {
        rolesListDataSource = dataSource.getRolesMapDataSource();
        List<Role> rolesToFound = new ArrayList<>();
        int cursor = 0;
        boolean isFound = false;
        while (cursor < rolesListDataSource.size()) {
            Role current = rolesListDataSource.get(cursor);
            isFound = isget(criteria, value, current);
            if (isFound) {
                rolesToFound.add(current);
                isFound = false;
            }
            cursor++;
        }
        return rolesToFound;
    }

}
