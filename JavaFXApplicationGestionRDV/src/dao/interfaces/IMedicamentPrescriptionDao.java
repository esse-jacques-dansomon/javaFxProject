/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import entity.MedicamentPrescription;
import entity.Ordonnance;
import entity.Patient;
import java.util.List;

/**
 *
 * @author EsseJacquesDansomon
 */
public interface IMedicamentPrescriptionDao extends IDao<MedicamentPrescription> {
    public int CreatePrescriptions(List<MedicamentPrescription> arrayPrecriptions, Ordonnance ordonnance);
    public List<MedicamentPrescription> findAllPrescriptionOfPatient(Patient patient);
}
