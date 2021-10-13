/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_libreria.servicios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import jpa_libreria.entidades.Editorial;
import jpa_libreria.entradas.EntradaEditorial;
import jpa_libreria.persistencia.EditorialDAO;

/**
 *
 * @author Gonzalo
 */
public class ServicioEditorial {
    Scanner read = new Scanner(System.in).useDelimiter("\n");
    EntradaEditorial ee = new EntradaEditorial();
    EditorialDAO daoe = new EditorialDAO();
    public void managerEditoriales() throws Exception {
        boolean manage = true;
        byte op = 0;
        while(manage == true) {
            System.out.println("\nAdministrador Editoriales:\n"
                    + "Si desea agregar una nueva Editorial, presione 1\n"
                    + "Si desea modificar una Editorial listada, presione 2\n"
                    + "Si desea eliminar una Editorial listada, presione 3\n"
                    + "Si desea consultar una Editorial listada, presione 4\n"
                    + "Si desea consultar la lista de Editoriales, presione 5\n"
                    + "Si desea regresar al menu anterior, presione 6");
            do {
                System.out.println("Ingrese el número de la operación que desea realizar:");
                op = read.nextByte();
                if (op > 6 || op < 1) {
                    System.out.println("Ingrese un valor permitido");
                }
            } while (op > 6 || op < 1);
            switch(op) {
                case 1:
                    Editorial ed1 = new Editorial();
                    ed1.setNombre(ee.ingresarNombre());
                    ed1.setAlta(true);
                    ed1.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                    daoe.crear(ed1);
                    break;
                case 2:
                    boolean upd = true;
                    Editorial ed2 = new Editorial();
                    ed2 = daoe.buscarPorNombre(ee.ingresarNombre());
                    while (upd == true) {
                        byte op1=0;
                        System.out.println(ed2);
                        System.out.println("Si desea modificar el nombre de la Editorial, presione 1.\n"
                            + "Si desea modificar el estado de alta o baja de la Editorial, presione 2.\n"
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
                                ed2.setNombre(ee.ingresarNuevoNombre());
                                break;
                            case 2:
                                ed2.setAlta(ee.ingresarAlta());
                                break;
                            case 3:
                                upd = false;
                                break;
                        }
                    }
                    daoe.modificar(ed2);
                    break;
                case 3:
                    Editorial ed3 = new Editorial();
                    ed3 = daoe.buscarPorNombre(ee.ingresarNombreEliminar());
                    daoe.eliminar(ed3);
                    break;
                case 4:
                    Editorial ed4 = new Editorial();
                    ed4 = daoe.buscarPorNombre(ee.ingresarNombre());
                    System.out.println(ed4);
                    break;
                case 5:
                    List <Editorial> portFolBuk = new ArrayList(daoe.listar());
                    Iterator<Editorial> it = portFolBuk.iterator();
                    int counter = 1;
                    while(it.hasNext()) {
                        Editorial edi = it.next();
                        System.out.println(counter + ")" + edi);
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
}