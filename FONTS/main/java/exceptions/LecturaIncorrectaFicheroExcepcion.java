package exceptions;

/**
 * Clase LecturaIncorrectaFicheroExcepcion
 * 
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */

 /**
  * Excepción que se lanza cuando no se puede leer de un fichero.
  */
public class LecturaIncorrectaFicheroExcepcion extends Exception {
    public LecturaIncorrectaFicheroExcepcion(String errorMessage) {
        super(errorMessage);
    }
}
