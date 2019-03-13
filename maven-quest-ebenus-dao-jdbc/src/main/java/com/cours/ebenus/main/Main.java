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

import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.impl.RoleDao;
import com.cours.ebenus.dao.impl.UtilisateurDao;

/**
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
        System.out.println("To get specific user by nom write 4");
        System.out.println("To get specific user by mail write 5");
        System.out.println("To create user write 6");
        System.out.println("To delete user write 7");
        System.out.println("To find all role write 8");
        System.out.println("To find user by role id  write 9");
        System.out.println();

        System.out.print("Your choice: ");
        Scanner inputUser = new Scanner(System.in);
        int choiceUser = inputUser.nextInt();
        UtilisateurDao utilisateurDao = new UtilisateurDao();
        RoleDao roleDao = new RoleDao();

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

            case 5:
                System.out.print("Type the identifiant: ");
                String mailResearch;
                mailResearch = inputUser.next();
                utilisateurDao.findUtilisateurByIdentifiant(mailResearch);
                break;

            case 6:
                String name, firstName, gender, mail, password;
                Boolean isActif, markAsErased;
                int version;

                System.out.print("Give the prenom of the new user: ");
                firstName = inputUser.next();

                System.out.print("Give the name of the new user: ");
                name = inputUser.next();

                System.out.print("Give the gender of the new user: ");
                gender = inputUser.next();

                System.out.print("Give the mail of the new user: ");
                mail = inputUser.next();

                System.out.print("Give the password of the new user: ");
                password = inputUser.next();

                System.out.print("Give the status of the new user: ");
                isActif = inputUser.nextBoolean();

                System.out.print("The markAsErased of the new user: ");
                markAsErased = inputUser.nextBoolean();

                System.out.print("Give the version of the new user: ");
                version = inputUser.nextInt();
                Utilisateur user = new Utilisateur(null, gender, firstName, name, mail, password, null, null, null, isActif,
                        markAsErased, version, null);

                utilisateurDao.createUtilisateur(user);
                break;

            case 7:
                System.out.print("Give the id of the user you wanted to erased: ");
                int idToDelete;
                idToDelete = inputUser.nextInt();
                Utilisateur userTodelete = utilisateurDao.findUtilisateurById(idToDelete);
                utilisateurDao.deleteUtilisateur(userTodelete);
                break;

            case 8:
                List<Role> roles = roleDao.findAllRoles();
                for (Role role : roles) {
                    System.out.println(role);
                }
                break;

            case 9:
                System.out.print("Give the id of the role : ");
                int idRole = inputUser.nextInt();
                List<Utilisateur> usersTofind = utilisateurDao.findUtilisateursByIdRole(idRole);
                if (!usersTofind.isEmpty()) {
                }
                for (Utilisateur u : usersTofind) {
                    System.out.println(u.toString());
                }
                break;
            default:
                System.out.println("the command does not exist");
                break;

        }
    }
}
