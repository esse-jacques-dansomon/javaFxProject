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
public class Ordonnance {
    private int id;
    private Consultation consultation ;

    public Ordonnance() {
    }
    public Ordonnance(int id) {
         this.id = id;
    }

    public Ordonnance(int id, Consultation consultation) {
        this.id = id;
        this.consultation = consultation;
    }

    public Ordonnance( Consultation consultation) {
        this.consultation = consultation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }


    @Override
    public String toString() {
        return "Ordonnance du " + this.consultation.getDate() + " " ;
    }

    
    
    
}