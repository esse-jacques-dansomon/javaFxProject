/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entity.Consultation;
import entity.Medecin;
import entity.Prestation;
import entity.TypeService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EsseJacquesDansomon
 */
public class DossierMedicalDto {
    private int id;
    private java.sql.Date date;
    private String constantes;
    private String service;
    private TypeService typeService;
    private Medecin medecin;
    

    public DossierMedicalDto() {
    }

    public DossierMedicalDto(int id, Date date, String constantes, TypeService typeService, Medecin medecin) {
        this.id = id;
        this.date = date;
        this.constantes = constantes;
        this.typeService = typeService;
        this.medecin = medecin;
    }

    public int getId() { return id;  }
    public void setId(int id) { this.id = id;}

    public String getConstantes() { return constantes;}
    public void setConstantes(String constantes) {this.constantes = constantes;}

    public TypeService getTypeService() { return typeService; }
    public void setTypeService(TypeService typeService) { this.typeService = typeService; }

    public Medecin getMedecin() {return medecin;  }
    public void setMedecin(Medecin medecin) {this.medecin = medecin;}

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
    private DossierMedicalDto ConsultationToDM(Consultation consultation){
        DossierMedicalDto dm = new DossierMedicalDto();
        dm.setId(consultation.getId());
        dm.constantes = consultation.getConstantes();
        dm.date = consultation.getDate();
        dm.typeService = consultation.getTypeConsultation();
        dm.medecin = consultation.getMedecin();
        dm.service = consultation.getTypeConsultation().getLibelle();
        return dm;
    }
    
     private DossierMedicalDto PrestationToDM(Prestation prestation){
        DossierMedicalDto dm = new DossierMedicalDto();
        dm.setId(prestation.getId());
        dm.constantes = prestation.getResultats();
        dm.date = prestation.getDate();
        dm.typeService = prestation.getTypePrestation();
        dm.medecin = new Medecin("Responsable prestation");
        dm.service = prestation.getTypePrestation().getLibelle();
        return dm;
    }
     
     
    public List<DossierMedicalDto> PrestationsToDM(List<Prestation> prestations){
        List<DossierMedicalDto>  dms = new ArrayList<>();
        prestations.forEach((Prestation ps) -> {
            dms.add(DossierMedicalDto.this.PrestationToDM(ps));
        });
        return dms;
    }
    public List<DossierMedicalDto> ConsultationsToDM(List<Consultation> consultations){
        List<DossierMedicalDto>  dms = new ArrayList<>();
        consultations.forEach((Consultation cl) -> {
            dms.add(DossierMedicalDto.this.ConsultationToDM(cl));
        });
        return dms;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
    
    
}
