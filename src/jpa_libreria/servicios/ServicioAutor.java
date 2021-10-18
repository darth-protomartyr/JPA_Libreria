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
import java.util.UUID;
import javax.persistence.NoResultException;
import jpa_libreria.entidades.Autor;
import jpa_libreria.entradas.EntradaAutor;
import jpa_libreria.persistencia.AutorDAO;

/**
 *
 * @author Gonzalo
 */
public class ServicioAutor {
    Scanner read = new Scanner(System.in).useDelimiter("\n");
    EntradaAutor ea = new EntradaAutor();
    AutorDAO daoa = new AutorDAO(); 
    public void managerAutores() throws Exception {
        byte op = 0;
        do {
            try {
                System.out.println("\nAdministrador Autores:\n"
                    + "Si desea agregar un nuevo Autor, presione 1\n"
                    + "Si desea modificar un Autor listado, presione 2\n"
                    + "Si desea eliminar un Autor listado, presione 3\n"
                    + "Si desea consultar un Autor listado, presione 4\n"
                    + "Si desea consultar el listado de Autores, presione 5\n"
                    + "Si desea regresar al menu anterior, presione 6");
            
                System.out.println("Ingrese el número de la operación que desea realizar:");
                op = read.nextByte();
                if (op > 6 || op < 1) {
                    System.out.println("Ingrese un valor permitido");
                }
                
                switch(op) {
                    case 1:
                        Autor buk1 = new Autor();
                        buk1 = crearAutor();
                        daoa.crear(buk1);
                        break;
                    case 2:
                        Autor buk = new Autor();
                        buk = daoa.buscarPorNombre(ea.ingresarNombre());                        
                        System.out.println(buk);
                        byte op1=0;
                        do {
                            try {
                                System.out.println("Si desea modificar el nombre del Autor, presione 1.\n"
                                    + "Si desea modificar el estado de alta o baja del Autor, presione 2.\n"
                                    + "Si desea guardar los cambios y volver al menú anterior, presione 3.\n");
                                System.out.println("Ingrese el número de la operación que desea realizar:");
                                op1 = read.nextByte();
                                switch (op1) {
                                    case 1:
                                        buk.setNombre(ea.ingresarNuevoNombre());
                                        break;
                                    case 2:
                                        buk.setAlta(ea.ingresarAlta());
                                        break;
                                    case 3:
                                        System.out.println("Usted ha regresado al menú anterior");
                                        break;
                                    default:
                                        System.out.println("Ingrese un valor comprendido entre 1 y 3");
                                }
                            } catch (InputMismatchException ex) {
                                    System.out.println("Usted no ingresó un valor numérico.\n");
                                    read.nextLine();
                            }
                        } while(op1 != 3);    
                        daoa.modificar(buk);
                        break;
                    case 3:
                        Autor buk2 = new Autor();
                        buk2 = daoa.buscarPorNombre(ea.ingresarNombreEliminar());
                        daoa.eliminar(buk2);
                        break;
                    case 4:
                        Autor buk3 = new Autor();
                        buk3 = daoa.buscarPorNombre(ea.ingresarNombre());
                        System.out.println(buk3);
                        break;
                    case 5:
                        List <Autor> portFolBuk = new ArrayList(daoa.listar());
                        Iterator<Autor> it = portFolBuk.iterator();
                        int counter = 1;
                        while(it.hasNext()) {
                            Autor wri = it.next();
                            System.out.println(counter + ")" + wri);
                            counter +=1;
                        }
                        break;
                    case 6:
                        System.out.println("Usted ha regresado al menú anterior");
                        break;
                    default:
                        System.out.println("Ingrese un valor entrre 1 y 6");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Usted no ingresó un valor numérico222.\n"
                    + "Elija una operacion nuevamente.");
                read.nextLine();
            } catch (NoResultException ex) {
                System.out.println("La consulta que realizó no arrojó ninguna coincidencia.\n"
                    + "Revise la lista y repita la operación, o seleccione otra.");
                read.nextLine();
            }       
        } while(op != 6);
    }
    
    public Autor crearAutor() {
        Autor salinger = new Autor();
        salinger.setNombre(ea.ingresarNombre());
        salinger.setAlta(true);
        salinger.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        return salinger;
    }
}