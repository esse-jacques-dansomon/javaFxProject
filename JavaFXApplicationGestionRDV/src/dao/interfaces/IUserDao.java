/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import entity.User;
import java.util.List;

/**
 *
 * @author EsseJacquesDansomon
 */
public interface IUserDao extends IDao<User> 
{
 
  
  /**
   * fonction qui permet de Rechercher un User selon login|password
   * @param login
   * @param password
   * @return User
   */
  public User findUserByLoginAndPassword(String login, String password);
  public List<User> findUserByTypeService(int type_service_id);
  public List<User> findUserByRoleName(String type_service_id);
}
