/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import entity.Consultation;
import entity.Prestation;
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
public class SecretairePrestationController implements Initializable {

    private User user = LoginController.getCtrl().getUser();
    private IService service = Fabrique.getInstanceService();
    private ObservableList<Prestation> obvPrestations;
    @FXML
    private TableView<Prestation> tableViewPrestaions;
    @FXML
    private TableColumn<Prestation, String> tblcTypeService;
    @FXML
    private TableColumn<Prestation, String> tblcPatient;
    @FXML
    private TableColumn<Prestation, String> tblcDate;
    @FXML
    private TableColumn<Prestation, String> tblcHeure;
    @FXML
    private TableColumn<Prestation, String> tblcStatut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableViewAllPrestations();
    }    
    
    private void loadTableViewAllPrestations()
    {
       obvPrestations = FXCollections
               .observableArrayList(this.service.searchAllPrestation());
       tblcTypeService.setCellValueFactory(
       cellData -> new SimpleStringProperty( cellData.getValue().getTypePrestation().getLibelle()));
       tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
       tblcStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
       tblcPatient.setCellValueFactory(
       cellData -> new SimpleStringProperty( cellData.getValue().getPatient().toString()));
       tableViewPrestaions.setItems(obvPrestations);
       
    }
}
