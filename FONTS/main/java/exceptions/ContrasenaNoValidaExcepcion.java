package exceptions;

/**
 * Clase ContrasenaNoValidaExcepcion
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */
public class ContrasenaNoValidaExcepcion extends Exception {
    public ContrasenaNoValidaExcepcion(String errorMessage) {
        super(errorMessage);
    }
}
