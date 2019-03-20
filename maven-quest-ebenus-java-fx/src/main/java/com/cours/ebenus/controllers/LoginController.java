/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.controllers;

import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.cours.ebenus.ihm.utils.Constants.*;
import static com.cours.ebenus.ihm.utils.LibUtils.*;

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
    @FXML
    private AnchorPane anchorPane;

    private List<Utilisateur> usersToLoadFromDb;

    private IServiceFacade serviceFacade = null;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
        usersToLoadFromDb = new ArrayList<>();
    }

    @FXML
    public void authenticate(ActionEvent event) throws Exception {
        String identifyText = this.identifiant.getText();
        String passwordText = this.motPasse.getText();
        if (!identifyText.isEmpty() && !passwordText.isEmpty() && serviceFacade.getUtilisateurDao() != null) {
            usersToLoadFromDb.addAll(serviceFacade.getUtilisateurDao().findUtilisateurByIdentifiant(identifyText));
            if (!usersToLoadFromDb.isEmpty() && isExist(identifyText, passwordText, usersToLoadFromDb)) {
                Parent root = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                dialogMessage(FIELD_WRONG);
                this.motPasse.setText(RESET_FIELD);
            }
        } else {
            System.err.println("fields empty");
            dialogMessage(FIELDS_EMPTY);
        }
    }

}
