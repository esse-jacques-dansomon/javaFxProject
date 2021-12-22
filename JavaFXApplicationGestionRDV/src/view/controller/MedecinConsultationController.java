/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import entity.Consultation;
import entity.Medicament;
import entity.MedicamentPrescription;
import entity.Ordonnance;
import entity.User;
import fabrique.Fabrique;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import service.IService;
import utils.ViewService;

/**
 * FXML Controller class
 *
 * @author EsseJacquesDansomon
 */
public class MedecinConsultationController implements Initializable {

    private final User user = LoginController.getCtrl().getUser();
    private final IService service = Fabrique.getInstanceService();
    private ObservableList<Consultation> obvConsultations;
    private ObservableList<MedicamentPrescription> obvMedecicaments
            = FXCollections
                    .observableArrayList();

    private Consultation ConsultationSelected;
    @FXML
    private TableView<Consultation> tableViewPateints;
    @FXML
    private TableColumn<Consultation, String> tblcNomPrenomPatient;
    @FXML
    private TableColumn<Consultation, String> tlbcDate;
    @FXML
    private TableColumn<Consultation, String> tblcHeure;
    @FXML
    private TextArea lblResultats;
    @FXML
    private TextField lblNomPrenomPatient;
    @FXML
    private TextField lblCodePatient;
    @FXML
    private TextField nomMedicaments;
    @FXML
    private TextField codeMedicament;
    @FXML
    private TextField labelPosologie;
    @FXML
    private TableView<MedicamentPrescription> tableViewMedecament;
    @FXML
    private TableColumn<MedicamentPrescription, String> tblcNomMedicament;
    @FXML
    private TableColumn<MedicamentPrescription, String> tblcPosologie;
    @FXML
    private Button buttonCancelConsultation;
    @FXML
    private Button buttonCancelAddMedicament;
    @FXML
    private FontAwesomeIcon IconAddMedicamentInBd;
    private int id_medicament;
    Ordonnance  ordornance = new  Ordonnance();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.loadTableViewAllConsultation();
        buttonCancelAddMedicament.setDisable(true);
        this.loadTableViewMedicamentPrescription();

        //buttonCancelConsultation.setVisible(false);
    }

    @FXML
    private void handleSelectedPatient(MouseEvent event) {
        this.ConsultationSelected = tableViewPateints.getSelectionModel().getSelectedItem();
        lblNomPrenomPatient.setText(this.ConsultationSelected.getPatient().getNom());
        lblCodePatient.setText(this.ConsultationSelected.getPatient().getPrenom());
        LocalDateTime today = LocalDateTime.now().plusDays(1);     //Today
        /*
        if(ConsultationSelected.getDate().before(Date.valueOf(today.toString())))
            buttonCancelConsultation.setVisible(false);
        buttonCancelConsultation.setVisible(true);*/
    }

    @FXML
    private void handleSaveConsultation(ActionEvent event) {
       this.ConsultationSelected.setConstantes(lblResultats.getText());
       this.ConsultationSelected.setStatut("TERMINE");
       if(this.tableViewMedecament != null){ 
           ordornance.setConsultation(ConsultationSelected);
           //Creation de l'ordoannance
           int id = this.service.CreateOrdonnance(this.ordornance);
           this.ordornance.setId(id);
           //Creation des prescriptions
           this.service.CreatePrescriptionMedicament(obvMedecicaments, this.ordornance);
           this.loadTableViewAllConsultation();
           this.loadTableViewMedicamentPrescription();   
       }
       this.service.updateConsultation(ConsultationSelected);
       this.loadTableViewAllConsultation();
    }

    @FXML
    private void handleAjoutMedacement(ActionEvent event) {
        String posologie = labelPosologie.getText();
        Medicament medicament = new Medicament(this.id_medicament,nomMedicaments.getText(),
                codeMedicament.getText());
        Ordonnance od = new Ordonnance(1);
        MedicamentPrescription medicamentPrecription = 
                new MedicamentPrescription(1,
                posologie, od, medicament);
        labelPosologie.clear();
        obvMedecicaments.add(medicamentPrecription);

    }

    @FXML
    private void handleAnnulerConsultation(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation l'Annuler ");
        alert.setHeaderText("Annuler consultation");
        alert.setContentText("Voulez-vous vraimant annuler le rdv?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            this.ConsultationSelected.setStatut("ANNULER");
            this.service.updateConsultation(ConsultationSelected);

            ViewService.loadALert(Alert.AlertType.INFORMATION, "consultation",
                    "consultation bien annuler ");
        } else {
            ViewService.loadALert(Alert.AlertType.INFORMATION, "Rendez-vous",
                    "consultation NON annuler");
        }
    }

    @FXML
    private void handleSearchMedicament(MouseEvent event) {
        String codeMedicamentSearch = codeMedicament.getText();
        nomMedicaments.setDisable(true);
        Medicament medic = this.service.searchMedicamentByCode(codeMedicamentSearch);
        if (medic != null) {
            nomMedicaments.setText(medic.getNom());
            id_medicament = medic.getId();
            medic = null;
            buttonCancelAddMedicament.setDisable(false);
        } else {
            this.addMedicament();
            nomMedicaments.clear();
        }


    }

    @FXML
    private void handleAddMedicamentInBaseDonnee(MouseEvent event) {
    }

    private void loadTableViewAllConsultation() {
        obvConsultations = FXCollections
                .observableArrayList(service.searchAllConsultationByMedecin(user));
        tlbcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcHeure.setCellValueFactory(new PropertyValueFactory<>("heure"));
        tblcNomPrenomPatient.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getPatient().toString()));
        tableViewPateints.setItems(obvConsultations);
    }

    private void loadTableViewMedicamentPrescription() {
        tblcNomMedicament.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getMedicament().getNom()));
        tblcPosologie.setCellValueFactory(new PropertyValueFactory<>("posologie"));
        tableViewMedecament.setItems(obvMedecicaments);
    }

    private int addMedicament() {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Ajout Medicament");
        dialog.setHeaderText("Veuillez Ajouter un medicament");

         // Set the icon (must be included in the project).
//        dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

         // Set the button types.
        ButtonType loginButtonType = new ButtonType("Ajouter", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Code");
        TextField password = new TextField();
        password.setPromptText("Nom");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

// Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            System.out.println("Username=" + usernamePassword.getKey() + 
                    ", Password=" + usernamePassword.getValue());
            Medicament medicament = new Medicament(username.getText(),password.getText());
            this.service.addMedicament(medicament);
        });
        
        return 0;
        
    }


}
