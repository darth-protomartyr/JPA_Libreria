/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_libreria.servicios;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.persistence.NoResultException;
import jpa_libreria.entidades.Libro;
import jpa_libreria.entradas.EntradaAutor;
import jpa_libreria.entradas.EntradaEditorial;
import jpa_libreria.entradas.EntradaLibro;
import jpa_libreria.persistencia.LibroDAO;
/**
 *
 * @author Gonzalo
 */
public class ServicioLibro {
    Scanner read = new Scanner(System.in).useDelimiter("\n");
    EntradaLibro el = new EntradaLibro();
    EntradaEditorial ee = new EntradaEditorial();
    EntradaAutor ea = new EntradaAutor();
    LibroDAO daol = new LibroDAO(); 
    public void managerLibros() throws Exception {
        byte op = 0;
        List<Libro> buks = new ArrayList();
        Libro ubik = new Libro();
        do {
            try {
                    System.out.println("\nAdministrador Libro:\n"
                    + "Si desea agregar un nuevo Libro, presione 1\n"
                    + "Si desea modificar un Libro listado, presione 2\n"
                    + "Si desea eliminar un Libro listado, presione 3\n"
                    + "Si desea consultar un Libro listado por ISBN, presione 4\n"
                    + "Si desea consultar un Libro listado por nombre, presione 5\n"
                    + "Si desea consultar uno o más Libros por Autor, presione 6\n"
                    + "Si desea consultar uno o más Libros por Editorial, presione 7\n"
                    + "Si desea listar los Libros, presione 8\n"
                    + "Si desea regresar al menú anterior, presione 9");
                System.out.println("Ingrese el número del 1 al 9 con la operación que desea realizar:");
                op = read.nextByte();
                switch(op) {
                    case 1:
                        ubik = el.crearLibro();
                        daol.crear(ubik);
                        break;
                    case 2:
                        ubik = daol.buscarPorTitulo(el.ingresarNombre());
                        System.out.println(ubik);
                        byte op1=0;
                        do {
                            try {
                                System.out.println("Si desea modificar el título del Libro, presione 1.\n"
                                    + "Si desea modificar el estado de alta o baja del Libro, presione 2.\n"
                                    + "Si desea modificar la cantidad de libros diponible para prestamos, presione 3.\n"
                                    + "Si desea modificar la Editorial, presione 4.\n"
                                    + "Si desea modificar el Autor, presione 5.\n"
                                    + "Si desea guardar los cambios y volver al menú anterior, presione 6.\n");
                                System.out.println("Ingrese el número de la operación que desea realizar:");
                                op1 = read.nextByte();
                                switch (op1) {
                                    case 1:
                                        ubik.setTitulo(el.ingresarNuevoNombre());
                                        break;
                                    case 2:
                                        ubik.setAlta(el.ingresarAlta());
                                        break;
                                    case 3:
                                        ubik = el.modificarPrestamo(ubik);
                                        break;
                                    case 4:
                                        System.out.println("\n Actualización de la Editorial");
                                        ubik.setEditorial(el.findPublisher());
                                        break;
                                    case 5:
                                        System.out.println("\n Actualización del Autor");
                                        ubik.setAutor(el.findWriter());
                                        break;
                                    case 6:
                                        System.out.println("Usted regresará al menú anterior");
                                        break;
                                    default:
                                        System.out.println("Ingrese un valor entre 1 y 6");
                                }
                            } catch (InputMismatchException ex) {
                                System.out.println("Usted no ingresó un valor numérico.\n");
                                read.nextLine();
                            }
                        } while (op1 != 6);
                        daol.modificar(ubik);
                        break;
                    case 3:
                        ubik = daol.buscarPorTitulo(el.ingresarNombreEliminar());
                        daol.eliminar(ubik);
                        break;
                    case 4:
                        ubik = daol.buscarPorISBN(el.ingresarISBN());
                        if (ubik != null) {
                            System.out.println(ubik);
                        }
                        break;
                    case 5:
                        ubik = daol.buscarPorTitulo(el.ingresarNombre());
                        System.out.println(ubik);
                        break;
                    case 6:
                        ubik = (Libro) daol.buscarPorAutor(ea.ingresarNombre());
                        System.out.println(ubik);
                        break;    
                    case 7:
                        String ed = ee.ingresarNombre();
                        buks = (List<Libro>) daol.buscarPorEditorial(ed);
                        System.out.println("Los Libros de la Editorial " + ed + " son:");
                        int counter = 1;
                        for (Libro buk : buks) {
                            System.out.println(counter + ")" + buk);
                        }
                        break;                   
                    case 8:
                        buks = (List<Libro>) daol.listar();
                        Iterator<Libro> it = buks.iterator();
                        int counter1 = 1;
                        while(it.hasNext()) {
                            Libro buk = it.next();
                            System.out.println(counter1 + ")" + buk);
                            counter1 +=1;
                        }
                        break;
                    case 9:
                        System.out.println("Usted regresó al menú anterior \n");
                        break;
                    default:
                        System.out.println("Usted no ingresó un número válido.");
                }
            }catch (InputMismatchException ex) {
                System.out.println("Usted no ingresó un valor numérico.\n"
                        + "Elija una operacion nuevamente.");
                        System.out.println("Error: " + ex.toString());
                        read.nextLine();
            } catch (NoResultException ex) {
                System.out.println("La consulta que realizó no arrojó ninguna coincidencia.\n"
                        + "Revise la lista y repita la operación, o seleccione otra.");
                        read.nextLine();
            }
        } while (op != 9);
    }
}