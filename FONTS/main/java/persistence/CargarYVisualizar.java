package persistence;

import exceptions.LecturaIncorrectaFicheroExcepcion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class CarfarYVisualizar
 * 
 * @author Momin Miah Begum
 */
public class CargarYVisualizar {
    // Este método retorna en formato String lo que hay en el fichero
    // y puede lanzar una excepción LecturaIncorrectaFicheroExcepcion
    public String cargar(String pathFile) throws LecturaIncorrectaFicheroExcepcion{

        StringBuilder contenido = new StringBuilder();
        String filePath = pathFile;

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

    // Este método visualiza los ficheros que hay en una carpeta (Textos, Listas De Palabras)
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
