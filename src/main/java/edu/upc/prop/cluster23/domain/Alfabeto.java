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
     * @param chars Los caracteres del alfabeto.
     */
    public Alfabeto(String nombre, String chars) {
        this.nombre = nombre;
        this.caracteres = toArray(chars);
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
    public void cambiarCaracteres(String chars) {
        this.caracteres = toArray(chars);
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

    /**
     * Convertir string en vector de chars
     * 
     * @param charsS String que contiene los caracteres.
     * @return ArrayList<Character> que contiene los caracteres.
     */

    public ArrayList<Character> toArray(String charsS) {
        ArrayList<Character> caracteres = new ArrayList<Character>();

        String chars = charsS.replaceAll("\\s","");

        for (int i = 0; i < chars.length(); ++i) {
            if (!caracteres.contains(chars.charAt(i))) {
                caracteres.add(chars.charAt(i));
            }
        }

        return caracteres;
    }

    /**
     * Convertir vector de chars en String
     * 
     * @param chars vector que contiene los characteres.
     * @return String que representa la cadena de chars.
     */
    public String toString(ArrayList<Character> chars){
        String s="";
        for(int i = 0; i < chars.size(); ++i){
            s+=chars.get(i);
            s+="\n";
        }
        return s;
    }
}
