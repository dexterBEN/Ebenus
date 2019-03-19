/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.ihm.utils.Constants;
import com.cours.ebenus.models.UserModel;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import static com.cours.ebenus.ihm.utils.Constants.DB_NOT_AVAILABLE;
import static com.cours.ebenus.ihm.utils.LibUtils.*;

/**
 * FXML Controller class
 *
 * @author elhad
 */
public class HomeController implements Initializable {

    private static final Log logger = LogFactory.getLog(HomeController.class);

    @FXML
    private TableView<UserModel> tableViewUsers;

    @FXML
    private TableColumn<UserModel, Boolean> actionColumn;
    @FXML
    private TableColumn<UserModel, Integer> col_idUtilisateur;
    @FXML
    private TableColumn<UserModel, String> col_civilite;
    @FXML
    private TableColumn<UserModel, String> col_prenom;
    @FXML
    private TableColumn<UserModel, String> col_nom;
    @FXML
    private TableColumn<UserModel, String> col_identifiant;
    @FXML
    private TableColumn<UserModel, String> col_password;
    @FXML
    private TableColumn<UserModel, String> col_birthDate;
    @FXML
    private TableColumn<UserModel, String> col_updateDate;
    @FXML
    private TableColumn<UserModel, String> col_createDate;
    @FXML
    private TableColumn<UserModel, String> col_role;

    private ObservableList<UserModel> observableListUserModel;

    private List<Utilisateur> users;
    private IServiceFacade serviceFacade;

    public HomeController() {
        super();
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
        users = new ArrayList<>(serviceFacade.getUtilisateurDao().findAllUtilisateurs());
        if(!users.isEmpty()){
            observableListUserModel = FXCollections.observableArrayList(getUsersModelFromUsers(users));
        }else{
            dialogMessage(DB_NOT_AVAILABLE);
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initUserModels();
        initUsersTableView();
    }

    private void initUserModels() {
        tableViewUsers.setItems(observableListUserModel);
    }

    private void initUsersTableView() {
        actionColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<UserModel, Boolean>, ObservableValue<Boolean>>() {
                    @Override
                    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<UserModel, Boolean> features) {
                        return new SimpleBooleanProperty(features.getValue() != null);
                    }
                });
        Callback<TableColumn<UserModel, Boolean>, TableCell<UserModel, Boolean>> cellFactory =
                new Callback<TableColumn<UserModel, Boolean>, TableCell<UserModel, Boolean>>() {
                    @Override
                    public TableCell call(final TableColumn<UserModel, Boolean> param) {
                        final TableCell<UserModel, Boolean> cell = new TableCell<UserModel, Boolean>() {
                            InputStream editAsStream = getImage(Constants.APP_PATH + "/src/main/resources/edit.png");
                            Image edit = new Image(editAsStream);
                            final Button updatePersonneBtn = new Button();
                            InputStream deleteAsStream = getImage(
                                    Constants.APP_PATH + "/src/main/resources/delete.png");
                            Image delete = new Image(deleteAsStream);
                            final Button deletePersonneBtn = new Button();

                            @Override
                            public void updateItem(Boolean item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    updatePersonneBtn.setGraphic(new ImageView(edit));
                                    updatePersonneBtn.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {
                                            UserModel userModelSelected = tableViewUsers.getSelectionModel().getSelectedItem();
                                            if(userModelSelected != null){
                                                Utilisateur user = getUserFromUserModel(userModelSelected, users);
                                                if(user != null){
                                                    //todo
                                                }
                                            }
                                        }
                                    });
                                    deletePersonneBtn.setGraphic(new ImageView(delete));
                                    deletePersonneBtn.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {
                                            UserModel userModelSelected = tableViewUsers.getSelectionModel().getSelectedItem();
                                            if(userModelSelected != null){
                                                Utilisateur user = getUserFromUserModel(userModelSelected, users);
                                                if(user != null){
                                                    user.setMarquerEffacer(true);
                                                    users.remove(user);
                                                    observableListUserModel = FXCollections.observableArrayList(getUsersModelFromUsers(users));
                                                    initUserModels();
                                                }
                                            }
                                        }
                                    });
                                    HBox pane = new HBox(updatePersonneBtn, deletePersonneBtn);
                                    setGraphic(pane);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        actionColumn.setCellFactory(cellFactory);
        tableViewUsers.setItems(observableListUserModel);
    }

    private InputStream getImage(String path) {
        InputStream imageAsStream = null;
        try {
            imageAsStream = new FileInputStream(new File(path));
        } catch (Exception ex) {
            logger.error("--> Erreur lors de l'execution, Exception: " + ex.getMessage());
        }
        return imageAsStream;
    }

    public void logout(ActionEvent event) {
        try {
            // Récupère la page ou on se trouve et la cache:
            ((Node) event.getSource()).getScene().getWindow().hide();

            Stage primaryStage = new Stage();
            FXMLLoader loaderPage = new FXMLLoader();
            Pane root = loaderPage.load(getClass().getResource("/views/login.fxml").openStream());

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addUser(ActionEvent event) {

    }

    public void exportCsv(ActionEvent event) {

    }

    public void exportXml(ActionEvent event) {

    }

    public void exportJson(ActionEvent event) {

    }

    public void importCsv(ActionEvent event) {

    }
}
