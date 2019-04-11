package com.cours.ebenus.maven.ebenus.factory;

import com.cour.ebenus.maven.ebenus.dao.ILoginDao.ILoginDao;
import com.cours.ebenus.maven.ebenus.dao.IRoleDao.IRoleDao;
import com.cours.ebenus.maven.ebenus.dao.IUtilisateurDao.IUtilisateurDao;
import com.cours.ebenus.maven.ebenus.dao.impl.RoleDao;
import com.cours.ebenus.maven.ebenus.dao.impl.UtilisateurDao;

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
	public ILoginDao getLoginDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
