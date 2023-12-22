package domain;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.ContrasenaNoValidaExcepcion;
import exceptions.EscrituraIncorrectaFicheroExcepcion;
import exceptions.NombreUsuarioNoValidoExcepcion;

/**
 * Clase CjtUsuarios
 * Representa un conjunto de usuarios. Estos se definen por su nombre de usuario
 *
 * @author Rubén Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 */

public class CjtUsuarios {

    /** Atributos **/
    private HashMap<String, Usuario> conjunto;

    /** Constructora **/
    public CjtUsuarios() {
        conjunto = new HashMap<String, Usuario>();
    }

    private static CjtUsuarios singletonObject;

    /**
     * Devuelve la instancia de CjtUsuarios
     * 
     * @return instancia de CjtUsuarios
     */
    public static CjtUsuarios getInstance() {
        if (singletonObject == null)
            singletonObject = new CjtUsuarios() {
            };
        return singletonObject;
    }

    /** Métodos públicos **/

    /** ----- Setters ----- **/

    /**
     * Crea un usuario nuevo y lo guarda en el conjunto
     * 
     * @param user el nombre del nuevo usuario
     * @param pass su contraseña
     * @throws NombreUsuarioNoValidoExcepcion si el nombre del usuario no es valido
     * @throws ContrasenaNoValidaExcepcion si la contraseña no es valida
     * @throws EscrituraIncorrectaFicheroExcepcion si no se puede escribir en el fichero
     */
    public void anadirNuevoUsuario(String user, String pass) throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion {
        if (existeUsuario(user))
            throw new NombreUsuarioNoValidoExcepcion("El usuario " + user + " ya existe.");
        Usuario usuario = new Usuario(user, pass);
        conjunto.put(user, usuario);
    }

    /**
     * Elimina un usuario del conjunto
     * 
     * @param user el nombre del usuario a eliminar
     * @throws NombreUsuarioNoValidoExcepcion si el nombre del usuario no es valido
     */
    public void eliminarUsuario(String user) throws NombreUsuarioNoValidoExcepcion {
		if (user.trim().isEmpty())
			throw new NombreUsuarioNoValidoExcepcion("El nombre del usuario no puede ser vacío.");
		else if (!existeUsuario(user))
			throw new NombreUsuarioNoValidoExcepcion("El usuario " + user + " no existe.");
        conjunto.remove(user);
    }

    /**
     * Modifica la contraseña de un usuario
     * 
     * @param user el nombre del usuario a modificar
     * @param oldPass la contraseña actual del usuario
     * @param newPass la nueva contraseña del usuario
     * @throws NombreUsuarioNoValidoExcepcion si el nombre del usuario no es valido
     * @throws ContrasenaNoValidaExcepcion si la contraseña no es valida
     */
    public void modificarContrasenaUsuario(String user, String oldPass, String newPass) throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion{
		if (user.trim().isEmpty())
			throw new NombreUsuarioNoValidoExcepcion("El nombre del usuario no puede ser vacío.");
		else if (!existeUsuario(user))
			throw new NombreUsuarioNoValidoExcepcion("El usuario " + user + " no existe.");
		else if(!correctPass(user, oldPass)){
			throw new ContrasenaNoValidaExcepcion("La contrasena actual no es correcta.");
		}
        conjunto.get(user).modificaContrasena(newPass);
    }

    /**
     * Comprueba si la contraseña de un usuario es correcta
     * 
     * @param nombreUsuario el nombre del usuario
     * @param contrasena la contraseña del usuario
     * @return true si la contraseña es correcta, false en caso contrario
     * @throws NombreUsuarioNoValidoExcepcion si el nombre del usuario no es valido
     * @throws ContrasenaNoValidaExcepcion si la contraseña no es valida
     */
    public boolean correctPass(String nombreUsuario, String contrasena) throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion{
        if (nombreUsuario.trim().isEmpty())
            throw new NombreUsuarioNoValidoExcepcion("El nombre del usuario no puede ser vacío.");
        else if (contrasena.trim().isEmpty())
            throw new ContrasenaNoValidaExcepcion("La contraseña no puede ser vacia.");
        if (!existeUsuario(nombreUsuario))
            throw new NombreUsuarioNoValidoExcepcion("El usuario y/o la contraseña son incorrectos.");
        else if (!conjunto.get(nombreUsuario).getContrasena().equals(contrasena))
            throw new ContrasenaNoValidaExcepcion("La contraseña es incorrecta.");
        return true;
    }

    /**
     * Comprueba si un usuario existe
     * 
     * @param user el nombre del usuario
     * @return el nombre del usuario
     */
    public boolean existeUsuario(String user) {
        return conjunto.containsKey(user);
    }

    /**
     * Devuelve el nombre de un usuario
     * 
     * @param user el nombre del usuario
     * @return el nombre del usuario
     */
    public String getNombreUsuario(String user) {
        return conjunto.get(user).getNombre();
    }

    /**
     * Devuelve la contraseña de un usuario
     * 
     * @param user el nombre del usuario
     * @return la contraseña del usuario
     */
    public String getContrasenaUsuario(String user) {
        return conjunto.get(user).getContrasena();
    }

    /**
     * Devuelve el conjunto de usuarios
     * 
     * @return el conjunto de usuarios
     */
    public ArrayList<String> getNombreUsuarios() {
        ArrayList<String> nombreUsuarios = new ArrayList<>();
        for (String nombreUsuario : conjunto.keySet()) {
            nombreUsuarios.add(nombreUsuario);
        }
        return nombreUsuarios;
    }

    /**
     * Limpia el conjunto de usuarios
     * 
     */
    public void clearCjtUsuarios() {
        conjunto.clear();
    }
}
