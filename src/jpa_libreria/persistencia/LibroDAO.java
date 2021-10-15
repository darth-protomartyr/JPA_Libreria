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
        Libro ubik = new Libro();
        ubik = em.find(Libro.class, id);
        if (ubik == null) {
            System.out.println("El ISBN ingresado no corresponde con ningún Libro ingresado en la Base de Datos");
            return null;
        } else {
            return ubik;
        }
    }

    public Libro buscarPorTitulo(String titulo){
        Libro ed = new Libro();
        do {
            ed = (Libro) em.createQuery("SELECT li "
                + "FROM Libro li "
                + "WHERE li.titulo = :titulo ").setParameter("titulo", titulo).getSingleResult();
            if (ed == null) {
                System.out.println("El titulo ingresado no pertenece a un Libro que se encuentre en la lista");
            }
        }while(ed == null);
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