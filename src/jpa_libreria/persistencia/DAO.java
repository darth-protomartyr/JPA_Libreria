/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_libreria.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Gonzalo
 */
public class DAO {
    protected EntityManager em = Persistence.createEntityManagerFactory("JPA_LibreriaPU").createEntityManager();
}
