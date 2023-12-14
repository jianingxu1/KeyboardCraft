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
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */

public class VistaTerminal {

    private CtrlDominio controladorDominio;
    private Scanner input;

    /**
     * Constructora
     */

    public VistaTerminal(CtrlDominio ctrlDominio) {
        this.controladorDominio = ctrlDominio;
        input = new Scanner(System.in);
    }

    /**
     * Inicializa  el controlador de dominio y llama a la función que ejecuta los
     * comandos
     */

     public void init() {
        System.out.println("\n*--------* Bienvenido al generador de teclados *--------*");
        System.out.println("Para navegar a través del programa, introduce el número de la");
        System.out.println("opción que desees en cada momento.\n");
    
        menuIniciarSesion();
    }
    
    public void menuIniciarSesion() {

         System.out.println("-------- Menú de inicio de sesión --------");
         System.out.println("1. Iniciar sesión\n2. Registrarse\n\n0. Salir del programa");
         System.out.println("------------------------------------------");
         seleccionIniciarSesion();
    }

    private void seleccionIniciarSesion() {

        boolean usuarioIdentificado = false;

        int opcion = -1;

        try {
            opcion = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException ime) {
            input.nextLine();
        }

        switch (opcion) {
        case 1:
            usuarioIdentificado = FuncIniciarSesion();
            break;
        case 2:
            FuncRegistrarse();
            break;
        case 0:
            System.out.println("Cerrando programa...");
            FuncEscribirUsuarios();
            // System.out.println("I'm here");
            break; //return;
        default:
            System.out.println("¡Número de opción inválido! Vuelve a probar.\n");
        }

        if (usuarioIdentificado) {
            System.out.println("¡Bienvenido " + controladorDominio.getNombreUsuario() + "!");
            if (opcion != 0) {
                // System.out.println("Volviendo al menú principal...");
                menuPrincipal();
            }
        }
        else if (opcion != 0 ) menuIniciarSesion();
    }

    /**
     * Función que permite a un usuario iniciar sesión en el sistema
     */
    Boolean FuncIniciarSesion() {
        System.out.println("----- Iniciar Sesión -----");
        boolean cancelarOperacion = false;
        boolean usuarioIdentificado = false;
        
        System.out.println("Introduce el nombre de usuario:");
        String nombreUsuario = input.nextLine();
        System.out.println("Introduce la contraseña:");
        String contrasena = input.nextLine();

        while (!cancelarOperacion) {
    
            try {
                usuarioIdentificado = controladorDominio.IniciarSesion(nombreUsuario, contrasena);
                cancelarOperacion = true;
            } catch (NombreUsuarioNoValidoExcepcion e) {
                System.out.println(e.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un nombre válido? (s/n):");
                cancelarOperacion = reintentarOperacionUsuario("nombre de usuario", "iniciado sesión");
                if (!cancelarOperacion) {
                    nombreUsuario = input.nextLine();
                }
            } catch (ContrasenaNoValidaExcepcion e) {
                System.out.println(e.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger una contraseña válida? (s/n):");
                cancelarOperacion = reintentarOperacionUsuario("contraseña", "iniciado sesión");
                if (!cancelarOperacion) {
                    contrasena = input.nextLine();
                }
            }
        }
    
        return usuarioIdentificado;
    }
    
    void FuncRegistrarse() {
        System.out.println("----- Registrarse -----");
        boolean cancelarOperacion = false;
    
        while (!cancelarOperacion) {
            System.out.println("Introduce el nombre de usuario:");
            String nombreUsuario = input.nextLine();
            System.out.println("Introduce la contraseña:");
            String contrasena = input.nextLine();
    
            try {
                controladorDominio.anadirNuevoUsuario(nombreUsuario, contrasena);
                cancelarOperacion = true;
                System.out.println("¡Se ha registrado el usuario \"" + nombreUsuario + "\" con éxito!");
                FuncCargarDatos();
            } catch (NombreUsuarioNoValidoExcepcion e) {
                System.out.println(e.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un nombre válido? (s/n):");
                cancelarOperacion = reintentarOperacionUsuario("nombre de usuario" , "registrado");
                if (!cancelarOperacion) {
                    nombreUsuario = input.nextLine();
                }
            } catch (ContrasenaNoValidaExcepcion e) {
                System.out.println(e.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger una contraseña válida? (s/n):");
                cancelarOperacion = reintentarOperacionUsuario("contraseña", "registrado");
                if (!cancelarOperacion) {
                    contrasena = input.nextLine();
                }
            } catch (EscrituraIncorrectaFicheroExcepcion e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void cerrarSesion() {
        FuncEscribirCambios();
        controladorDominio.CerrarSesion();
    }


    public Boolean reintentarOperacionUsuario(String elementoUsuario, String operacion) {
        String response = input.nextLine();
        
        if (response.equals("n")) {
            System.out.println("No se ha " + operacion + ".\n");
            return true;
        } else if (response.equals("s")) {
            if (elementoUsuario.equals("nombre de usuario")) {
                System.out.println("Introduce nuevamente el " + elementoUsuario + ":");
            }
            else {
                System.out.println("Introduce nuevamente la " + elementoUsuario + ":");
            }
            return false;
        }
    
        if(!response.equals("n") && !response.equals("s")) {
            System.out.println("El valor introducido no es válido.\n" + "volviendo al menú anterior...");
            
        }
        return true;
    }
    
    

    /**
     * Menú desde el cual se puede acceder a las zonas de gestión de teclados y alfabetos
     * así como las diferentes funciones que proporcionan estas
     */

    public void menuPrincipal() {
        FuncCargarDatos();
        System.out.println("-------- Menú Principal --------");
        System.out.println("1. Gestionar teclados\n2. Gestionar alfabetos\n3. Gestionar Usuario\n4. Cerrar Sesión\n\n0. Salir del programa");
        System.out.println("--------------------------------");
        seleccionMenuPrincipal();
    }

    private void seleccionMenuPrincipal() {

        int opcionPrincipal = -1;

        try {
            opcionPrincipal = input.nextInt();
        } catch (InputMismatchException ime) {
            input.nextLine();
        }

        switch (opcionPrincipal) {
        case 1:
            gestionarTeclados();
            break;
        case 2:
            gestionarAlfabetos();
            break;
        case 3:
            gestionarUsuario();
        break;
        case 4:
            cerrarSesion();
            menuIniciarSesion();
            // System.out.println("Volviendo al menú de inicio de sesión...");
            return; //break;
        case 0:
            cerrarSesion();
            FuncEscribirUsuarios();
            break; //return;
        default:
            System.out.println("¡Número de opción inválido! Vuelve a probar.\n");
        }

        if (opcionPrincipal != 0) menuPrincipal();
    }
    
    private void gestionarTeclados() {
        System.out.println("----- Gestión de Teclados -----");
        System.out.println("1. Crear teclado\n2. Modificar teclado existente\n3. Borrar teclado existente\n" +
                "4. Mostrar teclado existente\n5. Consultar todos los teclados existente\n\n0. Volver al menú principal");
        System.out.println("-------------------------------");
        seleccionTeclados();
    }

    private void seleccionTeclados() {

        int opcionTeclado = -1;

        try {
            opcionTeclado = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException ime) {
            input.nextLine();
        }

        switch (opcionTeclado) {
        case 1:
            FuncCreacionTeclado();
            break;
        case 2:
            FuncSwapTeclas();
            break;
        case 3:
            FuncBorrarTeclado();
            break;
        case 4:
            FuncMostrarTeclado();
            break;
        case 5:
            FuncMostrarNombresTeclados();
            break;
        case 0:
            return;
        default:
            System.out.println("Error: Valor inválido.");
        }
        gestionarTeclados();
    }
    
    private void gestionarAlfabetos() {
        System.out.println("----- Gestión de Alfabetos -----");
        System.out.println("1. Crear alfabeto\n2. Modificar alfabeto existente\n3. Borrar alfabeto existente\n" +
                "4. Mostrar alfabetos\n5. Consultar caracteres alfabeto\n\n0. Volver al menú principal");
        System.out.println("--------------------------------");
        seleccionAlfabeto();
    }

    private void seleccionAlfabeto() {

        int opcionAlfabeto = -1;

        try {
            opcionAlfabeto = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException ime) {
            input.nextLine();
        }

        switch (opcionAlfabeto) {
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
            return;
        default:
            System.out.println("Error: Valor inválido.");
        }
        gestionarAlfabetos();
    }

    public void gestionarUsuario() {

        System.out.println("----- Gestión de Usuario -----");
        System.out.println("1. Modificar contraseña\n2. Borrar usuario\n\n0. Volver al menú principal");
        System.out.println("------------------------------");
        seleccionUsuario();
    }

    private void seleccionUsuario() {

        int opcionUsuario = -1;

        try {
            opcionUsuario = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException ime) {
            input.nextLine();
        }

        switch (opcionUsuario) {
        case 1:
            FuncModificarContrasena();
            break;
        case 2:
            FuncBorrarUsuario();
            break;
        case 0:
            return;
        default:
            System.out.println("Error: Valor inválido.");
        }
        gestionarUsuario();
    }

    /**
     * Función que permite al usuario modificar su contraseña
     */
    public void FuncModificarContrasena()  
    {
        System.out.println("----- Modificar Contraseña -----");
        boolean cancelarOperacion = false;

        while (!cancelarOperacion) {
            System.out.println("Introduce el nombre de usuario:");
            String nombreUsuario = input.nextLine();
            System.out.println("Introduce la contraseña actual:");
            String contrasenaActual = input.nextLine();
            System.out.println("Introduce la nueva contraseña:");
            String contrasenaNueva = input.nextLine();

            try {
                controladorDominio.modificarContrasena(nombreUsuario, contrasenaActual, contrasenaNueva);
                cancelarOperacion = true;
                System.out.println("¡Se ha modificado la contraseña con éxito!");
            } catch (ContrasenaNoValidaExcepcion e) {
                System.out.println(e.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger una contraseña válida? (s/n):");
                cancelarOperacion = reintentarOperacionUsuario("contraseña", "modificado la contraseña");
                if (!cancelarOperacion) {
                    contrasenaNueva = input.nextLine();
                }
            }
            catch (NombreUsuarioNoValidoExcepcion e) {
                System.out.println(e.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un nombre válido? (s/n):");
                cancelarOperacion = reintentarOperacionUsuario("nombre de usuario", "modificado la contraseña");
                if (!cancelarOperacion) {
                    nombreUsuario = input.nextLine();
                }
            }
        }
    }

    /**
     * Función que permite al usuario borrar su cuenta
     */
    public void FuncBorrarUsuario()  {
        boolean cancelarOperacion = false;
        System.out.println("----- Borrar Usuario -----");
        System.out.println("¿Estás seguro de que quieres borrar tu cuenta? (s/n):");
        cancelarOperacion = reintentarOperacionUsuario("cuenta", "borrado");

        System.out.println("Por medidas de seguridad, para poder borrar la cuenta inicie" +
                            "de nuevo sesión para poder verificar al usuario.");
        boolean usuarioIdentificado = FuncIniciarSesion();
        
        try {
            if (usuarioIdentificado) {
                System.out.println("Por medidas de seguridad, introduzco el usuario nuevamente:");
                String nombreUsuario = input.nextLine();
                controladorDominio.eliminarUsuario(nombreUsuario);
            }
            else {
            System.out.println("No se ha borrado la cuenta.");
            System.out.println("Volviendo al menú anterior...");
        }
        } catch (NombreUsuarioNoValidoExcepcion e) {
            System.out.println(e.getMessage());
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
    
        // Entrada de datos para la creación del teclado
        System.out.println("Teclados actuales:\n" + controladorDominio.consultarNombresTeclados());
        System.out.println("Introduce el nombre del nuevo teclado:");
        String nombreTeclado = input.nextLine();
    
        System.out.println("Alfabetos actuales:\n" + controladorDominio.consultarNombresDeAlfabetos());
        System.out.println("Introduce el nombre del alfabeto a utilizar:");
        String idAlfabeto = input.nextLine();
    
        System.out.println("Introduce el texto para calcular frecuencias (ej: 'hola buenas tardes'):");
        String textoFrecuencias = input.nextLine();
    
        System.out.println("Introduce la lista de palabras con frecuencias (ej: 'coche 20 perro 15 teclado 200'):");
        String listaFrecuencias = input.nextLine();
    
        System.out.println("Introduce el nombre del algoritmo a utilizar (B&B o SA):\n" +
                "NOTA: Los alfabetos con más de 12 caracteres pueden tardar +10 minutos" +
                "con B&B.\n" + "Se recomienda SA en estos casos.");
    
        String algoritmo = input.nextLine();
        
        boolean cancelarOperacion = false;
        
        while (!cancelarOperacion) {
            
            try {       
                controladorDominio.creaTeclado(nombreTeclado, idAlfabeto, textoFrecuencias, listaFrecuencias, algoritmo);
                cancelarOperacion = true;
                System.out.println("¡Se ha creado el teclado \"" + nombreTeclado + "\" con éxito!");
    
                String keyboard = controladorDominio.consultarDistribucionDeTeclado(nombreTeclado);
                System.out.println(keyboard);
    
            } catch (NombreAlfabetoNoValidoExcepcion e) {
                System.out.println(e.getMessage());
                System.out.println("Recuerda que el alfabeto ha de existir antes de ser asignado.\n" +
                        "¿Quieres intentar de nuevo?(s/n):");

                cancelarOperacion = reintentarOperacionTeclado("alfabeto", nombreTeclado, "creado");
                if (!cancelarOperacion) {
                    idAlfabeto = input.nextLine();
                }
    
            } catch (NombreTecladoDuplicadoExcepcion ntd) {
                System.out.println(ntd.getMessage());
                System.out.println("Recuerda que no puedes tener dos teclados con el mismo nombre.\n" +
                        "¿Quieres intentar de nuevo?(s/n):");

                cancelarOperacion = reintentarOperacionTeclado("nombre", nombreTeclado, "creado");
                if (!cancelarOperacion) {
                    nombreTeclado = input.nextLine();
                }
    
            } catch (TipoAlgoritmoIncorrectoExcepcion e) {
                System.out.println(e.getMessage());
                System.out.println("Recuerda que el algoritmo ha de ser B&B o SA.\n" +
                        "¿Quieres intentar de nuevo?(s/n):");

                cancelarOperacion = reintentarOperacionTeclado("algoritmo", nombreTeclado, "creado");
                if (!cancelarOperacion) {
                    algoritmo = input.nextLine();
                }
    
            } catch (FrecuenciaIncorrectaExcepcion fi) {
                System.out.println(fi.getMessage());
                System.out.println("El formato de la lista de frecuencias es por ejemplo : hola 20 adios 10\n"+
                                    "¿Quieres intentar de nuevo y escoger un texto válido? (s/n):");

                cancelarOperacion = reintentarOperacionTeclado("lista de palabras con frecuencias", nombreTeclado, "creado");
                if (!cancelarOperacion) {
                    listaFrecuencias = input.nextLine();
                }
    
            } catch (NombreTecladoNoValidoExcepcion ntv) {
                System.out.println(ntv.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un nombre válido? (s/n):");
                cancelarOperacion = reintentarOperacionTeclado("nombre", nombreTeclado, "creado");
                if (!cancelarOperacion) {
                    nombreTeclado = input.nextLine();
                }
            }
        }
    }
    
    public Boolean reintentarOperacionTeclado(String element, String name, String operacion) {
        String response = input.nextLine();
        if (response.equals("n")) {
            System.out.println("El teclado \"" + name + "\" no ha sido "+ operacion +".\n");
            return true;
        } else if (response.equals("s")) {
            System.out.println("Introduce nuevamente el " + element + " del teclado:");
        }
        if(!response.equals("n") && !response.equals("s")) {
            System.out.println("El valor introducido no es válido.\n" + "volviendo al menú anterior...");
            return true;
        }
        return false;
    }
    
    /**
     * Función modificadora que gira la posición de dos teclas existentes de un teclado
     */

    void FuncSwapTeclas() {
        System.out.println("Teclados actuales:\n" + controladorDominio.consultarNombresTeclados());
        
        System.out.println("Indica el nombre del teclado:");
        String name = input.nextLine();
        boolean cancelarOperacion = false;
    
        while (!cancelarOperacion) {
            try {
                String keyboard = controladorDominio.consultarDistribucionDeTeclado(name);
                System.out.println(keyboard);
    
                System.out.println("Introduce el número de la fila y columna del primer elemento, y la fila y columna " +
                                   "del segundo elemento.\n" + "NOTA: Las filas y columnas se numeran desde 0");
    
                // Input de las posiciones a intercambiar
                int f1 = input.nextInt();
                int c1 = input.nextInt();
                int f2 = input.nextInt();
                int c2 = input.nextInt();
                input.nextLine();

                controladorDominio.intercambiarTeclasTeclado(name, f1, c1, f2, c2);
                cancelarOperacion = true;
    
                System.out.println("¡Se ha intercambiado las teclas del teclado \"" + name + 
                                    "\" con éxito!\nEsta es la nueva distribución:");
                
                keyboard = controladorDominio.consultarDistribucionDeTeclado(name);
                System.out.println(keyboard);
    
            } catch (InputMismatchException ime) {
                input.nextLine();
                System.out.println("El valor introducido no es un entero.");
                System.out.println("¿Quieres intentar de nuevo y escoger una posición válida? (s/n):");
                cancelarOperacion = reintentarOperacion();
            } catch (IndiceTeclaFueraDeRangoExcepcion itf) {
                System.out.println(itf.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger una posición válida? (s/n):");
                cancelarOperacion = reintentarOperacion();
            } catch (NombreTecladoNoValidoExcepcion ntd) {
                System.out.println("El teclado introducido no existe.");
                System.out.println("¿Quieres intentar de nuevo y escoger una teclado válido? (s/n):");
                cancelarOperacion = reintentarOperacion();
                if(!cancelarOperacion) {
                    System.out.println("Introduce el nombre del teclado:");
                    name = input.nextLine();
                }
            }
        }
    }
    
    private Boolean reintentarOperacion() {
        String response = input.nextLine();
        if (response.equals("n")) {
            return true;
            
        } else if (response.equals("s")) {
            return false;
        }
        if(!response.equals("n") && !response.equals("s")) {
            System.out.println("El valor introducido no es válido.\n" + "Saliendo del menú...");
        }
        return true;
    }
    
    /**
     * Borra un teclado de los existentes en el sistema
     */

    void FuncBorrarTeclado() {
        if (controladorDominio.consultarNombresTeclados().isEmpty()) {
            System.out.println("No tienes ningún teclado creado. Para poder borrar, crea uno antes.");
            return;
        }

        System.out.println("Teclados actuales:\n" + controladorDominio.consultarNombresTeclados());
        System.out.println("Introduce el nombre del teclado a borrar:");

        boolean cancelarOperacion = false;
        while (!cancelarOperacion) {
            try {

                String name = input.nextLine();
                controladorDominio.borrarTeclado(name);
                cancelarOperacion = true;
                System.out.println("¡Se ha borrado el teclado \"" + name + "\" con éxito!");

            } catch (NombreTecladoNoValidoExcepcion ntd) {

                System.out.println(ntd.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un nombre válido? (s/n):");

                cancelarOperacion = reintentarOperacion();
                if  (!cancelarOperacion) {
                    System.out.println("Introduce nuevamente el nombre del teclado a borrar:");
                }
                else {
                    System.out.println("No se ha borrado ningún teclado.\n");
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
        System.out.println("Teclados actuales:\n" + controladorDominio.consultarNombresTeclados());
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
                cancelarOperacion = reintentarOperacion();
                if  (!cancelarOperacion) {
                    System.out.println("Introduce nuevamente el nombre del teclado a mostrar:");
                }
                else {
                    System.out.println("No se ha mostrado ningún teclado.\n");
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
        // Solicitar nombre para el alfabeto
        System.out.println("Introduce el nombre que deseas asignar al alfabeto (ej: inglés):");
        String nombreAlfabeto = input.nextLine();
    
        // Solicitar caracteres para el alfabeto
        System.out.println("Introduce los caracteres que quieres que contenga,\ntodos juntos y sin espacios (ej: abcdef):");
        String caracteresAlfabeto = input.nextLine();
    
        boolean cancelarOperacion = false;
    
        while (!cancelarOperacion) {
            try {
                // Intentar anadir el alfabeto
                controladorDominio.anadirNuevoAlfabeto(nombreAlfabeto, caracteresAlfabeto);
                cancelarOperacion = true;
                System.out.println("¡Se ha creado el alfabeto \"" + nombreAlfabeto + "\" con éxito!");
    
            } catch (NombreAlfabetoDuplicadoExcepcion nad) {
                System.out.println(nad.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un nombre no duplicado? (s/n):");
                cancelarOperacion = reintentarOperacionAlfabeto("nombre", nombreAlfabeto, "creado");
                if (!cancelarOperacion) {
                    nombreAlfabeto = input.nextLine();
                }
    
            } catch (NoHayCaracteresExcepcion nae) {
                System.out.println(nae.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger caracteres validos? (s/n):");
                cancelarOperacion = reintentarOperacionAlfabeto("caracteres", caracteresAlfabeto, "creado");
                if (!cancelarOperacion) {
                    caracteresAlfabeto = input.nextLine();
                }
    
            } catch (NombreAlfabetoNoValidoExcepcion nae) {
                System.out.println(nae.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un nombre valido? (s/n):");
                cancelarOperacion = reintentarOperacionAlfabeto( "nombre", nombreAlfabeto, "creado");
                if (!cancelarOperacion) {
                    nombreAlfabeto = input.nextLine();
                }
            }
        }
    }

    /**
     * Modifica la lista de símbolos de uno de los alfabetos creados
     */

     void FuncModificarAlfabeto() {
        // Consultar alfabetos existentes
        String nombresAlfabetos = controladorDominio.consultarNombresDeAlfabetos();
        if (nombresAlfabetos.isEmpty()) {
            System.out.println("No tienes ningún alfabeto creado. Para poder modificar, crea uno antes.");
            return;
        } else {
            System.out.println("Estos son los alfabetos que existen:");
            System.out.println(nombresAlfabetos);
        }
    
        boolean cancelarOperacion = false;
    
        // Solicitar nombre del alfabeto a modificar
        System.out.println("Introduce el nombre del alfabeto a modificar:");
        String nombreAlfabeto = input.nextLine();
    
        // Solicitar nuevos caracteres para el alfabeto
        System.out.println("Introduce los caracteres que quieres que contenga,\ntodos juntos y sin espacios (ej: abcdef):");
        String nuevosCaracteres = input.nextLine();
    
        while (!cancelarOperacion) {
            try {
                // Intentar modificar el alfabeto
                controladorDominio.modificarAlfabeto(nombreAlfabeto, nuevosCaracteres);
                cancelarOperacion = true;
                System.out.println("¡Se ha modificado el alfabeto \"" + nombreAlfabeto + "\" con éxito!\nEstos son los nuevos caracteres que contiene:");
                System.out.println(controladorDominio.consultarCaracteresAlfabeto(nombreAlfabeto));
    
            } catch (NombreAlfabetoNoValidoExcepcion nae) {
                System.out.println(nae.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un nombre válido? (s/n):");
                cancelarOperacion = reintentarOperacionAlfabeto("nombre", nombreAlfabeto, "modificado");
                if (!cancelarOperacion) {
                    nombreAlfabeto = input.nextLine();
                }
    
            } catch (NoHayCaracteresExcepcion nae) {
                System.out.println(nae.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger caracteres diferentes? (s/n):");
                cancelarOperacion = reintentarOperacionAlfabeto("caracteres", nuevosCaracteres, "modificado");
                if (!cancelarOperacion) {
                    nuevosCaracteres = input.nextLine();
                }
            }
        }
    }

    /**
     * Borra uno de los alfabetos especificados por el input
     */

     void FuncBorrarAlfabeto() {
        // Consultar alfabetos existentes
        String nombresAlfabetos = controladorDominio.consultarNombresDeAlfabetos();
        if (nombresAlfabetos.isEmpty()) {
            System.out.println("No tienes ningún alfabeto creado. Para poder borrar, crea uno antes.");
            return;
        } else {
            System.out.println("Estos son los alfabetos que existen:");
            System.out.println(nombresAlfabetos);
        }
    
        boolean cancelarOperacion = false;
    
        // Solicitar nombre del alfabeto a borrar
        System.out.println("Introduce el nombre del alfabeto a borrar:");
        String nombreAlfabeto = input.nextLine();
    
        while (!cancelarOperacion) {
            try {
                // Intentar borrar el alfabeto
                controladorDominio.eliminarAlfabeto(nombreAlfabeto);
                cancelarOperacion = true;
                System.out.println("¡Se ha borrado el alfabeto \"" + nombreAlfabeto + "\" con éxito!");
    
            } catch (NombreAlfabetoNoValidoExcepcion nae) {
                System.out.println(nae.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger un nombre válido? (s/n):");
                cancelarOperacion = reintentarOperacionAlfabeto("nombre", nombreAlfabeto, "borrado");
                if (!cancelarOperacion) {
                    nombreAlfabeto = input.nextLine();
                }
            }
        }
    }
    
    /**
     * Muestra los caracteres de los que se compone uno de los alfabetos
     */

     void FuncMostrarCaracteresAlfabeto() {
        String nombresAlfabetos = controladorDominio.consultarNombresDeAlfabetos();
        
        if (nombresAlfabetos.isEmpty()) {
            System.out.println("No tienes ningún alfabeto creado.");
            return;
        }
        
        System.out.println("Introduce el nombre del alfabeto del que quieres ver los caracteres:");
        String nombreAlfabeto = input.nextLine();
        boolean cancelarOperacion = false;
        
        while (!cancelarOperacion) {
            try {
                String caracteresAlfabeto = controladorDominio.consultarCaracteresAlfabeto(nombreAlfabeto);
                System.out.println("Los caracteres del alfabeto \"" + nombreAlfabeto + "\" son: " + caracteresAlfabeto);
                cancelarOperacion = true;
            } catch (NombreAlfabetoNoValidoExcepcion nae) {
                System.out.println(nae.getMessage());
                System.out.println("¿Quieres intentar de nuevo y escoger con un nombre válido? (s/n):");
                cancelarOperacion = reintentarOperacionAlfabeto("nombre", nombreAlfabeto, "consultado");
                if (!cancelarOperacion) {
                    nombreAlfabeto = input.nextLine();
                }
            }
        }
    }

    // Función para manejar casos de reintento
    public Boolean reintentarOperacionAlfabeto(String elementoDelAlfabeto, String nombreAlfabeto, String operacion) {
        String respuesta = input.nextLine();
        if (respuesta.equals("n")) {
            System.out.println("El alfabeto \"" + nombreAlfabeto + "\" no ha sido " + operacion + ".\n");
            return true;
    
        } else if (respuesta.equals("s")) {
            System.out.println("Introduce nuevamente el " + elementoDelAlfabeto + " del alfabeto:");
            return false;
        }
        if(!respuesta.equals("n") && !respuesta.equals("s")) {
            System.out.println("El valor introducido no es válido.\n" + "volviendo al menú anterior...");
        }
        return true;
    }
    
    
    void FuncCargarDatos() {
        boolean completaCarga = false;
        while (!completaCarga) {            
            try {
                controladorDominio.cargarTeclados();
                controladorDominio.cargarAlfabetos();
                completaCarga = true;
            } catch (LecturaIncorrectaFicheroExcepcion e) {
                System.out.println(e.getMessage());
            }
        }
    }

    void FuncEscribirUsuarios() {
        try{
            controladorDominio.guardarUsuarios();
        } catch (EscrituraIncorrectaFicheroExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    void FuncEscribirCambios() {
        try{
            controladorDominio.guardarTeclados();
            controladorDominio.guardarAlfabetos();
        } catch (EscrituraIncorrectaFicheroExcepcion e) {
            System.out.println(e.getMessage());
        }
    }
}