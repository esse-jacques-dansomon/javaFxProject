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
public class RendezVous {
    
   private int id;
   private Date date;
   private String heure;
   private String etat;
   private User patient;
   private TypeService typeService;

    public RendezVous() {
    }

    public RendezVous(java.sql.Date date, String heure, User patient, TypeService typeService) {
        this.date = date;
        this.heure = heure;
        this.patient = patient;
        this.typeService = typeService;
    }

    public RendezVous(int id, java.sql.Date date, String heure, User patient, String etat, TypeService typeService) {
        this.id = id;
        this.date = date;
        this.heure = heure;
        this.patient = patient;
        this.typeService = typeService;
        this.etat= etat;
    }


    @Override
    public String toString() {
        return "RendezVous{" + "id=" + id + ", date=" + date + ", heure=" + heure + ", patient=" + patient + ", typeService=" + typeService + '}';
    }

    
    public int getId() {return id;}

    public void setId(int id) { this.id = id;}

    public java.sql.Date getDate() { return date;}

    public void setDate(java.sql.Date date) { this.date = date;}

    public String getHeure() { return heure;}

    public void setHeure(String heure) { this.heure = heure; }
    
    public User getPatient() { return patient; }

    public void setPatient(User patient) { this.patient = patient; }

    public TypeService getTypeService() {  return typeService;}

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setTypeService(TypeService typeService) { this.typeService = typeService;}  
}
