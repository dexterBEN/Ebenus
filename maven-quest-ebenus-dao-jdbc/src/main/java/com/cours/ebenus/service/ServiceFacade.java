/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.service;

import com.cours.ebenus.dao.IDao;
import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Adresse;
import com.cours.ebenus.dao.entities.ArticleCommande;
import com.cours.ebenus.dao.entities.Commande;
import com.cours.ebenus.dao.entities.Product;
import com.cours.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.factory.AbstractDaoFactory.FactoryDaoType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author ElHadji
 */
public class ServiceFacade implements IServiceFacade {

    private static final Log log = LogFactory.getLog(ServiceFacade.class);
    private final AbstractDaoFactory.FactoryDaoType DEFAULT_IMPLEMENTATION = AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY;

    // On liste toutes les DAO : un DAO pour chaque entité (Utilisateur,Role ect ....)
    private IUtilisateurDao utilisateurDao = null;

    private IRoleDao roleDao = null;

    private IDao<Product> productDao = null;
    private IDao<Commande> commandeDao = null;
    private IDao<ArticleCommande> articleCommandeDao = null;
    private IDao<Adresse> adresseDao = null;


    public ServiceFacade() {
        // mettre tous les DAO
        utilisateurDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getUtilisateurDao();
        roleDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getRoleDao();
        productDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getProductDao();
        commandeDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getCommandeDao();
        articleCommandeDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getArticleCOmmande();
        adresseDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getAdresseDao();
    }

    public ServiceFacade(FactoryDaoType daoType) {
        // mettre tous les DAO
        utilisateurDao = AbstractDaoFactory.getFactory(daoType).getUtilisateurDao();
        roleDao = AbstractDaoFactory.getFactory(daoType).getRoleDao();
        productDao = AbstractDaoFactory.getFactory(daoType).getProductDao();
        commandeDao = AbstractDaoFactory.getFactory(daoType).getCommandeDao();
        articleCommandeDao = AbstractDaoFactory.getFactory(daoType).getArticleCOmmande();
        adresseDao = AbstractDaoFactory.getFactory(daoType).getAdresseDao();
    }

    @Override
    public IUtilisateurDao getUtilisateurDao() {
        return utilisateurDao;
    }

    @Override
    public IRoleDao getRoleDao() {
        return roleDao;
    }

    @Override
    public IDao<Product> getProductDao() {
        return productDao;
    }

    @Override
    public IDao<Adresse> getAdresseDao() {
        return adresseDao;
    }

    @Override
    public IDao<Commande> getCommandeDao() {
        return commandeDao;
    }

    @Override
    public IDao<ArticleCommande> getArticleCOmmande() {
        return articleCommandeDao;
    }
}
