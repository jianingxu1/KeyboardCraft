package domain;

import java.util.Map;

/**
 * Interface Algoritmo
 * 
 * @author Rubén Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 */
public interface Algoritmo {
    public char[][] generarDistribucion(Alfabeto alfabeto, Map<String, Integer> bigramasConFrecuencia);
}
