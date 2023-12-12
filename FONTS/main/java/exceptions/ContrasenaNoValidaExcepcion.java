package exceptions;

/**
 * Clase ContrasenaNoValidaExcepcion
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */

/**
 * Excepción que se lanza cuando la contraseña no es válida.
 */
public class ContrasenaNoValidaExcepcion extends Exception {
    public ContrasenaNoValidaExcepcion(String errorMessage) {
        super(errorMessage);
    }
}
