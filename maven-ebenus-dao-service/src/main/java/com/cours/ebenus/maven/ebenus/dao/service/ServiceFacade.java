package com.cours.ebenus.maven.ebenus.dao.service;

import com.cour.ebenus.maven.ebenus.dao.ILoginDao.ILoginDao;
import com.cours.ebenus.maven.ebenus.dao.IRoleDao.IRoleDao;
import com.cours.ebenus.maven.ebenus.dao.IUtilisateurDao.IUtilisateurDao;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory.FactoryDaoType;

public class ServiceFacade implements IServiceFacade {

	// private static final Log log = LogFactory.getLog(ServiceFacade.class);
	private final AbstractDaoFactory.FactoryDaoType DEFAULT_IMPLEMENTATION = AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY;

	// On liste toutes les DAO : un DAO pour chaque entit� (Utilisateur,Role ect
	// ....)
	private IUtilisateurDao utilisateurDao = null;
	private IRoleDao roleDao = null;
	private ILoginDao loginDao = null;

	public ServiceFacade() {
		// mettre tous les DAO
		utilisateurDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getUtilisateurDao();
		roleDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getRoleDao();
		loginDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getLoginDao();
	}

	public ServiceFacade(FactoryDaoType daoType) {
		// mettre tous les DAO
		utilisateurDao = AbstractDaoFactory.getFactory(daoType).getUtilisateurDao();
		roleDao = AbstractDaoFactory.getFactory(daoType).getRoleDao();
		loginDao = AbstractDaoFactory.getFactory(daoType).getLoginDao();
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
	public ILoginDao getLoginDao() {
		// TODO Auto-generated method stub
		return loginDao;
	}
}
