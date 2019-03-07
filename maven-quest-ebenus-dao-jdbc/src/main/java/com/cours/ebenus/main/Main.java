/*
 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.main;

import java.util.List;

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

		UtilisateurDao utilisateurDao = new UtilisateurDao();

		List<Utilisateur> users = utilisateurDao.findAllUtilisateurs();

		for (Utilisateur user : users) {
			System.out.println(user);
		}
	}
}
