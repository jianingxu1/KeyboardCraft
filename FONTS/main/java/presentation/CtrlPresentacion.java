package presentation;
import domaincontrollers.CtrlDominio;
import java.lang.Exception;

/**
 * Clase CtrlPresentacion
 * Representa el controlador de presentacion del programa
 * 
 * @author Rubén Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 */
public class CtrlPresentacion {

	/** Atributos **/
	private CtrlDominio ctrlDominio;

	private VistaInterfazTitulo vistaBienvenida;
	private VistaIniciarSesion vistaIniciarSesion;
	private VistaCrearCuenta vistaCrearCuenta;

	/** Constructor y metodos de inicializacion **/

	public CtrlPresentacion() {
		ctrlDominio = new CtrlDominio();
		vistaBienvenida = new VistaInterfazTitulo(this);
	}

	public void inicializarPresentacion() {
		ctrlDominio.inicializarCtrlDominio();
		vistaBienvenida.hacerVisible();
		try {
			ctrlDominio.cargarUsuarios();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/** Métodos de sincronizacion entre vistas **/

	public void syncVistaBienvenida_a_IniciarSesion() {
		vistaBienvenida.desactivar();
		if (vistaIniciarSesion == null)
			vistaIniciarSesion = new VistaIniciarSesion(this);
		vistaIniciarSesion.hacerVisible();
	}

	public void syncVistaIniciarSesion_a_Bienvenida() {
		vistaIniciarSesion.hacerInvisible();
		vistaBienvenida.activar();
	}

	public void syncVistaBienvenida_a_CrearCuenta() {
		vistaBienvenida.desactivar();
		if (vistaCrearCuenta == null)
			vistaCrearCuenta = new VistaCrearCuenta(this);
		vistaCrearCuenta.hacerVisible();
	}

	public void syncVistaCrearCuenta_a_Bienvenida() {
		vistaCrearCuenta.hacerInvisible();
		vistaBienvenida.activar();
	}

	public void syncMenuPrincipal() {
		vistaIniciarSesion.hacerInvisible();
		System.exit(0);
	}

	/** Llamadas al controlador de dominio **/

	public boolean iniciarSesion(String Username, String Password) throws Exception {
		if (ctrlDominio.contraseñaCorrecta(Username,Password)) {
			ctrlDominio.IniciarSesion(Username,Password);
			return true;
		}
		else return false;
	}

	public void crearUsuario(String username, String password) throws Exception {
		ctrlDominio.añadirUsuario(username, password);
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
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void FuncEscribirUsuarios() {
		try{
			ctrlDominio.guardarUsuarios();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void FuncEscribirCambios() {
		try{
			ctrlDominio.guardarTeclados();
			ctrlDominio.guardarAlfabetos();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
