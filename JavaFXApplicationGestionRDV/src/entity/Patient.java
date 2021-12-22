/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


/**
 *
 * @author EsseJacquesDansomon
 */
public class Patient extends User {

    private final String ROLE = "ROLE_PATIENT";
    private String code;
    private String antecedants;

    public Patient() {
    }

    /**
     * Fonction de Insert d'un patient
     * @param nom
     * @param prenom
     * @param login
     * @param password
     * @param code
     * @param antecedants 
     */
    public Patient(String nom, String prenom, String login, String password, String code, String antecedants) {
        super(nom, prenom, login, password);
        this.code = code;
        this.antecedants = antecedants;
        this.role = this.ROLE;
    }

    public Patient(int id, String nom, String prenom, String login, String password, String role, String code, String antecedants) {
        super(id, nom, prenom, login, password, role);
        this.code = code;
        this.antecedants = antecedants;
    }
    
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAntecedants() {
        return antecedants;
    }

    public void setAntecedants(String antecedants) {
        this.antecedants = antecedants;
    }  
}
