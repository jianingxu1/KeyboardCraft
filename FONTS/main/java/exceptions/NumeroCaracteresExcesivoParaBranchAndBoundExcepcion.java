package exceptions;

/**
 * Clase NumeroCaracteresExcesivoParaBranchAndBoundExcepcion
 * Representa una excepción que se lanza cuando el número de caracteres es excesivo
 * para el algoritmo Branch and Bound, y no lo ejecuta en un tiempo razonable.
 * 
 * @author Jianing Xu (jianing.xu@estudiantat.upc.edu)
 * 
 * Excepción que se lanza cuando el número de caracteres es excesivo
 * para el algoritmo Branch and Bound, y no lo ejecuta en un tiempo razonable.
 */
public class NumeroCaracteresExcesivoParaBranchAndBoundExcepcion extends Exception {

    /**
     * Constructor de la excepción.
     * 
     * @param errorMessage Mensaje de error que indica la razón de la excepción.
     */
    public NumeroCaracteresExcesivoParaBranchAndBoundExcepcion(String errorMessage) {
        super(errorMessage);
    }
}
