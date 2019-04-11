package com.cours.ebenus.maven.ebenus.front.office.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cours.ebenus.maven.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.maven.ebenus.dao.service.ServiceFacade;

public class UpdateUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("WE ARE IN UPDATE USER SERVLET YOWZA");

		res.setContentType("text/html");

		// Get value of input form:
		String userIdentifiant = req.getParameter("email");
		String userPassWord = req.getParameter("password");
		String userFirstName = req.getParameter("firstname");
		String userLastName = req.getParameter("lastname");
		String userGender = req.getParameter("sex");
		String birthDate = req.getParameter("dteNaiss");
		String userGenderValue = "";
		System.out.println("=======" + req.getSession().getAttribute("idUtilisateur"));
		Integer userId = (Integer) req.getSession().getAttribute("idUtilisateur");
//		System.out.println(userId.length());

		Utilisateur userUpdated = null;

		Enumeration<String> attributes = req.getSession().getAttributeNames();
		while (attributes.hasMoreElements()) {
			String attribute = attributes.nextElement();
			System.out.println(attribute + " : " + req.getSession().getAttribute(attribute));
		}

		try {
			// Date parseBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date parsedDate = dateFormat.parse(birthDate);
//			int userIdToInt = Integer.parseInt(userId);
			// Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			System.out.println("userId : " + userId);
			Utilisateur userData = new Utilisateur();
			userData.setPrenom(userFirstName);
			userData.setNom(userLastName);
			userData.setIdentifiant(userIdentifiant);
			userData.setMotPasse(userPassWord);
			userData.setDateNaissance(parsedDate);
			userData.setIdUtilisateur(userId);

			ServiceFacade serviceFacade = new ServiceFacade();

			if (userGender.equals("man")) {
				userGenderValue = "Mr";
			} else {
				userGenderValue = "Mme";
			}

			userData.setCivilite(userGenderValue);
			userData.setRole(serviceFacade.getRoleDao().findRoleById(5));
			userUpdated = serviceFacade.getUtilisateurDao().updateUtilisateur(userData);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (userUpdated != null) {
			req.setAttribute("userIdentifiant", userIdentifiant);
			req.setAttribute("userPassword", userPassWord);
			req.setAttribute("userFirstName", userFirstName);
			req.setAttribute("userLastName", userLastName);
			req.setAttribute("userGenderValue", userGenderValue);

			RequestDispatcher rd = req.getRequestDispatcher("/compte-client.jsp");
		} else {
			System.out.println("Sorry username or password error");
			RequestDispatcher rd = req.getRequestDispatcher("/404.html");
			rd.include(req, res);
		}


	}

}
