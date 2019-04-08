package com.cours.ebenus.maven.ebenus.front.office.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/client")
public class WebClientController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WebClientController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// ServiceFacade toto = new ServiceFacade();

		// response.setContentType("text/html");
		// response.setCharacterEncoding("UTF-8");
		//
		// PrintWriter pen = response.getWriter();
		// pen.println("Good mornig");

		this.getServletContext().getRequestDispatcher("/WEB-INF/hello.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		// ServiceF
		// Test according to:
		// https://www.javatpoint.com/example-of-login-form-in-servlet
		response.setContentType("text/html");
		PrintWriter pencil = response.getWriter();

		String userName = request.getParameter("__email");
		String password = request.getParameter("password");

		if (userName.equals("dexter")) {
			RequestDispatcher rd = request.getRequestDispatcher("WebWelcomeUser");
			rd.forward(request, response);
		} else {
			pencil.print("Sorry mail or password wrong");
			/*
			 * RequestDispatcher rd = request .getRequestDispatcher(
			 * "/maven-ebenus-front-office-web/src/main/webapp/frontOffice/login.html");
			 */

			// la vue est charger mais pas forcément display
			this.getServletContext().getRequestDispatcher("path vers page user").forward(request, response);

			// affichage de la vue:

			// request.getAttribute(name);
			/*
			 * 
			 * Fairre la page .jsp afin de charger les donner dans "user.jsp"
			 * 
			 * request.setAttribute('attr-name', 'value-atttr'); //set des attribut de la
			 * vue .jsp
			 */

			// rd.include(request, response);
		}

	}
}
