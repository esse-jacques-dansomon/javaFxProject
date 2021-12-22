/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.interfaces.IPrestationDao;
import entity.Prestation;
import entity.TypePrestation;
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
public class PrestationDao extends DataBase implements IPrestationDao {

    @Override
    public List<Prestation> findAllPrestationByPatientId(User patient) {
       List<Prestation> arrayPrestationPatient = new ArrayList<>();

        try {
            String sql = "SELECT * FROM prestation p, type_prestation t "
                    + " WHERE p.type_prestation_id = t.id_type_prestation AND p.patient_id = ? ";
            this.openConnexion();
            this.initPrepareStatement(sql);
            this.getPs().setInt(1, patient.getId());
            ResultSet rs = this.executeSelect(sql);
            while(rs.next())
            {
                Prestation prestation= new Prestation();
                prestation.setPatient(patient);
                prestation.setDate(rs.getDate("date"));
                prestation.setStatut(rs.getString("statut"));
                prestation.setResultats(rs.getString("resultats"));
                System.out.println("affichage des resultats " + rs.getString("statut"));

                TypePrestation typePrestation = new TypePrestation();
                typePrestation.setId(rs.getInt("id_type_prestation"));
                typePrestation.setLibelle(rs.getString("libelle"));
                
                prestation.setTypePrestation(typePrestation);
                System.out.println(prestation);
                arrayPrestationPatient.add(prestation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
         this.closeConnexion();
        }
        return arrayPrestationPatient;
    }

    @Override
    public int insert(Prestation prestation) {
        int id_prestation = 0;
        try {
            String SQL_INSERT = "INSERT INTO `prestation` (`statut`, `date`, `patient_id`) VALUES ( ?, ?, ?) ";
            this.openConnexion();
            initPrepareStatement(SQL_INSERT);
            getPs().setDate(2, prestation.getDate());
            getPs().setString(1, prestation.getStatut());
            getPs().setInt(3, prestation.getPatient().getId());
            executeUpdate(SQL_INSERT);
            System.out.println("Requette Insert reussite ");
            ResultSet rs = getPs().getGeneratedKeys();
            if(rs.next())
                id_prestation = rs.getInt(1);
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            this.closeConnexion();
        }
        return id_prestation;
    }

    @Override
    public int update(Prestation obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prestation> findAll() {
        List<Prestation> arrayPrestationPatient = new ArrayList<>();
        try {
            String sql = "SELECT * FROM prestation p, type_prestation t WHERE p.id = t.id_type_prestation";
            this.openConnexion();
            this.initPrepareStatement(sql);
            ResultSet rs = this.executeSelect(sql);
            while(rs.next())
            {
                Prestation prestation= new Prestation();
                prestation.setId(rs.getInt("id"));
                prestation.setDate(rs.getDate("date"));
                prestation.setStatut(rs.getString("statut"));
                prestation.setResultats(rs.getString("resultats"));
                prestation.setPatient(new UserDao().findById(rs.getInt("patient_id")));
                prestation.setTypePrestation(new TypePrestation(
                rs.getInt("id_type_prestation"),
                 rs.getString("libelle")       
                ));
                arrayPrestationPatient.add(prestation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
         this.closeConnexion();
        }
        return arrayPrestationPatient;
    }

    @Override
    public Prestation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prestation> findAllPrestationByPatientIdAndStatus(User patient, String status) {
        return this.findAllPrestationByPatientId(patient).stream()
                .filter(ps -> ps.getStatut().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }
    
}
