/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * FXML Controller class
 *
 * @author elhad
 */
public class LoginController implements Initializable {

    private static final Log logger = LogFactory.getLog(LoginController.class);

    private TextField identifiant;

    private PasswordField motPasse;

    //private IServiceFacade serviceFacade = null;
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void authenticate(ActionEvent event) {
    }
}
