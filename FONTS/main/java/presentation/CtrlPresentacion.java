package presentation;

/**
 * Clase CtrlPresentacion
 * Representa el controlador de presentacion del programa
 * 
 * @author Rubén Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 */
public class CtrlPresentacion {

	/** Atributos **/

	private VistaTerminal viewTerminal;

	/** Constructor y metodos de inicializacion **/

	public CtrlPresentacion() {
	}

	public void inicializarPresentacion() {

		System.out.println("Durante esta primera entrega, solo tendrás la opción de la vista de terminal");

		viewTerminal = new VistaTerminal();

		viewTerminal.init();
	}

	/** Métodos de sincronizacion entre vistas **/

	/** Llamadas al controlador de dominio **/
}
