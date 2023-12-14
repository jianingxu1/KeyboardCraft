package persistence;

import domain.CjtAlfabetos;
import domain.CjtUsuarios;

import exceptions.*;
import java.util.*;

public class CtrlPersistencia {
    private GestorFicherosTeclados gestorFicherosTeclados;
    private GestorFicherosUsuarios gestorFicherosUsuarios;
    private GestorFicherosAlfabetos gestorFicherosAlfabetos;

    /*
     * Constructora de la clase CtrlPersistencia
     */
    public CtrlPersistencia() {
        gestorFicherosAlfabetos = new GestorFicherosAlfabetos();
        gestorFicherosTeclados = new GestorFicherosTeclados();
        gestorFicherosUsuarios = new GestorFicherosUsuarios();
    }

    /*
     * Guarda los usuarios en el fichero de usuarios
     * @throws EscrituraIncorrectaFicheroExcepcion Si el fichero de usuarios no se ha podido escribir correctamente
     * @param cjtUsuarios El conjunto de usuarios que se quiere guardar
     */
    public void guardarUsuarios(CjtUsuarios cjtUsuarios) throws EscrituraIncorrectaFicheroExcepcion {
        gestorFicherosUsuarios.guardarUsuarios(cjtUsuarios);
    }

    /*
     * Guarda los alfabetos en el fichero de alfabetos
     * @throws EscrituraIncorrectaFicheroExcepcion Si el fichero de alfabetos no se ha podido escribir correctamente
     * @param cjtAlfabetos El conjunto de alfabetos que se quiere guardar
     */
    public void guardarAlfabetos(CjtAlfabetos cjtAlfabetos, String userName)
            throws EscrituraIncorrectaFicheroExcepcion {
        gestorFicherosAlfabetos.guardarAlfabetos(cjtAlfabetos, userName);
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
    public CjtUsuarios cargarUsuarios() throws LecturaIncorrectaFicheroExcepcion {
        return gestorFicherosUsuarios.cargarUsuarios();
    }

    /*
     * Carga los alfabetos del fichero de alfabetos
     * @return El conjunto de alfabetos que se ha cargado
     * @throws LecturaIncorrectaFicheroExcepcion Si el fichero de alfabetos no se ha podido leer correctamente
     * @param userName El nombre del usuario que quiere cargar los alfabetos
     */
    public CjtAlfabetos cargarAlfabetos(String userName) throws LecturaIncorrectaFicheroExcepcion {
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
}
