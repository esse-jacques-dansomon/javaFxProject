/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.TypePrestation;
import java.util.List;
import dao.interfaces.ITypePrestationDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EsseJacquesDansomon
 */
public class TypePrestationDao extends DataBase implements ITypePrestationDao {

    @Override
    public int insert(TypePrestation obj) {
  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }

    @Override
    public int update(TypePrestation obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TypePrestation> findAll() {
        String SQL_ALL = " SELECT * FROM type_prestation  ";
        List<TypePrestation> typePrestations = new ArrayList<>();
        openConnexion();
        initPrepareStatement(SQL_ALL);
        ResultSet rs = executeSelect(SQL_ALL);
        try {
            while(rs.next()){
                TypePrestation typePrestation =new TypePrestation(rs.getInt(1),rs.getString("libelle"));
                typePrestations.add(typePrestation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypePrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnexion();
        return typePrestations;
    }

    @Override
    public TypePrestation findById(int id) {
        TypePrestation typePrestation = null ;
        try {
            String SQL = " SELECT * FROM type_prestation WHERE id_type_prestation = ?  ";
            this.openConnexion();
            this.initPrepareStatement(SQL);
            this.getPs().setInt(1, id);
            ResultSet rs = this.executeSelect(SQL);
            if(rs.next())
            {
              typePrestation = new TypePrestation(rs.getInt(1), rs.getString(2)) ; 
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypePrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            this.closeConnexion();
        }
        return typePrestation;
      }
    
}
