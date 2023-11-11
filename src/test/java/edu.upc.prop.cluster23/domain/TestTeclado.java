package edu.upc.prop.cluster23.domain;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;

public class TestTeclado {

    @Test
    public void testConstructor() {
        String a = "name";
        char[][] matrix = new char[1][2];
        matrix[0][0] = 'a';
        matrix[0][1] = 'b';

        Teclado tec = new Teclado(a,matrix);
        assertEquals("name", tec.getNombre());
        assertArrayEquals(matrix, tec.getTeclado());
    }

    @Test
    public void testCambiarDistribucion() {
        String a = "name";
        char[][] matrix = new char[1][2];
        matrix[0][0] = 'a';
        matrix[0][1] = 'b';

        Teclado tec = new Teclado(a,matrix);

        char[][] matrix2 = new char[2][2];

        matrix2[0][0] = 'a';
        matrix2[0][1] = 'b';
        matrix2[1][0] = 'c';
        matrix2[1][1] = 'd';

        tec.setDistribucion(matrix2);
        assertNotSame(matrix, tec.getTeclado());
        assertArrayEquals(matrix2, tec.getTeclado());
    }

    @Test
    public void testSwapDistribucion() {
        String a = "name";
        char[][] matrix = new char[1][2];
        matrix[0][0] = 'a';
        matrix[0][1] = 'b';

        Teclado tec = new Teclado(a, matrix);

        tec.intercambiarTeclas(0, 0, 0, 1);

        char[][] matrix2 = new char[1][2];
        matrix2[0][0] = 'n';
        matrix2[0][1] = 'a';

        assertFalse(Arrays.equals(matrix2, tec.getTeclado()));

        matrix2[0][0] = 'b';
        matrix2[0][1] = 'a';

        assertArrayEquals(matrix2, tec.getTeclado());
    }
}