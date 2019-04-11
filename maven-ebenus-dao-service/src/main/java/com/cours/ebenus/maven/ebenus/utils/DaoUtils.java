package com.cours.ebenus.maven.ebenus.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cours.ebenus.maven.ebenus.dao.connection.ConnectionHelper;
import com.cours.ebenus.maven.ebenus.dao.entities.Adresse;
import com.cours.ebenus.maven.ebenus.dao.entities.ArticleCommande;
import com.cours.ebenus.maven.ebenus.dao.entities.Commande;
import com.cours.ebenus.maven.ebenus.dao.entities.Product;
import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.maven.ebenus.dao.impl.UtilisateurDao;
import com.mysql.jdbc.Statement;

public class DaoUtils {
	
	 public enum Modules {
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

	    public static ArrayList<Object> getModuleFromResultset(ResultSet result, Object module) {
	        if (module != null) {
	            ArrayList<Object> listToRetrun = new ArrayList<>();
	            if (result != null) {
	                try {
	                    while (result.next()) {
	                        listToRetrun.add(getModuleType(module, result));
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

	    private static Object getModuleType(Object module, ResultSet resultSet) {
	        if (module != null && resultSet != null) {
	            if (module instanceof Product) {
	                module = buildProduct(resultSet);
	            } else if (module instanceof Commande) {
	                module = buildCommande(resultSet);
	            } else if (module instanceof ArticleCommande) {
	                module = buildArticleCommande(resultSet);
	            } else if (module instanceof Adresse) {
	                module = buildAdresse(resultSet);
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
	                query = ((Product) module).getReference() + Constants.qote
	                        + Constants.coma + ((Product) module).getPrice() + Constants.coma
	                        + Constants.qote + ((Product) module).getName() + Constants.qote
	                        + Constants.coma + Constants.qote + ((Product) module).getDescription() + Constants.qote
	                        + Constants.coma + ((Product) module).getStock() + Constants.coma
	                        + parseBooleanToInteger(((Product) module).isActive()) + Constants.coma
	                        + parseBooleanToInteger(((Product) module).isDeleted()) + Constants.coma
	                        + ((Product) module).getVersion() + ")" + Constants.coma
	                        + Statement.RETURN_GENERATED_KEYS;
	            } else if (module instanceof ArticleCommande) {
	                query = ((ArticleCommande) module).getUser().getIdUtilisateur() + Constants.coma
	                        + ((ArticleCommande) module).getAdresse().getIdAdresse() + Constants.coma
	                        + ((ArticleCommande) module).getAdresse().getIdAdresse() + Constants.coma
	                        + ((ArticleCommande) module).getProduct().getIdProduct() + Constants.coma
	                        + ((ArticleCommande) module).getTotalArticleCommande() + Constants.coma
	                        + Constants.qote + ((ArticleCommande) module).getReference() + Constants.qote + Constants.coma
	                        + ((ArticleCommande) module).getQuantite() + Constants.coma
	                        + Constants.qote + ((ArticleCommande) module).getStatus() + Constants.qote + Constants.coma
	                        + ((ArticleCommande) module).getDateModification() + Constants.coma
	                        + ((ArticleCommande) module).getVersion() + ")"+Constants.coma
	                        +Statement.RETURN_GENERATED_KEYS;
	            } else if (module instanceof Commande) {
	                query = ((Commande) module).getTotalCommand() +
	                		Constants.coma + ((Commande) module).getUser().getIdUtilisateur() + Constants.coma
	                        + ((Commande) module).getAdresse().getIdAdresse() + Constants.coma
	                        + Constants.qote + ((Commande) module).getStatus() + Constants.qote
	                        + Constants.coma + ((Commande) module).getCommadDate() + Constants.coma
	                        + ((Commande) module).getModificationDate() + Constants.coma + ((Commande) module).getVersion() + ")" + Constants.coma
	                        + Statement.RETURN_GENERATED_KEYS;
	            } else if (module instanceof Adresse) {
	                query = ((Adresse) module).getAdressOwner() + Constants.coma
	                        + Constants.qote + ((Adresse) module).getStreet() + Constants.qote + Constants.coma
	                        + Constants.qote + ((Adresse) module).getCodePostal() + Constants.qote + Constants.coma
	                        + Constants.qote + ((Adresse) module).getTown() + Constants.qote + Constants.coma
	                        + Constants.qote + ((Adresse) module).getCountry() + Constants.qote + Constants.coma
	                        + Constants.qote + ((Adresse) module).getStatus() + Constants.qote + Constants.coma
	                        + Constants.qote + ((Adresse) module).getAdressType() + Constants.qote + Constants.coma
	                        + parseBooleanToInteger(((Adresse) module).getPrincipale()) + Constants.coma
	                        + ((Adresse) module).getVersion() + ")" + Constants.coma
	                        + Statement.RETURN_GENERATED_KEYS;
	            }
	        }
	        return query;
	    }

	    public static String completeUpdateQuery(Object module) {
	        String query = null;
	        if (module != null) {
	            if (module instanceof Product) {
	                query = Modules.PRODUCT.getReference() + Constants.equal + Constants.qote + ((Product) module).getReference() + Constants.qote
	                        + Constants.coma + Modules.PRODUCT.getPrix() + Constants.equal + ((Product) module).getPrice() + Constants.coma
	                        + Modules.PRODUCT.getNom() + Constants.equal + Constants.qote + ((Product) module).getName() + Constants.qote
	                        + Constants.coma + Modules.PRODUCT.getDescription() + Constants.equal + Constants.qote + ((Product) module).getDescription() + Constants.qote
	                        + Constants.coma + Modules.PRODUCT.getStock() + Constants.equal + ((Product) module).getStock() + Constants.coma
	                        + Modules.PRODUCT.getActive() + Constants.equal + parseBooleanToInteger(((Product) module).isActive()) + Constants.coma
	                        + Modules.PRODUCT.getMarquerEffacer() + Constants.equal + parseBooleanToInteger(((Product) module).isDeleted()) + Constants.coma
	                        + Modules.PRODUCT.getVersion() + Constants.equal + ((Product) module).getVersion() + " WHERE "
	                        + Modules.PRODUCT.getIdProduit() + Constants.equal + ((Product) module).getIdProduct() + ";";
	            } else if (module instanceof ArticleCommande) {
	                query = Modules.ARTICLE_COMMANDE.getIdUtilisateur() + Constants.equal + ((ArticleCommande) module).getUser().getIdUtilisateur() + Constants.coma
	                        + Modules.ARTICLE_COMMANDE.getIdCommande() + Constants.equal + ((ArticleCommande) module).getAdresse().getIdAdresse() + Constants.coma
	                        + Modules.ARTICLE_COMMANDE.getIdAdresse() + Constants.equal + ((ArticleCommande) module).getAdresse().getIdAdresse() + Constants.coma
	                        + Modules.ARTICLE_COMMANDE.getIdProduit() + Constants.equal + ((ArticleCommande) module).getProduct().getIdProduct() + Constants.coma
	                        + Modules.ARTICLE_COMMANDE.getTotalArticleCommande() + Constants.equal + ((ArticleCommande) module).getTotalArticleCommande() + Constants.coma
	                        + Modules.ARTICLE_COMMANDE.getReference() + Constants.equal + Constants.qote + ((ArticleCommande) module).getReference() + Constants.qote + Constants.coma
	                        + Modules.ARTICLE_COMMANDE.getQuantite() + Constants.equal + ((ArticleCommande) module).getQuantite() + Constants.coma
	                        + Modules.ARTICLE_COMMANDE.getStatut() + Constants.equal + Constants.qote + ((ArticleCommande) module).getStatus() + Constants.qote + Constants.coma
	                        + Modules.ARTICLE_COMMANDE.getDateModification() + Constants.equal + ((ArticleCommande) module).getDateModification() + Constants.coma
	                        + Modules.ARTICLE_COMMANDE.getVersion() + Constants.equal + ((ArticleCommande) module).getVersion() + " WHERE "
	                        + Modules.ARTICLE_COMMANDE.getIdArticleCommande() + ((ArticleCommande) module).getIdArticleCommande() + ";";
	            } else if (module instanceof Commande) {
	                query = Modules.COMMANDE.getTotalCommande() + Constants.equal + ((Commande) module).getTotalCommand() +
	                		Constants.coma + Modules.COMMANDE.getIdUtilisateur() + Constants.equal + ((Commande) module).getUser().getIdUtilisateur() + Constants.coma
	                        + Modules.COMMANDE.getIdAdresse() + Constants.equal + ((Commande) module).getAdresse().getIdAdresse() + Constants.coma
	                        + Modules.COMMANDE.getStatut() + Constants.equal + Constants.qote + ((Commande) module).getStatus() + Constants.qote
	                        + Constants.coma + Modules.COMMANDE.getDateCommande() + Constants.equal + ((Commande) module).getCommadDate() + Constants.coma
	                        + Modules.COMMANDE.getDateModification() + Constants.equal + ((Commande) module).getModificationDate() + Constants.coma
	                        + Modules.COMMANDE.getVersion() + Constants.equal + ((Commande) module).getVersion() + " WHERE "
	                        + Modules.COMMANDE.getIdCommande() + Constants.equal + ((Commande) module).getIdCommand() + ";";
	            } else if (module instanceof Adresse) {
	                query = Modules.ADRESSE.getIdUtilisateur() + Constants.equal + ((Adresse) module).getAdressOwner() + Constants.coma
	                        + Modules.ADRESSE.getRue() + Constants.equal + Constants.qote + ((Adresse) module).getStreet() + Constants.qote + Constants.coma
	                        + Modules.ADRESSE.getCodePostal() + Constants.equal + Constants.qote + ((Adresse) module).getCodePostal() + Constants.qote + Constants.coma
	                        + Modules.ADRESSE.getVille() + Constants.equal + Constants.qote + ((Adresse) module).getTown() + Constants.qote + Constants.coma
	                        + Modules.ADRESSE.getPays() + Constants.equal + Constants.qote + ((Adresse) module).getCountry() + Constants.qote + Constants.coma
	                        + Modules.ADRESSE.getStatut() + Constants.equal + Constants.qote + ((Adresse) module).getStatus() + Constants.qote + Constants.coma
	                        + Modules.ADRESSE.getTypeAdresse() + Constants.equal + Constants.qote + ((Adresse) module).getAdressType() + Constants.qote + Constants.coma
	                        + Modules.ADRESSE.getPrincipale() + Constants.equal + parseBooleanToInteger(((Adresse) module).getPrincipale()) + Constants.coma
	                        + Modules.ADRESSE.getVersion() + Constants.equal + ((Adresse) module).getVersion() + ";";
	            }
	        }
	        return query;
	    }

	    public static int completeDeleteQuery(Object module) {
	        if (module != null) {
	            if (module instanceof Product) {
	                return ((Product) module).getIdProduct();
	            } else if (module instanceof Commande) {
	                return ((Commande) module).getIdCommand();
	            } else if (module instanceof ArticleCommande) {
	                return ((ArticleCommande) module).getIdArticleCommande();
	            } else {
	                return ((Adresse) module).getIdAdresse();
	            }
	        } else {
	            return -1;
	        }
	    }

	    public static List<Object> genericQuery(String query, Object objectPassed, Connection connection) {
	        List<Object> objects = new ArrayList<>();
	        PreparedStatement statement = null;
	        ResultSet result = null;
	        try {
	            if (query.contains("INSERT") || query.contains("UPDATE") || query.contains("DELETE")) {
	                if (query.contains("INSERT")) {
	                    query = query.concat(completeCreateQuery(objectPassed));
	                } else if (query.contains("UPDATE")) {
	                    query = query.concat(completeUpdateQuery(objectPassed));
	                } else {
	                    query = query.concat(completeDeleteQuery(objectPassed) + ";");
	                }
	                statement = connection.prepareStatement(query);
	                statement.executeUpdate();
	                result = statement.getGeneratedKeys();
	                if (result.next()) {
	                    objects.add(objectPassed);
	                } else {
	                    System.err.println("Insertion Failed");
	                }
	            } else {
	                statement = connection.prepareStatement(query);
	                result = statement.executeQuery();
	                objects.addAll(getModuleFromResultset(result, new Product()));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            ConnectionHelper.closeSqlResources(statement, result);
	        }
	        return objects;
	    }

	    private static Product buildProduct(ResultSet resultSet) {
	        try {
	            int id = resultSet.getInt(Modules.PRODUCT.getIdProduit());
	            String reference = resultSet.getString(Modules.PRODUCT.getReference());
	            double prix = resultSet.getDouble(Modules.PRODUCT.getPrix());
	            String nom = resultSet.getString(Modules.PRODUCT.getNom());
	            String description = resultSet.getString(Modules.PRODUCT.getDescription());
	            int stock = resultSet.getInt(Modules.PRODUCT.stock);
	            boolean active = resultSet.getBoolean(Modules.PRODUCT.getActive());
	            boolean marquerEfface = resultSet.getBoolean(Modules.PRODUCT.getMarquerEffacer());
	            int version = resultSet.getInt(Modules.PRODUCT.getVersion());
	            return new Product(id, reference, prix, nom, description, stock, active, marquerEfface, version);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return new Product();
	        }
	    }

	    private static Commande buildCommande(ResultSet resultSet) {
	        try {
	            int id = resultSet.getInt(Modules.COMMANDE.getIdCommande());
	            double totalCommande = resultSet.getDouble(Modules.COMMANDE.getTotalCommande());
	            String status = resultSet.getString(Modules.COMMANDE.getStatut());
	            Date dateCommande = resultSet.getDate(Modules.COMMANDE.getDateCommande());
	            Date dateModification = resultSet.getDate(Modules.COMMANDE.getDateModification());
	            int version = resultSet.getInt(Modules.COMMANDE.getVersion());

	            Utilisateur user = buildUtilisateur(resultSet);
	            Adresse address = buildAdresse(resultSet);

	            return new Commande(id, totalCommande, user, address, status, dateCommande, dateModification, version);
	        } catch (SQLException se) {
	            se.printStackTrace();
	            return new Commande();
	        }
	    }

	    private static ArticleCommande buildArticleCommande(ResultSet resultSet) {
	        try {
	            int id = resultSet.getInt(Modules.ARTICLE_COMMANDE.getIdArticleCommande());
	            double totalArticleCommande = resultSet.getDouble(Modules.ARTICLE_COMMANDE.getTotalArticleCommande());
	            String reference = resultSet.getString(Modules.ARTICLE_COMMANDE.getReference());
	            int quantite = resultSet.getInt(Modules.ARTICLE_COMMANDE.getQuantite());
	            String statut = resultSet.getString(Modules.ARTICLE_COMMANDE.getStatut());
	            Date dateModification = resultSet.getDate(Modules.ARTICLE_COMMANDE.getDateModification());
	            int version = resultSet.getInt(Modules.ARTICLE_COMMANDE.getVersion());

	            Utilisateur user = buildUtilisateur(resultSet);
	            Adresse address = buildAdresse(resultSet);
	            Commande command = buildCommande(resultSet);
	            Product product = buildProduct(resultSet);

	            return new ArticleCommande(id, command, user, address, product, totalArticleCommande, reference, quantite, statut, dateModification, version);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return new ArticleCommande();
	        }
	    }

	    private static Adresse buildAdresse(ResultSet resultSet) {
	        try {
	            int id = resultSet.getInt(Modules.ADRESSE.getIdAdresse());
	            String rue = resultSet.getString(Modules.ADRESSE.getRue());
	            String codePostal = resultSet.getString(Modules.ADRESSE.getCodePostal());
	            String ville = resultSet.getString(Modules.ADRESSE.getVille());
	            String pays = resultSet.getString(Modules.ADRESSE.getPays());
	            String status = resultSet.getString(Modules.ADRESSE.getStatut());
	            String typeAdresse = resultSet.getString(Modules.ADRESSE.getTypeAdresse());
	            boolean principale = resultSet.getBoolean(Modules.ADRESSE.getPrincipale());
	            int version = resultSet.getInt(Modules.ADRESSE.getVille());

	            Utilisateur user = buildUtilisateur(resultSet);

	            return new Adresse(id, user, rue, codePostal, ville, pays, status, typeAdresse, principale, version);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return new Adresse();
	        }
	    }

	    private static Utilisateur buildUtilisateur(ResultSet resultSet) {
	        try {
	            String name = resultSet.getString(UserUtils.UserLib.NOM.getField());
	            String firstName = resultSet.getString(UserUtils.UserLib.PRENOM.getField());
	            String gender = resultSet.getString(UserUtils.UserLib.CIVILITE.getField());
	            String mail = resultSet.getString(UserUtils.UserLib.IDENTIFIANT.getField());
	            String password = resultSet.getString(UserUtils.UserLib.MOT_PASSE.getField());
	            Date birthDate = resultSet.getDate(UserUtils.UserLib.DATE_NAISSANCE.getField());
	            Date createDate = resultSet.getDate(UserUtils.UserLib.DATE_CREATION.getField());
	            Date updateDate = resultSet.getDate(UserUtils.UserLib.DATE_MODIFICATION.getField());
	            Boolean activityState = resultSet.getBoolean(UserUtils.UserLib.IS_ACTIF.getField());
	            Boolean markAsErased = resultSet.getBoolean(UserUtils.UserLib.IS_DELETED.getField());
	            int idUser = resultSet.getInt(UserUtils.UserLib.ID.getField());
	            int version = resultSet.getInt(UserUtils.UserLib.VERSION.getField());

	            Role role = new Role(resultSet.getInt(RoleUtils.RoleLib.ID.getField()), resultSet.getString(UtilisateurDao.ROLE_IDENTIFIANT),
	                    resultSet.getString(RoleUtils.RoleLib.DESCRIPTION.getField()), resultSet.getInt(RoleUtils.RoleLib.VERSION.getField()));

	            return new Utilisateur(idUser, gender, firstName, name, mail, password, birthDate,
	                    createDate, updateDate, activityState, markAsErased, version, role);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return new Utilisateur();
	        }
	    }

}
