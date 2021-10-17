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
        byte op = 0;
        do {
            try {
                System.out.println("\nAdministrador Editoriales:\n"
                    + "Si desea agregar una nueva Editorial, presione 1\n"
                    + "Si desea modificar una Editorial listada, presione 2\n"
                    + "Si desea eliminar una Editorial listada, presione 3\n"
                    + "Si desea consultar una Editorial listada, presione 4\n"
                    + "Si desea consultar la lista de Editoriales, presione 5\n"
                    + "Si desea regresar al menu anterior, presione 6");
                System.out.println("Ingrese el número de la operación que desea realizar:");
                op = read.nextByte();
                switch(op) {
                    case 1:
                        Editorial ed1 = new Editorial();
                        ed1.setNombre(ee.ingresarNombre());
                        ed1.setAlta(true);
                        ed1.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                        daoe.crear(ed1);
                        break;
                    case 2:
                        Editorial ed2 = new Editorial();
                        ed2 = daoe.buscarPorNombre(ee.ingresarNombre());
                        System.out.println(ed2);
                        byte op1=0;
                        do {
                            try {
                                System.out.println("Si desea modificar el nombre de la Editorial, presione 1.\n"
                                    + "Si desea modificar el estado de alta o baja de la Editorial, presione 2.\n"
                                    + "Si desea guardar los cambios y volver al menú anterior, presione 3.\n");
                                System.out.println("Ingrese el número de la operación que desea realizar:");
                                op1 = read.nextByte();
                                switch (op1) {
                                    case 1:
                                        ed2.setNombre(ee.ingresarNuevoNombre());
                                        break;
                                    case 2:
                                        ed2.setAlta(ee.ingresarAlta());
                                        break;
                                    case 3:
                                        System.out.println("Usted regresará al menú anterior");
                                        break;
                                    default:
                                        System.out.println("Ingrese un valor entre 1 y 3");
                                }
                            } catch (InputMismatchException ex) {
                                System.out.println("Usted no ingresó un valor numérico1111.\n");
                                read.nextLine();
                            }
                        } while(op1 != 3);
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
                        System.out.println("Usted regresará al menú anterior");
                        break;
                    default :
                        System.out.println("Ingrese un valor entre 1 y 6");
                }
            }catch (InputMismatchException ex) {
                System.out.println("Usted no ingresó un valor numérico222.\n"
                    + "Elija una operacion nuevamente.");
                System.out.println("Error: " + ex.toString());
                read.nextLine();
            } catch (NoResultException ex) {
                System.out.println("La consulta que realizó no arrojó ninguna coincidencia.\n"
                    + "Revise la lista y repita la operación, o seleccione otra.");
                read.nextLine();
            }
        } while (op != 6);
    }
}