package com.cours.ebenus.maven.ebenus.front.office.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cours.ebenus.maven.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.maven.ebenus.dao.service.ServiceFacade;

public class FindUsersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		System.out.println("IN THE FIND USERS SERVLET");

		List<Utilisateur> userList = new ArrayList<>();
		ServiceFacade serviceFacade = new ServiceFacade();
		userList = serviceFacade.getUtilisateurDao().findAllUtilisateurs();

		if (userList != null) {
			RequestDispatcher rd = req.getRequestDispatcher("userList");

			req.setAttribute("userList", userList);

			for (Utilisateur user : userList) {
				System.out.println(user);

			}

			rd.forward(req, res);
		} else {
			System.out.println("Sorry username or password error");
			RequestDispatcher rd = req.getRequestDispatcher("/404.html");
			rd.include(req, res);

		}

	}

}
