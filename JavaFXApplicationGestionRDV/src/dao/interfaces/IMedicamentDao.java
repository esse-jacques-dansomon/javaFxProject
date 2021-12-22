/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import entity.Medicament;
import java.util.List;

/**
 *
 * @author EsseJacquesDansomon
 */
public interface IMedicamentDao extends IDao<Medicament>{
    List<Medicament> findAllMedicamendByOrdonnance(int id_ordonnance);
    Medicament findByCode(String code);

}
