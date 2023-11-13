package edu.upc.prop.cluster23.domain;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class TestCjtAlfabeto {
   @Test
    public void testDefaultConstructor1() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();
        assertTrue(cjtAlfabetos.getAlfabetos().isEmpty());
    }

    @Test
    public void testAnadirAlfabeto() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();
        ArrayList<Character> caracteres = new ArrayList<Character>();
        caracteres.add('a');
        caracteres.add('b');
        caracteres.add('c');
        cjtAlfabetos.anadirAlfabeto("Ingles",caracteres);
        assertFalse(cjtAlfabetos.getAlfabetos().isEmpty());
        assertEquals("Ingles",cjtAlfabetos.getAlfabeto("Ingles").getNombre());
        assertEquals(caracteres,cjtAlfabetos.getAlfabeto("Ingles").getCaracteres());
    }

    @Test
    public void testEliminarAlfabeto() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();
        ArrayList<Character> caracteres = new ArrayList<Character>();
        caracteres.add('a');
        caracteres.add('b');
        caracteres.add('c');
        cjtAlfabetos.anadirAlfabeto("Ingles",caracteres);
        cjtAlfabetos.eliminarAlfabeto("Ingles");
        assertTrue(cjtAlfabetos.getAlfabetos().isEmpty());
    }

    @Test
    public void testCambiarCaracteres() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();
        ArrayList<Character> caracteres = new ArrayList<Character>();
        caracteres.add('a');
        caracteres.add('b');
        caracteres.add('c');
        cjtAlfabetos.anadirAlfabeto("Ingles",caracteres);
        ArrayList<Character> caracteres2 = new ArrayList<Character>();
        caracteres2.add('R');
        caracteres2.add('G');
        caracteres2.add('B');
        cjtAlfabetos.cambiarCaracteres("Ingles",caracteres2);
        assertEquals(caracteres2,cjtAlfabetos.getAlfabeto("Ingles").getCaracteres());
        assertFalse(caracteres == cjtAlfabetos.getAlfabeto("Ingles").getCaracteres());   
    }

    @Test
    public void testCambiarNombre() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();
        ArrayList<Character> caracteres = new ArrayList<Character>();
        caracteres.add('a');
        caracteres.add('b');
        caracteres.add('c');
        cjtAlfabetos.anadirAlfabeto("Ingles",caracteres);
        cjtAlfabetos.cambiarNombre("Ingles","Español");
        assertEquals("Español",cjtAlfabetos.getAlfabeto("Español").getNombre());
        assertFalse("Ingles" == cjtAlfabetos.getAlfabeto("Español").getNombre());
    }

    @Test
    public void testGetAlfabeto() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();
        ArrayList<Character> caracteres = new ArrayList<Character>();
        caracteres.add('a');
        caracteres.add('b');
        caracteres.add('c');
        cjtAlfabetos.anadirAlfabeto("Ingles",caracteres);
        assertEquals("Ingles",cjtAlfabetos.getAlfabeto("Ingles").getNombre());
        assertEquals(caracteres,cjtAlfabetos.getAlfabeto("Ingles").getCaracteres());
    }

    @Test
    public void testGetAlfabetoCaracteres() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();
        ArrayList<Character> caracteres = new ArrayList<Character>();
        caracteres.add('a');
        caracteres.add('b');
        caracteres.add('c');
        cjtAlfabetos.anadirAlfabeto("Ingles",caracteres);
        assertEquals(caracteres,cjtAlfabetos.getAlfabetoCaracteres("Ingles"));
    }

    @Test
    public void testGetAlfabetos() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();
        ArrayList<Character> caracteres = new ArrayList<Character>();
        caracteres.add('a');
        caracteres.add('b');
        caracteres.add('c');
        cjtAlfabetos.anadirAlfabeto("Ingles",caracteres);
        ArrayList<Character> caracteres2 = new ArrayList<Character>();
        caracteres2.add('R');
        caracteres2.add('G');
        caracteres2.add('B');
        cjtAlfabetos.anadirAlfabeto("RGB",caracteres2);

        assertFalse(cjtAlfabetos.getAlfabetos().isEmpty());
        assertEquals("Ingles",cjtAlfabetos.getAlfabeto("Ingles").getNombre());
        assertEquals(caracteres,cjtAlfabetos.getAlfabeto("Ingles").getCaracteres());
        assertEquals("RGB",cjtAlfabetos.getAlfabeto("RGB").getNombre());
        assertEquals(caracteres2,cjtAlfabetos.getAlfabeto("RGB").getCaracteres());
        assertTrue(cjtAlfabetos.getAlfabetos().size() == 2);
    }

    @Test
    public void testExisteAlfabeto() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();
        ArrayList<Character> caracteres = new ArrayList<Character>();
        caracteres.add('a');
        caracteres.add('b');
        caracteres.add('c');
        cjtAlfabetos.anadirAlfabeto("Ingles",caracteres);
        ArrayList<Character> caracteres2 = new ArrayList<Character>();
        caracteres2.add('R');
        caracteres2.add('G');
        caracteres2.add('B');
        cjtAlfabetos.anadirAlfabeto("RGB",caracteres2);
    
        assertTrue(cjtAlfabetos.existeAlfabeto("RGB"));
        assertTrue(cjtAlfabetos.existeAlfabeto("Ingles"));
        assertFalse(cjtAlfabetos.existeAlfabeto("Español"));
    }

}