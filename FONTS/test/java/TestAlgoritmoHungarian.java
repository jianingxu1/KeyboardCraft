import static org.junit.Assert.*;
import org.junit.Test;

import domain.AlgoritmoHungarian;

/**
 * Clase TestAlgoritmoHungarian
 * Representa los tests de un algoritmo hungarian en concreto con sus matrices.
 * 
 * @author Jianing Xu (jianing.xu@estudiantat.upc.edu)
 */

public class TestAlgoritmoHungarian {
    @Test
    public void testEjecutar() {
        double[][] M1 = {
            { 21, 34, 31, 43 },
            { 20, 35, 32, 44 },
            { 20, 34, 33, 45 },
            { 21, 34, 31, 43 }
        };
        double solucion1 = 128.0;

        double[][] M2 = {
            { 4, 2, 5, 7 },
            { 8, 3, 10, 8 },
            { 12, 5, 4, 5 },
            { 6, 3, 7, 14 }
        };
        double solucion2 = 19.0;

        double[][] M3 = {
            { 93, 48, 66, 73 },
            { 74, 45, 57, 27 },
            { 98, 97, 36, 38 },
            { 41, 57, 52, 54 }
        };
        double solucion3 = 152.0;

        AlgoritmoHungarian alg = new AlgoritmoHungarian();
        double delta = 0.001;
        
        assertEquals(solucion1, alg.ejecutar(M1), delta);
        assertEquals(solucion2, alg.ejecutar(M2), delta);
        assertEquals(solucion3, alg.ejecutar(M3), delta);
    }
}
