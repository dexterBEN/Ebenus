/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.impl.UtilisateurDao;
import com.cours.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author elhad
 */
public class LoginController implements Initializable {

    private static final Log logger = LogFactory.getLog(LoginController.class);

    @FXML
    private TextField identifiant;
    @FXML
    private PasswordField motPasse;

    private List<Utilisateur> usersToLoadFromDb;

    // private IServiceFacade serviceFacade = null;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        IServiceFacade service = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
        System.out.println("second");
        if(service.getUtilisateurDao() != null){
            usersToLoadFromDb = new ArrayList<>(service.getUtilisateurDao().findAllUtilisateurs());
        }
    }

    @FXML
    public void authenticate(ActionEvent event) throws Exception {
        String identifyText = this.identifiant.getText();
        String passwordText = this.motPasse.getText();
        if (!identifyText.isEmpty() && !passwordText.isEmpty()) {
            if(isExist(identifyText, passwordText)) {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }else {
            System.err.println("fields empty");
        }
    }
    private boolean isExist(String identify, String password){
        boolean isFound = false;
        Iterator<Utilisateur> it = usersToLoadFromDb.iterator();
        while(!isFound && it.hasNext()){
            Utilisateur currentUser = it.next();
            isFound = identify.equals(currentUser.getIdentifiant()) && password.equals(currentUser.getMotPasse());
        }
        return isFound;
    }
}
