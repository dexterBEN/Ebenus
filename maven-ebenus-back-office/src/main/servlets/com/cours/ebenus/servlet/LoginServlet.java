package com.cours.ebenus.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.factory.AbstractDaoFactory.FactoryDaoType;
import com.cours.ebenus.service.ServiceFacade;
import com.cours.ebenus.utils.RoleUtils.RoleLib;

public class LoginServlet extends HttpServlet {
	
	ServiceFacade serviceFacade = null;
	private final String LOGIN_ID = "textinput";
	private final String PASSWORD_ID = "passwordinput";
	private final String Admin = "Administrateur";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		processRequest(request, response);
	}
	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
		ServiceFacade = new ServiceFacade(FactoryDaoType.JDBC_DAO_FACTORY);
		
		System.err.println("Login Page");

		res.setContentType("text/html");
		
		String login = request.getParameter(LOGIN_ID);
		String password = request.getParameter(PASSWORD_ID);
		
		if(login.isEmpty() || password.isEmpty()) {
			//todo diplays pop up
		}else {
			Utilisateur user = serviceFacade.getUtilisateurDao().findUtilisateurByIdentifiant(login));
			boolean hasAccess = user != null && Admin.equals(user.getRole().getIdentifiant()) 
					&& password.equals(user.getMotPasse());
			if(hasAccess) {
				RequestDispatcher requestDispatcher = reqrequest.getRequestDispatcher("/dashboard.html");
				requestDispatcher.include(request, response);
			}else {
				//todo clear fields and display message
			}
		}
	}
}
