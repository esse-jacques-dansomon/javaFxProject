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
public class TypeService {
    protected int id;
    protected String libelle;

    public TypeService() {
        
    }
    public TypeService(String libelle) {
        this.libelle = libelle;
    }
    public TypeService(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
    
    
}
