package edu.upc.prop.cluster23.presentation;

import edu.upc.prop.cluster23.domaincontrollers.CtrlDominio;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * Clase Tview
 * Vista por terminal del programa
 * @author Rubén Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 */

public class Tview {

    private CtrlDominio controladorDominio;
    private Scanner input;

    /**
     * Constructora
     */

    public Tview(){
        controladorDominio = new CtrlDominio();
        input = new Scanner(System.in);
    }

    /**
     * Inicializa el controlador de dominio y llama a la función que ejecuta los comandos
     */

    public void init() {
        controladorDominio.inicializarCtrlDominio();
        System.out.println("Bienvenido al generador de teclados - terminal edition");
        run();
    }

    public void run() {

        int value = -1;

        while (value != 3) {

            System.out.println("Selecciona una opción: 1. Teclados, 2. Alfabetos, 3. Salir");

            value = input.nextInt();

            if (value != 3) System.out.println("¿Qué deseas hacer?");
            switch (value) {
                case 1:
                    System.out.println("TECLADO:");
                    System.out.println("1. Crear\n2. Modificar\n3. Borrar\n4. Mostrar\n5. Ver todos\n6. Atrás");

                    int opt = input.nextInt();

                    switch (opt) {
                        case 1:
                            FuncCreacionTeclado();
                            break;

                        case 2:
                            System.out.println("1. Swap de teclas\n2. Cambiar algoritmo" +
                                    "(NO IMPLEMENTADO EN ESTA PRIMERA ENTREGA)\n" +
                                    "3. Cambiar texto\n 4. Cambiar lista de palabras\n 5.Atrás");

                            int modOpt = input.nextInt();

                            switch (modOpt) {
                                case 1:
                                    FuncSwapTeclas();
                                    break;

                                case 2:

                                    break;

                                case 3:

                                    break;

                                case 4:

                                    break;

                                case 5:
                                    break;
                            }

                            break;

                        case 3:
                            FuncBorrarTeclado();
                            break;

                        case 4:
                            FuncMostrarTeclado();
                            break;

                        case 5:
                            System.out.println(controladorDominio.consultarNombreTeclados());
                            break;

                        case 6:
                            break;

                        default:
                            System.out.println("Valor inválido");
                    }
                    break;
                case 2:
                    System.out.println("ALFABETO:");
                    System.out.println("1. Crear\n2. Modificar\n3. Borrar\n4. Atrás");

                    int optTec = input.nextInt();

                    switch (optTec) {
                        case 1:
                            FuncCreacionAlfabeto();
                            break;

                        case 2:
                            FuncModificarAlfabeto();
                            break;

                        case 3:
                            FuncBorrarAlfabeto();
                            break;

                        case 4:
                            break;
                    }
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
        else if (Objects.equals(alg, "QAP") || Objects.equals(alg, "SA")) {
            controladorDominio.creaTeclado(name,idAlf,text,list,alg);
        }
        else System.out.println("Nombre de algoritmo inválido");
    }

    void FuncSwapTeclas() {
        System.out.println("Indica el teclado y escribe la fila y columna del primer " +
                "carácter y fila y columna del segundo carácter");

        input.nextLine();

        String name = input.nextLine();

        int f1,c1,f2,c2;

        f1 = input.nextInt();
        c1 = input.nextInt();
        f2 = input.nextInt();
        c2 = input.nextInt();

        controladorDominio.intercambiarTeclasTeclado(name,f1,c1,f2,c2);
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

    void FuncMostrarTeclado() {
        System.out.println("Introduce el nombre del teclado a mostrar:");
        input.nextLine();

        String name = input.nextLine();

        String keyboard = controladorDominio.consultarDistribucionDeTeclado(name);

        System.out.println(keyboard);
    }

    void FuncCreacionAlfabeto() {

        System.out.println("Escribe el nombre y los caractéres del alfabeto a crear");

        input.nextLine();

        String name = input.nextLine();
        String idAlf = input.nextLine();

        controladorDominio.añadirAlfabeto(name,idAlf);
    }

    void FuncModificarAlfabeto() {

        System.out.println("Escribe el nombre y los caractéres del alfabeto a modificar");

        input.nextLine();

        String name = input.nextLine();
        String idAlf = input.nextLine();

        controladorDominio.modificarAlfabeto(name,idAlf);
    }

    void FuncBorrarAlfabeto() {
        System.out.println("Escribe el nombre del alfabeto a borrar");

        input.nextLine();

        String name = input.nextLine();

        controladorDominio.eliminarAlfabeto(name);
    }
}
