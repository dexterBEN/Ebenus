package com.cours.ebenus.ihm.utils;

import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.models.UserModel;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class LibUtils {

    private static String EMPTY = "";
    public static Utilisateur user = null;

    public static void dialogMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static boolean isExist(String identify, String password, List<Utilisateur> users) {
        boolean isFound = false;
        Iterator<Utilisateur> it = users.iterator();
        while (!isFound && it.hasNext()) {
            Utilisateur currentUser = it.next();
            isFound = identify.equals(currentUser.getIdentifiant()) && password.equals(currentUser.getMotPasse());
            if(isFound){
                setUser(currentUser);
            }
        }
        return isFound;
    }

    public static void setUser(Utilisateur us){
        user = us;
    }

    public static Utilisateur getUser(){
        return user;
    }

    public static List<UserModel> getUsersModelFromUsers(List<Utilisateur> users) {
        List<UserModel> userModelList = new ArrayList<>();
        if (!users.isEmpty()) {
            for (Utilisateur user : users) {
                UserModel userModel = new UserModel();
                userModel.setCivilite(user.getCivilite());
                userModel.setDateCreation(getDate(user.getDateCreation()));
                userModel.setRole(user.getRole() != null ? user.getRole().getDescription() : EMPTY);
                userModel.setPrenom(user.getPrenom());
                userModel.setNom(user.getNom());
                userModel.setIdentifiant(user.getIdentifiant());
                userModel.setDateNaissance(getDate(user.getDateNaissance()));
                userModel.setDateModification(getDate(user.getDateModification()));

                if(!user.isMarquerEffacer()){
                    userModelList.add(userModel);
                }
            }
        }
        return userModelList;
    }

    public static Utilisateur getUserFromUserModel(UserModel userModel, List<Utilisateur> listOfUser){
        Utilisateur user = null;
        boolean isFound = false;
        Iterator<Utilisateur> it = listOfUser.iterator();
        while(!isFound && it.hasNext()){
            Utilisateur current = it.next();
            isFound = userModel.getIdentifiant().equals(current.getIdentifiant());
            if(isFound)
                user = current;
        }
        return user;
    }

    public static String getDate(Date date) {
        if (date != null) {
            String pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.format(date);
        }else{
            return EMPTY;
        }
    }
    public static boolean isAdmin(Utilisateur userToCheck){
        return (user != null && userToCheck.getRole().getIdRole() == 1 && user.equals(userToCheck));
    }
}
