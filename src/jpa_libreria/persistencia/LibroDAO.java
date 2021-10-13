/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_libreria.persistencia;

import java.util.ArrayList;
import java.util.List;
import jpa_libreria.entidades.Libro;

/**
 *
 * @author Gonzalo
 */
public class LibroDAO extends DAO{
    public void crear(Libro libro) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            try {
                em.getTransaction().rollback();
            } catch (Exception ex) {
                throw new Exception("Un puto error haciendo un rollback");
            }
            throw new Exception("Error al persistir un libro");
        }
    }
   
    public void modificar(Libro libro) throws Exception{
        try {
            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al modificar un libro");
        }
    }
    
    public void eliminar(Libro libro) throws Exception{
        try {
            em.getTransaction().begin();
            em.remove(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al eliminar un libro");
        }
    }

    public Libro buscarPorISBN(Long id){
        return em.find(Libro.class, id);
    }

    public Libro buscarPorTitulo(String titulo){
        Libro ed = (Libro) em.createQuery("SELECT li "
                + "FROM Libro li "
                + "WHERE li.titulo = :titulo ").setParameter("titulo", titulo).getSingleResult();
        return ed;
    }

    public List<Libro> buscarPorEditorial(String nom){
        List<Libro> libros = new ArrayList(); 
        
        libros = (List<Libro>) em.createQuery("SELECT li "
                + " FROM Libro li"
                + " WHERE li.editorial.nombre LIKE :nom")
                .setParameter("nom", nom)
                .getResultList();
        return libros;
    }
    
    public List<Libro> buscarPorAutor(String nom){
        List<Libro> libros = new ArrayList(); 
        libros = (List<Libro>) em.createQuery("SELECT li "
                + " FROM Libro li"
                + " WHERE li.autor.nombre LIKE :nom")
                .setParameter("nom", nom)
                .getResultList();
        return libros;
    }

    public List<Libro> listar() {
        List<Libro> books = em.createQuery("SELECT li FROM Libro li ").getResultList();
        return books;
    }
}