package persistence;

import exceptions.EscrituraIncorrectaFicheroExcepcion;
import exceptions.LecturaIncorrectaFicheroExcepcion;

import java.util.HashMap;

/**
 * Interfaz para la estrategia de escritura y lectura de datos.
 *
 * @param <T> Tipo de datos a escribir en el fichero.
 * @param <U> Tipo de datos a cargar desde el fichero.
 */
public interface GestorEstrategia<T, U> {
    /**
     * Guarda los datos para un usuario dado.
     *
     * @param username Nombre de usuario.
     * @param datos    Datos a guardar.
     * @throws EscrituraIncorrectaFicheroExcepcion Si ocurre un error durante la escritura del fichero.
     */
    void guardar(String username, HashMap<String, T> datos) throws EscrituraIncorrectaFicheroExcepcion;

    /**
     * Carga los datos para un usuario dado.
     *
     * @param username Nombre de usuario.
     * @return Datos cargados.
     * @throws LecturaIncorrectaFicheroExcepcion Si ocurre un error durante la lectura del fichero.
     */
    HashMap<String, U> cargar(String username) throws LecturaIncorrectaFicheroExcepcion;
}
