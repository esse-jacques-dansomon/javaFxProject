/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.interfaces.IMedecinDao;
import entity.Medecin;
import entity.TypeConsultation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EsseJacquesDansomon
 */
public class MedecinDao extends DataBase  implements  IMedecinDao {

    @Override
    public int insert(Medecin obj) {
        throw new UnsupportedOperationException("Not supported yet."); 
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Medecin obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medecin> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Medecin findById(int id) {
        Medecin user = null;
        try {
            String SQL_LOGIN = "SELECT * FROM user u ,  type_prestation t WHERE u.type_consultation_id = t.id_type_prestation AND u.id = ? ";
            openConnexion();
            initPrepareStatement(SQL_LOGIN);
            getPs().setInt(1, id);
            ResultSet rs = executeSelect(SQL_LOGIN);
            if (rs.next()) 
            {
                user = new Medecin(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("role"),
                        new TypeConsultation(rs.getInt("type_consultation_id"),
                                rs.getString("libelle"))
                        );
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            this.closeConnexion();
        }
        return user;
    }
    
}
