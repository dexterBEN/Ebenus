package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.dao.IDao;
import com.cours.ebenus.dao.entities.Commande;
import com.cours.ebenus.dao.entities.Product;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static com.cours.ebenus.utils.Constants.firstIndice;
import static com.cours.ebenus.utils.DaoUtils.Modules;
import static com.cours.ebenus.utils.DaoUtils.genericQuery;
import static com.cours.ebenus.utils.Queries.*;

public class ProductDao implements IDao<Product> {


    private Connection connection = null;

    public ProductDao() {
        try {
            connection = DriverManagerSingleton.getConnectionInstance().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        return sendQuery(getProductsAllQuery, null);
    }

    @Override
    public Product findById(int id) {
        String query = getByProductsIDQuery.concat(id + ";");
        List<Product> results = new ArrayList<>(sendQuery(query, null));
        return !results.isEmpty() ? results.get(firstIndice) : null;
    }

    @Override
    public List<Product> findByCriteria(String criteria, Object valueCriteria) {
        String query = null;
        List<Product> productList = new ArrayList<>();
        if (valueCriteria != null && !criteria.isEmpty()) {
            if (criteria.equals(Modules.PRODUCT.getNom())) {
                query = getByProductNameQuery.concat(valueCriteria + "';");
                productList.addAll(sendQuery(query, null));
            } else if (criteria.equals(Modules.PRODUCT.getDescription())) {
                query = getByProductReferenceQuery.concat(valueCriteria + "';");
                productList.addAll(sendQuery(query, null));
            } else if (criteria.equals(Modules.PRODUCT.getPrix())) {
                query = getByProductPriceQuery.concat(valueCriteria + ";");
                productList.addAll(sendQuery(query, null));
            } else if (criteria.equals(Modules.PRODUCT.getReference())) {
                query = getByProductDescriptionQuery.concat(valueCriteria + "%';");
                productList.addAll(sendQuery(query, null));
            }
        }
        return productList;
    }

    @Override
    public Product create(Product product) {
        List<Product> results = new ArrayList<>(sendQuery(createProductQuery, product));
        return !results.isEmpty() ? results.get(firstIndice) : null;
    }

    @Override
    public Product update(Product product) {
        List<Product> results = new ArrayList<>(sendQuery(updateProductQuery, product));
        return !results.isEmpty() ? results.get(firstIndice) : null;
    }

    @Override
    public boolean delete(Product product) {
        product.setDeleted(true);
        List<Product> results = new ArrayList<>(sendQuery(updateProductQuery, product));
        return !results.isEmpty() && results.get(firstIndice) != null;
    }

    @Override
    public List<Product> sendQuery(String query, Product product) {
        List<Product> products = new ArrayList<>();
        for (Object object : genericQuery(query, product, connection)) {
            if (object instanceof Product) {
                products.add((Product) object);
            }
        }
        return products;
    }
}
