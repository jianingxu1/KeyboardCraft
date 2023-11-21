package exceptions;

/**
 * Clase TipoAlgoritmoIncorrectoExcepcion
 * Representa una excepción que se lanza cuando se intenta crear un algoritmo
 * con un nombre incorrecto (vacío) o que no existe en el sistema.
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @co-author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */

public class TipoAlgoritmoIncorrectoExcepcion extends Exception {
    public TipoAlgoritmoIncorrectoExcepcion(String errorMessage) {
        super(errorMessage);
    }
}