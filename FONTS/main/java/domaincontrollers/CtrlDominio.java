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
	 * @throws NombreAlfabetoNoValidoExcepcion Si el nombre del alfabeto no es valido.
	 * @throws NombreTecladoDuplicadoExcepcion Si el nombre del teclado ya existe.
	 * @throws TipoAlgoritmoIncorrectoExcepcion Si el tipo de algoritmo no es correcto.
	 * @throws FrecuenciaIncorrectaExcepcion Si la frecuencia de las palabras no es correcta.
	 * @throws NombreTecladoNoValidoExcepcion Si el nombre del teclado no es valido.
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
	 * @throws NombreTecladoNoValidoExcepcion Si el nombre del teclado no es valido.
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
	 * @throws NombreTecladoNoValidoExcepcion Si el nombre del teclado no es valido.
	 * @throws IndiceTeclaFueraDeRangoExcepcion Si el indice de la tecla esta fuera de rango.
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
	 * @throws NombreAlfabetoDuplicadoExcepcion Si el nombre del alfabeto ya existe.
	 * @throws NombreAlfabetoNoValidoExcepcion Si el nombre del alfabeto no es valido.
	 * @throws NoHayCaracteresExcepcion Si no hay caracteres en el alfabeto.
	 */
	public void añadirAlfabeto(String idAlfabeto, String caracteres)
			throws NombreAlfabetoDuplicadoExcepcion, NombreAlfabetoNoValidoExcepcion, NoHayCaracteresExcepcion {

		cjtAlfabetos.añadirAlfabeto(idAlfabeto, caracteres);
	}

	/**
	 * Eliminar alfabeto
	 * 
	 * @param idAlfabeto ID del alfabeto que se va a eliminar.
	 * @throws NombreAlfabetoNoValidoExcepcion Si el nombre del alfabeto no es valido.
	 */
	public void eliminarAlfabeto(String idAlfabeto) throws NombreAlfabetoNoValidoExcepcion {

		cjtAlfabetos.eliminarAlfabeto(idAlfabeto);
	}

	/**
	 * Modificar alfabeto del conjunto de alfabetos.
	 *
	 * @param alfabeto String que representa los caracteres modificados.
	 * @throws NombreAlfabetoNoValidoExcepcion Si el nombre del alfabeto no es valido.
	 * @throws NoHayCaracteresExcepcion Si no hay caracteres en el alfabeto.
	 */
	public void modificarAlfabeto(String idAlfabeto, String alfabeto)
			throws NombreAlfabetoNoValidoExcepcion, NoHayCaracteresExcepcion {

		cjtAlfabetos.modificarAlfabeto(idAlfabeto, alfabeto);
	}

	/**
	 * añaide un usuario al conjunto de usuarios
	 * 
	 * @param nombreUsuario el nombre del usuario que se quiere añadir
	 * @param contraseña la contraseña del usuario que se quiere añadir
	 * @throws NombreUsuarioNoValidoExcepcion si el nombre del usuario no es valido
	 * @throws ContrasenaNoValidaExcepcion si la contraseña no es valida
	 * @throws EscrituraIncorrectaFicheroExcepcion si no se puede escribir en el fichero
	 */
	public void añadirUsuario(String nombreUsuario, String contraseña) throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion, EscrituraIncorrectaFicheroExcepcion {
		cjtUsuarios.añadirUsuario(nombreUsuario, contraseña);
		userName = nombreUsuario;
	}

	/**
	 * Comprueba si existe un usuario en el conjunto de usuarios
	 * 
	 * @param nombreUsuario el nombre del usuario que se quiere comprobar
	 * @return true si el usuario existe, false en caso contrario
	 */
	public boolean existeUsuario(String nombreUsuario) {
		if (cjtUsuarios.existeUsuario(nombreUsuario))
			return true;
		return false;
	}

	/**
	 * Elimina un usuario del conjunto
	 * 
	 * @param nombreUsuario el nombre del usuario a eliminar
	 * @throws NombreUsuarioNoValidoExcepcion si el nombre del usuario no es valido
	 */
	public void eliminarUsuario(String nombreUsuario) throws NombreUsuarioNoValidoExcepcion{
		cjtUsuarios.eliminarUsuario(nombreUsuario);
		ctrlPersistencia.eliminarUsuario(nombreUsuario);
	}

	/**
	 * Modifica la contraseña de un usuario
	 * 
	 * @param nombreUsuario el nombre del usuario a modificar
	 * @param actualContrasena la contraseña actual del usuario
	 * @param nuevaContrasena la nueva contraseña del usuario
	 * @throws NombreUsuarioNoValidoExcepcion si el nombre del usuario no es valido
	 * @throws ContrasenaNoValidaExcepcion si la contraseña no es valida
	 */
	public void modificarContrasena(String nombreUsuario,String actualContrasena, String nuevaContrasena) throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion {
		cjtUsuarios.modificarContrasenaUsuario(nombreUsuario, actualContrasena, nuevaContrasena);
	}

	/**
	 * Inicia sesión con un usuario
	 * @param nombreUsuario
	 * @param contraseña
	 * @return
	 * @throws NombreUsuarioNoValidoExcepcion
	 * @throws ContrasenaNoValidaExcepcion
	 */
	public boolean IniciarSesion(String nombreUsuario, String contraseña)
			throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion {

		boolean usuarioIdentificado = cjtUsuarios.correctPass(nombreUsuario, contraseña);
		if (!usuarioIdentificado)
			throw new ContrasenaNoValidaExcepcion("La contraseña no es correcta.");
		else
			userName = nombreUsuario;
		return usuarioIdentificado;
	}

	/**
	 * Comprueba si la contraseña de un usuario es correcta
	 * @param nombreUsuario
	 * @param contraseña
	 * @return
	 * @throws NombreUsuarioNoValidoExcepcion
	 * @throws ContrasenaNoValidaExcepcion
	 */
	public boolean contraseñaCorrecta(String nombreUsuario, String contraseña) throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion {
		return cjtUsuarios.correctPass(nombreUsuario, contraseña);
	}

	/**
	 * Cierra la sesión del usuario actual
	 */
	public void CerrarSesion() {
		cjtTeclados.clearCjtTeclados();
		cjtAlfabetos.clearCjtAlfabetos();
		userName = "";
	}

	/**
	 * Guarda los teclados en un fichero
	 * @throws EscrituraIncorrectaFicheroExcepcion
	 */
	public void guardarTeclados() throws EscrituraIncorrectaFicheroExcepcion{
		if (userName.trim().isEmpty()) return;
		ctrlPersistencia.guardarTeclados(cjtTeclados, userName);
	}

	/**
	 * Carga los teclados de un fichero
	 * @throws LecturaIncorrectaFicheroExcepcion
	 */
	public void cargarTeclados() throws LecturaIncorrectaFicheroExcepcion{
		cjtTeclados = ctrlPersistencia.cargarTeclados(userName);
	}	

	/**
	 * Guarda los usuarios en un fichero
	 * @throws EscrituraIncorrectaFicheroExcepcion
	 */
	public void guardarUsuarios() throws EscrituraIncorrectaFicheroExcepcion{
		ctrlPersistencia.guardarUsuarios(cjtUsuarios);
	}

	/**
	 * carga los usuarios de un fichero
	 * @throws LecturaIncorrectaFicheroExcepcion
	 */
	public void cargarUsuarios() throws LecturaIncorrectaFicheroExcepcion{
		cjtUsuarios = ctrlPersistencia.cargarUsuarios();
	}

	/**
	 * Guarda los alfabetos en un fichero
	 * @throws EscrituraIncorrectaFicheroExcepcion
	 */
	public void guardarAlfabetos() throws EscrituraIncorrectaFicheroExcepcion{
		if (userName.trim().isEmpty()) return;
		ctrlPersistencia.guardarAlfabetos(cjtAlfabetos, userName);
	}

	/**
	 * Carga los alfabetos de un fichero
	 * @throws LecturaIncorrectaFicheroExcepcion
	 */
	public void cargarAlfabetos() throws LecturaIncorrectaFicheroExcepcion{
		cjtAlfabetos = ctrlPersistencia.cargarAlfabetos(userName);
	}

	// ----- Getters -----

	/**
	 * Retorna todos los nombres del conjunto de alfabetos.
	 * @param idAlfabeto ID del alfabeto que se quiere consultar.
	 * @return String con los nombres de todos los alfabetos.
	 * @throws NombreAlfabetoNoValidoExcepcion Si el nombre del alfabeto no es valido.
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
	 * @throws NombreTecladoNoValidoExcepcion Si el nombre del teclado no es valido.
	 */
	public String consultarDistribucionDeTeclado(String nombreTeclado) throws NombreTecladoNoValidoExcepcion {
		return cjtTeclados.getDistribucioString(nombreTeclado);
	}

	/**
	 * Devuelve el nombre de todos los teclados del conjunto de teclados.
	 * @return String con los nombres de todos los teclados.
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

	/**
	 * Devuelve el nombre del usuario actual
	 * @return el nombre del usuario actual
	 */
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