package edu.upc.prop.cluster23.domain;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;


/** Clase estCjtTAlfabeto
 *  Representan los test unitarios al cjtalfabetos.
 *  @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */
public class TestCjtAlfabeto {
   @Test
    public void testDefaultConstructor1() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();
        assertTrue(cjtAlfabetos.getAlfabetos().isEmpty());
    }

    @Test
    public void testAñadirAlfabeto() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();
        ArrayList<Character> caracteres = new ArrayList<Character>();
        caracteres.add('a');
        caracteres.add('b');
        caracteres.add('c');

        String alf = "abc";

        cjtAlfabetos.añadirAlfabeto("Ingles",alf);
        assertFalse(cjtAlfabetos.getAlfabetos().isEmpty());
        assertEquals("Ingles",cjtAlfabetos.getAlfabeto("Ingles").getNombre());
        assertEquals(caracteres,cjtAlfabetos.getAlfabeto("Ingles").getCaracteres());
    }

    @Test
    public void testEliminarAlfabeto() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();

        String alf = "abc";

        cjtAlfabetos.añadirAlfabeto("Ingles",alf);
        assertFalse(cjtAlfabetos.getAlfabetos().isEmpty());
        cjtAlfabetos.eliminarAlfabeto("Ingles");
        assertTrue(cjtAlfabetos.getAlfabetos().isEmpty());
    }

    @Test
    public void testModificarAlfabeto() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();
        ArrayList<Character> caracteres = new ArrayList<Character>();
        caracteres.add('a');
        caracteres.add('b');
        caracteres.add('c');

        String alf = "abc";

        cjtAlfabetos.añadirAlfabeto("Ingles",alf);
        ArrayList<Character> caracteres2 = new ArrayList<Character>();
        caracteres2.add('R');
        caracteres2.add('G');
        caracteres2.add('B');

        String alf2 = "RGB";

        cjtAlfabetos.modificarAlfabeto("Ingles",alf2);
        assertEquals(caracteres2,cjtAlfabetos.getAlfabeto("Ingles").getCaracteres());
        assertFalse(caracteres == cjtAlfabetos.getAlfabeto("Ingles").getCaracteres());   
    }

    @Test
    public void testCambiarNombre() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();

        String alf = "abc";
        cjtAlfabetos.añadirAlfabeto("Ingles",alf);
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

        String alf = "abc";

        cjtAlfabetos.añadirAlfabeto("Ingles",alf);
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

        String alf = "abc";

        cjtAlfabetos.añadirAlfabeto("Ingles",alf);
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

        String alf = "abc";

        cjtAlfabetos.añadirAlfabeto("Ingles",alf);
        ArrayList<Character> caracteres2 = new ArrayList<Character>();
        caracteres2.add('R');
        caracteres2.add('G');
        caracteres2.add('B');

        String alf2 = "RGB";
        cjtAlfabetos.añadirAlfabeto("RGB",alf2);

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

        String alf = "abc";

        cjtAlfabetos.añadirAlfabeto("Ingles",alf);
        ArrayList<Character> caracteres2 = new ArrayList<Character>();
        caracteres2.add('R');
        caracteres2.add('G');
        caracteres2.add('B');

        String alf2 = "RGB";
        cjtAlfabetos.añadirAlfabeto("RGB",alf2);
    
        assertTrue(cjtAlfabetos.existeAlfabeto("RGB"));
        assertTrue(cjtAlfabetos.existeAlfabeto("Ingles"));
        assertFalse(cjtAlfabetos.existeAlfabeto("Español"));
    }


}