package domain;

/**
 * Interface Algoritmo
 * 
 * @author Rubén Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 */
public interface Algoritmo {
    public char[][] generarDistribucion(Alfabeto alf, PalabrasConFrecuencia palabras, Texto texto);
}
