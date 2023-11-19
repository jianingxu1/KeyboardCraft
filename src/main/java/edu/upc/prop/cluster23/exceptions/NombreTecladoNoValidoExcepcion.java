package edu.upc.prop.cluster23.exceptions;

/** Clase NombreTecladoNoExisteExcepcion
 * Representa una excepción que se lanza cuando se intenta acceder a un teclado que no existe en el sistema.
 *  @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 *  @co-author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */
public class NombreTecladoNoValidoExcepcion extends Exception {
    public NombreTecladoNoValidoExcepcion(String errorMessage) {
        super(errorMessage);
    }
}
