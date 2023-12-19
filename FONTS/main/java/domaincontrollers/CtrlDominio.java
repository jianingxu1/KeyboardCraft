package domaincontrollers;

import domain.*;
import exceptions.*;
import persistence.*;

import java.util.*;

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
	private String username;

	// ----- Constructora -----
	
	public CtrlDominio() {
		cjtTeclados = CjtTeclados.getInstance();
		cjtAlfabetos = CjtAlfabetos.getInstance();
		cjtUsuarios = CjtUsuarios.getInstance();
		ctrlPersistencia = new CtrlPersistencia();
		username = "";
	}

	// ----- Métodos públicos -----

	// ----- Modificadoras -----

	/**
	 * Crea un teclado, con un texto y una lista de palabras que nos proporciona el
	 * usuario. El alfabeto ya existe.
	 *
	 * @param nombreTeclado Nombre del teclado.
	 * @param nombreAlfabeto    El ID del alfabeto asociado con el teclado.
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
	public void crearTeclado(String nombreTeclado, String nombreAlfabeto, String texto, String listaPalabras, String algoritmo)
			throws NombreAlfabetoNoValidoExcepcion, NombreTecladoDuplicadoExcepcion, TipoAlgoritmoIncorrectoExcepcion,
			FrecuenciaIncorrectaExcepcion, NombreTecladoNoValidoExcepcion {
		// Informacion necesaria para poder crear el teclado
		if (cjtTeclados.existeTeclado(nombreTeclado))
			throw new NombreTecladoDuplicadoExcepcion("El teclado " + nombreTeclado + " ya existe. Introduce otro nombre.");

		Alfabeto alfabeto = cjtAlfabetos.getAlfabeto(nombreAlfabeto);

		if (algoritmo == null || algoritmo.trim().isEmpty()) 
			throw new TipoAlgoritmoIncorrectoExcepcion("No has seleccionado ningún algoritmo. Selecciona uno.");
		else if (!algoritmo.equals("B&B") && !algoritmo.equals("SA"))
			throw new TipoAlgoritmoIncorrectoExcepcion(
					"El tipo de algoritmo \"" + algoritmo + "\" no es correcto, debe ser B&B o SA.");

		Texto text = new Texto(texto);
		PalabrasConFrecuencia palabras = new PalabrasConFrecuencia(listaPalabras);

		Character[][] distribucion = new Character[3][10];
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

	public ArrayList<String> getNombreAlgoritmos() {
		ArrayList<String> algoritmos = new ArrayList<>();
		algoritmos.add("B&B");
		algoritmos.add("SA");
		return algoritmos;
	}

	/**
	 * Elimina el teclado del conjunto de teclados y su respectiva información.
	 *
	 * @param nombreTeclado El nombre del teclado que se procederá a eliminar.
	 * @throws NombreTecladoNoValidoExcepcion Si el nombre del teclado no es valido.
	 */

	public void eliminarTeclado(String nombreTeclado) throws NombreTecladoNoValidoExcepcion {
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
	 * Anadir nuevo alfabeto
	 * 
	 * @param nombreAlfabeto el nombre del alfabeto que se va a anadir.
	 * @param caracteresAlfabeto String que representa caracteres.
	 * @throws NombreAlfabetoDuplicadoExcepcion Si el nombre del alfabeto ya existe.
	 * @throws NombreAlfabetoNoValidoExcepcion Si el nombre del alfabeto no es valido.
	 * @throws NoHayCaracteresExcepcion Si no hay caracteres en el alfabeto.
	 */
	public void anadirNuevoAlfabeto(String nombreAlfabeto, String caracteresAlfabeto)
			throws NombreAlfabetoDuplicadoExcepcion, NombreAlfabetoNoValidoExcepcion, NoHayCaracteresExcepcion {
		cjtAlfabetos.anadirNuevoAlfabeto(nombreAlfabeto, caracteresAlfabeto);
	}

	/**
	 * Eliminar alfabeto
	 * 
	 * @param nombreAlfabeto nombre del alfabeto que se va a eliminar.
	 * @throws NombreAlfabetoNoValidoExcepcion Si el nombre del alfabeto no es valido.
	 */
	public void eliminarAlfabeto(String nombreAlfabeto) throws NombreAlfabetoNoValidoExcepcion {
		cjtAlfabetos.eliminarAlfabeto(nombreAlfabeto);
	}

	/**
	 * Modificar alfabeto del conjunto de alfabetos.
	 *
	 * @param alfabeto String que representa los caracteres modificados.
	 * @throws NombreAlfabetoNoValidoExcepcion Si el nombre del alfabeto no es valido.
	 * @throws NoHayCaracteresExcepcion Si no hay caracteres en el alfabeto.
	 */
	public void modificarAlfabeto(String nombreAlfabeto, String caracteresAlfabeto)
			throws NombreAlfabetoNoValidoExcepcion, NoHayCaracteresExcepcion {
		cjtAlfabetos.modificarCaracteresAlfabeto(nombreAlfabeto, caracteresAlfabeto);
	}

	/**
	 * añaide un usuario al conjunto de usuarios
	 * 
	 * @param nombreUsuario el nombre del usuario que se quiere añadir
	 * @param contrasena la contraseña del usuario que se quiere añadir
	 * @throws NombreUsuarioNoValidoExcepcion si el nombre del usuario no es valido
	 * @throws ContrasenaNoValidaExcepcion si la contraseña no es valida
	 * @throws EscrituraIncorrectaFicheroExcepcion si no se puede escribir en el fichero
	 */
	public void anadirNuevoUsuario(String nombreUsuario, String contrasena) throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion, EscrituraIncorrectaFicheroExcepcion {
		cjtUsuarios.anadirNuevoUsuario(nombreUsuario, contrasena);
		username = nombreUsuario;
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
	 * @param contrasena
	 * @return
	 * @throws NombreUsuarioNoValidoExcepcion
	 * @throws ContrasenaNoValidaExcepcion
	 */
	public boolean IniciarSesion(String nombreUsuario, String contrasena)
			throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion {

		boolean usuarioIdentificado = cjtUsuarios.correctPass(nombreUsuario, contrasena);
		if (!usuarioIdentificado)
			throw new ContrasenaNoValidaExcepcion("La contraseña no es correcta.");
		else
			username = nombreUsuario;
		return usuarioIdentificado;
	}

	/**
	 * Comprueba si la contraseña de un usuario es correcta
	 * @param nombreUsuario
	 * @param contrasena
	 * @return
	 * @throws NombreUsuarioNoValidoExcepcion
	 * @throws ContrasenaNoValidaExcepcion
	 */
	public boolean contraseñaCorrecta(String nombreUsuario, String contrasena) throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion {
		return cjtUsuarios.correctPass(nombreUsuario, contrasena);
	}

	/**
	 * Cierra la sesión del usuario actual
	 */
	public void CerrarSesion() throws EscrituraIncorrectaFicheroExcepcion, NombreAlfabetoNoValidoExcepcion, NombreTecladoNoValidoExcepcion {
		guardarAlfabetos();
		guardarTeclados();
		cjtTeclados.clearCjtTeclados();
		cjtAlfabetos.clearCjtAlfabetos();
		username = "";
	}

	/**
	 * Guarda los teclados en un fichero
	 * @throws EscrituraIncorrectaFicheroExcepcion
	 */
	public void guardarTeclados() throws EscrituraIncorrectaFicheroExcepcion, NombreTecladoNoValidoExcepcion {
		if (username.trim().isEmpty()) return;
		HashMap<String, Character[][]> conjunto = new HashMap<>();
		
		ArrayList<String> nombreTeclados = cjtTeclados.getNombreTeclados();

		for (String nombreTeclado : nombreTeclados) {
			Character[][] distribucionTeclado = getDistribucionTeclado(nombreTeclado);
			conjunto.put(nombreTeclado, distribucionTeclado);
		}
		ctrlPersistencia.guardarTeclados(username, conjunto);
	}

	/**
	 * Carga los teclados de un fichero
	 * @throws LecturaIncorrectaFicheroExcepcion
	 */
	public void cargarTeclados() throws LecturaIncorrectaFicheroExcepcion, NombreTecladoDuplicadoExcepcion, NombreTecladoNoValidoExcepcion {
		HashMap<String, Character[][]> conjunto = ctrlPersistencia.cargarTeclados(username);
		for (Map.Entry<String, Character[][]> entry : conjunto.entrySet()) {
			String nombreTeclado = entry.getKey();
			Character[][] distribucion = entry.getValue();
			cjtTeclados.crearTeclado(nombreTeclado, distribucion);
		}
	}	

	/**
	 * Guarda los usuarios en un fichero
	 * @throws EscrituraIncorrectaFicheroExcepcion
	 */
	public void guardarUsuarios() throws EscrituraIncorrectaFicheroExcepcion {
		HashMap<String, String> conjuntoUsuarios = new HashMap<>();

		ArrayList<String> nombreUsuarios = cjtUsuarios.getNombreUsuarios();
		for (String nombreUsuario : nombreUsuarios) {
			String contrasena = cjtUsuarios.getContraseñaUsuario(nombreUsuario);
			conjuntoUsuarios.put(nombreUsuario, contrasena);
		}

		ctrlPersistencia.guardarUsuarios(conjuntoUsuarios);
	}

	/**
	 * carga los usuarios de un fichero
	 * @throws LecturaIncorrectaFicheroExcepcion
	 */
	public void cargarUsuarios() throws LecturaIncorrectaFicheroExcepcion, NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion {
		HashMap<String, String> conjuntoUsuarios = ctrlPersistencia.cargarUsuarios();
		for (Map.Entry<String, String> entry : conjuntoUsuarios.entrySet()) {
			String nombreUsuario = entry.getKey();
			String contrasena = entry.getValue();
			cjtUsuarios.anadirNuevoUsuario(nombreUsuario, contrasena);
		}
	}

	/**
	 * Guarda los alfabetos en un fichero
	 * @throws EscrituraIncorrectaFicheroExcepcion
	 */
	public void guardarAlfabetos() throws EscrituraIncorrectaFicheroExcepcion, NombreAlfabetoNoValidoExcepcion {
		if (username.trim().isEmpty()) return;
		ArrayList<String> nombreAlfabetos = cjtAlfabetos.getNombreAlfabetos();
		HashMap<String, ArrayList<Character>> conjuntoAlfabetos = new HashMap<>();
		for (String nombreAlfabeto : nombreAlfabetos) {
			ArrayList<Character> listaCaracteres = cjtAlfabetos.getCaracteresDeAlfabeto(nombreAlfabeto);
			conjuntoAlfabetos.put(nombreAlfabeto, listaCaracteres);
		}
		ctrlPersistencia.guardarAlfabetos(username, conjuntoAlfabetos);
	}

	/**
	 * Carga los alfabetos de un fichero
	 * @throws LecturaIncorrectaFicheroExcepcion
	 */
	public void cargarAlfabetos() throws LecturaIncorrectaFicheroExcepcion, NombreAlfabetoNoValidoExcepcion, NombreAlfabetoDuplicadoExcepcion, NoHayCaracteresExcepcion {
		HashMap<String, String> conjuntoAlfabetos = ctrlPersistencia.cargarAlfabetos(username);
		for (Map.Entry<String, String> entry : conjuntoAlfabetos.entrySet()) {
			String nombreAlfabeto = entry.getKey();
			String caracteres = entry.getValue();
			cjtAlfabetos.anadirNuevoAlfabeto(nombreAlfabeto, caracteres);
		}
	}

	//Funciones de importacion y visualizacion de textos y listas de palabras

	/**
	 * Visualiza los textos que se pueden importar
	 * @return una lista con los nombres de los textos que se pueden importar
	 */

	public ArrayList<String> visualizarTextos() {
		return ctrlPersistencia.visualizarTextos();
	}

	/**
	 * Visualiza las listas de palabras que se pueden importar
	 * @return una lista con los nombres de las listas de palabras que se pueden importar
	 */

	public ArrayList<String> visualizarListasPalabras() {
		return ctrlPersistencia.visualizarListasDePalabras();
	}

	/**
	 * Importa un texto
	 * @param nombreTexto el nombre del texto que se quiere importar
	 * @return el texto importado
	 * @throws LecturaIncorrectaFicheroExcepcion
	 */
	public String importarTexto(String nombreTexto) throws LecturaIncorrectaFicheroExcepcion {
		return ctrlPersistencia.cargarTexto(nombreTexto);
	}

	/**
	 * Importa una lista de palabras
	 * @param nombreListaPalabras el nombre de la lista de palabras que se quiere importar
	 * @return la lista de palabras importada
	 * @throws LecturaIncorrectaFicheroExcepcion
	 */
	public String importarListaPalabras(String nombreListaPalabras) throws LecturaIncorrectaFicheroExcepcion {
		return ctrlPersistencia.cargarListaDePalabras(nombreListaPalabras);
	}
	
	// ----- Getters -----

	public ArrayList<String> getNombreAlfabetos() {
		return cjtAlfabetos.getNombreAlfabetos();
	}

	public ArrayList<Character> getCaracteresDeAlfabeto(String nombreAlfabeto) throws NombreAlfabetoNoValidoExcepcion {
		return cjtAlfabetos.getCaracteresDeAlfabeto(nombreAlfabeto);
	}

	/**
	 * Devuelve el nombre de todos los teclados del conjunto de teclados.
	 * @return ArrayList con los nombres de todos los teclados.
	 */
	public ArrayList<String> getNombreTeclados() {
		return cjtTeclados.getNombreTeclados();
	}

	public Character[][] getDistribucionTeclado(String nombreTeclado) throws NombreTecladoNoValidoExcepcion {
		return cjtTeclados.getDistribucionTeclado(nombreTeclado);
	}

	/**
	 * Devuelve el nombre del usuario actual
	 * @return el nombre del usuario actual
	 */
	public String getNombreUsuario() {
		return username;
	}
}