package persistence;

import domain.CjtTeclados;
import exceptions.EscrituraIncorrectaFicheroExcepcion;
import exceptions.LecturaIncorrectaFicheroExcepcion;

import java.io.*;

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
     * @param userName El nombre del usuario que quiere guardar los teclados
     */
    public void guardarTeclados(CjtTeclados cjtTeclados, String userName) throws EscrituraIncorrectaFicheroExcepcion {
        if (userName.trim().isEmpty()) return;
        try {
            String path = "../DATA/" + userName + "Teclados" + ".prop";
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, false));
            for (String nombreTeclado : cjtTeclados.getNombreTeclados()) {
                writer.write(nombreTeclado + 'º' + cjtTeclados.getDistribucioStringSimplificado(nombreTeclado) + "\n");
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
    public CjtTeclados cargarTeclados(String userName) throws LecturaIncorrectaFicheroExcepcion {
        CjtTeclados cjtTeclados = CjtTeclados.getInstance();
        String path = "../DATA/" + userName + "Teclados" + ".prop";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String elementos[] = linea.split("º");
                String nombreTeclado = elementos[0];
                char[][] distribucion = convertirStringADistribucion(elementos[1]);
                cjtTeclados.crearTeclado(nombreTeclado, distribucion);
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
    private static char[][] convertirStringADistribucion(String distribucionString) {
        char[][] distribucion = new char[3][10];
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
}
