package edu.upc.prop.cluster23.domain;

/**
 * Clase Teclado
 * Representa un teclado con su nombre y distribución de teclas.
 * 
 * @author Rubén Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 */
public class Teclado {

    /** Atributos **/
    private String nombre;
    private char[][] distribucion;

    /** Constructora **/
    public Teclado(String nombre, char[][] distribucion) {
        this.nombre = nombre;
        this.distribucion = distribucion;
    }

    /** Métodos públicos **/

    /**
     * Intercambia la posición (i,j) de dos teclas del teclado.
     *
     * @param i1 fila tecla 1
     * @param j1 columna tecla 1
     * @param i2 fila tecla 2
     * @param j2 columna tecla 2
     */
    public void intercambiarTeclas(int i1, int j1, int i2, int j2) {
        char temp = distribucion[i2][j2];
        distribucion[i2][j2] = distribucion[i1][j1];
        distribucion[i1][j1] = temp;
    }

    /**
     * Obtiene el nombre del teclado.
     *
     * @return nombre del teclado
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Devuelve la matriz con la distribución del teclado.
     *
     * @return distribucion
     */
    public char[][] getTeclado() {
        return distribucion;
    }

    /**
     * Establece la distribución del teclado.
     *
     * @param distribucion nueva distribución
     */
    public void setDistribucion(char[][] distribucion) {
        this.distribucion = distribucion;
    }

    /** Métodos redefinidos **/

    /**
     * Devuelve la distribución del teclado en formato de string para mostrar por
     * pantalla
     * 
     * @return String que representa la distribución
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < distribucion.length; ++i) {
            s.append("---------------------\n");
            for (int j = 0; j < distribucion[i].length; ++j) {
                if (distribucion[i][j] == 0)
                    s.append("| ");
                else {
                    s.append("|");
                    s.append(distribucion[i][j]);
                }
            }
            for (int j = distribucion[i].length; j < 10; ++j) {
                s.append("| ");
            }
            s.append("|\n");
        }

        s.append("---------------------\n");
        s.append("|↑| | | space | |←|↩|\n");
        s.append("---------------------\n");

        return s.toString();
    }
}
