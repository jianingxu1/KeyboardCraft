package edu.upc.prop.cluster23.exceptions;

public class NombreAlfabetoDuplicadoExcepcion extends Exception {
    public NombreAlfabetoDuplicadoExcepcion(String errorMessage) {
        super("El alfabeto " + errorMessage + " ya existe en el conjunto de alfabetos.");
    }
}
