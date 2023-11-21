package exceptions;

/**
 * Clase InputNoEsEnteroExcepcion
 * Representa una excepción que se lanza cuando el valor a leer de la entrada no es un número entero.
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @co-author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */
public class InputNoEsEnteroExcepcion extends Exception {
    public InputNoEsEnteroExcepcion(String errorMessage) {
        super("La entrada: " + errorMessage + " no es un número entero.");
    }
}
