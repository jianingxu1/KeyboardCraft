package presentation;
import domaincontrollers.CtrlDominio;

import java.lang.Exception;
import java.util.*;

/**
 * Clase CtrlPresentacion
 * Representa el controlador de presentacion del programa
 * 
 * @author Rubén Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 */
public class CtrlPresentacion {

	/** Atributos **/
	private CtrlDominio ctrlDominio;

	private VistaBienvenida vistaBienvenida;
	private VistaCrearCuenta vistaCrearCuenta;
	private VistaMenuPrincipal vistaMenuPrincipal;

	private VistaGestionAlfabetos vistaGestionAlfabetos;
	private VistaGestionTeclados vistaGestionTeclados;

	private VistaGestionPerfil vistaGestionPerfil;

	private VistaConsultarTeclado vistaConsultarTeclado;

	private VistaCambiarContraseña vistaCambiarContraseña;

	/** Constructor y metodos de inicializacion **/

	public CtrlPresentacion() {
		ctrlDominio = new CtrlDominio();
		vistaBienvenida = new VistaBienvenida(this);
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
	public void syncVistaBienvenida_a_CrearCuenta() {
		if (vistaBienvenida == null)
			vistaBienvenida = new VistaBienvenida(this);
		vistaBienvenida.hacerInvisible();
		if (vistaCrearCuenta == null)
			vistaCrearCuenta = new VistaCrearCuenta(this);
		vistaCrearCuenta.hacerVisible();
	}

	public void syncVistaCrearCuenta_a_Bienvenida() {
		if (vistaCrearCuenta == null)
			vistaCrearCuenta = new VistaCrearCuenta(this);
		vistaCrearCuenta.hacerInvisible();
		if (vistaBienvenida == null)
			vistaBienvenida = new VistaBienvenida(this);
		vistaBienvenida.hacerVisible();
	}

	public void syncVistaBienvenida_a_MenuPrincipal() {
		if (vistaBienvenida == null)
			vistaBienvenida = new VistaBienvenida(this);
		vistaBienvenida.hacerInvisible();
		if (vistaMenuPrincipal == null)
			vistaMenuPrincipal = new VistaMenuPrincipal(this);
		vistaMenuPrincipal.hacerVisible();
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

	public void syncVistaGestionPerfil_a_Bienvenida() {
		vistaGestionPerfil.hacerInvisible();
		vistaGestionPerfil.desactivar();
		vistaBienvenida.activar();
		vistaBienvenida.hacerVisible();
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

	public void syncVistaGestionPerfil_a_CambiarContraseña() {
		vistaGestionPerfil.desactivar();
		vistaGestionPerfil.hacerInvisible();
		if (vistaCambiarContraseña == null)
			vistaCambiarContraseña = new VistaCambiarContraseña(this);
		vistaCambiarContraseña.activar();
		vistaCambiarContraseña.hacerVisible();
	}

	public void syncVistaConsultarTeclado_a_GestionTeclados() {
		vistaConsultarTeclado.hacerInvisible();
		vistaConsultarTeclado.desactivar();
		if (vistaGestionTeclados == null)
			vistaGestionTeclados = new VistaGestionTeclados(this);
		vistaGestionTeclados.activar();
		vistaGestionTeclados.hacerVisible();
	}

	public void syncVistaCambiarContraseña_a_GestionPerfil() {
		vistaCambiarContraseña.hacerInvisible();
		vistaCambiarContraseña.desactivar();
		vistaGestionPerfil.hacerVisible();
		vistaGestionPerfil.activar();
	}

	/** Llamadas al controlador de dominio **/

	public void iniciarSesion(String Username, String Password) throws Exception {
		ctrlDominio.IniciarSesion(Username,Password);
		cargarDatosUsuario();
	}

	public void cerrarSesion() throws Exception {
		ctrlDominio.cerrarSesion();
	}

	public void crearUsuario(String username, String password) throws Exception {
		ctrlDominio.anadirNuevoUsuario(username, password);
	}

	public boolean existeUsuario(String Username) {
		return ctrlDominio.existeUsuario(Username);
	}

	public void eliminarTeclado(String nombreTeclado) throws Exception {
		ctrlDominio.eliminarTeclado(nombreTeclado);
	}

	public ArrayList<String> getNombreTeclados() {
		return ctrlDominio.getNombreTeclados();
	}

	public ArrayList<String> getNombreAlfabetos() {
		return ctrlDominio.getNombreAlfabetos();
	}

	public ArrayList<String> getNombreAlgoritmos() {
		return ctrlDominio.getNombreAlgoritmos();
	}

	public void crearTeclado(String nombreTeclado, String nombreAlfabeto, String texto, String palabrasConFrecuencia, String nombreAlgoritmo) throws Exception {
		ctrlDominio.crearTeclado(nombreTeclado, nombreAlfabeto, texto, palabrasConFrecuencia, nombreAlgoritmo);
	}

	public Character[][] getDistribucionTeclado(String nombreTeclado) throws Exception {
		return ctrlDominio.getDistribucionTeclado(nombreTeclado);
	}

	public void crearAlfabeto(String nombreAlfabeto, String caracteresAlfabeto) throws Exception {
		ctrlDominio.crearAlfabeto(nombreAlfabeto, caracteresAlfabeto);
	}
	
	public void eliminarAlfabeto(String nombreAlfabeto) throws Exception {
		ctrlDominio.eliminarAlfabeto(nombreAlfabeto);
	}

	public void modificarCaracteresAlfabeto(String nombreAlfabeto, String caracteresAlfabeto) throws Exception {
		ctrlDominio.modificarCaracteresAlfabeto(nombreAlfabeto, caracteresAlfabeto);
	}

	public ArrayList<Character> getCaracteresDeAlfabeto(String nombreAlfabeto) throws Exception {
		return ctrlDominio.getCaracteresDeAlfabeto(nombreAlfabeto);
	}

	private void cargarDatosUsuario() throws Exception{
			ctrlDominio.cargarTeclados();
			ctrlDominio.cargarAlfabetos();
	}

	public void guardarDatos() throws Exception {
		ctrlDominio.guardarTeclados();
		ctrlDominio.guardarAlfabetos();
		ctrlDominio.guardarUsuarios();
	}

	public String importarTexto(String filePath) throws Exception {
		return ctrlDominio.importarTexto(filePath);
	}
	
	public String importarListaPalabras(String filePath) throws Exception {
		return ctrlDominio.importarListaPalabras(filePath);
	}

	public void cambiarContraseña(String oldPass, String newPass) throws Exception {
		ctrlDominio.modificarContrasenaDesdeInterfaz(oldPass, newPass);
	}

	public void borrarCuenta() throws Exception {
		ctrlDominio.eliminarUsuarioDesdeInterfaz();
		syncVistaGestionPerfil_a_Bienvenida();
	}

	public void cerrarPrograma() throws Exception {
		System.exit(0);
	}
}
