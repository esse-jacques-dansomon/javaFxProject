/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.TypeConsultation;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import dao.interfaces.ITypeConsultationDao;

/**
 *
 * @author EsseJacquesDansomon
 */
public class TypeConsultationDao  extends DataBase implements ITypeConsultationDao{

    @Override
    public int insert(TypeConsultation specialite) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(TypeConsultation specialite) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TypeConsultation> findAll() {
        String SQL_ALL = " SELECT * FROM type_consultation  ";
        List<TypeConsultation> specialites = new ArrayList<>();
        openConnexion();
        initPrepareStatement(SQL_ALL);
        ResultSet rs = executeSelect(SQL_ALL);
        try {
            while(rs.next()){
                TypeConsultation specialite =new TypeConsultation(rs.getInt(1),rs.getString("libelle"));
                specialites.add(specialite);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypeConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnexion();
        return specialites;
    }

    @Override
    public TypeConsultation findById(int id) {
            TypeConsultation typeConsultation = null ;
        try {
            String SQL = " SELECT * FROM type_consultation WHERE id_type_consultation = ? ";
            this.openConnexion();
            this.initPrepareStatement(SQL);
            this.getPs().setInt(1, id);
            ResultSet rs = this.executeSelect(SQL);
            if(rs.next())
            {
              typeConsultation = new TypeConsultation(rs.getInt(1), rs.getString(2)) ; 
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypePrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            this.closeConnexion();
        }
        return typeConsultation;}
    
}
