/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import entity.Ordonnance;
import entity.User;
import java.util.List;

/**
 *
 * @author EsseJacquesDansomon
 */
public interface IOrdonnanceDao extends IDao<Ordonnance> {
    public List<Ordonnance>  findAllOrdonnanceByPatient(User patient);
}
