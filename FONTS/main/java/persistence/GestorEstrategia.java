package persistence;

import exceptions.EscrituraIncorrectaFicheroExcepcion;
import exceptions.LecturaIncorrectaFicheroExcepcion;

import java.util.HashMap;

// Interfaz para la estrategia de escritura y lectura
// Interfaz para la estrategia de escritura y lectura
public interface GestorEstrategia<T,U> {
    /**
     * Guarda los datos para un usuario dado.
     * @param username
     * @param datos
     * @throws EscrituraIncorrectaFicheroExcepcion
     */
    void guardar(String username, HashMap<String, T> datos) throws EscrituraIncorrectaFicheroExcepcion;

    /**
     * Carga los datos para un usuario dado.
     * @param username
     * @return
     * @throws LecturaIncorrectaFicheroExcepcion
     */
    HashMap<String, U> cargar(String username) throws LecturaIncorrectaFicheroExcepcion;
}