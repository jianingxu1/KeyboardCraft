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
	private ArrayList<String> caracteres;
    /** Los teclados que usan este alfabeto */
    private HashMap<String, Teclado> utilizadoPorTeclados;
	
    /** ----- Constructoras ----- **/

    /**
     * Crea un alfabeto.
     *
     * @param nombre El nombre del alfabeto.
     */
    public Alfabeto(String nombre) {
        this.nombre = nombre;
        this.caracteres = new ArrayList<String>();
        utilizadoPorTeclados = new HashMap<String, Teclado>();
    }
	
    /**
     * Crea un alfabeto.
     *
     * @param nombre El nombre del alfabeto.
     * @param caracteres Los caracteres del alfabeto.
     */
    public Alfabeto(String nombre, ArrayList<String> caracteres) {
        this.nombre = nombre;
        this.caracteres = caracteres;
        utilizadoPorTeclados = new HashMap<String, Teclado>();
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
    public void cambiarCaracteres(ArrayList<String> caracteres) {
        this.caracteres = caracteres;
    }

    /**
     * Anade un nuevo teclado el cual usará el alfabeto.
     *
     * @param teclado El teclado que usará el alfabeto.
     */
    public void anadirTeclado(Teclado teclado) {
        utilizadoPorTeclados.put(teclado.getNombre(), teclado);
    }

    /**
     * Elimina un teclado que usaba el alfabeto.
     *
     * @param nombre El nombre del teclado que usaba el alfabeto.
     */
    public void eliminarTeclado(String nombre) {
        utilizadoPorTeclados.remove(nombre);
    }

    /**
     * Elimina todos los teclados que usaban el alfabeto.
     */
    public void eliminarTeclados() {
        utilizadoPorTeclados.clear();
    }

    /** ----- Getters ----- **/

    /**
     * Retorna el teclado con el nombre especificado.
     *
     * @param nombre El nombre del teclado.
     */
    public Teclado getTeclado(String nombre) {
        return utilizadoPorTeclados.get(nombre);
    }

    //Retorna los teclados los cuales usan este alfabeto
    /**
     * Devuelve los teclados que usan este alfabeto.
     *
     * @return Los teclados que usan este alfabeto.
     */
    public Set<String> getUtilizadoPorTecladosNombre() {
        return utilizadoPorTeclados.keySet();
    }

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
    public ArrayList<String> getCaracteres() {
        return caracteres;
    }

    /**
     * Verifica si un teclado con el nombre especificado usa el alfabeto.
     */
    public boolean contieneTeclado(String nombre) {
        return utilizadoPorTeclados.containsKey(nombre);
    }
}
