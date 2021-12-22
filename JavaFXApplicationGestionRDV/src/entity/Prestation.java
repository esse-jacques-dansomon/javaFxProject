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
public class Prestation {
    private int id;
    private Date date; 
    private String heure;
    private String statut;
    private String resultats;
    private TypePrestation typePrestation;
    private User patient;

    public Prestation() {
    }

    public Prestation(Date date, String statut, String resultats, TypePrestation typePrestation, User patient) {
        this.date = date;
        this.statut = statut;
        this.resultats = resultats;
        this.typePrestation = typePrestation;
        this.patient = patient;
    }

    public Prestation(int id, Date date, String statut, String resultats, TypePrestation typePrestation, User patient) {
        this.id = id;
        this.date = date;
        this.statut = statut;
        this.resultats = resultats;
        this.typePrestation = typePrestation;
        this.patient = patient;
    }

    public int getId() {return id;    }
    public void setId(int id) {this.id = id; }

    public Date getDate() {return date;  }
    public void setDate(Date date) {this.date = date; }

    public String getStatut() {return statut; }
    public void setStatut(String statut) {this.statut = statut;    }

    public String getResultats() { return resultats;   }
    public void setResultats(String resultats) { this.resultats = resultats;}

    public TypePrestation getTypePrestation() {return typePrestation;    }
    public void setTypePrestation(TypePrestation typePrestation) {this.typePrestation = typePrestation;}

    public User getPatient() {return patient;    }
    public void setPatient(User patient) { this.patient = patient;    }  

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    @Override
    public String toString() {
        return "Prestation{" + "id=" + id + ", date=" + date + ", statut=" + statut + ", resultats=" + resultats + ", typePrestation=" + typePrestation + ", patient=" + patient + '}';
    }
    
    
}
