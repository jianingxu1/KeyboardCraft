package exceptions;

/**
 * Clase NoHayCaracteresExcepcion
 * Representa una excepción que se lanza cuando la cadena de caracteres está vacía.
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @co-author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */
public class NoHayCaracteresExcepcion extends Exception {
    public NoHayCaracteresExcepcion() {
        super("La cadena de caracteres no puede estar vacía.");
    }
}
