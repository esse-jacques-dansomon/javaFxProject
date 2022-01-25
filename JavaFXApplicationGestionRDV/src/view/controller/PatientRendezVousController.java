/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import entity.RendezVous;
import entity.TypeService;
import entity.User;
import fabrique.Fabrique;
import java.net.URL;
import java.util.ResourceBundle;
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
public class PatientRendezVousController implements Initializable {

    private final User user = LoginController.getCtrl().getUser();
    private final IService service = Fabrique.getInstanceService();
    private ObservableList<RendezVous> obvRendezVous;
    @FXML
    private TableView<RendezVous> tableViewMesRendezVous;
    @FXML
    private TableColumn<RendezVous, TypeService> tblcTypeService;
    @FXML
    private TableColumn<RendezVous, java.sql.Date> tblcDate;
    @FXML
    private TableColumn<RendezVous, String> tblcHeure;
    @FXML
    private TableColumn<RendezVous, String> tblcStatut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTableViewProfesseur();
    }    
    
    private void loadTableViewProfesseur()
    {
       obvRendezVous = FXCollections
               .observableArrayList(service.searchAllRdvByPatient(user));
       tblcTypeService.setCellValueFactory(new PropertyValueFactory<>("typeService"));
       tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
       tblcHeure.setCellValueFactory(new PropertyValueFactory<>("heure"));
       tblcStatut.setCellValueFactory(new PropertyValueFactory<>("etat"));
       tableViewMesRendezVous.setItems(obvRendezVous);
       
    }

}
