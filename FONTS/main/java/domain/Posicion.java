package domain;

/**
 * Clase Posicion
 * Representa una posicion en una matriz
 * 
 * @author Jianing Xu (jianing.xu@estudiantat.upc.edu)
 */
public class Posicion {
    int fila;
    int col;

    /**
     * Constructor de la clase Posicion
     * 
     * @param fila Valor de la fila
     * @param col  Valor de la columna
     */
    Posicion(int fila, int col) {
        this.fila = fila;
        this.col = col;
    }

    /**
     * Constructor de copia de la clase Posicion
     * 
     * @param posicion Objeto Posicion a copiar
     */
    Posicion(Posicion posicion) {
        this.fila = posicion.fila;
        this.col = posicion.col;
    }

    /**
     * Calcula la distancia euclidiana entre dos Posiciones
     * 
     * @param posicion Posicion a la que calcular la distancia
     * @return La distancia euclidiana entre las dos Posiciones
     */
    public double euclidianDistanceTo(Posicion posicion) {
        //deltaX = x2 - x1
        //deltaY = y2 - y1
        double deltaX = (double) (this.fila - posicion.fila);
        double deltaY = (double) (this.col - posicion.col);
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
