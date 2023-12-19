package persistence;

import exceptions.LecturaIncorrectaFicheroExcepcion;

import java.io.*;
import java.util.ArrayList;

public class CargarYVisualizarListaDePalabras implements CargarYVisualizar {
    // La ruta de los archivos se encuentra en la carpeta ../DATA/ListaDePalabras/
    private String path = "../DATA/ListaDePalabras/";

    /**
     * Visualiza los ficheros que hay en la carpeta ListaDePalabras.
     */
    @Override
    public ArrayList<String> visualizar() {
        ArrayList<String> listaTextos = new ArrayList<>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    listaTextos.add(file.getName());
                }
            }
        }
        return listaTextos;
    }

    /**
     * Carga el contenido del fichero que se le pasa por parámetro.
     *
     * @param nombreArchivo Nombre del archivo a cargar.
     * @return Contenido del archivo como una cadena.
     * @throws LecturaIncorrectaFicheroExcepcion Si ocurre un error durante la lectura del fichero.
     */
    @Override
    public String cargar(String nombreArchivo) throws LecturaIncorrectaFicheroExcepcion {
        StringBuilder contenido = new StringBuilder();
        String filePath = path + nombreArchivo;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            // Lanza tu excepción personalizada
            throw new LecturaIncorrectaFicheroExcepcion("Error durante la lectura del archivo: " + e.getMessage());
        }

        return contenido.toString();
    }
}
