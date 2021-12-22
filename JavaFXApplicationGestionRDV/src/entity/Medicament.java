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
public class Medicament {
    private int id;
    private String nom; 
    private String code;

    public Medicament() {
    }

    public Medicament(String nom, String code) {
        this.nom = nom;
        this.code = code;
    }
    


    public Medicament(int id, String nom, String code) {
        this.id = id;
        this.nom = nom;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
    
}
