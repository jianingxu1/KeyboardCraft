package edu.upc.prop.cluster23.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/** Clase Alfabeto
 *  Representa un alfabeto en concreto con sus caracteres y teclados asociados.
 *  @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */
public class Alfabeto {

	/** ----- Atributos ----- **/
	
    /** Nombre e Identificador del alfabeto en cuestion*/
	private String nombre;
    /** Los caracteres que contiene el alfabeto */
	private ArrayList<Character> caracteres;

    /** ----- Constructoras ----- **/

    /**
     * Crea un alfabeto.
     *
     * @param nombre El nombre del alfabeto.
     */
    public Alfabeto(String nombre) {
        this.nombre = nombre;
        this.caracteres = new ArrayList<Character>();
    }
	
    /**
     * Crea un alfabeto.
     *
     * @param nombre El nombre del alfabeto.
     * @param caracteres Los caracteres del alfabeto.
     */
    public Alfabeto(String nombre, ArrayList<Character> caracteres) {
        this.nombre = nombre;
        this.caracteres = caracteres;
    }

	/** ----- Métodos públicos ----- **/

    /** ----- Modificadoras ----- **/

    public void cambiarNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Cambia los caracteres del alfabeto.
     *
     * @param caracteres Los nuevos caracteres del alfabeto.
     */
    public void cambiarCaracteres(ArrayList<Character> caracteres) {
        this.caracteres = caracteres;
    }

    /** ----- Getters ----- **/

    /**
     * Devuelve el nombre del alfabeto.
     *
     * @return El nombre del alfabeto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve los caracteres del alfabeto, en una estructura de datos.
     *
     * @return Los caracteres del alfabeto.
     */
    public ArrayList<Character> getCaracteres() {
        return caracteres;
    }
}
