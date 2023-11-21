package exceptions;

/**
 * Clase NombreAlfabetoNoExisteExcepcion
 * Representa una excepción que se lanza cuando se intenta acceder a un alfabeto
 * que no existe en el sistema o cuyo nombre es inválido (vacío).
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */
public class NombreAlfabetoNoValidoExcepcion extends Exception {
    public NombreAlfabetoNoValidoExcepcion(String errorMessage) {
        super(errorMessage);
    }
}
