package exceptions;

/**
 * Clase EscrituraIncorrectaFicheroExcepcion
 * Excepción que se lanza cuando no se puede escribir en un fichero.
 *
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */
public class EscrituraIncorrectaFicheroExcepcion extends Exception {

    /**
     * Constructor de la excepción.
     *
     * @param errorMessage Mensaje de error que describe la razón de la excepción.
     */
    public EscrituraIncorrectaFicheroExcepcion(String errorMessage) {
        super(errorMessage);
    }
}
