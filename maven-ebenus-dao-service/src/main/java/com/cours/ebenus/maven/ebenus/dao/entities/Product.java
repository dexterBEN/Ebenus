package com.cours.ebenus.maven.ebenus.dao.entities;

import java.util.Objects;

public class Product {
	
	private int idProduct;
    private String reference;
    private double price;
    private String name;
    private String description;
    private int stock;
    private boolean isActive;
    private boolean isDeleted;
    private int version;

    public Product() {
        //nothing
    }

    public Product(int idProduct, String reference, double price, String name, String description, int stock, boolean isActive, boolean isDeleted, int version) {
        this.idProduct = idProduct;
        this.reference = reference;
        this.price = price;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.version = version;
    }

    public Product(String reference, double price, String name, String description, int stock, boolean isActive, boolean isDeleted, int version) {
        this.reference = reference;
        this.price = price;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.version = version;
    }

    public Product(String reference, double price, String name, String description, int stock, boolean isActive, boolean isDeleted) {
        this.reference = reference;
        this.price = price;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
    }

    public Product(String reference, double price, String name, String description, int stock, boolean isActive) {
        this.reference = reference;
        this.price = price;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if(o instanceof Product){
            Product product = (Product) o;
            return idProduct == product.idProduct;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct);
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", reference='" + reference + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                ", isActive=" + isActive +
                ", isDeleted=" + isDeleted +
                ", version=" + version +
                '}';
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}
