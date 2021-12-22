/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.interfaces.IRendezVousDao;
import entity.Patient;
import entity.RendezVous;
import entity.TypeConsultation;
import entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EsseJacquesDansomon
 */
public class RendezVousDao extends DataBase implements IRendezVousDao { 

    @Override
    public int insert(RendezVous rendezVous) {
        String SQL_INSERT_PRE = "INSERT INTO rendez_vous "
                + " ( date, heure, patient_id, type_prestation_id) "
                + "VALUES ( ?, ? , ?, ? ) ";
        String SQL_INSERT_CO = "INSERT INTO rendez_vous "
                + " ( date, heure, patient_id, type_consultation_id) "
                + "VALUES ( ?, ?, ?, ? ) ";
        String SQL_INSERT = null;
        int idRendezVous = 0 ;
        
        try {
            openConnexion();
            
            if(rendezVous.getTypeService()  instanceof TypeConsultation )
                SQL_INSERT = SQL_INSERT_CO;
            else
                SQL_INSERT = SQL_INSERT_PRE;  
                 
            initPrepareStatement(SQL_INSERT);
           
            getPs().setDate(1, rendezVous.getDate());
            getPs().setString(2, rendezVous.getHeure());
            getPs().setInt(3, rendezVous.getPatient().getId());
            getPs().setInt(4, rendezVous.getTypeService().getId());
           
            executeUpdate(SQL_INSERT);
            System.out.println("Requette Insert reussite ");
            ResultSet rs = getPs().getGeneratedKeys();
            System.out.println(rs);
            if(rs.next())
            {
                idRendezVous = rs.getInt(1);
                System.out.println("Id Du rendezVous " + idRendezVous);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnexion();   
        }
        return idRendezVous;
    }

    @Override
    public int update(RendezVous rendezVous) {
        int id_rdv = 0;
        try {
            String SQL_UPDATE = "UPDATE `rendez_vous` SET etat = ? WHERE id =? ";
            this.openConnexion();
            this.initPrepareStatement(SQL_UPDATE);
            this.getPs().setString(1, rendezVous.getEtat());
            this.getPs().setInt(2, rendezVous.getId());
            executeUpdate(SQL_UPDATE);
            System.out.println("Requette Insert reussite ");
            ResultSet rs = getPs().getGeneratedKeys();
            if(rs.next())
                id_rdv = rs.getInt(1);
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            this.closeConnexion();
        }
        return id_rdv;
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RendezVous> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RendezVous findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RendezVous> findAllByPatientId(User patient) {
        List<RendezVous> rendezVousListes = new ArrayList();
        try {
            String sql = "SELECT * FROM `rendez_vous` WHERE patient_id = ? ";
            this.openConnexion();
            this.initPrepareStatement(sql);
            this.getPs().setInt(1, patient.getId());
            ResultSet rs = this.executeSelect(sql);
            //Passage Relation en Object RDV
            while(rs.next())
            {
                RendezVous rendezVous = new RendezVous();
                rendezVous.setId(rs.getInt("id"));
                rendezVous.setDate(rs.getDate("date"));
                rendezVous.setHeure(rs.getString("heure"));
                rendezVous.setEtat(rs.getString("etat"));
                rendezVous.setPatient(patient);
                int type_consultation_id = rs.getInt("type_consultation_id");
                int type_prestation_id = rs.getInt("type_prestation_id");
                if( type_consultation_id != 0 )
                    rendezVous.setTypeService(new TypeConsultationDao().findById(type_consultation_id));
                else
                    rendezVous.setTypeService(new TypePrestationDao().findById(type_prestation_id));
                
                rendezVousListes.add(rendezVous);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            this.closeConnexion();
        }
        return rendezVousListes;
    }

    @Override
    public List<RendezVous> findAllByEtat(String etat) {
        List<RendezVous> rendezVousListes = new ArrayList();
        try {
            String sql = "SELECT * FROM `rendez_vous` r, user u WHERE r.patient_id = u.id AND r.etat = ?  ";
            this.openConnexion();
            this.initPrepareStatement(sql);
            this.getPs().setString(1, etat.toUpperCase());
            ResultSet rs = this.executeSelect(sql);
            //Passage Relation en Object RDV
            while(rs.next())
            {
                RendezVous rendezVous = new RendezVous();
                rendezVous.setId(rs.getInt(1));
                rendezVous.setDate(rs.getDate("date"));
                rendezVous.setHeure(rs.getString("heure"));
                rendezVous.setEtat(rs.getString("etat"));
                //Recuperation du Patient
                Patient patient = new Patient();
                patient.setId(rs.getInt("patient_id"));
                patient.setNom(rs.getString("nom"));
                patient.setPrenom(rs.getString("prenom"));
                //Recuperation du type de service
                rendezVous.setPatient(patient);
                
                
                int type_consultation_id = rs.getInt("type_consultation_id");
                int type_prestation_id = rs.getInt("type_prestation_id");
                if( type_consultation_id != 0 )
                    rendezVous.setTypeService(new TypeConsultationDao().findById(type_consultation_id));
                else
                    rendezVous.setTypeService(new TypePrestationDao().findById(type_prestation_id));
                
                rendezVousListes.add(rendezVous);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            this.closeConnexion();
        }
        return rendezVousListes;
    }
    
}
