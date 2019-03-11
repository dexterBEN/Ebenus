package com.cours.ebenus.utils;

import com.cours.ebenus.dao.entities.Role;

import java.util.Date;

public class UserUtils {

    public enum UserLib{

        ID("idUtilisateur"),
        CIVILITE("civilite"),
        PRENOM("prenom"),
        NOM("nom"),
        IDENTIFIANT("identifiant"),
        MOT_PASSE("motPasse"),
        DATE_NAISSANCE("dateNaissance"),
        DATE_CREATION("dateCreation"),
        DATE_MODIFICATION("dateModification"),
        IS_ACTIF("actif"),
        IS_DELETED("arquerEffacer"),
        VERSION("version"),
        ID_ROLE("idRole");

        String field;

        UserLib(String filed) {
            this.field = filed;
        }

        public String getField() {
            return field;
        }
    }

    public static final String getAllUserQuery = "SELECT * FROM utilisateur";
    public static final String getUserByIDQuery = "SELECT * FROM utilisateur WHERE idUtilisateur = ";
    public static final String getUserByFirstNameQuery = "SELECT * FROM utilisateur WHERE prenom = '";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";
    public static final String getUserQuery = "";



}
