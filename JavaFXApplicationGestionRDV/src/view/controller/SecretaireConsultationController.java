/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import entity.Consultation;
import entity.User;
import fabrique.Fabrique;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.IService;

/**
 * FXML Controller class
 *
 * @author EsseJacquesDansomon
 */
public class SecretaireConsultationController implements Initializable {

    private final User user = LoginController.getCtrl().getUser();
    private final IService service = Fabrique.getInstanceService();
    private ObservableList<Consultation> obvConsultations;
    @FXML
    private TableView<Consultation> tableViewMesRendezVous;
    @FXML
    private TableColumn<Consultation, String> tblcTypeService;
    @FXML
    private TableColumn<Consultation, String> tblcPatient;
    @FXML
    private TableColumn<Consultation, String> tblcDate;
    @FXML
    private TableColumn<Consultation, String> tblcdocteur;
    @FXML
    private TableColumn<Consultation, String> tblcStatut;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTableViewAllConsultation();
    }    
    
        private void loadTableViewAllConsultation()
    {
       obvConsultations = FXCollections
               .observableArrayList(service.searchAllConsultation());
       tblcTypeService.setCellValueFactory(
       cellData -> new SimpleStringProperty( cellData.getValue().getMedecin().getTypeConsultation().getLibelle()));
       tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
       tblcStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
       tblcdocteur.setCellValueFactory(
       cellData -> new SimpleStringProperty( cellData.getValue().getMedecin().toString()));  
       tblcPatient.setCellValueFactory(
       cellData -> new SimpleStringProperty( cellData.getValue().getPatient().toString()));
       tableViewMesRendezVous.setItems(obvConsultations);
       
    }
}
