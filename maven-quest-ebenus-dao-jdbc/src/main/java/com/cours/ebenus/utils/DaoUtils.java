package com.cours.ebenus.utils;

import com.cours.ebenus.dao.entities.Adresse;
import com.cours.ebenus.dao.entities.ArticleCommande;
import com.cours.ebenus.dao.entities.Commande;
import com.cours.ebenus.dao.entities.Product;
import com.mysql.jdbc.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import static com.cours.ebenus.utils.Constants.coma;
import static com.cours.ebenus.utils.Constants.qote;

public class DaoUtils {

    enum Modules {
        PRODUCT("idProduct", "reference", "prix", "nom", "description", "stock", "active", "marquerEffacer", "version"),
        COMMANDE("idCommande", "totalCommande", "idUtilisateur", "idAdresse", "statut", "dateCommande", "dateModification", "version"),
        ARTICLE_COMMANDE("idArticleCommande", "idCommande", "idUtilisateur", "idAdresse", "idProduit", "totalArticleCommande", "reference", "quantite", "statut", "dateModification", "version"),
        ADRESSE("idAdresse", "idUtilisateur", "rue", "codePostal", "ville", "pays", "statut", "typeAdresse", "principale", "version");

        public String idProduit;
        public String idCommande;
        public String idArticleCommande;
        public String idAdresse;
        public String reference;
        public String prix;
        public String nom;
        public String description;
        public String stock;
        public String active;
        public String marquerEffacer;
        public String version;
        public String totalCommande;
        public String idUtilisateur;
        public String statut;
        public String dateCommande;
        public String dateModification;
        public String totalArticleCommande;
        public String quantite;
        public String principale;
        public String typeAdresse;
        public String pays;
        public String ville;
        public String codePostal;
        public String rue;

        Modules(String idProduct, String reference, String prix, String nom, String description, String stock, String active, String marquerEffacer, String version) {
            this.idProduit = idProduct;
            this.reference = reference;
            this.prix = prix;
            this.nom = nom;
            this.description = description;
            this.stock = stock;
            this.active = active;
            this.marquerEffacer = marquerEffacer;
            this.version = version;
        }

        Modules(String idCommande, String totalCommande, String idUtilisateur, String idAdresse, String statut, String dateCommande, String dateModification, String version) {
            this.idCommande = idCommande;
            this.totalCommande = totalCommande;
            this.idUtilisateur = idUtilisateur;
            this.idAdresse = idAdresse;
            this.statut = statut;
            this.dateCommande = dateCommande;
            this.dateModification = dateModification;
            this.version = version;
        }

        Modules(String idArticleCommande, String idCommande, String idUtilisateur, String idAdresse, String idProduit, String totalArticleCommande, String reference, String quantite, String statut, String dateModification, String version) {
            this.idArticleCommande = idArticleCommande;
            this.idCommande = idCommande;
            this.idUtilisateur = idUtilisateur;
            this.idAdresse = idAdresse;
            this.idProduit = idProduit;
            this.totalArticleCommande = totalArticleCommande;
            this.reference = reference;
            this.quantite = quantite;
            this.statut = statut;
            this.dateModification = dateModification;
            this.version = version;
        }

        Modules(String idAdresse, String idUtilisateur, String rue, String codePostal, String ville, String pays, String statut, String typeAdresse, String principale, String version) {
            this.idAdresse = idAdresse;
            this.idUtilisateur = idUtilisateur;
            this.rue = rue;
            this.codePostal = codePostal;
            this.ville = ville;
            this.pays = pays;
            this.statut = statut;
            this.typeAdresse = typeAdresse;
            this.principale = principale;
            this.version = version;
        }

        public String getIdProduit() {
            return idProduit;
        }

        public void setIdProduit(String idProduit) {
            this.idProduit = idProduit;
        }

        public String getIdCommande() {
            return idCommande;
        }

        public void setIdCommande(String idCommande) {
            this.idCommande = idCommande;
        }

        public String getIdArticleCommande() {
            return idArticleCommande;
        }

        public void setIdArticleCommande(String idArticleCommande) {
            this.idArticleCommande = idArticleCommande;
        }

        public String getIdAdresse() {
            return idAdresse;
        }

        public void setIdAdresse(String idAdresse) {
            this.idAdresse = idAdresse;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public String getPrix() {
            return prix;
        }

        public void setPrix(String prix) {
            this.prix = prix;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public String getMarquerEffacer() {
            return marquerEffacer;
        }

        public void setMarquerEffacer(String marquerEffacer) {
            this.marquerEffacer = marquerEffacer;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getTotalCommande() {
            return totalCommande;
        }

        public void setTotalCommande(String totalCommande) {
            this.totalCommande = totalCommande;
        }

        public String getIdUtilisateur() {
            return idUtilisateur;
        }

        public void setIdUtilisateur(String idUtilisateur) {
            this.idUtilisateur = idUtilisateur;
        }

        public String getStatut() {
            return statut;
        }

        public void setStatut(String statut) {
            this.statut = statut;
        }

        public String getDateCommande() {
            return dateCommande;
        }

        public void setDateCommande(String dateCommande) {
            this.dateCommande = dateCommande;
        }

        public String getDateModification() {
            return dateModification;
        }

        public void setDateModification(String dateModification) {
            this.dateModification = dateModification;
        }

        public String getTotalArticleCommande() {
            return totalArticleCommande;
        }

        public void setTotalArticleCommande(String totalArticleCommande) {
            this.totalArticleCommande = totalArticleCommande;
        }

        public String getQuantite() {
            return quantite;
        }

        public void setQuantite(String quantite) {
            this.quantite = quantite;
        }

        public String getPrincipale() {
            return principale;
        }

        public void setPrincipale(String principale) {
            this.principale = principale;
        }

        public String getTypeAdresse() {
            return typeAdresse;
        }

        public void setTypeAdresse(String typeAdresse) {
            this.typeAdresse = typeAdresse;
        }

        public String getPays() {
            return pays;
        }

        public void setPays(String pays) {
            this.pays = pays;
        }

        public String getVille() {
            return ville;
        }

        public void setVille(String ville) {
            this.ville = ville;
        }

        public String getCodePostal() {
            return codePostal;
        }

        public void setCodePostal(String codePostal) {
            this.codePostal = codePostal;
        }

        public String getRue() {
            return rue;
        }

        public void setRue(String rue) {
            this.rue = rue;
        }
    }

    public static ArrayList<Object> getModuleFormResultset(ResultSet result, Object module) {
        if (module != null) {
            ArrayList<Object> listToRetrun = new ArrayList<>();
            if (result != null) {
                try {
                    while (result.next()) {
                        listToRetrun.add(getObject(module, result));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return listToRetrun;
        } else {
            return null;
        }
    }

    private static Object getObject(Object module, ResultSet resultSet) {
        if (module != null && resultSet != null) {
            try {
                if (module instanceof Product) {
                    int id = resultSet.getInt(Modules.PRODUCT.getIdProduit());
                    String reference = resultSet.getString(Modules.PRODUCT.getReference());
                    double prix = resultSet.getDouble(Modules.PRODUCT.getPrix());
                    String nom = resultSet.getString(Modules.PRODUCT.getNom());
                    String description = resultSet.getString(Modules.PRODUCT.getDescription());
                    int stock = resultSet.getInt(Modules.PRODUCT.stock);
                    boolean active = resultSet.getBoolean(Modules.PRODUCT.getActive());
                    boolean marquerEfface = resultSet.getBoolean(Modules.PRODUCT.getMarquerEffacer());
                    int version = resultSet.getInt(Modules.PRODUCT.getVersion());
                    module = new Product(id, reference, prix, nom, description, stock, active, marquerEfface, version);
                } else if (module instanceof Commande) {
                    int id = resultSet.getInt(Modules.COMMANDE.getIdCommande());
                    double totalCommande = resultSet.getDouble(Modules.COMMANDE.getTotalCommande());
                    int idUser = resultSet.getInt(Modules.COMMANDE.getIdUtilisateur());
                    int idAdress = resultSet.getInt(Modules.COMMANDE.getIdAdresse());
                    String status = resultSet.getString(Modules.COMMANDE.getStatut());
                    Date dateCommande = resultSet.getDate(Modules.COMMANDE.getDateCommande());
                    Date dateModification = resultSet.getDate(Modules.COMMANDE.getDateModification());
                    int version = resultSet.getInt(Modules.COMMANDE.getVersion());
                    // todo to make queries for get User and Address then replace into module
                    module = new Commande(id, totalCommande, null, null, status, dateCommande, dateModification, version);
                } else if (module instanceof ArticleCommande) {
                    int id = resultSet.getInt(Modules.ARTICLE_COMMANDE.getIdArticleCommande());
                    int idCommande = resultSet.getInt(Modules.ARTICLE_COMMANDE.getIdCommande());
                    int idUtilisateur = resultSet.getInt(Modules.ARTICLE_COMMANDE.getIdUtilisateur());
                    int idAdresse = resultSet.getInt(Modules.ARTICLE_COMMANDE.getIdAdresse());
                    int idProduct = resultSet.getInt(Modules.ARTICLE_COMMANDE.getIdProduit());
                    double totalArticleCommande = resultSet.getDouble(Modules.ARTICLE_COMMANDE.getTotalArticleCommande());
                    String reference = resultSet.getString(Modules.ARTICLE_COMMANDE.getReference());
                    int quantite = resultSet.getInt(Modules.ARTICLE_COMMANDE.getQuantite());
                    String statut = resultSet.getString(Modules.ARTICLE_COMMANDE.getStatut());
                    Date dateModification = resultSet.getDate(Modules.ARTICLE_COMMANDE.getDateModification());
                    int version = resultSet.getInt(Modules.ARTICLE_COMMANDE.getVersion());
                    // todo to make queries for get User,Address,command,product then replace into module
                    module = new ArticleCommande(id, null, null, null, null, totalArticleCommande, reference, quantite, statut, dateModification, version);
                } else if (module instanceof Adresse) {
                    int id = resultSet.getInt(Modules.ADRESSE.getIdAdresse());
                    int idUser = resultSet.getInt(Modules.ADRESSE.getIdUtilisateur());
                    String rue = resultSet.getString(Modules.ADRESSE.getRue());
                    String codePostal = resultSet.getString(Modules.ADRESSE.getCodePostal());
                    String ville = resultSet.getString(Modules.ADRESSE.getVille());
                    String pays = resultSet.getString(Modules.ADRESSE.getPays());
                    String status = resultSet.getString(Modules.ADRESSE.getStatut());
                    String typeAdresse = resultSet.getString(Modules.ADRESSE.getTypeAdresse());
                    boolean principale = resultSet.getBoolean(Modules.ADRESSE.getPrincipale());
                    int version = resultSet.getInt(Modules.ADRESSE.getVille());
                    // todo to make queries for get User then replace into module
                    module = new Adresse(id, null, rue, codePostal, ville, pays, status, typeAdresse, principale, version);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return module;
    }

    public static Integer parseBooleanToInteger(Boolean isActif) {
        return (isActif) ? 1 : 0;
    }

    public static String completeCreateQuery(Object module) {
        String query = null;
        if (module != null) {
            if (module instanceof Product) {
                query = ((Product) module).getReference() + qote
                        + coma + ((Product) module).getPrice() + coma
                        + qote + ((Product) module).getName() + qote
                        + coma + qote + ((Product) module).getDescription() + qote
                        + coma + ((Product) module).getStock() + coma
                        + parseBooleanToInteger(((Product) module).isActive()) + coma
                        + parseBooleanToInteger(((Product) module).isDeleted()) + coma
                        + ((Product) module).getVersion() + ")" + coma
                        + Statement.RETURN_GENERATED_KEYS;
            } else if (module instanceof ArticleCommande) {

            } else if (module instanceof Commande) {

            } else if (module instanceof Adresse) {

            }
        }
        return query;
    }

    public static String completeUpdateQuery(Object module) {
        String query = null;
        if (module != null) {
            if (module instanceof Product) {
                query = ((Product) module).getReference() + qote
                        + coma + ((Product) module).getPrice() + coma
                        + qote + ((Product) module).getName() + qote
                        + coma + qote + ((Product) module).getDescription() + qote
                        + coma + ((Product) module).getStock() + coma
                        + parseBooleanToInteger(((Product) module).isActive()) + coma
                        + parseBooleanToInteger(((Product) module).isDeleted()) + coma
                        + ((Product) module).getVersion() + ")" + coma
                        + Statement.RETURN_GENERATED_KEYS;
            } else if (module instanceof ArticleCommande) {

            } else if (module instanceof Commande) {

            } else if (module instanceof Adresse) {

            }
        }
        return query;
    }

    public static String completeDeleteQuery(Object module) {
        String query = null;
        if (module != null) {
            if (module instanceof Product) {
                query = ((Product) module).getReference() + qote
                        + coma + ((Product) module).getPrice() + coma
                        + qote + ((Product) module).getName() + qote
                        + coma + qote + ((Product) module).getDescription() + qote
                        + coma + ((Product) module).getStock() + coma
                        + parseBooleanToInteger(((Product) module).isActive()) + coma
                        + parseBooleanToInteger(((Product) module).isDeleted()) + coma
                        + ((Product) module).getVersion() + ")" + coma
                        + Statement.RETURN_GENERATED_KEYS;
            } else if (module instanceof ArticleCommande) {

            } else if (module instanceof Commande) {

            } else if (module instanceof Adresse) {

            }
        }
        return query;
    }
}
