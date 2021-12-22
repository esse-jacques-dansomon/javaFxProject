/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entity.Medecin;
import fabrique.Fabrique;
import service.IService;

/**
 *
 * @author EsseJacquesDansomon
 */
public class MainTest {
    
    public static void main (String[] args){
       final IService service = Fabrique.getInstanceService();
       Medecin  medecin = new Medecin();
       medecin.setId(1);
       service.searchAllConsultationByMedecin(medecin).forEach(
              cl ->  System.out.println(cl));

   }
    
}
