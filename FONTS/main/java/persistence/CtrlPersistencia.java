package persistence;

import exceptions.*;
import java.util.*;

/**
 * Clase CtrlPersistencia
 * 
 * Esta clase gestiona la persistencia de usuarios, alfabetos y teclados,
 * interactuando con los respectivos gestores de ficheros y la clase
 * CargarYVisualizar.
 * 
 * @author Momin Miah Begum
 * @author Muhammad Yasin Khokhar
 */
public class CtrlPersistencia {
    private GestorFicherosTeclados gestorFicherosTeclados;
    private GestorFicherosUsuarios gestorFicherosUsuarios;
    private GestorFicherosAlfabetos gestorFicherosAlfabetos;
    private CargarYVisualizar importador;

    /**
     * Constructor de la clase CtrlPersistencia.
     */
    public CtrlPersistencia() {
        gestorFicherosAlfabetos = new GestorFicherosAlfabetos();
        gestorFicherosTeclados = new GestorFicherosTeclados();
        gestorFicherosUsuarios = new GestorFicherosUsuarios();
        importador = new CargarYVisualizar();
    }

    /**
     * Guarda los usuarios en el fichero de usuarios.
     * 
     * @param cjtUsuarios El conjunto de usuarios que se quiere guardar.
     * @throws EscrituraIncorrectaFicheroExcepcion Si el fichero de usuarios no se
     *                                             ha podido escribir correctamente.
     */
    public void guardarUsuarios(HashMap<String, String> cjtUsuarios) throws EscrituraIncorrectaFicheroExcepcion {
        gestorFicherosUsuarios.guardar("", cjtUsuarios);
    }

    /**
     * Guarda los alfabetos en el fichero de alfabetos.
     * 
     * @param userName          El nombre del usuario que quiere guardar los
     *                          alfabetos.
     * @param conjuntoAlfabetos El conjunto de alfabetos que se quiere guardar.
     * @throws EscrituraIncorrectaFicheroExcepcion Si el fichero de alfabetos no se
     *                                             ha podido escribir correctamente.
     */
    public void guardarAlfabetos(String userName, HashMap<String, ArrayList<Character>> conjuntoAlfabetos)
            throws EscrituraIncorrectaFicheroExcepcion {
        gestorFicherosAlfabetos.guardar(userName, conjuntoAlfabetos);
    }

    /**
     * Guarda los teclados en el fichero de teclados.
     * 
     * @param username    El nombre del usuario que quiere guardar los teclados.
     * @param cjtTeclados El conjunto de teclados que se quiere guardar.
     * @throws EscrituraIncorrectaFicheroExcepcion Si el fichero de teclados no se
     *                                             ha podido escribir correctamente.
     */
    public void guardarTeclados(String username, HashMap<String, Character[][]> cjtTeclados)
            throws EscrituraIncorrectaFicheroExcepcion {
        gestorFicherosTeclados.guardar(username, cjtTeclados);
    }

    /**
     * Carga los usuarios del fichero de usuarios.
     * 
     * @return El conjunto de usuarios que se ha cargado.
     * @throws LecturaIncorrectaFicheroExcepcion Si el fichero de usuarios no se ha
     *                                           podido leer correctamente.
     */
    public HashMap<String, String> cargarUsuarios() throws LecturaIncorrectaFicheroExcepcion {
        return gestorFicherosUsuarios.cargar("");
    }

    /**
     * Carga los alfabetos del fichero de alfabetos.
     * 
     * @param userName El nombre del usuario que quiere cargar los alfabetos.
     * @return El conjunto de alfabetos que se ha cargado.
     * @throws LecturaIncorrectaFicheroExcepcion Si el fichero de alfabetos no se ha
     *                                           podido leer correctamente.
     */
    public HashMap<String, String> cargarAlfabetos(String userName) throws LecturaIncorrectaFicheroExcepcion {
        return gestorFicherosAlfabetos.cargar(userName);
    }

    /**
     * Carga los teclados del fichero de teclados.
     * 
     * @param username El nombre del usuario que quiere cargar los teclados.
     * @return El conjunto de teclados que se ha cargado.
     * @throws LecturaIncorrectaFicheroExcepcion Si el fichero de teclados no se ha
     *                                           podido leer correctamente.
     */
    public HashMap<String, Character[][]> cargarTeclados(String username) throws LecturaIncorrectaFicheroExcepcion {
        return gestorFicherosTeclados.cargar(username);
    }

    /**
     * Elimina el usuario del fichero de usuarios.
     * 
     * @param nombreUsuario El nombre del usuario que se quiere eliminar.
     */
    public void eliminarUsuario(String nombreUsuario) {
        gestorFicherosTeclados.eliminarTecladosDeUsuario(nombreUsuario);
        gestorFicherosAlfabetos.eliminarAlfabetosDeUsuario(nombreUsuario);
    }

    /**
     * Carga el contenido del fichero que se le pasa por parámetro.
     * 
     * @param pathArchivo Ruta del archivo a cargar.
     * @return Contenido del archivo en formato String.
     * @throws LecturaIncorrectaFicheroExcepcion Si ocurre un error durante la
     *                                           lectura del archivo.
     */
    public String cargar(String pathArchivo) throws LecturaIncorrectaFicheroExcepcion {
        return importador.cargar(pathArchivo);
    }

    /**
     * Visualiza los ficheros que hay en la carpeta pathFolder.
     * 
     * @param pathFolder Ruta de la carpeta a visualizar.
     * @return Lista de nombres de archivos en la carpeta.
     */
    public ArrayList<String> visualizar(String pathFolder) {
        return importador.visualizar(pathFolder);
    }
}
