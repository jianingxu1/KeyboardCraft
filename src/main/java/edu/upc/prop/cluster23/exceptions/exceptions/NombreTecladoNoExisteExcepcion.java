package edu.upc.prop.cluster23.exceptions;

public class NombreTecladoNoExisteExcepcion extends Exception {
    public NombreTecladoNoExisteExcepcion(String errorMessage) {
        super("El teclado " + errorMessage + " no existe.");
    }
}
