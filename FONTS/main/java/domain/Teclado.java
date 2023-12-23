package domain;

import exceptions.CaracteresNoValidosExcepcion;

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
    /**
     * Crea un teclado con su nombre y distribución de teclas.
     * @param nombre Nombre del teclado.
     * @param distribucion Matriz que contiene la distribución de teclas.
     */
    public Teclado(String nombre, Character[][] distribucion) {
        this.nombre = nombre;
        this.distribucion = distribucion;
    }

    /** Métodos públicos **/

    /**
     * Intercambia la posición de dos teclas en la distribución del teclado.
     *
     * @param caracter1 Primer caracter a intercambiar.
     * @param caracter2 Segundo caracter a intercambiar.
     * @throws CaracteresNoValidosExcepcion Si los caracteres son iguales, no se
     *                                      encuentran en el teclado
     *                                      o hay algún otro problema durante el
     *                                      intercambio.
     */
    public void intercambiarTeclas(char caracter1, char caracter2) throws CaracteresNoValidosExcepcion {
        // Verificar si los caracteres son iguales
        if (caracter1 == caracter2) {
            throw new CaracteresNoValidosExcepcion("No puedes intercambiar el mismo caracter");
        }

        // Buscar las posiciones de los caracteres en la distribución
        int[] posicionCaracter1 = buscarPosicion(caracter1);
        int[] posicionCaracter2 = buscarPosicion(caracter2);

        // Verificar si se encontraron las posiciones
        if (posicionCaracter1 == null || posicionCaracter2 == null) {
            throw new CaracteresNoValidosExcepcion("No se han encontrado los caracteres en el teclado");
        }

        // Realizar el intercambio
        distribucion[posicionCaracter1[0]][posicionCaracter1[1]] = caracter2;
        distribucion[posicionCaracter2[0]][posicionCaracter2[1]] = caracter1;
    }

    /**
     * Busca la posición de un caracter en la distribución del teclado.
     *
     * @param caracter Caracter a buscar.
     * @return Array de dos elementos con la posición [fila, columna] del caracter,
     *         o null si no se encuentra.
     */
    private int[] buscarPosicion(char caracter) {
        for (int i = 0; i < distribucion.length; ++i) {
            for (int j = 0; j < distribucion[i].length; ++j) {
                if (distribucion[i][j] != null && distribucion[i][j] == caracter) {
                    return new int[] { i, j };
                }
            }
        }
        return null; // Caracter no encontrado
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
