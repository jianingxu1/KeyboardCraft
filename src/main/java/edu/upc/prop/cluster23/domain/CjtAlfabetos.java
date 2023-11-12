package edu.upc.prop.cluster23.domain;

import java.util.ArrayList;
import java.util.HashMap;


/** Clase PalabrasConFrecuencia
 *  Representa una colección de alfabetos que tiene el sistema.
 *  @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */
public class CjtAlfabetos {

    /** ----- Atributos ----- **/
    /** El conjunto de alfabetos y sus respectos identificadores UNICOS */
    private HashMap<String, Alfabeto> alfabetos; 
    
    /** ----- Constructora ----- **/

    /** Construye una nueva instancia de CjtAlfabeto sin ningun alfabeto. */
    public CjtAlfabetos() {
        alfabetos = new HashMap<String, Alfabeto>();
    }

    /** ----- Métodos públicos ----- **/

    /** ----- Setters ----- **/

    /**
     * Añade un alfabeto al conjunto de alfabetos ya creados anteriormente.
     *
     * @param nombre El nombre del alfabeto.
     * @param caracteres Los caracteres del alfabeto.
     */
    public void anadirAlfabeto(String nombre, ArrayList<Character> caracteres) {
        Alfabeto alfabeto = new Alfabeto(nombre, caracteres);
        alfabetos.put(nombre, alfabeto);
    }

    /**
     * Elimina un alfabeto del conujnto de alfabetos.
     *
     * @param nombre El identificador unico del alfabeto.
     */
    public void eliminarAlfabeto(String nombre) {
        alfabetos.remove(nombre);
    }

    /**
     * Cambia el alfabeto de un alfabeto.
     *
     * @param nombre El nombre del alfabeto.
     */
    public void cambiarCaracteres (String nombre, ArrayList<Character> caracteres) {
        alfabetos.get(nombre).cambiarCaracteres(caracteres);
    }

    /**
     * Cambia el nombre de un alfabeto.
     *
     * @param nombre El nombre del alfabeto.
     * @param nuevoNombre El nuevo nombre del alfabeto.
     */
    public void cambiarNombre (String nombre, String nuevoNombre) {
        alfabetos.get(nombre).cambiarNombre(nuevoNombre);
        //actualiza la hashmap
        Alfabeto alfabeto = alfabetos.get(nombre);
        alfabetos.remove(nombre);
        alfabetos.put(nuevoNombre, alfabeto);
    }

    /** ----- Getters ----- **/

    /**
     * Retorna la instancia alfabeto con el identificador especificado.
     *
     * @param nombre El identificador unico del alfabeto.
     */
    public Alfabeto getAlfabeto(String nombre) {
        return alfabetos.get(nombre);
    }

    /**
     * Retorna la estructura donde se almacena el alfabeto con el nombre especificado.
     *
     * @param nombre El nombre del alfabeto.
     */
    public ArrayList<Character> getAlfabetoCaracteres(String nombre) {
        return alfabetos.get(nombre).getCaracteres();
    }

    /**
     * Retorna la estructura donde se almacena el alfabeto de todos los alfabetos del conjunto.
     */    
    public ArrayList<ArrayList<Character>> getAlfabetos() {
        ArrayList<ArrayList<Character>> estructuras = new ArrayList<ArrayList<Character>>();
        for (Alfabeto alfabeto : alfabetos.values()) {
            estructuras.add(alfabeto.getCaracteres());
        }
        return estructuras;
    }

    /**
     * Retorna true si existe un alfabeto con el nombre especificado, en su defecto retorna false.
     *
     * @param nombre El nombre del alfabeto.
     */
    public Boolean existeAlfabeto(String nombre) {
        return alfabetos.containsKey(nombre);
    }

}
