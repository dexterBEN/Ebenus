package com.cours.ebenus.maven.ebenus.factory;

import com.cour.ebenus.maven.ebenus.dao.ILoginDao.ILoginDao;
import com.cours.ebenus.maven.ebenus.dao.IRoleDao.IRoleDao;
import com.cours.ebenus.maven.ebenus.dao.IUtilisateurDao.IUtilisateurDao;

public abstract class AbstractDaoFactory {

	public static String className = AbstractDaoFactory.class.getName();
	// private static final Log log = LogFactory.getLog(AbstractDaoFactory.class);

	public enum FactoryDaoType {

		JDBC_DAO_FACTORY;
	}

	public abstract IUtilisateurDao getUtilisateurDao();

	public abstract IRoleDao getRoleDao();

	public abstract ILoginDao getLoginDao();

	/**
	 * M�thode pour r�cup�rer une factory de DAO
	 *
	 * @param daoType
	 * @return AbstractDaoFactory
	 */
	public static AbstractDaoFactory getFactory(FactoryDaoType daoType) {
		return new DaoFactory();
	}

}
