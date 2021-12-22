/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import entity.Prestation;
import entity.User;
import fabrique.Fabrique;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.IService;

/**
 * FXML Controller class
 *
 * @author EsseJacquesDansomon
 */
public class PatientPrestationController implements Initializable {

    private User user = LoginController.getCtrl().getUser();
    private IService service = Fabrique.getInstanceService();
    private ObservableList<Prestation> obvPrestations;
    @FXML
    private TableColumn<Prestation, String> tblcSatut;
    @FXML
    private TableColumn<Prestation, String>  tblcDate;
    @FXML
    private TableColumn<Prestation, String>  tblcTypePretation;
    @FXML
    private TableColumn<Prestation, String>  tblcResultats;
    @FXML
    private ComboBox<String> comboStatus;
    @FXML
    private TextField labelSearchByDate;
    @FXML
    private TableView<Prestation> tableViewPrestation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadTableView();
    }  
    
    private void loadTableView()
    {
        obvPrestations = FXCollections
               .observableArrayList(service.searchAllPrestationByPatient(user));
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcSatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        tblcResultats.setCellValueFactory(new PropertyValueFactory<>("resultats"));
        tblcTypePretation.setCellValueFactory(new PropertyValueFactory<>("typePrestation"));
        tableViewPrestation.setItems(obvPrestations); 
        
    }
    
    
}
