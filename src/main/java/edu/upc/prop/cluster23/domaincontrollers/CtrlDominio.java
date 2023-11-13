package edu.upc.prop.cluster23.domaincontrollers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import org.w3c.dom.Text;

import edu.upc.prop.cluster23.domain.Alfabeto;
import edu.upc.prop.cluster23.domain.Algoritmo;
import edu.upc.prop.cluster23.domain.AlgoritmoQAP;
import edu.upc.prop.cluster23.domain.CjtTeclados;
import edu.upc.prop.cluster23.domain.GeneradorDistribucionTeclado;
import edu.upc.prop.cluster23.domain.InformacionTeclados;
import edu.upc.prop.cluster23.domain.PalabrasConFrecuencia;
import edu.upc.prop.cluster23.domain.Teclado;
import edu.upc.prop.cluster23.domain.Texto;

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
		 cjtTeclados = new CjtTeclados();
		 informacionTeclados = new InformacionTeclados();
	}

	/**
	 * Crea un teclado 
	 *
	 * @param nombreTeclado Nombre del teclado.
	 * @param idAlfabeto El ID del alfabeto asociado con el teclado.
	 * @param caracteres Un ArrayList de caracteres que representan las teclas del teclado.
	 * @param texto Información adicional para la generación de la distribución del teclado.
	 * @param listaPalabras Texto que contiene las palabras que se desean incluir en la distribución del teclado.
	 * @param algoritmo Un string que representa el algoritmo utilizado para la creación del teclado.
	 */
	public void creaTeclado(String nombreTeclado, String idAlfabeto, ArrayList<Character> caracteres, String texto, String listaPalabras, String algor) {

		if(informacionTeclados.existeTeclado(nombreTeclado)) {
			System.out.println("El teclado ya existe");
			return;
		}

		Alfabeto a = new Alfabeto(idAlfabeto, caracteres);
		PalabrasConFrecuencia pc = new PalabrasConFrecuencia(listaPalabras);
		Texto t = new Texto(texto); 
		informacionTeclados.añadirTeclado(nombreTeclado, a, t, pc, algor);
		
		char[][] distribucion = new char[0][0];
		if(algor == "QAP"){
			GeneradorDistribucionTeclado gdt = new GeneradorDistribucionTeclado(new AlgoritmoQAP());
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
		if(cjtTeclados.existeTeclado(nombreTeclado)){
			cjtTeclados.eliminarTeclado(nombreTeclado);
			informacionTeclados.borrarTeclado(nombreTeclado);
		}
		else{
			System.out.println("El teclado no existe");
		}
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
			if(algoritmo == "QAP"){
				actualizarDistribucion(nombreTeclado, informacionTeclados);
			}
			actualizarDistribucion(nombreTeclado, informacionTeclados);
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
		if(cjtTeclados.existeTeclado(nombreTeclado)){
			informacionTeclados.modificarTextoDeTeclado(nombreTeclado, texto);
			actualizarDistribucion(nombreTeclado, informacionTeclados);
		}
		else{
			System.out.println("El teclado no existe");
		}
	}

	/**
	 * Modificar la lista de palabras de un teclado.
	 *
	 * @param nombreTeclado El nombre del teclado en el que se realizará el cambio.
	 * @param listaPalabras Nueva lista de palabras.
	 */
	public void modificarListaPalabrasDeTeclado(String nombreTeclado, String listaPalabras){
		if(cjtTeclados.existeTeclado(nombreTeclado)){
			informacionTeclados.modificarListaPalabrasDeTeclado(nombreTeclado, listaPalabras);
			actualizarDistribucion(nombreTeclado, informacionTeclados);
		}
		else{
			System.out.println("El teclado no existe");
		}
	}

	/**
	 * Modificar alfabetos de un teclado.
	 *
	 * @param nombreTeclado El nombre del teclado en el que se realizará el cambio.
	 * @param alfabeto ArrayList con los caracteres modificados.
	 */
	public void modificarAlfabeto(String nombreTeclado, ArrayList<Character> alfabeto){
		if(cjtTeclados.existeTeclado(nombreTeclado)){
			informacionTeclados.modificarAlfabetoDeTeclado(nombreTeclado, alfabeto);
			actualizarDistribucion(nombreTeclado, informacionTeclados);
		}
		else{
			System.out.println("El teclado no existe");
		}
	}

	// ----- Getters -----

	/**
	 * Printea la distribución de un teclado.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de mostrar.
	 */
	public void consultarDistribucionDeTeclado(String nombreTeclado) {
		if(!cjtTeclados.existeTeclado(nombreTeclado)){
			System.out.println("El teclado no existe");
			return;
		}
		char[][] distribucion = cjtTeclados.getTeclado(nombreTeclado);
		System.out.println("Teclado: " + nombreTeclado);
		for(int i = 0; i < distribucion.length; ++i) {
			for(int j = 0; j < distribucion[0].length; ++j) {
				System.out.print(distribucion[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * Printea el nombre de todos los teclados del conjunto de teclados.
	 */
	public void consultarNombreTeclados() {
		ArrayList<String> nombreTeclados = cjtTeclados.getNombreTeclados();
		for(int i = 0; i < nombreTeclados.size(); ++i) {
			System.out.println(nombreTeclados.get(i));
		}
	}

	/**
	 * Printea todos los alfabetos de un teclado.
	 * 
	 * @param nombreTeclado  El nombre del teclado que se ha de consultar.
	 */
	public void consultarAlfabetosDeTeclado(String nombreTeclado) {
		if(!cjtTeclados.existeTeclado(nombreTeclado)){
			System.out.println("El teclado no existe");
			return;
		}
		ArrayList<Character> alfabeto = informacionTeclados.getCaracteresAlfabetoDeTeclado(nombreTeclado).getCaracteres();
		for(int i = 0; i < alfabeto.size(); ++i) {
			System.out.print(alfabeto.get(i));
		}
		System.out.println();
	}

	/**
	 * Printea el texto de un teclado.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de consultar.
	 */
	public void consultarTextoDeTeclado(String nombreTeclado) {
		if(!cjtTeclados.existeTeclado(nombreTeclado)){
			System.out.println("El teclado no existe");
			return;
		}
		else if(informacionTeclados.existeTextoDeTeclado(nombreTeclado)){
			System.out.println("El teclado no tiene texto");
			return;
		}
		System.out.println(informacionTeclados.getTextoDeTeclado(nombreTeclado).getTexto());
	}

	/**
	 * Printea la lista de palabras de un teclado.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de consultar.
	 */
	public void consultarListaPalabrasDeTeclado(String nombreTeclado) {
		if(!cjtTeclados.existeTeclado(nombreTeclado)){
			System.out.println("El teclado no existe");
			return;
		}
		else if(informacionTeclados.existeListaPalabrasDeTeclado(nombreTeclado)){
			System.out.println("El teclado no tiene lista de palabras");
			return;
		}

		Map<String, Integer> listaPalabras = informacionTeclados.getListaPalabrasDeTeclado(nombreTeclado).getMap();
		for (Map.Entry<String, Integer> entry : listaPalabras.entrySet()) {
			System.out.println(entry.getKey() + " frec:" + entry.getValue());
		}
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

	// ----- Utilidades -----

	/**
	 * Actualiza la distribución de un teclado.
	 *
	 * @param nombreTeclado El nombre del teclado cuya distribución se actualizará.
	 * @param informacionTeclados La información del teclado que se requiere para regenerar la distribución.
	 */
	private void actualizarDistribucion(String nombreTeclado,InformacionTeclados informacionTeclados) {
		char[][] distribucion = new char[0][0];
		if(informacionTeclados.getTipoAlgoritmoDeTeclado(nombreTeclado) == "QAP"){
			GeneradorDistribucionTeclado gdt = new GeneradorDistribucionTeclado(new AlgoritmoQAP());
			distribucion = gdt.generarDistribucion(informacionTeclados.getCaracteresAlfabetoDeTeclado(nombreTeclado),informacionTeclados.getListaPalabrasDeTeclado(nombreTeclado), informacionTeclados.getTextoDeTeclado(nombreTeclado));
		}
		cjtTeclados.setDistribucionTeclado(nombreTeclado, distribucion);
	}

}