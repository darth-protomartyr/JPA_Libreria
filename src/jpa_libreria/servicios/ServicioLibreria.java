/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_libreria.servicios;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Gonzalo
 */
public class ServicioLibreria {
    Scanner read = new Scanner(System.in).useDelimiter("\n");
    ServicioLibro sl = new ServicioLibro();
    ServicioEditorial se = new ServicioEditorial();
    ServicioAutor sa = new ServicioAutor();
   
    public void managerLibreria() throws Exception {
        byte op = 0;
        do {
            try {
                System.out.println("\nAdministrador Libreria:\n"
                + "Si desea administrar Libros, presione 1\n"
                + "Si desea administrar Editoriales, presione 2\n"
                + "Si desea administrar Autores, presione 3\n"
                + "Si desea finalizar el programa, presione 4");
                System.out.println("");
                System.out.println("Ingrese el número de la operación que desea realizar:");
                op = read.nextByte();
                switch(op) {
                    case 1:
                        sl.managerLibros();
                        break;
                    case 2:
                        se.managerEditoriales();
                        break;
                    case 3:
                        sa.managerAutores();
                        break;
                    case 4:
                        System.out.println("Usted ha dado por finalizado el programa.");
                        break;
                    default:
                        System.out.println("Ingrese un valor numérico comprendido entre 1 y 4:"); 
                }
            } catch (InputMismatchException ex) {
                System.out.println("Usted no ingresó un valor numérico.\n");
                read.nextLine();
            }
        } while (op != 4);
    }
}