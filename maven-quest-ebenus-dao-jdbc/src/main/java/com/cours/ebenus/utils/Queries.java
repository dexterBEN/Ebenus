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

    //////////////////////********************************************Adresse Queries ****************************////////////////////////////////////////////////////////////////////
    public static String getAdresseAllQuery = "SELECT *, Role.identifiant AS roleIdent FROM Adresse LEFT JOIN Utilisateur ON Adresse.idUtilisateur = Utilisateur.idUtilisateur LEFT JOIN Role ON Role.idRole = Utilisateur.idRole";
    public static String getAdresseByIDQuery = "SELECT *, Role.identifiant AS roleIdent FROM Adresse LEFT JOIN Utilisateur ON Adresse.idUtilisateur = Utilisateur.idUtilisateur LEFT JOIN Role ON Role.idRole = Utilisateur.idRole WHERE Adresse.idAdresse = ";
    public static String createAdresseQuery = "INSERT INTO `Adresse`(`idUtilisateur`, `rue`, `codePostal`, `ville`, `pays`, `statut`, `typeAdresse`, `principale`, `version`) VALUES (";
    public static String updateAdresseQuery = "UPDATE `Adresse` SET ";
    public static String deleteAdresseQuery = "DELETE FROM `Adresse` WHERE Adresse.idAdresse = ";
    public static String getGetByIdUtilisateurQuery = "SELECT *, Role.identifiant AS roleIdent FROM Adresse LEFT JOIN Utilisateur ON Adresse.idUtilisateur = Utilisateur.idUtilisateur LEFT JOIN Role ON Role.idRole = Utilisateur.idRole WHERE Adresse.idUtilisateur = ";
    public static String getAdresseByCodePostalQuery = "SELECT *, Role.identifiant AS roleIdent FROM Adresse LEFT JOIN Utilisateur ON Adresse.idUtilisateur = Utilisateur.idUtilisateur LEFT JOIN Role ON Role.idRole = Utilisateur.idRole WHERE Adresse.codePostal LIKE '%";
    public static String getAdresseByVilleQuery = "SELECT *, Role.identifiant AS roleIdent FROM Adresse LEFT JOIN Utilisateur ON Adresse.idUtilisateur = Utilisateur.idUtilisateur LEFT JOIN Role ON Role.idRole = Utilisateur.idRole WHERE Adresse.ville LIKE '%";
    public static String getAdresseByPaysQuery = "SELECT *, Role.identifiant AS roleIdent FROM Adresse LEFT JOIN Utilisateur ON Adresse.idUtilisateur = Utilisateur.idUtilisateur LEFT JOIN Role ON Role.idRole = Utilisateur.idRole WHERE Adresse.pays LIKE '%";
    public static String getAdresseByRueQuery = "SELECT *, Role.identifiant AS roleIdent FROM Adresse LEFT JOIN Utilisateur ON Adresse.idUtilisateur = Utilisateur.idUtilisateur LEFT JOIN Role ON Role.idRole = Utilisateur.idRole WHERE Adresse.rue LIKE '%";

    /////////////////////////////////////////////////////////////////////////////// Adresse queries ////////////////////////////////////////////////////////////////////////////////////

    //////////////////////********************************************Commande Queries ****************************////////////////////////////////////////////////////////////////////
    public static String getAllCommandQuery = "SELECT *, Role.identifiant AS roleIdent FROM Commande LEFT JOIN Utilisateur ON Commande.idUtilisateur = Utilisateur.idUtilisateur LEFT JOIN Adresse ON Commande.idAdresse = Adresse.idAdresse LEFT JOIN Role ON Utilisateur.idRole = Role.idRole ";
    public static String getCommandByIDQuery = "SELECT *, Role.identifiant AS roleIdent FROM Commande LEFT JOIN Utilisateur ON Commande.idUtilisateur = Utilisateur.idUtilisateur LEFT JOIN Adresse ON Commande.idAdresse = Adresse.idAdresse LEFT JOIN Role ON Utilisateur.idRole = Role.idRole  WHERE Commande.idCommande =";
    public static String getByCommmadeDateQuery = "SELECT * FROM Commande WHERE dateCommande = ";
    public static String getByIdAdresseQuery = "SELECT * FROM Commande WHERE idAdresse = ";
    public static String getByIdUserQuery = "SELECT * FROM Commande WHERE idUtilisateur = ";

    public static String getByIdCommandQuery = "SELECT * FROM Commande WHERE idCommande = ";
    public static String createCommandeQuery = "INSERT INTO `Commande`(`totalCommande`, `idUtilisateur`, `idAdresse`, `statut`, `dateCommande`, `dateModification`, `version`) VALUES (";
    public static String updateCommandeQuery = "UPDATE `Commande` SET ";
    public static String deleteQuery = "DELETE FROM `Commande` WHERE idCommande =";

    /////////////////////////////////////////////////////////////////////////////// commande queries ////////////////////////////////////////////////////////////////////////////////////

   //////////////////////********************************************ArticleCommande Queries ****************************////////////////////////////////////////////////////////////////////
   public static String getArticleCommandeAllQuery = "SELECT *, Role.identifiant AS roleIdent FROM ArticleCommande LEFT JOIN Utilisateur ON ArticleCommande.idUtilisateur = Utilisateur.idUtilisateur LEFT JOIN Adresse ON ArticleCommande.idAdresse = Adresse.idAdresse LEFT JOIN Role ON Utilisateur.idRole = Role.idRole LEFT JOIN Commande ON ArticleCommande.idCommande = Commande.idCommande LEFT JOIN Produit ON ArticleCommande.idProduit = Produit.idProduit";
    public static String getArticleCommandeByIDQuery = "SELECT *, Role.identifiant AS roleIdent FROM ArticleCommande LEFT JOIN Utilisateur ON ArticleCommande.idUtilisateur = Utilisateur.idUtilisateur LEFT JOIN Adresse ON ArticleCommande.idAdresse = Adresse.idAdresse LEFT JOIN Role ON Utilisateur.idRole = Role.idRole LEFT JOIN Commande ON ArticleCommande.idCommande = Commande.idCommande LEFT JOIN Produit ON ArticleCommande.idProduit = Produit.idProduit WHERE ArticleCommande.idArticleCommande =";
    public static String getArticleCommandeByIdUtilisateurQuery = "SELECT *, Role.identifiant AS roleIdent FROM ArticleCommande LEFT JOIN Utilisateur ON ArticleCommande.idUtilisateur = Utilisateur.idUtilisateur LEFT JOIN Adresse ON ArticleCommande.idAdresse = Adresse.idAdresse LEFT JOIN Role ON Utilisateur.idRole = Role.idRole LEFT JOIN Commande ON ArticleCommande.idCommande = Commande.idCommande LEFT JOIN Produit ON ArticleCommande.idProduit = Produit.idProduit WHERE ArticleCommande.idUtilisateur =";
    public static String getArticleCommandeByIdAdresseQuery = "SELECT *, Role.identifiant AS roleIdent FROM ArticleCommande LEFT JOIN Utilisateur ON ArticleCommande.idUtilisateur = Utilisateur.idUtilisateur LEFT JOIN Adresse ON ArticleCommande.idAdresse = Adresse.idAdresse LEFT JOIN Role ON Utilisateur.idRole = Role.idRole LEFT JOIN Commande ON ArticleCommande.idCommande = Commande.idCommande LEFT JOIN Produit ON ArticleCommande.idProduit = Produit.idProduit WHERE ArticleCommande.idAdresse =";
    public static String getArticleCommandeByIdCommandeQuery = "SELECT *, Role.identifiant AS roleIdent FROM ArticleCommande LEFT JOIN Utilisateur ON ArticleCommande.idUtilisateur = Utilisateur.idUtilisateur LEFT JOIN Adresse ON ArticleCommande.idAdresse = Adresse.idAdresse LEFT JOIN Role ON Utilisateur.idRole = Role.idRole LEFT JOIN Commande ON ArticleCommande.idCommande = Commande.idCommande LEFT JOIN Produit ON ArticleCommande.idProduit = Produit.idProduit WHERE ArticleCommande.idCommande =";
    public static String getArticleCommandeByIdProduitQuery = "SELECT *, Role.identifiant AS roleIdent FROM ArticleCommande LEFT JOIN Utilisateur ON ArticleCommande.idUtilisateur = Utilisateur.idUtilisateur LEFT JOIN Adresse ON ArticleCommande.idAdresse = Adresse.idAdresse LEFT JOIN Role ON Utilisateur.idRole = Role.idRole LEFT JOIN Commande ON ArticleCommande.idCommande = Commande.idCommande LEFT JOIN Produit ON ArticleCommande.idProduit = Produit.idProduit WHERE ArticleCommande.idProduit =";
    public static String getArticleCommandeByReferenceQuery = "SELECT *, Role.identifiant AS roleIdent FROM ArticleCommande LEFT JOIN Utilisateur ON ArticleCommande.idUtilisateur = Utilisateur.idUtilisateur LEFT JOIN Adresse ON ArticleCommande.idAdresse = Adresse.idAdresse LEFT JOIN Role ON Utilisateur.idRole = Role.idRole LEFT JOIN Commande ON ArticleCommande.idCommande = Commande.idCommande LEFT JOIN Produit ON ArticleCommande.idProduit = Produit.idProduit WHERE ArticleCommande.reference = '";
    public static String createArticleCommandeQuery = "INSERT INTO `ArticleCommande`(`idArticleCommande`, `idCommande`, `idUtilisateur`, `idAdresse`, `idProduit`, `totalArticleCommande`, `reference`, `quantite`, `statut`, `dateModification`, `version`) VALUES (";
    public static String updateArticleCommandeQuery = "UPDATE `ArticleCommande` SET";
    public static String deleteArticleCommandeQuery = "DELETE FROM `ArticleCommande` WHERE ArticleCommande.idArticleCommande = ";
//    public static String getGetByIdRoleJoinQuery = "SELECT *, Role.idRole AS roleId, Role.identifiant AS roleIdent, Role.description AS roleDescrpt, Role.version AS roleVers FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Utilisateur.idRole =";
//    public static String getGetByIdentifiantRoleJoinQuery = "SELECT *, Role.idRole AS roleId, Role.identifiant AS roleIdent, Role.description AS roleDescrpt, Role.version AS roleVers FROM Utilisateur LEFT JOIN Role On Utilisateur.idRole = Role.idRole WHERE Role.identifiant =";
//    /////////////////////////////////////////////////////////////////////////////// ArticleCommande queries ////////////////////////////////////////////////////////////////////////////////////
}
