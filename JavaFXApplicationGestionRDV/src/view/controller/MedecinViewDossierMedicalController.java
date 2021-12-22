/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import dto.DossierMedicalDto;
import entity.MedicamentPrescription;
import entity.Patient;
import fabrique.Fabrique;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.IService;
import utils.ViewService;

/**
 * FXML Controller class
 *
 * @author EsseJacquesDansomon
 */
public class MedecinViewDossierMedicalController implements Initializable {

    private String serviceChosen = "Consultation";
    private Patient patient = new Patient();
    private final IService service = Fabrique.getInstanceService();
    private ObservableList<DossierMedicalDto> obvConsultation;
    @FXML
    private TableView<DossierMedicalDto> tableViewDossierMedical;
    @FXML
    private TableColumn<DossierMedicalDto, String> tblcTypeConsultation;
    @FXML
    private TableColumn<DossierMedicalDto, String> tblcMedecin;
    @FXML
    private TableColumn<DossierMedicalDto, String> tblcDate;
    @FXML
    private TableColumn<DossierMedicalDto, String> tblcConstantes;
    
    private ObservableList<MedicamentPrescription> obvMedicamentPrescription;

    @FXML
    private TableView<MedicamentPrescription> tableViewMedicamentPrescriptions;
    @FXML
    private TableColumn<MedicamentPrescription, String> tblcNomMedicament;
    @FXML
    private TableColumn<MedicamentPrescription, String> tblcCodeMedicament;
    @FXML
    private TableColumn<MedicamentPrescription, String> tblcPosologie;
    @FXML
    private TextField labeleCodePatientSearch;
    @FXML
    private ComboBox<String> ComboBoxTypeService;
    @FXML
    private TextField labelNomPatient;
    @FXML
    private TextField labelPrenomPatient;
    @FXML
    private TextField labelAntecedantPatient;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ViewService.loadComboBoxService(ComboBoxTypeService);
    }    

    @FXML
    private void handleSearchPatientByCode(MouseEvent event) {
        this.patient = this.service.findPatientByCode(labeleCodePatientSearch.getText());
        if (this.patient != null ) {
            this.labelNomPatient.setText(this.patient.getNom());
            this.labelPrenomPatient.setText(this.patient.getNom());
            this.labelAntecedantPatient.setText(this.patient.getAntecedants());

        }else{
            
            //tableViewDossierMedical = new TableView<>();
            obvConsultation.clear();
             this.obvMedicamentPrescription.clear();
            this.labelNomPatient.clear();
            this.labelPrenomPatient.clear();
            this.labelAntecedantPatient.clear(); 
        }
        this.loadTableView();     

       
    }

    @FXML
    private void handleMedicamentPrescription(MouseEvent event) {
        if(this.serviceChosen.equalsIgnoreCase("Consultation") && this.patient.getId() != 0 ){
            this.obvMedicamentPrescription = 
            FXCollections.observableArrayList(
            this.service.searchAllPrecriptionForPatient(patient));
            this.tblcNomMedicament.setCellValueFactory(
                cellData -> new SimpleStringProperty(
                        cellData.getValue().getMedicament().getNom()));
            this.tblcCodeMedicament.setCellValueFactory( cellData -> new SimpleStringProperty(
                        cellData.getValue().getMedicament().getCode()));
            this.tblcPosologie.setCellValueFactory(new PropertyValueFactory<>("posologie"));
            this.tableViewMedicamentPrescriptions.setItems(obvMedicamentPrescription);
        }else {
          this.obvMedicamentPrescription.clear();
        }
    }
    
    private void loadTableView()
    {
        //Convertion
        if (this.patient != null )
        {
                  obvConsultation = FXCollections.observableArrayList(this.DossierMedicalDtoArray());
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcMedecin.setCellValueFactory(
                cellData -> new SimpleStringProperty(
                        cellData.getValue().getMedecin().getNom())
       
        );
        tblcTypeConsultation.setCellValueFactory(
                cellData -> new SimpleStringProperty(
                      cellData.getValue().getTypeService().getLibelle() )) ;
       
             
        tblcConstantes.setCellValueFactory(new PropertyValueFactory<>("constantes"));
        tableViewDossierMedical.setItems(obvConsultation);  
        }
    
    }

    @FXML
    private void handleChangeService(ActionEvent event) {
       this.serviceChosen = ComboBoxTypeService.getSelectionModel().getSelectedItem();
       this.loadTableView();
       this.obvMedicamentPrescription.clear();
    }
    
    
    private List<DossierMedicalDto> DossierMedicalDtoArray()
    {
        DossierMedicalDto dm = new DossierMedicalDto();
        if("consultation".equals(this.serviceChosen.toLowerCase().trim()) || serviceChosen == null )
             return dm.ConsultationsToDM(this.service.searchConsultationByPatientAndBySatut(this.patient, "TERMINE"));
        return dm.PrestationsToDM(this.service.searchPrestationByPatientAndBySatut(this.patient, "TERMINE"));
    }
    
}
