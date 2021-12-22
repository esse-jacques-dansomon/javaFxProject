/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import entity.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.IService;
import fabrique.Fabrique;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author EsseJacquesDansomon
 */
public class LoginController implements Initializable {
    
    //declarer LoginCOntroller comme classe Static
    private static LoginController ctrl;

    IService service = Fabrique.getInstanceService();
    
    //l'utilisateur connected
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private PasswordField labelPassword;
    @FXML
    private TextField labelLogin;
    @FXML
    private Text txtError;
    @FXML
    private ImageView imageLogin;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
        txtError.setVisible(false);
        imageLogin.getImage();
        
        //ctrl prend tous les attributs de LoginCOntroller
        ctrl = this;
        
    }  

    public static LoginController getCtrl() {
        return ctrl;
    }


    public User getUser() {
        return user;
    }
   
    
    
    @FXML
    private void handleLogin(ActionEvent event) 
    {
       String login = labelLogin.getText().trim();
       String password = labelPassword.getText().trim(); 
       
        if(login.isEmpty() || password.isEmpty())
        {
          txtError.setText("login ou le mot de passe Obligatoire");
          txtError.setVisible(true);
        }
        else{
          user = service.login(login, password);
          if(user == null)
          {
               txtError.setText("login ou le mot de passe Incorrect");
               txtError.setVisible(true);
          }
          else
          {
              //Cache la fénétre de connexion // on ne ferme pas
              this.txtError.getScene().getWindow().hide();
              AnchorPane root = null;
              System.out.println(this.user.getRole());
              try {
                switch(this.user.getRole())
                  {
                    case "ROLE_PATIENT":
                        root = FXMLLoader.load(getClass().getResource("/view/patient/PatientProfile.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                        break;
                    case "ROLE_MEDECIN":
                        root = FXMLLoader.load(getClass().getResource("/view/medecin/MedecinProfile.fxml"));
                        Scene scene1 = new Scene(root);
                        Stage stage1 = new Stage();
                        stage1.setScene(scene1);
                        stage1.show();
                        break;
                    case "ROLE_SECRETAIRE":
                        root = FXMLLoader.load(getClass().getResource("/view/secretaire/SecretaireProfile.fxml"));
                        Scene scene2 = new Scene(root);
                        Stage stage2 = new Stage();
                        stage2.setScene(scene2);
                        stage2.show();
                        break;
                    case "ROLE_PRESTATAIRE":
                        root = FXMLLoader.load(getClass().getResource("/view/patient/PatientProfile.fxml"));
                        Scene scene3 = new Scene(root);
                        Stage stage3 = new Stage();
                        stage3.setScene(scene3);
                        stage3.show();
                        break;
                  }
                  
              } catch (IOException ex) {
                  Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
        }
    }

    @FXML
    private void handleIncriptionView(MouseEvent event) {
        //Cache la fénétre de connexion
        this.txtError.getScene().getWindow().hide();
        AnchorPane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/InscriptionView.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }
    
}
