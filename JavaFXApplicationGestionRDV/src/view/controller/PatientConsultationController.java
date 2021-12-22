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
public class PatientConsultationController implements Initializable {

    private User user = LoginController.getCtrl().getUser();
    private IService service = Fabrique.getInstanceService();
    private ObservableList<Consultation> obvConsultation;
    @FXML
    private TableColumn<Consultation, String> tblcTypeConsultation;
    @FXML
    private TableColumn<Consultation, String> tblcSatut;
    @FXML
    private TableColumn<Consultation, String> tblcDate;
    @FXML
    private TableColumn<Consultation, String> tblcConstantes;
    @FXML
    private TableColumn<Consultation, String> tblcMedecin;
    @FXML
    private ComboBox<?> comboStatus;
    @FXML
    private TextField labelEarchByDate;
    @FXML
    private TableView<Consultation> tableViewConsultations;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadTableView();
    }    
         
    private void loadTableView()
    {
        obvConsultation = FXCollections
               .observableArrayList(service.searchAllConsultationByPatient(user));
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcSatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        tblcMedecin.setCellValueFactory(new PropertyValueFactory<>("medecin"));
        tblcTypeConsultation.setCellValueFactory(
        cellData -> new SimpleStringProperty( cellData.getValue().getMedecin().getTypeConsultation().getLibelle()));
        tblcConstantes.setCellValueFactory(new PropertyValueFactory<>("constantes"));
        tableViewConsultations.setItems(obvConsultation);    
    }
    
    public void loadTableViewOrdonnance()
    {
        
    }
    
    
}
