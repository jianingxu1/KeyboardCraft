package edu.upc.prop.cluster23.domain;

import edu.upc.prop.cluster23.exceptions.*;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;

/**
 * Clase TesTeclados
 * Representa los tests de un teclado.
 *
 * @author Rubén Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 */

public class TestTeclado {

    @Test
    public void testConstructor() {
        String a = "name";
        char[][] matrix = new char[1][2];
        matrix[0][0] = 'a';
        matrix[0][1] = 'b';

        Teclado tec = new Teclado(a, matrix);
        assertEquals("name", tec.getNombre());
        assertArrayEquals(matrix, tec.getTeclado());
    }

    @Test
    public void testCambiarDistribucion() {
        String a = "name";
        char[][] matrix = new char[1][2];
        matrix[0][0] = 'a';
        matrix[0][1] = 'b';

        Teclado tec = new Teclado(a, matrix);

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

    @Test
    public void testToString() {
        String a = "name";
        char[][] matrix = new char[2][2];
        matrix[0][0] = 'a';
        matrix[0][1] = 'b';
        matrix[1][0] = 'c';
        matrix[1][1] = 'd';

        Teclado tec = new Teclado(a, matrix);
        String s =
                "---------------------\n" +
                        "|a|b| | | | | | | | |\n" +
                        "---------------------\n" +
                        "|c|d| | | | | | | | |\n" +
                        "---------------------\n" +
                        "|↑| | | space | |←|↩|\n" +
                        "---------------------\n";
        assertEquals(s, tec.toString());
    }

    @Test
    public void testExcepcionesInputNombreTeclado(){
        String a = "name";
        char[][] matrix = new char[1][2];
        matrix[0][0] = 'a';
        matrix[0][1] = 'b';
        try{
            creaTeclado(a, matrix, null);
        }catch(NombreTecladoNoValidoExcepcion e){
            // No debería ocurrir
            assertEquals("El nombre del teclado no puede ser vacio.", e.getMessage());
        }
        try{
            creaTeclado("", matrix, null);
            fail("No se ha lanzado excepcion");
        }catch(NombreTecladoNoValidoExcepcion e){
            assertEquals("El nombre del teclado no puede ser vacio.", e.getMessage());
        }
    }

    private void creaTeclado(String nombreTeclado, char[][] distribucion , Teclado teclado)
            throws  NombreTecladoNoValidoExcepcion {
        // Informacion necesaria para poder crear el teclado

        if (nombreTeclado.trim().isEmpty())
            throw new NombreTecladoNoValidoExcepcion("El nombre del teclado no puede ser vacio.");
        teclado = new Teclado(nombreTeclado, distribucion);
    }

    @Test
    public void testExcepcionesSwapDistribucion(){
        String a = "name";
        char[][] matrix = new char[1][2];
        matrix[0][0] = 'a';
        matrix[0][1] = 'b';
        Teclado tec = new Teclado(a, matrix);
        try{
            intercambiarTeclasTeclado(a, -1, -1, 0, 1, tec);
            fail("No se ha lanzado excepcion");
        }catch(IndiceTeclaFueraDeRangoExcepcion e){
            assertEquals("La posicion de la primera tecla -1 -1 no es correcta", e.getMessage());
        }
        catch(NombreTecladoNoValidoExcepcion e){
            assertEquals("El nombre del teclado no puede ser vacio.", e.getMessage());
        }
        try{
            intercambiarTeclasTeclado(a, 0, 0, -1, -1, tec);
            fail("No se ha lanzado excepcion");
        }catch(IndiceTeclaFueraDeRangoExcepcion e){
            assertEquals("La posicion de la segunda tecla -1 -1 no es correcta", e.getMessage());
        }
        catch(NombreTecladoNoValidoExcepcion e){
            assertEquals("El nombre del teclado no puede ser vacio.", e.getMessage());
        }
        try{
            intercambiarTeclasTeclado(a, -1, -1, -1, -1, tec);
            fail("No se ha lanzado excepcion");
        }catch(IndiceTeclaFueraDeRangoExcepcion e){
            assertEquals("La posicion de la primera tecla -1 -1 no es correcta y la posicion de la segunda tecla -1 -1 no es correcta", e.getMessage());
        }
        catch(NombreTecladoNoValidoExcepcion e){
            assertEquals("El nombre del teclado no puede ser vacio.", e.getMessage());
        }

    }

    public void intercambiarTeclasTeclado(String nombreTeclado, int i1, int j1, int i2, int j2,Teclado teclado)
            throws NombreTecladoNoValidoExcepcion, IndiceTeclaFueraDeRangoExcepcion {
        if (nombreTeclado.trim().isEmpty())
            throw new NombreTecladoNoValidoExcepcion("El nombre del teclado no puede ser vacio.");

        char[][] distribucion = teclado.getTeclado();
        String mensaje = "";

        if (i1 < 0 || i1 >= distribucion.length || j1 < 0 || j1 >= distribucion[0].length || i2 < 0
                || i2 >= distribucion.length || j2 < 0 || j2 >= distribucion[0].length) {
            if (i1 < 0 || i1 >= distribucion.length || j1 < 0 || j1 >= distribucion[0].length) {
                mensaje += "La posicion de la primera tecla " + i1 + " " + j1 + " no es correcta";
            }
            if (i2 < 0 || i2 >= distribucion.length || j2 < 0 || j2 >= distribucion[0].length) {
                if (mensaje.isEmpty())
                    mensaje += "La posicion de la segunda tecla " + i2 + " " + j2 + " no es correcta";
                else {
                    mensaje += " y la posicion de la segunda tecla " + i2 + " " + j2 + " no es correcta";
                }
            }

            throw new IndiceTeclaFueraDeRangoExcepcion(mensaje);
        }

        teclado.intercambiarTeclas( i1, j1, i2, j2);
    }


}