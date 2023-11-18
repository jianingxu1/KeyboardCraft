package edu.upc.prop.cluster23.presentation;

public class CtrlPresentacion {

	/** Atributos **/

	private Tview viewTerminal;

	/** Constructor y metodos de inicializacion **/

	public CtrlPresentacion() {}

	public void inicializarPresentacion() {

		System.out.println("Durante esta primera entrega, solo tendrás la opción de la vista de terminal");

		viewTerminal = new Tview();

		viewTerminal.init();
	}

	/** Métodos de sincronizacion entre vistas **/

	/** Llamadas al controlador de dominio **/
}
