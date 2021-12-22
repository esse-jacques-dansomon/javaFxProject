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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author EsseJacquesDansomon
 */
public class MedecinProfileController implements Initializable {

    private User user = LoginController.getCtrl().getUser();
    @FXML
    private Text labelUsername;
    @FXML
    private Text roleName;
    @FXML
    private AnchorPane anchorContent;
    @FXML
    private Text textBreadcrumb;

    /**
     * Initializes the controller class.
     * @param  url
     * @param  rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.user = LoginController.getCtrl().getUser();

        labelUsername.setText(this.user.toString());
        roleName.setText(this.user.getRole());
        try {
            this.loadView("MedecinConsultation");
            //this.textBreadcrumb.setText("home/profile");
            //this.textBreaCrumdTitle.setText("Demande de Rendez-Vous");
        } catch (IOException ex) {
            Logger.getLogger(MedecinProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void handleSecredaireLogout(ActionEvent event) {
    }

    @FXML
    private void handleMedecineConsultaions(ActionEvent event) {
        try {
            this.loadView("MedecinConsultation");
        } catch (IOException ex) {
            Logger.getLogger(MedecinProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handleMedecinAskDossierMedical(ActionEvent event) throws IOException {
        this.loadView("MedecinViewDossierMedical");
    }

    @FXML
    private void handleMedecinPlannierRdv(ActionEvent event) throws IOException {
        AnchorPane children;
        children = FXMLLoader.load(getClass().getResource("/view/patient/rendezVousView.fxml"));
        anchorContent.getChildren().clear();
        anchorContent.getChildren().add(children);
    }
    
    private void loadView(String view) throws IOException{
        AnchorPane children;
        children = FXMLLoader.load(getClass().getResource("/view/medecin/" + view +".fxml"));
        anchorContent.getChildren().clear();
        anchorContent.getChildren().add(children);
    }
    
    
}
