/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Consultation;
import entity.Ordonnance;
import entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dao.interfaces.IOrdonnanceDao;

/**
 *
 * @author EsseJacquesDansomon
 */
public class OrdonnanceDao extends DataBase implements IOrdonnanceDao {

    
    @Override
    public int insert(Ordonnance ordonnance) {
        String SQL_INSERT = "INSERT INTO `ordonnance`(`id_ordonnance`, `consultation_id`) VALUES (?,?)";
    int id_ordonnance = 0;
        try {
            this.openConnexion();
            initPrepareStatement(SQL_INSERT);
            getPs().setInt(1, ordonnance.getId());
            getPs().setInt(2, ordonnance.getConsultation().getId());
            executeUpdate(SQL_INSERT);
            ResultSet rs = getPs().getGeneratedKeys();
            if(rs.next())
                id_ordonnance = rs.getInt(1);
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(OrdonnanceDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            this.closeConnexion();
        }
        return id_ordonnance;
    }

    @Override
    public int update(Ordonnance obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ordonnance> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ordonnance findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ordonnance>  findAllOrdonnanceByPatient(User patient) {
        List<Ordonnance>  ordonnances = new ArrayList<>() ;
        try {
            String sql = "SELECT * FROM `ordonnance` o, consultation c "
                    + " WHERE o.consultation_id = c.id_consultation AND c.patient_id= ? ";
            this.openConnexion();
            this.initPrepareStatement(sql);
            this.getPs().setInt(1, patient.getId());
            ResultSet rs = this.executeSelect(sql);
            while(rs.next())
            {
                Ordonnance ordonnance = new Ordonnance();
                ordonnance.setId(rs.getInt("id_ordonnance"));
                
                //COntruction de la consultation
                Consultation consultation = new ConsultationDao().findById(rs.getInt("consultation_id"));
              
                //add consultation a ordonnance
                ordonnance.setConsultation(consultation);
                
                //add ordonnance to array
                ordonnances.add(ordonnance);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdonnanceDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            this.closeConnexion();
        }
        return ordonnances;
    }
    
}
