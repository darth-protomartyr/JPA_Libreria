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
        boolean manage = true;
        byte op = 0;
        while(manage == true) {
            System.out.println("\nAdministrador Autores:\n"
                    + "Si desea agregar un nuevo Autor, presione 1\n"
                    + "Si desea modificar un Autor listado, presione 2\n"
                    + "Si desea eliminar un Autor listado, presione 3\n"
                    + "Si desea consultar un Autor listado, presione 4\n"
                    + "Si desea consultar el listado de Autores, presione 5\n"
                    + "Si desea regresar al menu anterior, presione 6");
            try {
                do {
                    System.out.println("Ingrese el número de la operación que desea realizar:");
                    op = read.nextByte();
                    if (op > 6 || op < 1) {
                        System.out.println("Ingrese un valor permitido");
                    }
                } while (op > 6 || op < 1);
            } catch (InputMismatchException ex) {
                System.out.println("Solo puedes ingresar valores numéricos.");
            }
            switch(op) {
                case 1:
                    Autor salinger = new Autor();
                    salinger = crearAutor();
                    daoa.crear(salinger);
                    break;
                case 2:
                    boolean upd = true;
                    Autor buk = new Autor();
                    buk = daoa.buscarPorNombre(ea.ingresarNombre());
                    while (upd == true) {
                        byte op1=0;
                        System.out.println(buk);
                        System.out.println("Si desea modificar el nombre del Autor, presione 1.\n"
                            + "Si desea modificar el estado de alta o baja del Autor, presione 2.\n"
                            + "Si desea guardar los cambios y volver al menú anterior, presione 3.\n");
                        do {
                            System.out.println("Ingrese el número de la operación que desea realizar:");
                            op1 = read.nextByte();
                            if (op1 > 3 || op < 1) {
                                System.out.println("Ingrese un valor permitido");
                            }
                        } while (op1 > 3 || op < 1);
                        switch (op1) {
                            case 1:
                                buk.setNombre(ea.ingresarNuevoNombre());
                                break;
                            case 2:
                                buk.setAlta(ea.ingresarAlta());
                                break;
                            case 3:
                                upd = false;
                                break;
                        }
                    }
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
                    manage = false;
                    break;
            }
        }
        System.out.println("Usted ha regresado al menú anterior.");
    }
    
    public Autor crearAutor() {
        Autor salinger = new Autor();
        salinger.setNombre(ea.ingresarNombre());
        salinger.setAlta(true);
        salinger.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        return salinger;
    }
}