package domain;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.NoHayCaracteresExcepcion;
import exceptions.NombreAlfabetoDuplicadoExcepcion;
import exceptions.NombreAlfabetoNoValidoExcepcion;

/**
 * Clase PalabrasConFrecuencia
 * Representa una colección de alfabetos que tiene el sistema.
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
            singletonObject = new CjtAlfabetos() {
            };
        return singletonObject;
    }

    /** ----- Métodos públicos ----- **/

    /** ----- Setters ----- **/

    /**
     * Anade un alfabeto al conjunto de alfabetos ya creados anteriormente.
     *
     * @param nombre     El nombre del alfabeto.
     * @param caracteres Los caracteres del alfabeto.
     * @throws NombreAlfabetoNoValidoExcepcion Si el nombre del alfabeto no es valido.
     * @throws NombreAlfabetoDuplicadoExcepcion Si el nombre del alfabeto ya existe.
     * @throws NoHayCaracteresExcepcion         Si no hay caracteres en el alfabeto.
     */
    public void crearAlfabeto(String nombre, String caracteres) throws NombreAlfabetoNoValidoExcepcion, NombreAlfabetoDuplicadoExcepcion, NoHayCaracteresExcepcion{
        if (nombre.trim().isEmpty()) throw new NombreAlfabetoNoValidoExcepcion("El nombre del alfabeto no puede ser vacio.");

        else if (existeAlfabeto(nombre))
        throw new NombreAlfabetoDuplicadoExcepcion(nombre);

        else if (caracteres.trim().isEmpty())
        throw new NoHayCaracteresExcepcion();

        Alfabeto alfabeto;

        if (caracteres.trim().length() <= 30) alfabeto = new Alfabeto(nombre, caracteres);
        else {
            String subAlf = caracteres.trim().substring(0,30);
            alfabeto = new Alfabeto(nombre, subAlf);
        }
        alfabetos.put(nombre, alfabeto);
    }

    /**
     * Elimina un alfabeto del conujnto de alfabetos.
     *
     * @param nombre El identificador unico del alfabeto.
     * @throws NombreAlfabetoNoValidoExcepcion Si el nombre del alfabeto no es valido.
     */
    public void eliminarAlfabeto(String nombre) throws NombreAlfabetoNoValidoExcepcion {
        if (nombre.trim().isEmpty())
        throw new NombreAlfabetoNoValidoExcepcion("El nombre del alfabeto no puede ser vacio.");
    else if (!existeAlfabeto(nombre))
        throw new NombreAlfabetoNoValidoExcepcion("El alfabeto \"" + nombre + "\" no existe.");

        alfabetos.remove(nombre);
    }

    /**
     * Modifica los caracteres de un alfabeto.
     *
     * @param nombre     El nombre del alfabeto.
     * @param caracteres Los nuevos caracteres del alfabeto.
     * @throws NombreAlfabetoNoValidoExcepcion Si el nombre del alfabeto no es valido.
     * @throws NoHayCaracteresExcepcion         Si no hay caracteres en el alfabeto.
     */
    public void modificarCaracteresAlfabeto(String nombre, String caracteres) throws NombreAlfabetoNoValidoExcepcion, NoHayCaracteresExcepcion{
        if (nombre.trim().isEmpty())
        throw new NombreAlfabetoNoValidoExcepcion("El nombre del alfabeto no puede ser vacio.");
        
        else if (!existeAlfabeto(nombre))
        throw new NombreAlfabetoNoValidoExcepcion("El alfabeto \"" + nombre + "\" no existe.");

        else if (caracteres.trim().isEmpty()) throw new NoHayCaracteresExcepcion();

        if (caracteres.trim().length() <= 30) alfabetos.get(nombre).modificarCaracteres(caracteres);
        else {
            String subAlf = caracteres.trim().substring(0,30);
            alfabetos.get(nombre).modificarCaracteres(subAlf);
        }
    }

    /** ----- Getters ----- **/

    /**
     * Retorna la instancia alfabeto con el identificador especificado.
     *
     * @param nombre El identificador unico del alfabeto.
     * @throws NombreAlfabetoNoValidoExcepcion Si el nombre del alfabeto no es valido.
     */
    public Alfabeto getAlfabeto(String nombre) throws NombreAlfabetoNoValidoExcepcion{
        if (nombre == null || nombre.trim().isEmpty()) throw new NombreAlfabetoNoValidoExcepcion("El nombre del alfabeto no puede ser vacio.");
        else if (!existeAlfabeto(nombre)) throw new NombreAlfabetoNoValidoExcepcion("El alfabeto \"" + nombre + "\" no existe.");
        return alfabetos.get(nombre);
    }

    /**
     * Retorna los caracteres del alfabeto en formato String
     *
     * @param nombre El nombre del alfabeto.
     * @throws NombreAlfabetoNoValidoExcepcion Si el nombre del alfabeto no es valido.
     */
    public String getAlfabetoCaracteresEnString(String nombre) throws NombreAlfabetoNoValidoExcepcion {
        if (nombre.trim().isEmpty()) throw new NombreAlfabetoNoValidoExcepcion("Introduce un nombre de alfabeto válido.");
        else if (!existeAlfabeto(nombre)) throw new NombreAlfabetoNoValidoExcepcion(nombre);
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
        String s = "";
        int mapSize = alfabetos.size();
        int count = 0;
        for (Alfabeto alfabeto : alfabetos.values()) {
            s += alfabeto.getNombre();
            if (++count < mapSize)
                s += "\n";
        }
        return s;
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
        if (!existeAlfabeto(nombre)) throw new NombreAlfabetoNoValidoExcepcion(nombre);
        return alfabetos.get(nombre).getCaracteres();
    }
}
