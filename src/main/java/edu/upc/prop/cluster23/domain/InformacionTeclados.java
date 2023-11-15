package edu.upc.prop.cluster23.domain;


import edu.upc.prop.cluster23.domain.Alfabeto;
import edu.upc.prop.cluster23.domain.PalabrasConFrecuencia;
import edu.upc.prop.cluster23.domain.Texto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** Clase CtrlDominio
 *  Representa un controlador
 *  @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)   
 * 	@co-author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */
public class InformacionTeclados {

  private HashMap<String,Info> informacionTeclado;

	private CjtAlfabetos cjtAlfabetos;

	private static InformacionTeclados singletonObject;

	public static InformacionTeclados getInstance() {
		if (singletonObject == null)
			singletonObject = new InformacionTeclados() {
			};
		return singletonObject;
	}

	private class Info {	
		private String idAlfabeto;
		private Texto texto;
		private PalabrasConFrecuencia pcF;
		private String tipoAlgoritmo;

		private Info(String a , Texto t, PalabrasConFrecuencia pc, String algoritmo) {
				this.idAlfabeto = a;
				this.texto = t;
				this.pcF = pc;
				this.tipoAlgoritmo = algoritmo;
		}
	}

	public InformacionTeclados() {
		informacionTeclado = new HashMap<String,Info>();
		cjtAlfabetos = new CjtAlfabetos();
	}

	// ----- Modificadores -----
	
	/**
	 * Borra la información del teclado con nombre nombreTeclado.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de borrar.
	 */
	public void borrarTeclado(String nombreTeclado) {
		informacionTeclado.remove(nombreTeclado);
	}

	/**
	 * Añade un teclado con nombre nombreTeclado y su respectiva información.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de añadir.
	 * @param idAlfabeto Identifica el alfabeto que se va a utilizar del conjunto de alfabetos.
	 * @param texto Instancia de la clase Texto.
	 * @param pcF Instancia de la clase PalabrasConFrecuencia.
	 * @param algoritmo El tipo de algoritmo que se va a aplicar.
	 */
	public void añadirTeclado(String nombreTeclado, String idAlfabeto, Texto texto, PalabrasConFrecuencia pcF, String algoritmo) {
		Info i;
		i = new Info(idAlfabeto,texto,pcF,algoritmo);
		informacionTeclado.put(nombreTeclado,i);
	}

	/**
	 * Modifica el texto del teclado.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de modificar.
	 * @param texto El nuevo texto que tendrá el teclado.
	 */
	public void modificarTextoDeTeclado(String nombreTeclado, String texto) {
		informacionTeclado.get(nombreTeclado).texto.modificarTexto(texto);
	}

	/**
	 * Assigna nuevo alfabeto a un teclado existente.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de modificar.
	 * @param idAlfabeto ID del nuevo alfabeto que se va a utilizar.
	 */
	public void assignaNuevoAlfabetoDeTeclado(String nombreTeclado, String idAlfabeto) {
		informacionTeclado.get(nombreTeclado).idAlfabeto = idAlfabeto;
	}

	/**
	 * Modifica la lista de palabras del teclado.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de modificar.
	 * @param listaPalabras String con la nueva lista de palabras.
	 */
	public void modificarListaPalabrasDeTeclado(String nombreTeclado, String listaPalabras) {
		informacionTeclado.get(nombreTeclado).pcF.cambiarMap(listaPalabras);
	}

	/**
	 * Modifica el algoritmo del teclado.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de modificar.
	 * @param algoritmo String que identifica el nuevo tipo de algoritmo que se va a aplicar.
	 */
	public void modificarAlgoritmoDeTeclado(String nombreTeclado, String algoritmo) {
		informacionTeclado.get(nombreTeclado).tipoAlgoritmo = algoritmo;
	}

		/**
	 * Añadir alfabeto al conjunto de alfabetos
	 * 
	 * @param idAlfabeto ID del alfabeto que se va a añadir.
	 * @param alfabeto String que representa los nuevos caracteres.
	 */
	public void añadirAlfabeto(String idAlfabeto, String alfabeto) {
		cjtAlfabetos.anadirAlfabeto(idAlfabeto,alfabeto);
	}

	/**
	 * Modificar alfabeto del conjunto de alfabetos
	 * 
	 * @param idAlfabeto ID del alfabeto que se va a modificar.
	 * @param alfabeto String que representa los nuevos caracteres.
	 */
	public void modificarAlfabeto(String idAlfabeto, String alfabeto) {
		cjtAlfabetos.cambiarCaracteres(idAlfabeto,alfabeto);
	}

	/**
	 * Eliminar alfabeto del conjunto alfabetos.
	 * 
	 * @param idAlfabeto ID del alfabeto a eliminar.
	 */
	public void eliminarAlfabeto(String idAlfabeto){
		cjtAlfabetos.eliminarAlfabeto(idAlfabeto);
	}

	// ----- Getters -----

	/**
	 * Devuelve el nombre del alfabeto del teclado.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de consultar.
	 * @return String del nombre del alfabeto.
	 */
	public String getNombreAlfabeto(String nombreTeclado) {
		return informacionTeclado.get(nombreTeclado).idAlfabeto;
	}

	public String consultarAlfabetos(){
		return cjtAlfabetos.consultarAlfabetos();
	}

	/**
	 * Devuelve el objeto Alfabeto que se ha utilizado para la construcción del teclado.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de consultar.
	 * @return Alfabeto que se utiliza para el teclado.
	 */
	public Alfabeto getCaracteresAlfabetoDeTeclado(String nombreTeclado) {

		return cjtAlfabetos.getAlfabeto(informacionTeclado.get(nombreTeclado).idAlfabeto);
	}

	/**
	 * Devuelve el objeto Texto que se ha utilizado para la construcción del teclado.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de consultar.
	 * @return Texto del teclado.
	 */
	public Texto getTextoDeTeclado(String nombreTeclado) {
		return informacionTeclado.get(nombreTeclado).texto;
	}

	/**
	 * Devuelve el objeto PalabrasConFrecuencia que se ha utilizado para la construcción del teclado.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de consultar.
	 * @return PalabrasConFrecuencia del teclado.
	 */
	public PalabrasConFrecuencia getListaPalabrasDeTeclado(String nombreTeclado) {
		return informacionTeclado.get(nombreTeclado).pcF;
	}

	/**
	 * Devuelve el tipo de algoritmo que se ha utilizado para la construcción del teclado.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de consultar.
	 * @return String con el tipo de algoritmo utilizado.
	 */
	public String getTipoAlgoritmoDeTeclado(String nombreTeclado) {
		return informacionTeclado.get(nombreTeclado).tipoAlgoritmo;
	}

	/**
	 * Comprueba si existe un teclado con nombre nombreTeclado.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de consultar.
	 * @return True si existe el teclado, false en caso contrario.
	 */
	public boolean existeTeclado(String nombreTeclado) {
		return informacionTeclado.containsKey(nombreTeclado);
	}

	/**
	 * Comprueba si el teclado tiene asociado un texto
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de consultar.
	 * @return True si existe un texto, false en caso contrario.
	 */
	public boolean existeTextoDeTeclado(String nombreTeclado) {
		return informacionTeclado.get(nombreTeclado).texto != null;
	}

	/**
	 * Comprueba si el teclado tiene asociado una lista de palabras
	 *
	 * @param nombreTeclado El nombre del teclado que se ha de consultar.
	 * @return True si existe una lista de palabras, false en caso contrario. 
	 */
	public boolean existeListaPalabrasDeTeclado(String nombreTeclado) {
		return informacionTeclado.get(nombreTeclado).pcF != null;
	}
	
	/**
	 * Comprueba si existe un alfabeto con nombre idAlfabeto en el conjunto de alfabetos.
	 *
	 * @param idAlfabeto El nombre del alfabeto que quiere consultar.
	 * @return True si existe un alfabeto, false en caso contrario. 
	 */
	public boolean existeAlfabeto(String idAlfabeto) {
		return cjtAlfabetos.existeAlfabeto(idAlfabeto);
	}
}