package com.cours.ebenus.maven.ebenus.front.office.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cours.ebenus.maven.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.maven.ebenus.dao.service.ServiceFacade;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		System.out.println("WE ARE IN LOGIN SERVLET YOLO BRO");

		res.setContentType("text/html");
		// PrintWriter out = res.getWriter();

		String userIdentifiant = req.getParameter("__email");
		String userPassWord = req.getParameter("password");

		ServiceFacade serviceFacade = new ServiceFacade();
		Utilisateur user = serviceFacade.getUtilisateurDao().authenticate(userIdentifiant, userPassWord);

		if (user != null) {

			req.setAttribute("userIdentifiant", userIdentifiant);
			RequestDispatcher rd = req.getRequestDispatcher("user");

			HttpSession sessionUser = req.getSession();
			sessionUser.setAttribute("email", user.getIdentifiant());
			sessionUser.setAttribute("prenom", user.getPrenom());
			sessionUser.setAttribute("nom", user.getNom());
			sessionUser.setAttribute("civilite", user.getCivilite());
			sessionUser.setAttribute("password", user.getMotPasse());

			rd.forward(req, res);

		} else {

			System.out.println("Sorry username or password error");
			RequestDispatcher rd = req.getRequestDispatcher("/404.html");
			rd.include(req, res);
		}
		// out.close();
	}

}
