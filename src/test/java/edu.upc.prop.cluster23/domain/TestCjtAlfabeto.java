package edu.upc.prop.cluster23.domain;

import static org.junit.Assert.*;
import org.junit.Test;

import java.beans.Transient;
import java.util.ArrayList;

import edu.upc.prop.cluster23.exceptions.NoHayCaracteresExcepcion;
import edu.upc.prop.cluster23.exceptions.NombreAlfabetoDuplicadoExcepcion;
import edu.upc.prop.cluster23.exceptions.NombreAlfabetoNoValidoExcepcion;

/**
 * Clase estCjtTAlfabeto
 * Representan los test unitarios al cjtalfabetos.
 * 
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
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

        cjtAlfabetos.añadirAlfabeto("Ingles", alf);
        assertFalse(cjtAlfabetos.getAlfabetos().isEmpty());
        assertEquals("Ingles", cjtAlfabetos.getAlfabeto("Ingles").getNombre());
        assertEquals(caracteres, cjtAlfabetos.getAlfabeto("Ingles").getCaracteres());
    }

    @Test
    public void testEliminarAlfabeto() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();

        String alf = "abc";

        cjtAlfabetos.añadirAlfabeto("Ingles", alf);
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

        cjtAlfabetos.añadirAlfabeto("Ingles", alf);
        ArrayList<Character> caracteres2 = new ArrayList<Character>();
        caracteres2.add('R');
        caracteres2.add('G');
        caracteres2.add('B');

        String alf2 = "RGB";

        cjtAlfabetos.modificarAlfabeto("Ingles", alf2);
        assertEquals(caracteres2, cjtAlfabetos.getAlfabeto("Ingles").getCaracteres());
        assertFalse(caracteres == cjtAlfabetos.getAlfabeto("Ingles").getCaracteres());
    }

    @Test
    public void testCambiarNombre() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();

        String alf = "abc";
        cjtAlfabetos.añadirAlfabeto("Ingles", alf);
        cjtAlfabetos.cambiarNombre("Ingles", "Español");
        assertEquals("Español", cjtAlfabetos.getAlfabeto("Español").getNombre());
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

        cjtAlfabetos.añadirAlfabeto("Ingles", alf);
        assertEquals("Ingles", cjtAlfabetos.getAlfabeto("Ingles").getNombre());
        assertEquals(caracteres, cjtAlfabetos.getAlfabeto("Ingles").getCaracteres());
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

        cjtAlfabetos.añadirAlfabeto("Ingles", alf);
        assertEquals(caracteres, cjtAlfabetos.getAlfabetoCaracteres("Ingles"));

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

        cjtAlfabetos.añadirAlfabeto("Ingles", alf);
        ArrayList<Character> caracteres2 = new ArrayList<Character>();
        caracteres2.add('R');
        caracteres2.add('G');
        caracteres2.add('B');

        String alf2 = "RGB";
        cjtAlfabetos.añadirAlfabeto("RGB", alf2);

        assertFalse(cjtAlfabetos.getAlfabetos().isEmpty());
        assertEquals("Ingles", cjtAlfabetos.getAlfabeto("Ingles").getNombre());
        assertEquals(caracteres, cjtAlfabetos.getAlfabeto("Ingles").getCaracteres());
        assertEquals("RGB", cjtAlfabetos.getAlfabeto("RGB").getNombre());
        assertEquals(caracteres2, cjtAlfabetos.getAlfabeto("RGB").getCaracteres());
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

        cjtAlfabetos.añadirAlfabeto("Ingles", alf);
        ArrayList<Character> caracteres2 = new ArrayList<Character>();
        caracteres2.add('R');
        caracteres2.add('G');
        caracteres2.add('B');

        String alf2 = "RGB";
        cjtAlfabetos.añadirAlfabeto("RGB", alf2);

        assertTrue(cjtAlfabetos.existeAlfabeto("RGB"));
        assertTrue(cjtAlfabetos.existeAlfabeto("Ingles"));
        assertFalse(cjtAlfabetos.existeAlfabeto("Español"));
    }

    @Test
    public void testExcepcionesAñadirAlfabeto(){
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();
        cjtAlfabetos.añadirAlfabeto("Ingles", "abc");
        try{
            añadirAlfabeto("Ingles", "abc", cjtAlfabetos);
            }catch(NombreAlfabetoDuplicadoExcepcion e){
                assertEquals("El alfabeto " + "Ingles" + " ya existe en el conjunto de alfabetos.", e.getMessage());
            }catch(NombreAlfabetoNoValidoExcepcion e){
                assertEquals("El nombre del alfabeto no puede ser vacio.", e.getMessage());
            }catch(NoHayCaracteresExcepcion e){
                assertEquals("El alfabeto no puede estar vacio.", e.getMessage());
            }   
    }
    
    public void añadirAlfabeto(String idAlfabeto, String caracteres, CjtAlfabetos cjtAlfabetos)
			throws NombreAlfabetoDuplicadoExcepcion, NombreAlfabetoNoValidoExcepcion, NoHayCaracteresExcepcion {
		if (idAlfabeto.trim().isEmpty())
			throw new NombreAlfabetoNoValidoExcepcion("El nombre del alfabeto no puede ser vacio.");

		if (cjtAlfabetos.existeAlfabeto(idAlfabeto))
			throw new NombreAlfabetoDuplicadoExcepcion(idAlfabeto);

		if (caracteres.trim().isEmpty())
			throw new NoHayCaracteresExcepcion();

		cjtAlfabetos.añadirAlfabeto(idAlfabeto, caracteres);
	}

    @Test 
    public void testExcepsionesModificarAlfabeto(){
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();
        cjtAlfabetos.añadirAlfabeto("Ingles", "abc");
        try{
            modificarAlfabeto("Ingles", "abcd", cjtAlfabetos);
            }catch(NombreAlfabetoNoValidoExcepcion e){
                assertEquals("El alfabeto \"Ingles\" no existe.", e.getMessage());
            }catch(NoHayCaracteresExcepcion e){
                assertEquals("El alfabeto no puede estar vacio.", e.getMessage());
            }   
    }

    public void modificarAlfabeto(String idAlfabeto, String alfabeto, CjtAlfabetos cjtAlfabetos)
			throws NombreAlfabetoNoValidoExcepcion, NoHayCaracteresExcepcion {
		if (idAlfabeto.trim().isEmpty())
			throw new NombreAlfabetoNoValidoExcepcion("El nombre del alfabeto no puede ser vacio.");
		else if (!cjtAlfabetos.existeAlfabeto(idAlfabeto))
			throw new NombreAlfabetoNoValidoExcepcion("El alfabeto \"" + idAlfabeto + "\" no existe.");
		if (alfabeto.trim().isEmpty())
			throw new NoHayCaracteresExcepcion();
		cjtAlfabetos.modificarAlfabeto(idAlfabeto, alfabeto);
	}

    @Test 
    public void testExcepsionesEliminarAlfabeto(){
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();
        cjtAlfabetos.añadirAlfabeto("Ingles", "abc");
        try{
            eliminarAlfabeto("Ingles", cjtAlfabetos);
            eliminarAlfabeto("Ingles", cjtAlfabetos);
            fail("No se ha lanzado la excepcion");
            }catch(NombreAlfabetoNoValidoExcepcion e){
                assertEquals("El alfabeto \"" + "Ingles" + "\" no existe.", e.getMessage());
            }   
    }

    public void eliminarAlfabeto(String idAlfabeto, CjtAlfabetos cjtAlfabetos) throws NombreAlfabetoNoValidoExcepcion {
		if (idAlfabeto.trim().isEmpty())
			throw new NombreAlfabetoNoValidoExcepcion("El nombre del alfabeto no puede ser vacio.");
		else if (!cjtAlfabetos.existeAlfabeto(idAlfabeto))
			throw new NombreAlfabetoNoValidoExcepcion("El alfabeto \"" + idAlfabeto + "\" no existe.");

		cjtAlfabetos.eliminarAlfabeto(idAlfabeto);
	}
}