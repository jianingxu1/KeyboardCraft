package domaincontrollers;

import domain.*;
import exceptions.*;
import persistence.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * Clase CtrlDominio
 * Representa Controlador de Dominio. Se encarga de gestionar teclados y todas
 * sus características.
 * 
 * @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */

public class CtrlDominio {

	/** Atributos */
	private static CjtTeclados cjtTeclados;
	private CjtAlfabetos cjtAlfabetos;
	private CjtUsuarios cjtUsuarios;
	private CtrlPersistencia ctrlPersistencia;
	private String userName;

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
		cjtUsuarios = CjtUsuarios.getInstance();
		ctrlPersistencia = new CtrlPersistencia();
		userName = "";
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
		PalabrasConFrecuencia palabras = new PalabrasConFrecuencia(listaPalabras);
		Texto text = new Texto(texto);
		Alfabeto alfabeto = cjtAlfabetos.getAlfabeto(idAlfabeto);
		char[][] distribucion = new char[3][10];

		if (algoritmo.trim().isEmpty())
			throw new TipoAlgoritmoIncorrectoExcepcion("El algoritmo no puede ser vacio.");

		else if (!algoritmo.equals("B&B") && !algoritmo.equals("SA"))
			throw new TipoAlgoritmoIncorrectoExcepcion(
					"El tipo de algoritmo \"" + algoritmo + "\" no es correcto, debe ser B&B o SA.");

		// El algoritmo genera una distribucion dependiendo del algoritmo elejido
		if (algoritmo.equals("B&B")) {
			GeneradorDistribucionTeclado gdt = new GeneradorDistribucionTeclado(new AlgoritmoBranchAndBound());
			Map<String, Integer> bigramasConFrecuencia = new CalculadoraBigramasConFrecuencia().ejecutar(alfabeto, palabras,
					text);
			distribucion = gdt.generarDistribucion(alfabeto, bigramasConFrecuencia);
		} else if (algoritmo.equals("SA")) {
			GeneradorDistribucionTeclado gdt = new GeneradorDistribucionTeclado(new AlgoritmoSimulatedAnnealing());
			Map<String, Integer> bigramasConFrecuencia = new CalculadoraBigramasConFrecuencia().ejecutar(alfabeto, palabras,
					text);
			distribucion = gdt.generarDistribucion(alfabeto, bigramasConFrecuencia);
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

		cjtAlfabetos.añadirAlfabeto(idAlfabeto, caracteres);
	}

	/**
	 * Eliminar alfabeto
	 * 
	 * @param idAlfabeto ID del alfabeto que se va a eliminar.
	 */
	public void eliminarAlfabeto(String idAlfabeto) throws NombreAlfabetoNoValidoExcepcion {

		cjtAlfabetos.eliminarAlfabeto(idAlfabeto);
	}

	/**
	 * Modificar alfabeto del conjunto de alfabetos.
	 *
	 * @param alfabeto String que representa los caracteres modificados.
	 */
	public void modificarAlfabeto(String idAlfabeto, String alfabeto)
			throws NombreAlfabetoNoValidoExcepcion, NoHayCaracteresExcepcion {

		cjtAlfabetos.modificarAlfabeto(idAlfabeto, alfabeto);
	}


	public void añadirUsuario(String nombreUsuario, String contraseña) throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion, EscrituraIncorrectaFicheroExcepcion {
		cjtUsuarios.añadirUsuario(nombreUsuario, contraseña);
		userName = nombreUsuario;
	}

	public boolean existeUsuario(String nombreUsuario) {
		if (cjtUsuarios.existeUsuario(nombreUsuario))
			return true;
		return false;
	}

	//Implementar este caso en el main, seria como borrar cuenta
	public void eliminarUsuario(String nombreUsuario) throws NombreUsuarioNoValidoExcepcion{
		cjtUsuarios.eliminarUsuario(nombreUsuario);
		ctrlPersistencia.eliminarUsuario(nombreUsuario);
	}

	public void modificarContrasena(String nombreUsuario,String actualContrasena, String nuevaContrasena) throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion {
		cjtUsuarios.modificarContrasenaUsuario(nombreUsuario, actualContrasena, nuevaContrasena);
	}

	public boolean IniciarSesion(String nombreUsuario, String contraseña)
			throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion {
		boolean usuarioIdentificado = cjtUsuarios.correctPass(nombreUsuario, contraseña);
		if (!usuarioIdentificado)
			throw new ContrasenaNoValidaExcepcion("La contraseña no es correcta.");
		else
			userName = nombreUsuario;
		return usuarioIdentificado;
	}

	public void CerrarSesion() {
		cjtTeclados.clearCjtTeclados();
		cjtAlfabetos.clearCjtAlfabetos();
		userName = "";
	}


	public void guardarTeclados() throws EscrituraIncorrectaFicheroExcepcion{
		if (userName.trim().isEmpty()) return;
		ctrlPersistencia.guardarTeclados(cjtTeclados, userName);
	}

	public void cargarTeclados() throws LecturaIncorrectaFicheroExcepcion{
		cjtTeclados = ctrlPersistencia.cargarTeclados(userName);
	}	

	public void guardarUsuarios() throws EscrituraIncorrectaFicheroExcepcion{
		ctrlPersistencia.guardarUsuarios(cjtUsuarios);
	}

	public void cargarAlfabetos() throws LecturaIncorrectaFicheroExcepcion{
		cjtAlfabetos = ctrlPersistencia.cargarAlfabetos(userName);
	}

	public void guardarAlfabetos() throws EscrituraIncorrectaFicheroExcepcion{
		if (userName.trim().isEmpty()) return;
		ctrlPersistencia.guardarAlfabetos(cjtAlfabetos, userName);
	}

	// ----- Getters -----

	/**
	 * Retorna todos los nombres del conjunto de alfabetos.
	 */
	public String consultarCaracteresAlfabeto(String idAlfabeto) throws NombreAlfabetoNoValidoExcepcion {
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
	 * 
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
		return cjtTeclados.getDistribucioString(nombreTeclado);
	}

	/**
	 * Devuelve el nombre de todos los teclados del conjunto de teclados.
	 */
	public String consultarNombresTeclados() {
		return ArrayToString(cjtTeclados.getNombreTeclados());
	}

	public String getNombreUsuario() {
		return userName;
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