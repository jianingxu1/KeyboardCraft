package domaincontrollers;

import domain.*;
import exceptions.*;
import persistence.*;

import java.util.*;

/**
 * Clase CtrlDominio
 * Representa Controlador de Dominio. Se encarga de gestionar teclados y todas
 * sus características.
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */

public class CtrlDominio {

    /** Atributos */
    private static CjtTeclados cjtTeclados;
    private CjtAlfabetos cjtAlfabetos;
    private CjtUsuarios cjtUsuarios;
    private CtrlPersistencia ctrlPersistencia;

    /**
     * El nombre del usuario actual. Si no hay ningún usuario activo, el nombre es
     * una cadena vacía.
     */
    private String username;

    // ----- Constructora -----

    public CtrlDominio() {
        cjtTeclados = CjtTeclados.getInstance();
        cjtAlfabetos = CjtAlfabetos.getInstance();
        cjtUsuarios = CjtUsuarios.getInstance();
        ctrlPersistencia = new CtrlPersistencia();
        username = "";
    }

    // ----- Métodos públicos -----

    // ----- Modificadoras -----

    /**
     * Crea un teclado con una distribución generada por un algoritmo específico.
     *
     * @param nombreTeclado  Nombre del teclado.
     * @param nombreAlfabeto El ID del alfabeto asociado con el teclado.
     * @param texto          Información adicional para la generación de la
     *                       distribución del teclado.
     * @param listaPalabras  Texto que contiene las palabras que se desean incluir
     *                       en
     *                       la distribución del teclado.
     * @param algoritmo      Un string que representa el algoritmo utilizado para la
     *                       creación del teclado (Branch and Bound o Simulated Annealing).
     * @throws NombreAlfabetoNoValidoExcepcion         Si el nombre del alfabeto no
     *                                                 es valido.
     * @throws NombreTecladoDuplicadoExcepcion         Si el nombre del teclado ya
     *                                                 existe.
     * @throws TipoAlgoritmoIncorrectoExcepcion        Si el tipo de algoritmo no es
     *                                                 correcto.
     * @throws FrecuenciaIncorrectaExcepcion           Si la frecuencia de las
     *                                                 palabras no es correcta.
     * @throws NombreTecladoNoValidoExcepcion          Si el nombre del teclado no
     *                                                 es valido.
     * @throws NumeroCaracteresExcesivoParaBranchAndBoundExcepcion Si el número de caracteres es
     *                                                 excesivo para Branch and
     *                                                 Bound.
     */
    public void crearTeclado(String nombreTeclado, String nombreAlfabeto, String texto, String listaPalabras,
            String algoritmo) throws NombreAlfabetoNoValidoExcepcion, NombreTecladoDuplicadoExcepcion,
            TipoAlgoritmoIncorrectoExcepcion, FrecuenciaIncorrectaExcepcion, NombreTecladoNoValidoExcepcion,
            NumeroCaracteresExcesivoParaBranchAndBoundExcepcion {
        // Verificar si el teclado ya existe
        if (cjtTeclados.existeTeclado(nombreTeclado)) {
            throw new NombreTecladoDuplicadoExcepcion(
                    "El teclado " + nombreTeclado + " ya existe. Introduce otro nombre.");
        }

        // Obtener el alfabeto asociado al teclado
        Alfabeto alfabeto = cjtAlfabetos.getAlfabeto(nombreAlfabeto);

        // Validar el tipo de algoritmo
        validarTipoAlgoritmo(algoritmo);

        Texto text = new Texto(texto);
        PalabrasConFrecuencia palabras = new PalabrasConFrecuencia(listaPalabras);

        Character[][] distribucion = new Character[3][10];
        // El algoritmo genera una distribucion dependiendo del algoritmo elejido
        if (algoritmo.equals("B&B")) {
            // Ya que para mas de 12 caracteres el algoritmo tarda mucho
            final int MAX_CARACTERES_BB = 12;
            if (alfabeto.getCaracteres().size() > MAX_CARACTERES_BB) {
                throw new NumeroCaracteresExcesivoParaBranchAndBoundExcepcion(
                        "El algoritmo Branch and Bound tarda muchísimo en generar distribuciones para alfabetos con más de "
                                + MAX_CARACTERES_BB
                                + " caracteres. Selecciona otro alfabeto con menos tamaño o cambia de algoritmo.");
            }
            GeneradorDistribucionTeclado gdt = new GeneradorDistribucionTeclado(new AlgoritmoBranchAndBound());
            Map<String, Integer> bigramasConFrecuencia = new CalculadoraBigramasConFrecuencia().ejecutar(alfabeto,
                    palabras,
                    text);
            distribucion = gdt.generarDistribucion(alfabeto, bigramasConFrecuencia);

        } else if (algoritmo.equals("SA")) {
            GeneradorDistribucionTeclado gdt = new GeneradorDistribucionTeclado(new AlgoritmoSimulatedAnnealing());
            Map<String, Integer> bigramasConFrecuencia = new CalculadoraBigramasConFrecuencia().ejecutar(alfabeto,
                    palabras,
                    text);
            distribucion = gdt.generarDistribucion(alfabeto, bigramasConFrecuencia);
        }
        // Se añade el teclado con la distribucion generada
        cjtTeclados.crearTeclado(nombreTeclado, distribucion);
    }

    /**
     * Valida si el tipo de algoritmo es correcto (B&B o SA).
     *
     * @param algoritmo Tipo de algoritmo a validar.
     * @throws TipoAlgoritmoIncorrectoExcepcion Si el tipo de algoritmo no es
     *                                          correcto.
     */
    private void validarTipoAlgoritmo(String algoritmo) throws TipoAlgoritmoIncorrectoExcepcion {
        if (algoritmo == null || algoritmo.trim().isEmpty()) {
            throw new TipoAlgoritmoIncorrectoExcepcion("No has seleccionado ningún algoritmo. Selecciona uno.");
        } else if (!algoritmo.equals("B&B") && !algoritmo.equals("SA")) {
            throw new TipoAlgoritmoIncorrectoExcepcion(
                    "El tipo de algoritmo \"" + algoritmo + "\" no es correcto, debe ser B&B o SA.");
        }
    }

    /**
     * Retorna los algoritmos disponibles para la generación de teclados.
     *
     * @return ArrayList con los nombres de los algoritmos disponibles.
     */
    public ArrayList<String> getNombreAlgoritmos() {
        ArrayList<String> algoritmos = new ArrayList<>();
        algoritmos.add("B&B");
        algoritmos.add("SA");
        return algoritmos;
    }

    /**
     * Elimina el teclado del conjunto de teclados y su respectiva información.
     *
     * @param nombreTeclado El nombre del teclado que se procederá a eliminar.
     * @throws NombreTecladoNoValidoExcepcion Si el nombre del teclado no es valido.
     */
    public void eliminarTeclado(String nombreTeclado) throws NombreTecladoNoValidoExcepcion {
        cjtTeclados.eliminarTeclado(nombreTeclado);
    }

    /**
     * Intercambia las posiciones de dos teclas en un teclado.
     *
     * @param nombreTeclado El nombre del teclado en el que se realizará el cambio.
     * @param caracter1     El primer carácter a intercambiar.
     * @param caracter2     El segundo carácter a intercambiar.
     * @throws NombreTecladoNoValidoExcepcion   Si el nombre del teclado no es
     *                                          válido.
     * @throws IndiceTeclaFueraDeRangoExcepcion Si el índice de la tecla está fuera
     *                                          de rango.
     * @throws CaracteresNoValidosExcepcion     Si los caracteres no son válidos
     *                                          para intercambiar.
     */
    public void intercambiarTeclasTeclado(String nombreTeclado, char caracter1, char caracter2)
            throws NombreTecladoNoValidoExcepcion, IndiceTeclaFueraDeRangoExcepcion, CaracteresNoValidosExcepcion {
        cjtTeclados.intercambiarTeclasTeclado(nombreTeclado, caracter1, caracter2);
    }

    /**
     * Añade un nuevo alfabeto al conjunto de alfabetos.
     *
     * @param nombreAlfabeto     El nombre del alfabeto que se va a añadir.
     * @param caracteresAlfabeto Una cadena que representa los caracteres del
     *                           alfabeto.
     * @throws NombreAlfabetoDuplicadoExcepcion Si el nombre del alfabeto ya existe.
     * @throws NombreAlfabetoNoValidoExcepcion  Si el nombre del alfabeto no es
     *                                          válido.
     * @throws NoHayCaracteresExcepcion         Si no hay caracteres en el alfabeto.
     */
    public void crearAlfabeto(String nombreAlfabeto, String caracteresAlfabeto)
            throws NombreAlfabetoDuplicadoExcepcion, NombreAlfabetoNoValidoExcepcion, NoHayCaracteresExcepcion {
        cjtAlfabetos.crearAlfabeto(nombreAlfabeto, caracteresAlfabeto);
    }

    /**
     * Eliminar alfabeto
     * 
     * @param nombreAlfabeto nombre del alfabeto que se va a eliminar.
     * @throws NombreAlfabetoNoValidoExcepcion Si el nombre del alfabeto no es
     *                                         valido.
     */
    public void eliminarAlfabeto(String nombreAlfabeto) throws NombreAlfabetoNoValidoExcepcion {
        cjtAlfabetos.eliminarAlfabeto(nombreAlfabeto);
    }

    /**
     * Modificar alfabeto del conjunto de alfabetos.
     *
     * @param nombreAlfabeto     String que representa los caracteres modificados.
     * @param caracteresAlfabeto String con el nuevo conjunto de caracteres
     * @throws NombreAlfabetoNoValidoExcepcion Si el nombre del alfabeto no es
     *                                         valido.
     * @throws NoHayCaracteresExcepcion        Si no hay caracteres en el alfabeto.
     */
    public void modificarCaracteresAlfabeto(String nombreAlfabeto, String caracteresAlfabeto)
            throws NombreAlfabetoNoValidoExcepcion, NoHayCaracteresExcepcion {
        cjtAlfabetos.modificarCaracteresAlfabeto(nombreAlfabeto, caracteresAlfabeto);
    }

    /**
     * Añade un nuevo usuario al conjunto de usuarios.
     *
     * @param nombreUsuario El nombre del usuario que se quiere añadir.
     * @param contrasena    La contraseña del usuario que se quiere añadir.
     * @throws NombreUsuarioNoValidoExcepcion      Si el nombre del usuario no es
     *                                             válido.
     * @throws ContrasenaNoValidaExcepcion         Si la contraseña no es válida.
     * @throws EscrituraIncorrectaFicheroExcepcion Si ocurre un error al intentar
     *                                             escribir en el fichero.
     */
    public void anadirNuevoUsuario(String nombreUsuario, String contrasena)
            throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion, EscrituraIncorrectaFicheroExcepcion {
        cjtUsuarios.anadirNuevoUsuario(nombreUsuario, contrasena);
        username = nombreUsuario;
    }

    /**
     * Comprueba si existe un usuario en el conjunto de usuarios
     * 
     * @param nombreUsuario El nombre del usuario que se quiere comprobar
     * @return True si el usuario existe, false en caso contrario
     */
    public boolean existeUsuario(String nombreUsuario) {
        if (cjtUsuarios.existeUsuario(nombreUsuario))
            return true;
        return false;
    }

    /**
     * Elimina un usuario del conjunto
     * 
     * @param nombreUsuario El nombre del usuario a eliminar
     * @throws NombreUsuarioNoValidoExcepcion Si el nombre del usuario no es valido
     */
    public void eliminarUsuario(String nombreUsuario) throws NombreUsuarioNoValidoExcepcion {
        cjtUsuarios.eliminarUsuario(nombreUsuario);
        ctrlPersistencia.eliminarUsuario(nombreUsuario);
    }

    /**
     * Elimina el usuario actual del conjunto de usuarios y limpia los conjuntos de
     * teclados y alfabetos asociados.
     *
     * @throws NombreUsuarioNoValidoExcepcion Si el nombre de usuario actual no es
     *                                        válido.
     */
    public void eliminarUsuarioActual() throws NombreUsuarioNoValidoExcepcion {
        if (username.trim().isEmpty())
            return;
        eliminarUsuario(username);
        cjtTeclados.clearCjtTeclados();
        cjtAlfabetos.clearCjtAlfabetos();
        username = "";
    }

    /**
     * Modifica la contraseña de un usuario
     * 
     * @param nombreUsuario    el nombre del usuario a modificar
     * @param actualContrasena la contraseña actual del usuario
     * @param nuevaContrasena  la nueva contraseña del usuario
     * @throws NombreUsuarioNoValidoExcepcion si el nombre del usuario no es valido
     * @throws ContrasenaNoValidaExcepcion    si la contraseña no es valida
     */
    public void modificarContrasena(String nombreUsuario, String actualContrasena, String nuevaContrasena)
            throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion {
        cjtUsuarios.modificarContrasenaUsuario(nombreUsuario, actualContrasena, nuevaContrasena);
    }

    /**
     * Modifica la contraseña del usuario actual.
     *
     * @param actualContrasena La contraseña actual del usuario.
     * @param nuevaContrasena  La nueva contraseña del usuario.
     * @throws NombreUsuarioNoValidoExcepcion Si el nombre del usuario actual no es
     *                                        válido.
     * @throws ContrasenaNoValidaExcepcion    Si la contraseña no es válida.
     */
    public void modificarContrasenaUsuarioActual(String actualContrasena, String nuevaContrasena)
            throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion {
        cjtUsuarios.modificarContrasenaUsuario(username, actualContrasena, nuevaContrasena);
    }

    /**
     * Inicia sesión con un usuario proporcionado y una contraseña.
     *
     * @param nombreUsuario El nombre del usuario para iniciar sesión.
     * @param contrasena    La contraseña asociada al usuario.
     * @return True si el usuario es identificado correctamente, false en caso
     *         contrario.
     * @throws NombreUsuarioNoValidoExcepcion Si el nombre de usuario no es válido.
     * @throws ContrasenaNoValidaExcepcion    Si la contraseña no es válida.
     */
    public boolean IniciarSesion(String nombreUsuario, String contrasena)
            throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion {

        boolean usuarioIdentificado = cjtUsuarios.correctPass(nombreUsuario, contrasena);
        if (!usuarioIdentificado)
            throw new ContrasenaNoValidaExcepcion("La contraseña no es correcta.");
        else
            username = nombreUsuario;
        return usuarioIdentificado;
    }

    /**
     * Verifica si la contraseña proporcionada coincide con la del usuario
     * especificado.
     *
     * @param nombreUsuario El nombre del usuario cuya contraseña se verificará.
     * @param contrasena    La contraseña que se comprobará.
     * @return True si la contraseña coincide con la del usuario, false en caso
     *         contrario.
     * @throws NombreUsuarioNoValidoExcepcion Si el nombre de usuario no es válido.
     * @throws ContrasenaNoValidaExcepcion    Si la contraseña no es válida.
     */
    public boolean contrasenaCorrecta(String nombreUsuario, String contrasena)
            throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion {
        return cjtUsuarios.correctPass(nombreUsuario, contrasena);
    }

    /**
     * Cierra la sesión del usuario actual, guardando los alfabetos y teclados, y
     * limpiando los conjuntos de teclados y alfabetos asociados.
     *
     * @throws EscrituraIncorrectaFicheroExcepcion Si ocurre un error al escribir en
     *                                             el archivo.
     * @throws NombreAlfabetoNoValidoExcepcion     Si el nombre del alfabeto no es
     *                                             válido.
     * @throws NombreTecladoNoValidoExcepcion      Si el nombre del teclado no es
     *                                             válido.
     */
    public void cerrarSesion()
            throws EscrituraIncorrectaFicheroExcepcion, NombreAlfabetoNoValidoExcepcion,
            NombreTecladoNoValidoExcepcion {
        guardarAlfabetos();
        guardarTeclados();
        cjtTeclados.clearCjtTeclados();
        cjtAlfabetos.clearCjtAlfabetos();
        username = "";
    }

    /**
     * Guarda los teclados del usuario en un archivo, si hay un usuario activo.
     *
     * @throws EscrituraIncorrectaFicheroExcepcion Si ocurre un error al escribir en
     *                                             el archivo.
     * @throws NombreTecladoNoValidoExcepcion      Si el nombre del teclado no es
     *                                             válido.
     */
    public void guardarTeclados() throws EscrituraIncorrectaFicheroExcepcion, NombreTecladoNoValidoExcepcion {
        if (username.trim().isEmpty())
            return;
        HashMap<String, Character[][]> conjunto = new HashMap<>();

        ArrayList<String> nombreTeclados = cjtTeclados.getNombreTeclados();

        for (String nombreTeclado : nombreTeclados) {
            Character[][] distribucionTeclado = getDistribucionTeclado(nombreTeclado);
            conjunto.put(nombreTeclado, distribucionTeclado);
        }
        ctrlPersistencia.guardarTeclados(username, conjunto);
    }

    /**
     * Carga los teclados del usuario desde un archivo, si hay un usuario activo.
     *
     * @throws LecturaIncorrectaFicheroExcepcion Si ocurre un error al leer el
     *                                           archivo.
     * @throws NombreTecladoDuplicadoExcepcion   Si se encuentra un nombre de
     *                                           teclado duplicado durante la carga.
     * @throws NombreTecladoNoValidoExcepcion    Si el nombre del teclado no es
     *                                           válido.
     */
    public void cargarTeclados()
            throws LecturaIncorrectaFicheroExcepcion, NombreTecladoDuplicadoExcepcion, NombreTecladoNoValidoExcepcion {
        HashMap<String, Character[][]> conjunto = ctrlPersistencia.cargarTeclados(username);
        for (Map.Entry<String, Character[][]> entry : conjunto.entrySet()) {
            String nombreTeclado = entry.getKey();
            Character[][] distribucion = entry.getValue();
            cjtTeclados.crearTeclado(nombreTeclado, distribucion);
        }
    }

    /**
     * Guarda los usuarios en un archivo.
     *
     * @throws EscrituraIncorrectaFicheroExcepcion Si ocurre un error al escribir en
     *                                             el archivo.
     */
    public void guardarUsuarios() throws EscrituraIncorrectaFicheroExcepcion {
        HashMap<String, String> conjuntoUsuarios = new HashMap<>();

        ArrayList<String> nombreUsuarios = cjtUsuarios.getNombreUsuarios();
        for (String nombreUsuario : nombreUsuarios) {
            String contrasena = cjtUsuarios.getContrasenaUsuario(nombreUsuario);
            conjuntoUsuarios.put(nombreUsuario, contrasena);
        }

        ctrlPersistencia.guardarUsuarios(conjuntoUsuarios);
    }

    /**
     * Carga los usuarios desde un archivo.
     *
     * @throws LecturaIncorrectaFicheroExcepcion Si ocurre un error al leer el
     *                                           archivo.
     * @throws NombreUsuarioNoValidoExcepcion    Si se encuentra un nombre de
     *                                           usuario no válido en los datos
     *                                           cargados.
     * @throws ContrasenaNoValidaExcepcion       Si se encuentra una contraseña no
     *                                           válida en los datos cargados.
     */
    public void cargarUsuarios()
            throws LecturaIncorrectaFicheroExcepcion, NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion {
        HashMap<String, String> conjuntoUsuarios = ctrlPersistencia.cargarUsuarios();
        for (Map.Entry<String, String> entry : conjuntoUsuarios.entrySet()) {
            String nombreUsuario = entry.getKey();
            String contrasena = entry.getValue();
            cjtUsuarios.anadirNuevoUsuario(nombreUsuario, contrasena);
        }
    }

    /**
     * Guarda los alfabetos en un fichero
     * 
     * @throws EscrituraIncorrectaFicheroExcepcion Si ocurre un error al escribir en
     *                                            el archivo.
     * @throws NombreAlfabetoNoValidoExcepcion     Si se encuentra un nombre de
     *                                           alfabeto no válido al guardar.
     */
    public void guardarAlfabetos() throws EscrituraIncorrectaFicheroExcepcion, NombreAlfabetoNoValidoExcepcion {
        if (username.trim().isEmpty())
            return;
        ArrayList<String> nombreAlfabetos = cjtAlfabetos.getNombreAlfabetos();
        HashMap<String, ArrayList<Character>> conjuntoAlfabetos = new HashMap<>();
        for (String nombreAlfabeto : nombreAlfabetos) {
            ArrayList<Character> listaCaracteres = cjtAlfabetos.getCaracteresDeAlfabeto(nombreAlfabeto);
            conjuntoAlfabetos.put(nombreAlfabeto, listaCaracteres);
        }
        ctrlPersistencia.guardarAlfabetos(username, conjuntoAlfabetos);
    }

    /**
     * Carga los alfabetos de un archivo.
     *
     * @throws LecturaIncorrectaFicheroExcepcion  Si ocurre un error al leer el
     *                                           archivo.
     * @throws NombreAlfabetoNoValidoExcepcion     Si se encuentra un nombre de
     *                                             alfabeto no válido al guardar.
     * @throws NombreAlfabetoDuplicadoExcepcion    Si se encuentra un nombre de
     *                                            alfabeto duplicado al guardar.
     * @throws NoHayCaracteresExcepcion            Si no hay caracteres en el
     *                                            alfabeto.
     */
    public void cargarAlfabetos() throws LecturaIncorrectaFicheroExcepcion, NombreAlfabetoNoValidoExcepcion,
            NombreAlfabetoDuplicadoExcepcion, NoHayCaracteresExcepcion {
        HashMap<String, String> conjuntoAlfabetos = ctrlPersistencia.cargarAlfabetos(username);
        for (Map.Entry<String, String> entry : conjuntoAlfabetos.entrySet()) {
            String nombreAlfabeto = entry.getKey();
            String caracteres = entry.getValue();
            cjtAlfabetos.crearAlfabeto(nombreAlfabeto, caracteres);
        }
    }

    // Funciones de importacion y visualizacion de textos y listas de palabras

    /**
     * Obtiene una lista de nombres de archivos disponibles en la carpeta de textos
     * en la ruta especificada.
     *
     * @param pathFolderTextos La ruta de la carpeta que contiene los textos.
     * @return Una lista de nombres de archivos de texto disponibles en la carpeta
     *         especificada.
     */
    public ArrayList<String> visualizarTextos(String pathFolderTextos) {
        return ctrlPersistencia.visualizar(pathFolderTextos);
    }

    /**
     * Obtiene una lista de nombres de archivos disponibles en la carpeta de listas
     * de palabras en la ruta especificada.
     *
     * @param pathFolderListaDePalabras La ruta de la carpeta que contiene las
     *                                  listas de palabras.
     * @return Una lista de nombres de archivos de listas de palabras disponibles en
     *         la carpeta especificada.
     */
    public ArrayList<String> visualizarListasPalabras(String pathFolderListaDePalabras) {
        return ctrlPersistencia.visualizar(pathFolderListaDePalabras);
    }

    /**
     * Importa y devuelve el texto contenido en el archivo ubicado en la ruta
     * especificada.
     *
     * @param pathFileTexto La ruta del archivo de texto que se va a importar.
     * @return El texto importado del archivo.
     * @throws LecturaIncorrectaFicheroExcepcion Si hay errores en la lectura del
     *                                           archivo.
     */
    public String importarTexto(String pathFileTexto) throws LecturaIncorrectaFicheroExcepcion {
        String texto = ctrlPersistencia.cargar(pathFileTexto);
        Texto text = new Texto(texto);
        return text.getTexto();
    }

    /**
     * Importa y devuelve la lista de palabras con sus frecuencias desde el archivo
     * especificado.
     *
     * @param pathFileListaDePalabras La ruta del archivo que contiene la lista de
     *                                palabras.
     * @return La representación en cadena de la lista de palabras y sus
     *         frecuencias.
     * @throws LecturaIncorrectaFicheroExcepcion Si hay errores en la lectura del
     *                                           archivo.
     * @throws FrecuenciaIncorrectaExcepcion     Si la frecuencia de las palabras en
     *                                           el archivo no es válida.
     */
    public String importarListaPalabras(String pathFileListaDePalabras)
            throws LecturaIncorrectaFicheroExcepcion, FrecuenciaIncorrectaExcepcion {
        String lista = ctrlPersistencia.cargar(pathFileListaDePalabras);
        PalabrasConFrecuencia palabras = new PalabrasConFrecuencia(lista);
        return palabras.toString();
    }

    // ----- Getters -----
    /**
     * Obtiene los nombres de los alfabetos disponibles.
     *
     * @return Una lista de String que representa los nombres de los alfabetos
     *         existentes.
     */
    public ArrayList<String> getNombreAlfabetos() {
        return cjtAlfabetos.getNombreAlfabetos();
    }

    /**
     * Obtiene los caracteres asociados a un alfabeto específico.
     *
     * @param nombreAlfabeto El nombre del alfabeto del que se desean obtener los
     *                       caracteres.
     * @return Una lista de caracteres que representan el alfabeto especificado.
     * @throws NombreAlfabetoNoValidoExcepcion Si el nombre del alfabeto no es
     *                                         válido.
     */
    public ArrayList<Character> getCaracteresDeAlfabeto(String nombreAlfabeto) throws NombreAlfabetoNoValidoExcepcion {
        return cjtAlfabetos.getCaracteresDeAlfabeto(nombreAlfabeto);
    }

    /**
     * Devuelve el nombre de todos los teclados del conjunto de teclados.
     * 
     * @return ArrayList con los nombres de todos los teclados.
     */
    public ArrayList<String> getNombreTeclados() {
        return cjtTeclados.getNombreTeclados();
    }

    /**
     * Obtiene la distribución de teclas de un teclado específico.
     *
     * @param nombreTeclado El nombre del teclado del que se desea obtener la
     *                      distribución.
     * @return Una matriz de caracteres que representa la distribución del teclado
     *         especificado.
     * @throws NombreTecladoNoValidoExcepcion Si el nombre del teclado no es válido.
     */
    public Character[][] getDistribucionTeclado(String nombreTeclado) throws NombreTecladoNoValidoExcepcion {
        return cjtTeclados.getDistribucionTeclado(nombreTeclado);
    }

    /**
     * Devuelve el nombre del usuario actual
     * 
     * @return el nombre del usuario actual
     */
    public String getNombreUsuario() {
        return username;
    }
}