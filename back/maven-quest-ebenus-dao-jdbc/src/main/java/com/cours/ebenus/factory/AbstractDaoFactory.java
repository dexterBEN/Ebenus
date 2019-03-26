/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.factory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.IUtilisateurDao;

/**
 *
 * @author ElHadji
 */
public abstract class AbstractDaoFactory {

	public static String className = AbstractDaoFactory.class.getName();
	private static final Log log = LogFactory.getLog(AbstractDaoFactory.class);

	public enum FactoryDaoType {

		JDBC_DAO_FACTORY;
	}

	public abstract IUtilisateurDao getUtilisateurDao();

	public abstract IRoleDao getRoleDao();

	/**
	 * Méthode pour récupérer une factory de DAO
	 *
	 * @param daoType
	 * @return AbstractDaoFactory
	 */
	public static AbstractDaoFactory getFactory(FactoryDaoType daoType) {
		return new DaoFactory();
	}
}
