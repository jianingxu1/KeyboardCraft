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
     * @param p Objeto Posicion a copiar
     */
    Posicion(Posicion p) {
        this.fila = p.fila;
        this.col = p.col;
    }

    /**
     * Calcula la distancia euclidiana entre dos Posiciones
     * 
     * @param p Posicion a la que calcular la distancia
     * @return La distancia euclidiana entre las dos Posiciones
     */
    public double euclidianDistanceTo(Posicion p) {
        double deltaX = (double) (this.fila - p.fila);
        double deltaY = (double) (this.col - p.col);
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
