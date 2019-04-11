package com.cours.ebenus.maven.ebenus.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.cour.ebenus.maven.ebenus.dao.IDao.IDao;
import com.cours.ebenus.maven.ebenus.dao.connection.DriverManagerSingleton;
import com.cours.ebenus.maven.ebenus.dao.entities.Product;
import com.cours.ebenus.maven.ebenus.utils.Constants;
import com.cours.ebenus.maven.ebenus.utils.DaoUtils;
import com.cours.ebenus.maven.ebenus.utils.DaoUtils.Modules;
import com.cours.ebenus.maven.ebenus.utils.Queries;

public class ProductDao implements IDao<Product>{
	
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
        return sendQuery(Queries.getProductsAllQuery, null);
    }

    @Override
    public Product findById(int id) {
        String query = Queries.getByProductsIDQuery.concat(id + ";");
        List<Product> results = new ArrayList<>(sendQuery(query, null));
        return !results.isEmpty() ? results.get(Constants.firstIndice) : new Product();
    }

    @Override
    public List<Product> findByCriteria(String criteria, Object valueCriteria) {
        String query = null;
        List<Product> productList = new ArrayList<>();
        if (valueCriteria != null && !criteria.isEmpty()) {
            if (criteria.equals(Modules.PRODUCT.getNom())) {
                query = Queries.getByProductNameQuery.concat(valueCriteria + "';");
                productList.addAll(sendQuery(query, null));
            } else if (criteria.equals(Modules.PRODUCT.getDescription())) {
                query = Queries.getByProductReferenceQuery.concat(valueCriteria + "';");
                productList.addAll(sendQuery(query, null));
            } else if (criteria.equals(Modules.PRODUCT.getPrix())) {
                query = Queries.getByProductPriceQuery.concat(valueCriteria + ";");
                productList.addAll(sendQuery(query, null));
            } else if (criteria.equals(Modules.PRODUCT.getReference())) {
                query = Queries.getByProductDescriptionQuery.concat(valueCriteria + "%';");
                productList.addAll(sendQuery(query, null));
            }
        }
        return productList;
    }

    @Override
    public Product create(Product product) {
        List<Product> results = new ArrayList<>(sendQuery(Queries.createProductQuery, product));
        return !results.isEmpty() ? results.get(Constants.firstIndice) : new Product();
    }

    @Override
    public Product update(Product product) {
        List<Product> results = new ArrayList<>(sendQuery(Queries.updateProductQuery, product));
        return !results.isEmpty() ? results.get(Constants.firstIndice) : new Product();
    }

    @Override
    public boolean delete(Product product) {
        product.setDeleted(true);
        List<Product> results = new ArrayList<>(sendQuery(Queries.updateProductQuery, product));
        return !results.isEmpty() && results.get(Constants.firstIndice) != null;
    }

    @Override
    public List<Product> sendQuery(String query, Product product) {
        List<Product> products = new ArrayList<>();
        for (Object object : DaoUtils.genericQuery(query, product, connection)) {
            if (object instanceof Product) {
                products.add((Product) object);
            }
        }
        return products;
    }

}
