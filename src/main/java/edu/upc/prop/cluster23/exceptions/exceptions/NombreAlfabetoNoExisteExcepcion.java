package edu.upc.prop.cluster23.exceptions;

public class NombreAlfabetoNoExisteExcepcion extends Exception {
    public NombreAlfabetoNoExisteExcepcion(String errorMessage) {
        super("El nombre de alfabeto " + errorMessage + " no existe.");
    }
}
