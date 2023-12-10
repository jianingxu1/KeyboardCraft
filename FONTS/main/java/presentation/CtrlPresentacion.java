package presentation;
import domaincontrollers.CtrlDominio;
import exceptions.ContrasenaNoValidaExcepcion;
import exceptions.EscrituraIncorrectaFicheroExcepcion;
import exceptions.LecturaIncorrectaFicheroExcepcion;
import exceptions.NombreUsuarioNoValidoExcepcion;

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
	private VistaIniciarSesion vSesion;
	private VistaCrearCuenta vCuenta;

	/** Constructor y metodos de inicializacion **/

	public CtrlPresentacion(int opcion) {
		this.opcion = opcion;

		if (opcion == 2) {
			if (vTitulo == null)  // innecesario
				vTitulo = new VistaInterfazTitulo(this);
		}

		ctrlDominio = new CtrlDominio();

		ctrlDominio.inicializarCtrlDominio();

		try {
			ctrlDominio.cargarUsuarios();
		} catch (LecturaIncorrectaFicheroExcepcion e) {
			System.out.println("Error al cargar usuarios: " + e.getMessage());
		}
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

	public void syncIniciarSesion() {
		vTitulo.desactivar();
		if (vSesion == null) {
			vSesion = new VistaIniciarSesion(this);
		}
		vSesion.hacerVisible();
	}

	public void syncCrearCuenta() {
		if (vCuenta == null) {
			vCuenta = new VistaCrearCuenta(this);
		}
		vCuenta.hacerVisible();
	}

	/** Llamadas al controlador de dominio **/

	public void crearUsuario(String Username, String Password) throws EscrituraIncorrectaFicheroExcepcion, NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion {
		ctrlDominio.añadirUsuario(Username,Password);
		vCuenta.hacerInvisible();
		FuncCargarDatos();
	}

	public boolean existeUsuario(String Username) {
		return ctrlDominio.existeUsuario(Username);
	}

	public void cerrarPrograma() {
		FuncEscribirUsuarios();
		System.exit(0);
	}

	private void FuncCargarDatos() {
		boolean completaCarga = false;
		while (!completaCarga) {
			try {
				ctrlDominio.cargarTeclados();
				ctrlDominio.cargarAlfabetos();
				completaCarga = true;
			} catch (LecturaIncorrectaFicheroExcepcion e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void FuncEscribirUsuarios() {
		try{
			ctrlDominio.guardarUsuarios();
		} catch (EscrituraIncorrectaFicheroExcepcion e) {
			System.out.println(e.getMessage());
		}
	}

	private void FuncEscribirCambios() {
		try{
			ctrlDominio.guardarTeclados();
			ctrlDominio.guardarAlfabetos();
		} catch (EscrituraIncorrectaFicheroExcepcion e) {
			System.out.println(e.getMessage());
		}
	}

	public void cerrarPestañaCrearCuenta() {
		vCuenta.hacerInvisible();
	}
}
