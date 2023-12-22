package exceptions;

/**
 * Clase ContrasenaNoValidaExcepcion
 * Excepción que se lanza cuando la contraseña no es válida.
 *
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */
public class ContrasenaNoValidaExcepcion extends Exception {

    /**
     * Constructor de la excepción.
     *
     * @param errorMessage Mensaje de error que describe la razón de la excepción.
     */
    public ContrasenaNoValidaExcepcion(String errorMessage) {
        super(errorMessage);
    }
}
