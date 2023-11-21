package domaincontrollers;

import domain.*;
import exceptions.*;
import java.util.ArrayList;

import domain.*;
import exceptions.*;

/**
 * Clase CtrlDominio
 * Representa Controlador de Dominio. Se encarga de gestionar teclados y todas
 * sus características.
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @co-author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */

public class CtrlDominio {

	/** Atributos */
	private CjtTeclados cjtTeclados;
	private CjtAlfabetos cjtAlfabetos;

	// ----- Constructora -----

	/**
	 * Crea una instancia de CtrlDominio.
	 */
	public CtrlDominio() {
		inicializarCtrlDominio();
	}

	/**
	 * Inicializa el controlador de dominio.
	 * Se crean instancias nuevas de CjtTeclados, InformacionTeclados
	 */
	public void inicializarCtrlDominio() {
		cjtTeclados = CjtTeclados.getInstance();
		cjtAlfabetos = CjtAlfabetos.getInstance();
	}

	// ----- Métodos públicos -----

	// ----- Modificadoras -----

	/**
	 * Crea un teclado, con un texto y una lista de palabras que nos proporciona el
	 * usuario. El alfabeto ya existe.
	 *
	 * @param nombreTeclado Nombre del teclado.
	 * @param idAlfabeto    El ID del alfabeto asociado con el teclado.
	 * @param texto         Información adicional para la generación de la
	 *                      distribución del teclado.
	 * @param listaPalabras Texto que contiene las palabras que se desean incluir en
	 *                      la distribución del teclado.
	 * @param algoritmo     Un string que representa el algoritmo utilizado para la
	 *                      creación del teclado.
	 */
	public void creaTeclado(String nombreTeclado, String idAlfabeto, String texto, String listaPalabras, String algoritmo)
			throws NombreAlfabetoNoValidoExcepcion, NombreTecladoDuplicadoExcepcion, TipoAlgoritmoIncorrectoExcepcion,
			FrecuenciaIncorrectaExcepcion, NombreTecladoNoValidoExcepcion {
		// Informacion necesaria para poder crear el teclado

		if (nombreTeclado.trim().isEmpty())
			throw new NombreTecladoNoValidoExcepcion("El nombre del teclado no puede ser vacio.");
		else if (cjtTeclados.existeTeclado(nombreTeclado))
			throw new NombreTecladoDuplicadoExcepcion(nombreTeclado);

		PalabrasConFrecuencia palabras;
		String[] palabrasSeparadas = listaPalabras.split(" ");
		int words = palabrasSeparadas.length;
		if (words % 2 == 1 && !listaPalabras.isEmpty())
			throw new FrecuenciaIncorrectaExcepcion(
			"El formato de palabras con frecuencia no es correcto, debe ser palabras seguidas de un espacio y su número de frecuencia.");
		try {
			palabras = new PalabrasConFrecuencia(listaPalabras);

		} catch (NumberFormatException e) {
			throw new FrecuenciaIncorrectaExcepcion(
			"El formato de palabras con frecuencia no es correcto, debe ser palabras seguida de un espacio y su número de frecuencia.");
		} catch (IllegalArgumentException i) {
			throw new FrecuenciaIncorrectaExcepcion(i.getMessage());
		}
		if (idAlfabeto.trim().isEmpty())
			throw new NombreAlfabetoNoValidoExcepcion("El nombre del alfabeto no puede ser vacio.");

		else if (!cjtAlfabetos.existeAlfabeto(idAlfabeto))
			throw new NombreAlfabetoNoValidoExcepcion("El alfabeto \"" + idAlfabeto + "\" no existe.");

		if (algoritmo.trim().isEmpty())
			throw new TipoAlgoritmoIncorrectoExcepcion("El algoritmo no puede ser vacio.");
		else if (!algoritmo.equals("QAP") && !algoritmo.equals("SA"))
			throw new TipoAlgoritmoIncorrectoExcepcion(
					"El tipo de algoritmo \"" + algoritmo + "\" no es correcto, debe ser QAP o SA.");

		Texto text = new Texto(texto);
		Alfabeto alfabeto = cjtAlfabetos.getAlfabeto(idAlfabeto);

		char[][] distribucion = new char[3][10];

		// El algoritmo genera una distribucion dependiendo del algoritmo elejido
		if (algoritmo.equals("B&B")) {
			GeneradorDistribucionTeclado gdt = new GeneradorDistribucionTeclado(new AlgoritmoBranchAndBound());
			distribucion = gdt.generarDistribucion(alfabeto, palabras, text);
		} else if (algoritmo.equals("SA")) {
			GeneradorDistribucionTeclado gdt = new GeneradorDistribucionTeclado(new AlgoritmoSimulatedAnnealing());
			distribucion = gdt.generarDistribucion(alfabeto, palabras, text);

		}
		// Se añade el teclado con la distribucion generada
		cjtTeclados.crearTeclado(nombreTeclado, distribucion);
	}

	/**
	 * Elimina el teclado del conjunto de teclados y su respectiva información.
	 *
	 * @param nombreTeclado El nombre del teclado que se procederá a eliminar.
	 */

	public void borrarTeclado(String nombreTeclado) throws NombreTecladoNoValidoExcepcion {
		if (nombreTeclado.trim().isEmpty())
			throw new NombreTecladoNoValidoExcepcion("El nombre del teclado no puede ser vacio.");
		else if (!cjtTeclados.existeTeclado(nombreTeclado))
			throw new NombreTecladoNoValidoExcepcion("El teclado " + nombreTeclado + " no existe.");
		cjtTeclados.eliminarTeclado(nombreTeclado);
	}

	/**
	 * Intercambia las posiciones de dos teclas en un teclado.
	 *
	 * @param nombreTeclado El nombre del teclado en el que se realizará el cambio.
	 * @param i1            La fila de la primera tecla.
	 * @param j1            La columna de la primera tecla.
	 * @param i2            La fila de la segunda tecla.
	 * @param j2            La columna de la segunda tecla.
	 */
	public void intercambiarTeclasTeclado(String nombreTeclado, int i1, int j1, int i2, int j2)
			throws NombreTecladoNoValidoExcepcion, IndiceTeclaFueraDeRangoExcepcion {
		if (nombreTeclado.trim().isEmpty())
			throw new NombreTecladoNoValidoExcepcion("El nombre del teclado no puede ser vacio.");
		else if (!cjtTeclados.existeTeclado(nombreTeclado))
			throw new NombreTecladoNoValidoExcepcion("El teclado " + nombreTeclado + " no existe.");

		char[][] distribucion = cjtTeclados.getTeclado(nombreTeclado);
		String mensaje = "";

		if (i1 < 0 || i1 >= distribucion.length || j1 < 0 || j1 >= distribucion[0].length || i2 < 0
				|| i2 >= distribucion.length || j2 < 0 || j2 >= distribucion[0].length) {
			if (i1 < 0 || i1 >= distribucion.length || j1 < 0 || j1 >= distribucion[0].length) {
				mensaje += "La posicion de la primera tecla " + i1 + " " + j1 + " no es correcta";
			}
			if (i2 < 0 || i2 >= distribucion.length || j2 < 0 || j2 >= distribucion[0].length) {
				if (mensaje.isEmpty())
					mensaje += "La posicion de la segunda tecla " + i2 + " " + j2 + " no es correcta";
				else {
					mensaje += " y la posicion de la segunda tecla " + i2 + " " + j2 + " no es correcta";
				}
			}

			throw new IndiceTeclaFueraDeRangoExcepcion(mensaje);
		}

		cjtTeclados.intercambiarTeclasTeclado(nombreTeclado, i1, j1, i2, j2);
	}

	/**
	 * Añadir nuevo alfabeto
	 * 
	 * @param idAlfabeto ID del alfabeto que se va a añadir.
	 * @param caracteres String que representa caracteres.
	 */
	public void añadirAlfabeto(String idAlfabeto, String caracteres)
			throws NombreAlfabetoDuplicadoExcepcion, NombreAlfabetoNoValidoExcepcion, NoHayCaracteresExcepcion {
		if (idAlfabeto.trim().isEmpty())
			throw new NombreAlfabetoNoValidoExcepcion("El nombre del alfabeto no puede ser vacio.");

		if (cjtAlfabetos.existeAlfabeto(idAlfabeto))
			throw new NombreAlfabetoDuplicadoExcepcion(idAlfabeto);

		if (caracteres.trim().isEmpty())
			throw new NoHayCaracteresExcepcion();

		cjtAlfabetos.añadirAlfabeto(idAlfabeto, caracteres);
	}

	/**
	 * Eliminar alfabeto
	 * 
	 * @param idAlfabeto ID del alfabeto que se va a eliminar.
	 */
	public void eliminarAlfabeto(String idAlfabeto) throws NombreAlfabetoNoValidoExcepcion {
		if (idAlfabeto.trim().isEmpty())
			throw new NombreAlfabetoNoValidoExcepcion("El nombre del alfabeto no puede ser vacio.");
		else if (!cjtAlfabetos.existeAlfabeto(idAlfabeto))
			throw new NombreAlfabetoNoValidoExcepcion("El alfabeto \"" + idAlfabeto + "\" no existe.");

		cjtAlfabetos.eliminarAlfabeto(idAlfabeto);
	}

	/**
	 * Modificar alfabeto del conjunto de alfabetos.
	 *
	 * @param alfabeto String que representa los caracteres modificados.
	 */
	public void modificarAlfabeto(String idAlfabeto, String alfabeto)
			throws NombreAlfabetoNoValidoExcepcion, NoHayCaracteresExcepcion {
		if (idAlfabeto.trim().isEmpty())
			throw new NombreAlfabetoNoValidoExcepcion("El nombre del alfabeto no puede ser vacio.");
		else if (!cjtAlfabetos.existeAlfabeto(idAlfabeto))
			throw new NombreAlfabetoNoValidoExcepcion("El alfabeto \"" + idAlfabeto + "\" no existe.");
		if (alfabeto.trim().isEmpty())
			throw new NoHayCaracteresExcepcion();
		cjtAlfabetos.modificarAlfabeto(idAlfabeto, alfabeto);
	}

	// ----- Getters -----

	/**
	 * Retorna todos los nombres del conjunto de alfabetos.
	 */
	public String consultarCaracteresAlfabeto(String idAlfabeto) throws NombreAlfabetoNoValidoExcepcion {
		if (idAlfabeto.trim().isEmpty())
			throw new NombreAlfabetoNoValidoExcepcion("Introduce un nombre de alfabeto válido.");
		if (!cjtAlfabetos.existeAlfabeto(idAlfabeto))
			throw new NombreAlfabetoNoValidoExcepcion(idAlfabeto);
		String s = cjtAlfabetos.getAlfabetoCaracteresEnString(idAlfabeto);
		String res = "";
		res += "{";
		for (int i = 0; i < s.length(); ++i) {
			if (s.charAt(i) == '\n') {
				if (i != s.length() - 1)
					res += ", ";
				else
					res += "";
			} else {
				res += s.charAt(i);
			}
		}
		res += "}";
		return res;
	}

	/**
	 * Muestra los nombres de los alfabetos creados hasta el momento
	 * @return String con los nombres de todos los alfabetos
	 */

	public String consultarNombresDeAlfabetos() {
		return cjtAlfabetos.getNombresYCaracteresDeAlfabetos();

	}

	/**
	 * Printea la distribución de un teclado.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de mostrar.
	 * @return La distribución del teclado en forma de String.
	 */
	public String consultarDistribucionDeTeclado(String nombreTeclado) throws NombreTecladoNoValidoExcepcion {
		if (nombreTeclado.trim().isEmpty())
			throw new NombreTecladoNoValidoExcepcion("El nombre del teclado no puede ser vacio.");
		else if (!cjtTeclados.existeTeclado(nombreTeclado))
			throw new NombreTecladoNoValidoExcepcion("El teclado " + nombreTeclado + " no existe.");
		return cjtTeclados.getDistribucioString(nombreTeclado);
	}

	/**
	 * Devuelve el nombre de todos los teclados del conjunto de teclados.
	 */
	public String consultarNombresTeclados() {
		return ArrayToString(cjtTeclados.getNombreTeclados());
	}

	/**
	 * Consulta si un teclado existe.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de consultar.
	 * @return True si el teclado existe, false si no existe.
	 */
	public boolean existeTeclado(String nombreTeclado) {
		return cjtTeclados.existeTeclado(nombreTeclado);
	}

	/**
	 * Comprueba si existe un alfabeto en el conjunto de alfabetos.
	 * 
	 * @param nombreAlfabeto ID del alfabeto que se quiere consultar.
	 * @return True si existe el alfabeto, false en caso contrario.
	 */
	public boolean existeAlfabeto(String nombreAlfabeto) {
		return cjtAlfabetos.existeAlfabeto(nombreAlfabeto);
	}

	// ----- Utilidades -----

	/**
	 * Convierte un ArrayList de Strings en un String.
	 * 
	 * @param array ArrayList de Strings.
	 * @return String de los elementos del ArrayList.
	 */
	private String ArrayToString(ArrayList<String> array) {
		String s = "";
		for (int i = 0; i < array.size(); ++i) {
			s += array.get(i);
			s += "\n";
		}
		return s;
	}

}