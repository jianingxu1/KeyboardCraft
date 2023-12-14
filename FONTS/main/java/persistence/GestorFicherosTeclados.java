package persistence;

import exceptions.EscrituraIncorrectaFicheroExcepcion;
import exceptions.LecturaIncorrectaFicheroExcepcion;

import java.io.*;
import java.util.*;

public class GestorFicherosTeclados {

    /*
     * Constructora de la clase CtrlFicherosTeclados
     */

    public GestorFicherosTeclados() {
    }

    /*
     * Guarda los teclados en el fichero de teclados
     * @throws EscrituraIncorrectaFicheroExcepcion Si el fichero de teclados no se ha podido escribir correctamente
     * @param cjtTeclados El conjunto de teclados que se quiere guardar
     * @param username El nombre del usuario que quiere guardar los teclados
     */
    public void guardarTeclados(String username, HashMap<String, Character[][]> cjtTeclados) throws EscrituraIncorrectaFicheroExcepcion {
        if (username.trim().isEmpty()) return;
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
            throw new EscrituraIncorrectaFicheroExcepcion("Error al escribir en el fichero de teclados " + e.getMessage());
        }
    }

    /*
     * Carga los teclados del fichero de teclados
     * @return El conjunto de teclados que se ha cargado
     * @throws LecturaIncorrectaFicheroExcepcion Si el fichero de teclados no se ha podido leer correctamente
     * @param userName El nombre del usuario que quiere cargar los teclados
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

    /*
     * Elimina el fichero de teclados de un usuario
     * @param userName El nombre del usuario que quiere eliminar sus teclados
     */
    public void eliminarTecladosDeUsuario(String userName) {
        File fileTeclados = new File("../DATA/" + userName + "Teclados" + ".prop");
        fileTeclados.delete();
    }

    /*
     * Convierte un string en una matriz de caracteres
     * @param distribucionString El string que contiene la distribucion
     * @return La matriz de caracteres que contiene la distribucion
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


    private String convertirDistribucionAString(Character[][] distribucion) {
        StringBuilder s = new StringBuilder();
        //guarda la distribucion por filas
        for (Character[] fila : distribucion) {
            for (char caracter : fila) {
                s.append(caracter);
            }
            s.append("◘");
        }
        return s.toString();
    }
}
