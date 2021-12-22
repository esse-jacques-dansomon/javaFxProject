/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.interfaces.IMedicamentPrescriptionDao;
import entity.Medicament;
import entity.MedicamentPrescription;
import entity.Ordonnance;
import entity.Patient;
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
public class MedicamentPrescriptionDao extends DataBase implements IMedicamentPrescriptionDao {

    @Override
    public int insert(MedicamentPrescription obj) {
        return 0;
    }

    @Override
    public int update(MedicamentPrescription obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MedicamentPrescription> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MedicamentPrescription findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int CreatePrescriptions(List<MedicamentPrescription> arrayPrecriptions, Ordonnance ordonnance) {
        int id_medicament_prescription = 0;
        try {
            String SQL_INSERT = "INSERT INTO `medicament_prescription`(`ordonnance_id`, `medicament_id`, `posologie`) VALUES ( ?, ?, ? )";
            this.openConnexion();
            initPrepareStatement(SQL_INSERT);
            for (MedicamentPrescription medicament_prescription : arrayPrecriptions) {
                getPs().setInt(1,  ordonnance.getId());
                getPs().setInt(2, medicament_prescription.getMedicament().getId());
                getPs().setString(3, medicament_prescription.getPosologie());
                executeUpdate(SQL_INSERT);
                ResultSet rs = getPs().getGeneratedKeys();
                if (rs.next()) {
                    id_medicament_prescription =+ rs.getInt(1);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closeConnexion();
        }
        return id_medicament_prescription;
    }

    @Override
    public List<MedicamentPrescription> findAllPrescriptionOfPatient(Patient patient) {
                String sql  = "SELECT p.id_medicament_prescrit, m.id_medicament, m.code, m.nom, p.posologie FROM "
                        + "`medicament_prescription` p, medicament m, consultation c, "
                        + "ordonnance o WHERE p.medicament_id = m.id_medicament and "
                        + "c.id_consultation = o.consultation_id and o.id_ordonnance "
                        + "= p.ordonnance_id and c.patient_id = ?;";
            List<MedicamentPrescription> arrayMedicamentPrescriptions = new ArrayList<>();
            try {
           this.openConnexion();
            this.initPrepareStatement(sql);
            this.getPs().setInt(1, patient.getId());
            ResultSet rs = this.executeSelect(sql);
            while(rs.next())
            {
                MedicamentPrescription p = new MedicamentPrescription();
                p.setId(rs.getInt("id_medicament_prescrit"));
                p.setPosologie(rs.getString("posologie"));
                p.setMedicament(new Medicament(
               rs.getInt("id_medicament"),
                rs.getString("nom"),
               rs.getString("code")
                ));
                arrayMedicamentPrescriptions.add(p);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicamentPrescriptionDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            this.closeConnexion();
        }
        return arrayMedicamentPrescriptions;
                
                
    }
}
