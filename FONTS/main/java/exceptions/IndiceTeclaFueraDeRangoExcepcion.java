package exceptions;

/**
 * Clase IndiceTeclaFueraDeRangoExcepcion
 * Representa una excepción que se lanza cuando se intenta acceder a una tecla que no existe en el teclado.
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 *
 * Excepción que se lanza cuando se intenta acceder a una tecla que no existe.
 */
public class IndiceTeclaFueraDeRangoExcepcion extends Exception {

    /**
     * Constructor de la excepción.
     *
     * @param errorMessage Mensaje de error que describe la razón de la excepción.
     */
    public IndiceTeclaFueraDeRangoExcepcion(String errorMessage) {
        super(errorMessage);
    }
}
