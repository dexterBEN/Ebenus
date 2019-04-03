package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.dao.IDao;
import com.cours.ebenus.dao.entities.ArticleCommande;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static com.cours.ebenus.utils.DaoUtils.genericQuery;

public class ArticleCommandeDao implements IDao<ArticleCommande> {


    Connection connection = null;

    public ArticleCommandeDao() {
        try {
            connection = DriverManagerSingleton.getConnectionInstance().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ArticleCommande> findAll() {
        return null;
    }

    @Override
    public ArticleCommande findById(int id) {
        return null;
    }

    @Override
    public List<ArticleCommande> findByCriteria(String criteria, Object valueCriteria) {
        return null;
    }

    @Override
    public ArticleCommande create(ArticleCommande articleCommande) {
        return null;
    }

    @Override
    public ArticleCommande update(ArticleCommande articleCommande) {
        return null;
    }

    @Override
    public boolean delete(ArticleCommande articleCommande) {
        return false;
    }

    @Override
    public List<ArticleCommande> sendQuery(String query, ArticleCommande articleCommande) {
        List<ArticleCommande> articleCommandes = new ArrayList<>();
        for (Object object : genericQuery(query, articleCommande, connection)) {
            if (object instanceof ArticleCommande) {
                articleCommandes.add((ArticleCommande) object);
            }
        }
        return articleCommandes;
    }
}
