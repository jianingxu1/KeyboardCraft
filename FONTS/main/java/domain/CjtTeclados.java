package domain;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.CaracteresNoValidosExcepcion;
import exceptions.NombreTecladoDuplicadoExcepcion;
import exceptions.NombreTecladoNoValidoExcepcion;

/**
 * Clase CjtTeclados
 * Representa un conjunto de teclados. Cada teclado está definido por su nombre y su distribución de teclas.
 * Esta clase proporciona métodos para gestionar y manipular los teclados en el conjunto.
 * 
 * Funcionalidades principales:
 * - Crear un nuevo teclado en el conjunto.
 * - Eliminar un teclado existente del conjunto.
 * - Obtener la distribución de teclas de un teclado específico.
 * - Intercambiar la posición de teclas en un teclado.
 * 
 * Cada teclado tiene un nombre único y una matriz de caracteres que representa la disposición de las teclas.
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
            singletonObject = new CjtTeclados();
        return singletonObject;
    }

    /** Métodos públicos **/

    /** ----- Modificadoras ----- **/

    /**
     * Asigna nueva distribución de teclas a un teclado
     * 
     * @param nombre nombre del teclado
     * @param dist   matriz de caractéres a asignar
     */
    public void setDistribucionTeclado(String nombre, Character[][] dist) {
        conjunto.get(nombre).setDistribucion(dist);
    }

    /**
     * Crea un teclado nuevo y lo guarda en el conjunto.
     * 
     * @param nombre El nombre del nuevo teclado.
     * @param mat    La matriz de caracteres que representa la distribución del teclado.
     * @throws NombreTecladoDuplicadoExcepcion Si el nombre del teclado ya existe.
     * @throws NombreTecladoNoValidoExcepcion Si el nombre del teclado no es válido.
     */
    public void crearTeclado(String nombre, Character[][] mat)
            throws NombreTecladoDuplicadoExcepcion, NombreTecladoNoValidoExcepcion {
        // Validar nombre del teclado
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NombreTecladoNoValidoExcepcion("El nombre del teclado no puede ser vacío.");
        } else if (existeTeclado(nombre)) {
            throw new NombreTecladoDuplicadoExcepcion("El teclado \"" + nombre + "\" ya existe.");
        }

        // Crear y almacenar el nuevo teclado en el conjunto
        Teclado teclado = new Teclado(nombre, mat);
        conjunto.put(nombre, teclado);
    }

    /**
     * Borra un teclado del conjunto.
     * 
     * @param nombre El nombre del teclado a borrar.
     * @throws NombreTecladoNoValidoExcepcion Si el nombre del teclado no es válido.
     */
    public void eliminarTeclado(String nombre) throws NombreTecladoNoValidoExcepcion {
        // Validar nombre del teclado
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NombreTecladoNoValidoExcepcion("El nombre del teclado no puede ser vacío.");
        } else if (!existeTeclado(nombre)) {
            throw new NombreTecladoNoValidoExcepcion("El teclado \"" + nombre + "\" no existe.");
        }

        // Eliminar el teclado del conjunto
        conjunto.remove(nombre);
    }

    /**
     * Intercambia la posición (i,j) de dos teclas del teclado.
     *
     * @param nombre     El nombre del teclado.
     * @param caracter1  El primer carácter a intercambiar.
     * @param caracter2  El segundo carácter a intercambiar.
     * @throws NombreTecladoNoValidoExcepcion     Si el nombre del teclado no es válido.
     * @throws CaracteresNoValidosExcepcion       Si los caracteres proporcionados no son válidos.
     */
    public void intercambiarTeclasTeclado(String nombre, char caracter1, char caracter2)
            throws NombreTecladoNoValidoExcepcion, CaracteresNoValidosExcepcion {
        // Validar nombre del teclado
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NombreTecladoNoValidoExcepcion("El nombre del teclado no puede ser vacío.");
        } else if (!existeTeclado(nombre)) {
            throw new NombreTecladoNoValidoExcepcion("El teclado \"" + nombre + "\" no existe.");
        }
        
        // Realizar el intercambio de teclas en el teclado correspondiente
        conjunto.get(nombre).intercambiarTeclas(caracter1, caracter2);
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
     * Devuelve la matriz de caracteres que representa la distribución de teclas de un teclado.
     *
     * @param nombreTeclado El nombre del teclado escogido.
     * @return Matriz de caracteres (char[][]) que representa la distribución.
     * @throws NombreTecladoNoValidoExcepcion Si el nombre del teclado no es válido.
     */
    public Character[][] getDistribucionTeclado(String nombreTeclado) throws NombreTecladoNoValidoExcepcion {
        // Validar nombre del teclado
        if (nombreTeclado == null || nombreTeclado.trim().isEmpty()) {
            throw new NombreTecladoNoValidoExcepcion("El nombre del teclado no puede ser vacío.");
        } else if (!existeTeclado(nombreTeclado)) {
            throw new NombreTecladoNoValidoExcepcion("El teclado \"" + nombreTeclado + "\" no existe.");
        }

        // Devolver la matriz de distribución del teclado correspondiente
        return conjunto.get(nombreTeclado).getDistribucion();
    }
    
    /**
     * Limpiar el conjunto de teclados
     */
    public void clearCjtTeclados() {
        conjunto.clear();
    }
}
