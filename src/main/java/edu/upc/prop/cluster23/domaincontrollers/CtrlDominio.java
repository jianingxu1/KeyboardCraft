package edu.upc.prop.cluster23.domaincontrollers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import edu.upc.prop.cluster23.domain.*;
import org.w3c.dom.Text;

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

		if(informacionTeclados.existeTeclado(nombreTeclado)) {
			System.out.println("El teclado ya existe");
			return;
		}

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
		if(!cjtTeclados.existeTeclado(nombreTeclado)){
			System.out.println("El teclado no existe");
			return;
		}
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
		if(cjtTeclados.existeTeclado(nombreTeclado)){
			cjtTeclados.intercambiarTeclasTeclado(nombreTeclado,i1, j1, i2, j2);
		}
		else{
			System.out.println("El teclado no existe");
		}
	}

	/**
	 * Cambiar el algoritmo de un teclado.
	 *
	 * @param nombreTeclado El nombre del teclado en el que se realizará el cambio.
	 * @param algoritmo Nuevo algoritmo que se desea utilizar.
	 */
	public void cambiarAlgoritmoDeTeclado(String nombreTeclado, String algoritmo){
		if(cjtTeclados.existeTeclado(nombreTeclado)){
			informacionTeclados.modificarAlgoritmoDeTeclado(nombreTeclado,algoritmo);	
			actualizarDistribucion(nombreTeclado);
		}
		else{
			System.out.println("El teclado no existe");
		}
	}

	/**
	 * Modificar el texto de un teclado.
	 *
	 * @param nombreTeclado El nombre del teclado en el que se realizará el cambio.
	 * @param texto Nuevo texto modificado.
	 */	
	public void modificarTextoDeTeclado(String nombreTeclado, String texto){
		if(!cjtTeclados.existeTeclado(nombreTeclado)){
			System.out.println("El teclado no existe");
			return;
		}
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
		if(!cjtTeclados.existeTeclado(nombreTeclado)){
			System.out.println("El teclado no existe");
			return;
		}
		informacionTeclados.modificarListaPalabrasDeTeclado(nombreTeclado, listaPalabras);
		actualizarDistribucion(nombreTeclado);
	}

	/**
	 * Añadir nuevo alfabeto
	 * @param idAlfabeto ID del alfabeto que se va a añadir.
	 * @param caracteres String que representa caracteres.
	 */
	public void añadirAlfabeto(String idAlfabeto, String caracteres){
		if(informacionTeclados.existeAlfabeto(idAlfabeto)){
			System.out.println("El alfabeto ya existe");
			return;
		}
		informacionTeclados.añadirAlfabeto(idAlfabeto,caracteres);
	}

	/**
	 * Modificar alfabeto del conjunto de alfabetos.
	 *
	 * @param alfabeto String que representa los caracteres modificados.
	 */
	public void modificarAlfabeto(String idAlfabeto,String alfabeto){
		if(!informacionTeclados.existeAlfabeto(idAlfabeto)){
			System.out.println("El alfabeto con id:"+idAlfabeto+" no existe.");
			return;
		}
		//System.out.println("Se procederá actualizar todos los teclados que compartan ese alfabeto.");
		informacionTeclados.modificarAlfabeto(idAlfabeto,alfabeto);
	}

	/**
	 * Eliminar alfabeto
	 * @param idAlfabeto ID del alfabeto que se va a eliminar.
	 */
	public void eliminarAlfabeto(String idAlfabeto){
		if(!informacionTeclados.existeAlfabeto(idAlfabeto)){
			System.out.println("El alfabeto no existe");
			return;
		}
		/***Caso NO implementado, eliminar un alfabet que es utilizado por uno o varios teclados***/

		//System.out.println("Se procederá a eliminar todos los teclados que utilizan ese alfabeto.");
		informacionTeclados.eliminarAlfabeto(idAlfabeto);

		System.out.println("Alfabeto borrado");
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
		if(!informacionTeclados.existeTeclado(nombreTeclado) || !informacionTeclados.existeAlfabeto(idAlfabeto)){
			System.out.println("Los parametros son incorrectos o no existen.");
			return;	
		}
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
		if(!cjtTeclados.existeTeclado(nombreTeclado)){
			System.out.println("El teclado no existe");
			return "";
		}
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
			System.out.println("El teclado no existe");
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
		if(!cjtTeclados.existeTeclado(nombreTeclado)){
			System.out.println("El teclado no existe");
			return "";
		}
		else if(informacionTeclados.existeTextoDeTeclado(nombreTeclado)){
			System.out.println("El teclado no tiene texto");
			return "";
		}
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
		if(!cjtTeclados.existeTeclado(nombreTeclado)){
			System.out.println("El teclado no existe");
			return "";
		}
		else if(informacionTeclados.existeListaPalabrasDeTeclado(nombreTeclado)){
			System.out.println("El teclado no tiene lista de palabras");
			return "";
		}

		Map<String, Integer> listaPalabras = informacionTeclados.getListaPalabrasDeTeclado(nombreTeclado).getMap();
		return listaPalabras.toString();
	}

	/**
	 * Consulta el algoritmo de un teclado.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de consultar.
	 */
	public void consultarAlgoritmoDeTeclado(String nombreTeclado) {
		if(!cjtTeclados.existeTeclado(nombreTeclado)){
			System.out.println("El teclado no existe");
			return;
		}
		System.out.println(informacionTeclados.getTipoAlgoritmoDeTeclado(nombreTeclado));
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