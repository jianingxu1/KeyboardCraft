package presentation;

import java.util.InputMismatchException;
import java.util.Scanner;

import domaincontrollers.CtrlDominio;
import exceptions.*;

/**
 * Clase VistaTerminal
 * Vista por terminal del programa
 * 
 * @author Rubén Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 * @co-authors Muhammad Yasin Khokar & Momin Miah Begum
 */

public class VistaTerminal {

    private CtrlDominio controladorDominio;
    private Scanner input;

    /**
     * Constructora
     */

    public VistaTerminal() {
        controladorDominio = new CtrlDominio();
        input = new Scanner(System.in);
    }

    /**
     * Inicializa  el controlador de dominio y llama a la función que ejecuta los
     * comandos
     */

    public void init() {
        controladorDominio.inicializarCtrlDominio();
        System.out.println("\n*--------* Bienvenido al generador de teclados *--------*");
        System.out.println(
                "Para navegar a través del programa, introduce el número de la\nopción que desees en cada momento.\n");
        menuPrincipal();
    }

    /**
     * Menú desde el cual se puede acceder a las zonas de gestión de teclados y alfabetos
     * así como las diferentes funciones que proporcionan estas
     */

    public void menuPrincipal() {

        int value = -1;

        while (value != 0) {
            System.out.println("-------- Menú Principal --------");
            System.out.println("1. Gestionar teclados\n2. Gestionar alfabetos\n\n0. Salir del programa");
            System.out.println("--------------------------------");
            try {
                value = input.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println("El valor introducido no es un entero.");
                input.nextLine();
                continue;
            }
            switch (value) {
                case 1:
                    // FUNCIONES DE LOS TECLADOS
                    int opt = -1;
                    while (opt != 0) {
                        System.out.println("----- Gestión de Teclados -----");
                        System.out.println(
                                "1. Crear teclado\n2. Modificar teclado existente\n3. Borrar teclado existente\n4. Mostrar teclado existente\n5. Consultar todos los teclados existente\n\n0. Volver al menú principal");
                        System.out.println("-------------------------------");
                        try {
                            opt = input.nextInt();
                            input.nextLine();
                        } catch (InputMismatchException ime) {
                            System.out.println("El valor introducido no es un entero.\n");
                            input.nextLine();
                            continue;
                        }
                        switch (opt) {
                            // CASO CREAR TECLADO
                            case 1:
                                FuncCreacionTeclado();
                                break;
                            // CASO MODIFICAR TECLADOS (SWAP unicamente)
                            case 2:
                                if (controladorDominio.consultarNombresTeclados().isEmpty()) {
                                    System.out.println(
                                            "No tienes ningún teclado creado. La modificación de teclado requiere que exista un teclado.");
                                }
                                else {
                                    System.out.println("----- Modificación de teclado -----");
                                    System.out.println("1. Swap de teclas\n\n0. Volver a la gestión de teclados");
                                    System.out.println("-----------------------------------");
                                    int modOpt = -1;
                                    try {
                                        modOpt = input.nextInt();
                                        input.nextLine();
                                    } catch (InputMismatchException ime) {
                                        System.out.println("El valor introducido no es un entero.\n");
                                        input.nextLine();
                                        continue;
                                    }
                                    switch (modOpt) {
                                    case 1:
                                        FuncSwapTeclas();
                                        break;

                                    case 0:
                                        break;

                                    default:
                                        System.out.println("Valor inválido.");
                                    }
                                }
                                break;

                        // CASO BORRAR TECLADOS
                            case 3:
                                FuncBorrarTeclado();
                                break;

                            // CASO MOSTRAR UN TECLADO
                            case 4:
                                FuncMostrarTeclado();
                                break;
                            // CASO MOSTRAR TODOS LOS TECLADOS
                            case 5:
                                FuncMostrarNombresTeclados();
                                break;
                            // CASO VOLVER ATRAS
                            case 0:
                                break;
                            default:
                                System.out.println("Valor inválido.");
                        }
                    }
                    break;
                // FUNCIONES DE ALFABETO
                case 2:
                    int optTec = -1;
                    while (optTec != 0) {
                        System.out.println("----- Gestión de Alfabetos -----");
                        System.out.println(
                                "1. Crear alfabeto\n2. Modificar alfabeto existente\n3. Borrar alfabeto existente\n4. Mostrar alfabetos\n5. Consultar caracteres alfabeto\n\n0. Volver al menú principal");
                        System.out.println("--------------------------------");
                        try {
                            optTec = input.nextInt();
                            input.nextLine();
                        } catch (InputMismatchException ime) {
                            System.out.println("El valor introducido no es un entero.\n");
                            input.nextLine();
                            continue;
                        }
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
                            case 5:
                                FuncMostrarCaracteresAlfabeto();
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Valor inválido.");
                        }
                    }
                    break;
                // CASO SALIR DEL PROGRAMA
                case 0:
                    System.out.println("Cerrando programa...");
                    break;

                default:
                    System.out.println("¡Número de opción inválido! Vuelve a probar.\n");
            }
        }
    }

    /**
     * Función que mediante unos parámetros recibidos como input, genera y guarda un teclado
     */

    void FuncCreacionTeclado() {
        String alfabetos = controladorDominio.consultarNombresDeAlfabetos();
        if (alfabetos.isEmpty()) {
            System.out.println("No tienes ningún alfabeto creado. La creación de teclado requiere de un alfabeto.");
            return;
        }

        System.out.println("Teclados actuales:\n" +
                        controladorDominio.consultarNombresTeclados());
        System.out.println("Introduce el nombre del nuevo teclado:");
        String name = input.nextLine();

        System.out.println("Alfabetos actuales:\n" +
                        controladorDominio.consultarNombresDeAlfabetos());

        System.out.println("Introduce el nombre del alfabeto a utilizar:");
        String idAlf = input.nextLine();

        System.out.println("Introduce el texto para calcular frecuencias (ej: 'hola buenas tardes'):");
        String text = input.nextLine();

        System.out.println("Introduce la lista de palabras con frecuencias (ej: 'coche 20 perro 15 teclado 200'):");
        String list = input.nextLine();

        System.out.println("Introduce el nombre del algoritmo a utilizar (QAP o SA):\n" +
                "NOTA: Los alfabetos con más de 12 caractéres pueden tardar +10 minutos con QAP.\n" +
                "Se recomienda SA en estos casos.");
        String alg = input.nextLine();
        boolean cancelarOperacion = false;
        while (!cancelarOperacion) {
            try {
                controladorDominio.creaTeclado(name, idAlf, text, list, alg);
                cancelarOperacion = true;
                System.out.println("¡Se ha creado el teclado \"" + name + "\" con éxito!");

                String keyboard = controladorDominio.consultarDistribucionDeTeclado(name);
                System.out.println(keyboard);

            } catch (NombreAlfabetoNoValidoExcepcion e) {
                System.out.println(e.getMessage());
                System.out.println(
                        "Recuerda que el alfabeto ha de existir antes de ser asignado. ¿Quieres intentar de nuevo?(s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("El teclado \"" + name + "\" no ha sido creado.\n");
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce de nuevo el alfabeto a usar:");
                    idAlf = input.nextLine();
                }
            } catch (NombreTecladoDuplicadoExcepcion ntd) {
                System.out.println(ntd.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un nombre válido? (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("El teclado \"" + name + "\" no ha sido creado.\n");
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente el nombre del teclado:");
                    name = input.nextLine();
                }
            } catch (TipoAlgoritmoIncorrectoExcepcion e) {
                System.out.println(e.getMessage());
                System.out.println(
                        "¿Quieres intentar de nuevo y escoger un algoritmo de nuevo, recuerda que el alfabeto ha de existir (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("El teclado \"" + name + "\" no ha sido creado.\n");
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
                    System.out.println("El teclado \"" + name + "\" no ha sido creado.\n");
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente la lista de palabras con frecuencias:");
                    list = input.nextLine();
                }
            } catch (NombreTecladoNoValidoExcepcion ntv) {
                System.out.println(ntv.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un nombre válido? (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("El teclado \"" + name + "\" no ha sido creado.\n");
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente el nombre del teclado:");
                    name = input.nextLine();
                }
            }
        }
    }

    /**
     * Función modificadora que gira la posición de dos teclas existentes de un teclado
     */

    void FuncSwapTeclas() {

        System.out.println("Teclados actuales:\n" +
                controladorDominio.consultarNombresTeclados());

        System.out.println("Indica el nombre del teclado:");
        int f1, c1, f2, c2;
        boolean cancelarOperacion2 = false;

        String name = input.nextLine();

        while (!cancelarOperacion2) {
            try {
                String keyboard = controladorDominio.consultarDistribucionDeTeclado(name);
                System.out.println(keyboard);

                System.out.println("Introduce el número de la fila y columna del primer elemento, y la fila y columna " +
                        "del segundo elemento.\n" +
                        "NOTA: Las filas y columnas se numeran desde 0");

                f1 = input.nextInt();
                c1 = input.nextInt();
                f2 = input.nextInt();
                c2 = input.nextInt();
                input.nextLine();
                controladorDominio.intercambiarTeclasTeclado(name, f1, c1, f2, c2);
                cancelarOperacion2 = true;
                System.out.println("¡Se ha intercambiado las teclas del teclado \"" + name + "\" con éxito!\n Esta es la nueva distribución:");
                keyboard = controladorDominio.consultarDistribucionDeTeclado(name);
                System.out.println(keyboard);
            } catch (InputMismatchException ime) {
                input.nextLine();
                System.out.println("El valor introducido no es un entero.");
                System.out.println("¿Quieres intentar de nuevo y escoger una posición válida? (s/n):");
                String response = input.nextLine();

                while (!response.equals("s") && !response.equals("n")) {
                    System.out.println("¿Quieres intentar de nuevo y escoger una posición válida? (s/n):");
                    response = input.nextLine();
                }

                if (response.equals("n")) {
                    System.out.println("El teclado \"" + name + "\" no ha sido modificado.\n");
                    cancelarOperacion2 = true;
                }

            } catch (IndiceTeclaFueraDeRangoExcepcion itf) {
                System.out.println(itf.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger una posición válida? (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("El teclado \"" + name + "\" no ha sido creado.\n");
                    cancelarOperacion2 = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente las posiciones de las teclas.\n");
                }
            } catch (NombreTecladoNoValidoExcepcion ntd) {
                System.out.println("El teclado introducido no existe.");
                System.out.println("¿Quieres intentar de nuevo y escoger una teclado válido? (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("No se ha modificado ningún teclado\n");
                    cancelarOperacion2 = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente el nombre del teclado:");
                    name = input.nextLine();
                }
            }
        }
    }

    /**
     * Borra un teclado de los existentes en el sistema
     */

    void FuncBorrarTeclado() {
        if (controladorDominio.consultarNombresTeclados().isEmpty()) {
            System.out.println("No tienes ningún teclado creado. Para poder borrar, crea uno antes.");
            return;
        }

        System.out.println("Teclados actuales:\n" +
                controladorDominio.consultarNombresTeclados());

        System.out.println("Introduce el nombre del teclado a borrar:");
        boolean cancelarOperacion = false;
        while (!cancelarOperacion) {
            try {
                String nameDel = input.nextLine();
                controladorDominio.borrarTeclado(nameDel);
                cancelarOperacion = true;
                System.out.println("¡Se ha borrado el teclado \"" + nameDel + "\" con éxito!");
            } catch (NombreTecladoNoValidoExcepcion ntd) {
                System.out.println(ntd.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un nombre válido? (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("No se ha borrado ningun teclado.\n");
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente el nombre del teclado.\n");
                }
            }
        }
    }

    /**
     * Se muestra por pantalla la distribución del teclado, del cual se pasa su nombre por input
     */
    void FuncMostrarTeclado() {
        if (controladorDominio.consultarNombresTeclados().isEmpty()) {
            System.out.println("No tienes ningún teclado creado. Para poder mostrar, crea uno antes.");
            return;
        }

        System.out.println("Teclados actuales:\n" +
                controladorDominio.consultarNombresTeclados());

        System.out.println("Introduce el nombre del teclado a mostrar:");
        boolean cancelarOperacion = false;
        while (!cancelarOperacion) {
            try {
                String name = input.nextLine();
                String keyboard = controladorDominio.consultarDistribucionDeTeclado(name);
                System.out.println(keyboard);
                cancelarOperacion = true;
            } catch (NombreTecladoNoValidoExcepcion ntd) {
                System.out.println(ntd.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un nombre válido? (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente el nombre del teclado.\n");
                }
            }
        }
    }

    /**
     * Muestra el nombre de todos los alfabetos creados hasta el momento
     */

    void FuncMostrarAlfabetos() {
        String alfabetos = controladorDominio.consultarNombresDeAlfabetos();
        if (alfabetos.isEmpty())
            System.out.println("No tienes ningún alfabeto creado.\n");
        else {
            System.out.println("Estos son los alfabetos que existen:");
            System.out.println(alfabetos);
        }
    }

    /**
     * Muestra el nombre de todos los teclados creados hasta el momento
     */

    void FuncMostrarNombresTeclados() {
        if (controladorDominio.consultarNombresTeclados().isEmpty()) {
            System.out.println("No tienes ningún teclado creado.");
            return;
        }
        System.out.println("Estos son los teclados que existen:");
        System.out.println(controladorDominio.consultarNombresTeclados());
    }

    /**
     * Crea un nuevo alfabeto mediante un nombre y los caracteres que contendrá pasados por input
     */

    void FuncCreacionAlfabeto() {
        System.out.println("Introduce el nombre que deseas asignar al alfabeto (ej: inglés):");
        String name = input.nextLine();
        System.out.println(
                "Introduce los caracteres que quieres que contenga,\ntodos juntos y sin espacios (ej: abcdef):");
        String idAlf = input.nextLine();
        boolean cancelarOperacion = false;
        while (!cancelarOperacion) {
            try {
                controladorDominio.añadirAlfabeto(name, idAlf);
                cancelarOperacion = true;
                System.out.println("¡Se ha creado el alfabeto \"" + name + "\" con éxito!");
            } catch (NombreAlfabetoDuplicadoExcepcion nad) {
                System.out.println(nad.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un nombre válido? (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("El alfabeto \"" + name + "\" no ha sido creado.\n");
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente el nombre del alfabeto:");
                    name = input.nextLine();
                }
            } catch (NoHayCaracteresExcepcion nae) {
                System.out.println(nae.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger caracteres diferentes? (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("El alfabeto \"" + name + "\" no ha sido creado.\n");
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente los caracteres del alfabeto:");
                    idAlf = input.nextLine();
                }
            } catch (NombreAlfabetoNoValidoExcepcion nae) {
                System.out.println(nae.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un nombre válido? (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("El alfabeto \"" + name + "\" no ha sido creado.\n");
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente el nombre del alfabeto:");
                    name = input.nextLine();
                }
            }
        }
    }

    /**
     * Modifica la lista de símbolos de uno de los alfabetos creados
     */

    void FuncModificarAlfabeto() {
        String alfabetos = controladorDominio.consultarNombresDeAlfabetos();
        if (alfabetos.isEmpty()) {
            System.out.println("No tienes ningún alfabeto creado. Para poder modificar, crea uno antes.");
            return;
        } else {
            System.out.println("Estos son los alfabetos que existen:");
            System.out.println(alfabetos);
        }
        boolean cancelarOperacion = false;
        System.out.println("Introduce el nombre del alfabeto a modificar:");
        String name = input.nextLine();
        System.out.println(
                "Introduce los caracteres que quieres que contenga,\ntodos juntos y sin espacios (ej: abcdef):");
        String idAlf = input.nextLine();
        while (!cancelarOperacion) {
            try {
                controladorDominio.modificarAlfabeto(name, idAlf);
                cancelarOperacion = true;
                System.out.println("¡Se ha modificado el alfabeto \"" + name + "\" con éxito!\nEstos son los nuevos caracteres que contiene:");
                System.out.println(controladorDominio.consultarCaracteresAlfabeto(name));
            } catch (NombreAlfabetoNoValidoExcepcion nae) {
                System.out.println(nae.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un nombre válido? (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("No se ha modificado ningún alfabeto.\n");
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente el nombre del alfabeto a modificar:");
                    name = input.nextLine();
                }
            } catch (NoHayCaracteresExcepcion nae) {
                System.out.println(nae.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger caracteres diferentes? (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("El alfabeto \"" + name + "\" no ha sido creado.\n");
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println(
                            "Introduce nuevamente los caracteres que quieres que contenga,\ntodos juntos y sin espacios (ej: abcdef):");
                    idAlf = input.nextLine();
                }
            }
        }
    }

    /**
     * Borra uno de los alfabetos especificados por el input
     */

    void FuncBorrarAlfabeto() {
        String alfabetos = controladorDominio.consultarNombresDeAlfabetos();
        if (alfabetos.isEmpty()) {
            System.out.println("No tienes ningún alfabeto creado. Para poder borrar, crea uno antes.");
            return;
        } else {
            System.out.println("Estos son los alfabetos que existen:");
            System.out.println(alfabetos);
        }
        System.out.println("Introduce el nombre del alfabeto a borrar:");
        boolean cancelarOperacion = false;
        while (!cancelarOperacion) {
            try {
                String name = input.nextLine();
                controladorDominio.eliminarAlfabeto(name);
                cancelarOperacion = true;
                System.out.println("¡Se ha borrado el alfabeto \"" + name + "\" con éxito!");
            } catch (NombreAlfabetoNoValidoExcepcion nae) {
                System.out.println(nae.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger con un nombre válido? (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    System.out.println("No se ha borrado ningún alfabeto.\n");
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente el nombre del alfabeto a borrar:");
                }
            }
        }
    }

    /**
     * Muestra los caracteres de los que se compone uno de los alfabetos
     */

    void FuncMostrarCaracteresAlfabeto() {
        String alfabetos = controladorDominio.consultarNombresDeAlfabetos();
        if (alfabetos.isEmpty()) {
            System.out.println("No tienes ningún alfabeto creado.");
            return;
        }
        System.out.println("Introduce el nombre del alfabeto del que quieres ver los caracteres:");
        boolean cancelarOperacion = false;
        while (!cancelarOperacion) {
            try {
                String name = input.nextLine();
                String alfabeto = controladorDominio.consultarCaracteresAlfabeto(name);
                System.out.println("Los caracteres del alfabeto \"" + name + "\" son: " + alfabeto);
                cancelarOperacion = true;
            } catch (NombreAlfabetoNoValidoExcepcion nae) {
                System.out.println(nae.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger con un nombre válido? (s/n):");
                String response = input.nextLine();
                if (response.equals("n")) {
                    cancelarOperacion = true;
                } else if (response.equals("s")) {
                    System.out.println("Introduce nuevamente el nombre del alfabeto a consultar:");
                }
            }
        }
    }
}