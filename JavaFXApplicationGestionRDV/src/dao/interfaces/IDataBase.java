/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author EsseJacquesDansomon
 */
public interface IDataBase {
    
    //fonction permettant d'ouvrir la connexion
    public void openConnexion();
    //Ferme la connexion á la DataBase
    public void closeConnexion();
    //Prepare la requette la requette á excecuter
    public void initPrepareStatement(String sql);
    //Permet d'injecter les parametres de la requette
    public PreparedStatement getPs();
    //Permet d''executer une requette Delete|Insert|Delete
    public int executeUpdate(String sql);
    //Permet d''executer une requette select
    public ResultSet executeSelect(String sql);

}
