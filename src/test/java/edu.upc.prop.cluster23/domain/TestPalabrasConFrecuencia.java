package edu.upc.prop.cluster23.domain;

import static org.junit.Assert.*;
import org.junit.Test;

import edu.upc.prop.cluster23.domain.PalabrasConFrecuencia;

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
        String input = "phone 1 keyboard 2 home 10";
        PalabrasConFrecuencia palabras = new PalabrasConFrecuencia(input);
        assertEquals(input, palabras.toString());

        String input1 = "";
        PalabrasConFrecuencia palabras1 = new PalabrasConFrecuencia(input1);
        assertEquals(input1, palabras1.toString());
    }
}
