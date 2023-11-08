package edu.upc.prop.cluster23.domain;

import java.util.ArrayList;
import java.util.HashMap;
import Alfabeto;

/** Clase PalabrasConFrecuencia
 *  Representa una colección de alfabetos que tiene el sistema.
 *  @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */
public class Cjt_Alfabetos {

    /** ----- Atributos ----- **/
    /** El conjunto de alfabetos y sus respectos identificadores UNICOS */
    private HashMap<String,Alfabeto> alfabetos; 
    
    /** ----- Constructora ----- **/

    /** Construye una nueva instancia de CjtAlfabeto sin ningun alfabeto. */
    public CjtAlfabetos () {
        alfabetos = new HashMap<String,Alfabeto>();
    }

    /** ----- Métodos públicos ----- **/

    /** ----- Setters ----- **/

    /**
     * Añade un alfabeto al conjunto de alfabetos ya creados anteriormente.
     *
     * @param nombre El nombre del alfabeto.
     * @param caracteres Los caracteres del alfabeto.
     */
    public void addAlfabeto (String nombre, ArrayList<String> caracteres) {
        Alfabeto alfabeto = new Alfabeto(nombre,caracteres);
        alfabetos.put(nombre,alfabeto);
    }

    /**
     * Elimina un alfabeto del conujnto de alfabetos.
     *
     * @param nombre El identificador unico del alfabeto.
     */
    public void removeAlfabeto (int nombre) {
        alfabetos.remove(nombre);
    }

    /**
     * Cambia el alfabeto de un alfabeto.
     *
     * @param nombre El nombre del alfabeto.
     */
    public void changeCaracteres (String nombre, ArrayList<String> caracteres) {
        alfabetos.get(nombre).setCaracteres(caracteres);
    }

    /** ----- Getters ----- **/

    /**
     * Retorna la instancia alfabeto con el identificador especificado.
     *
     * @param nombre El identificador unico del alfabeto.
     */
    public Alfabeto getAlfabetoInstancia (int nombre) {
        return alfabetos.get(nombre);
    }

    /**
     * Retorna la estructura donde se almacena el alfabeto con el nombre especificado.
     *
     * @param nombre El nombre del alfabeto.
     */
    public ArrayList<String> getAlfabetoEstructura (String nombre) {
        return alfabetos.get(nombre).getAlfabeto();
    }

    /**
     * Retorna la estructura donde se almacena el alfabeto de todos los alfabetos del conjunto.
     */    
    public ArrayList<ArrayList<String>> getAlfabetos () {
        ArrayList<ArrayList<String>> estructuras = new ArrayList<ArrayList<String>>();
        for (Alfabeto alfabeto : alfabetos.values()) {
            estructuras.add(alfabeto.getAlfabeto());
        }
        return estructuras;
    }

    /**
     * Retorna una instancia de todos los alfabetos del conjunto.
     */    
    public Set<Alfabeto> getAlfabetosInstancia () {
        return alfabetos.values();
    }

    /**
     * Retorna true si existe un alfabeto con el nombre especificado, en su defecto retorna false.
     *
     * @param nombre El nombre del alfabeto.
     */
    public bool existAlfabeto (String nombre) {
        return alfabetos.containsKey(nombre);
    }

}
