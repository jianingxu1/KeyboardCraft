package domain;

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

    private HashMap<String, Usuario> conjunto;

    public CjtUsuarios() {
        conjunto = new HashMap<String, Usuario>();
    }

    private static CjtUsuarios singletonObject;

    public static CjtUsuarios getInstance() {
        if (singletonObject == null)
            singletonObject = new CjtUsuarios() {
            };
        return singletonObject;
    }

    public void añadirUsuario(String user, String pass) throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion, EscrituraIncorrectaFicheroExcepcion {
        if (existeUsuario(user))
            throw new NombreUsuarioNoValidoExcepcion("El usuario " + user + " ya existe.");
        Usuario usuario = new Usuario(user, pass);
        conjunto.put(user, usuario);
    }

    public void eliminarUsuario(String user) throws NombreUsuarioNoValidoExcepcion {
		if (user.trim().isEmpty())
			throw new NombreUsuarioNoValidoExcepcion("El nombre del usuario no puede ser vacío.");
		else if (!existeUsuario(user))
			throw new NombreUsuarioNoValidoExcepcion("El usuario " + user + " no existe.");
        conjunto.remove(user);
    }

    public void modificarContrasenaUsuario(String user, String oldPass, String newPass) throws NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion{
		if (user.trim().isEmpty())
			throw new NombreUsuarioNoValidoExcepcion("El nombre del usuario no puede ser vacío.");
		else if (!existeUsuario(user))
			throw new NombreUsuarioNoValidoExcepcion("El usuario " + user + " no existe.");
		else if(!correctPass(user, oldPass)){
			throw new ContrasenaNoValidaExcepcion("La contrasena actual no es correcta.");
		}
        conjunto.get(user).modificaContraseña(newPass);
    }

    public boolean correctPass(String user, String pass) throws NombreUsuarioNoValidoExcepcion,ContrasenaNoValidaExcepcion {
        if (user.trim().isEmpty())
            throw new NombreUsuarioNoValidoExcepcion("El nombre del usuario no puede ser vacío.");
        else if (!existeUsuario(user))
            throw new NombreUsuarioNoValidoExcepcion("El usuario " + user + " no existe.");
        else if (pass.trim().isEmpty())
            throw new ContrasenaNoValidaExcepcion("La contraseña no puede ser vacia.");
        else if (pass.length() < 8)
            throw new ContrasenaNoValidaExcepcion("La contraseña debe tener al menos 8 caracteres.");
        return conjunto.get(user).getContraseña().equals(pass);
    }

    public boolean existeUsuario(String user) {
        return conjunto.containsKey(user);
    }

    public String getNombreUsuario(String user) {
        return conjunto.get(user).getNombre();
    }

    public String getContraseñaUsuario(String user) {
        return conjunto.get(user).getContraseña();
    }

    public String[] getNombreUsuarios() {
        String[] usuarios = new String[conjunto.size()];
        int i = 0;
        for (String user : conjunto.keySet()) {
            usuarios[i] = user;
            ++i;
        }
        return usuarios;
    }

    public void clearCjtUsuarios() {
        conjunto.clear();
    }
}
