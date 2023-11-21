package domain;

/**
 * Clase Texto
 * Representa el texto que se podrá utilizar para gestionar el teclado.
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 */

public class Texto {

    /**
     * Texto en que se basa el objeto de la clase.
     */
    String texto;

    // ----- Constructoras -----
    /**
     * Construye un objeto Texto con el texto que se ha proporcionado.
     *
     * @param texto El texto con el que se inicializa el objeto Texto.
     */
    public Texto(String texto) {
        this.texto = texto;
    }

    /**
     * Construye un objeto Texto con un texto vacío.
     */
    public Texto() {
        this.texto = new String();
    }

    // ----- Modificadoras -----

    /**
     * Modifica el texto que contiene el objeto Texto.
     *
     * @param texto El nuevo texto con el que se reemplazará el texto actual del
     *              objeto.
     */
    public void modificarTexto(String texto) {
        this.texto = texto;
    }

    // ----- Getters -----
    /**
     * Retorna el texto contenido en el objeto Texto.
     *
     * @return El texto que hay en el objeto Texto.
     */
    public String getTexto() {
        return this.texto;
    }

}
