package exceptions;

/**
 * Clase NombreAlfabetoDuplicadoExcepcion
 * Representa una excepción que se lanza cuando se intenta crear un alfabeto con
 * un nombre que ya existe en el sistema.
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */
public class NombreAlfabetoDuplicadoExcepcion extends Exception {
    public NombreAlfabetoDuplicadoExcepcion(String errorMessage) {
        super("El alfabeto " + errorMessage + " ya existe en el conjunto de alfabetos.");
    }
}
