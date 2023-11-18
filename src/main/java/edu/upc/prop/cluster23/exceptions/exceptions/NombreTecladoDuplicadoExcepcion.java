package edu.upc.prop.cluster23.exceptions;

/** Clase NombreTecladoDuplicadoExcepcion
 *  Representa una excepción que se lanza cuando se intenta crear un teclado con un nombre que ya existe en el sistema.
 *  @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 *  @co-author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */
public class NombreTecladoDuplicadoExcepcion extends Exception {
    public NombreTecladoDuplicadoExcepcion(String errorMessage) {
        super("El teclado " + errorMessage + " ya existe en el conjunto de teclados.");
    }
}
