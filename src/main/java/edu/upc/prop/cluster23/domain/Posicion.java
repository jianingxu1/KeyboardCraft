package edu.upc.prop.cluster23.domain;

/**
 * Clase Posicion
 * Representa una posición en una matriz
 * @author Rubén Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 */

public class Posicion {
    int fila;
    int col;
    Posicion(int fila, int col) {
        this.fila = fila;
        this.col = col;
    }
    Posicion(Posicion p) {
        this.fila = p.fila;
        this.col = p.col;
    }
    public double euclidianDistanceTo(Posicion p) {
        double deltaX = (double)(this.fila - p.fila);
        double deltaY = (double)(this.col - p.col);
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
