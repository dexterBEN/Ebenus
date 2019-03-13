/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author elhad
 */
public class LoginController implements Initializable {

	private static final Log logger = LogFactory.getLog(LoginController.class);

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
		String loginFxmlPath = "C:\\Users\\benoni.d\\eclipse-workspace\\maven-quest-ebenus-java-fx\\src\\main\\resources\\views\\login.fxml";
		FXMLLoader loader = new FXMLLoader();
		try {
			FileInputStream fxmlStream = new FileInputStream(loginFxmlPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void authenticate(ActionEvent event) {
	}
}
