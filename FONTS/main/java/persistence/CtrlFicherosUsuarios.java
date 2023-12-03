package persistence;

import domain.CjtUsuarios;
import exceptions.EscrituraIncorrectaFicheroExcepcion;
import exceptions.LecturaIncorrectaFicheroExcepcion;

import java.io.*;

public class CtrlFicherosUsuarios {

    public void guardarUsuarios(CjtUsuarios cjtUsuarios) throws EscrituraIncorrectaFicheroExcepcion {
        try {
            String path = "../DATA/usuarios.prop";
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, false));

            for (String nombreUsuario : cjtUsuarios.getNombreUsuarios()) {
                writer.write(nombreUsuario + 'º' + cjtUsuarios.getContraseñaUsuario(nombreUsuario) + "\n");
            }
            writer.close();
        } catch (Exception e) {
            throw new EscrituraIncorrectaFicheroExcepcion("Error al escribir en el fichero de usuarios " + e.getMessage());
        }
    }

    public CjtUsuarios cargarUsuarios() throws LecturaIncorrectaFicheroExcepcion {
        CjtUsuarios cjtUsuarios = CjtUsuarios.getInstance();
        String path = "../DATA/usuarios.prop";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] elementos = linea.split("º");
                String nombreUsuario = elementos[0];
                String contrasena = elementos[1];
                cjtUsuarios.añadirUsuario(nombreUsuario, contrasena);
            }
            reader.close();
        } catch (Exception e) {
            File file = new File(path);
            try {
                file.createNewFile();
            } catch (Exception ex) {
                throw new LecturaIncorrectaFicheroExcepcion("Error al crear el fichero de usuarios " + ex.getMessage());
            }
            throw new LecturaIncorrectaFicheroExcepcion("Error al leer el fichero de usuarios " + e.getMessage() +
                    "\nSe ha creado un fichero nuevo.");
        }
        return cjtUsuarios;
    }

    public void eliminarUsuario(String nombreUsuario) {
        File fileTeclados = new File("../DATA/" + nombreUsuario + "Teclados" + ".prop");
        fileTeclados.delete();

        File fileAlfabetos = new File("../DATA/" + nombreUsuario + "Alfabetos" + ".prop");
        fileAlfabetos.delete();
    }
}
