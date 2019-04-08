package com.cours.ebenus.maven.ebenus.front.office.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HomePage")
public class IndexPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public IndexPageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * RequestDispatcher view = request .getRequestDispatcher(
		 * "/maven-ebenus-front-office-web/src/main/webapp/frontOffice/index.html");
		 * view.forward(request, response);
		 */

		this.getServletContext().getRequestDispatcher("/indexo.html").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
