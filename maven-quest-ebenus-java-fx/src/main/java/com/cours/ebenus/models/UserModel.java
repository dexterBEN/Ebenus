package com.cours.ebenus.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserModel {

    private static final Log logger = LogFactory.getLog(UserModel.class);
    private final SimpleIntegerProperty idUtilisateur = new SimpleIntegerProperty();
    private final SimpleStringProperty civilite = new SimpleStringProperty();
    private final SimpleStringProperty prenom = new SimpleStringProperty();
    private final SimpleStringProperty nom = new SimpleStringProperty();
    private final SimpleStringProperty identifiant = new SimpleStringProperty();
    private final SimpleStringProperty motPasse = new SimpleStringProperty();
    private final SimpleStringProperty dateNaissance = new SimpleStringProperty();
    private final SimpleStringProperty dateModification = new SimpleStringProperty();
    private final SimpleStringProperty dateCreation = new SimpleStringProperty();
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final SimpleStringProperty role = new SimpleStringProperty();

    public UserModel() {

    }

    public int getIdUtilisateur() {
        return idUtilisateur.get();
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur.set(idUtilisateur);
    }

    public SimpleIntegerProperty idUtilisateur() {
        return idUtilisateur;
    }

    public String getPrenom() {
        return prenom.get();
    }

    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public SimpleStringProperty prenom() {
        return prenom;
    }

    public String getRole() {
        return role.get();
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    public SimpleStringProperty role() {
        return role;
    }

    public String getDateNaissance() {
        return dateNaissance.get();
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance.set(dateNaissance);
    }

    public SimpleStringProperty dateNaissance() {
        return dateNaissance;
    }

    public String getDateModification() {
        return dateModification.get();
    }

    public void setDateModification(String dateModification) {
        this.dateModification.set(dateModification);
    }

    public SimpleStringProperty dateModification() {
        return dateModification;
    }

    public String getDateCreation() {
        return dateCreation.get();
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation.set(dateCreation);
    }

    public SimpleStringProperty dateCreation() {
        return dateCreation;
    }

    public String getMotPasse() {
        return motPasse.get();
    }

    public void setMotPasse(String motPasse) {
        this.motPasse.set(motPasse);
    }

    public SimpleStringProperty motPasse() {
        return motPasse;
    }

    public String getIdentifiant() {
        return identifiant.get();
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant.set(identifiant);
    }

    public SimpleStringProperty identifiant() {
        return identifiant;
    }

    public String getCivilite() {
        return civilite.get();
    }

    public void setCivilite(String civilite) {
        this.civilite.set(civilite);
    }

    public SimpleStringProperty civilite() {
        return civilite;
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public SimpleStringProperty nom() {
        return nom;
    }
}
