package edu.upc.prop.cluster23.exceptions;

public class InputNoEsEnteroExcepcion extends Exception {
    public InputNoEsEnteroExcepcion(String errorMessage) {
        super("La entrada: " + errorMessage + " no es un numero entero.");
    }
}
