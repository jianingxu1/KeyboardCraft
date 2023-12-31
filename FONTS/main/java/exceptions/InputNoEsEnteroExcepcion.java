package exceptions;

/**
 * Clase InputNoEsEnteroExcepcion
 * Representa una excepción que se lanza cuando el valor a leer de la entrada no es un número entero.
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 *
 * Excepción que se lanza cuando el valor a leer de la entrada no es un número entero.
 */
public class InputNoEsEnteroExcepcion extends Exception {

    /**
     * Constructor de la excepción.
     *
     * @param errorMessage Mensaje de error que describe la razón de la excepción.
     */
    public InputNoEsEnteroExcepcion(String errorMessage) {
        super("La entrada: " + errorMessage + " no es un número entero.");
    }
}
