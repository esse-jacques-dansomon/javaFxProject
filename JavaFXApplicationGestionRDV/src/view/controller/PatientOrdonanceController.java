/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import entity.Medicament;
import entity.MedicamentPrescription;
import entity.Ordonnance;
import entity.Patient;
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
import javafx.scene.input.MouseEvent;

import service.IService;

/**
 * FXML Controller class
 *
 * @author EsseJacquesDansomon
 */
public class PatientOrdonanceController implements Initializable {

    private User user = LoginController.getCtrl().getUser();
    private IService service = Fabrique.getInstanceService();
    private Ordonnance ordonnanceSlected = new Ordonnance();
    private ObservableList<Ordonnance> obvOrdonnances;
    private ObservableList<MedicamentPrescription> obvMedicaments;

    @FXML
    private ComboBox<String> comboStatus;
    @FXML
    private TextField labelSearchByDate;
    @FXML
    private TableColumn<Ordonnance, String> tblcDateAt;
    @FXML
    private TableColumn<Ordonnance, String> tblcTypeConsultation;
    //private TableColumn<Ordonnance, String> tblcPosologie;
    @FXML
    private TableView<Ordonnance> tableViewOrdonnance;
    @FXML
    private TableView<MedicamentPrescription> tableViewMedicament;
    @FXML
    private TableColumn<MedicamentPrescription, String> tblcTypeNomMedicament;
    @FXML
    private TableColumn<MedicamentPrescription, String> tblcTypeCodeMedicament;
    @FXML
    private TableColumn<MedicamentPrescription, String> tblcPosologieMedicament;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.loadTableView();
    } 
    
    private void loadTableView()
    {
        obvOrdonnances = FXCollections
               .observableArrayList(this.service.searchAllOrdonnanceByPatient(user));
        tblcDateAt.setCellValueFactory(
      cellData -> new SimpleStringProperty( cellData.getValue().getConsultation().getDate().toString() ));
     
        tblcTypeConsultation.setCellValueFactory(
              cellData -> new SimpleStringProperty( cellData.getValue().getConsultation().getTypeConsultation().getLibelle() ));

        tableViewOrdonnance.setItems(obvOrdonnances); 
        
    }

        
    private void loadTableViewMedacement()
    {
       
        
    }
    @FXML
    private void handleShowAllMyMedicamentByOrdonnance(MouseEvent event) {
        this.obvMedicaments =  FXCollections.observableArrayList(this.service.searchAllPrecriptionForPatient(user.userToPatient()));
            this.tblcTypeNomMedicament.setCellValueFactory(
                cellData -> new SimpleStringProperty(
                        cellData.getValue().getMedicament().getNom()));
            this.tblcTypeCodeMedicament.setCellValueFactory( cellData -> new SimpleStringProperty(
                        cellData.getValue().getMedicament().getCode()));
            this.tblcPosologieMedicament.setCellValueFactory(new PropertyValueFactory<>("posologie"));
            this.tableViewMedicament.setItems(obvMedicaments);
    }
    
    
    

    
}
