/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import entity.Patient;
import fabrique.Fabrique;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.IService;
import utils.Validator;
import utils.ViewService;

/**
 * FXML Controller class
 *
 * @author EsseJacquesDansomon
 */
public class InscriptionController implements Initializable {

    
    IService service = Fabrique.getInstanceService();
    @FXML
    private TextField labelNom;
    @FXML
    private TextField labelLogin;
    @FXML
    private TextField labelPrenoms;
    @FXML
    private PasswordField labelPassword;
    @FXML
    private PasswordField labelConfirmedPassword;
    @FXML
    private TextField labelCode;
    @FXML
    private TextField labelAntecedant;
    @FXML
    private Text textErrors;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        textErrors.setVisible(false);
    }    

    @FXML
    private void handleInscription(ActionEvent event) {
        //Recuperation des champs 
        String nom = labelNom.getText();
        String prenoms = labelPrenoms.getText();
        String login = labelLogin.getText();
        String password = labelPassword.getText();
        String confirmedPassword = labelConfirmedPassword.getText();
        String code = labelCode.getText();
        String antecedant = labelAntecedant.getText();
        
        //Validation du formulaire
        Validator validator = new Validator();
        validator.isValidMail(login, "login");
        validator.isEmptyStrig(nom, "Votre nom");
        validator.isEmptyStrig(code, "Code");
        validator.isEmptyStrig(prenoms, "Votre Prenom");
        validator.isEqualWIth(password, confirmedPassword, "Les mots de passe ne correspondent pas ");
        
        
        if(validator.isValide())
        {
           
        Patient patient  = new Patient(nom,prenoms, login, password,code, antecedant);
        int idPatient  =  service.createPatient(patient);
        if(idPatient != 0 )
        {
            patient.setId(idPatient);
            LoginController.getCtrl().setUser(patient);
            ViewService.loadALert(Alert.AlertType.INFORMATION, "Inscription", 
                    "Inscription Resuite, Bivemue sur votre dashboard " + patient.toString());
              //Cache la fénétre de connexion
              this.textErrors.getScene().getWindow().hide();
              AnchorPane root = null;
              try {
                  root = FXMLLoader.load(getClass().getResource("/view/patient/PatientProfile.fxml"));
                  Scene scene = new Scene(root);
                  Stage stage = new Stage();
                  stage.setScene(scene);
                  stage.show();
                  
              } catch (IOException ex) {
                  Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
              }
        }else{
            ViewService.loadALert(Alert.AlertType.ERROR, "Error", 
                    "Echec Inscription, Veuillez Ressayer");
        } 
        }else{
            validator.showErrorsAlert();
        }
    }
    
}
