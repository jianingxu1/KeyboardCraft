package edu.upc.prop.cluster23.domain;

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
