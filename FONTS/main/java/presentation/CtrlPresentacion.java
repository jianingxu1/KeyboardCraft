package presentation;

import domaincontrollers.CtrlDominio;

import java.util.*;

/**
 * Clase CtrlPresentacion
 * Representa el controlador de presentacion del programa
 * 
 * @author Rubén Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 * @author Jianing Xu (jianing.xu@estudiantat.upc.edu)
 */
public class CtrlPresentacion {

	/** Atributos **/
	private CtrlDominio ctrlDominio;

	/** Vistas **/
	private VistaBienvenida vistaBienvenida;
	private VistaCrearCuenta vistaCrearCuenta;
	private VistaMenuPrincipal vistaMenuPrincipal;
	private VistaGestionAlfabetos vistaGestionAlfabetos;
	private VistaGestionTeclados vistaGestionTeclados;
	private VistaGestionPerfil vistaGestionPerfil;
	private VistaCambiarContrasena vistaCambiarContrasena;

	/** Constructor y metodos de inicializacion **/

	/**
	 * Creadora por defecto
	 */
	public CtrlPresentacion() {
		ctrlDominio = new CtrlDominio();
		vistaBienvenida = new VistaBienvenida(this);
	}

	/**
	 * Inicializa la presentacion
	 */
	public void inicializarPresentacion() {
		if (vistaBienvenida == null)
			vistaBienvenida = new VistaBienvenida(this);
		vistaBienvenida.hacerVisible();
		vistaBienvenida.activar();
		try {
			ctrlDominio.cargarUsuarios();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/** Métodos de sincronizacion entre vistas **/

	/**
	 * Sincroniza la vista de bienvenida con la vista de crear cuenta
	 */
	public void syncVistaBienvenida_a_CrearCuenta() {
		if (vistaBienvenida == null)
			vistaBienvenida = new VistaBienvenida(this);
		vistaBienvenida.hacerInvisible();
		vistaBienvenida.desactivar();

		if (vistaCrearCuenta == null)
			vistaCrearCuenta = new VistaCrearCuenta(this);
		vistaCrearCuenta.hacerVisible();
		vistaCrearCuenta.activar();
	}

	/**
	 * Sincroniza la vista de crear cuenta con la vista de bienvenida
	 */
	public void syncVistaCrearCuenta_a_Bienvenida() {
		if (vistaCrearCuenta == null)
			vistaCrearCuenta = new VistaCrearCuenta(this);
		vistaCrearCuenta.hacerInvisible();
		vistaCrearCuenta.desactivar();

		if (vistaBienvenida == null)
			vistaBienvenida = new VistaBienvenida(this);
		vistaBienvenida.hacerVisible();
		vistaBienvenida.activar();
	}

	/**
	 * Sincroniza la vista de bienvenida con la vista de menu principal
	 */
	public void syncVistaBienvenida_a_MenuPrincipal() {
		if (vistaBienvenida == null)
			vistaBienvenida = new VistaBienvenida(this);
		vistaBienvenida.hacerInvisible();
		vistaBienvenida.desactivar();

		if (vistaMenuPrincipal == null)
			vistaMenuPrincipal = new VistaMenuPrincipal(this);
		vistaMenuPrincipal.hacerVisible();
		vistaMenuPrincipal.activar();
	}

	/**
	 * Sincroniza la vista de menu principal con la vista de bienvenida
	 */
	public void syncVistaMenuPrincipal_a_Bienvenida() {
		if (vistaMenuPrincipal == null)
			vistaMenuPrincipal = new VistaMenuPrincipal(this);
		vistaMenuPrincipal.hacerInvisible();
		vistaMenuPrincipal.desactivar();

		if (vistaBienvenida == null)
			vistaBienvenida = new VistaBienvenida(this);
		vistaBienvenida.hacerVisible();
		vistaBienvenida.activar();
	}

	/**
	 * Sincroniza la vista de menu principal con la vista de gestion de alfabetos
	 */
	public void syncVistaGestionAlfabetos_a_MenuPrincipal() {
		if (vistaGestionAlfabetos == null)
			vistaGestionAlfabetos = new VistaGestionAlfabetos(this);
		vistaGestionAlfabetos.hacerInvisible();
		vistaGestionAlfabetos.desactivar();

		if (vistaMenuPrincipal == null)
			vistaMenuPrincipal = new VistaMenuPrincipal(this);
		vistaMenuPrincipal.hacerVisible();
		vistaMenuPrincipal.activar();
	}

	/**
	 * Sincroniza la vista de gestion de alfabetos con la vista de menu principal
	 */
	public void syncVistaMenuPrincipal_a_GestionAlfabetos() {
		if (vistaMenuPrincipal == null)
			vistaMenuPrincipal = new VistaMenuPrincipal(this);
		vistaMenuPrincipal.hacerInvisible();
		vistaMenuPrincipal.desactivar();

		if (vistaGestionAlfabetos == null)
			vistaGestionAlfabetos = new VistaGestionAlfabetos(this);
		vistaGestionAlfabetos.hacerVisible();
		vistaGestionAlfabetos.activar();
	}

	/**
	 * Sincroniza la vista de gestion de perfil con la vista de menu principal
	 */
	public void syncVistaGestionPerfil_a_MenuPrincipal() {
		if (vistaGestionPerfil == null)
			vistaGestionPerfil = new VistaGestionPerfil(this);
		vistaGestionPerfil.hacerInvisible();
		vistaGestionPerfil.desactivar();

		if (vistaMenuPrincipal == null)
			vistaMenuPrincipal = new VistaMenuPrincipal(this);
		vistaMenuPrincipal.activar();
		vistaMenuPrincipal.hacerVisible();
	}

	/**
	 * Sincroniza la vista de gestion de perfil con la vista de bienvenida
	 */
	public void syncVistaGestionPerfil_a_Bienvenida() {
		if (vistaGestionPerfil == null)
			vistaGestionPerfil = new VistaGestionPerfil(this);
		vistaGestionPerfil.hacerInvisible();
		vistaGestionPerfil.desactivar();

		if (vistaBienvenida == null)
			vistaBienvenida = new VistaBienvenida(this);
		vistaBienvenida.hacerVisible();
		vistaBienvenida.activar();
	}

	/**
	 * Sincroniza la de menu principal con la vista de gestion de perfil
	 */
	public void syncVistaMenuPrincipal_a_GestionPerfil() {
		if (vistaMenuPrincipal == null)
			vistaMenuPrincipal = new VistaMenuPrincipal(this);
		vistaMenuPrincipal.hacerInvisible();
		vistaMenuPrincipal.desactivar();

		if (vistaGestionPerfil == null)
			vistaGestionPerfil = new VistaGestionPerfil(this);
		vistaGestionPerfil.hacerVisible();
		vistaGestionPerfil.activar();
	}

	/**
	 * Sincroniza la vista de menu principal con la vista de gestion de teclados
	 */
	public void syncVistaMenuPrincipal_a_GestionTeclados() {
		if (vistaMenuPrincipal == null)
			vistaMenuPrincipal = new VistaMenuPrincipal(this);
		vistaMenuPrincipal.hacerInvisible();
		vistaMenuPrincipal.desactivar();

		if (vistaGestionTeclados == null)
			vistaGestionTeclados = new VistaGestionTeclados(this);
		vistaGestionTeclados.hacerVisible();
		vistaGestionTeclados.activar();
	}

	/**
	 * Sincroniza la vista de gestion de teclados con la vista de menu principal
	 */
	public void syncVistaGestionTeclados_a_MenuPrincipal() {
		if (vistaGestionTeclados == null)
			vistaGestionTeclados = new VistaGestionTeclados(this);
		vistaGestionTeclados.hacerInvisible();
		vistaGestionTeclados.desactivar();

		if (vistaMenuPrincipal == null)
			vistaMenuPrincipal = new VistaMenuPrincipal(this);
		vistaMenuPrincipal.activar();
		vistaMenuPrincipal.hacerVisible();
	}

	/**
	 * Sincroniza la vista de gestion perfil a la vista de cambiar contrasena
	 */
	public void syncVistaGestionPerfil_a_CambiarContrasena() {
		if (vistaGestionPerfil == null)
			vistaGestionPerfil = new VistaGestionPerfil(this);
		vistaGestionPerfil.desactivar();
		vistaGestionPerfil.hacerInvisible();

		if (vistaCambiarContrasena == null)
			vistaCambiarContrasena = new VistaCambiarContrasena(this);
		vistaCambiarContrasena.activar();
		vistaCambiarContrasena.hacerVisible();
	}

	/**
	 * Sincroniza la vista de cambiar contrasena a la vista de gestion perfil
	 */
	public void syncVistaCambiarContrasena_a_GestionPerfil() {
		if (vistaCambiarContrasena == null)
			vistaCambiarContrasena = new VistaCambiarContrasena(this);
		vistaCambiarContrasena.hacerInvisible();
		vistaCambiarContrasena.desactivar();

		if (vistaGestionPerfil == null)
			vistaGestionPerfil = new VistaGestionPerfil(this);
		vistaGestionPerfil.hacerVisible();
		vistaGestionPerfil.activar();
	}

	/** Llamadas al controlador de dominio **/

	/**
	 * Inicia sesión para el usuario con el nombre de usuario y contraseña
	 * proporcionados.
	 *
	 * @param username Nombre de usuario para iniciar sesión.
	 * @param password Contraseña asociada al nombre de usuario.
	 * @throws Exception Si ocurre algún error durante el proceso de inicio de
	 *                   sesión.
	 */
	public void iniciarSesion(String username, String password) throws Exception {
		ctrlDominio.IniciarSesion(username, password);
		cargarDatosUsuario();
	}

	/**
	 * Cierra la sesión del usuario actual.
	 *
	 * @throws Exception Si ocurre algún error durante el proceso de cierre de
	 *                   sesión.
	 */
	public void cerrarSesion() throws Exception {
		ctrlDominio.cerrarSesion();
	}

	/**
	 * Crea un nuevo usuario con el nombre de usuario y contraseña proporcionados.
	 *
	 * @param username Nombre de usuario para el nuevo usuario.
	 * @param password Contraseña asociada al nuevo usuario.
	 * @throws Exception Si ocurre algún error durante la creación del nuevo
	 *                   usuario.
	 */
	public void crearUsuario(String username, String password) throws Exception {
		ctrlDominio.anadirNuevoUsuario(username, password);
	}

	/**
	 * Verifica si un usuario con el nombre de usuario proporcionado existe.
	 *
	 * @param username Nombre de usuario a verificar.
	 * @return True si el usuario existe, false de lo contrario.
	 */
	public boolean existeUsuario(String username) {
		return ctrlDominio.existeUsuario(username);
	}

	// ... (otros métodos de la clase)

	/**
	 * Elimina un teclado asociado al usuario actual.
	 *
	 * @param nombreTeclado Nombre del teclado a eliminar.
	 * @throws Exception Si ocurre algún error durante el proceso de eliminación del
	 *                   teclado.
	 */
	public void eliminarTeclado(String nombreTeclado) throws Exception {
		ctrlDominio.eliminarTeclado(nombreTeclado);
	}

	/**
	 * Obtiene la lista de nombres de los teclados asociados al usuario actual.
	 *
	 * @return Lista de nombres de teclados.
	 */
	public ArrayList<String> getNombreTeclados() {
		return ctrlDominio.getNombreTeclados();
	}

	/**
	 * Obtiene la lista de nombres de los alfabetos asociados al usuario actual.
	 *
	 * @return Lista de nombres de alfabetos.
	 */
	public ArrayList<String> getNombreAlfabetos() {
		return ctrlDominio.getNombreAlfabetos();
	}

	/**
	 * Obtiene la lista de nombres de los algoritmos asociados al usuario actual.
	 *
	 * @return Lista de nombres de algoritmos.
	 */
	public ArrayList<String> getNombreAlgoritmos() {
		return ctrlDominio.getNombreAlgoritmos();
	}

	/**
	 * Crea un nuevo teclado con los parámetros proporcionados y lo asocia al
	 * usuario actual.
	 *
	 * @param nombreTeclado         Nombre del nuevo teclado.
	 * @param nombreAlfabeto        Nombre del alfabeto asociado al teclado.
	 * @param texto                 Texto asociado al teclado.
	 * @param palabrasConFrecuencia Palabras con frecuencia asociadas al teclado.
	 * @param nombreAlgoritmo       Nombre del algoritmo asociado al teclado.
	 * @throws Exception Si ocurre algún error durante la creación del nuevo
	 *                   teclado.
	 */
	public void crearTeclado(String nombreTeclado, String nombreAlfabeto, String texto, String palabrasConFrecuencia,
			String nombreAlgoritmo) throws Exception {
		ctrlDominio.crearTeclado(nombreTeclado, nombreAlfabeto, texto, palabrasConFrecuencia, nombreAlgoritmo);
		ctrlDominio.guardarTeclados();
	}

	/**
	 * Intercambia dos teclas en el teclado especificado por nombre.
	 *
	 * @param nombreTeclado Nombre del teclado en el cual realizar el intercambio.
	 * @param tecla1        Primera tecla a intercambiar.
	 * @param tecla2        Segunda tecla a intercambiar.
	 * @throws Exception Si ocurre algún error durante el proceso de intercambio de
	 *                   teclas.
	 */
	public void intercambiarTeclasTeclado(String nombreTeclado, char tecla1, char tecla2) throws Exception {
		ctrlDominio.intercambiarTeclasTeclado(nombreTeclado, tecla1, tecla2);
	}

	/**
	 * Obtiene la distribución del teclado especificado por nombre.
	 *
	 * @param nombreTeclado Nombre del teclado del cual obtener la distribución.
	 * @return Matriz de caracteres que representa la distribución del teclado.
	 * @throws Exception Si ocurre algún error durante el proceso de obtención de la
	 *                   distribución del teclado.
	 */
	public Character[][] getDistribucionTeclado(String nombreTeclado) throws Exception {
		return ctrlDominio.getDistribucionTeclado(nombreTeclado);
	}

	/**
	 * Crea un nuevo alfabeto con el nombre y caracteres especificados.
	 *
	 * @param nombreAlfabeto     Nombre del nuevo alfabeto.
	 * @param caracteresAlfabeto Caracteres asociados al nuevo alfabeto.
	 * @throws Exception Si ocurre algún error durante el proceso de creación del
	 *                   nuevo alfabeto.
	 */
	public void crearAlfabeto(String nombreAlfabeto, String caracteresAlfabeto) throws Exception {
		ctrlDominio.crearAlfabeto(nombreAlfabeto, caracteresAlfabeto);
	}

	/**
	 * Elimina el alfabeto especificado por nombre.
	 *
	 * @param nombreAlfabeto Nombre del alfabeto a eliminar.
	 * @throws Exception Si ocurre algún error durante el proceso de eliminación del
	 *                   alfabeto.
	 */
	public void eliminarAlfabeto(String nombreAlfabeto) throws Exception {
		ctrlDominio.eliminarAlfabeto(nombreAlfabeto);
	}

	/**
	 * Modifica los caracteres del alfabeto especificado por nombre.
	 *
	 * @param nombreAlfabeto     Nombre del alfabeto a modificar.
	 * @param caracteresAlfabeto Nuevos caracteres asociados al alfabeto.
	 * @throws Exception Si ocurre algún error durante el proceso de modificación de
	 *                   caracteres del alfabeto.
	 */
	public void modificarCaracteresAlfabeto(String nombreAlfabeto, String caracteresAlfabeto) throws Exception {
		ctrlDominio.modificarCaracteresAlfabeto(nombreAlfabeto, caracteresAlfabeto);
	}

	/**
	 * Obtiene los caracteres asociados al alfabeto especificado por nombre.
	 *
	 * @param nombreAlfabeto Nombre del alfabeto del cual obtener los caracteres.
	 * @return Lista de caracteres asociados al alfabeto.
	 * @throws Exception Si ocurre algún error durante el proceso de obtención de
	 *                   caracteres del alfabeto.
	 */
	public ArrayList<Character> getCaracteresDeAlfabeto(String nombreAlfabeto) throws Exception {
		return ctrlDominio.getCaracteresDeAlfabeto(nombreAlfabeto);
	}

	/**
	 * Carga los teclados y alfabetos asociados al usuario actual.
	 *
	 * @throws Exception Si ocurre algún error durante el proceso de carga de datos
	 *                   del usuario.
	 */
	private void cargarDatosUsuario() throws Exception {
		ctrlDominio.cargarTeclados();
		ctrlDominio.cargarAlfabetos();
	}

	/**
	 * Guarda los teclados, alfabetos y usuarios asociados al usuario actual.
	 *
	 * @throws Exception Si ocurre algún error durante el proceso de guardado de
	 *                   datos.
	 */
	public void guardarDatos() throws Exception {
		ctrlDominio.guardarTeclados();
		ctrlDominio.guardarAlfabetos();
		ctrlDominio.guardarUsuarios();
	}

	/**
	 * Importa un texto desde un archivo.
	 *
	 * @param filePath Ruta del archivo desde el cual importar el texto.
	 * @return Texto importado.
	 * @throws Exception Si ocurre algún error durante el proceso de importación de
	 *                   texto.
	 */
	public String importarTexto(String filePath) throws Exception {
		return ctrlDominio.importarTexto(filePath);
	}

	/**
	 * Importa una lista de palabras desde un archivo.
	 *
	 * @param filePath Ruta del archivo desde el cual importar la lista de palabras.
	 * @return Lista de palabras importada.
	 * @throws Exception Si ocurre algún error durante el proceso de importación de
	 *                   la lista de palabras.
	 */
	public String importarListaPalabras(String filePath) throws Exception {
		return ctrlDominio.importarListaPalabras(filePath);
	}

	/**
	 * Cambia la contraseña del usuario actual.
	 *
	 * @param oldPass Contraseña actual del usuario.
	 * @param newPass Nueva contraseña para el usuario.
	 * @throws Exception Si ocurre algún error durante el proceso de cambio de
	 *                   contraseña.
	 */
	public void cambiarContrasena(String oldPass, String newPass) throws Exception {
		ctrlDominio.modificarContrasenaUsuarioActual(oldPass, newPass);
	}

	/**
	 * Elimina la cuenta del usuario actual.
	 *
	 * @throws Exception Si ocurre algún error durante el proceso de eliminación de
	 *                   la cuenta.
	 */
	public void borrarCuenta() throws Exception {
		ctrlDominio.eliminarUsuarioActual();
	}

	/**
	 * Cierra el programa.
	 *
	 * @throws Exception Si ocurre algún error durante el proceso de cierre del
	 *                   programa.
	 */
	public void cerrarPrograma() throws Exception {
		System.exit(0);
	}

}
