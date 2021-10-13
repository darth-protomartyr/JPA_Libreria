/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_libreria.entradas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import jpa_libreria.entidades.Autor;
import jpa_libreria.entidades.Editorial;
import jpa_libreria.entidades.Libro;
import jpa_libreria.persistencia.AutorDAO;
import jpa_libreria.persistencia.EditorialDAO;
import jpa_libreria.servicios.ServicioAutor;
import jpa_libreria.servicios.ServicioLibro;

/**
 *
 * @author Gonzalo
 */
public class EntradaLibro {
    Scanner read = new Scanner(System.in).useDelimiter("\n");
    EditorialDAO daoe = new EditorialDAO();
    AutorDAO daoa = new AutorDAO();
    EntradaAutor ea = new EntradaAutor();
    ServicioAutor sa = new ServicioAutor();
    //ServicioLibro serli = new ServicioLibro();
    EntradaEditorial ee = new EntradaEditorial();
    String strKey;
    
    public Libro crearLibro() throws Exception{
        Libro ubik =  new Libro();
        System.out.println("Ingrese el nombre del Libro:");
        String tit = read.next();
        ubik.setTitulo(tit);
        ubik.setIsbn(isbnGenerator());
        int ep = (int) (Math.random() * 50);
        ubik.setEjemplaresPrestados(ep);
        int er = 100 - ep;
        ubik.setEjemplaresRestantes(er);
        ubik.setAlta(true);
        Autor buk = new Autor();
        buk = findWriter();
        ubik.setAutor(buk);
        Editorial edit = new Editorial();
        edit = findPublisher();
        ubik.setEditorial(edit);
        if (ubik == null) {
            ubik = crearLibro();
        }
        return ubik;
    }

    public String ingresarNombre() {
        System.out.println("Ingrese el Nombre del Libro que desea consultar:");
        String nom = read.next();
        return nom;
    }
    
    public long ingresarISBN() {
        System.out.println("Ingrese el ISBN del Libro que desea consultar:");
        long nom = read.nextLong();
        return nom;
    }
    
    public String ingresarNuevoNombre() {
        System.out.println("Ingrese el nuevo Nombre del Libro:");
        String nom = read.next();
        return nom;
    }
    
    public String ingresarNombreEliminar() {
        System.out.println("Ingrese el Nombre del Libro que desea eliminar:");
        String nom = read.next();
        return nom;
    }

    public Boolean ingresarAlta() {
        boolean alta;
        byte op = 0;
        do {
            System.out.println("Si desea dar baja al Libro, presione 1.\n"
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

    public Libro modificarPrestamo(Libro libro) {
        Libro ubik = new Libro();
        System.out.println("Si desea actualizar los retiros, presione 1\n"
                + "Si desea actualizar las devoluciones, presione 2"
                + "Si desea, regresar al menu anteroir, presione 3");
        System.out.println("Ingrese la operación que desea realizar:");
        int op = read.nextInt();
        boolean menu = true;
        int rest;
        int prest;
        while (menu == true) {
            switch (op) {
                case 1:
                    System.out.println("ingrese la cantidad de Libros que serán retirados");
                    rest = read.nextInt();
                    rest = ubik.getEjemplaresRestantes() - rest;
                    prest = 100 - rest;
                    ubik.setEjemplaresRestantes(rest);
                    ubik.setEjemplaresPrestados(prest);
                case 2:
                    System.out.println("Ingrese la cantidad de Libros que serán devueltos");
                    prest = read.nextInt();
                    prest = ubik.getEjemplaresPrestados() - prest;
                    rest = 100 - prest;
                    ubik.setEjemplaresPrestados(prest);
                    ubik.setEjemplaresRestantes(rest);
                case 3:
                    menu = false;
            }
        }
        return ubik;
    }

    public Editorial findPublisher() throws Exception {
        List <Editorial> portFolEd = new ArrayList(daoe.listar());
        Editorial edi = new Editorial();
        System.out.println("\nSeleccione uno de las siguientes editoriales o ingrese una nueva:");
        Iterator<Editorial> it = portFolEd.iterator();
        int counter = 1;
        while(it.hasNext()) {
            Editorial ed = it.next();
            System.out.println(counter + ")" + ed.getNombre());
            counter +=1;
            }
        System.out.println(counter + ")Ingrese una nueva editorial");
        byte op = read.nextByte();
        if (op == counter) {
            edi.setNombre(ee.ingresarNombre());
            edi.setAlta(true);
            strKey = UUID.randomUUID().toString().replaceAll("-", "");
            edi.setId(strKey);
            daoe.crear(edi);
        } else {
            edi = portFolEd.get(op-1); 
        }
        return edi;
    }

    public Autor findWriter() throws Exception {
        List <Autor> portFolWr = new ArrayList(daoa.listar());
        Autor wr = new Autor();
        System.out.println("\nSeleccione uno de los siguientes autores o ingrese uno nuevo:");
        Iterator<Autor> it = portFolWr.iterator();
        int counter = 1;
        while(it.hasNext()) {
            Autor wri = it.next();
            System.out.println(counter + ")" + wri.getNombre());
            counter +=1;
            }
        System.out.println(counter + ")Ingrese un nuevo Autor");
        byte op = read.nextByte();
        if (op == counter) {
            wr = sa.crearAutor();
            daoa.crear(wr);
        } else {
            try {
                wr = portFolWr.get(op-1);
            } catch (IndexOutOfBoundsException ex) {
                    System.out.println("No ingresó uno de los números indicados.\n"
                            + "Seleccione un acción.");
                    read.nextLine(); //Limpia Buffer de entrada
                    throw new Exception("No pudo setear el autor."); 
                    //crearLibro();
                    //read.nextLine(); //Limpia Buffer de entrada

            }
        }
        return wr;
    }

    private Long isbnGenerator() {
        long isbn = (long) (Math.random() * 899999999) + 100000000;
        return isbn;
    }
}