package edu.upc.prop.cluster23.exceptions;

/**
 * Clase FrecuenciaIncorrectaExcepcion
 * Representa una excepción que se lanza cuando el formato de palabras con
 * frecuencia no es correcto.
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @co-author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */
public class NoHayCaracteresExcepcion extends Exception {
    public NoHayCaracteresExcepcion() {
        super("La cadena de caracteres no puede estar vacia.");
    }
}
