package edu.upc.prop.cluster23.domain;

import static org.junit.Assert.*;
import org.junit.Test;

import java.beans.Transient;
import java.util.ArrayList;

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

       alfabeto = new Alfabeto(a,alf);
       assertEquals("Ingles", alfabeto.getNombre());
       assertEquals(caracteres,alfabeto.getCaracteres());
    }

    @Test
    public void testCambiarNombreAlfabeto(){
        String a = "Catalan";
        String b = "Español";
        Alfabeto alfabeto;
        alfabeto = new Alfabeto(a);
        alfabeto.cambiarNombre(b);
        assertEquals("Español",alfabeto.getNombre());
        assertFalse("Catalan" == alfabeto.getNombre());
    }

    // @Test
    // public void testCambiarCaracteresDelAlfabeto(){
    //     String a = "Ingles";
    //     ArrayList<Character> caracteres = new ArrayList<Character>();
    //     caracteres.add('a');
    //     caracteres.add('b');
    //     caracteres.add('c');
    //     Alfabeto alfabeto;

    //     String alf = "abc";

    //     alfabeto = new Alfabeto(a,alf);
    //     ArrayList<Character> caracteres2 = new ArrayList<Character>();
    //     caracteres2.add('R');
    //     caracteres2.add('G');
    //     caracteres2.add('B');

    //     String alf2 = "RGB";

    //     alfabeto.cambiarCaracteres(alf2);
    //     assertEquals(caracteres2,alfabeto.getCaracteres());
    //     assertFalse(caracteres == alfabeto.getCaracteres());   
    // }

    @Test 
    public void testToString(){
        String name = "alf";
        ArrayList<Character> vectorCaracteres = new ArrayList<Character>();
        vectorCaracteres.add('a');
        vectorCaracteres.add('b');
        vectorCaracteres.add('c');

        Alfabeto alfabeto = new Alfabeto(name);

        String s = "a\nb\nc\n";
        assertEquals(s,alfabeto.toString(vectorCaracteres));

        vectorCaracteres = new ArrayList<Character>();
        alfabeto = new Alfabeto(name);
        
        s = "";
        assertEquals(s,alfabeto.toString(vectorCaracteres));
    }

    @Test
    public void testToArray(){
        String name = "alf";
        Alfabeto alfabeto = new Alfabeto(name, "abc");
        assertEquals(alfabeto.getCaracteres(),alfabeto.toArray("abc"));
    }

}