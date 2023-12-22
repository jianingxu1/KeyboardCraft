package domain;

import java.util.Map;

/**
 * Interfaz Algoritmo
 * 
 * Esta interfaz define el contrato que deben seguir todos los algoritmos de generación de distribuciones.
 * Los algoritmos implementarán el método generarDistribucion para crear una matriz de caracteres basada en
 * el alfabeto y los bigramas con frecuencia proporcionados.
 * 
 * @author Ruben Catalan Rua (ruben.catalan@estudiantat.upc.edu)
 */
public interface Algoritmo {
    /**
     * Genera una distribución de caracteres.
     * 
     * @param alfabeto El alfabeto sobre el cual se generará la distribución.
     * @param bigramasConFrecuencia Un mapa que contiene bigramas y sus frecuencias.
     * @return Una matriz de caracteres que representa la distribución generada.
     */
    public Character[][] generarDistribucion(Alfabeto alfabeto, Map<String, Integer> bigramasConFrecuencia);
}
