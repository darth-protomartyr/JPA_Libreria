/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_libreria.persistencia;

import java.util.ArrayList;
import java.util.List;
import jpa_libreria.entidades.Editorial;

/**
 *
 * @author Gonzalo
 */
public class EditorialDAO extends DAO{
    
    public void crear(Editorial editorial) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            try {
                em.getTransaction().rollback();
            } catch (Exception ex) {
                throw new Exception("Error haciendo un rollback");
            }
            
            throw new Exception("Error al persitir un autor");
        }
    }
    
    
    

    public void modificar(Editorial editorial) throws Exception{
        try {
            em.getTransaction().begin();
            em.merge(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al modificar una editorial");
        }
    }
   
    public void eliminar(Editorial editorial) throws Exception{
        try {
            em.getTransaction().begin();
            em.remove(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al eliminar una editorial");
        }
    }

    public Editorial buscarPorNombre(String nombre){
        Editorial ed = (Editorial) em.createQuery("SELECT e "
                + "FROM Editorial e "
                + "WHERE e.nombre = :nombre ").setParameter("nombre", nombre).getSingleResult();
        return ed;
    }

    public List<Editorial> listar() {
        List<Editorial> eds = em.createQuery("SELECT e FROM Editorial e").getResultList();
        return eds;
    }
}