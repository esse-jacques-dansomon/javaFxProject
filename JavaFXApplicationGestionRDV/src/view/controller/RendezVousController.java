/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import entity.RendezVous;
import entity.TypeService;
import entity.User;
import fabrique.Fabrique;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import service.IService;
import utils.MailSender;
import utils.ViewService;

/**
 * FXML Controller class
 *
 * @author EsseJacquesDansomon
 */
public class RendezVousController implements Initializable {

    private final IService service = Fabrique.getInstanceService();
    private String serviceChosen;
    private final User userConnected = LoginController.getCtrl().getUser();
    private User patient;
    private ObservableList<TypeService> typeServiceObservable;
    @FXML
    private JFXTimePicker JFXTimePickeed;
    @FXML
    private ComboBox<String> ComboBoxService;
    @FXML
    private ComboBox<TypeService> ComboBoxTypeService;
    @FXML
    private JFXDatePicker JFXDatePeckeed;
    @FXML
    private Text timePicked;
    @FXML
    private TextField labelCodePatient;
    @FXML
    private TextField labelNomPatient;
    @FXML
    private FontAwesomeIcon iconSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ViewService.loadComboBoxService(ComboBoxService);
        loadTypeService(ComboBoxTypeService);
        JFXDatePeckeed.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now().plusDays(2)) < 0);
            }
        });
        if (this.userConnected.getRole().equalsIgnoreCase("ROLE_PATIENT")) {
            this.patient = this.userConnected;
            this.labelNomPatient.setVisible(false);
            this.labelCodePatient.setVisible(false);
            this.iconSearch.setVisible(false);
        } else {
            this.labelNomPatient.setVisible(true);
            this.labelNomPatient.setVisible(true);
            this.iconSearch.setVisible(true);
        }
    }

    @FXML
    private void handleRdvDemande(MouseEvent event) throws Exception {
        TypeService typeService = ComboBoxTypeService.getSelectionModel().getSelectedItem();
       
             
        if (null != JFXTimePickeed  &&  JFXTimePickeed != null && typeService != null) {
              LocalTime timeRdv = JFXTimePickeed.getValue();
                java.sql.Date dateRdv = java.sql.Date.valueOf(JFXDatePeckeed.getValue());
                String timeRdvString = timeRdv.toString();
            if (this.patient == null) {
                ViewService.loadALert(Alert.AlertType.ERROR, "Demande de Rendez-VOus",
                        "Veuillez choisir un patient");
            } else {
                RendezVous rendezVous = new RendezVous(dateRdv, timeRdvString, patient, typeService);
                //Creation du rdv
                int idRdv = service.createRendezVous(rendezVous);
                if (idRdv != 0) {
                    clearFields();
                    ViewService.loadALert(Alert.AlertType.INFORMATION, "Demande de Rendez-VOus",
                            "votre demande est bien envoyée et en cours de traitement ");
                      String message = "Mr/Mrs "+ this.patient.getNom() + " votre demande est bien envoyee et en cours de traitement  ";
                        MailSender.sendMail(this.patient.getLogin(), "CLINIQUE221",message );
                    
                } else {
                    ViewService.loadALert(Alert.AlertType.ERROR, "Demande de Rendez-VOus",
                            "Une Erreur s'est produite, veuillez recommencer");
                }

            }
        } else {
           ViewService.loadALert(Alert.AlertType.ERROR, "Erreur dans le formulaire", "Veuillez remplir tous les champs");
        }

    }

    @FXML
    private void handleChangeService(ActionEvent event) {
        this.serviceChosen = ComboBoxService.getSelectionModel().getSelectedItem();
        loadTypeService(ComboBoxTypeService);
    }

    private void loadTypeService(ComboBox<TypeService> cbo) {
        if (serviceChosen == null) {
            typeServiceObservable = FXCollections.observableArrayList(service.searchAllSpecialite());
        } else {
            if ("consultation".equals(this.serviceChosen.toLowerCase().trim())) {
                typeServiceObservable = FXCollections.observableArrayList(service.searchAllSpecialite());
            } else {
                typeServiceObservable = FXCollections.observableArrayList(service.searchAllTypePrestation());
            }
        }
        cbo.setItems(typeServiceObservable);
    }

    private void clearFields() {
        ComboBoxTypeService.getItems().clear();
    }

    @FXML
    private void handleSearchPatientByCode(MouseEvent event) {
        this.patient = this.service.findPatientByCode(labelCodePatient.getText());
        if (this.patient != null) {
            this.labelNomPatient.setText(this.patient.getNom());
        } else {
            this.labelNomPatient.clear();
        }

    }
}
