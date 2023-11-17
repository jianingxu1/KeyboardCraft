package edu.upc.prop.cluster23.domain;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

import java.util.*;

import edu.upc.prop.cluster23.domain.*;

public class TestAlgoritmoQAP {
    @Test
    public void testGenerarDistribucion() {
        AlgoritmoQAP alg = new AlgoritmoQAP();
        Alfabeto alf = new Alfabeto("ingles", "abcdefghi");
        PalabrasConFrecuencia pal = new PalabrasConFrecuencia("ab 100 ac 90 ad 80 ae 70 af 50 bc 200 hi 200");
        Texto texto = new Texto();
        // char[][] distribucion = alg.generarDistribucion(alf, pal, texto);

        // System.out.println("coste: " + alg.getMejorCosteTotal());
        // for (int i = 0; i < distribucion.length; ++i) {
        //     for (int j = 0; j < distribucion[i].length; ++j) {
        //         System.out.print(distribucion[i][j]);
        //     }
        //     System.out.println();
        // }
    }

    @Test
    public void testCalcularAsignacionOptima() {
        // Las instancias de QAP resueltas han sido extraidas de:
        // https://coral.ise.lehigh.edu/data-sets/qaplib/qaplib-problem-instances-and-solutions/

        // Nombre de instancia resuelta: Chr12a 
        int[][] frecuencias = {
            {0, 90, 10, 23, 43, 0, 0, 0, 0, 0, 0, 0},
            {90, 0, 0, 0, 0, 88, 0, 0, 0, 0, 0, 0},
            {10, 0, 0, 0, 0, 0, 26, 16, 0, 0, 0, 0},
            {23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {43, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 88, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 26, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 16, 0, 0, 0, 0, 0, 0, 96, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 29, 0},
            {0, 0, 0, 0, 0, 0, 0, 96, 0, 0, 0, 37},
            {0, 0, 0, 0, 0, 0, 0, 0, 29, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 37, 0, 0}
        };
        double[][] distancias = {
            {0.0, 36.0, 54.0, 26.0, 59.0, 72.0, 9.0, 34.0, 79.0, 17.0, 46.0, 95.0},
            {36.0, 0.0, 73.0, 35.0, 90.0, 58.0, 30.0, 78.0, 35.0, 44.0, 79.0, 36.0},
            {54.0, 73.0, 0.0, 21.0, 10.0, 97.0, 58.0, 66.0, 69.0, 61.0, 54.0, 63.0},
            {26.0, 35.0, 21.0, 0.0, 93.0, 12.0, 46.0, 40.0, 37.0, 48.0, 68.0, 85.0},
            {59.0, 90.0, 10.0, 93.0, 0.0, 64.0, 5.0, 29.0, 76.0, 16.0, 5.0, 76.0},
            {72.0, 58.0, 97.0, 12.0, 64.0, 0.0, 96.0, 55.0, 38.0, 54.0, 0.0, 34.0},
            {9.0, 30.0, 58.0, 46.0, 5.0, 96.0, 0.0, 83.0, 35.0, 11.0, 56.0, 37.0},
            {34.0, 78.0, 66.0, 40.0, 29.0, 55.0, 83.0, 0.0, 44.0, 12.0, 15.0, 80.0},
            {79.0, 35.0, 69.0, 37.0, 76.0, 38.0, 35.0, 44.0, 0.0, 64.0, 39.0, 33.0},
            {17.0, 44.0, 61.0, 48.0, 16.0, 54.0, 11.0, 12.0, 64.0, 0.0, 70.0, 86.0},
            {46.0, 79.0, 54.0, 68.0, 5.0, 0.0, 56.0, 15.0, 39.0, 70.0, 0.0, 18.0},
            {95.0, 36.0, 63.0, 85.0, 76.0, 34.0, 37.0, 80.0, 33.0, 86.0, 18.0, 0.0}
        };

        // Nombre de instancia resuelta: Had12
        int[][] frecuencias1 = {
            {0, 1, 2, 2, 3, 4, 4, 5, 3, 5, 6, 7},
            {1, 0, 1, 1, 2, 3, 3, 4, 2, 4, 5, 6},
            {2, 1, 0, 2, 1, 2, 2, 3, 1, 3, 4, 5},
            {2, 1, 2, 0, 1, 2, 2, 3, 3, 3, 4, 5},
            {3, 2, 1, 1, 0, 1, 1, 2, 2, 2, 3, 4},
            {4, 3, 2, 2, 1, 0, 2, 3, 3, 1, 2, 3},
            {4, 3, 2, 2, 1, 2, 0, 1, 3, 1, 2, 3},
            {5, 4, 3, 3, 2, 3, 1, 0, 4, 2, 1, 2},
            {3, 2, 1, 3, 2, 3, 3, 4, 0, 4, 5, 6},
            {5, 4, 3, 3, 2, 1, 1, 2, 4, 0, 1, 2},
            {6, 5, 4, 4, 3, 2, 2, 1, 5, 1, 0, 1},
            {7, 6, 5, 5, 4, 3, 3, 2, 6, 2, 1, 0}
        };
        double[][] distancias1 = {
            {0, 3, 4, 6, 8, 5, 6, 6, 5, 1, 4, 6},
            {3, 0, 6, 3, 7, 9, 9, 2, 2, 7, 4, 7},
            {4, 6, 0, 2, 6, 4, 4, 4, 2, 6, 3, 6},
            {6, 3, 2, 0, 5, 5, 3, 3, 9, 4, 3, 6},
            {8, 7, 6, 5, 0, 4, 3, 4, 5, 7, 6, 7},
            {5, 9, 4, 5, 4, 0, 8, 5, 5, 5, 7, 5},
            {6, 9, 4, 3, 3, 8, 0, 6, 8, 4, 6, 7},
            {6, 2, 4, 3, 4, 5, 6, 0, 1, 5, 5, 3},
            {5, 2, 2, 9, 5, 5, 8, 1, 0, 4, 5, 2},
            {1, 7, 6, 4, 7, 5, 4, 5, 4, 0, 7, 7},
            {4, 4, 3, 3, 6, 7, 6, 5, 5, 7, 0, 9},
            {6, 7, 6, 6, 7, 5, 7, 3, 2, 7, 9, 0}
        };
        
        AlgoritmoQAP alg = new AlgoritmoQAP();
        // double coste = alg.calcularAsignacionOptima(frecuencias, distancias);
        // assertEquals(9552.0, coste, 0.001); // Tiempo: 3s

        // coste = alg.calcularAsignacionOptima(frecuencias1, distancias1);
        // assertEquals(1652.0, coste, 0.001); //  Tiempo: 3s
    }
}