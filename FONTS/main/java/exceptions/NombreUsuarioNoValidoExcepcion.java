package exceptions;

/**
 * Clase NombreUsuarioNoValidoExcepcion
 * Representa una excepción que se lanza cuando el nombre de usuario no es válido.
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 * 
 * Excepción que se lanza cuando el nombre de usuario no es válido.
 */
public class NombreUsuarioNoValidoExcepcion extends Exception {

    /**
     * Constructor de la excepción.
     * 
     * @param errorMessage Mensaje de error que indica la invalidez del nombre de usuario.
     */
    public NombreUsuarioNoValidoExcepcion(String errorMessage) {
        super(errorMessage);
    }
}
