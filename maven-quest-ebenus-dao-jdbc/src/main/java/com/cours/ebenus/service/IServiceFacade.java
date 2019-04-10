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

/**
 *
 * @author ElHadji
 */
public interface IServiceFacade {

    public IUtilisateurDao getUtilisateurDao();

    public IRoleDao getRoleDao();

    public IDao<Product> getProductDao();

    public IDao<Adresse> getAdresseDao();

    public IDao<Commande> getCommandeDao();

    public IDao<ArticleCommande> getArticleCOmmande();
}
