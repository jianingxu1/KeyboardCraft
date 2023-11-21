package edu.upc.prop.cluster23.domain;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.upc.prop.cluster23.exceptions.*;
import java.beans.Transient;
import java.util.ArrayList;

/**
 * Clase TestAlfabeto
 * Representa los Test de un alfabeto en concreto con sus caracteres.
 * 
 * @author Momin Miah Begum (momin.miah@estudiantat.upc.edu)
 */

public class TestAlfabeto {
    @Test
    public void testDefaultConstructor1() {
        String a = "Español";
        Alfabeto alfabeto;
        alfabeto = new Alfabeto(a);
        assertEquals("Español", alfabeto.getNombre());
    }

    @Test
    public void testDefaultConstructor2() {
        String a = "Ingles";
        ArrayList<Character> caracteres = new ArrayList<Character>();
        caracteres.add('a');
        caracteres.add('b');
        caracteres.add('c');
        Alfabeto alfabeto;

        String alf = "abc";

        alfabeto = new Alfabeto(a, alf);
        assertEquals("Ingles", alfabeto.getNombre());
        assertEquals(caracteres, alfabeto.getCaracteres());
    }

    @Test
    public void testCambiarNombreAlfabeto() {
        String a = "Catalan";
        String b = "Español";
        Alfabeto alfabeto;
        alfabeto = new Alfabeto(a);
        alfabeto.cambiarNombre(b);
        assertEquals("Español", alfabeto.getNombre());
        assertFalse("Catalan" == alfabeto.getNombre());
    }

    @Test
    public void testModificarAlfabeto() {
        String a = "Ingles";
        ArrayList<Character> caracteres = new ArrayList<Character>();
        caracteres.add('a');
        caracteres.add('b');
        caracteres.add('c');
        Alfabeto alfabeto;

        String alf = "abc";

        alfabeto = new Alfabeto(a, alf);
        ArrayList<Character> caracteres2 = new ArrayList<Character>();
        caracteres2.add('R');
        caracteres2.add('G');
        caracteres2.add('B');

        String alf2 = "RGB";

        alfabeto.modificarAlfabeto(alf2);
        assertEquals(caracteres2, alfabeto.getCaracteres());
        assertFalse(caracteres == alfabeto.getCaracteres());
    }

    @Test
    public void testToString() {
        String name = "alf";
        ArrayList<Character> vectorCaracteres = new ArrayList<Character>();
        vectorCaracteres.add('a');
        vectorCaracteres.add('b');
        vectorCaracteres.add('c');

        Alfabeto alfabeto = new Alfabeto(name);

        String s = "a\nb\nc\n";
        assertEquals(s, alfabeto.toString(vectorCaracteres));

        vectorCaracteres = new ArrayList<Character>();
        alfabeto = new Alfabeto(name);

        s = "";
        assertEquals(s, alfabeto.toString(vectorCaracteres));
    }

    @Test
    public void testToArray() {
        String name = "alf";
        Alfabeto alfabeto = new Alfabeto(name, "abc");
        assertEquals(alfabeto.getCaracteres(), alfabeto.toArray("abc"));
    }

    @Test
    public void testExcepcionesAlfabetoCrear() {
        //el alfabeto no puede estar vacio, ni el nombre ni el alfabeto
        String name = "";
        String alf = "";
        try {
            crearAlfabeto(name, alf);
        } catch (NombreAlfabetoNoValidoExcepcion e) {
            assertEquals("El nombre del alfabeto no puede ser vacio.", e.getMessage());
        }
        catch (NoHayCaracteresExcepcion e) {
            assertEquals("La cadena de caracteres no puede estar vacia.", e.getMessage());
        }
    }

    public void crearAlfabeto(String idAlfabeto, String caracteres)
			throws NombreAlfabetoNoValidoExcepcion, NoHayCaracteresExcepcion {
		if (idAlfabeto.trim().isEmpty())
			throw new NombreAlfabetoNoValidoExcepcion("El nombre del alfabeto no puede ser vacio.");

		if (caracteres.trim().isEmpty())
			throw new NoHayCaracteresExcepcion();
	}

    @Test 
    public void testExcepcionesAlfabetoBorrar(){
        //el alfabeto no puede estar vacio, ni el nombre ni el alfabeto
        String name = "";
        try {
            borrarAlfabeto(name);
        } catch (NombreAlfabetoNoValidoExcepcion e) {
            assertEquals("El nombre del alfabeto no puede ser vacio.", e.getMessage());
        }
    }

    public void borrarAlfabeto(String idAlfabeto) throws NombreAlfabetoNoValidoExcepcion {
        if (idAlfabeto.trim().isEmpty())
            throw new NombreAlfabetoNoValidoExcepcion("El nombre del alfabeto no puede ser vacio.");
    }

    @Test 
    public void testExcepcionesAlfabetoModificar(){
        //el alfabeto no puede estar vacio, ni el nombre ni el alfabeto
        String name = "";
        String alf = "";
        try {
            modificarAlfabeto(name, alf);
        } catch (NombreAlfabetoNoValidoExcepcion e) {
            assertEquals("El nombre del alfabeto no puede ser vacio.", e.getMessage());
        }
        catch (NoHayCaracteresExcepcion e) {
            assertEquals("La cadena de caracteres no puede estar vacia.", e.getMessage());
        }
    }

    public void modificarAlfabeto(String idAlfabeto, String caracteres)
            throws NombreAlfabetoNoValidoExcepcion, NoHayCaracteresExcepcion {
        if (idAlfabeto.trim().isEmpty())
            throw new NombreAlfabetoNoValidoExcepcion("El nombre del alfabeto no puede ser vacio.");
        if(caracteres.trim().isEmpty())
            throw new NoHayCaracteresExcepcion();
            }

}