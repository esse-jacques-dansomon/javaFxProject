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
public class Medecin extends User {
    private TypeConsultation typeConsultation;

    public Medecin(String responsable_prestation) {
        this.nom = responsable_prestation;
    }

    public TypeConsultation getTypeConsultation() {
        return typeConsultation;
    }

    public void setTypeConsultation(TypeConsultation typeConsulation) {
        this.typeConsultation = typeConsulation;
    }

    public Medecin() {
        this.role = "ROLE_MEDECIN";

    }

    public Medecin( int id, String nom, String prenom, String login, String password, String role, TypeConsultation typeConsulation) {
        super(id, nom, prenom, login, password, role);
        this.typeConsultation = typeConsulation;
    }

    public Medecin( String nom, String prenom, String login, String password, TypeConsultation typeConsulation) {
        super( nom, prenom, login, password);
        this.typeConsultation = typeConsulation;
        this.role = "ROLE_MEDECIN";
    }
  
}
