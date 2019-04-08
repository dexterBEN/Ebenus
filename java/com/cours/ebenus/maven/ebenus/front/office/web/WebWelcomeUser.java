package com.cours.ebenus.maven.ebenus.front.office.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebWelcomeUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
		doGet(request, response);

		response.setContentType("text/html");
		PrintWriter pen = response.getWriter();

		String userName = request.getParameter("__email");
		pen.print("Welcom:" + userName);

		this.getServletContext().getRequestDispatcher("/compte-client.jsp").forward(request, response);

		pen.close();
	}
}
