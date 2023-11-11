import static org.junit.Assert.*;
import org.junit.Test;

import edu.upc.prop.cluster23.domain.PalabrasConFrecuencia;

public class TestPalabrasConFrecuencia {

    @Test
    public void testDefaultConstructor() {
        PalabrasConFrecuencia palabras = new PalabrasConFrecuencia();
        assertEquals(0, palabras.getMap().size());
    }

    @Test
    public void testParametrizedConstructor() {
        String input = "phone 1\nkeyboard 2\nhome 10";
        PalabrasConFrecuencia palabras = new PalabrasConFrecuencia(input);

        System.out.println("hifdsafsf");
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
        String input = "phone 1\nkeyboard 2\nhome 10";

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
        System.out.println("FKJSDLJFKDSJLKFLSDKLFKJSD");
        String input = "phone 1\nkeyboard 2\nhome 10\n";
        PalabrasConFrecuencia palabras = new PalabrasConFrecuencia(input);
        assertEquals(input, palabras.toString());

        String input2 = "";
        PalabrasConFrecuencia palabras2 = new PalabrasConFrecuencia(input2);
        assertEquals(input2, palabras2.toString());
    }
}
