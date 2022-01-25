/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.interfaces.IConsultationDao;
import entity.Consultation;
import entity.Medecin;
import entity.TypeConsultation;
import entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author EsseJacquesDansomon
 */
public class ConsultationDao extends DataBase implements IConsultationDao {

    @Override
    public int insert(Consultation consultation) {
        int id_consultation = 0;
        try {
            String SQL_INSERT = "INSERT INTO `consultation`( `statut`, `date`, `patient_id`, `medecin_id`, `heure`) VALUES ( ?, ?, ?, ?, ?)";
            this.openConnexion();
            initPrepareStatement(SQL_INSERT);
            getPs().setString(1, consultation.getStatut());
            getPs().setDate(2, consultation.getDate());
            getPs().setInt(3, consultation.getPatient().getId());
            getPs().setInt(4, consultation.getMedecin().getId());
            getPs().setString(5, consultation.getHeure());
            executeUpdate(SQL_INSERT);
            ResultSet rs = getPs().getGeneratedKeys();
            if(rs.next())
                id_consultation = rs.getInt(1);
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            this.closeConnexion();
        }
        return id_consultation;

    }

    @Override
    public int update(Consultation consultation) {
        String SQL_UPDATE = 
         "UPDATE `consultation` SET `statut`  = ?, constantes  = ? WHERE id_consultation = ?";
        int id_consultation = 0;
        try {
            this.openConnexion();
            this.initPrepareStatement(SQL_UPDATE);
            this.getPs().setString(1, consultation.getStatut());
            this.getPs().setInt(3, consultation.getId());
            this.getPs().setString(2, consultation.getConstantes());
            id_consultation = executeUpdate(SQL_UPDATE);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            this.closeConnexion();
        }
        return id_consultation;
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Consultation> findAll() {
        List<Consultation> arrayConsultationPatient = new ArrayList<>();
        try {
            String sql = 
                    "SELECT * FROM consultation";
            this.openConnexion();
            this.initPrepareStatement(sql);
            ResultSet rs = this.executeSelect(sql);
            while(rs.next())
            {
                Consultation consultation= new Consultation();
                consultation.setId(rs.getInt("id_consultation"));
                consultation.setDate(rs.getDate("date"));
                consultation.setHeure(rs.getString("heure"));
                consultation.setStatut(rs.getString("statut"));
                consultation.setConstantes(rs.getString("constantes"));
                consultation.setPatient(new UserDao().findById(rs.getInt("patient_id")));
                Medecin medecin =  new MedecinDao().findById(rs.getInt("medecin_id"));              
                consultation.setMedecin(medecin);
                consultation.setTypeConsultation(medecin.getTypeConsultation());
                arrayConsultationPatient.add(consultation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
         this.closeConnexion();
        }
        return arrayConsultationPatient;
    }

    @Override
    public Consultation findById(int id) {
        return this.findAll().stream()
         .filter(cl ->cl.getId() == id )
          .findAny()
           .get()   ;
    }

    @Override
    public List<Consultation> findAllConsultationByPatient(User patient) {
       List<Consultation> arrayConsultationPatient = new ArrayList<>();

        try {
            String sql = "SELECT * FROM consultation c, user u , "
                    + " type_consultation t  WHERE u.id = c.medecin_id AND "
                    + " u.type_consultation_id=t.id_type_consultation "
                    + "AND c.patient_id = ? ";
            this.openConnexion();
            this.initPrepareStatement(sql);
            this.getPs().setInt(1, patient.getId());
            ResultSet rs = this.executeSelect(sql);
            while(rs.next())
            {
                Consultation consultation= new Consultation();
                
                consultation.setId(rs.getInt("id_consultation"));
                consultation.setDate(rs.getDate("date"));
                consultation.setStatut(rs.getString("statut"));
                consultation.setConstantes(rs.getString("constantes"));
                consultation.setPatient(patient);
                
                //Construction du typeConsultation  pour le medecin
                TypeConsultation typeConsultation = new TypeConsultation();
                typeConsultation.setId(rs.getInt("id_type_consultation"));
                typeConsultation.setLibelle(rs.getString("libelle"));
                //Construction du medecin
                Medecin medecin = new Medecin();
                
                medecin.setId(rs.getInt("id"));
                medecin.setLogin(rs.getString("login"));
                medecin.setNom(rs.getString("nom"));
                medecin.setPrenom(rs.getString("prenom"));
                medecin.setTypeConsultation(typeConsultation);
                consultation.setTypeConsultation(typeConsultation);
                consultation.setMedecin(medecin);
                
                arrayConsultationPatient.add(consultation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
         this.closeConnexion();
        }
        return arrayConsultationPatient;
    }

    @Override
    public List<Consultation> findAllConsultationByEtat(String etat) {
         List<Consultation> arrayConsultationPatient = new ArrayList<>();

        try {
            String sql = "SELECT * FROM consultation  WHERE statut = ? ";
            this.openConnexion();
            this.initPrepareStatement(sql);
            this.getPs().setString(1, etat);
            ResultSet rs = this.executeSelect(sql);
            while(rs.next())
            {
                Consultation consultation= new Consultation();
                
                consultation.setId(rs.getInt("id_consultation"));
                consultation.setDate(rs.getDate("date"));
                consultation.setStatut(rs.getString("statut"));
                consultation.setConstantes(rs.getString("constantes"));
                consultation.setPatient(new UserDao().findById(rs.getInt("patient_id")));
                Medecin medecin = (Medecin) new UserDao().findById(rs.getInt("patient_id"));
                consultation.setMedecin(medecin); 
                arrayConsultationPatient.add(consultation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
         this.closeConnexion();
        }
        return arrayConsultationPatient;
    }

    @Override
    public List<Consultation> findAllConsultationByMedecin(User mdecin) {        
        return this.findAll().stream()
                .filter( consultation -> 
                        consultation.getMedecin().getId() == mdecin.getId() &&
                                "EN_COURS".equals(consultation.getStatut())
                                )
                .collect(Collectors.toList()); 
    }

    @Override
    public List<Consultation> findAllConsultationByPatientAndByEtat(User patient, String etat) {
        return this.findAllConsultationByPatient(patient)
                .stream()
                .filter(cl -> cl.getStatut().equals(etat))
                .collect(Collectors.toList());
    }
    
    
}
