package domain;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.NoHayCaracteresExcepcion;
import exceptions.NombreAlfabetoDuplicadoExcepcion;
import exceptions.NombreAlfabetoNoValidoExcepcion;

/**
 * Clase CjtAlfabetos
 * Representa una colección de alfabetos en el sistema.
 *
 * Esta clase gestiona y almacena un conjunto de alfabetos, proporcionando
 * métodos
 * para crear, modificar, acceder y eliminar alfabetos en la colección.
 *
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */
public class CjtAlfabetos {

    /** ----- Atributos ----- **/
    /**
     * El conjunto de alfabetos y sus respectos identificadores UNICOS
     */
    private HashMap<String, Alfabeto> alfabetos;

    /** ----- Constructora ----- **/

    /**
     * Construye una nueva instancia de CjtAlfabeto sin ningun alfabeto.
     */
    public CjtAlfabetos() {
        alfabetos = new HashMap<String, Alfabeto>();
    }

    private static CjtAlfabetos singletonObject;

    public static CjtAlfabetos getInstance() {
        if (singletonObject == null)
            singletonObject = new CjtAlfabetos();
        return singletonObject;
    }

    /** ----- Métodos públicos ----- **/

    /** ----- Setters ----- **/

    /**
     * Anade un alfabeto al conjunto de alfabetos ya creados anteriormente.
     *
     * @param nombre     El nombre del alfabeto.
     * @param caracteres Los caracteres del alfabeto.
     * @throws NombreAlfabetoNoValidoExcepcion  Si el nombre del alfabeto no es
     *                                          valido.
     * @throws NombreAlfabetoDuplicadoExcepcion Si el nombre del alfabeto ya existe.
     * @throws NoHayCaracteresExcepcion         Si no hay caracteres en el alfabeto.
     */
    public void crearAlfabeto(String nombre, String caracteres)
            throws NombreAlfabetoNoValidoExcepcion, NombreAlfabetoDuplicadoExcepcion, NoHayCaracteresExcepcion {

        // Validar nombre del alfabeto
        if (nombre.trim().isEmpty()) {
            throw new NombreAlfabetoNoValidoExcepcion("El nombre del alfabeto no puede ser vacío.");
        }

        // Validar duplicados
        if (existeAlfabeto(nombre)) {
            throw new NombreAlfabetoDuplicadoExcepcion(nombre);
        }

        // Validar caracteres
        if (caracteres.trim().isEmpty()) {
            throw new NoHayCaracteresExcepcion();
        }

        // Crear el alfabeto
        Alfabeto alfabeto;

        if (caracteres.trim().length() <= 30) {
            alfabeto = new Alfabeto(nombre, caracteres);
        } else {
            String subAlf = caracteres.trim().substring(0, 30);
            alfabeto = new Alfabeto(nombre, subAlf);
        }

        // Agregar el alfabeto al conjunto
        alfabetos.put(nombre, alfabeto);
    }

    /**
     * Elimina un alfabeto del conjunto de alfabetos.
     *
     * @param nombre El identificador único del alfabeto.
     * @throws NombreAlfabetoNoValidoExcepcion Si el nombre del alfabeto no es
     *                                         válido.
     */
    public void eliminarAlfabeto(String nombre) throws NombreAlfabetoNoValidoExcepcion {
        // Validar nombre del alfabeto
        if (nombre.trim().isEmpty()) {
            throw new NombreAlfabetoNoValidoExcepcion("El nombre del alfabeto no puede ser vacío.");
        } else if (!existeAlfabeto(nombre)) {
            throw new NombreAlfabetoNoValidoExcepcion("El alfabeto \"" + nombre + "\" no existe.");
        }

        // Eliminar el alfabeto del conjunto
        alfabetos.remove(nombre);
    }

    /**
     * Modifica los caracteres de un alfabeto.
     *
     * @param nombre     El nombre del alfabeto.
     * @param caracteres Los nuevos caracteres del alfabeto.
     * @throws NombreAlfabetoNoValidoExcepcion Si el nombre del alfabeto no es
     *                                         válido.
     * @throws NoHayCaracteresExcepcion        Si no hay caracteres en el alfabeto.
     */
    public void modificarCaracteresAlfabeto(String nombre, String caracteres)
            throws NombreAlfabetoNoValidoExcepcion, NoHayCaracteresExcepcion {
        // Validar nombre del alfabeto
        if (nombre.trim().isEmpty()) {
            throw new NombreAlfabetoNoValidoExcepcion("El nombre del alfabeto no puede ser vacío.");
        } else if (!existeAlfabeto(nombre)) {
            throw new NombreAlfabetoNoValidoExcepcion("El alfabeto \"" + nombre + "\" no existe.");
        }

        // Validar caracteres
        if (caracteres.trim().isEmpty()) {
            throw new NoHayCaracteresExcepcion();
        }

        // Modificar los caracteres del alfabeto
        Alfabeto alfabeto = alfabetos.get(nombre);
        if (caracteres.trim().length() <= 30) {
            alfabeto.modificarCaracteres(caracteres);
        } else {
            String subAlf = caracteres.trim().substring(0, 30);
            alfabeto.modificarCaracteres(subAlf);
        }
    }

    /** ----- Getters ----- **/

    /**
     * Retorna la instancia de un alfabeto con el identificador especificado.
     *
     * @param nombre El identificador único del alfabeto.
     * @throws NombreAlfabetoNoValidoExcepcion Si el nombre del alfabeto no es
     *                                         válido.
     */
    public Alfabeto getAlfabeto(String nombre) throws NombreAlfabetoNoValidoExcepcion {
        // Validar nombre del alfabeto
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NombreAlfabetoNoValidoExcepcion("El nombre del alfabeto no puede ser vacío.");
        } else if (!existeAlfabeto(nombre)) {
            throw new NombreAlfabetoNoValidoExcepcion("El alfabeto \"" + nombre + "\" no existe.");
        }

        return alfabetos.get(nombre);
    }

    /**
     * Retorna los caracteres del alfabeto en formato String.
     *
     * @param nombre El nombre del alfabeto.
     * @throws NombreAlfabetoNoValidoExcepcion Si el nombre del alfabeto no es
     *                                         válido.
     */
    public String getAlfabetoCaracteresEnString(String nombre) throws NombreAlfabetoNoValidoExcepcion {
        // Validar nombre del alfabeto
        if (nombre.trim().isEmpty()) {
            throw new NombreAlfabetoNoValidoExcepcion("Introduce un nombre de alfabeto válido.");
        } else if (!existeAlfabeto(nombre)) {
            throw new NombreAlfabetoNoValidoExcepcion(nombre);
        }

        return alfabetos.get(nombre).getCaracteresStringFormat();
    }

    /**
     * Retorna true si existe un alfabeto con el nombre especificado, en su defecto
     * retorna false.
     *
     * @param nombre El nombre del alfabeto.
     */
    public Boolean existeAlfabeto(String nombre) {
        return alfabetos.containsKey(nombre);
    }

    /**
     * Retorna el alfabeto del formato {nombre | caracteres} de todos los alfabetos
     * del conjunto.
     */
    public String getNombresYCaracteresDeAlfabetos() {
        String nombresAlfabetos = "";
        int mapSize = alfabetos.size();
        int count = 0;
        for (Alfabeto alfabeto : alfabetos.values()) {
            nombresAlfabetos += alfabeto.getNombre();
            if (++count < mapSize)
                nombresAlfabetos += "\n";
        }
        return nombresAlfabetos;
    }

    /**
     * Limpia el conjunto de alfabetos
     */
    public void clearCjtAlfabetos() {
        alfabetos.clear();
    }

    /**
     * Retorna los nombres de los alfabetos
     */
    public ArrayList<String> getNombreAlfabetos() {
        ArrayList<String> nombres = new ArrayList<>();
        for (Alfabeto alfabeto : alfabetos.values()) {
            nombres.add(alfabeto.getNombre());
        }
        return nombres;
    }

    /**
     * Retorna la estructura donde se almacena el alfabeto con el nombre
     * especificado.
     *
     * @param nombre El nombre del alfabeto.
     */
    public ArrayList<Character> getCaracteresDeAlfabeto(String nombre) throws NombreAlfabetoNoValidoExcepcion {
        if (!existeAlfabeto(nombre))
            throw new NombreAlfabetoNoValidoExcepcion(nombre);
        return alfabetos.get(nombre).getCaracteres();
    }
}
