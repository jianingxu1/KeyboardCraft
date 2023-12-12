package exceptions;

/**
 * Clase FrecuenciaIncorrectaExcepcion
 * Representa una excepción que se lanza cuando el formato de palabras con
 * frecuencia no es correcto.
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */

/**
 *  
 * Excepción que se lanza cuando el formato de palabras con frecuencia no es
 * correcto.
 */
public class FrecuenciaIncorrectaExcepcion extends Exception {
    public FrecuenciaIncorrectaExcepcion(String errorMessage) {
        super(errorMessage);
    }
}
