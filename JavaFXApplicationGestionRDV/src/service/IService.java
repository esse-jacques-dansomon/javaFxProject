/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Consultation;
import entity.Medicament;
import entity.MedicamentPrescription;
import entity.Ordonnance;
import entity.Patient;
import entity.Prestation;
import entity.RendezVous;
import entity.TypeConsultation;
import entity.TypePrestation;
import entity.User;
import java.util.List;

/**
 *
 * @author EsseJacquesDansomon
 */
public interface IService {
    
    //Fonctions du package securité
    public User login(String login,String password);
    
    //Fonction du package de l'acteur Patient
    public int createPatient(Patient patient);
    public int createRendezVous(RendezVous rendezVous);
    public List<RendezVous> searchAllRdvByPatient(User patient);
    public List<Prestation> searchAllPrestationByPatient(User patient);
    public List<Consultation> searchAllConsultationByPatient(User patient);
    public List<Ordonnance> searchAllOrdonnanceByPatient(User patient);
    public List<Medicament> searchAllMedicamentByPatient(int id_ordonnance);


    //Fonction du package de l'acteur Secretaire
    public List<RendezVous> searchAllRdvByEtat(String etat);
     public List<RendezVous> searchAllRdvByEtatAndDate(String etat, String date);
    public List<User> searchAllUserByTypeService(int id);
    public List<User> searchAllUserByRoleName(String role);
    public int ValiderConsultation(Consultation consultation);
    public int ValiderPrestation(Prestation prestation);
    public int UpdateRendezVous(RendezVous rendezvous);
    public List<Consultation> searchAllConsultation();
    public List<Prestation> searchAllPrestation();
    
    
    //Fomnction du package de l'acteur Medecin
    public List<Consultation> searchAllConsultationByMedecin(User medecin);
    public int updateConsultation(Consultation consultation);
    public int addMedicament(Medicament medicament);
    public Patient findPatientByCode(String code);
    public List<Consultation> searchConsultationByPatientAndBySatut(User patient, String status);
    public List<Prestation> searchPrestationByPatientAndBySatut(User patient, String status);
    public List<MedicamentPrescription> searchAllPrecriptionForPatient(Patient patient);
    //Fonctions sur les affcihes des services 
    public List<TypePrestation> searchAllTypePrestation();
    public List<TypeConsultation> searchAllSpecialite();
    public Medicament searchMedicamentByCode(String codeMedicamentSearch);
    public int CreateOrdonnance(Ordonnance odonnance);
    public int CreatePrescriptionMedicament(List<MedicamentPrescription> prescriptions, Ordonnance ordonnance);
    
        
}
