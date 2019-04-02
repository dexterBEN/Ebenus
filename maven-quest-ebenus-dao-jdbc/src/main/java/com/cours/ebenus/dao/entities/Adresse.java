package com.cours.ebenus.dao.entities;

import java.util.Objects;

public class Adresse {

    private int idAdresse;
    private Utilisateur adressOwner;
    private String street;
    private String codePostal;
    private String town;
    private String country;
    private String status;
    private String adressType;
    private boolean principale;
    private int version;

    public Adresse() {
        //empty constructor
    }

    public Adresse(int idAdresse, Utilisateur user, String street, String codePostal, String town, String country, String status, String adressType, boolean principale, int version) {
        this.idAdresse = idAdresse;
        this.adressOwner = user;
        this.street = street;
        this.codePostal = codePostal;
        this.town = town;
        this.country = country;
        this.status = status;
        this.adressType = adressType;
        this.principale = principale;
        this.version = version;
    }

    public Adresse(Utilisateur user, String street, String codePostal, String town, String country, String status, String adressType, boolean principale, int version) {
        this.adressOwner = user;
        this.street = street;
        this.codePostal = codePostal;
        this.town = town;
        this.country = country;
        this.status = status;
        this.adressType = adressType;
        this.principale = principale;
        this.version = version;
    }

    public Adresse(Utilisateur user, String street, String codePostal, String town, String country, String status, String adressType, boolean principale) {
        this.adressOwner = user;
        this.street = street;
        this.codePostal = codePostal;
        this.town = town;
        this.country = country;
        this.status = status;
        this.adressType = adressType;
        this.principale = principale;
    }

    public Adresse(Utilisateur user, String street, String codePostal, String town, String country, String status, String adressType) {
        this.adressOwner = user;
        this.street = street;
        this.codePostal = codePostal;
        this.town = town;
        this.country = country;
        this.status = status;
        this.adressType = adressType;
    }

    public Adresse(Utilisateur user, String street, String codePostal, String town, String country, String adressType) {
        this.adressOwner = user;
        this.street = street;
        this.codePostal = codePostal;
        this.town = town;
        this.country = country;
        this.adressType = adressType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (o instanceof Adresse) {
            Adresse adresse = (Adresse) o;
            return idAdresse == adresse.idAdresse;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdresse);
    }


    @Override
    public String toString() {
        return "Adresse{" +
                "idAdresse=" + idAdresse +
                ", adressOwner=" + adressOwner +
                ", street='" + street + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", town='" + town + '\'' +
                ", country='" + country + '\'' +
                ", status='" + status + '\'' +
                ", adressType='" + adressType + '\'' +
                ", principale=" + principale +
                ", version=" + version +
                '}';
    }

    public int getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(int idAdresse) {
        this.idAdresse = idAdresse;
    }

    public Utilisateur getAdressOwner() {
        return adressOwner;
    }

    public void setAdressOwner(Utilisateur adressOwner) {
        this.adressOwner = adressOwner;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdressType() {
        return adressType;
    }

    public void setAdressType(String adressType) {
        this.adressType = adressType;
    }

    public boolean getPrincipale() {
        return principale;
    }

    public void setPrincipale(boolean principale) {
        this.principale = principale;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
