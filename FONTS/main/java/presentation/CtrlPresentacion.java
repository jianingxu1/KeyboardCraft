package presentation;
import domaincontrollers.CtrlDominio;

/**
 * Clase CtrlPresentacion
 * Representa el controlador de presentacion del programa
 * 
 * @author Rubén Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 */
public class CtrlPresentacion {

	/** Atributos **/

	private VistaTerminal viewTerminal;
	private CtrlDominio ctrlDominio;

	private int opcion;	// 1: terminal, 2: interfaz
	private VistaInterfazTitulo vTitulo;

	/** Constructor y metodos de inicializacion **/

	public CtrlPresentacion(int opcion) {
		this.opcion = opcion;

		if (opcion == 2) {
			if (vTitulo == null)  // innecesario
				vTitulo = new VistaInterfazTitulo(this);
		}

		ctrlDominio = new CtrlDominio();
	}

	public void inicializarPresentacion() {

		if (opcion == 1) {
			viewTerminal = new VistaTerminal(ctrlDominio);
			viewTerminal.init();
		}
		else {
			vTitulo.hacerVisible();
		}
	}



	/** Métodos de sincronizacion entre vistas **/

	/** Llamadas al controlador de dominio **/
}
