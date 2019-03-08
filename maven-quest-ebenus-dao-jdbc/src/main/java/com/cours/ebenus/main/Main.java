/*
 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.main;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.impl.UtilisateurDao;

/**
 *
 * @author elhad
 */
public class Main {

	private static final Log log = LogFactory.getLog(Main.class);

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here

		System.out.println("What you want to do?");
		System.out.println("To see the list of user write 1");
		System.out.println("To get specific user by id write 2");
		System.out.println("To get specific user by prenom write 3");
		System.out.print("Your choice: ");
		Scanner inputUser = new Scanner(System.in);
		int choiceUser = inputUser.nextInt();
		UtilisateurDao utilisateurDao = new UtilisateurDao();

		switch (choiceUser) {

		case 1:
			List<Utilisateur> users = utilisateurDao.findAllUtilisateurs();
			for (Utilisateur user : users) {
				System.out.println(user);
			}
			break;

		case 2:
			System.out.print("The id you search: ");
			int idToFind;
			idToFind = inputUser.nextInt();
			utilisateurDao.findUtilisateurById(idToFind);
			break;

		case 3:
			System.out.print("The firstname you search: ");
			String firstNameResearch;
			firstNameResearch = inputUser.next();
			utilisateurDao.findUtilisateursByPrenom(firstNameResearch);
			break;

		case 4:
			System.out.print("The name you search: ");
			String nameResearch;
			nameResearch = inputUser.next();
			utilisateurDao.findUtilisateursByNom(nameResearch);
			break;

		default:
			System.out.println("the commande does not exist");
			break;

		}
	}
}
