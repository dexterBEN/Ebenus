package com.cours.ebenus.maven.ebenus.front.office.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cours.ebenus.maven.ebenus.front.office.dao.LoginDao;

public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		System.out.print("WE ARE IN LOGIN SERVLET IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");

		res.setContentType("text/html");
		// PrintWriter out = res.getWriter();

		String userIdentifiant = req.getParameter("__email");
		String userPassWord = req.getParameter("password");

		// ServiceFacade serviceFacade = new ServiceFacade();
		// Utilisateur user = serviceFacade.getClass();

		if (LoginDao.validate(userIdentifiant, userPassWord)) {

			req.setAttribute("userIdentifiant", userIdentifiant);
			RequestDispatcher rd = req.getRequestDispatcher("user");

			rd.forward(req, res);

		} else {

			System.out.println("Sorry username or password error");
			RequestDispatcher rd = req.getRequestDispatcher("/404.html");
			rd.include(req, res);
		}
		// out.close();
	}

}
