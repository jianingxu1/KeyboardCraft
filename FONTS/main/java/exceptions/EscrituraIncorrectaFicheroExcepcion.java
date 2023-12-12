package exceptions;

/**
 * Clase EscrituraIncorrectaFicheroExcepcion
 * 
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */

/**
 * Excepción que se lanza cuando no se puede escribir en un fichero.
 */
public class EscrituraIncorrectaFicheroExcepcion extends Exception {
    public EscrituraIncorrectaFicheroExcepcion(String errorMessage) {
        super(errorMessage);
    }
}
