package edu.upc.prop.cluster23.domain;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

public class TestCjtTeclados {

    @Test
    public void testConstructionInsertionDeletion() {
        CjtTeclados tecs = new CjtTeclados();

        assertEquals(0, tecs.totalTeclados());

        char[][] mat1 = new char[1][1];
        mat1[0][0] = 'a';
        char[][] mat2 = new char[1][1];
        mat2[0][0] = 'b';

        tecs.crearTeclado("Name", mat1);
        tecs.crearTeclado("Name2", mat2);

        assertEquals(2, tecs.totalTeclados());

        assertTrue(tecs.existeTeclado("Name"));
        assertTrue(tecs.existeTeclado("Name2"));

        tecs.eliminarTeclado("Name2");

        assertEquals(1, tecs.totalTeclados());

        assertFalse(tecs.existeTeclado("Name2"));

        mat2[0][0] = 'a';

        assertArrayEquals(mat2, tecs.getTeclado("Name"));

        mat2[0][0] = 'b';

        tecs.setDistribucionTeclado("Name", mat2);

        assertNotSame(mat1, tecs.getTeclado("Name"));
    }

    @Test
    public void testChangedKeyboard() {
        CjtTeclados tecs = new CjtTeclados();

        char[][] mat1 = new char[2][2];
        mat1[0][0] = 'a';
        mat1[0][1] = 'b';
        mat1[1][0] = 'c';
        mat1[1][1] = 'd';

        char[][] mat2 = new char[2][2];
        mat2[0][0] = 'a';
        mat2[0][1] = 'b';
        mat2[1][0] = 'c';
        mat2[1][1] = 'd';

        tecs.crearTeclado("Name", mat1);

        tecs.intercambiarTeclasTeclado("Name", 0,0,1,1);

        assertNotSame(mat2, tecs.getTeclado("Name"));

        mat2[0][0] = 'd';
        mat2[1][1] = 'a';

        tecs.setDistribucionTeclado("Name", mat2);

        assertArrayEquals(mat1, tecs.getTeclado("Name"));
    }
    
    @Test
    public void getNombreTeclados(){
        CjtTeclados tecs = new CjtTeclados();

        assertEquals(0, tecs.getNombreTeclados().totalTeclados());

        tecs.crearTeclado("Name", new char[1][1]);
        tecs.crearTeclado("Name2", new char[1][1]);        
        tecs.crearTeclado("Name3", new char[1][1]);
        tecs.crearTeclado("Name4", new char[1][1]);

        ArrayList<String> nombres = new ArrayList<String>();
        nombres.add("Name");
        nombres.add("Name2");
        nombres.add("Name3");
        nombres.add("Name4");

        ArrayList<String> nombres2 = tecs.getNombreTeclados();
        for (int i = 0; i < nombres.totalTeclados(); i++) {
            assertTrue(nombres.contains(nombres2.get(i)));
        }
    }
    
    @Test
    public void testgetTecladoObjeto() {
        String name="Name";
        char[][] mat1 = new char[1][1];
        mat1[0][0] = 'a';

        CjtTeclados tecs = new CjtTeclados();
        tecs.crearTeclado(name, mat1);

        Teclado aux = tecs.getTecladoObjeto(name);
        assertEquals(aux.getNombre(),name);
        assertArrayEquals(aux.getTeclado(),mat1);
    }
}
