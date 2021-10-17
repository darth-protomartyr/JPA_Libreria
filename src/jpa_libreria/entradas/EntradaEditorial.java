/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_libreria.entradas;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import jpa_libreria.entidades.Editorial;

/**
 *
 * @author Gonzalo
 */
public class EntradaEditorial {
    Scanner read = new Scanner(System.in).useDelimiter("\n");

    public String ingresarNombre() {
        System.out.println("Ingrese el Nombre de la editorial:");
        String nom = read.next();
        return nom;
    }
    
    public String ingresarNuevoNombre() {
        System.out.println("Ingrese el nuevo Nombre de la editorial:");
        String nom = read.next();
        return nom;
    }
    
    public String ingresarNombreEliminar() {
        System.out.println("Ingrese el Nombre de la editorial que desea eliminar:");
        String nom = read.next();
        return nom;
    }

    public Boolean ingresarAlta() {
        boolean alta = true;
        byte op = 0;
        do {
            try {
                System.out.println("Si desea dar baja a  la editorial presione 1.\n"
                    + "Si desea darle el alta, presione 2.\n");
                System.out.println("Ingrese el número de la operación:");
                op = read.nextByte();
                if (op > 3 || op < 1) {
                    System.out.println("Ingrese un valor permitido");
                }   
            } catch(InputMismatchException ex) {
                    System.out.println("No ingresó un valor númérico");
                    read.nextLine();
            }
        } while (op > 2 || op < 1);
        if (op == 1) {
            alta = false;
        } else {
            alta = true;
        }
        return alta;
    }
}