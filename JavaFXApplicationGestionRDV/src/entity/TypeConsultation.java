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
public class TypeConsultation extends TypeService {


    public TypeConsultation() {
    }

    /**
     * Constructeur de Insert
     * @param libelle 
     */
    public TypeConsultation(String libelle) {
        super(libelle);
    }

    /**
     * Constructeur de Select|Update
     * @param id
     * @param libelle 
     */
    public TypeConsultation(int id, String libelle) {
        super(id, libelle);
    }

  
    
}
