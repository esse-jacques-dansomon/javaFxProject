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
public class MedicamentPrescription {
    private int id;
    private String posologie;
    private Ordonnance ordonnance;
    private Medicament medicament;

    public MedicamentPrescription() {
    }
    public MedicamentPrescription(String posologie, Medicament medicament) {
        this.posologie = posologie;
        this.medicament = medicament;
    }
    public MedicamentPrescription(String posologie, Ordonnance ordonnance, Medicament medicament) {
        this.posologie = posologie;
        this.ordonnance = ordonnance;
        this.medicament = medicament;
    }

    public MedicamentPrescription(int id, String posologie, Ordonnance ordonnance, Medicament medicament) {
        this.id = id;
        this.posologie = posologie;
        this.ordonnance = ordonnance;
        this.medicament = medicament;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosologie() {
        return posologie;
    }

    public void setPosologie(String posologie) {
        this.posologie = posologie;
    }

    public Ordonnance getOrdonnance() {
        return ordonnance;
    }

    public void setOrdonnance(Ordonnance ordonnance) {
        this.ordonnance = ordonnance;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }
    
    
    
}
