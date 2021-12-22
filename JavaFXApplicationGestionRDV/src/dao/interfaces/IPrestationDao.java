/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import entity.Prestation;
import entity.User;
import java.util.List;

/**
 *
 * @author EsseJacquesDansomon
 */
public interface IPrestationDao extends IDao<Prestation> {
    public List<Prestation> findAllPrestationByPatientId(User patient);
    public List<Prestation> findAllPrestationByPatientIdAndStatus(User patient, String status);
    
}
