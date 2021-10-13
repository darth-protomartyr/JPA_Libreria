/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_libreria.persistencia;

import java.util.List;
import jpa_libreria.entidades.Autor;

/**
 *
 * @author Gonzalo
 */
public class AutorDAO extends DAO {
    public void crear(Autor autor) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(autor);
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

    public void modificar(Autor autor) throws Exception{
        try {
            em.getTransaction().begin();
            em.merge(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al modificar un autor");
        }
    }
   
    public void eliminar(Autor autor) throws Exception{
        try {
            em.getTransaction().begin();
            em.remove(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al eliminar un autor");
        }
    }

    public Autor buscarPorNombre(String nombre){
        Autor ed = (Autor) em.createQuery("SELECT e "
                + "FROM Autor e "
                + "WHERE e.nombre = :nombre ").setParameter("nombre", nombre).getSingleResult();
        return ed;
    }
    
    public List<Autor> listar() {
        List<Autor> wris = em.createQuery("SELECT e FROM Autor e").getResultList();
        return wris;
    }
}