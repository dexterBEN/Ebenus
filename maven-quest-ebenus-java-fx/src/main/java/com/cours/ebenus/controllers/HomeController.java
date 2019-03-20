/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.controllers;

import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.ihm.utils.Constants;
import com.cours.ebenus.models.UserModel;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static com.cours.ebenus.ihm.utils.Constants.*;
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
    public static final String xmlFilePath = APP_PATH + "/utilisateur.xml";

    private ObservableList<UserModel> observableListUserModel;

    private List<Utilisateur> users;
    private IServiceFacade serviceFacade;
    private static Utilisateur currentUser;
    public static String TAG;

    public HomeController() {
        super();
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
        users = new ArrayList<>(serviceFacade.getUtilisateurDao().findAllUtilisateurs());
        currentUser = getUser();
        if (!users.isEmpty()) {
            observableListUserModel = FXCollections.observableArrayList(getUsersModelFromUsers(users));
        } else {
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
                                            if (userModelSelected != null) {
                                                Utilisateur user = getUserFromUserModel(userModelSelected, users);
                                                if (user != null && isAdmin(currentUser)) {
                                                    TAG = UPDATE_TAG;
                                                    launchCrudView(event, user);
                                                } else {
                                                    dialogMessage(NOT_AUTHORIZED);
                                                }
                                            }
                                        }
                                    });
                                    deletePersonneBtn.setGraphic(new ImageView(delete));
                                    deletePersonneBtn.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {
                                            UserModel userModelSelected = tableViewUsers.getSelectionModel().getSelectedItem();
                                            if (userModelSelected != null) {
                                                Utilisateur user = getUserFromUserModel(userModelSelected, users);
                                                if (user != null && isAdmin(currentUser)) {
                                                    if (!user.equals(currentUser)) {
                                                        user.setMarquerEffacer(true);
                                                        serviceFacade.getUtilisateurDao().updateUtilisateur(user);
                                                        users.remove(user);
                                                        observableListUserModel = FXCollections.observableArrayList(getUsersModelFromUsers(users));
                                                        initUserModels();
                                                    } else {
                                                        dialogMessage(OPERATION_DENIED);
                                                    }
                                                } else {
                                                    dialogMessage(NOT_AUTHORIZED);
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
        if (isAdmin(currentUser)) {
            TAG = CREATE_TAG;
            launchCrudView(event, null);
        } else {
            dialogMessage(NOT_AUTHORIZED);
        }
    }

    @FXML
    public void exportCsv(ActionEvent event) throws Exception {

        Writer writer = null;
        try {
            File file = new File(APP_PATH + "/utilisateur.csv");
            writer = new BufferedWriter(new FileWriter(file));
            for (UserModel userModel : observableListUserModel) {

                String text = userModel.getCivilite() + ";" + userModel.getNom() + ";" + userModel.getPrenom() + ";"
                        + userModel.getIdUtilisateur() + ";" + userModel.getDateCreation() + ";"
                        + userModel.getDateModification() + ";" + userModel.getDateNaissance() + ";"
                        + userModel.getRole() + "\n";

                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            writer.flush();
            writer.close();
        }

    }

    private void launchCrudView(ActionEvent event, Utilisateur utilisateur) {
        Parent root = null;
        try {
            //setUser(utilisateur);
            CrudUserController controller = new CrudUserController();
            controller.setUtilisateur(utilisateur);
            root = FXMLLoader.load(getClass().getResource("/views/addUpdateUser.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.info("error on loading update page");
        }
    }

    @FXML
    public void exportXml(ActionEvent event) {

        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element parentElement = document.createElement("userlist");// create tag <userList></userList>
            document.appendChild(parentElement);

            for (UserModel userModel : observableListUserModel) {
                // NDT: checké les cas de colonnes null
                // Create DOM Tag:
                Element userElement = document.createElement("user");
                Element idUserElement = document.createElement("id");
                Element nameUserElement = document.createElement("name");
                Element surnameUserElement = document.createElement("surname");
                Element emailUserElement = document.createElement("email");

                nameUserElement.setTextContent(userModel.getNom());
                surnameUserElement.setTextContent(userModel.getPrenom());
                emailUserElement.setTextContent(userModel.getIdentifiant());

                String idUser = Integer.toString(userModel.getIdUtilisateur());
                idUserElement.setTextContent(idUser);

                userElement.appendChild(nameUserElement);
                userElement.appendChild(surnameUserElement);
                userElement.appendChild(emailUserElement);
                userElement.appendChild(idUserElement);

                parentElement.appendChild(userElement);

            }

            // create the xml file
            // transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));

            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException pce) {

            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }


    @FXML
    public void exportJson(ActionEvent event) {

        JSONArray userList = new JSONArray();

        try {
            FileWriter file = new FileWriter(APP_PATH + "/utilisateur.json");

            for (UserModel userModel : observableListUserModel) {
                JSONObject userObject = new JSONObject();

                userObject.put("idUtilisateur", userModel.getIdUtilisateur());
                userObject.put("civilite", userModel.getCivilite());
                userObject.put("nom", userModel.getNom());
                userObject.put("prenom", userModel.getPrenom());
                userObject.put("identifiant", userModel.getIdentifiant());

                userList.add(userObject);
            }

            file.write(userList.toString());
            file.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void importCsv(ActionEvent event) {
        try {
            String separator = ";";
            String line = "";
            List<Utilisateur> users = new ArrayList<>();
            BufferedReader br = null;

            // observableListUserModel = FXCollections.observableArrayList();
            br = new BufferedReader(new FileReader(APP_PATH + "/utilisateur_import.csv"));
            Date birthDate;
            Utilisateur user;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(separator);
                try {
                    birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(data[6]);
                    user = new Utilisateur(data[1], data[2], data[3], data[4], data[5], birthDate);
                    users.add(user);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (!users.isEmpty()) {
                // logger.info("Data user: " + users);
                IServiceFacade serviceFacade = new ServiceFacade();

                for (Utilisateur utilisateur : users) {
                    Utilisateur ut = serviceFacade.getUtilisateurDao().createUtilisateur(utilisateur);
                }
                dialogMessage(USER_CREATE);
                users = new ArrayList<>(serviceFacade.getUtilisateurDao().findAllUtilisateurs());
                observableListUserModel = FXCollections.observableArrayList(getUsersModelFromUsers(users));
                initUserModels();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
