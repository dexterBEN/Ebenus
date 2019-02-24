/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.manual.list.impl;

import com.cours.ebenus.dao.DataSource;
import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.entities.Role;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * @author ElHadji
 */
public class ManualListRoleDao /*extends AbstractListDao<Role>*/ implements IRoleDao {

    private static final Log log = LogFactory.getLog(ManualListRoleDao.class);
    private List<Role> rolesListDataSource = null;
    private DataSource dataSource = DataSource.getInstance();
    private static final String GETID = "getIdRole";
    private static final String GETIDENTIFICATION = "getIdentifiant";
    private static final String GETDESCRIPTION = "getDescription";

    //public ManualListRoleDao() {
    //    super(Role.class, DataSource.getInstance().getRolesListDataSource());
    //}

    /**
     * Méthode qui retourne la liste de tous les rôles de la database (ici
     * rolesListDataSource)
     *
     * @return La liste de tous les rôles de la database
     */
    @Override
    public List<Role> findAllRoles() {
        return dataSource.getRolesListDataSource();
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
        rolesListDataSource = dataSource.getRolesListDataSource();
        int maxId = rolesListDataSource.size();
        for (Role rol : rolesListDataSource) {
            if (rol.getIdRole() >= maxId) {
                maxId = rol.getIdRole() + 1;
            }
        }
        role.setIdRole(maxId);
        List<Role> Tab = new ArrayList<>();
        Tab.add(role);
        Collections.copy(rolesListDataSource, Tab);
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
        rolesListDataSource = dataSource.getRolesListDataSource();
        Role current = null;
        ListIterator<Role> it = rolesListDataSource.listIterator();
        while (!isFound && it.hasNext()) {
            current = it.next();
            isFound = current.equals(role);
            if (isFound) {
                current = role;
            }
        }
        List<Role> tab = new ArrayList<>();
        tab.add(current);
        Collections.copy(rolesListDataSource,tab);
        return current;
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
        rolesListDataSource = dataSource.getRolesListDataSource();
        List<Role> Tab = rolesListDataSource;
        Role current = null;
        ListIterator<Role> it = rolesListDataSource.listIterator();
        while (!isFound && it.hasNext()) {
            current = it.next();
            isFound = current.equals(role);
            if (isFound) {
                Tab.remove(current);
            }
        }
        Collections.copy(rolesListDataSource,Tab);
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
        rolesListDataSource = dataSource.getRolesListDataSource();
        Role roleToFound = null;
        boolean isFound = false;
        ListIterator<Role> it = rolesListDataSource.listIterator();
        while (!isFound && it.hasNext()) {
            Role current = it.next();
            isFound = isget(criteria, value, current);
            if (isFound) {
                roleToFound = current;
            }
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
        rolesListDataSource = dataSource.getRolesListDataSource();
        List<Role> rolesToFound = new ArrayList<>();
        ListIterator<Role> it = rolesListDataSource.listIterator();
        boolean isFound = false;
        while (it.hasNext()) {
            Role current = it.next();
            isFound = isget(criteria, value, current);
            if (isFound) {
                rolesToFound.add(current);
                isFound = false;
            }
        }
        return rolesToFound;
    }
}
