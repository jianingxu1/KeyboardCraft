package domaincontrollers;

import domain.*;
import exceptions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
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

		if (nombreTeclado.trim().isEmpty())
			throw new NombreTecladoNoValidoExcepcion("El nombre del teclado no puede ser vacio.");
		else if (cjtTeclados.existeTeclado(nombreTeclado))
			throw new NombreTecladoDuplicadoExcepcion("El teclado " + nombreTeclado + " ya existe.");

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
		else if (!algoritmo.equals("B&B") && !algoritmo.equals("SA"))
			throw new TipoAlgoritmoIncorrectoExcepcion(
					"El tipo de algoritmo \"" + algoritmo + "\" no es correcto, debe ser B&B o SA.");

		Texto text = new Texto(texto);
		Alfabeto alfabeto = cjtAlfabetos.getAlfabeto(idAlfabeto);

		char[][] distribucion = new char[3][10];

		// El algoritmo genera una distribucion dependiendo del algoritmo elejido
		if (algoritmo.equals("B&B")) {
			GeneradorDistribucionTeclado gdt = new GeneradorDistribucionTeclado(new AlgoritmoBranchAndBound());
			Map<String, Integer> bigramasConFrecuencia = new CalculadoraBigramasConFrecuencia().ejecutar(alfabeto, palabras, text);
			distribucion = gdt.generarDistribucion(alfabeto, bigramasConFrecuencia);
		} else if (algoritmo.equals("SA")) {
			GeneradorDistribucionTeclado gdt = new GeneradorDistribucionTeclado(new AlgoritmoSimulatedAnnealing());
			Map<String, Integer> bigramasConFrecuencia = new CalculadoraBigramasConFrecuencia().ejecutar(alfabeto, palabras, text);
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

	public void guardarTeclados() throws EscrituraIncorrectaFicheroExcepcion{
		if (userName.trim().isEmpty()) return;
		try {
			String path = "../DATA/" + userName + "Teclados" + ".prop";
      BufferedWriter writer = new BufferedWriter(new FileWriter(path,false));
			for (String nombreTeclado : cjtTeclados.getNombreTeclados()) {
				writer.write(nombreTeclado + 'º' +cjtTeclados.getDistribucioStringSimplificado(nombreTeclado)+"\n");
			}
			writer.close();
    } catch (Exception e) {
      throw new EscrituraIncorrectaFicheroExcepcion("Error al escribir en el fichero de teclados "+ e.getMessage());
    }
	}

	public void cargarTeclados() throws LecturaIncorrectaFicheroExcepcion{
		if (userName.trim().isEmpty()) return;
		String path = "../DATA/" + userName + "Teclados" + ".prop";
		try {
      BufferedReader reader = new BufferedReader(new FileReader(path));
      String linea;
      while ((linea = reader.readLine()) != null) {
          // System.out.println(linea);
					String elementos [] = linea.split("º");
					String nombreTeclado = elementos[0];
					char[][] distribucion = convertirStringADistribucion(elementos[1]);
					cjtTeclados.crearTeclado(nombreTeclado, distribucion);
      }
      reader.close();
		}
		catch (Exception e) {
			File file = new File(path);
			try {
				file.createNewFile();
			} catch (Exception ex) {
				throw new LecturaIncorrectaFicheroExcepcion("Error al crear el fichero de teclados "+ ex.getMessage());
			}
			//throw new LecturaIncorrectaFicheroExcepcion("Error al leer el fichero de teclados "+ e.getMessage()+"\nSe ha creado un fichero nuevo.");
		}
  }

	private static char[][] convertirStringADistribucion(String distribucionString){
		char[][] distribucion = new char[3][10];
		int i = 0;
		int j = 0;
		for (int k = 0; k < distribucionString.length(); ++k) {
			if (distribucionString.charAt(k) == '◘') {
				i++;
				j = 0;
			} else {
				distribucion[i][j] = distribucionString.charAt(k);
				j++;
			}
		}
		return distribucion;
	}

	public void añadirUsuario(String nombreUsuario, String contraseña) throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion, EscrituraIncorrectaFicheroExcepcion{
		if (nombreUsuario.trim().isEmpty())
			throw new NombreUsuarioNoValidoExcepcion("El nombre del usuario no puede ser vacío.");
		else if (cjtUsuarios.existeUsuario(nombreUsuario))
			throw new NombreUsuarioNoValidoExcepcion("El usuario " + nombreUsuario + " ya existe.");
		else if (contraseña.trim().isEmpty())
			throw new ContrasenaNoValidaExcepcion("La contraseña no puede ser vacia.");
		else if (contraseña.length() < 8)
			throw new ContrasenaNoValidaExcepcion("La contraseña debe tener al menos 8 caracteres.");
		cjtUsuarios.añadirUsuario(nombreUsuario, contraseña);
		userName = nombreUsuario;
	}

	//Implementar este caso en el main, seria como borrar cuenta
	public void eliminarUsuario(String nombreUsuario) throws NombreUsuarioNoValidoExcepcion{
		if (nombreUsuario.trim().isEmpty())
			throw new NombreUsuarioNoValidoExcepcion("El nombre del usuario no puede ser vacío.");
		else if (!cjtUsuarios.existeUsuario(nombreUsuario))
			throw new NombreUsuarioNoValidoExcepcion("El usuario " + nombreUsuario + " no existe.");
		cjtUsuarios.eliminarUsuario(nombreUsuario);
		File file = new File("../DATA/" + nombreUsuario + "Teclados" + ".prop");
		file.delete();
		File file2 = new File("../DATA/" + nombreUsuario + "Alfabetos" + ".prop");
		file2.delete();
	}

	public void modificarContraseña(String nombreUsuario, String nuevaContraseña) throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion{
		if (nombreUsuario.trim().isEmpty())
			throw new NombreUsuarioNoValidoExcepcion("El nombre del usuario no puede ser vacío.");
		else if (!cjtUsuarios.existeUsuario(nombreUsuario))
			throw new NombreUsuarioNoValidoExcepcion("El usuario " + nombreUsuario + " no existe.");
		else if (nuevaContraseña.trim().isEmpty())
			throw new ContrasenaNoValidaExcepcion("La contraseña no puede ser vacia.");
		else if (nuevaContraseña.length() < 8)
			throw new ContrasenaNoValidaExcepcion("La contraseña debe tener al menos 8 caracteres.");
		
			cjtUsuarios.modificarUsuario(nombreUsuario, nuevaContraseña);
	}

	public boolean IniciarSesion(String nombreUsuario, String contraseña) throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion{
		if (nombreUsuario.trim().isEmpty())
			throw new NombreUsuarioNoValidoExcepcion("El nombre del usuario no puede ser vacío.");
		else if (!cjtUsuarios.existeUsuario(nombreUsuario))
			throw new NombreUsuarioNoValidoExcepcion("El usuario " + nombreUsuario + " no existe.");
		else if (contraseña.trim().isEmpty())
			throw new ContrasenaNoValidaExcepcion("La contraseña no puede ser vacia.");
		else if (contraseña.length() < 8)
			throw new ContrasenaNoValidaExcepcion("La contraseña debe tener al menos 8 caracteres.");
		boolean usuarioIdentificado = cjtUsuarios.correctPass(nombreUsuario, contraseña);
		if (!usuarioIdentificado) throw new ContrasenaNoValidaExcepcion("La contraseña no es correcta.");
		else userName = nombreUsuario;
		return usuarioIdentificado;
	}

	public void CerrarSesion(){
		cjtTeclados.clearCjtTeclados();
		cjtAlfabetos.clearCjtAlfabetos();
		userName = "";
	}

	public void cargarUsuarios() throws LecturaIncorrectaFicheroExcepcion{
		String path = "../DATA/usuarios.prop";
		try {
      BufferedReader reader = new BufferedReader(new FileReader(path));
      String linea;
      while ((linea = reader.readLine()) != null) {
          // System.out.println(linea);
					String elementos [] = linea.split("º");
					String nombreUsuario = elementos[0];
					String contraseña = elementos[1];
					cjtUsuarios.añadirUsuario(nombreUsuario, contraseña);
      }
      reader.close();
		}
		catch (Exception e) {
			File file = new File(path);
			try {
				file.createNewFile();
			} catch (Exception ex) {
				throw new LecturaIncorrectaFicheroExcepcion("Error al crear el fichero de usuarios "+ ex.getMessage());
			}
			throw new LecturaIncorrectaFicheroExcepcion("Error al leer el fichero de usuarios "+ e.getMessage()+"\nSe ha creado un fichero nuevo.");
			
		}
	}

	public void guardarUsuarios() throws EscrituraIncorrectaFicheroExcepcion{
		try {
			String path = "../DATA/usuarios.prop";
			BufferedWriter writer = new BufferedWriter(new FileWriter(path,false));			

			for (String nombreUsuario : cjtUsuarios.getNombreUsuarios()) {
				writer.write(nombreUsuario + 'º' +cjtUsuarios.getContraseñaUsuario(nombreUsuario)+"\n");
			}
			writer.close();
		} catch (Exception e) {
			throw new EscrituraIncorrectaFicheroExcepcion("Error al escribir en el fichero de usuarios "+ e.getMessage());
		}
	}

	public void guardarAlfabetos() throws EscrituraIncorrectaFicheroExcepcion{
		if (userName.trim().isEmpty()) return;
		try {
			String path = "../DATA/"+userName+"Alfabetos"+".prop";
			BufferedWriter writer = new BufferedWriter(new FileWriter(path,false));
			for (String idAlfabeto: cjtAlfabetos.getNombreAlfabetos()){
				writer.write(idAlfabeto + 'º' +cjtAlfabetos.getAlfabetoCaracteresEnString(idAlfabeto).replaceAll("\\s", "◘") + "\n");
			}
			writer.close();
		} catch (Exception e) {
			throw new EscrituraIncorrectaFicheroExcepcion("Error al escribir en el fichero de alfabetos "+ e.getMessage());
		}
	}

	public void cargarAlfabetos() throws LecturaIncorrectaFicheroExcepcion{
		//System.out.println(userName+"Alfabetos");
		String path = "../DATA/" + userName + "Alfabetos" + ".prop";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String linea;
			while ((linea = reader.readLine()) != null) {
					// System.out.println(linea);
					String elementos [] = linea.split("º");
					String idAlfabeto = elementos[0];
					String caracteres = elementos[1];
					caracteres = caracteres.replace("◘", "\n");
					cjtAlfabetos.añadirAlfabeto(idAlfabeto, caracteres);
			}
			reader.close();
		}
		catch (Exception e) {
			File file = new File(path);
			try {
				file.createNewFile();
			} catch (Exception ex) {
				throw new LecturaIncorrectaFicheroExcepcion("Error al crear el fichero de alfabetos "+ ex.getMessage());
			}
			//throw new LecturaIncorrectaFicheroExcepcion("Error al leer el fichero de alfabetos "+ e.getMessage()+"\nSe ha creado un fichero nuevo.");
		}
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