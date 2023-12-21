package domain;

import exceptions.NombreUsuarioNoValidoExcepcion;
import exceptions.ContrasenaNoValidaExcepcion;

/**
 * Clase Usuario
 * Representa un usuario con su nombre de usuario y contraseña.
 *
 * @author Rubén Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 */
public class Usuario {

    /** Atributos **/
    private String username;
    private String password;

    /** Constructora **/
    /**
     * Crea un usuario nuevo con su nombre de usuario y contraseña
     * @param username nombre de usuario
     * @param password contraseña
     * @throws NombreUsuarioNoValidoExcepcion si el nombre de usuario no es valido
     * @throws ContrasenaNoValidaExcepcion si la contraseña no es valida
     */
    public Usuario(String username, String password) throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion{
        if (username.trim().isEmpty())
			throw new NombreUsuarioNoValidoExcepcion("El nombre del usuario no puede ser vacío.");
        this.username = username;
        modificaContrasena(password);
    }

    /** Métodos públicos **/

    /** ----- Setters ----- **/

    /**
     * Modifica la contraseña del usuario
     * @param newPass nueva contraseña
     * @throws ContrasenaNoValidaExcepcion si la contraseña no es valida
     */
    public void modificaContrasena(String newPass) throws ContrasenaNoValidaExcepcion {
		if (newPass.trim().isEmpty())
			throw new ContrasenaNoValidaExcepcion("La contraseña no puede ser vacia.");
		else if (newPass.length() < 8)
			throw new ContrasenaNoValidaExcepcion("La contraseña debe tener al menos 8 caracteres.");
        this.password = newPass;
    }

    /** ----- Getters ----- **/

    /**
     * Devuelve el nombre de usuario
     * @return nombre de usuario
     */
    public String getNombre() {
        return username;
    }

    /**
     * Devuelve la contraseña
     * @return contraseña
     */
     public String getContrasena() {
        return password;
    }
}
