package exceptions;

/**
 * Clase NombreUsuarioNoValidoExcepcion
 * 
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */
public class NombreUsuarioNoValidoExcepcion extends Exception {
    public NombreUsuarioNoValidoExcepcion(String errorMessage) {
        super(errorMessage);
    }
}
