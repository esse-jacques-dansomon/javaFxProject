/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import java.util.Date;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
/**
 *
 * @author EsseJacquesDansomon
 */
public class ViewService {
    
    
    public static void  loadALert(AlertType alerttype, String title, String content)
    {
        Alert alert = new Alert(alerttype);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }
    
    public static void loadComboBoxService(ComboBox<String> comboBoxService){
        comboBoxService.getItems().add("Consultation");
        comboBoxService.getItems().add("Prestation");
        comboBoxService.getSelectionModel().selectFirst();
    }
    
    public static void loadComboBoxRole(ComboBox<String> comboBoxService){
        comboBoxService.getItems().add("ROLE_SECRETAIRE");
        comboBoxService.getItems().add("ROLE_MEDECIN");
        comboBoxService.getItems().add("ROLE_RP");
        comboBoxService.getItems().add("ROLE_ADMIN");
        comboBoxService.getSelectionModel().selectFirst();
    }
    

    
    public static java.sql.Date convertFromJAVADateToSQLDate(
            java.util.Date javaDate) {
        java.sql.Date sqlDate = null;
        if (javaDate != null) {
            sqlDate = (java.sql.Date) new Date(javaDate.getTime());
        }
        return sqlDate;
    }
}
