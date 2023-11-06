package edu.upc.prop.cluster23.presentation;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import edu.upc.prop.cluster23.domaincontrollers.CtrlDominio;

/** Ejemplo de Controlador de Presentación. **/
public class CtrlPresentacion {

	/** Atributos **/

	private CtrlDominio controladorDominio;

	/** Constructor y metodos de inicializacion **/

	public CtrlPresentacion() {
		controladorDominio = new CtrlDominio();
	}

	public void inicializarPresentacion() {
		controladorDominio.inicializarCtrlDominio();
	}

	/** Métodos de sincronizacion entre vistas **/

	/** Llamadas al controlador de dominio **/
}
