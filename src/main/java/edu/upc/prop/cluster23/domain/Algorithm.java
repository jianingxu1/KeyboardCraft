package edu.upc.prop.cluster23.domain;
import java.util.HashMap;

public interface Algorithm {
    char[][] generarDistribucion(HashMap<String,Integer> freqs, int total);
}
