/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.manual.list.impl;

import com.cours.ebenus.dao.DataSource;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import com.cours.ebenus.dao.manual.array.impl.ManualArrayUtilisateurDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ManualListUtilisateurDao /*extends AbstractListDao<Utilisateur>*/ implements IUtilisateurDao {

    private static final Log log = LogFactory.getLog(ManualListUtilisateurDao.class);
    private List<Utilisateur> utilisateursListDataSource = null;

    private DataSource dataSource = DataSource.getInstance();
    private static final String GETID = "getIdUtilisateur";
    private static final String GETCIVILITE = "getCivilite";
    private static final String GETPRENOM = "getPrenom";
    private static final String GETNOM = "getNom";
    private static final String GETIDENTIFIANT = "getIdentifiant";
    private static final String GETMOTPASSE = "getMotPasse";
    private static final String GETDATENAISSANCE = "getDateNaissance";
    private static final String GETDATECREATION = "getDateCreation";
    private static final String GETDATEMODIFICATION = "getDateModification";
    private static final  String GETROLE = "getRole";

    //public ManualListUtilisateurDao() {
    //    super(Utilisateur.class, DataSource.getInstance().getUtilisateursListDataSource());
    //}
    /**
     * Méthode qui retourne la liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource)
     *
     * @return La liste de tous les utilisateurs de la database
     */
    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        return dataSource.getUtilisateursListDataSource();
    }

    /**
     * Méthode qui retourne l'utilisateur d'id passé en paramètre de la database
     * (ici utilisateursListDataSource)
     *
     * @param idUtilisateur L'id de l'user à récupérer
     * @return L'utilisateur d'id passé en paramètre, null si non trouvé
     */
    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur) {
        return getUserByCriteria(getCriteria.ID.field, idUtilisateur);
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont le prénom est égal au paramètre
     * passé
     *
     * @param prenom Le prénom des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le prénom est égal au
     * paramètre passé
     */
    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
        return getUsersByCriteria(getCriteria.PRENOM.field, prenom);
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont le nom est égal au paramètre passé
     *
     * @param nom Le nom des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le nom est égal au
     * paramètre passé
     */
    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom) {
        return getUsersByCriteria(getCriteria.NOM.field, nom);
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont l'identifiant est égal au paramètre
     * passé
     *
     * @param identifiant Le nom des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont l'identifiant est égal au
     * paramètre passé
     */
    @Override
    public List<Utilisateur> findUtilisateurByIdentifiant(String identifiant) {
        return getUsersByCriteria(getCriteria.IDENTIFIANT.field, identifiant);
    }

    /**
     * Méthode qui permet d'ajouter un utilisateur dans la database (ici
     * utilisateursListDataSource)
     *
     * @param user L'utilisateur à ajouter
     * @return L'utilisateur ajouté ou null si échec
     */
    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
        utilisateursListDataSource = dataSource.getUtilisateursListDataSource();
        int maxId = utilisateursListDataSource.size();
        for (Utilisateur utilisateur :  utilisateursListDataSource) {
            if (utilisateur.getIdUtilisateur() >= maxId){
                maxId = utilisateur.getIdUtilisateur() + 1;
            }
        }
        user.setIdUtilisateur(maxId);
        List<Utilisateur> userTab = new ArrayList<>();
        userTab.add(user);
        Collections.copy(utilisateursListDataSource, userTab);
        return user;
    }

    /**
     * Méthode qui permet d'update un utilisateur existant dans la database (ici
     * utilisateursListDataSource)
     *
     * @param user L'utilisateur à modifier
     * @return L'utilisateur modifié ou null si ce dernier n'existe pas dans la
     * database
     */
    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) {
        boolean isFound = false;
        utilisateursListDataSource = dataSource.getUtilisateursListDataSource();
        List<Utilisateur> userTab = utilisateursListDataSource;
        Utilisateur current = null;
        ListIterator<Utilisateur> it = userTab.listIterator();
        while (!isFound && it.hasNext()){
            current = it.next();
            isFound = current.equals(user);
            if(isFound){
                userTab.remove(user);
            }
        }
        Collections.copy(utilisateursListDataSource, userTab);
        return current;
    }

    /**
     * Méthode qui permet de supprimer un utilisateur existant dans la database
     * (ici utilisateursListDataSource)
     *
     * @param user L'utilisateur à supprimer
     * @return True si suppression effectuée, false sinon
     */
    @Override
    public boolean deleteUtilisateur(Utilisateur user) {
        boolean isFound = false;
        utilisateursListDataSource = dataSource.getUtilisateursListDataSource();
        List<Utilisateur> userTab = utilisateursListDataSource;
        Utilisateur current = null;
        ListIterator<Utilisateur> it = userTab.listIterator();
        while (!isFound && it.hasNext()){
            current = it.next();
            isFound = current.equals(user);
            if(isFound){
                userTab.remove(current);
            }
        }
        Collections.copy(utilisateursListDataSource, userTab);
        return isFound;
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont le rôle a comme id celui passé en
     * paramètre
     *
     * @param idRole L'id du rôle des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le rôle a comme id celui
     * passé en paramètre
     */
    @Override
    public List<Utilisateur> findUtilisateursByIdRole(int idRole) {
        Role role = getRoleFromCriteria(idRole);
        if(role != null){
            return getUsersByCriteria(getCriteria.IDROLE.field, role);
        }else {
            return null;
        }
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont le rôle a comme identifiant celui
     * passé en paramètre
     *
     * @param identifiantRole L'identifiant du rôle des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le rôle a comme
     * identifiant celui passé en paramètre
     */
    @Override
    public List<Utilisateur> findUtilisateursByIdentifiantRole(String identifiantRole) {
        Role role = getRoleFromCriteria(identifiantRole);
        if(role != null){
            return getUsersByCriteria(getCriteria.IDROLE.field, role);
        }else {
            return null;
        }
    }

    private Utilisateur getUserByCriteria(String criteria, Object value) {
        utilisateursListDataSource = dataSource.getUtilisateursListDataSource();
        Utilisateur userToFound = null;
        boolean isFound = false;
        ListIterator<Utilisateur> it = utilisateursListDataSource.listIterator();
        while (!isFound && it.hasNext()) {
            Utilisateur currentUser = it.next();
            isFound =isget(criteria, value,currentUser);
            if (isFound) {
                userToFound = currentUser;
            }
        }
        return userToFound;
    }

    private Role getRoleFromCriteria(Object value){
        Role role = null;
        List<Role> roles = dataSource.getRolesListDataSource();
        boolean isFound = false;
        ListIterator<Role> it = roles.listIterator();
        if(value instanceof String){
            while (!isFound && it.hasNext()){
                Role current = it.next();
                isFound = current.getIdentifiant().equals(value);
                if (isFound){
                    role = current;
                }
            }
        }else if(value instanceof Integer){
            while (!isFound && it.hasNext()){
                Role current = it.next();
                isFound = current.getIdRole().equals(value);
                if (isFound){
                    role = current;
                }
            }
        }
        return role;
    }

    private boolean isget(String criteria, Object value, Utilisateur currentuser){
        boolean isFound = false;
        if (value instanceof String) {
            Method method = getMethodToInvoke(criteria);
            try {
                isFound = method.invoke(currentuser.getClass()).equals(criteria);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else if (value instanceof Integer) {
            Method method = getMethodToInvoke(criteria);
            try {
                isFound = method.invoke(currentuser.getClass()) == value;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else if( value instanceof Role){
            Method method = getMethodToInvoke(criteria);
            try {
                isFound = method.invoke(currentuser.getClass()).equals(value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return isFound;
    }

    private List<Utilisateur> getUsersByCriteria(String criteria, Object value) {
        utilisateursListDataSource = dataSource.getUtilisateursListDataSource();
        List<Utilisateur> usersToFound = new ArrayList<>();
        ListIterator<Utilisateur> it = utilisateursListDataSource.listIterator();
        boolean isFound = false;
        while (it.hasNext()) {
            Utilisateur currentUser = it.next();
            isFound =isget(criteria, value, currentUser);
            if (isFound) {
                usersToFound.add(currentUser);
                isFound = false;
            }
        }
        return usersToFound;
    }

    private enum getCriteria {
        ID("id"),
        CIVILTE("civilite"),
        PRENOM("prenom"),
        NOM("nom"),
        IDENTIFIANT("identifiant"),
        MOTPASSE("motPasse"),
        DATENAISSANCE("dateNaissance"),
        DATECREATION("dateCreation"),
        DATEMODIFICATION("dateModification"),
        IDROLE("role");

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
                method = Utilisateur.class.getMethod(GETID);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } else if (field.equals(getCriteria.CIVILTE.field)) {
            try {
                method = Utilisateur.class.getMethod(GETCIVILITE);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } else if (field.equals(getCriteria.PRENOM.field)) {
            try {
                method = Utilisateur.class.getMethod(GETPRENOM);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } else if (field.equals(getCriteria.NOM.field)) {
            try {
                method = Utilisateur.class.getMethod(GETNOM);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } else if (field.equals(getCriteria.IDENTIFIANT.field)) {
            try {
                method = Utilisateur.class.getMethod(GETIDENTIFIANT);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } else if (field.equals(getCriteria.MOTPASSE.field)) {
            try {
                method = Utilisateur.class.getMethod(GETMOTPASSE);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } else if (field.equals(getCriteria.DATECREATION.field)) {
            try {
                method = Utilisateur.class.getMethod(GETDATECREATION);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } else if (field.equals(getCriteria.DATENAISSANCE.field)) {
            try {
                method = Utilisateur.class.getMethod(GETDATENAISSANCE);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } else if (field.equals(getCriteria.DATEMODIFICATION.field)) {
            try {
                method = Utilisateur.class.getMethod(GETDATEMODIFICATION);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }else if(field.equals(getCriteria.IDROLE)){
            try{
                method = Utilisateur.class.getMethod(GETROLE);
            }catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return method;
    }
}
