package exceptions;

/**
 * Clase IndiceTeclaFueraDeRangoExcepcion
 * Representa una excepción que se lanza cuando se intenta acceder a una tecla
 * que no existe en el teclado.
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @co-author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */
public class IndiceTeclaFueraDeRangoExcepcion extends Exception {
    public IndiceTeclaFueraDeRangoExcepcion(String errorMessage) {
        super(errorMessage);
    }
}
