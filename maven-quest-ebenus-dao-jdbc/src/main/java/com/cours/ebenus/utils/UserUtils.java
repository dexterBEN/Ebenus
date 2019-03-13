package com.cours.ebenus.utils;

public class UserUtils {


    public static final String getAllUserQuery = "SELECT * FROM Utilisateur";
    public static final String getUserByIDQuery = "SELECT * FROM Utilisateur WHERE idUtilisateur = ";
    public static final String getUserByFirstNameQuery = "SELECT * FROM Utilisateur WHERE prenom = '";
    public static final String getUserByNameQuery = "SELECT * FROM Utilisateur WHERE nom = '";
    public static final String getUserIdentifaintQuery = "SELECT * FROM Utilisateur WHERE identifiant = '";
    public static final String getUserByIdRoleQuery = "SELECT * FROM Utilisateur WHERE idRole = ";
    public static final String createUserQuery = "INSERT INTO `Utilisateur`(`idRole`, `civilite`, `prenom`, `nom`, `identifiant`, `motPasse`, `actif`, `marquerEffacer`, `version`) VALUES (";
    public static final String updateUserQuery = "UPDATE Utilisateur SET ";
    public static final String deleteUserQuery = "DELETE FROM `Utilisateur` WHERE idUtilisateur =";

    public enum UserLib {

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
        IS_DELETED("marquerEffacer"),
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
}