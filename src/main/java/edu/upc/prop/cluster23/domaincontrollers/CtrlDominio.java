package edu.upc.prop.cluster23.domaincontrollers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.upc.prop.cluster23.domain.*;

/** Clase CtrlDominio
 *  Representa Controlador de Dominio. Se encarga de gestionar teclados y todas sus características.
 *  @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)   
 * 	@co-author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
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
	 * Crea un teclado, con un texto y una lista de palabras que nos proporciona el usuario. El alfabeto ya existe.
	 *
	 * @param nombreTeclado Nombre del teclado.
	 * @param idAlfabeto El ID del alfabeto asociado con el teclado.
	 * @param texto Información adicional para la generación de la distribución del teclado.
	 * @param listaPalabras Texto que contiene las palabras que se desean incluir en la distribución del teclado.
	 * @param algor Un string que representa el algoritmo utilizado para la creación del teclado.
	 */
	public void creaTeclado (String nombreTeclado, String idAlfabeto, String texto, String listaPalabras, String algoritmo) {
		//Informacion necesaria para poder crear el teclado
		PalabrasConFrecuencia palabras = new PalabrasConFrecuencia(listaPalabras);
		Texto text = new Texto(texto); 
		Alfabeto alfabeto = cjtAlfabetos.getAlfabeto(idAlfabeto);

		char[][] distribucion = new char[3][10];

		//El algoritmo genera una distribucion dependiendo del algoritmo elejido
		if (algoritmo.equals("QAP")){
			GeneradorDistribucionTeclado gdt = new GeneradorDistribucionTeclado(new AlgoritmoQAP());
			distribucion = gdt.generarDistribucion(alfabeto, palabras, text);
		}
		else if (algoritmo.equals("SA")) {
			GeneradorDistribucionTeclado gdt = new GeneradorDistribucionTeclado(new AlgoritmoSA());
			distribucion = gdt.generarDistribucion(alfabeto, palabras, text);

		}
		//Se añade el teclado con la distribucion generada
		cjtTeclados.crearTeclado(nombreTeclado, distribucion);
	}


	/**
	 * Elimina el teclado del conjunto de teclados y su respectiva información.
	 *
	 * @param nombreTeclado El nombre del teclado que se procederá a eliminar.
	 */

	public void borrarTeclado(String nombreTeclado) {
		cjtTeclados.eliminarTeclado(nombreTeclado);
	}

	/**
	 * Intercambia las posiciones de dos teclas en un teclado.
	 *
	 * @param nombreTeclado El nombre del teclado en el que se realizará el cambio.
	 * @param i1 La fila de la primera tecla.
	 * @param j1 La columna de la primera tecla.
	 * @param i2 La fila de la segunda tecla.
	 * @param j2 La columna de la segunda tecla.
	 */
	public void intercambiarTeclasTeclado(String nombreTeclado, int i1, int j1, int i2, int j2){
		cjtTeclados.intercambiarTeclasTeclado(nombreTeclado,i1, j1, i2, j2);
	}

	/**
	 * Añadir nuevo alfabeto
	 * @param idAlfabeto ID del alfabeto que se va a añadir.
	 * @param caracteres String que representa caracteres.
	 */
	public void añadirAlfabeto(String idAlfabeto, String caracteres){
		cjtAlfabetos.añadirAlfabeto(idAlfabeto,caracteres);
	}

	/**
	 * Eliminar alfabeto
	 * @param idAlfabeto ID del alfabeto que se va a eliminar.
	 */
	public void eliminarAlfabeto(String idAlfabeto){
	
		cjtAlfabetos.eliminarAlfabeto(idAlfabeto);
	}



	// ----- Getters -----


	/**
	 * Retorna todos los nombres del conjunto de alfabetos.
	 */
	public String consultarAlfabetos(){
		return cjtAlfabetos.getAlfabetosEnString();
	}

	/**
	 * Printea la distribución de un teclado.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de mostrar.
	 * @return La distribución del teclado en forma de String.
	 */
	public String consultarDistribucionDeTeclado(String nombreTeclado) {
		return cjtTeclados.getDistribucioString(nombreTeclado);
	}

	/**
	 * Devuelve el nombre de todos los teclados del conjunto de teclados.
	 */
	public String consultarNombreTeclados() {
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
	private String ArrayToString(ArrayList<String> array){
		String s = "";
		for(int i = 0; i < array.size(); ++i) {
			s += array.get(i);
			s += "\n";
		}
		return s;
	}





	//FUNCIONES ELIMINADAS

	// /**************************************************************************************************
	//  * Modificar alfabeto del conjunto de alfabetos.
	//  *
	//  * @param alfabeto String que representa los caracteres modificados.
	//  */
	// public void modificarAlfabeto(String idAlfabeto,String alfabeto){
	// 	//System.out.println("Se procederá actualizar todos los teclados que compartan ese alfabeto.");
	// 	cjtAlfabetos.modificarAlfabeto(idAlfabeto,alfabeto);
	// }****************************************************************************************************
	
	// /*******************************************************************************************************************
	//  * Devuelve todos los alfabetos de un teclado.
	//  * 
	//  * @param nombreTeclado  El nombre del teclado que se ha de consultar.
	//  * @return Los alfabetos del teclado en forma de String.
	//  */
	// public String consultarAlfabetosDeTeclado(String nombreTeclado) {
	// 	if(!cjtTeclados.existeTeclado(nombreTeclado)){
	// 		return "";
	// 	}
	// 	ArrayList<Character> alfabeto = informacionTeclados.getCaracteresAlfabetoDeTeclado(nombreTeclado).getCaracteres();
	// 	//alfabeto tiene una función toString() que devuelve un String con todos los caracteres
	// 	return alfabeto.toString();
	// }*******************************************************************************************************************

	// /********************************************************************************************************************
	//  * Printea el texto de un teclado.
	//  * 
	//  * @param nombreTeclado El nombre del teclado que se ha de consultar.
	//  * @return El texto del teclado en forma de String.
	//  */
	// public String consultarTextoDeTeclado(String nombreTeclado) {
	// 	Texto t = informacionTeclados.getTextoDeTeclado(nombreTeclado);
	// 	return t.getTexto();
	// }*********************************************************************************************************************

	// /*********************************************************************************************************************
	//  * Printea la lista de palabras de un teclado.
	//  * 
	//  * @param nombreTeclado El nombre del teclado que se ha de consultar.
	//  * @return La lista de palabras del teclado en forma de String.
	//  */
	// public String consultarListaPalabrasDeTeclado(String nombreTeclado) {
	// 	Map<String, Integer> listaPalabras = informacionTeclados.getListaPalabrasDeTeclado(nombreTeclado).getMap();
	// 	return listaPalabras.toString();
	// }*********************************************************************************************************************

	// /*********************************************************************************************************************
	//  * Consulta el algoritmo de un teclado.
	//  * 
	//  * @param nombreTeclado El nombre del teclado que se ha de consultar.
	//  * @return El algoritmo que se ha utilizado para la distribucion del teclado en forma de String.
	//  */
	// public String consultarAlgoritmoDeTeclado(String nombreTeclado) {
	// 	return informacionTeclados.getTipoAlgoritmoDeTeclado(nombreTeclado);
	// }*********************************************************************************************************************


	// /*********************************************************************************************************************************
	//  * Actualiza la distribución de un teclado.
	//  *
	//  * @param nombreTeclado El nombre del teclado cuya distribución se actualizará.
	//  */


	// private void actualizarDistribucion(String nombreTeclado) {
	// 	char[][] distribucion = new char[0][0];
	// 	if(informacionTeclados.getTipoAlgoritmoDeTeclado(nombreTeclado) == "QAP"){
	// 		GeneradorDistribucionTeclado gdt = new GeneradorDistribucionTeclado(new AlgoritmoQAP());
	// 		distribucion = gdt.generarDistribucion(informacionTeclados.getCaracteresAlfabetoDeTeclado(nombreTeclado),informacionTeclados.getListaPalabrasDeTeclado(nombreTeclado), informacionTeclados.getTextoDeTeclado(nombreTeclado));
	// 	}
	// 	else if(informacionTeclados.getTipoAlgoritmoDeTeclado(nombreTeclado) == "SA"){
	// 		GeneradorDistribucionTeclado gdt = new GeneradorDistribucionTeclado(new AlgoritmoSA());
	// 		distribucion = gdt.generarDistribucion(informacionTeclados.getCaracteresAlfabetoDeTeclado(nombreTeclado),informacionTeclados.getListaPalabrasDeTeclado(nombreTeclado), informacionTeclados.getTextoDeTeclado(nombreTeclado));
	// 	}
	// 	cjtTeclados.setDistribucionTeclado(nombreTeclado, distribucion);
	// }************************************************************************************************************************************












}