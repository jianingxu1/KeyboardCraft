package edu.upc.prop.cluster23.exceptions;

public class NombreTecladoDuplicadoExcepcion extends Exception {
    public NombreTecladoDuplicadoExcepcion(String errorMessage) {
        super("El teclado " + errorMessage + " ya existe en el conjunto de teclados.");
    }
}
