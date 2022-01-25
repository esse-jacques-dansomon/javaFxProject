/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import entity.RendezVous;
import entity.User;
import java.util.List;

/**
 *
 * @author EsseJacquesDansomon
 */
public interface IRendezVousDao extends IDao<RendezVous> {
    public List<RendezVous> findAllByPatientId(User patient);
    public List<RendezVous> findAllByEtat(String etat);
    public List<RendezVous> findAllByDate(String etat, String date);

}
