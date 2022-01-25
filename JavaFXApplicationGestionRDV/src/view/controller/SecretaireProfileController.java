/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import entity.User;
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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author EsseJacquesDansomon
 */
public class SecretaireProfileController implements Initializable {

    private User user;
    @FXML
    private Text labelUsername;
    @FXML
    private Text roleName;
    @FXML
    private AnchorPane anchorContent;
    @FXML
    private Text textBreadcrumb;
    @FXML
    private Button btnDeconnexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            this.user = LoginController.getCtrl().getUser();
            labelUsername.setText(this.user.toString());
            roleName.setText(this.user.getRole());
            loadView("SecretaireDemandeDeRdv");
            //this.textBreadcrumb.setText("home/demande-rdv");
            //this.textBreaCrumdTitle.setText("Demande de Rendez-Vous");
        } catch (IOException ex) {
            Logger.getLogger(SecretaireProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void handleSecredaireLogout(ActionEvent event) {
                  AnchorPane root;
        try
        {
            Stage stage = (Stage) btnDeconnexion.getScene().getWindow();
            stage.hide();
            root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Scene scene = new Scene(root);
            Stage stage1 =  new Stage();
            stage1.setScene(scene);
            stage1.show();
                   
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }


    @FXML
    private void handleSecretairePrestations(ActionEvent event) {
        try {
            loadView("SecretairePrestation");
            //this.textBreaCrumdTitle.setText("Demande de Rendez-Vous");
        } catch (IOException ex) {
            Logger.getLogger(PatientProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleSecretaireConsultation(ActionEvent event) {
        try {
            loadView("SecretaireConsultation");
            //this.textBreaCrumdTitle.setText("Demande de Rendez-Vous");
        } catch (IOException ex) {
            Logger.getLogger(PatientProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void loadView(String view) throws IOException{
        AnchorPane children;
        children = FXMLLoader.load(getClass().getResource("/view/secretaire/" + view +".fxml"));
        anchorContent.getChildren().clear();
        anchorContent.getChildren().add(children);
    }

    @FXML
    private void handleSecretaireDemandeDeRdv(ActionEvent event) {
            try {
            loadView("SecretaireDemandeDeRdv");
            //this.textBreaCrumdTitle.setText("Demande de Rendez-Vous");
        } catch (IOException ex) {
            Logger.getLogger(PatientProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
