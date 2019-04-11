package com.cours.ebenus.maven.ebenus.dao.entities;

import java.util.Date;
import java.util.Objects;

public class ArticleCommande {
	
	private int idArticleCommande;
    private Commande commande;
    private Utilisateur user;
    private Adresse adresse;
    private Product product;
    private double totalArticleCommande;
    private String reference;
    private int quantite;
    private String status;
    private Date dateModification;
    private int version;

    public ArticleCommande(){
        //empty constructor
    }

    public ArticleCommande(int idArticleCommande, Commande commande, Utilisateur user, Adresse adresse, Product product, double totalArticleCommande, String reference, int quantite, String status, Date dateModification, int version){
        this.idArticleCommande = idArticleCommande;
        this.commande = commande;
        this.user = user;
        this.adresse = adresse;
        this.product = product;
        this.totalArticleCommande = totalArticleCommande;
        this.reference = reference;
        this.quantite = quantite;
        this.status = status;
        this.dateModification = dateModification;
        this.version = version;
    }

    public ArticleCommande(Commande commande, Utilisateur user, Adresse adresse, Product product, double totalArticleCommande, String reference, int quantite, String status, Date dateModification, int version){
        this.commande = commande;
        this.user = user;
        this.adresse = adresse;
        this.product = product;
        this.totalArticleCommande = totalArticleCommande;
        this.reference = reference;
        this.quantite = quantite;
        this.status = status;
        this.dateModification = dateModification;
        this.version = version;
    }
    public ArticleCommande(Commande commande, Utilisateur user, Adresse adresse, Product product, double totalArticleCommande, String reference, int quantite, String status, Date dateModification){
        this.commande = commande;
        this.user = user;
        this.adresse = adresse;
        this.product = product;
        this.totalArticleCommande = totalArticleCommande;
        this.reference = reference;
        this.quantite = quantite;
        this.status = status;
        this.dateModification = dateModification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if(o instanceof ArticleCommande){
            ArticleCommande that = (ArticleCommande) o;
            return idArticleCommande == that.idArticleCommande;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArticleCommande);
    }

    @Override
    public String toString() {
        return "ArticleCommande{" +
                "idArticleCommande=" + idArticleCommande +
                ", commande=" + commande +
                ", user=" + user +
                ", adresse=" + adresse +
                ", product=" + product +
                ", totalArticleCommande=" + totalArticleCommande +
                ", reference='" + reference + '\'' +
                ", quantite=" + quantite +
                ", status='" + status + '\'' +
                ", dateModification=" + dateModification +
                ", version=" + version +
                '}';
    }

    public int getIdArticleCommande() {
        return idArticleCommande;
    }

    public void setIdArticleCommande(int idArticleCommande) {
        this.idArticleCommande = idArticleCommande;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getTotalArticleCommande() {
        return totalArticleCommande;
    }

    public void setTotalArticleCommande(double totalArticleCommande) {
        this.totalArticleCommande = totalArticleCommande;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}
