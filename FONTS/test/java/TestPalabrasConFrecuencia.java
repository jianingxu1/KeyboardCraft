import static org.junit.Assert.*;
import org.junit.Test;

import domain.PalabrasConFrecuencia;
import exceptions.FrecuenciaIncorrectaExcepcion;

/**
 * Clase TestPalabrasConFrecuencia
 * Representa los test a una colección de palabras y sus frecuencias.
 * 
 * @author Jianing Xu (jianing.xu@estudiantat.upc.edu)
 */
public class TestPalabrasConFrecuencia {

    @Test
    public void testDefaultConstructor() {
        PalabrasConFrecuencia palabras = new PalabrasConFrecuencia();
        assertEquals(0, palabras.getMap().size());
    }

    @Test
    public void testParametrizedConstructor() {
        String input = "phone 1 keyboard 2 home 10";
        try {
            PalabrasConFrecuencia palabras = new PalabrasConFrecuencia(input);
            assertEquals(3, palabras.getMap().size());

            assertTrue(palabras.getMap().containsKey("phone"));
            assertTrue(palabras.getMap().containsKey("keyboard"));
            assertTrue(palabras.getMap().containsKey("home"));
            assertFalse(palabras.getMap().containsKey("apple"));

            assertEquals(Integer.valueOf(1), palabras.getMap().get("phone"));
            assertEquals(Integer.valueOf(2), palabras.getMap().get("keyboard"));
            assertEquals(Integer.valueOf(10), palabras.getMap().get("home"));
            assertNull(palabras.getMap().get("apple"));

            String input2 = "";
            PalabrasConFrecuencia palabras2 = new PalabrasConFrecuencia(input2);
            assertEquals(0, palabras2.getMap().size());
            assertFalse(palabras2.getMap().containsKey("phone"));
            assertFalse(palabras2.getMap().containsKey("keyboard"));
            assertFalse(palabras2.getMap().containsKey("home"));
            assertFalse(palabras2.getMap().containsKey("apple"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    @Test
    public void testCambiarMap() {
        PalabrasConFrecuencia palabras = new PalabrasConFrecuencia();
        String input = "phone 1 keyboard 2 home 10";

        palabras.cambiarMap(input);

        assertEquals(3, palabras.getMap().size());

        assertTrue(palabras.getMap().containsKey("phone"));
        assertTrue(palabras.getMap().containsKey("keyboard"));
        assertTrue(palabras.getMap().containsKey("home"));
        assertFalse(palabras.getMap().containsKey("apple"));

        assertEquals(Integer.valueOf(1), palabras.getMap().get("phone"));
        assertEquals(Integer.valueOf(2), palabras.getMap().get("keyboard"));
        assertEquals(Integer.valueOf(10), palabras.getMap().get("home"));
        assertNull(palabras.getMap().get("apple"));
    }

    @Test
    public void testToString() {
        try {
            String input = "phone 1 keyboard 2 home 10";
            PalabrasConFrecuencia palabras = new PalabrasConFrecuencia(input);
            assertEquals(input, palabras.toString());

            String input1 = "";
            PalabrasConFrecuencia palabras1 = new PalabrasConFrecuencia(input1);
            assertEquals(input1, palabras1.toString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testExcepcionesInputListaDePalabras(){
        String input = "phone 1 keyboard 2 home 10";
        try {
            mirarInputListaDePalabras(input);
        } catch (FrecuenciaIncorrectaExcepcion e) {
            fail("No debería lanzar excepción");
        }
        try {
            mirarInputListaDePalabras("phone 1 keyboard 2 home 10 11");
            fail("Debería lanzar excepción");
        } catch (FrecuenciaIncorrectaExcepcion e) {
            assertEquals("El formato de palabras con frecuencia no es correcto, debe ser palabras seguidas de un espacio y su número de frecuencia.", e.getMessage());
        }
        try {
            mirarInputListaDePalabras("phone 1 keyboard 2 home");
            fail("Debería lanzar excepción");
        } catch (FrecuenciaIncorrectaExcepcion e) {
            assertEquals("El formato de palabras con frecuencia no es correcto, debe ser palabras seguidas de un espacio y su número de frecuencia.", e.getMessage());
        }
        try {
            mirarInputListaDePalabras("phone 1 keyboard 2 home 10 11 12");
        } catch (FrecuenciaIncorrectaExcepcion e) {
            assertEquals("El formato de palabras con frecuencia no es correcto, debe ser palabras seguidas de un espacio y su número de frecuencia.", e.getMessage());
        }
        try {
            mirarInputListaDePalabras("           ");
            fail("No Debería lanzar excepción");
        } catch (FrecuenciaIncorrectaExcepcion e) {
            assertEquals("El formato de palabras con frecuencia no es correcto, debe ser palabras seguidas de un espacio y su número de frecuencia.", e.getMessage());
        }
        try {
            mirarInputListaDePalabras("");
            fail("No Debería lanzar excepción");
        } catch (FrecuenciaIncorrectaExcepcion e) {
            assertEquals("El formato de palabras con frecuencia no es correcto, debe ser palabras seguidas de un espacio y su número de frecuencia.", e.getMessage());
        }
        try {
            mirarInputListaDePalabras("10 hola 10 adios");
            fail("Debería lanzar excepción");
        } catch (FrecuenciaIncorrectaExcepcion e) {
            assertEquals("El formato de palabras con frecuencia no es correcto, debe ser palabras seguidas de un espacio y su número de frecuencia.", e.getMessage());
        }
    }

    public void mirarInputListaDePalabras(String listaPalabras) throws FrecuenciaIncorrectaExcepcion {
        PalabrasConFrecuencia palabras ;
        String[] palabrasSeparadas = listaPalabras.split(" ");
        int words = palabrasSeparadas.length;
        if (words % 2 == 1 && !listaPalabras.isEmpty())
            throw new FrecuenciaIncorrectaExcepcion(
                    "El formato de palabras con frecuencia no es correcto, debe ser palabras seguidas de un espacio y su número de frecuencia.");

        if(listaPalabras.trim().isEmpty()) throw new FrecuenciaIncorrectaExcepcion("El formato de palabras con frecuencia no es correcto, debe ser palabras seguidas de un espacio y su número de frecuencia.");
        try {
            palabras = new PalabrasConFrecuencia(listaPalabras);
        } catch (NumberFormatException e) {
            throw new FrecuenciaIncorrectaExcepcion(
                    "El formato de palabras con frecuencia no es correcto, debe ser palabras seguidas de un espacio y su número de frecuencia.");
        } catch (IllegalArgumentException i) {
            throw new FrecuenciaIncorrectaExcepcion(i.getMessage());
        }
    }
}
