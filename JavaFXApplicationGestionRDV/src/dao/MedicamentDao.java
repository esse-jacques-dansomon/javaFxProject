/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.interfaces.IMedicamentDao;
import entity.Medicament;
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
public class MedicamentDao extends DataBase implements IMedicamentDao {

    @Override
    public List<Medicament> findAllMedicamendByOrdonnance(int id_ordonnance) {
    List<Medicament> medicaments = new ArrayList<>() ;
        try {
            String sql = "SELECT * FROM medicament WHERE ordonnance_id = ? ";
            this.openConnexion();
            this.initPrepareStatement(sql);
            this.getPs().setInt(1, id_ordonnance);
            ResultSet rs = this.executeSelect(sql);
            while(rs.next())
            {
                Medicament medicament = new Medicament();
                medicament.setId(rs.getInt("id_medicament"));
                medicament.setNom(rs.getString("nom"));
                medicament.setCode(rs.getString("code"));
                medicaments.add(medicament);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicamentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            this.closeConnexion();
        }
        return medicaments;
    }

    @Override
    public int insert(Medicament medicament) {
        int id_medicament = 0;
        try {
            String SQL_INSERT = "INSERT INTO `medicament`( `code`, `nom`) VALUES ( ?, ?)";
            this.openConnexion();
            initPrepareStatement(SQL_INSERT);
            getPs().setString(1, medicament.getCode());
            getPs().setString(2, medicament.getNom());
            executeUpdate(SQL_INSERT);
            ResultSet rs = getPs().getGeneratedKeys();
            if(rs.next())
                id_medicament = rs.getInt(1);
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(MedicamentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            this.closeConnexion();
        }
        return id_medicament;
    }

    @Override
    public int update(Medicament medicament) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medicament> findAll() {
        List<Medicament> medicaments = new ArrayList<>() ;
        try {
            String sql = "SELECT * FROM medicament";
            this.openConnexion();
            this.initPrepareStatement(sql);
            ResultSet rs = this.executeSelect(sql);
            while(rs.next())
            {
                Medicament medicament = new Medicament();
                medicament.setId(rs.getInt("id_medicament"));
                medicament.setNom(rs.getString("nom"));
                medicament.setCode(rs.getString("code"));
                medicaments.add(medicament);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicamentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            this.closeConnexion();
        }
        return medicaments;
    }

    @Override
    public Medicament findById(int id) {
       return this.findAll().stream()
        .filter(medicament -> medicament.getId()== id)
        .findFirst()
        .get();       
    }

    @Override
    public Medicament findByCode(String code) {
        return this.findAll().stream()
        .filter(medicament -> medicament.getCode().equals(code))
        .findFirst()
        .orElse(null);          
    }
     
}
