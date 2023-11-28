package domain;

import java.util.HashMap;

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

    public void crearUsuario(String user, String pass) {
        Usuario usuario = new Usuario(user, pass);
        conjunto.put(user, usuario);
    }

    public void eliminarUsuario(String user) {
        conjunto.remove(user);
    }

    public void modificarUsuario(String user, String newPass) {
        conjunto.get(user).modifyPassword(newPass);
    }

    public boolean correctPass(String user, String pass) {
        return pass == conjunto.get(user).getPassword();
    }

    public boolean userExists(String user) {
        return conjunto.containsKey(user);
    }
}
