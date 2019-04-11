package com.cours.ebenus.maven.ebenus.dao.service;

import com.cour.ebenus.maven.ebenus.dao.ILoginDao.ILoginDao;
import com.cours.ebenus.maven.ebenus.dao.IRoleDao.IRoleDao;
import com.cours.ebenus.maven.ebenus.dao.IUtilisateurDao.IUtilisateurDao;

public interface IServiceFacade {

	public IUtilisateurDao getUtilisateurDao();

	public IRoleDao getRoleDao();

	public ILoginDao getLoginDao();

}
