package exceptions;

/**
 * Clase NombreTecladoDuplicadoExcepcion
 * Representa una excepción que se lanza cuando se intenta crear un teclado con
 * un nombre que ya existe en el sistema.
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 *
 * Excepción que se lanza cuando se intenta crear un teclado con un nombre que
 * ya existe en el sistema.
 */
public class NombreTecladoDuplicadoExcepcion extends Exception {

    /**
     * Constructor de la excepción.
     * 
     * @param errorMessage Mensaje de error que indica la duplicación del nombre del teclado.
     */
    public NombreTecladoDuplicadoExcepcion(String errorMessage) {
        super(errorMessage);
    }
}
