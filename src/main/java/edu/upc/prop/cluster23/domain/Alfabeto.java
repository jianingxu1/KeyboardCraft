package edu.upc.prop.cluster23.domain;

/** Clase PalabrasConFrecuencia
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
    private Map<String,Teclado> teclados; 
	
    /** ----- Constructora ----- **/

    /**
     * Crea un alfabeto.
     *
     * @param nombre El nombre del alfabeto.
     */
    public Alfabeto (String nombre) {
        this.nombre = nombre;
    }
	
    /**
     * Crea un alfabeto.
     *
     * @param nombre El nombre del alfabeto.
     * @param caracteres Los caracteres del alfabeto.
     */
    public Alfabeto (String nombre, ArrayList<String> caracteres) {
        this.nombre = nombre;
        this.caracteres = caracteres;
    }

	/** ----- Métodos públicos ----- **/

    /** ----- Setters ----- **/

    /**
     * Cambia el alfabeto del alfabeto.
     *
     * @param caracteres El nuevo alfabeto del alfabeto.
     */
    public void setCaracteres(ArrayList<String> caracteres) {
        this.caracteres = caracteres;
    }

    /**
     * Añade un nuevo teclado el cual usará el alfabeto.
     *
     * @param teclado El teclado que usará el alfabeto.
     */
    public void addTeclado(Teclado teclado) {
        teclados.put(teclado.getNombre(),teclado);
    }

    /**
     * Elimina un teclado que usaba el alfabeto.
     *
     * @param nombre El nombre del teclado que usaba el alfabeto.
     */
    public void removeTeclado(String nombre) {
        teclados.remove(nombre);
    }

    /**
     * Elimina todos los teclados que usaban el alfabeto.
     */
    public void removeAllTeclados() {
        teclados.clear();
    }

    /**
     * Retorna el teclado con el nombre especificado.
     *
     * @param nombre El nombre del teclado.
     */
    public Teclado getTeclado(String nombre) {
        return teclados.get(nombre);
    }

    /** ----- Getters ----- **/

    //Retorna los teclados los cuales usan este alfabeto
    /**
     * Devuelve los teclados que usan este alfabeto.
     *
     * @return Los teclados que usan este alfabeto.
     */
    public Set<Teclados> getTeclados() {
        return teclados.keySet();
    }

    /**
     * Devuelve los caracteres del alfabeto, en una estructura de datos.
     *
     * @return Los caracteres del alfabeto.
     */
    public ArrayList<String> getAlfabetoEstructura() {
        return caracteres;
    }

    /**
     * Devuelve la instancia del alfabeto.
     *
     * @return La instancia del alfabeto.
     */
    public Alfabeto getAlfabetoInstancia() {
        return this;
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
     * Verifica si un teclado con el nombre especificado usa el alfabeto.
     */
    public boolean containsTeclado(String nombre) {
        return teclados.containsKey(nombre);
    }

}
