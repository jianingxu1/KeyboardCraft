package edu.upc.prop.cluster23.domain;

/**
 * Interface Algoritmo
 * @author ruben.catalan(ruben.catalan@estudiantat.upc.edu)
 */

public interface Algoritmo {
    public char[][] generarDistribucion(Alfabeto alf, PalabrasConFrecuencia palabras, Texto texto);
}
