package exceptions;

/**
 * Clase NombreTecladoNoValidoExcepcion
 * Representa una excepción que se lanza cuando se intenta acceder a un teclado
 * que no existe en el sistema o es cuyo nombre es inválido (vacío).
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 *
 * Excepción que se lanza cuando se intenta acceder a un teclado que no existe 
 * en el sistema o cuyo nombre es inválido (vacío).
 */
public class NombreTecladoNoValidoExcepcion extends Exception {

    /**
     * Constructor de la excepción.
     * 
     * @param errorMessage Mensaje de error que indica la invalidez del nombre del teclado.
     */
    public NombreTecladoNoValidoExcepcion(String errorMessage) {
        super(errorMessage);
    }
}
