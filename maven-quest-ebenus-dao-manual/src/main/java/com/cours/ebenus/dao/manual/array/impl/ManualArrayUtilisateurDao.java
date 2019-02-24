/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.manual.array.impl;

import com.cours.ebenus.dao.DataSource;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author ElHadji
 */
public class ManualArrayUtilisateurDao /*extends AbstractArrayDao<Utilisateur>*/ implements IUtilisateurDao {

    private static final Log log = LogFactory.getLog(ManualArrayUtilisateurDao.class);
    private Utilisateur[] utilisateursArrayDataSource = null;
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

    //public ManualArrayUtilisateurDao() {
    //   super(Utilisateur.class, DataSource.getInstance().getUtilisateursArrayDataSource());
    //}

    /**
     * Méthode qui retourne la liste de tous les utilisateurs de la database
     * (ici utilisateursArrayDataSource)
     *
     * @return La liste de tous les utilisateurs de la database
     */
    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        utilisateursArrayDataSource = dataSource.getUtilisateursArrayDataSource();
        return Arrays.asList(utilisateursArrayDataSource);
    }

    /**
     * Méthode qui retourne l'utilisateur d'id passé en paramètre de la database
     * (ici utilisateursArrayDataSource)
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
     * (ici utilisateursArrayDataSource) dont le prénom est égal au paramètre
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
     * (ici utilisateursArrayDataSource) dont le nom est égal au paramètre passé
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
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursArrayDataSource) dont le rôle a comme id celui passé en
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
     * (ici utilisateursArrayDataSource) dont le rôle a comme identifiant celui
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

    /**
     * Méthode qui permet d'ajouter un utilisateur dans la database (ici
     * utilisateursArrayDataSource)
     *
     * @param user L'utilisateur à ajouter
     * @return L'utilisateur ajouté ou null si échec
     */
    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
        utilisateursArrayDataSource = dataSource.getUtilisateursArrayDataSource();
        int maxId = utilisateursArrayDataSource.length;
        for (Utilisateur utilisateur :  utilisateursArrayDataSource) {
            if (utilisateur.getIdUtilisateur() >= maxId){
                maxId = utilisateur.getIdUtilisateur() + 1;
            }
        }
        user.setIdUtilisateur(maxId);
        Utilisateur[] userTab = new Utilisateur[]{user};
        System.arraycopy(utilisateursArrayDataSource, 0, userTab, 0,utilisateursArrayDataSource.length);
        return user;
    }

    /**
     * Méthode qui permet d'update un utilisateur existant dans la database (ici
     * utilisateursArrayDataSource)
     *
     * @param user L'utilisateur à modifier
     * @return L'utilisateur modifié ou null si ce dernier n'existe pas dans la
     * database
     */
    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) {
        boolean isFound = false;
        utilisateursArrayDataSource = dataSource.getUtilisateursArrayDataSource();
        Utilisateur[] userTab = utilisateursArrayDataSource;
        int cursor = 0;
        Utilisateur current = null;
        while (!isFound && cursor < utilisateursArrayDataSource.length){
            current = utilisateursArrayDataSource[cursor];
            isFound = current.equals(user);
            if(isFound){
                userTab[cursor] = user;
            }
        }
        System.arraycopy(utilisateursArrayDataSource, 0, userTab, 0, 0);
        return current;
    }

    /**
     * Méthode qui permet de supprimer un utilisateur existant dans la database
     * (ici utilisateursArrayDataSource)
     *
     * @param user L'utilisateur à supprimer
     * @return True si suppression effectuée, false sinon
     */
    @Override
    public boolean deleteUtilisateur(Utilisateur user) {
        boolean isFound = false;
        utilisateursArrayDataSource = dataSource.getUtilisateursArrayDataSource();
        Utilisateur[] userTab = utilisateursArrayDataSource;
        int cursor = 0;
        Utilisateur current = null;
        while (!isFound && cursor < utilisateursArrayDataSource.length){
            current = utilisateursArrayDataSource[cursor];
            isFound = current.equals(user);
            if(isFound){
                userTab[cursor] = null;
            }
        }
        System.arraycopy(utilisateursArrayDataSource, 0, userTab, 0, 0);
        return isFound;
    }

    private Utilisateur getUserByCriteria(String criteria, Object value) {
        utilisateursArrayDataSource = dataSource.getUtilisateursArrayDataSource();
        Utilisateur userToFound = null;
        boolean isFound = false;
        ListIterator<Utilisateur> it = Arrays.asList(utilisateursArrayDataSource).listIterator();
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
        Role[] roles = dataSource.getRolesArrayDataSource();
        boolean isFound = false;
        if(value instanceof String){
            int cursor = 0;
            while (!isFound && cursor < roles.length){
                isFound = roles[cursor].getIdentifiant().equals(value);
                if (isFound){
                    role = roles[cursor];
                }
                cursor++;
            }
        }else if(value instanceof Integer){
            int cursor = 0;
            while (!isFound && cursor < roles.length){
                isFound = roles[cursor].getIdRole().equals(value);
                if (isFound){
                    role = roles[cursor];
                }
                cursor++;
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
        utilisateursArrayDataSource = dataSource.getUtilisateursArrayDataSource();
        List<Utilisateur> usersToFound = new ArrayList<>();
        ListIterator<Utilisateur> it = Arrays.asList(utilisateursArrayDataSource).listIterator();
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
