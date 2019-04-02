package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.ConnectionHelper;
import com.cours.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.dao.IDao;
import com.cours.ebenus.dao.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.cours.ebenus.utils.Constants.firstIndice;
import static com.cours.ebenus.utils.DaoUtils.*;
import static com.cours.ebenus.utils.Queries.*;

public class ProductDao implements IDao<Product> {


    Connection connection = null;

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
        return sendQuery(getByProductsIDQuery, null) != null ? sendQuery(getByProductsIDQuery, null).get(firstIndice) : null;
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
        return sendQuery(createProductQuery, product) != null ? sendQuery(createProductQuery, product).get(firstIndice) : null;
    }

    @Override
    public Product update(Product product) {
        return sendQuery(updateProductQuery, product) != null ? sendQuery(createProductQuery, product).get(firstIndice) : null;
    }

    @Override
    public boolean delete(Product product) {
        product.setDeleted(true);
        return sendQuery(updateProductQuery, product) != null && sendQuery(createProductQuery, product).get(firstIndice) != null;
    }

    @Override
    public List<Product> sendQuery(String query, Product product) {
        List<Product> products = new ArrayList<>();
        for (Object object : genericQuery(query, product, connection)) {
            if(object instanceof Product){
                products.add((Product) object);
            }
        }
        return products;
    }
}
