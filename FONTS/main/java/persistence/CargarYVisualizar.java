package persistence;

import exceptions.LecturaIncorrectaFicheroExcepcion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase CargarYVisualizar
 * 
 * Esta clase proporciona métodos para cargar el contenido de un archivo y visualizar
 * los archivos en una carpeta.
 * 
 * @author Momin Miah Begum
 */
public class CargarYVisualizar {

    /**
     * Carga el contenido de un archivo en formato String.
     * 
     * @param pathFile Ruta del archivo a cargar.
     * @return Contenido del archivo en formato String.
     * @throws LecturaIncorrectaFicheroExcepcion Si ocurre un error durante la lectura del archivo.
     */
    public String cargar(String pathFile) throws LecturaIncorrectaFicheroExcepcion {

        StringBuilder contenido = new StringBuilder();
        String filePath = pathFile;
    
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            boolean firstLine = true;  // Para evitar agregar un salto de línea antes de la primera línea
            while ((linea = br.readLine()) != null) {
                if (!firstLine) {
                    contenido.append("\n");
                } else {
                    firstLine = false;
                }
                contenido.append(linea);
            }
        } catch (IOException e) {
            // Lanza tu excepción personalizada
            throw new LecturaIncorrectaFicheroExcepcion("Error durante la lectura del archivo: " + e.getMessage());
        }
    
        return contenido.toString();
    }
    
    /**
     * Visualiza los archivos en una carpeta y devuelve sus nombres en una lista.
     * 
     * @param pathFolder Ruta de la carpeta a visualizar.
     * @return Lista de nombres de archivos en la carpeta.
     */
    public ArrayList<String> visualizar(String pathFolder){

        ArrayList<String> listaTextos = new ArrayList<>();
        File folder = new File(pathFolder);
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
}
