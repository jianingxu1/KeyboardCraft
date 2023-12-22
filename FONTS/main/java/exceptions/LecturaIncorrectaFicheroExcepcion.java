package exceptions;

/**
 * Clase LecturaIncorrectaFicheroExcepcion
 * Representa una excepción que se lanza cuando no se puede leer de un fichero.
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 *
 * Excepción que se lanza cuando no se puede leer de un fichero.
 */
public class LecturaIncorrectaFicheroExcepcion extends Exception {

    /**
     * Constructor de la excepción.
     *
     * @param errorMessage Mensaje de error que describe la razón de la excepción.
     */
    public LecturaIncorrectaFicheroExcepcion(String errorMessage) {
        super(errorMessage);
    }
}
