package com.cours.ebenus.utils;

public class Queries {


    //////////////////////********************************************Products Queries ****************************////////////////////////////////////////////////////////////////////
    public static String getProductsAllQuery = "SELECT * FROM Produit";
    public static String getByProductsIDQuery = "SELECT * FROM Produit WHERE Produit.idProduit =";
    public static String getByProductReferenceQuery = "SELECT * FROM Produit WHERE Produit.reference = '";
    public static String getByProductPriceQuery = "SELECT * FROM Produit WHERE Produit.prix = ";
    public static String getByProductNameQuery = "SELECT * FROM Produit WHERE Produit.nom = '";
    public static String getByProductDescriptionQuery = "SELECT * FROM Produit WHERE Produit.description LIKE '%";
    public static String createProductQuery = "INSERT INTO `Produit`(`reference`, `prix`, `nom`, `description`, `stock`, `active`, `marquerEffacer`, `version`) VALUES ('";
    public static String updateProductQuery = "UPDATE Produit SET ";

    /////////////////////////////////////////////////////////////////////////////// products queries ////////////////////////////////////////////////////////////////////////////////////
//
//    //////////////////////********************************************Adresse Queries ****************************////////////////////////////////////////////////////////////////////
//    public static String getAdresseAllQuery = "SELECT *, Role.idRole AS roleId, Role.identifiant AS roleIdent, Role.description AS roleDescrpt, Role.version AS roleVers FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole";
//
//    public static String getAdresseByIDQuery = "SELECT * FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Utilisateur.idUtilisateur =";
//    public static String getByFirstNameQuery = "SELECT * FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Utilisateur.prenom = '";
//    public static String getByNameQuery = "SELECT * FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Utilisateur.nom = '";
//    public static String getIdentifaintQuery = "SELECT *, Role.identifiant AS roleIdentifiant FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Utilisateur.identifiant = '";
//
//    public static String getByIdRoleQuery = "SELECT * FROM Utilisateur WHERE idRole = ";
//    public static String createQuery = "INSERT INTO `Utilisateur`(`idRole`, `civilite`, `prenom`, `nom`, `identifiant`, `motPasse`, `actif`, `marquerEffacer`, `version`) VALUES (";
//    public static String updateQuery = "UPDATE Utilisateur SET ";
//    public static String deleteQuery = "DELETE FROM `Utilisateur` WHERE idUtilisateur =";
//    public static String getGetByIdRoleJoinQuery = "SELECT *, Role.idRole AS roleId, Role.identifiant AS roleIdent, Role.description AS roleDescrpt, Role.version AS roleVers FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Utilisateur.idRole =";
//    public static String getGetByIdentifiantRoleJoinQuery = "SELECT *, Role.idRole AS roleId, Role.identifiant AS roleIdent, Role.description AS roleDescrpt, Role.version AS roleVers FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Role.identifiant =";
//    /////////////////////////////////////////////////////////////////////////////// Adresse queries ////////////////////////////////////////////////////////////////////////////////////
//
//    //////////////////////********************************************Commande Queries ****************************////////////////////////////////////////////////////////////////////
    public static String getAllCommandQuery = "SELECT *, Role.identifiant AS roleIdent FROM Commande LEFT JOIN Utilisateur ON Commande.idUtilisateur = Utilisateur.idUtilisateur LEFT JOIN Adresse ON Commande.idAdresse = Adresse.idAdresse LEFT JOIN Role ON Utilisateur.idRole = Role.idRole ";
//todo WIP
    public static String getByIDQuery = "SELECT * FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Utilisateur.idUtilisateur =";
    public static String getByFirstNameQuery = "SELECT * FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Utilisateur.prenom = '";
    public static String getByNameQuery = "SELECT * FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Utilisateur.nom = '";
    public static String getIdentifaintQuery = "SELECT *, Role.identifiant AS roleIdentifiant FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Utilisateur.identifiant = '";

    public static String getByIdRoleQuery = "SELECT * FROM Utilisateur WHERE idRole = ";
    public static String createQuery = "INSERT INTO `Utilisateur`(`idRole`, `civilite`, `prenom`, `nom`, `identifiant`, `motPasse`, `actif`, `marquerEffacer`, `version`) VALUES (";
    public static String updateQuery = "UPDATE Utilisateur SET ";
    public static String deleteQuery = "DELETE FROM `Utilisateur` WHERE idUtilisateur =";
    public static String getGetByIdRoleJoinQuery = "SELECT *, Role.idRole AS roleId, Role.identifiant AS roleIdent, Role.description AS roleDescrpt, Role.version AS roleVers FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Utilisateur.idRole =";
    public static String getGetByIdentifiantRoleJoinQuery = "SELECT *, Role.idRole AS roleId, Role.identifiant AS roleIdent, Role.description AS roleDescrpt, Role.version AS roleVers FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Role.identifiant =";
//    /////////////////////////////////////////////////////////////////////////////// commande queries ////////////////////////////////////////////////////////////////////////////////////
//
//    //////////////////////********************************************ArticleCommande Queries ****************************////////////////////////////////////////////////////////////////////
//    public static String getAllQuery = "SELECT *, Role.idRole AS roleId, Role.identifiant AS roleIdent, Role.description AS roleDescrpt, Role.version AS roleVers FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole";
//
//    public static String getByIDQuery = "SELECT * FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Utilisateur.idUtilisateur =";
//    public static String getByFirstNameQuery = "SELECT * FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Utilisateur.prenom = '";
//    public static String getByNameQuery = "SELECT * FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Utilisateur.nom = '";
//    public static String getIdentifaintQuery = "SELECT *, Role.identifiant AS roleIdentifiant FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Utilisateur.identifiant = '";
//
//    public static String getByIdRoleQuery = "SELECT * FROM Utilisateur WHERE idRole = ";
//    public static String createQuery = "INSERT INTO `Utilisateur`(`idRole`, `civilite`, `prenom`, `nom`, `identifiant`, `motPasse`, `actif`, `marquerEffacer`, `version`) VALUES (";
//    public static String updateQuery = "UPDATE Utilisateur SET ";
//    public static String deleteQuery = "DELETE FROM `Utilisateur` WHERE idUtilisateur =";
//    public static String getGetByIdRoleJoinQuery = "SELECT *, Role.idRole AS roleId, Role.identifiant AS roleIdent, Role.description AS roleDescrpt, Role.version AS roleVers FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Utilisateur.idRole =";
//    public static String getGetByIdentifiantRoleJoinQuery = "SELECT *, Role.idRole AS roleId, Role.identifiant AS roleIdent, Role.description AS roleDescrpt, Role.version AS roleVers FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Role.identifiant =";
//    /////////////////////////////////////////////////////////////////////////////// ArticleCommande queries ////////////////////////////////////////////////////////////////////////////////////
}
