package edu.upc.prop.cluster23.domain;

public class GeneradorDistribucionTeclado {
  private Algoritmo algoritmo;

  public GeneradorDistribucionTeclado(Algoritmo algoritmo) {
    this.algoritmo = algoritmo;
  }

  public char[][] generarDistribucion(Alfabeto alf, PalabrasConFrecuencia palabras, Texto texto) {
    return algoritmo.generarDistribucion(alf, palabras, texto);
  }

  public void setAlgoritmo(Algoritmo algoritmo) {
    this.algoritmo = algoritmo;
  }

  public Algoritmo getAlgoritmo() {
    return algoritmo;
  }
}
