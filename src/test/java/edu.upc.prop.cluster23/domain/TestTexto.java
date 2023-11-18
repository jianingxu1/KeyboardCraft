package edu.upc.prop.cluster23.domain;

import static org.junit.Assert.*;
import org.junit.Test;

import edu.upc.prop.cluster23.domain.Texto;

/** Clase TestTexto
 *  Representan los test unitarios a la clase Texto.
 *  @author Muhammad Yasin Khokhar (muhammad.yasin.khokhar@estudiantat.upc.edu)
 */

public class TestTexto{
  
  @Test
  public void testDefaultConstructor() {
      Texto texto = new Texto();
      assertEquals("", texto.getTexto());
  }
  
  @Test
  public void testParametrizedConstructor() {
    String input = "abcdedfghijklmnopqrstuvwxyz";
    Texto texto = new Texto(input);
    assertEquals(input, texto.getTexto());
  }
  
  @Test
  public void testModificarTexto() {
    String input = "abcdedfghijklmnopqrstuvwxyz";
    Texto texto = new Texto(input);
    assertEquals(input, texto.getTexto());
    String input2 = "abcdef";
    texto.modificarTexto(input2);
    assertEquals(input2, texto.getTexto());
  }
  
  @Test
  public void testGetTexto() {
    String input = "abcdedfghijklmnopqrstuvwxyz";
    Texto texto = new Texto(input);
    assertEquals(input, texto.getTexto());
  }

}