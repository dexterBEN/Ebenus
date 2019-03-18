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

    public static void dialogMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static boolean isExist(String identify, String password, List<Utilisateur> users) {
        boolean isFound = false;
        Iterator<Utilisateur> it = users.iterator();
        while (!isFound && it.hasNext()) {
            Utilisateur currentUser = it.next();
            isFound = identify.equals(currentUser.getIdentifiant()) && password.equals(currentUser.getMotPasse());
        }
        return isFound;
    }

    public static List<UserModel> setUserToUserModel(List<Utilisateur> users) {
        List<UserModel> userModelList = new ArrayList<>();
        if (!users.isEmpty()) {
            for (Utilisateur user : users) {
                UserModel userModel = new UserModel();
                userModel.setCivilite(user.getCivilite());
                userModel.setDateCreation(getDate(user.getDateCreation()));
                userModel.setRole(user.getRole() != null ? user.getRole().getIdentifiant() : EMPTY);
                userModel.setPrenom(user.getPrenom());
                userModel.setNom(user.getNom());
            }
        }
        return userModelList;
    }

    public static String getDate(Date date) {
        if (date != null) {
            String pattern = "dd/MM/aaaa";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.format(date);
        }else{
            return EMPTY;
        }
    }
}
