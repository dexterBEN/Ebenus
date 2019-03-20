package com.cours.ebenus.controllers;

import com.cours.ebenus.models.UserModel;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CrudUserController implements Initializable {

    private static final Log logger = LogFactory.getLog(CrudUserController.class);

    private ComboBox civilite;

    private TextField prenom;

    private TextField nom;

    private TextField identifiant;

    private PasswordField motPasse;

    private DatePicker dateNaissance;

    private ComboBox role;

    private UserModel userModelToUpdate;

    //private Utilisateur utilisateur;
    //private IServiceFacade serviceFacade = null;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void addUpdateUtilisateur(ActionEvent event) {

    }

    /*public void setUtilisateur(Utilisateur utilisateur) {
     this.utilisateur = utilisateur;
     }*/
    public void setUserModelToUpdate(UserModel userModelToUpdate) {
        this.userModelToUpdate = userModelToUpdate;
        nom.setText(userModelToUpdate.getNom());
        prenom.setText(userModelToUpdate.getPrenom());
        identifiant.setText(userModelToUpdate.getIdentifiant());
        motPasse.setText(userModelToUpdate.getMotPasse());
        dateNaissance.setValue(LocalDate.MIN);
    }
}
