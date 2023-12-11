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

    private String username;
    private String password;

    public Usuario(String username, String password) throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion{
        if (username.trim().isEmpty())
			throw new NombreUsuarioNoValidoExcepcion("El nombre del usuario no puede ser vacío.");
		else if (password.trim().isEmpty())
			throw new ContrasenaNoValidaExcepcion("La contraseña no puede ser vacia.");
		else if (password.length() < 8)
			throw new ContrasenaNoValidaExcepcion("La contraseña debe tener al menos 8 caracteres.");
        this.username = username;
        this.password = password;
    }

    public void modificaContraseña(String newPass) {
        this.password = newPass;
    }

    public String getContraseña() {
        return password;
    }

    public String getNombre() {
        return username;
    }
}
