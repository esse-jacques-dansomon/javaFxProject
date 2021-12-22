/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import entity.Consultation;
import entity.User;
import java.util.List;

/**
 *
 * @author EsseJacquesDansomon
 */
public interface IConsultationDao extends IDao<Consultation> {
    List<Consultation> findAllConsultationByPatient(User patient);
    List<Consultation> findAllConsultationByPatientAndByEtat(User patient, String etat);
    List<Consultation> findAllConsultationByMedecin(User mdecin);
    List<Consultation> findAllConsultationByEtat(String etat);
}
