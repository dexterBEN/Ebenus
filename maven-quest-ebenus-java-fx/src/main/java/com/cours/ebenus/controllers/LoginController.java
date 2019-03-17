/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.controllers;

import java.net.URL;
import java.util.ResourceBundle;

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

	private PasswordField motPasse;

	// private IServiceFacade serviceFacade = null;
	/**
	 * Initializes the controller class.
	 *
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		/*
		 * IServiceFacade service = null; System.out.println("second");
		 * service.getUtilisateurDao().findAllUtilisateurs();
		 */

	}

	@FXML
	public void authenticate(ActionEvent event) throws Exception {
		if ("admin".equals(identifiant.getText()) && "admin".equals(identifiant.getText())) {

			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}
	}
}
