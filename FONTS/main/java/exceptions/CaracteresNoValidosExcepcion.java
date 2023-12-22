package exceptions;

/**
 * Clase CaracteresNoValidosExcepcion
 * Excepción que se lanza cuando los caracteres proporcionados para el
 * intercambio de teclado no son válidos.
 *
 * @author Jianing Xu (jianing.xu@estudiantat.upc.edu)
 */
public class CaracteresNoValidosExcepcion extends Exception {

    /**
     * Constructor de la excepción.
     *
     * @param errorMessage Mensaje de error que describe la razón de la excepción.
     */
    public CaracteresNoValidosExcepcion(String errorMessage) {
        super(errorMessage);
    }
}
