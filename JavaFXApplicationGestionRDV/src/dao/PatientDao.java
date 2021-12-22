/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.interfaces.IPatientDao;
import entity.Medecin;
import entity.Patient;
import entity.TypeConsultation;
import entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EsseJacquesDansomon
 */
public class PatientDao extends DataBase implements IPatientDao {

    /**
     *
     * @param patient
     * @return
     */
    @Override
    public int insert(Patient patient) {
        int idPatient = 0;
        String SQL_INSERT = "insert into `user` "
                + " ( `login`, `password`, `nom`, `prenom`, `code`, `antecedents`, `role`) "
                + " VALUES ( ?, ?, ?, ?, ?, ?, ? ) ";
        try {
            openConnexion();

            initPrepareStatement(SQL_INSERT);

            getPs().setString(1, patient.getLogin());
            getPs().setString(2, patient.getPassword());
            getPs().setString(3, patient.getNom());
            getPs().setString(4, patient.getPrenom());
            getPs().setString(5, patient.getCode());
            getPs().setString(6, patient.getAntecedants());
            getPs().setString(7, patient.getRole());

            executeUpdate(SQL_INSERT);

            ResultSet rs = getPs().getGeneratedKeys();
            System.out.println(rs);
            if (rs.next()) {
                idPatient = rs.getInt(1);
                System.out.println("Id Du patient " + idPatient);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnexion();
        }
        return idPatient;
    }

    @Override
    public int update(Patient patient) {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Patient> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Patient findById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Patient findPatientByCode(String code) {
        String SQL_LOGIN = "SELECT * FROM user WHERE code =  ?";
        Patient patient = null;
        openConnexion();
        initPrepareStatement(SQL_LOGIN);
        try {
            getPs().setString(1, code);
            ResultSet rs = executeSelect(SQL_LOGIN);
            if (rs.next()) {
                patient = new Patient(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("code"),
                        rs.getString("antecedents"));
            }
        }
    
    catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
    }finally{
       this.closeConnexion();
    }
        return patient;

        
    }

    
}
