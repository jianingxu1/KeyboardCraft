package domain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase CjtTeclados
 * Representa un conjunto de teclados. Estos se definen por su nombre
 * 
 * @author Rubén Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 */
public class CjtTeclados {

    /** Atributos **/
    private HashMap<String, Teclado> conjunto;

    /** Constructora **/
    public CjtTeclados() {
        conjunto = new HashMap<String, Teclado>();
    }

    private static CjtTeclados singletonObject;

    /**
     * Devuelve la instancia de CjtTeclados
     * 
     * @return instancia de CjtTeclados
     */
    public static CjtTeclados getInstance() {
        if (singletonObject == null)
            singletonObject = new CjtTeclados() {
            };
        return singletonObject;
    }

    /** Métodos públicos **/

    /** ----- Setters ----- **/

    /**
     * Crea un teclado nuevo y lo guarda en el conjunto
     * 
     * @param nombre el nombre del nuevo teclado
     * @param mat    su matriz de caractéres que representa la distribución de su
     *               teclado
     */
    public void crearTeclado(String nombre, char[][] mat) {
        Teclado teclado = new Teclado(nombre, mat);
        conjunto.put(nombre, teclado);
    }

    /**
     * Borra un teclado del conjunto
     * 
     * @param nombre el nombre del teclado a borrar
     */

    public void eliminarTeclado(String nombre) {
        conjunto.remove(nombre);
    }

    /**
     * Intercambia dos teclas de un teclado del conjunto
     * 
     * @param nombre el nombre del teclado al que se le intercambian las disposición
     *               de teclas
     * @param i1     fila tecla 1
     * @param j1     col. tecla 1
     * @param i2     fila tecla 2
     * @param j2     col. tecla 2
     */

    public void intercambiarTeclasTeclado(String nombre, int i1, int j1, int i2, int j2) {
        conjunto.get(nombre).intercambiarTeclas(i1, j1, i2, j2);
    }

    /**
     * Asigna nueva distribución de teclas a un teclado
     * 
     * @param nombre nombre del teclado
     * @param dist   matriz de caractéres a asignar
     */
    public void setDistribucionTeclado(String nombre, char[][] dist) {
        conjunto.get(nombre).setDistribucion(dist);
    }

    // ** ----- Getters ----- **/

    /**
     * Indica si un teclado existe o no en el conjunto
     * 
     * @param nombre el nombre del teclado del cual comprobamos su existencia
     * @return true si existe en el conjunto, false en cualquier otro caso
     */

    public boolean existeTeclado(String nombre) {
        return conjunto.containsKey(nombre);
    }

    /**
     * Deuvelve la matriz de caractéres que representa la distribución de teclas de
     * un teclado
     * 
     * @param nombre nombre del teclado escogido
     * @return matriz de caractéres (char[][]) que representa la distribución
     */

    public char[][] getTeclado(String nombre) {
        return conjunto.get(nombre).getTeclado();
    }

    /**
     * Devuele un vector de Strings con los nombres de todos los teclados del
     * conjunto.
     * 
     * @return vector de Strings.
     */
    public ArrayList<String> getNombreTeclados() {
        if (conjunto.isEmpty())
            return new ArrayList<String>();

        ArrayList<String> nombres = new ArrayList<String>();
        for (String nombre : conjunto.keySet()) {
            nombres.add(nombre);
        }
        return nombres;
    }

    /**
     * Devuelve el objeto Teclado del conjunto de teclados.
     * 
     * @param nombre El nombre del teclado que se ha de consultar.
     * @return Teclado del conjunto de teclados.
     */
    public Teclado getTecladoObjeto(String nombre) {
        return conjunto.get(nombre);
    }

    /**
     * Devuelve la distribución de un teclado en formato String
     * 
     * @param nombre nombre del teclado
     * @return String que representa la distribución
     */
    public String getDistribucioString(String nombre) {
        return conjunto.get(nombre).toString();
    }

    /**
     * Devuelve el número de teclados en el conjunto
     * 
     * @return entero con el tamaño
     */
    public int totalTeclados() {
        return conjunto.size();
    }
}
