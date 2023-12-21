package persistence;

import exceptions.*;
import java.util.*;

public class CtrlPersistencia {
    private GestorFicherosTeclados gestorFicherosTeclados;
    private GestorFicherosUsuarios gestorFicherosUsuarios;
    private GestorFicherosAlfabetos gestorFicherosAlfabetos;
    private CargarYVisualizar importador;
    /*
     * Constructora de la clase CtrlPersistencia
     */
    public CtrlPersistencia() {
        gestorFicherosAlfabetos = new GestorFicherosAlfabetos();
        gestorFicherosTeclados = new GestorFicherosTeclados();
        gestorFicherosUsuarios = new GestorFicherosUsuarios();
        importador = new CargarYVisualizar();
    }

    /*
     * Guarda los usuarios en el fichero de usuarios
     * @throws EscrituraIncorrectaFicheroExcepcion Si el fichero de usuarios no se ha podido escribir correctamente
     * @param cjtUsuarios El conjunto de usuarios que se quiere guardar
     */
    public void guardarUsuarios(HashMap<String, String> cjtUsuarios) throws EscrituraIncorrectaFicheroExcepcion {
        gestorFicherosUsuarios.guardar("",cjtUsuarios);
    }

    /*
     * Guarda los alfabetos en el fichero de alfabetos
     * @throws EscrituraIncorrectaFicheroExcepcion Si el fichero de alfabetos no se ha podido escribir correctamente
     * @param cjtAlfabetos El conjunto de alfabetos que se quiere guardar
     */
    public void guardarAlfabetos(String userName, HashMap<String, ArrayList<Character>> conjuntoAlfabetos)
            throws EscrituraIncorrectaFicheroExcepcion {
        gestorFicherosAlfabetos.guardar(userName, conjuntoAlfabetos);
    }

    /*
     * Guarda los teclados en el fichero de teclados
     * @throws EscrituraIncorrectaFicheroExcepcion Si el fichero de teclados no se ha podido escribir correctamente
     * @param cjtTeclados El conjunto de teclados que se quiere guardar
     * @param userName El nombre del usuario que quiere guardar los teclados
     */
    public void guardarTeclados(String username, HashMap<String, Character[][]> cjtTeclados) throws EscrituraIncorrectaFicheroExcepcion {
        gestorFicherosTeclados.guardar(username, cjtTeclados);
    }

    /*
     * Carga los usuarios del fichero de usuarios
     * @return El conjunto de usuarios que se ha cargado
     * @throws LecturaIncorrectaFicheroExcepcion Si el fichero de usuarios no se ha podido leer correctamente
     */
    public HashMap<String, String> cargarUsuarios() throws LecturaIncorrectaFicheroExcepcion {
        return gestorFicherosUsuarios.cargar("");
    }

    /*
     * Carga los alfabetos del fichero de alfabetos
     * @return El conjunto de alfabetos que se ha cargado
     * @throws LecturaIncorrectaFicheroExcepcion Si el fichero de alfabetos no se ha podido leer correctamente
     * @param userName El nombre del usuario que quiere cargar los alfabetos
     */
    public HashMap<String, String> cargarAlfabetos(String userName) throws LecturaIncorrectaFicheroExcepcion {
        return gestorFicherosAlfabetos.cargar(userName);
    }

    /*
     * Carga los teclados del fichero de teclados
     * @return El conjunto de teclados que se ha cargado
     * @throws LecturaIncorrectaFicheroExcepcion Si el fichero de teclados no se ha podido leer correctamente
     * @param userName El nombre del usuario que quiere cargar los teclados
     */
    public HashMap<String, Character[][]> cargarTeclados(String username) throws LecturaIncorrectaFicheroExcepcion {
        return gestorFicherosTeclados.cargar(username);
    }

    /*
     * Elimina el usuario del fichero de usuarios
     * @param nombreUsuario El nombre del usuario que se quiere eliminar
     */
    public void eliminarUsuario(String nombreUsuario) {
        gestorFicherosTeclados.eliminarTecladosDeUsuario(nombreUsuario);
        gestorFicherosAlfabetos.eliminarAlfabetosDeUsuario(nombreUsuario);
    }

    /**
     * Carga el contenido del fichero que se le pasa por parámetro.
     * @param pathArchivo
     * @return
     * @throws LecturaIncorrectaFicheroExcepcion
     */
    public String cargar(String pathArchivo) throws LecturaIncorrectaFicheroExcepcion {
        return importador.cargar(pathArchivo);
    }

    /**
     * Visualiza los ficheros que hay en la carpeta pathFolder
     * @param pathFolder
     * @return
     */
    public ArrayList<String> visualizar(String pathFolder) {
        return importador.visualizar(pathFolder);
    }

}
