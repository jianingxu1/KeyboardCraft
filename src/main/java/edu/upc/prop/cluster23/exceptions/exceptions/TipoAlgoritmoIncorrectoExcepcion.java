package edu.upc.prop.cluster23.exceptions;

public class TipoAlgoritmoIncorrectoExcepcion extends Exception{
    public TipoAlgoritmoIncorrectoExcepcion(String errorMessage) {
        super("El tipo de algoritmo " + errorMessage + " no es correcto, debe ser QAP o SA.");
    }
}