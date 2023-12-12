package domain;

import java.util.*;

/**
 * Clase CalculadoraBigramasConFrecuencia
 * Representa una clase para calcular la frecuencia de bigramas en un texto,
 * dado un alfabeto y un conjunto de palabras con su frecuencia.
 * 
 * @author Jianing Xu (jianing.xu@estudiantat.upc.edu)
 */
public class CalculadoraBigramasConFrecuencia {

    private HashMap<String, Integer> bigramasConFrecuencia;

    /**
     * Metodo para ejecutar el calculo de frecuencia de bigramas.
     * 
     * @param alf      Alfabeto a utilizar
     * @param palabras Palabras con su frecuencia
     * @param texto    Texto a analizar
     * @return Un mapa que contiene los bigramas y su frecuencia
     */
    public HashMap<String, Integer> ejecutar(Alfabeto alf, PalabrasConFrecuencia palabras, Texto texto) {
        inicializarBigramas(alf.getCaracteres());
        contarBigramasListaPalabras(palabras.getMap());
        contarBigramasTexto(texto.getTexto());
        return bigramasConFrecuencia;
    }

    /**
     * Inicializa el mapa de bigramas con frecuencia con todas las combinaciones
     * posibles de bigramas a partir de un alfabeto dado, estableciendo la
     * frecuencia inicial en 0 para cada bigrama.
     * 
     * @param alfChar Lista de caracteres del alfabeto
     */
    private void inicializarBigramas(ArrayList<Character> alfChar) {
        bigramasConFrecuencia = new HashMap<>();
        for (int i = 0; i < alfChar.size(); ++i) {
            for (int j = i + 1; j < alfChar.size(); ++j) {
                bigramasConFrecuencia.put("" + alfChar.get(i) + alfChar.get(j), 0);
            }
        }
    }

    /**
     * Obtiene un bigrama a partir de dos caracteres dados, considerando tambien
     * su reverso si el bigrama no existe en el mapa.
     * 
     * @param caracter1 Primer caracter del bigrama
     * @param caracter2 Segundo caracter del bigrama
     * @return El bigrama formado por los dos caracteres
     */
    private String obtenerBigramaParaMap(char caracter1, char caracter2) {
        StringBuilder bigrama = new StringBuilder("" + caracter1 + caracter2);
        if (!bigramasConFrecuencia.containsKey(bigrama.toString())) {
            bigrama.reverse();
        }
        return bigrama.toString();
    }

    /**
     * Cuenta la frecuencia de los bigramas en un texto dado y actualiza el mapa
     * de bigramas con la frecuencia correspondiente.
     * 
     * @param texto Texto en el que se contaran los bigramas
     */
    private void contarBigramasTexto(String texto) {
        for (int i = 0; i < texto.length() - 1; ++i) {
            char caracter1 = texto.charAt(i);
            char caracter2 = texto.charAt(i + 1);
            String bigrama = obtenerBigramaParaMap(caracter1, caracter2);
            if (bigramasConFrecuencia.containsKey(bigrama))
                bigramasConFrecuencia.put(bigrama, bigramasConFrecuencia.get(bigrama) + 1);
        }
    }

    /**
     * Cuenta la frecuencia de los bigramas en un conjunto de palabras con su
     * frecuencia y actualiza el mapa de bigramas con la frecuencia correspondiente.
     * 
     * @param ListaPal Mapa que contiene palabras y su frecuencia
     */
    private void contarBigramasListaPalabras(Map<String, Integer> ListaPal) {
        for (Map.Entry<String, Integer> entry : ListaPal.entrySet()) {
            String palabra = entry.getKey();
            Integer frecuencia = entry.getValue();
            for (int j = 0; j < palabra.length() - 1; ++j) {
                char caracter1 = palabra.charAt(j);
                char caracter2 = palabra.charAt(j + 1);
                String bigrama = obtenerBigramaParaMap(caracter1, caracter2);
                if (bigramasConFrecuencia.containsKey(bigrama))
                    bigramasConFrecuencia.put(bigrama, bigramasConFrecuencia.get(bigrama) + frecuencia);
            }
        }
    }
}
