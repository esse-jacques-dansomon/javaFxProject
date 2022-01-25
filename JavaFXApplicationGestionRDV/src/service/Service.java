/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.interfaces.IConsultationDao;
import dao.interfaces.IMedicamentDao;
import dao.interfaces.IMedicamentPrescriptionDao;
import dao.interfaces.IOrdonnanceDao;
import dao.interfaces.IPatientDao;
import dao.interfaces.IPrestationDao;
import dao.interfaces.IRendezVousDao;
import dao.interfaces.ITypePrestationDao;
import dao.interfaces.IUserDao;
import entity.Patient;
import entity.TypeConsultation;
import entity.TypePrestation;
import entity.User;
import java.util.List;
import dao.interfaces.ITypeConsultationDao;
import entity.Consultation;
import entity.Medicament;
import entity.MedicamentPrescription;
import entity.Ordonnance;
import entity.Prestation;
import entity.RendezVous;

/**
 *
 * @author EsseJacquesDansomon
 */
public class Service implements IService {

    //couplsage faible
    private IUserDao userDao ;
    private IPatientDao patientDao ;
    private ITypeConsultationDao specialiteDao ;
    private ITypePrestationDao typePrestationDao;
    private IRendezVousDao rendezVousDao;
    private IPrestationDao prestationDao;
    private IConsultationDao consultationDao;
    private IOrdonnanceDao ordonnanceDao;
    private IMedicamentDao medicamentDao;
    private IMedicamentPrescriptionDao medicamentPrescriptionDao;

    //imjection de dependance
    /*
    public Service(IUserDao userDao, IPatientDao patientDao, 
            ITypeConsultationDao specialiteDao,ITypePrestationDao typePrestationDao, 
            IRendezVousDao rendezVousDao,  IPrestationDao prestationDao, IConsultationDao consultationDao,
            IOrdonnanceDao  ordonnanceDao, IMedicamentDao medicamentDao) {
        this.userDao = userDao;
        this.patientDao = patientDao;
        this.specialiteDao = specialiteDao;
        this.typePrestationDao = typePrestationDao;  
        this.rendezVousDao =rendezVousDao;
        this.prestationDao = prestationDao;
        this.consultationDao = consultationDao;
        this.ordonnanceDao = ordonnanceDao;
        this.medicamentDao = medicamentDao;

    }
*/
    public Service() {
    }
  
    //Setters
    public void setUserDao(IUserDao userDao) {this.userDao = userDao;}
    public void setPatientDao(IPatientDao patientDao) { this.patientDao = patientDao;}
    public void setSpecialiteDao(ITypeConsultationDao specialiteDao) {this.specialiteDao = specialiteDao;}
    public void setTypePrestationDao(ITypePrestationDao typePrestationDao) {this.typePrestationDao = typePrestationDao;}
    public void setRendezVousDao(IRendezVousDao rendezVousDao) {this.rendezVousDao = rendezVousDao;}
    public void setPrestationDao(IPrestationDao prestationDao) {this.prestationDao = prestationDao;}
    public void setConsultationDao(IConsultationDao consultationDao) { this.consultationDao = consultationDao;}
    public void setOrdonnanceDao(IOrdonnanceDao ordonnanceDao) {this.ordonnanceDao = ordonnanceDao;}
    public void setMedicamentDao(IMedicamentDao medicamentDao) {
        this.medicamentDao = medicamentDao;
    }

    public void setMedicamentPrescriptionDao(IMedicamentPrescriptionDao medicamentPrescriptionDao) {
        this.medicamentPrescriptionDao = medicamentPrescriptionDao;
    }
    
    
//PACKAGE SECURITE
    @Override
    public User login(String login, String password) {
        return this.userDao.findUserByLoginAndPassword(login, password);
    }

    //DEBUT PACKAGE DU PATIENT 
    @Override
    public int createPatient(Patient patient) {
        return this.patientDao.insert(patient);
    }

    @Override
    public List<TypePrestation> searchAllTypePrestation() {
        return this.typePrestationDao.findAll();
    }

    @Override
    public List<TypeConsultation> searchAllSpecialite() {
        return this.specialiteDao.findAll();
    }

    @Override
    public int createRendezVous(RendezVous rendezVous) {
        return rendezVousDao.insert(rendezVous);
    }

    @Override
    public List<RendezVous> searchAllRdvByPatient(User patient) {
        return this.rendezVousDao.findAllByPatientId(patient);
    }

    @Override
    public List<Prestation> searchAllPrestationByPatient(User patient) {
        return this.prestationDao.findAllPrestationByPatientId(patient);
    }

    @Override
    public List<Consultation> searchAllConsultationByPatient(User patient) {
        return this.consultationDao.findAllConsultationByPatient(patient);
    }

    @Override
    public List<Ordonnance> searchAllOrdonnanceByPatient(User patient) {
        return this.ordonnanceDao.findAllOrdonnanceByPatient(patient);
    }

    @Override
    public List<Medicament> searchAllMedicamentByPatient(int id_ordonnance) {
        return this.medicamentDao.findAllMedicamendByOrdonnance(id_ordonnance);
    }
     //*PACKAGE DU SECRETAIRE*//
    @Override
    public List<RendezVous> searchAllRdvByEtat(String etat) {
        return this.rendezVousDao.findAllByEtat(etat);
    }

    @Override
    public List<User> searchAllUserByTypeService(int id) {
        return this.userDao.findUserByTypeService(id);
    }

    @Override
    public List<User> searchAllUserByRoleName(String role) {
        return this.userDao.findUserByRoleName(role);
    }

    @Override
    public int ValiderConsultation(Consultation consultation) {
           return this.consultationDao.insert(consultation);
    }

    @Override
    public int ValiderPrestation(Prestation prestation) {
        return this.prestationDao.insert(prestation);
    }

    @Override
    public int UpdateRendezVous(RendezVous rendezvous) {
        return this.rendezVousDao.update(rendezvous);
    }

    @Override
    public List<Consultation> searchAllConsultation() {
        return this.consultationDao.findAll();
    }

    @Override
    public List<Prestation> searchAllPrestation() {
        return this.prestationDao.findAll();
    }

    @Override
    public List<Consultation> searchAllConsultationByMedecin(User medecin) {
        return this.consultationDao.findAllConsultationByMedecin(medecin);
    }

    @Override
    public int updateConsultation(Consultation consultation) {
        return this.consultationDao.update(consultation);
    }

    @Override
    public Medicament searchMedicamentByCode(String codeMedicamentSearch) {
        return this.medicamentDao.findByCode(codeMedicamentSearch);
    }

    @Override
    public int addMedicament(Medicament medicament) {
      return this.medicamentDao.insert(medicament);
              }

    @Override
    public int CreateOrdonnance(Ordonnance odonnance) {
        return this.ordonnanceDao.insert(odonnance);
    }

    @Override
    public int CreatePrescriptionMedicament(List<MedicamentPrescription> prescriptions,Ordonnance ordonnance) {
          return this.medicamentPrescriptionDao.CreatePrescriptions(prescriptions, ordonnance);
    }

    @Override
    public Patient findPatientByCode(String code) {
       return this.patientDao.findPatientByCode(code);
    }

    @Override
    public List<Consultation> searchConsultationByPatientAndBySatut(User patient, String status) {
        return this.consultationDao.findAllConsultationByPatientAndByEtat(patient, status);
    }

    @Override
    public List<Prestation> searchPrestationByPatientAndBySatut(User patient, String status) {
        return this.prestationDao.findAllPrestationByPatientIdAndStatus(patient, status);
    }

    @Override
    public List<MedicamentPrescription> searchAllPrecriptionForPatient(Patient patient) {
            return this.medicamentPrescriptionDao.findAllPrescriptionOfPatient(patient);
    }

    @Override
    public List<RendezVous> searchAllRdvByEtatAndDate(String etat, String date) {
        return this.rendezVousDao.findAllByDate(etat, date);
    }


}




