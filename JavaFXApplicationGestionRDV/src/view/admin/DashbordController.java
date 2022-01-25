/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.admin;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import entity.Medecin;
import entity.TypeService;
import entity.User;
import fabrique.Fabrique;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.IService;
import utils.Validator;
import utils.ViewService;
import view.controller.LoginController;

/**
 * FXML Controller class
 *
 * @author EsseJacquesDansomon
 */
public class DashbordController implements Initializable {
    
    private String serviceChosen = null;
    private final User userConnected = LoginController.getCtrl().getUser();
    private ObservableList<TypeService> typeServiceObservable;
    private final IService service = Fabrique.getInstanceService();
    private ObservableList<User> obvUsers;
    @FXML
    private Label textLabelNameUserConnected;
    @FXML
    private TextField textFieldnom;
    @FXML
    private TextField textFieldPrenoms;
    @FXML
    private TextField textFieldLogin;
    @FXML
    private PasswordField textFieldPassword;
    @FXML
    private PasswordField textFieldConfirnmedPassword;
    private ComboBox<TypeService> comboBoxTypeService;
    @FXML
    private Button staticRdvInTheDay;
    @FXML
    private TableView<User> tableViewUsers;
    @FXML
    private TableColumn<User, String> tblcName;
    @FXML
    private TableColumn<User, String> tblcPrenoms;
    @FXML
    private TableColumn<User, String> tblcRole;
    @FXML
    private TableColumn<User, String> tblcSpecialte;
    @FXML
    private FontAwesomeIcon statRdvDone;
    @FXML
    private Button statRdvCancel;
    @FXML
    private Button buttonAddUser;
    @FXML
    private ComboBox<String> comboBoxRole;
    @FXML
    private Button btnDeconnexion;
    @FXML
    private ComboBox<?> TypeSpecialite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadTableView();
        ViewService.loadComboBoxRole(comboBoxRole);
        textLabelNameUserConnected.setText(this.userConnected.getLogin());
    }    

    @FXML
    private void handleDeleteUserForm(ActionEvent event) {
    }

    @FXML
    private void handleSaveUserForm(ActionEvent event) {
        String nom = this.textFieldnom.getText();
        String prenoms = this.textFieldPrenoms.getText();
        String login = this.textFieldLogin.getText();
        String password = this.textFieldPassword.getText();
        String confirmedPassword = this.textFieldConfirnmedPassword.getText();
        //validation
        Validator validator = new Validator();
        validator.isEmptyStrig(nom, "Nom");
        validator.isEmptyStrig(prenoms, "prenoms");
        validator.isValidMail(login, "Nom");
        validator.isEqualWIth(password, confirmedPassword, "Mots de passe ne conrespondent pas");
        if(validator.isValide())
        {
           //Creation d'un user 
            if(this.serviceChosen != null)
            {
                Medecin medecin = new Medecin();
               // medecin.setTypeConsultation(comboBoxTypeService.getCellFactory());
                
            }
            
            this.handleResetUserForm(event);
        }else{
            validator.showErrorsAlert();
        }
        

        
        
    }

    @FXML
    private void handleUpdateUserForm(ActionEvent event) {
    }
    
    private void loadTableView()
    {
        List<User> secretaire = this.service.searchAllUserByRoleName("ROLE_SECRETAIRE");
        List<User> medecins = this.service.searchAllUserByRoleName("ROLE_MEDECIN");
        secretaire.addAll(medecins);
        this.obvUsers = FXCollections.observableArrayList(secretaire);
        this.tblcName.setCellValueFactory(new PropertyValueFactory<>("nom"));
        this.tblcPrenoms.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        this.tblcRole.setCellValueFactory(new PropertyValueFactory<>("role")); 
        this.tblcSpecialte.setCellValueFactory(new PropertyValueFactory<>("login"));
        this.tableViewUsers.setItems(obvUsers);
    }

    @FXML
    private void handleSelectedUser(MouseEvent event) {
        User user = this.tableViewUsers.getSelectionModel().getSelectedItem();
        if(user != null ){
            this.textFieldnom.setText(user.getNom());
            this.textFieldPrenoms.setText(user.getPrenom());
            this.textFieldLogin.setText(user.getLogin()); 
        }

        if("ROLE_MEDECIN".equals(user.getRole()))
        {
            this.comboBoxTypeService.setVisible(true);
        }
        else{
            this.comboBoxTypeService.setVisible(false);  
        }
        this.buttonAddUser.setDisable(true);
        this.textFieldPassword.setDisable(true);
        this.textFieldConfirnmedPassword.setDisable(true);
    }

    @FXML
    private void handleResetUserForm(ActionEvent event) {
        
        this.comboBoxTypeService.setVisible(true);
        this.buttonAddUser.setDisable(true);
        this.textFieldPassword.setDisable(false);
        this.textFieldConfirnmedPassword.setDisable(false);
        this.textFieldnom.clear();
        this.textFieldPrenoms.clear();
        this.textFieldLogin.clear();
        this.loadTypeService(comboBoxTypeService);

    }
    
    
    private void loadTypeService(ComboBox<TypeService> cbo) {
        if ("ROLE_MEDECIN".equals(this.serviceChosen.toLowerCase().trim())) {
            typeServiceObservable = FXCollections.observableArrayList(service.searchAllSpecialite());
        }if ("ROLE_RP".equals(this.serviceChosen.toLowerCase().trim()))
        {
            typeServiceObservable = FXCollections.observableArrayList(service.searchAllTypePrestation());
        }else{
            ///
        }
        cbo.setItems(this.typeServiceObservable);
    }

    @FXML
    private void handleChangeRole(ActionEvent event) {
        this.serviceChosen = this.comboBoxRole.getSelectionModel().getSelectedItem();
        loadTypeService(this.comboBoxTypeService);
    }

    @FXML
    private void handleLogOut(ActionEvent event) {
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
}
