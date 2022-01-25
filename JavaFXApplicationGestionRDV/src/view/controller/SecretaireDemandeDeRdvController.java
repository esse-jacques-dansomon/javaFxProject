/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import entity.Consultation;
import entity.Medecin;
import entity.Prestation;
import entity.RendezVous;
import entity.TypeConsultation;
import entity.TypePrestation;
import entity.TypeService;
import entity.User;
import fabrique.Fabrique;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class SecretaireDemandeDeRdvController implements Initializable {

    private User userChosen = new User();
    private RendezVous rdvChosen = new RendezVous() ;
    private User user = LoginController.getCtrl().getUser();
    private final IService service = Fabrique.getInstanceService();
    
    private ObservableList<RendezVous> obvRendezVous;
    
    private ObservableList<User> obvUsersByTypeService;

    @FXML
    private TableView<RendezVous> tableViewMesRendezVous;
    @FXML
    private TableColumn<RendezVous, TypeService> tblcTypeService;
    @FXML
    private TableColumn<RendezVous, String> tblcDate;
    @FXML
    private TableColumn<RendezVous, String> tblcHeure;
    @FXML
    private TableColumn<RendezVous, String> tblcStatut;
    @FXML
    private TableColumn<RendezVous, String> tblcPatient;
    @FXML
    private TextField lblcNomUser;
    @FXML
    private TextField lblcPrenom;
    @FXML
    private TextField lblcNci;
    @FXML
    private ComboBox<User> comboUsersByType;
    @FXML
    private DatePicker datePicker;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTableViewRendezVous();
    }    

    @FXML
    private void handleAcceptationDrv(ActionEvent event) {      
     
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Veuillez Confirmer la validation");
        alert.setContentText("Vous voulez valider le rendez-vous?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){   
            if(this.rdvChosen.getTypeService() instanceof TypeConsultation)
            {
              //creation d'une consultation
                Consultation consultation = new Consultation();
                consultation.setStatut("EN_COURS");
                consultation.setDate(this.rdvChosen.getDate());
                consultation.setMedecin((Medecin) this.userChosen);
                consultation.setPatient(this.rdvChosen.getPatient());
                consultation.setHeure(this.rdvChosen.getHeure());
                if(this.service.ValiderConsultation(consultation) != 0 )
                {
                  this.rdvChosen.setEtat("VALIDE");
                  this.service.UpdateRendezVous(rdvChosen);
                  ViewService.loadALert(Alert.AlertType.INFORMATION, "consultation", 
                        "La consultation a été bien programmé");   
                }else{
                  ViewService.loadALert(Alert.AlertType.ERROR, "consultation", 
                        "UNE ERREUR S'EST PRODUTE");    
                 }  
            }else{  
               //creation d'une prestation
               Prestation prestation = new Prestation();
               prestation.setStatut("EN_COURS");
               prestation.setDate(this.rdvChosen.getDate());
               prestation.setPatient(this.rdvChosen.getPatient());
               prestation.setTypePrestation((TypePrestation) this.rdvChosen.getTypeService());
               if(this.service.ValiderPrestation(prestation) != 0 )
                {
                  this.rdvChosen.setEtat("VALIDE");
                  this.service.UpdateRendezVous(rdvChosen);
                  ViewService.loadALert(Alert.AlertType.INFORMATION, "prestation", 
                        "La prestation a été bien programmé"); 
                }else{
                  ViewService.loadALert(Alert.AlertType.ERROR, "Prestation", 
                        "UNE ERREUR S'EST PRODUTE");    
                }   
        }
        } else {
           ViewService.loadALert(Alert.AlertType.INFORMATION, "RENDEZ-vous", 
                    "ACTION NON EFFECTUÉE"); 
        }
        
        loadTableViewRendezVous();  
    }

    @FXML
    private void handleAnnuler(ActionEvent event) {        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){   
            this.rdvChosen.setEtat("ANNULER");
            this.service.UpdateRendezVous(rdvChosen); 
            
            ViewService.loadALert(Alert.AlertType.INFORMATION, "Rendez-vous", 
                    "Rendez-vous bien annuler ");
        } else {
           ViewService.loadALert(Alert.AlertType.INFORMATION, "Rendez-vous", 
                    "Rendez-vous NON annuler"); 
        }
      
    }
    
    private void loadTableViewRendezVous()
    {
       obvRendezVous = FXCollections
               .observableArrayList(service.searchAllRdvByEtat("EN_COURS"));
       tblcTypeService.setCellValueFactory(new PropertyValueFactory<>("typeService"));
       tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
       tblcHeure.setCellValueFactory(new PropertyValueFactory<>("heure"));
       tblcStatut.setCellValueFactory(new PropertyValueFactory<>("etat"));
       tblcPatient.setCellValueFactory(
      cellData -> new SimpleStringProperty( cellData.getValue().getPatient().toString()));
       tableViewMesRendezVous.setItems(obvRendezVous);
       
    }

    @FXML
    private void handleChoixRdv(MouseEvent event) {
       this.rdvChosen = tableViewMesRendezVous.getSelectionModel().getSelectedItem();
       //Chargement du combox
       
       int id = this.rdvChosen.getTypeService().getId();
        List<User> users = new ArrayList<>();
        
        if(this.rdvChosen.getTypeService() instanceof TypeConsultation)
        {
          users = service.searchAllUserByTypeService(id);  
        }else{
           user  = new User();
           user.setNom("Resposable");
           user.setPrenom("Prestation");
           users.add(user);
        }
       obvUsersByTypeService = FXCollections.observableArrayList(users);
       comboUsersByType.setItems(obvUsersByTypeService);
       comboUsersByType.getSelectionModel().selectFirst();
    }

    @FXML
    private void handleChangeUser(ActionEvent event) {
        this.userChosen = comboUsersByType.getSelectionModel().getSelectedItem();
        if(this.userChosen != null)
        {
           lblcNomUser.setText(this.userChosen.getNom()); 
           lblcPrenom.setText(this.userChosen.getPrenom());
        }

        
    }

    @FXML
    private void handleFiltreParDate(MouseEvent event) {
        String date = this.datePicker.getValue().toString();
        System.out.println(date);
        if(this.datePicker != null)
        {
      obvRendezVous = FXCollections
               .observableArrayList(service.searchAllRdvByEtatAndDate("EN_COURS", date));
       tblcTypeService.setCellValueFactory(new PropertyValueFactory<>("typeService"));
       tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
       tblcHeure.setCellValueFactory(new PropertyValueFactory<>("heure"));
       tblcStatut.setCellValueFactory(new PropertyValueFactory<>("etat"));
       tblcPatient.setCellValueFactory(
      cellData -> new SimpleStringProperty( cellData.getValue().getPatient().toString()));
       tableViewMesRendezVous.setItems(obvRendezVous); 
        }
        ViewService.loadALert(AlertType.ERROR, "FIltre impossible", "Veuillez choisir une date");
    }

    @FXML
    private void hansdleFiltreAll(ActionEvent event) {
        this.loadTableViewRendezVous();
    }
    
}
