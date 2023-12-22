package domain;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.IndiceTeclaFueraDeRangoExcepcion;
import exceptions.NombreTecladoDuplicadoExcepcion;
import exceptions.NombreTecladoNoValidoExcepcion;

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
     * Crea un teclado nuevo y lo guarda en el conjunto
     * 
     * @param nombre el nombre del nuevo teclado
     * @param mat    su matriz de caractéres que representa la distribución de su
     *               teclado
     * @throws NombreTecladoDuplicadoExcepcion si el nombre del teclado ya existe 
     * @throws NombreTecladoNoValidoExcepcion si el nombre del teclado no es valido
     */
    public void crearTeclado(String nombre, Character[][] mat) throws NombreTecladoDuplicadoExcepcion, NombreTecladoNoValidoExcepcion{
        if (nombre == null || nombre.trim().isEmpty()) throw new NombreTecladoNoValidoExcepcion("El nombre del teclado no puede ser vacio."); 
        else if (existeTeclado(nombre))
            throw new NombreTecladoDuplicadoExcepcion("El teclado " + nombre + " ya existe.");
        Teclado teclado = new Teclado(nombre, mat);
        conjunto.put(nombre, teclado);
    }

    /**
     * Borra un teclado del conjunto
     * 
     * @param nombre el nombre del teclado a borrar
     * @throws NombreTecladoNoValidoExcepcion si el nombre del teclado no es valido
     */

    public void eliminarTeclado(String nombre) throws NombreTecladoNoValidoExcepcion{
        if (nombre == null || nombre.trim().isEmpty()) throw new NombreTecladoNoValidoExcepcion("El nombre del teclado no puede ser vacio.");

        else if (!existeTeclado(nombre)) throw new NombreTecladoNoValidoExcepcion("El teclado " + nombre + " no existe.");
        conjunto.remove(nombre);
    }

    public void intercambiarTeclasTeclado(String nombre, char caracter1, char caracter2) throws NombreTecladoNoValidoExcepcion, IndiceTeclaFueraDeRangoExcepcion, Exception {
        if (nombre == null || nombre.trim().isEmpty())
            throw new NombreTecladoNoValidoExcepcion("El nombre del teclado no puede ser vacio.");
        
        else if (!existeTeclado(nombre))
			throw new NombreTecladoNoValidoExcepcion("El teclado " + nombre + " no existe.");

		// Character[][] distribucion = getDistribucionTeclado(nombre);
		// String mensaje = "";

		// if (i1 < 0 || i1 >= distribucion.length || j1 < 0 || j1 >= distribucion[0].length || i2 < 0
		// 		|| i2 >= distribucion.length || j2 < 0 || j2 >= distribucion[0].length) {
		// 	if (i1 < 0 || i1 >= distribucion.length || j1 < 0 || j1 >= distribucion[0].length) {
		// 		mensaje += "La posicion de la primera tecla " + i1 + " " + j1 + " no es correcta";
		// 	}
		// 	if (i2 < 0 || i2 >= distribucion.length || j2 < 0 || j2 >= distribucion[0].length) {
		// 		if (mensaje.isEmpty())
		// 			mensaje += "La posicion de la segunda tecla " + i2 + " " + j2 + " no es correcta";
		// 		else {
		// 			mensaje += " y la posicion de la segunda tecla " + i2 + " " + j2 + " no es correcta";
		// 		}
		// 	}

		// 	throw new IndiceTeclaFueraDeRangoExcepcion(mensaje);
		// }
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
     * Deuvelve la matriz de caractéres que representa la distribución de teclas de
     * un teclado
     * 
     * @param nombre nombre del teclado escogido
     * @return matriz de caractéres (char[][]) que representa la distribución
     */
    public Character[][] getDistribucionTeclado(String nombreTeclado) throws NombreTecladoNoValidoExcepcion {
        if (nombreTeclado == null || nombreTeclado.trim().isEmpty())
            throw new NombreTecladoNoValidoExcepcion("El nombre del teclado no puede ser vacio.");
		else if (!existeTeclado(nombreTeclado))
            throw new NombreTecladoNoValidoExcepcion("El teclado " + nombreTeclado + " no existe.");
        return conjunto.get(nombreTeclado).getDistribucion();   
    }
    
    public void clearCjtTeclados() {
        conjunto.clear();
    }
}
