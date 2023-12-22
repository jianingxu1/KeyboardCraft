package exceptions;

/**
 * Clase NoHayCaracteresExcepcion
 * Representa una excepción que se lanza cuando la cadena de caracteres está
 * vacía.
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 *
 *         Excepción que se lanza cuando la cadena de caracteres está vacía.
 */
public class NoHayCaracteresExcepcion extends Exception {

    /**
     * Constructor de la excepción.
     */
    public NoHayCaracteresExcepcion() {
        super("La cadena de caracteres no puede estar vacía.");
    }
}
