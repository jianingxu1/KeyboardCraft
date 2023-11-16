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
	private InformacionTeclados informacionTeclados;	

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
		 informacionTeclados = InformacionTeclados.getInstance();
	}

	/**
	 * Crea un teclado 
	 *
	 * @param nombreTeclado Nombre del teclado.
	 * @param idAlfabeto El ID del alfabeto asociado con el teclado.
	 * @param texto Información adicional para la generación de la distribución del teclado.
	 * @param listaPalabras Texto que contiene las palabras que se desean incluir en la distribución del teclado.
	 * @param algor Un string que representa el algoritmo utilizado para la creación del teclado.
	 */
	public void creaTeclado(String nombreTeclado, String idAlfabeto, String texto, String listaPalabras, String algor) {

		PalabrasConFrecuencia pc = new PalabrasConFrecuencia(listaPalabras);
		Texto t = new Texto(texto); 
		informacionTeclados.añadirTeclado(nombreTeclado, idAlfabeto, t, pc, algor);
		
		char[][] distribucion = new char[3][10];
		if (algor.equals("QAP")){
			GeneradorDistribucionTeclado gdt = new GeneradorDistribucionTeclado(new AlgoritmoQAP());
			distribucion = gdt.generarDistribucion(informacionTeclados.getCaracteresAlfabetoDeTeclado(nombreTeclado),informacionTeclados.getListaPalabrasDeTeclado(nombreTeclado), informacionTeclados.getTextoDeTeclado(nombreTeclado));
		}
		else if (algor.equals("SA")) {
			GeneradorDistribucionTeclado gdt = new GeneradorDistribucionTeclado(new AlgoritmoSA());
			distribucion = gdt.generarDistribucion(informacionTeclados.getCaracteresAlfabetoDeTeclado(nombreTeclado),informacionTeclados.getListaPalabrasDeTeclado(nombreTeclado), informacionTeclados.getTextoDeTeclado(nombreTeclado));
		}
		cjtTeclados.crearTeclado(nombreTeclado, distribucion);
	}

	// ----- Modificadoras -----

	/**
	 * Elimina el teclado del conjunto de teclados y su respectiva información.
	 *
	 * @param nombreTeclado El nombre del teclado que se procederá a eliminar.
	 */

	public void borrarTeclado(String nombreTeclado) {
		cjtTeclados.eliminarTeclado(nombreTeclado);
		informacionTeclados.borrarTeclado(nombreTeclado);
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
	 * Cambiar el algoritmo de un teclado.
	 *
	 * @param nombreTeclado El nombre del teclado en el que se realizará el cambio.
	 * @param algoritmo Nuevo algoritmo que se desea utilizar.
	 */
	public void cambiarAlgoritmoDeTeclado(String nombreTeclado, String algoritmo){
		informacionTeclados.modificarAlgoritmoDeTeclado(nombreTeclado,algoritmo);	
		actualizarDistribucion(nombreTeclado);
	}

	/**
	 * Modificar el texto de un teclado.
	 *
	 * @param nombreTeclado El nombre del teclado en el que se realizará el cambio.
	 * @param texto Nuevo texto modificado.
	 */	
	public void modificarTextoDeTeclado(String nombreTeclado, String texto){
		informacionTeclados.modificarTextoDeTeclado(nombreTeclado, texto);
		actualizarDistribucion(nombreTeclado);
	}

	/**
	 * Modificar la lista de palabras de un teclado.
	 *
	 * @param nombreTeclado El nombre del teclado en el que se realizará el cambio.
	 * @param listaPalabras Nueva lista de palabras.
	 */
	public void modificarListaPalabrasDeTeclado(String nombreTeclado, String listaPalabras){
		informacionTeclados.modificarListaPalabrasDeTeclado(nombreTeclado, listaPalabras);
		actualizarDistribucion(nombreTeclado);
	}

	/**
	 * Añadir nuevo alfabeto
	 * @param idAlfabeto ID del alfabeto que se va a añadir.
	 * @param caracteres String que representa caracteres.
	 */
	public void añadirAlfabeto(String idAlfabeto, String caracteres){
		informacionTeclados.añadirAlfabeto(idAlfabeto,caracteres);
	}

	/**
	 * Modificar alfabeto del conjunto de alfabetos.
	 *
	 * @param alfabeto String que representa los caracteres modificados.
	 */
	public void modificarAlfabeto(String idAlfabeto,String alfabeto){
		//System.out.println("Se procederá actualizar todos los teclados que compartan ese alfabeto.");
		informacionTeclados.modificarAlfabeto(idAlfabeto,alfabeto);
	}

	/**
	 * Eliminar alfabeto
	 * @param idAlfabeto ID del alfabeto que se va a eliminar.
	 */
	public void eliminarAlfabeto(String idAlfabeto){
		/***Caso NO implementado, eliminar un alfabet que es utilizado por uno o varios teclados***/

		//System.out.println("Se procederá a eliminar todos los teclados que utilizan ese alfabeto.");
		informacionTeclados.eliminarAlfabeto(idAlfabeto);
	}

	/**
	 * Retorna todos los alfabetos del conjunto de alfabetos.
	 */
	public String consultarAlfabetos(){
		return informacionTeclados.consultarAlfabetos();
	}
	/**
	 * Assigna nuevo alfabeto a un teclado existente.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de modificar.
	 * @param idAlfabeto ID del nuevo alfabeto a assignar el teclado.
	 */
	public void assignaNuevoAlfabetoDeTeclado(String nombreTeclado,String idAlfabeto) {
		informacionTeclados.assignaNuevoAlfabetoDeTeclado(nombreTeclado,idAlfabeto);
	}

	// ----- Getters -----

	/**
	 * Printea la distribución de un teclado.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de mostrar.
	 * @return La distribución del teclado en forma de String.
	 */
	public String consultarDistribucionDeTeclado(String nombreTeclado) {
		return cjtTeclados.getDist(nombreTeclado);
	}

	/**
	 * Devuelve el nombre de todos los teclados del conjunto de teclados.
	 */
	public String consultarNombreTeclados() {
		return ArrayToString(cjtTeclados.getNombreTeclados());
	}

	/**
	 * Devuelve todos los alfabetos de un teclado.
	 * 
	 * @param nombreTeclado  El nombre del teclado que se ha de consultar.
	 * @return Los alfabetos del teclado en forma de String.
	 */
	public String consultarAlfabetosDeTeclado(String nombreTeclado) {
		if(!cjtTeclados.existeTeclado(nombreTeclado)){
			return "";
		}
		ArrayList<Character> alfabeto = informacionTeclados.getCaracteresAlfabetoDeTeclado(nombreTeclado).getCaracteres();
		//alfabeto tiene una función toString() que devuelve un String con todos los caracteres
		return alfabeto.toString();
	}

	/**
	 * Printea el texto de un teclado.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de consultar.
	 * @return El texto del teclado en forma de String.
	 */
	public String consultarTextoDeTeclado(String nombreTeclado) {
		Texto t = informacionTeclados.getTextoDeTeclado(nombreTeclado);
		return t.getTexto();
	}

	/**
	 * Printea la lista de palabras de un teclado.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de consultar.
	 * @return La lista de palabras del teclado en forma de String.
	 */
	public String consultarListaPalabrasDeTeclado(String nombreTeclado) {
		Map<String, Integer> listaPalabras = informacionTeclados.getListaPalabrasDeTeclado(nombreTeclado).getMap();
		return listaPalabras.toString();
	}

	/**
	 * Consulta el algoritmo de un teclado.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de consultar.
	 * @return El algoritmo que se ha utilizado para la distribucion del teclado en forma de String.
	 */
	public String consultarAlgoritmoDeTeclado(String nombreTeclado) {
		return informacionTeclados.getTipoAlgoritmoDeTeclado(nombreTeclado);
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
		return informacionTeclados.existeAlfabeto(nombreAlfabeto);
	}

	// ----- Utilidades -----

	/**
	 * Actualiza la distribución de un teclado.
	 *
	 * @param nombreTeclado El nombre del teclado cuya distribución se actualizará.
	 */


	private void actualizarDistribucion(String nombreTeclado) {
		char[][] distribucion = new char[0][0];
		if(informacionTeclados.getTipoAlgoritmoDeTeclado(nombreTeclado) == "QAP"){
			GeneradorDistribucionTeclado gdt = new GeneradorDistribucionTeclado(new AlgoritmoQAP());
			distribucion = gdt.generarDistribucion(informacionTeclados.getCaracteresAlfabetoDeTeclado(nombreTeclado),informacionTeclados.getListaPalabrasDeTeclado(nombreTeclado), informacionTeclados.getTextoDeTeclado(nombreTeclado));
		}
		else if(informacionTeclados.getTipoAlgoritmoDeTeclado(nombreTeclado) == "SA"){
			GeneradorDistribucionTeclado gdt = new GeneradorDistribucionTeclado(new AlgoritmoSA());
			distribucion = gdt.generarDistribucion(informacionTeclados.getCaracteresAlfabetoDeTeclado(nombreTeclado),informacionTeclados.getListaPalabrasDeTeclado(nombreTeclado), informacionTeclados.getTextoDeTeclado(nombreTeclado));
		}
		cjtTeclados.setDistribucionTeclado(nombreTeclado, distribucion);
	}

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

}