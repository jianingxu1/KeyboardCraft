package exceptions;

/**
 * Clase NombreUsuarioNoValidoExcepcion
 * 
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */

/**
 *  Excepción que se lanza cuando el nombre de usuario no es válido.
 */
public class NombreUsuarioNoValidoExcepcion extends Exception {
    public NombreUsuarioNoValidoExcepcion(String errorMessage) {
        super(errorMessage);
    }
}
