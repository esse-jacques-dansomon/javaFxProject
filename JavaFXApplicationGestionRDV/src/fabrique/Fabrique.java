/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabrique;

import dao.ConsultationDao;
import dao.MedicamentDao;
import dao.MedicamentPrescriptionDao;
import dao.OrdonnanceDao;
import dao.PatientDao;
import dao.PrestationDao;
import dao.RendezVousDao;
import dao.TypeConsultationDao;
import dao.TypePrestationDao;
import dao.UserDao;
import service.IService;
import service.Service;

/**
 *
 * @author EsseJacquesDansomon
 */
public class  Fabrique {
    
        public static IService getInstanceService()
        {     
            Service  service=  new Service();
            service.setUserDao(new UserDao());
            service.setPatientDao(new PatientDao());
            service.setSpecialiteDao(new TypeConsultationDao());
            service.setTypePrestationDao(new TypePrestationDao());
            service.setRendezVousDao(new RendezVousDao());
            service.setPrestationDao(new PrestationDao());
            service.setConsultationDao(new ConsultationDao());
            service.setOrdonnanceDao(new OrdonnanceDao());
            service.setMedicamentDao(new MedicamentDao());
            service.setMedicamentPrescriptionDao(new MedicamentPrescriptionDao());

            return service;   
        }
}
