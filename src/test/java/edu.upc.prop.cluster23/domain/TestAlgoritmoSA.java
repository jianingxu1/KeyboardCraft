package edu.upc.prop.cluster23.domain;

import org.junit.Assert;
import org.junit.Test;

public class TestAlgoritmoSA {

    @Test
    public void testAlgoritmoSA() {
        String input = "phone 1 keyboard 40 home 1000";
        PalabrasConFrecuencia palabras = new PalabrasConFrecuencia(input);

        String input2 = "abcdedfghijklmnopqrstuvwxyz";
        Texto texto = new Texto(input2);

        String a = "ingles";
        String alf = "abcdefghijklmnopqrstuvwxyz";

        Alfabeto alfabeto = new Alfabeto(a,alf);

        AlgoritmoSA alg = new AlgoritmoSA();

        char[][] dist = alg.generarDistribucion(alfabeto, palabras, texto);

        for (int i = 0; i < dist.length; ++i) {
            for (int j = 0; j < dist[0].length; ++j) {
                System.out.print(dist[i][j]);
            }
            System.out.println();
        }
    }
}
