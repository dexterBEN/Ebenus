package com.cours.ebenus.controllers;

import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.models.UserModel;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static com.cours.ebenus.controllers.HomeController.TAG;
import static com.cours.ebenus.ihm.utils.Constants.*;
import static com.cours.ebenus.ihm.utils.LibUtils.*;

public class CrudUserController implements Initializable {

    private static final Log logger = LogFactory.getLog(CrudUserController.class);

    @FXML
    private ComboBox civilite;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField identifiant;
    @FXML
    private PasswordField motPasse;
    @FXML
    private DatePicker dateNaissance;
    @FXML
    private ComboBox role;

    private UserModel userModelToUpdate;

    private static Utilisateur utilisateur;
    private IServiceFacade serviceFacade = null;
    private String gender;
    private String roleIdentifiant;
    private boolean isInsert;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // utilisateur = getUser();
        isInsert = false;
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
        init();
    }

    public void addUpdateUtilisateur(ActionEvent event) {
        setParams();
        try {
            if (TAG.equals(UPDATE_TAG) && isInsert) {
                utilisateur = serviceFacade.getUtilisateurDao().updateUtilisateur(utilisateur);
                dialogMessage(USER_UPDATE);
            } else if (TAG.equals(CREATE_TAG) && isInsert) {
                utilisateur = serviceFacade.getUtilisateurDao().createUtilisateur(utilisateur);
                dialogMessage(USER_CREATE);
            }
        } catch (NullPointerException e) {
            dialogMessage(ERROR);
        }
        if (utilisateur != null) {
            goBack(event);
        }
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void setUserModelToUpdate(UserModel userModelToUpdate) {
        this.userModelToUpdate = userModelToUpdate;
        nom.setText(userModelToUpdate.getNom());
        prenom.setText(userModelToUpdate.getPrenom());
        identifiant.setText(userModelToUpdate.getIdentifiant());
        motPasse.setText(userModelToUpdate.getMotPasse());
        Date date = getDateFromString(userModelToUpdate.getDateNaissance());
        dateNaissance.setValue(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
    }

    private void init() {
        defaultValues();
        List<Role> roles = serviceFacade.getRoleDao().findAllRoles();
        List<Utilisateur> array = new ArrayList<>();
        array.add(utilisateur);
        List<String> rolesIdent = new ArrayList<>();
        for (Role r : roles) {
            rolesIdent.add(r.getIdentifiant());
        }
        ObservableList<String> dataRoles = FXCollections.observableArrayList(rolesIdent);
        role.setItems(dataRoles);
        role.getSelectionModel().select(roleIdentifiant);
        ObservableList<String> data = FXCollections.observableArrayList("Mr", "Mme");
        civilite.setItems(data);
        civilite.getSelectionModel().select(gender);

        if (TAG.equals(UPDATE_TAG)) {
            setUserModelToUpdate(getUsersModelFromUsers(array).get(0));
        }
    }

    private void defaultValues() {
        if (utilisateur != null) {
            Role role = serviceFacade.getRoleDao().findRoleById(utilisateur.getRole().getIdRole());
            roleIdentifiant = role.getIdentifiant();
            gender = utilisateur.getCivilite();
            this.motPasse.setEditable(TAG.equals(CREATE_TAG));
        }
    }

    private void setParams() {
        String name = nom.getText();
        String firstName = prenom.getText();
        String email = identifiant.getText();
        String git = motPasse.getText();
        Date date = Date.from(dateNaissance.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        String civ = (String) civilite.getSelectionModel().getSelectedItem();
        String status = (String) role.getSelectionModel().getSelectedItem();
        if (!name.isEmpty() && !firstName.isEmpty() && !email.isEmpty() && !password.isEmpty() && !civ.isEmpty()) {
            if (utilisateur == null) {
                utilisateur = new Utilisateur();
            }
            utilisateur.setNom(name);
            utilisateur.setPrenom(firstName);
            utilisateur.setIdentifiant(email);
            utilisateur.setCivilite(civ);
            utilisateur.setRole(status != null ? serviceFacade.getRoleDao().findRoleByIdentifiant(status).get(0) : null);
            utilisateur.setDateNaissance(date);
            utilisateur.setMotPasse(password);
            isInsert = true;
        } else {
            dialogMessage(FILL_ALL_FIELD);
        }
    }

    private void goBack(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.info("error on loading home page");
        }
    }
}
