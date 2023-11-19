package edu.upc.prop.cluster23.exceptions;

/**
 * Clase InputNoEsEnteroExcepcion
 * Representa una excepción que se lanza cuando se intenta acceder a una
 * posicion que no existe en el sistema.
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @co-author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */
public class InputNoEsEnteroExcepcion extends Exception {
    public InputNoEsEnteroExcepcion(String errorMessage) {
        super("La entrada: " + errorMessage + " no es un numero entero.");
    }
}
