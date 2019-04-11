package com.cours.ebenus.maven.ebenus.utils;

public class UserUtils {
	
	public static final String getAllUserQuery = "SELECT *, Role.idRole AS roleId, Role.identifiant AS roleIdent, Role.description AS roleDescrpt, Role.version AS roleVers FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole";

    public static final String getUserByIDQuery = "SELECT *,Role.identifiant AS roleIdent FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Utilisateur.idUtilisateur =";
    public static final String getUserByFirstNameQuery = "SELECT *,Role.identifiant AS roleIdent FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Utilisateur.prenom = '";
    public static final String getUserByNameQuery = "SELECT *,Role.identifiant AS roleIdent FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Utilisateur.nom = '";
    public static final String getUserIdentifaintQuery = "SELECT *, Role.identifiant AS roleIdentifiant FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Utilisateur.identifiant = '";

    public static final String getUserByIdRoleQuery = "SELECT * FROM Utilisateur WHERE idRole = ";
    public static final String createUserQuery = "INSERT INTO `Utilisateur`(`idRole`, `civilite`, `prenom`, `nom`, `identifiant`, `motPasse`, `actif`, `marquerEffacer`, `version`) VALUES (";
    public static final String updateUserQuery = "UPDATE Utilisateur SET ";
    public static final String deleteUserQuery = "DELETE FROM `Utilisateur` WHERE idUtilisateur =";
    public static final String getGetUserByIdRoleJoinQuery = "SELECT *, Role.idRole AS roleId, Role.identifiant AS roleIdent, Role.description AS roleDescrpt, Role.version AS roleVers FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Utilisateur.idRole =";
    public static final String getGetUserByIdentifiantRoleJoinQuery = "SELECT *, Role.idRole AS roleId, Role.identifiant AS roleIdent, Role.description AS roleDescrpt, Role.version AS roleVers FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Role.identifiant =";

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
