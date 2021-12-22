/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author EsseJacquesDansomon
 */
public class Consultation {
    private int id;
    private Date date;
    private String heure;
    private String statut;
    private String constantes;
    private User patient; 
    private Medecin medecin;
    private TypeConsultation typeConsultation;

    public Consultation() {
        
    }

    public Consultation(Date date, String statut, String constantes, User patient, Medecin medecin) {
        this.date = date;
        this.statut = statut;
        this.constantes = constantes;
        this.patient = patient;
        this.medecin = medecin;      
    }

    public Consultation(int id, Date date, String statut, String constantes, User patient, Medecin medecin ) {
        this.id = id;
        this.date = date;
        this.statut = statut;
        this.constantes = constantes;
        this.patient = patient;
        this.medecin = medecin;
    }

    
    
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public Date getDate() {return date;}
    public void setDate(Date date) {this.date = date;}

    public String getStatut() {return statut;}
    public void setStatut(String statut) {this.statut = statut;}

    public String getConstantes() {return constantes;}
    public void setConstantes(String constantes) {this.constantes = constantes;}

    public User getPatient() {return patient;}
    public void setPatient(User patient) {this.patient = patient;}

    public Medecin getMedecin() {return medecin;}
    public void setMedecin(Medecin medecin) {this.medecin = medecin;}

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    
    
    @Override
    public String toString() {
        return "Consultation{" + "date=" + date + ", statut=" + statut + ", patient=" + patient + ", medecin=" + medecin + '}';
    }

    public TypeConsultation getTypeConsultation() {
        return typeConsultation;
    }

    public void setTypeConsultation(TypeConsultation typeConsultation) {
        this.typeConsultation = typeConsultation;
    }
    
    
   
}


