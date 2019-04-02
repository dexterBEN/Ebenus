package com.cours.ebenus.dao.entities;

import java.util.Date;
import java.util.Objects;

public class Commande {

    private int idCommand;
    private double totalCommand;
    private Utilisateur user;
    private Adresse adresse;
    private String status;
    private Date commadDate;
    private Date modificationDate;
    private int version;

    public Commande() {
        //empty constructor
    }

    public Commande(int idCommand, double totalCommand, Utilisateur user, Adresse adresse, String status, Date commadDate, Date modificationDate, int version) {
        this.idCommand = idCommand;
        this.totalCommand = totalCommand;
        this.user = user;
        this.adresse = adresse;
        this.status = status;
        this.commadDate = commadDate;
        this.modificationDate = modificationDate;
        this.version = version;
    }

    public Commande(double totalCommand, Utilisateur user, Adresse adresse, String status, Date commadDate, Date modificationDate, int version) {
        this.totalCommand = totalCommand;
        this.user = user;
        this.adresse = adresse;
        this.status = status;
        this.commadDate = commadDate;
        this.modificationDate = modificationDate;
        this.version = version;
    }

    public Commande(double totalCommand, Utilisateur user, Adresse adresse, String status, Date modificationDate) {
        this.totalCommand = totalCommand;
        this.user = user;
        this.adresse = adresse;
        this.status = status;
        this.modificationDate = modificationDate;
    }

    public Commande(double totalCommand, Utilisateur user, Adresse adresse) {
        this.totalCommand = totalCommand;
        this.user = user;
        this.adresse = adresse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (o instanceof Commande) {
            Commande commande = (Commande) o;
            return idCommand == commande.idCommand;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCommand);
    }

    @Override
    public String toString() {
        return "Commande{" +
                "idCommand=" + idCommand +
                ", totalCommand=" + totalCommand +
                ", user=" + user +
                ", adresse=" + adresse +
                ", status='" + status + '\'' +
                ", commadDate=" + commadDate +
                ", modificationDate=" + modificationDate +
                ", version=" + version +
                '}';
    }

    public int getIdCommand() {
        return idCommand;
    }

    public void setIdCommand(int idCommand) {
        this.idCommand = idCommand;
    }

    public double getTotalCommand() {
        return totalCommand;
    }

    public void setTotalCommand(double totalCommand) {
        this.totalCommand = totalCommand;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCommadDate() {
        return commadDate;
    }

    public void setCommadDate(Date commadDate) {
        this.commadDate = commadDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}