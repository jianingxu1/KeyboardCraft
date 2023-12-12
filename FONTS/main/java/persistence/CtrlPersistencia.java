package persistence;

import domain.CjtAlfabetos;
import domain.CjtTeclados;
import domain.CjtUsuarios;
import exceptions.*;

public class CtrlPersistencia {

    
    private GestorFicherosTeclados gestorFicherosTeclados;
    private GestorFicherosUsuarios gestorFicherosUsuarios;
    private GestorFicherosAlfabetos gestorFicherosAlfabetos;


    /*
     * Constructora de la clase CtrlPersistencia
     */
    public CtrlPersistencia() {
    }

    /*
     * Guarda los usuarios en el fichero de usuarios
     * @throws EscrituraIncorrectaFicheroExcepcion Si el fichero de usuarios no se ha podido escribir correctamente
     * @param cjtUsuarios El conjunto de usuarios que se quiere guardar
     */
    public void guardarUsuarios(CjtUsuarios cjtUsuarios) throws EscrituraIncorrectaFicheroExcepcion {
        gestorFicherosUsuarios = new GestorFicherosUsuarios();
        gestorFicherosUsuarios.guardarUsuarios(cjtUsuarios);
    }

    /*
     * Guarda los alfabetos en el fichero de alfabetos
     * @throws EscrituraIncorrectaFicheroExcepcion Si el fichero de alfabetos no se ha podido escribir correctamente
     * @param cjtAlfabetos El conjunto de alfabetos que se quiere guardar
     */
    public void guardarAlfabetos(CjtAlfabetos cjtAlfabetos, String userName)
            throws EscrituraIncorrectaFicheroExcepcion {
        gestorFicherosAlfabetos = new GestorFicherosAlfabetos();
        gestorFicherosAlfabetos.guardarAlfabetos(cjtAlfabetos, userName);
    }

    /*
     * Guarda los teclados en el fichero de teclados
     * @throws EscrituraIncorrectaFicheroExcepcion Si el fichero de teclados no se ha podido escribir correctamente
     * @param cjtTeclados El conjunto de teclados que se quiere guardar
     * @param userName El nombre del usuario que quiere guardar los teclados
     */
    public void guardarTeclados(CjtTeclados cjtTeclados, String userName) throws EscrituraIncorrectaFicheroExcepcion {
        gestorFicherosTeclados = new GestorFicherosTeclados();
        gestorFicherosTeclados.guardarTeclados(cjtTeclados, userName);
    }

    /*
     * Carga los usuarios del fichero de usuarios
     * @return El conjunto de usuarios que se ha cargado
     * @throws LecturaIncorrectaFicheroExcepcion Si el fichero de usuarios no se ha podido leer correctamente
     */
    public CjtUsuarios cargarUsuarios() throws LecturaIncorrectaFicheroExcepcion {
        gestorFicherosUsuarios = new GestorFicherosUsuarios();
        return gestorFicherosUsuarios.cargarUsuarios();
    }

    /*
     * Carga los alfabetos del fichero de alfabetos
     * @return El conjunto de alfabetos que se ha cargado
     * @throws LecturaIncorrectaFicheroExcepcion Si el fichero de alfabetos no se ha podido leer correctamente
     * @param userName El nombre del usuario que quiere cargar los alfabetos
     */
    public CjtAlfabetos cargarAlfabetos(String userName) throws LecturaIncorrectaFicheroExcepcion {
        gestorFicherosAlfabetos = new GestorFicherosAlfabetos();
        return gestorFicherosAlfabetos.cargarAlfabetos(userName);
    }

    /*
     * Carga los teclados del fichero de teclados
     * @return El conjunto de teclados que se ha cargado
     * @throws LecturaIncorrectaFicheroExcepcion Si el fichero de teclados no se ha podido leer correctamente
     * @param userName El nombre del usuario que quiere cargar los teclados
     */
    public CjtTeclados cargarTeclados(String userName) throws LecturaIncorrectaFicheroExcepcion {
        gestorFicherosTeclados = new GestorFicherosTeclados();
        return gestorFicherosTeclados.cargarTeclados(userName);
    }

    /*
     * Elimina el usuario del fichero de usuarios
     * @param nombreUsuario El nombre del usuario que se quiere eliminar
     */
    public void eliminarUsuario(String nombreUsuario) {
        gestorFicherosTeclados = new GestorFicherosTeclados();
        gestorFicherosTeclados.eliminarTecladosDeUsuario(nombreUsuario);

        gestorFicherosAlfabetos = new GestorFicherosAlfabetos();
        gestorFicherosAlfabetos.eliminarAlfabetosDeUsuario(nombreUsuario);

    }
}
