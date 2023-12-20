package persistence;

import exceptions.EscrituraIncorrectaFicheroExcepcion;
import exceptions.LecturaIncorrectaFicheroExcepcion;

import java.io.*;
import java.util.*;

/**
 * Clase que gestiona la escritura, lectura y eliminacion de teclados en ficheros.
 */
public class GestorFicherosTeclados {

    /**
     * Constructor de la clase GestorFicherosTeclados.
     */
    public GestorFicherosTeclados() {
    }

    /**
     * Guarda los teclados en un fichero asociado a un usuario.
     *
     * @param username    Nombre de usuario.
     * @param cjtTeclados HashMap que contiene los teclados a guardar.
     * @throws EscrituraIncorrectaFicheroExcepcion Si ocurre un error al escribir en el fichero.
     */
    public void guardarTeclados(String username, HashMap<String, Character[][]> cjtTeclados)
            throws EscrituraIncorrectaFicheroExcepcion {
        if (username.trim().isEmpty())
            return;
        try {
            String path = "../DATA/" + username + "Teclados" + ".prop";
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, false));
            for (Map.Entry<String, Character[][]> entry : cjtTeclados.entrySet()) {
                String nombreTeclado = entry.getKey();
                Character[][] distribucion = entry.getValue();
                writer.write(nombreTeclado + 'º' + convertirDistribucionAString(distribucion) + "\n");
            }
            writer.close();
        } catch (Exception e) {
            throw new EscrituraIncorrectaFicheroExcepcion(
                    "Error al escribir en el fichero de teclados " + e.getMessage());
        }
    }

    /**
     * Carga los teclados desde el fichero asociado a un usuario.
     *
     * @param username Nombre de usuario.
     * @return HashMap que contiene los teclados cargados.
     * @throws LecturaIncorrectaFicheroExcepcion Si ocurre un error al leer el fichero.
     */
    public HashMap<String, Character[][]> cargarTeclados(String username) throws LecturaIncorrectaFicheroExcepcion {
        HashMap<String, Character[][]> cjtTeclados = new HashMap<>();
        String path = "../DATA/" + username + "Teclados" + ".prop";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String elementos[] = linea.split("º");
                String nombreTeclado = elementos[0];
                Character[][] distribucion = convertirStringADistribucion(elementos[1]);
                cjtTeclados.put(nombreTeclado, distribucion);
            }
            reader.close();
        } catch (Exception e) {
            File file = new File(path);
            try {
                file.createNewFile();
            } catch (Exception ex) {
                throw new LecturaIncorrectaFicheroExcepcion("Error al crear el fichero de teclados " + ex.getMessage());
            }
        }
        return cjtTeclados;
    }

    /**
     * Elimina los teclados asociados a un usuario.
     *
     * @param userName Nombre de usuario.
     */
    public void eliminarTecladosDeUsuario(String userName) {
        File fileTeclados = new File("../DATA/" + userName + "Teclados" + ".prop");
        fileTeclados.delete();
    }

    /**
     * Convierte una cadena de caracteres en una distribución de teclado.
     *
     * @param distribucionString Cadena de caracteres que representa la distribución de teclado.
     * @return Distribución de teclado en forma de matriz de caracteres.
     */
    private static Character[][] convertirStringADistribucion(String distribucionString) {
        Character[][] distribucion = new Character[3][10];
        int i = 0;
        int j = 0;
        for (int k = 0; k < distribucionString.length(); ++k) {
            if (distribucionString.charAt(k) == '◘') {
                i++;
                j = 0;
            } else {
                distribucion[i][j] = distribucionString.charAt(k);
                j++;
            }
        }
        return distribucion;
    }

    /**
     * Convierte una distribución de teclado en una cadena de caracteres.
     *
     * @param distribucion Distribución de teclado en forma de matriz de caracteres.
     * @return Cadena de caracteres que representa la distribución de teclado.
     */
    private String convertirDistribucionAString(Character[][] distribucion) {
        StringBuilder s = new StringBuilder();
        // guarda la distribucion por filas
        for (Character[] fila : distribucion) {
            for (int i = 0; i < fila.length; ++i) {
                if (fila[i] == null) s.append(" ");
                else s.append(fila[i]);
            }
            s.append("◘");
        }
        return s.toString();
    }
}
