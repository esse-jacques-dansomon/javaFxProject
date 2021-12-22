/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.interfaces.IUserDao;
import entity.Patient;
import entity.User;
import entity.Medecin;
import entity.TypeConsultation;
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
public class UserDao extends DataBase implements IUserDao {

    @Override
    public int insert(User ogj) {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(User ogj) {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User findById(int id) {
        User user = new User();
        try {
            String SQL_LOGIN = "SELECT * FROM user WHERE id =  ?  ";
            openConnexion();
            initPrepareStatement(SQL_LOGIN);
            getPs().setInt(1, id);
            ResultSet rs = executeSelect(SQL_LOGIN);
            if (rs.next()) 
            {
                user = new User(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("role")
                        );
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            this.closeConnexion();
        }
        return user;
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        String SQL_LOGIN = "SELECT * FROM user WHERE login =  ? AND password = ? ";
        openConnexion();
        initPrepareStatement(SQL_LOGIN);
        try {
            getPs().setString(1, login);
            getPs().setString(2, password);
            ResultSet rs = executeSelect(SQL_LOGIN);
            if (rs.next()) {
                switch (rs.getString("role")) {
                    case "ROLE_PATIENT":
                        Patient patient = new Patient(
                                rs.getInt("id"),
                                rs.getString("nom"),
                                rs.getString("prenom"),
                                rs.getString("login"),
                                rs.getString("password"),
                                rs.getString("role"),
                                rs.getString("password"),
                                rs.getString("password")
                        );
                        return patient;

                    case "ROLE_MEDECIN":
                        TypeConsultation typeConsultation = new TypeConsultation();
                        Medecin medecin = new Medecin(
                                rs.getInt("id"),
                                rs.getString("nom"),
                                rs.getString("prenom"),
                                rs.getString("login"),
                                rs.getString("password"),
                                rs.getString("role"),
                                typeConsultation);
                        return medecin;
                    default:
                        User user = new User(
                                rs.getInt("id"),
                                rs.getString("nom"),
                                rs.getString("prenom"),
                                rs.getString("login"),
                                rs.getString("password"),
                                rs.getString("role"));
                        return user;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<User> findUserByTypeService(int type_service_id) {
        List<User> medecins = new ArrayList<>();
        try {
            String SQL_LOGIN = "SELECT * FROM user WHERE type_consultation_id =  ?  ";
            openConnexion();
            initPrepareStatement(SQL_LOGIN);
            getPs().setInt(1, type_service_id);
            ResultSet rs = executeSelect(SQL_LOGIN);
            while (rs.next()) 
            {
                TypeConsultation typeConsultation = new TypeConsultation();
                Medecin medecin = new Medecin(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("role"),
                        typeConsultation);
                medecins.add(medecin);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            this.closeConnexion();
        }
        return medecins;
        
    }

    @Override
    public List<User> findUserByRoleName(String role) {
                List<User> prestataires = new ArrayList<>();
        try {
            String SQL_LOGIN = "SELECT * FROM user WHERE role =  ?  ";
            openConnexion();
            initPrepareStatement(SQL_LOGIN);
            getPs().setString(1, role.toUpperCase());
            ResultSet rs = executeSelect(SQL_LOGIN);
            while (rs.next()) 
            {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setRole( rs.getString("role"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                prestataires.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            this.closeConnexion();
        }
        return prestataires;
    }
  

    
}
