package persistence;

import exceptions.EscrituraIncorrectaFicheroExcepcion;
import exceptions.LecturaIncorrectaFicheroExcepcion;

import java.io.*;
import java.util.*;

public class GestorFicherosUsuarios implements GestorEstrategia<String, String> {

    /*
     * Constructora de la clase GestorFicherosUsuarios
     */
    public GestorFicherosUsuarios() {
    }

    /*
     * Guarda los usuarios en el fichero de usuarios
     * @throws EscrituraIncorrectaFicheroExcepcion Si el fichero de usuarios no se ha podido escribir correctamente
     * @param cjtUsuarios El conjunto de usuarios que se quiere guardar
     */
    @Override
    public void guardar(String useless, HashMap<String, String> cjtUsuarios) throws EscrituraIncorrectaFicheroExcepcion {
        try {   
            String path = "../DATA/usuarios.prop";
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, false));
            for (Map.Entry<String, String> entry : cjtUsuarios.entrySet()) {
                String nombreUsuario = entry.getKey();
                String contrasena = entry.getValue();
                writer.write(nombreUsuario + 'º' + contrasena + "\n");
            }
            writer.close();
        } catch (Exception e) {
            throw new EscrituraIncorrectaFicheroExcepcion("Error al escribir en el fichero de usuarios " + e.getMessage());
        }
    }

    /*
     * Carga los usuarios del fichero de usuarios
     * @return El conjunto de usuarios que se ha cargado
     * @throws LecturaIncorrectaFicheroExcepcion Si el fichero de usuarios no se ha podido leer correctamente
     */
    @Override
    public HashMap<String, String> cargar (String useless) throws LecturaIncorrectaFicheroExcepcion {
        HashMap<String, String> cjtUsuarios = new HashMap<>();
        String path = "../DATA/usuarios.prop";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] elementos = linea.split("º");
                String nombreUsuario = elementos[0];
                String contrasena = elementos[1];
                cjtUsuarios.put(nombreUsuario, contrasena);
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

}
