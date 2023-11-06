package edu.upc.prop.cluster23.domaincontrollers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.upc.prop.cluster23.domain.Teclado;

/** Ejemplo de Controlador de Dominio. **/
public class CtrlDominio {

	/** Atributos **/
	
	private Map<String, Teclado> teclados;
	private String tecladoSeleccionado;

	/** Constructor y metodos de inicializacion **/

	public CtrlDominio() {
		inicializarCtrlDominio();
	}

	public void inicializarCtrlDominio() {
		teclados = new HashMap<String, Teclado>();
		tecladoSeleccionado = null;
	}

	/**
	 * Funciones que se llaman desde el controlador de presentacion. Por
	 * convención, únicamente se usan Strings para la comunicación entre las dos
	 * capas.
	 **/

	public String getTecladoSeleccionado() {
		return tecladoSeleccionado;
	}

	public void setTecladoSeleccionado(String teclado) {
		tecladoSeleccionado = teclado;
	}
}
