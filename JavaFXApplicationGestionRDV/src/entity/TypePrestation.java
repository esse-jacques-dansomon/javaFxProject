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
public class TypePrestation extends TypeService {
   


    public TypePrestation() {
    }

    /**
     * Constructeur de Insert
     * @param libelle 
     */
    public TypePrestation(String libelle) {
        super(libelle);
    }

        /**
     * Constructeur de Select|Update
     * @param id
     * @param libelle 
     */
    public TypePrestation(int id, String libelle) {
        super(id, libelle);
    }   
}
