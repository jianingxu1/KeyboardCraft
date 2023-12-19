package persistence;

import exceptions.*;
import java.util.*;

public class CtrlPersistencia {
    private GestorFicherosTeclados gestorFicherosTeclados;
    private GestorFicherosUsuarios gestorFicherosUsuarios;
    private GestorFicherosAlfabetos gestorFicherosAlfabetos;
    private CargarYVisualizarTextos cargadorTextos;
    private CargarYVisualizarListaDePalabras cargadorListas;

    /*
     * Constructora de la clase CtrlPersistencia
     */
    public CtrlPersistencia() {
        gestorFicherosAlfabetos = new GestorFicherosAlfabetos();
        gestorFicherosTeclados = new GestorFicherosTeclados();
        gestorFicherosUsuarios = new GestorFicherosUsuarios();
        cargadorTextos = new CargarYVisualizarTextos();
        cargadorListas = new CargarYVisualizarListaDePalabras();
    }

    /*
     * Guarda los usuarios en el fichero de usuarios
     * @throws EscrituraIncorrectaFicheroExcepcion Si el fichero de usuarios no se ha podido escribir correctamente
     * @param cjtUsuarios El conjunto de usuarios que se quiere guardar
     */
    public void guardarUsuarios(HashMap<String, String> cjtUsuarios) throws EscrituraIncorrectaFicheroExcepcion {
        gestorFicherosUsuarios.guardarUsuarios(cjtUsuarios);
    }

    /*
     * Guarda los alfabetos en el fichero de alfabetos
     * @throws EscrituraIncorrectaFicheroExcepcion Si el fichero de alfabetos no se ha podido escribir correctamente
     * @param cjtAlfabetos El conjunto de alfabetos que se quiere guardar
     */
    public void guardarAlfabetos(String userName, HashMap<String, ArrayList<Character>> conjuntoAlfabetos)
            throws EscrituraIncorrectaFicheroExcepcion {
        gestorFicherosAlfabetos.guardarAlfabetos(userName, conjuntoAlfabetos);
    }

    /*
     * Guarda los teclados en el fichero de teclados
     * @throws EscrituraIncorrectaFicheroExcepcion Si el fichero de teclados no se ha podido escribir correctamente
     * @param cjtTeclados El conjunto de teclados que se quiere guardar
     * @param userName El nombre del usuario que quiere guardar los teclados
     */
    public void guardarTeclados(String username, HashMap<String, Character[][]> cjtTeclados) throws EscrituraIncorrectaFicheroExcepcion {
        gestorFicherosTeclados.guardarTeclados(username, cjtTeclados);
    }

    /*
     * Carga los usuarios del fichero de usuarios
     * @return El conjunto de usuarios que se ha cargado
     * @throws LecturaIncorrectaFicheroExcepcion Si el fichero de usuarios no se ha podido leer correctamente
     */
    public HashMap<String, String> cargarUsuarios() throws LecturaIncorrectaFicheroExcepcion {
        return gestorFicherosUsuarios.cargarUsuarios();
    }

    /*
     * Carga los alfabetos del fichero de alfabetos
     * @return El conjunto de alfabetos que se ha cargado
     * @throws LecturaIncorrectaFicheroExcepcion Si el fichero de alfabetos no se ha podido leer correctamente
     * @param userName El nombre del usuario que quiere cargar los alfabetos
     */
    public HashMap<String, String> cargarAlfabetos(String userName) throws LecturaIncorrectaFicheroExcepcion {
        return gestorFicherosAlfabetos.cargarAlfabetos(userName);
    }

    /*
     * Carga los teclados del fichero de teclados
     * @return El conjunto de teclados que se ha cargado
     * @throws LecturaIncorrectaFicheroExcepcion Si el fichero de teclados no se ha podido leer correctamente
     * @param userName El nombre del usuario que quiere cargar los teclados
     */
    public HashMap<String, Character[][]> cargarTeclados(String username) throws LecturaIncorrectaFicheroExcepcion {
        return gestorFicherosTeclados.cargarTeclados(username);
    }

    /*
     * Elimina el usuario del fichero de usuarios
     * @param nombreUsuario El nombre del usuario que se quiere eliminar
     */
    public void eliminarUsuario(String nombreUsuario) {
        gestorFicherosTeclados.eliminarTecladosDeUsuario(nombreUsuario);

        gestorFicherosAlfabetos.eliminarAlfabetosDeUsuario(nombreUsuario);

    }

    /*
     * visualiza los textos existentes en el sistema para poder importarlos
     */
    public ArrayList<String> visualizarTextos() {
        return cargadorTextos.visualizar();
    }

    /*
     * Carga el texto que se le pasa por parámetro
     * @param nombreArchivo El nombre del archivo que se quiere cargar
     * @return El contenido del archivo como una cadena
     * @throws LecturaIncorrectaFicheroExcepcion Si el fichero de textos no se ha podido leer correctamente
     */
    public String cargarTexto(String nombreArchivo) throws LecturaIncorrectaFicheroExcepcion{
        return cargadorTextos.cargar(nombreArchivo);
    }

    /*
     * visualiza las listas de palabras existentes en el sistema para poder importarlas
     */
    public ArrayList<String> visualizarListasDePalabras() {
        return cargadorListas.visualizar();
    }

    /*
     * Carga la lista que se le pasa por parámetro
     * @param nombreArchivo El nombre del archivo que se quiere cargar
     * @return El contenido del archivo como una cadena
     * @throws LecturaIncorrectaFicheroExcepcion Si el fichero de listas no se ha podido leer correctamente
     */
    public String cargarListaDePalabras(String nombreArchivo) throws LecturaIncorrectaFicheroExcepcion {
        return cargadorListas.cargar(nombreArchivo);
    }
}
