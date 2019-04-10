package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.dao.IDao;
import com.cours.ebenus.dao.entities.ArticleCommande;
import com.cours.ebenus.utils.DaoUtils;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static com.cours.ebenus.utils.Constants.firstIndice;
import static com.cours.ebenus.utils.DaoUtils.genericQuery;
import static com.cours.ebenus.utils.Queries.*;

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
        return sendQuery(getArticleCommandeAllQuery, null);
    }

    @Override
    public ArticleCommande findById(int id) {
        String query = getArticleCommandeByIDQuery.concat(id + ";");
        List<ArticleCommande> results = new ArrayList<>(sendQuery(query, null));
        return !results.isEmpty() ? results.get(firstIndice) : new ArticleCommande();
    }

    @Override
    public List<ArticleCommande> findByCriteria(String criteria, Object valueCriteria) {
        String query = null;
        List<ArticleCommande> commandeList = new ArrayList<>();
        if (valueCriteria != null && !criteria.isEmpty()) {
            if (criteria.equals(DaoUtils.Modules.ARTICLE_COMMANDE.getIdUtilisateur())) {
                query = getArticleCommandeByIdUtilisateurQuery.concat(valueCriteria + ";");
                commandeList.addAll(sendQuery(query, null));
            } else if (criteria.equals(DaoUtils.Modules.ARTICLE_COMMANDE.getIdAdresse())) {
                query = getArticleCommandeByIdAdresseQuery.concat(valueCriteria + ";");
                commandeList.addAll(sendQuery(query, null));
            } else if (criteria.equals(DaoUtils.Modules.ARTICLE_COMMANDE.getIdCommande())) {
                query = getArticleCommandeByIdCommandeQuery.concat(valueCriteria + ";");
                commandeList.addAll(sendQuery(query, null));
            } else if (criteria.equals(DaoUtils.Modules.ARTICLE_COMMANDE.getIdProduit())) {
                query = getArticleCommandeByIdProduitQuery.concat(valueCriteria + ";");
                commandeList.addAll(sendQuery(query, null));
            }else if (criteria.equals(DaoUtils.Modules.ARTICLE_COMMANDE.getReference())) {
                query = getArticleCommandeByReferenceQuery.concat(valueCriteria + "';");
                commandeList.addAll(sendQuery(query, null));
            }
        }
        return commandeList;
    }

    @Override
    public ArticleCommande create(ArticleCommande articleCommande) {
        List<ArticleCommande> commandList = new ArrayList<>(sendQuery(createArticleCommandeQuery, articleCommande));
        return !commandList.isEmpty() ? commandList.get(firstIndice) : new ArticleCommande();
    }

    @Override
    public ArticleCommande update(ArticleCommande articleCommande) {
        List<ArticleCommande> commandeList = new ArrayList<>(sendQuery(updateArticleCommandeQuery, articleCommande));
        return !commandeList.isEmpty() ? commandeList.get(firstIndice) : new ArticleCommande();
    }

    @Override
    public boolean delete(ArticleCommande articleCommande) {
        List<ArticleCommande> commandeList = new ArrayList<>(sendQuery(deleteArticleCommandeQuery, articleCommande));
        return !commandeList.isEmpty() && commandeList.get(firstIndice) != null;
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
