package exceptions;

/**
 * Clase TipoAlgoritmoIncorrectoExcepcion
 * Representa una excepción que se lanza cuando se intenta crear un algoritmo
 * con un nombre incorrecto (vacío) o que no existe en el sistema.
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 * 
 * Excepción que se lanza cuando se intenta crear un algoritmo con un nombre
 * incorrecto (vacío) o que no existe en el sistema.
 */
public class TipoAlgoritmoIncorrectoExcepcion extends Exception {

    /**
     * Constructor de la excepción.
     * 
     * @param errorMessage Mensaje de error que indica la razón de la excepción.
     */
    public TipoAlgoritmoIncorrectoExcepcion(String errorMessage) {
        super(errorMessage);
    }
}
