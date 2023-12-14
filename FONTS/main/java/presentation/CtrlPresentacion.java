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
	private VistaMenuPrincipal vistaMenuPrincipal;

	private VistaGestionAlfabetos vistaGestionAlfabetos;
	private VistaGestionTeclados vistaGestionTeclados;

	private VistaGestionPerfil vistaGestionPerfil;

	private VistaConsultarTeclado vistaConsultarTeclado;

	/** Constructor y metodos de inicializacion **/

	public CtrlPresentacion() {
		ctrlDominio = new CtrlDominio();
		vistaBienvenida = new VistaInterfazTitulo(this);
	}

	public void inicializarPresentacion() {
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

	public void syncVistaIniciarSesion_a_MenuPrincipal() {
		vistaIniciarSesion.hacerInvisible();
		if (vistaMenuPrincipal == null)
			vistaMenuPrincipal = new VistaMenuPrincipal(this);
		vistaMenuPrincipal.hacerVisible();
		vistaBienvenida.hacerInvisible();
	}

	public void syncVistaMenuPrincipal_a_Bienvenida() {
		vistaMenuPrincipal.hacerInvisible();
		vistaBienvenida.hacerVisible();
		vistaBienvenida.activar();
	}

	public void syncVistaGestionAlfabetos_a_MenuPrincipal() {
		vistaGestionAlfabetos.hacerInvisible();
		vistaGestionAlfabetos.desactivar();
		vistaMenuPrincipal.activar();
		vistaMenuPrincipal.hacerVisible();
	}

	public void syncVistaMenuPrincipal_a_GestionAlfabetos() {
		vistaMenuPrincipal.desactivar();
		vistaMenuPrincipal.hacerInvisible();
		if (vistaGestionAlfabetos == null) {
			vistaGestionAlfabetos = new VistaGestionAlfabetos(this);
		}
		vistaGestionAlfabetos.activar();
		vistaGestionAlfabetos.hacerVisible();
	}

	public void syncVistaGestionPerfil_a_MenuPrincipal() {
		vistaGestionPerfil.hacerInvisible();
		vistaGestionPerfil.desactivar();
		vistaMenuPrincipal.activar();
		vistaMenuPrincipal.hacerVisible();
	}

	public void syncVistaMenuPrincipal_a_GestionPerfil() {
		vistaMenuPrincipal.desactivar();
		vistaMenuPrincipal.hacerInvisible();
		if (vistaGestionPerfil == null) {
			vistaGestionPerfil = new VistaGestionPerfil(this);
		}
		vistaGestionPerfil.activar();
		vistaGestionPerfil.hacerVisible();
	}

	public void syncVistaMenuPrincipal_a_GestionTeclados() {
		vistaMenuPrincipal.desactivar();
		vistaMenuPrincipal.hacerInvisible();
		if (vistaGestionTeclados == null) {
			vistaGestionTeclados = new VistaGestionTeclados(this);
		}
		vistaGestionTeclados.activar();
		vistaGestionTeclados.hacerVisible();
	}

	public void syncVistaGestionTeclados_a_MenuPrincipal() {
		vistaGestionTeclados.hacerInvisible();
		vistaGestionTeclados.desactivar();
		vistaMenuPrincipal.activar();
		vistaMenuPrincipal.hacerVisible();
	}

	public void syncVistaGestionTeclados_a_ConsultarTeclado() {
		vistaGestionTeclados.desactivar();
		vistaGestionTeclados.hacerInvisible();
		if (vistaConsultarTeclado == null)
			vistaConsultarTeclado = new VistaConsultarTeclado(this);
		vistaConsultarTeclado.activar();
		vistaConsultarTeclado.hacerVisible();
	}

	public void syncVistaConsultarTeclado_a_GestionTeclados() {
		vistaConsultarTeclado.hacerInvisible();
		vistaConsultarTeclado.desactivar();
		if (vistaGestionTeclados == null)
			vistaGestionTeclados = new VistaGestionTeclados(this);
		vistaGestionTeclados.activar();
		vistaGestionTeclados.hacerVisible();
	}

	/** Llamadas al controlador de dominio **/

	public void iniciarSesion(String Username, String Password) throws Exception {
		ctrlDominio.IniciarSesion(Username,Password);
	}

	public void crearUsuario(String username, String password) throws Exception {
		ctrlDominio.anadirNuevoUsuario(username, password);
		FuncCargarDatos();
	}

	public boolean existeUsuario(String Username) {
		return ctrlDominio.existeUsuario(Username);
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

	public void guardarDatos() throws Exception {
		ctrlDominio.guardarTeclados();
		ctrlDominio.guardarAlfabetos();
		ctrlDominio.guardarUsuarios();
	}

	public void cerrarPrograma() throws Exception {
		System.exit(0);
	}
}
