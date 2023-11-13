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

	private class Info {	
		private Alfabeto alfabeto;
		private Texto texto;
		private PalabrasConFrecuencia pcF;
		private String tipoAlgoritmo;

		private Info(Alfabeto a , Texto t, PalabrasConFrecuencia pc, String algoritmo) {
				this.alfabeto = a;
				this.texto = t;
				this.pcF = pc;
				this.tipoAlgoritmo = algoritmo;
		}
	}

	public InformacionTeclados() {
		informacionTeclado = new HashMap<String,Info>();
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
	 * @param alfabeto Instancia de la clase Alfabeto.
	 * @param texto Instancia de la clase Texto.
	 * @param pcF Instancia de la clase PalabrasConFrecuencia.
	 * @param algoritmo String que representa el tipo de algoritmo que se va a aplicar.
	 */
	public void añadirTeclado(String nombreTeclado, Alfabeto alfabeto, Texto texto, PalabrasConFrecuencia pcF, String algoritmo) {
		Info i;
		i = new Info(alfabeto,texto,pcF,algoritmo);
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
	 * Modifica los alfabetos del teclado.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de modificar.
	 * @param alfabeto ArrayList de con caracteres modificados.
	 */
	public void modificarAlfabetoDeTeclado(String nombreTeclado, ArrayList<Character> alfabeto) {
		informacionTeclado.get(nombreTeclado).alfabeto.cambiarCaracteres(alfabeto);
	}

	/**
	 * Modifica la lista de palabras del teclado con nombre nombreTeclado.
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

	// ----- Getters -----

	/**
	 * Devuelve el nombre del alfabeto del teclado.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de consultar.
	 * @return String del nombre del alfabeto.
	 */
	public String getNombreAlfabeto(String nombreTeclado) {
		return informacionTeclado.get(nombreTeclado).alfabeto.getNombre();
	}

	/**
	 * Devuelve el objeto Alfabeto que se ha utilizado para la construcción del teclado.
	 * 
	 * @param nombreTeclado El nombre del teclado que se ha de consultar.
	 * @return Alfabeto que se utiliza para el teclado.
	 */
	public Alfabeto getCaracteresAlfabetoDeTeclado(String nombreTeclado) {
		return informacionTeclado.get(nombreTeclado).alfabeto;
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
	 * @param nombreAlfabeto El nombre del teclado que se ha de consultar.
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
}