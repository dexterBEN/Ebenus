package com.cours.ebenus.ihm.utils;

import com.cours.ebenus.dao.entities.Utilisateur;

import javax.swing.*;
import java.util.Iterator;
import java.util.List;

public class LibUtils {

    public static void dialogMessage(String message){
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
}
