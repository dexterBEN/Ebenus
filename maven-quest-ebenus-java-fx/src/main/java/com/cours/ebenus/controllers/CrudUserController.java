package com.cours.ebenus.controllers;

import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.models.UserModel;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static com.cours.ebenus.controllers.HomeController.TAG;
import static com.cours.ebenus.ihm.utils.Constants.UPDATE_TAG;
import static com.cours.ebenus.ihm.utils.LibUtils.getUser;
import static com.cours.ebenus.ihm.utils.LibUtils.getUsersModelFromUsers;

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

    private Utilisateur utilisateur;
    private IServiceFacade serviceFacade = null;
    private String gender;
    private String roleIdentifiant;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        utilisateur = getUser();
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
        if(TAG.equals(UPDATE_TAG)){
            init();
        }else{
            defaultValues();
        }
    }

    public void addUpdateUtilisateur(ActionEvent event) {

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
        dateNaissance.setValue(LocalDate.MIN);
    }

    private void init(){
        defaultValues();
        List<Role> roles = serviceFacade.getRoleDao().findAllRoles();
        List<Utilisateur> array = new ArrayList<>();
        array.add(utilisateur);
        List<String> rolesIdent = new ArrayList<>();
        for(Role r : roles){
            rolesIdent.add(r.getIdentifiant());
        }
        ObservableList<String> dataRoles = FXCollections.observableArrayList(rolesIdent);
        role.setItems(dataRoles);
        role.getSelectionModel().select(roleIdentifiant);
        setUserModelToUpdate(getUsersModelFromUsers(array).get(0));
        ObservableList<String> data = FXCollections.observableArrayList("Mr", "Mme");
        civilite.setItems(data);
        civilite.getSelectionModel().select(gender);
    }

    private void defaultValues(){
        if(utilisateur != null){
            Role role = serviceFacade.getRoleDao().findRoleById(utilisateur.getRole().getIdRole());
            roleIdentifiant = role.getIdentifiant();
            gender = utilisateur.getCivilite();
            this.motPasse.setEditable(false);
        }
    }
}
