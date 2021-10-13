/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_libreria.entradas;

import java.util.Scanner;

/**
 *
 * @author Gonzalo
 */
public class EntradaAutor {
    Scanner read = new Scanner(System.in).useDelimiter("\n");
    

    public String ingresarNombre() {
        System.out.println("Ingrese el Nombre del Autor:");
        String nom = read.next();
        return nom;
    }
    
    public String ingresarNuevoNombre() {
        System.out.println("Ingrese el nuevo Nombre del Autor:");
        String nom = read.next();
        return nom;
    }
    
    public String ingresarNombreEliminar() {
        System.out.println("Ingrese el Nombre del Autor que desea eliminar:");
        String nom = read.next();
        return nom;
    }

    public Boolean ingresarAlta() {
        boolean alta = true;
        byte op = 0;
        do {
            System.out.println("Si desea dar baja al autor, presione 1.\n"
                + "Si desea darle el alta, presione 2.\n");
            System.out.println("Ingrese el número de la operación:");
            op = read.nextByte();
            if (op > 3 || op < 1) {
                System.out.println("Ingrese un valor permitido");
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