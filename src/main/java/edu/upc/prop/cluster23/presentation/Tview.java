package edu.upc.prop.cluster23.presentation;

import edu.upc.prop.cluster23.domaincontrollers.CtrlDominio;

import java.util.Scanner;

public class Tview {

    private CtrlDominio controladorDominio;

    public Tview(){
        controladorDominio = new CtrlDominio();
    }

    public void run() {
        controladorDominio.inicializarCtrlDominio();

        System.out.println("Bienvenido al generador de teclados - terminal edition");

        Scanner input = new Scanner(System.in);

        System.out.println("Selecciona una opción: 1. Teclados, 2. Alfabetos, 3. Textos, 4. Palabras con frecuencia");

        int value = input.nextInt();

        System.out.println("¿Qué deseas hacer?");

        switch (value) {
            case 1:
                System.out.println("TECLADO:");
                System.out.println("1. Crear\n"
                + "2. Modificar\n"
                + "3. Borrar");
                break;
            case 2:
                System.out.println("ALFABETO:");
                System.out.println("1. Crear\n"
                        + "2. Modificar\n"
                        + "3. Borrar");
                break;
            case 3:
                System.out.println("TEXTO:");
                System.out.println("1. Crear\n"
                        + "2. Modificar\n"
                        + "3. Borrar");
                break;
            case 4:
                System.out.println("PALABRAS CON FRECUENCIA:");
                System.out.println("1. Crear\n"
                        + "2. Modificar\n"
                        + "3. Borrar");
                break;
        }
    }
}
