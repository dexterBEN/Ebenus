/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.factory;

import com.cours.ebenus.dao.IDao;
import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Adresse;
import com.cours.ebenus.dao.entities.ArticleCommande;
import com.cours.ebenus.dao.entities.Commande;
import com.cours.ebenus.dao.entities.Product;
import com.cours.ebenus.dao.impl.AdresseDao;
import com.cours.ebenus.dao.impl.ArticleCommandeDao;
import com.cours.ebenus.dao.impl.CommandeDao;
import com.cours.ebenus.dao.impl.ProductDao;
import com.cours.ebenus.dao.impl.RoleDao;
import com.cours.ebenus.dao.impl.UtilisateurDao;

/**
 *
 * @author ElHadji
 */
public class DaoFactory extends AbstractDaoFactory {

    @Override
    public IUtilisateurDao getUtilisateurDao() {
        return new UtilisateurDao();
    }

    @Override
    public IRoleDao getRoleDao() {
        return new RoleDao();
    }

	@Override
	public IDao<Product> getProductDao() {
		// TODO Auto-generated method stub
		return new ProductDao();
	}

	@Override
	public IDao<Adresse> getAdresseDao() {
		// TODO Auto-generated method stub
		return new AdresseDao();
	}

	@Override
	public IDao<Commande> getCommandeDao() {
		// TODO Auto-generated method stub
		return new CommandeDao();
	}

	@Override
	public IDao<ArticleCommande> getArticleCOmmande() {
		// TODO Auto-generated method stub
		return new ArticleCommandeDao();
	}
}
