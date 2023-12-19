package persistence;

import exceptions.LecturaIncorrectaFicheroExcepcion;
import java.util.ArrayList;

/**
 * Interface Algoritmo
 * 
 * @author Momin Miah Begum
 */
public interface CargarYVisualizar {
    // Este método retorna en formato String lo que hay en el fichero
    // y puede lanzar una excepción LecturaIncorrectaFicheroExcepcion
    public String cargar(String nombreArchivo) throws LecturaIncorrectaFicheroExcepcion;

    // Este método visualiza los ficheros que hay en una carpeta (Textos, Listas De Palabras)
    public ArrayList<String> visualizar();
}
