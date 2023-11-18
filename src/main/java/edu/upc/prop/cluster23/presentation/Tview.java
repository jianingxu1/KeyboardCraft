package edu.upc.prop.cluster23.presentation;
import edu.upc.prop.cluster23.domaincontrollers.CtrlDominio;
import edu.upc.prop.cluster23.exceptions.*;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Clase Tview
 * Vista por terminal del programa
 * @author Rubén Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 * @co-authors Yasin  & Momin
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
        System.out.println("Bienvenido al generador de teclados - Terminal Edition");
        run();
    }

    public void run() {

        int value = -1;

        while (value != 3) {

            System.out.println("Selecciona una opción: | 1. Teclados | 2. Alfabetos | 3. Salir |");

            value = input.nextInt();

            if (value != 3) System.out.println("¿Qué deseas hacer?");
            switch (value) {
                case 1:
                    //FUNCIONES DE LOS TECLADOS
                    System.out.println("----- TECLADO ----- ");
                    System.out.println("1. Crear\n2. Modificar\n3. Borrar\n4. Mostrar\n5. Ver todos\n6. Atrás");

                    int opt = input.nextInt();

                    switch (opt) {
                        //CASO CREAR TECLADO
                        case 1:
                            FuncCreacionTeclado();
                            break;
                        //CASO MODIFICAR TECLADOS (SWAP unicamente)
                        case 2:
                            
                            System.out.println("1. Swap de teclas\n2. Atrás");

                            int modOpt = input.nextInt();

                            switch (modOpt) {
                                case 1:
                                    FuncSwapTeclas();
                                    break;

                                case 2:
                                    break;

                                default:
                                    System.out.println("Valor inválido");
                            }

                            break;
                        //CASO BORRAR TECLADOS
                        case 3:
                            FuncBorrarTeclado();
                            break;

                        //CASO MOSTRAR UN TECLADO
                        case 4:
                            FuncMostrarTeclado();
                            break;
                        //CASO MOSTRAR TODOS LOS TECLADOS
                        case 5:
                            FuncMostrarNombresTeclados();
                            break;
                        //CASO VOLVER ATRAS
                        case 6:
                            break;

                        default:
                            System.out.println("Valor inválido");
                    }
                    break;

                //FUNCIONES DE ALFABETO
                case 2:
                    System.out.println("ALFABETO:");
                    System.out.println("1. Crear\n2. Modificar\n3. Borrar\n4. Mostrar\n5.Atrás");

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
                            FuncMostrarAlfabetos();
                            break;
                        default:
                            System.out.println("Valor inválido");
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
        boolean cancelarOperacion = false;
        while (!cancelarOperacion) {
            try {
                controladorDominio.creaTeclado(name, idAlf, text, list, alg);
                cancelarOperacion = true;
                System.out.println("Teclado con nombre: " + name + " creado con éxito!");
            } catch (NombreAlfabetoNoExisteExcepcion e) {
                System.out.println(e.getMessage());
                System.out.println("Recuerda que el alfabeto ha de existir antes de ser asignado. ¿Quieres intentar de nuevo?(s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("El teclado con nombre: " + name + " no ha sido creado.");
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce de nuevo el alfabeto a usar:");
                    idAlf = input.nextLine();
                }
            } catch (NombreTecladoDuplicadoExcepcion ntd) {
                System.out.println(ntd.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un nombre distinto? (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("El teclado con nombre: " + name + " no ha sido creado.");
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente el nombre del teclado:");
                    name = input.nextLine();
                }
            } catch (TipoAlgoritmoIncorrectoExcepcion e) {
                System.out.println(e.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un algoritmo de nuevo, recuerda que el alfabeto ha de existir (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("El teclado con nombre: " + name + " no ha sido creado.");
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente el algoritmo a usar, debe ser QAP o SA:");
                    alg = input.nextLine();
                }
            } catch (FrecuenciaIncorrectaExcepcion fi) {
                System.out.println(fi.getMessage());
                System.out.println("¿Quieres intentar de nuevo y añadir lista de palabras con frecuencias? (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("El teclado con nombre: " + name + " no ha sido creado.");
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente la lista de palabras con frecuencias:");
                    list = input.nextLine();
                }
            }
        }
    }

    void FuncSwapTeclas() {
        System.out.println("Indica el teclado y escribe la fila y columna del primer " +
                "carácter y fila y columna del segundo carácter:");
        input.nextLine();
        String name = input.nextLine();
        int f1, c1, f2, c2;
        boolean cancelarOperacion = false;
        while (!cancelarOperacion) {
            try {
                f1 = input.nextInt();
                c1 = input.nextInt();
                f2 = input.nextInt();
                c2 = input.nextInt();
                input.nextLine();
                controladorDominio.intercambiarTeclasTeclado(name, f1, c1, f2, c2);
                cancelarOperacion = true;
                System.out.println("Teclas intercambiadas con éxito!");
            } catch (InputMismatchException ime) {
                System.out.println("El valor introducido no es un entero.");
                System.out.println("¿Quieres intentar de nuevo y escoger una posición válida? (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("El teclado con nombre: " + name + " no ha sido modificado.");
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente las posiciones de las teclas:");
                }
            } catch (NombreTecladoNoExisteExcepcion ntd) {
                System.out.println(ntd.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un nombre distinto? (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("El teclado con nombre: " + name + " no se ha modificado.");
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente el nombre del teclado.");
                    name = input.nextLine();
                }
            } catch (IndiceTeclaFueraDeRangoExcepcion itf) {
                System.out.println(itf.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger una posición válida? (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("El teclado con nombre: " + name + " no ha sido creado.");
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente las posiciones de las teclas.");
                }
            }
        }
    }

    void FuncBorrarTeclado() {
        System.out.println("Introduce el nombre del teclado a borrar:");
        input.nextLine();
        boolean cancelarOperacion = false;
        while (!cancelarOperacion) {
            try {
                String nameDel = input.nextLine();
                controladorDominio.borrarTeclado(nameDel);
                cancelarOperacion = true;
                System.out.println("Teclado con nombre: " + nameDel + " borrado con éxito!");
            } catch (NombreTecladoNoExisteExcepcion ntd) {
                System.out.println(ntd.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un nombre distinto? (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("No se ha borrado ningun teclado.");
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente el nombre del teclado.");
                }
            }
        }
    }

    void FuncMostrarTeclado() {
        System.out.println("Introduce el nombre del teclado a mostrar:");
        input.nextLine();
        boolean cancelarOperacion = false;
        while (!cancelarOperacion) {
            try {
                String name = input.nextLine();
                String keyboard = controladorDominio.consultarDistribucionDeTeclado(name);
                System.out.println(keyboard);
                cancelarOperacion = true;
            } catch (NombreTecladoNoExisteExcepcion ntd) {
                System.out.println(ntd.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un nombre distinto? (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente el nombre del teclado.");
                }
            }
        }
    }

    void FuncMostrarAlfabetos() {
        System.out.println(controladorDominio.consultarNombresYCaracteresDeAlfabetos());
    }

    void FuncMostrarNombresTeclados() {
        System.out.println(controladorDominio.consultarNombresTeclados());
    }

    void FuncCreacionAlfabeto() {
        System.out.println("Escribe el nombre y los caractéres del alfabeto a crear:");
        input.nextLine();
        String name = input.nextLine();
        String idAlf = input.nextLine();
        boolean cancelarOperacion = false;
        while (!cancelarOperacion) {
            try {
                controladorDominio.añadirAlfabeto(name, idAlf);
                cancelarOperacion = true;
                System.out.println("Alfabeto con nombre: " + name + " creado con éxito!");
            } catch (NombreAlfabetoDuplicadoExcepcion nad) {
                System.out.println(nad.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un nombre distinto? (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("El alfabeto con nombre: " + name + " no ha sido creado.");
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente el nombre del alfabeto:");
                    name = input.nextLine();
                }
            }
        }
    }

    void FuncModificarAlfabeto() {
        System.out.println("Escribe el nombre y los caractéres del alfabeto a modificar:");
        input.nextLine();
        boolean cancelarOperacion = false;
        while (!cancelarOperacion) {
            try {
                String name = input.nextLine();
                String idAlf = input.nextLine();
                controladorDominio.modificarAlfabeto(name, idAlf);
                cancelarOperacion = true;
                System.out.println("Alfabeto con nombre: " + name + " modificado con éxito!");
            } catch (NombreAlfabetoNoExisteExcepcion nae) {
                System.out.println(nae.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un nombre distinto? (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("No se ha modificado ningun alfabeto.");
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente el nombre del alfabeto a modificar:");
                }
            }
        }
    }

    void FuncBorrarAlfabeto() {
        System.out.println("Escribe el nombre del alfabeto a borrar");
        input.nextLine();
        boolean cancelarOperacion = false;
        while (!cancelarOperacion) {
            try {
                String name = input.nextLine();
                controladorDominio.eliminarAlfabeto(name);
                cancelarOperacion = true;
                System.out.println("Alfabeto con nombre: " + name + " borrado con éxito!");
            } catch (NombreAlfabetoNoExisteExcepcion nae) {
                System.out.println(nae.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un nombre distinto? (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("No se ha borrado ningun alfabeto.");
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente el nombre del alfabeto a borrar:");
                }
            }
        }
    }
}
