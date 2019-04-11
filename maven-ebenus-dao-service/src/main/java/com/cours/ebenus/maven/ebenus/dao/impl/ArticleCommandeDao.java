package com.cours.ebenus.maven.ebenus.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.cour.ebenus.maven.ebenus.dao.IDao.IDao;
import com.cours.ebenus.maven.ebenus.dao.connection.DriverManagerSingleton;
import com.cours.ebenus.maven.ebenus.dao.entities.ArticleCommande;
import com.cours.ebenus.maven.ebenus.utils.Constants;
import com.cours.ebenus.maven.ebenus.utils.DaoUtils;
import com.cours.ebenus.maven.ebenus.utils.Queries;

public class ArticleCommandeDao implements IDao<ArticleCommande>{
	

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
        return sendQuery(Queries.getArticleCommandeAllQuery, null);
    }

    @Override
    public ArticleCommande findById(int id) {
        String query = Queries.getArticleCommandeByIDQuery.concat(id + ";");
        List<ArticleCommande> results = new ArrayList<>(sendQuery(query, null));
        return !results.isEmpty() ? results.get(Constants.firstIndice) : new ArticleCommande();
    }

    @Override
    public List<ArticleCommande> findByCriteria(String criteria, Object valueCriteria) {
        String query = null;
        List<ArticleCommande> commandeList = new ArrayList<>();
        if (valueCriteria != null && !criteria.isEmpty()) {
            if (criteria.equals(DaoUtils.Modules.ARTICLE_COMMANDE.getIdUtilisateur())) {
                query = Queries.getArticleCommandeByIdUtilisateurQuery.concat(valueCriteria + ";");
                commandeList.addAll(sendQuery(query, null));
            } else if (criteria.equals(DaoUtils.Modules.ARTICLE_COMMANDE.getIdAdresse())) {
                query = Queries.getArticleCommandeByIdAdresseQuery.concat(valueCriteria + ";");
                commandeList.addAll(sendQuery(query, null));
            } else if (criteria.equals(DaoUtils.Modules.ARTICLE_COMMANDE.getIdCommande())) {
                query = Queries.getArticleCommandeByIdCommandeQuery.concat(valueCriteria + ";");
                commandeList.addAll(sendQuery(query, null));
            } else if (criteria.equals(DaoUtils.Modules.ARTICLE_COMMANDE.getIdProduit())) {
                query = Queries.getArticleCommandeByIdProduitQuery.concat(valueCriteria + ";");
                commandeList.addAll(sendQuery(query, null));
            }else if (criteria.equals(DaoUtils.Modules.ARTICLE_COMMANDE.getReference())) {
                query = Queries.getArticleCommandeByReferenceQuery.concat(valueCriteria + "';");
                commandeList.addAll(sendQuery(query, null));
            }
        }
        return commandeList;
    }

    @Override
    public ArticleCommande create(ArticleCommande articleCommande) {
        List<ArticleCommande> commandList = new ArrayList<>(sendQuery(Queries.createArticleCommandeQuery, articleCommande));
        return !commandList.isEmpty() ? commandList.get(Constants.firstIndice) : new ArticleCommande();
    }

    @Override
    public ArticleCommande update(ArticleCommande articleCommande) {
        List<ArticleCommande> commandeList = new ArrayList<>(sendQuery(Queries.updateArticleCommandeQuery, articleCommande));
        return !commandeList.isEmpty() ? commandeList.get(Constants.firstIndice) : new ArticleCommande();
    }

    @Override
    public boolean delete(ArticleCommande articleCommande) {
        List<ArticleCommande> commandeList = new ArrayList<>(sendQuery(Queries.deleteArticleCommandeQuery, articleCommande));
        return !commandeList.isEmpty() && commandeList.get(Constants.firstIndice) != null;
    }

    @Override
    public List<ArticleCommande> sendQuery(String query, ArticleCommande articleCommande) {
        List<ArticleCommande> articleCommandes = new ArrayList<>();
        for (Object object : DaoUtils.genericQuery(query, articleCommande, connection)) {
            if (object instanceof ArticleCommande) {
                articleCommandes.add((ArticleCommande) object);
            }
        }
        return articleCommandes;
    }

}
