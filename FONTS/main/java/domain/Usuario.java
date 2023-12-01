package domain;

/**
 * Clase Usuario
 * Representa un usuario con su nombre de usuario y contraseña.
 *
 * @author Rubén Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 */
public class Usuario {

    private String username;
    private String password;

    public Usuario(String username, String password) {
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
