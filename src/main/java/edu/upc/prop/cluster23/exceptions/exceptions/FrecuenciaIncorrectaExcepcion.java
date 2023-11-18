package edu.upc.prop.cluster23.exceptions;

public class FrecuenciaIncorrectaExcepcion extends Exception {
    public FrecuenciaIncorrectaExcepcion() {
        super("El formato de palabras con frecuncia no es correcto, debe ser palabras seguida de un espacio y su numero de frecuencia.");
    }
}
