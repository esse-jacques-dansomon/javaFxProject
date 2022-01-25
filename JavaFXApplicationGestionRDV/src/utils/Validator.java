/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;

/**
 *
 * @author EsseJacquesDansomon
 */
public class Validator {
    //Tableau contenants les erruers du formaulaire
    private final ArrayList<String> arrayErreurs = new ArrayList<>();
    
    //foction veriufiant si un champ mail est value et 
    //affiche un message avec le nom du champ
    public  void isValidMail(String email,String champName)
    {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pat = Pattern.compile(emailRegex);
        if ( email == null || "".equals(email) || !pat.matcher(email).matches())
            this.arrayErreurs.add("Veuillez entrer un email pour le champ " + champName);
    }
    
    /**
     *@param tel
    *@see isValidMail
    * 
    */
    public void isValidTel(String tel, String champName)
    {
        String telRegex = "^(\\+|00)(221)(70|76|77|78|33)([0-9]{7})";
        Pattern pat = Pattern.compile(telRegex);
        if (tel == null || tel == "" || !pat.matcher(tel).matches())
             this.arrayErreurs.add("Veuillez entrer un numero valide pour le champ " + champName);
    }
    
    /**
     *@param string
     *@see isValidMail
    */
    public void isEmptyStrig(String string, String champName)
    {
        if (string == null || "".equals(string) )
            this.arrayErreurs.add("Veuillez entrer une valeur valide pour le champ " + champName);
    }
    

    /**
    * verified une collection de champ de champ (Valeur et nom du champ) 
    */
    public void isEmptyStrig(Map<String, String>  champs)
    {
        champs.keySet().forEach((key) -> {
            this.isEmptyStrig(champs.get(key), key);
        });
    }
    
    
    public void isEqualWIth(String value1, String value2, String message)
    {

      if( "".equals(value1))          
          this.arrayErreurs.add(message);
      else{
          if(!value1.equals(value2)) 
          this.arrayErreurs.add(message); 
      }

          
          
    }
     
    /*
    *verifie le formulaire contient une erreur
    */
    public boolean isValide(){
         return this.arrayErreurs.isEmpty();
    };
    
     /*
    *affiche les erreurs
    */
    public void showErrorsAlert() { 
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur dans les champs du formulaire");
        alert.setHeaderText("Erreurs");
        String allErrors = "";
        allErrors = this.arrayErreurs.stream().map((errorValue) -> errorValue + "\n").reduce(allErrors, String::concat);
        alert.setContentText(allErrors);
        alert.showAndWait();
    } 
}
