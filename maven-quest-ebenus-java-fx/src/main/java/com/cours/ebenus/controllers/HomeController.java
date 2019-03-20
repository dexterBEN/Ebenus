/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.ihm.utils.Constants;
import com.cours.ebenus.models.UserModel;
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

/**
 * FXML Controller class
 *
 * @author elhad
 */
public class HomeController implements Initializable {

	private static final Log logger = LogFactory.getLog(HomeController.class);
	public static final String xmlFilePath = "C://Users//benoni.d//eclipse-workspace//maven-quest-ebenus-java-fx//utilisateur.xml";
	@FXML
	private TableView<UserModel> tableViewUsers;
	@FXML
	private TableColumn<UserModel, Boolean> actionColumn;

	/*
	 * @FXML private TableColumn<UserModel, Integer> col_idUtilisateur;
	 * 
	 * @FXML private TableColumn<UserModel, String> col_civilite = new
	 * TableColumn<>("civilite");
	 * 
	 * @FXML private TableColumn<UserModel, String> col_prenom = new
	 * TableColumn<>("prenom");
	 * 
	 * @FXML private TableColumn<UserModel, String> col_nom = new
	 * TableColumn<>("nom");
	 * 
	 * @FXML private TableColumn<UserModel, String> col_identifiant = new
	 * TableColumn<>("identifiant");
	 * 
	 * @FXML private TableColumn<UserModel, String> col_password = new
	 * TableColumn<>("password");
	 * 
	 * @FXML private TableColumn<UserModel, String> col_birthDate = new
	 * TableColumn<>("dateNaissance");
	 * 
	 * @FXML private TableColumn<UserModel, String> col_updateDate = new
	 * TableColumn<>("dateModification");
	 * 
	 * @FXML private TableColumn<UserModel, String> col_createDate = new
	 * TableColumn<>("dateCreation"); private TableColumn<UserModel, String>
	 * col_role;
	 */

	private ObservableList<UserModel> observableListUserModel = null;

	public HomeController() {
		super();
	}

	/**
	 * Initializes the controller class.
	 *
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// col_civilite.setCellValueFactory(new PropertyValueFactory<>("civilite"));
		// col_prenom.setCellValueFactory(cellData -> cellData.getValue().prenom());
		initUserModels();
		initUsersTableView();
	}

	private void initUserModels() {
		ServiceFacade serviceFacade = new ServiceFacade();
		List<Utilisateur> users = new ArrayList<>();
		users = serviceFacade.getUtilisateurDao().findAllUtilisateurs();
		observableListUserModel = FXCollections.observableArrayList();

		for (Utilisateur user : users) {

			UserModel userModel = new UserModel();

			userModel.setIdUtilisateur(user.getIdUtilisateur());
			userModel.setCivilite(user.getCivilite());
			userModel.setIdentifiant(user.getIdentifiant());
			userModel.setNom(user.getNom());
			userModel.setPrenom(user.getPrenom());
			userModel.setDateCreation(user.getDateCreation().toString());
			userModel.setDateModification(user.getDateModification().toString());
			userModel.setDateNaissance(user.getDateNaissance().toString());
			userModel.setRole(user.getRole().getDescription());
			observableListUserModel.add(userModel);
		}
	}

	private void initUsersTableView() {
		actionColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<UserModel, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<UserModel, Boolean> features) {
						return new SimpleBooleanProperty(features.getValue() != null);
					}
				});
		Callback<TableColumn<UserModel, Boolean>, TableCell<UserModel, Boolean>> cellFactory = //
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

										}
									});
									deletePersonneBtn.setGraphic(new ImageView(delete));
									deletePersonneBtn.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent event) {

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
		System.out.print("SIZE THERE: " + observableListUserModel.size());
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
			// Récupère la page ou ont se trouve et la cache:
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

	@FXML
	public void exportCsv(ActionEvent event) throws Exception {

		Writer writer = null;
		try {
			File file = new File("C://Users//benoni.d//eclipse-workspace//maven-quest-ebenus-java-fx//utilisateur.csv");
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

	@FXML
	public void exportXml(ActionEvent event) {

		/*
		 * try { DocumentBuilderFactory documentFactory =
		 * DocumentBuilderFactory.newInstance(); DocumentBuilder documentBuilder =
		 * documentFactory.newDocumentBuilder(); Document document =
		 * documentBuilder.newDocument();
		 * 
		 * Element parent = document.createElement("userlist"); } catch
		 * (ParserConfigurationException pce) { // TODO Auto-generated catch block
		 * pce.printStackTrace(); } catch (TransformerException tfe) {
		 * tfe.printStackTrace(); }
		 */
	}

	@FXML
	public void exportJson(ActionEvent event) {

		JSONArray userList = new JSONArray();

		try {
			FileWriter file = new FileWriter(
					"C://Users//benoni.d//eclipse-workspace//maven-quest-ebenus-java-fx//utilisateur.json");

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

	public void importCsv(ActionEvent event) {

	}
}
