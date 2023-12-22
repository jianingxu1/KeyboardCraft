package domain;

/**
 * Clase Teclado
 * Representa un teclado con su nombre y distribución de teclas.
 * 
 * @author Rubén Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 */
public class Teclado {

    /** Atributos **/
    private String nombre;
    private Character[][] distribucion;

    /** Constructora **/
    public Teclado(String nombre, Character[][] distribucion) {
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
    public void intercambiarTeclas(char caracter1, char caracter2) throws Exception {
        if (caracter1 == caracter2)
            throw new Exception("No puedes intercambiar el mismo caracter");
        int i1 = -1, j1 = -1, i2 = -1, j2 = -1;
        for (int i = 0; i < distribucion.length; ++i) {
            for (int j = 0; j < distribucion[i].length; ++j) {
                if (distribucion[i][j] == caracter1) {
                    i1 = i;
                    j1 = j;
                } else if (distribucion[i][j] == caracter2) {
                    i2 = i;
                    j2 = j;
                }
            }
        }
        if (i1 == -1 || i2 == -1 || j1 == -1 || j2 == -1)
            throw new Exception("No se han encontrado los caracteres en el teclado");
        distribucion[i1][j1] = caracter2;
        distribucion[i2][j2] = caracter1;
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
    public Character[][] getDistribucion() {
        return distribucion;
    }

    /**
     * Establece la distribución del teclado.
     *
     * @param distribucion nueva distribución
     */
    public void setDistribucion(Character[][] distribucion) {
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
