import static org.junit.Assert.*;

import domain.CjtAlfabetos;
import org.junit.Test;

import java.util.ArrayList;

import exceptions.NoHayCaracteresExcepcion;
import exceptions.NombreAlfabetoDuplicadoExcepcion;
import exceptions.NombreAlfabetoNoValidoExcepcion;

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
    public void anadirNuevoAlfabeto() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();
        ArrayList<Character> caracteres = new ArrayList<Character>();
        caracteres.add('a');
        caracteres.add('b');
        caracteres.add('c');

        String alf = "abc";

        try {
            cjtAlfabetos.anadirNuevoAlfabeto("Ingles", alf);
            assertFalse(cjtAlfabetos.getAlfabetos().isEmpty());
            assertEquals("Ingles", cjtAlfabetos.getAlfabeto("Ingles").getNombre());
            assertEquals(caracteres, cjtAlfabetos.getAlfabeto("Ingles").getCaracteres());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testEliminarAlfabeto() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();

        String alf = "abc";

        try {
            cjtAlfabetos.anadirNuevoAlfabeto("Ingles", alf);
            assertFalse(cjtAlfabetos.getAlfabetos().isEmpty());
            cjtAlfabetos.eliminarAlfabeto("Ingles");
            assertTrue(cjtAlfabetos.getAlfabetos().isEmpty());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testModificarAlfabeto() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();
        ArrayList<Character> caracteres = new ArrayList<Character>();
        caracteres.add('a');
        caracteres.add('b');
        caracteres.add('c');

        try {
            String alf = "abc";
            cjtAlfabetos.anadirNuevoAlfabeto("Ingles", alf);
            ArrayList<Character> caracteres2 = new ArrayList<Character>();
            caracteres2.add('R');
            caracteres2.add('G');
            caracteres2.add('B');
            String alf2 = "RGB";

            cjtAlfabetos.modificarCaracteresAlfabeto("Ingles", alf2);
            assertEquals(caracteres2, cjtAlfabetos.getAlfabeto("Ingles").getCaracteres());
            assertFalse(caracteres == cjtAlfabetos.getAlfabeto("Ingles").getCaracteres());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

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

        try {
            cjtAlfabetos.anadirNuevoAlfabeto("Ingles", alf);
            assertEquals("Ingles", cjtAlfabetos.getAlfabeto("Ingles").getNombre());
            assertEquals(caracteres, cjtAlfabetos.getAlfabeto("Ingles").getCaracteres());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
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

        try {
            cjtAlfabetos.anadirNuevoAlfabeto("Ingles", alf);
            assertEquals(caracteres, cjtAlfabetos.getCaracteresDeAlfabeto("Ingles"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    @Test
    public void testGetAlfabetos() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();
        ArrayList<Character> caracteres = new ArrayList<Character>();
        caracteres.add('a');
        caracteres.add('b');
        caracteres.add('c');

        try {
            String alf = "abc";

            cjtAlfabetos.anadirNuevoAlfabeto("Ingles", alf);
            ArrayList<Character> caracteres2 = new ArrayList<Character>();
            caracteres2.add('R');
            caracteres2.add('G');
            caracteres2.add('B');

            String alf2 = "RGB";
            cjtAlfabetos.anadirNuevoAlfabeto("RGB", alf2);

            assertFalse(cjtAlfabetos.getAlfabetos().isEmpty());
            assertEquals("Ingles", cjtAlfabetos.getAlfabeto("Ingles").getNombre());
            assertEquals(caracteres, cjtAlfabetos.getAlfabeto("Ingles").getCaracteres());
            assertEquals("RGB", cjtAlfabetos.getAlfabeto("RGB").getNombre());
            assertEquals(caracteres2, cjtAlfabetos.getAlfabeto("RGB").getCaracteres());
            assertTrue(cjtAlfabetos.getAlfabetos().size() == 2);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testExisteAlfabeto() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();
        ArrayList<Character> caracteres = new ArrayList<Character>();
        caracteres.add('a');
        caracteres.add('b');
        caracteres.add('c');

        try {
            String alf = "abc";

            cjtAlfabetos.anadirNuevoAlfabeto("Ingles", alf);
            ArrayList<Character> caracteres2 = new ArrayList<Character>();
            caracteres2.add('R');
            caracteres2.add('G');
            caracteres2.add('B');

            String alf2 = "RGB";
            cjtAlfabetos.anadirNuevoAlfabeto("RGB", alf2);

            assertTrue(cjtAlfabetos.existeAlfabeto("RGB"));
            assertTrue(cjtAlfabetos.existeAlfabeto("Ingles"));
            assertFalse(cjtAlfabetos.existeAlfabeto("Español"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testExcepcionesAnadirNuevoAlfabeto() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();
        try {
            cjtAlfabetos.anadirNuevoAlfabeto("Ingles", "abc");
            cjtAlfabetos.anadirNuevoAlfabeto("Ingles", "abc");
        } catch (NombreAlfabetoDuplicadoExcepcion e) {
            assertEquals("El alfabeto " + "Ingles" + " ya existe en el conjunto de alfabetos.", e.getMessage());
        } catch (NombreAlfabetoNoValidoExcepcion e) {
            assertEquals("El nombre del alfabeto no puede ser vacio.", e.getMessage());
        } catch (NoHayCaracteresExcepcion e) {
            assertEquals("El alfabeto no puede estar vacio.", e.getMessage());
        }
    }

    @Test
    public void testExcepcionesModificarAlfabeto() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();
        try {
            cjtAlfabetos.anadirNuevoAlfabeto("Ingles", "abc");
            modificarAlfabeto("Ingles", "abcd", cjtAlfabetos);
        } catch (NombreAlfabetoNoValidoExcepcion e) {
            assertEquals("El alfabeto \"Ingles\" no existe.", e.getMessage());
        } catch (NoHayCaracteresExcepcion e) {
            assertEquals("El alfabeto no puede estar vacio.", e.getMessage());
        } catch (NombreAlfabetoDuplicadoExcepcion e) {
            assertEquals("El alfabeto Ingles ya existe en el conjunto de alfabetos.", e.getMessage());
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
        cjtAlfabetos.modificarCaracteresAlfabeto(idAlfabeto, alfabeto);
    }

    @Test
    public void testExcepcionesEliminarAlfabeto() {
        CjtAlfabetos cjtAlfabetos;
        cjtAlfabetos = new CjtAlfabetos();
        try {
            cjtAlfabetos.anadirNuevoAlfabeto("Ingles", "abc");
            eliminarAlfabeto("Ingles", cjtAlfabetos);
            eliminarAlfabeto("Ingles", cjtAlfabetos);
            fail("No se ha lanzado la excepcion");
        } catch (NombreAlfabetoNoValidoExcepcion e) {
            assertEquals("El alfabeto \"" + "Ingles" + "\" no existe.", e.getMessage());
        } catch (NombreAlfabetoDuplicadoExcepcion e) {
            assertEquals("El alfabeto Ingles ya existe en el conjunto de alfabetos.", e.getMessage());
        } catch (NoHayCaracteresExcepcion e) {
            assertEquals("El alfabeto no puede estar vacio.", e.getMessage());
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