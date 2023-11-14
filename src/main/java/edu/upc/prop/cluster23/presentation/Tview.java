package edu.upc.prop.cluster23.presentation;

import edu.upc.prop.cluster23.domaincontrollers.CtrlDominio;

import java.util.Objects;
import java.util.Scanner;

public class Tview {

    private CtrlDominio controladorDominio;
    private Scanner input;

    public Tview(){
        controladorDominio = new CtrlDominio();
        input = new Scanner(System.in);
    }

    public void init() {
        controladorDominio.inicializarCtrlDominio();
        System.out.println("Bienvenido al generador de teclados - terminal edition");
        run();
    }

    public void run() throws IllegalStateException {

        int value = -1;

        while (value != 3) {

            System.out.println("Selecciona una opción: 1. Teclados, 2. Alfabetos, 3. Salir");

            value = input.nextInt();

            if (value != 3) System.out.println("¿Qué deseas hacer?");
            switch (value) {
                case 1:
                    System.out.println("TECLADO:");
                    System.out.println("1. Crear\n2. Modificar\n3. Borrar");

                    int opt = input.nextInt();

                    switch (opt) {
                        case 1:
                            FuncCreacionTeclado();
                            break;

                        case 2:
                            // code
                            break;

                        case 3:
                            FuncBorrarTeclado();
                            break;
                    }
                    break;
                case 2:
                    System.out.println("ALFABETO:");
                    System.out.println("1. Crear\n2. Modificar\n3. Borrar");
                    break;

                case 3:
                    System.out.println("Cerrando programa...");
                    break;

                default:
                    System.out.println("Valor inválido");
            }
        }
    }

    void FuncCreacionTeclado() {
        System.out.println("Introduce los siguientes parámetros del nuevo teclado:\n" +
                "Nombre del teclado y alfabeto a usar, texto y lista de palabras, " +
                "algoritmo a usar (auto: QAP)");

        input.nextLine();

        String name = input.nextLine();
        String idAlf = input.nextLine();
        String text = input.nextLine();
        String list = input.nextLine();
        String alg = input.nextLine();

        if (!controladorDominio.existeAlfabeto(idAlf)) {
            System.out.println("El alfabeto no existe");
        }
        else if (!Objects.equals(alg, "QAP")) {
            System.out.println("Nombre de algoritmo inválido");
        }
        else controladorDominio.creaTeclado(name,idAlf,text,list,alg);
    }
    void FuncBorrarTeclado() {
        System.out.println("Introduce el nombre del teclado a borrar:");

        input.nextLine();

        String nameDel = input.nextLine();

        if (!controladorDominio.existeTeclado(nameDel)) {
            System.out.println("El teclado no existe");
        }
        else {
            controladorDominio.borrarTeclado(nameDel);
            System.out.println("El teclado " + nameDel + " ha sido borrado");
        }
    }
}
