package domain;

import java.util.Map;

/**
 * Clase GeneradorDistribucionTeclado
 * Representa una clase para generar distribuciones de teclado con la opcion
 * de utilizar diferentes algoritmos para ello.
 * 
 * Autor: Jianing Xu (jianing.xu@estudiantat.upc.edu)
 */
public class GeneradorDistribucionTeclado {
  private Algoritmo algoritmo;

  /**
   * Constructor de la clase GeneradorDistribucionTeclado
   * 
   * @param algoritmo Algoritmo a utilizar
   */
  public GeneradorDistribucionTeclado(Algoritmo algoritmo) {
    this.algoritmo = algoritmo;
  }

  /**
   * Genera una distribucion de caracteres en una matriz según el algoritmo
   * proporcionado
   * 
   * @param alf      Alfabeto a utilizar
   * @param palabras Palabras con su frecuencia
   * @param texto    Texto a analizar
   * @return La distribucion de caracteres en una matriz generada por el algoritmo
   */
  public char[][] generarDistribucion(Alfabeto alfabeto, Map<String, Integer> bigramasConFrecuencia) {
    return algoritmo.generarDistribucion(alfabeto, bigramasConFrecuencia);
  }

  /**
   * Establece el algoritmo a utilizar
   * 
   * @param algoritmo Algoritmo a establecer
   */
  public void setAlgoritmo(Algoritmo algoritmo) {
    this.algoritmo = algoritmo;
  }

  /**
   * Obtiene el algoritmo actualmente utilizado
   * 
   * @return El algoritmo utilizado
   */
  public Algoritmo getAlgoritmo() {
    return algoritmo;
  }
}
