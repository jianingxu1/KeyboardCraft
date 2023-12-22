package exceptions;

/**
 * Clase NumeroCaracteresExcesivoParaBranchAndBoundExcepcion
 * 
 * @author Jianing Xu (jianing.xu@estudiantat.upc.edu)
 */

/**
 * Excepción que se lanza cuando el número de caracteres es excesivo para el algoritmo Branch and Bound, y no lo ejecuta en un tiempo razonable.
 */
public class NumeroCaracteresExcesivoParaBranchAndBoundExcepcion extends Exception {
    public NumeroCaracteresExcesivoParaBranchAndBoundExcepcion(String errorMessage) {
        super(errorMessage);
    }
}