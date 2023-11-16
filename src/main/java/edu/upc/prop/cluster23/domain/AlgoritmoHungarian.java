package edu.upc.prop.cluster23.domain;

import java.util.Arrays;

/**
 * Clase AlgoritmoHungarian
 * Representa un algoritmo para resolver el problema de asignacion utilizando el
 * metodo Hungarian Algorithm. Se utiliza para encontrar el coste de la
 * asignación optima entre dos conjuntos de elementos, obteniendo el coste minimo.
 * 
 * @author Jianing Xu (jianing.xu@estudiantat.upc.edu)
 */
public class AlgoritmoHungarian {
    public AlgoritmoHungarian() {};

    /**
     * Ejecuta el algoritmo hungaro en la matriz de entrada para encontrar el
     * coste de la asignación optima.
     * 
     * @param matrizEntrada La matriz de entrada que representa los costos de
     * asignación
     * @return El costo minimo de la asignacion optima
     */
    public double ejecutar(double[][] matrizEntrada) {
        int n = matrizEntrada.length;
        double[][] M = new double[n][n];
        for (int i = 0; i < n; ++i) {
            M[i] = matrizEntrada[i].clone();
        }

        // Paso 1: Preproceso
        preprocesarMatriz(M);

        // Paso 2: Fase iterativa
        boolean[][] esCero = obtenerMatrizCero(M);
        boolean[][] esCeroMarcado = obtenerMejorAsignacion(esCero);
        boolean[] filaCubierta = new boolean[n];
        boolean[] colCubierta = new boolean[n];
        int numLineas = obtenerMinNumLineas(esCero, esCeroMarcado, filaCubierta, colCubierta);

        while (numLineas != n) {
            double minimo = obtenerMinimoNoCubierto(M, filaCubierta, colCubierta);
            sumarYRestarMinimoCubiertos(M, filaCubierta, colCubierta, minimo);
            esCero = obtenerMatrizCero(M);
            esCeroMarcado = obtenerMejorAsignacion(esCero);
            numLineas = obtenerMinNumLineas(esCero, esCeroMarcado, filaCubierta, colCubierta);
        }
        return obtenerCosteDeAsignacion(matrizEntrada, esCeroMarcado);
    }

    /**
     * Realiza el preprocesamiento de la matriz para prepararla para el algoritmo.
     * 
     * @param M La matriz de entrada a ser preprocesada
     */
    private void preprocesarMatriz(double[][] M) {
        int n = M.length;
        // Substrae el minimo de cada fila
        for (int i = 0; i < n; ++i) {
            double min = Double.MAX_VALUE;
            for (int j = 0; j < n; ++j) {
                min = Math.min(M[i][j], min);
            }
            for (int j = 0; j < n; ++j) {
                M[i][j] -= min;
            }
        }

        // Substrae el minimo de cada columna
        for (int j = 0; j < n; ++j) {
            double min = Double.MAX_VALUE;
            for (int i = 0; i < n; ++i) {
                min = Math.min(M[i][j], min);
            }
            for (int i = 0; i < n; ++i) {
                M[i][j] -= min;
            }
        }
    }

    /**
     * Obtiene una matriz booleana que indica celdas con valor cero.
     * 
     * @param M La matriz de entrada para detectar ceros
     * @return Una matriz booleana indicando con true las celdas con valor cero
     */
    private boolean[][] obtenerMatrizCero(double[][] M) {
        int n = M.length;
        boolean[][] esCero = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                esCero[i][j] = (M[i][j] == 0.0);
            }
        }
        return esCero;
    }

    /**
     * Funcion recursiva que obtiene la asignacion con el maximo de ceros
     * marcados posible tales que no esten en la misma fila ni columna.
     *
     * @param esCero La matriz booleana que indica celdas con valor cero
     * @param filaCubierta Array de booleanos indicando filas con un cero
     * asignado
     * @param colCubierta Array de booleanos indicando columnas con un cero
     * asignado
     * @param countCubiertos Contador de celdas cubiertas
     * @param esCeroAsignado La matriz booleana de asignacion marcada
     * @param mejorCountCubiertos Mejor contador de celdas cubiertas
     * @param mejorEsCeroAsignado Mejor matriz booleana de asignacion marcada
     * @param fila Indice de fila actual para la recursion
     */
    private void obtenerMejorAsignacionRecursivo(boolean[][] esCero, boolean[] filaCubierta,
    boolean[] colCubierta, int countCubiertos, boolean[][] esCeroAsignado,
    int[] mejorCountCubiertos, boolean[][] mejorEsCeroAsignado, int fila) {
        int n = esCero.length;
        if (fila == n) {
            if (countCubiertos > mejorCountCubiertos[0]) {
                mejorCountCubiertos[0] = countCubiertos;
                for (int i = 0; i < n; ++i) {
                    mejorEsCeroAsignado[i] = esCeroAsignado[i].clone();
                }
            }
            return;
        }
        if (mejorCountCubiertos[0] == n) return;
        for (int col = 0; col < n; ++col) {
            if (esCero[fila][col] && !filaCubierta[fila] && !colCubierta[col]) {
                filaCubierta[fila] = true;
                colCubierta[col] = true;
                esCeroAsignado[fila][col] = true;
                obtenerMejorAsignacionRecursivo(esCero, filaCubierta, colCubierta,
                    countCubiertos + 1, esCeroAsignado, mejorCountCubiertos,
                    mejorEsCeroAsignado, fila + 1);
                filaCubierta[fila] = false;
                colCubierta[col] = false;
                esCeroAsignado[fila][col] = false;
            }
        }
        obtenerMejorAsignacionRecursivo(esCero, filaCubierta, colCubierta,
            countCubiertos, esCeroAsignado, mejorCountCubiertos, mejorEsCeroAsignado,
            fila + 1);
    }

    /**
     * Obtiene la asignacion con el maximo de ceros marcados posible tales que
     * no esten en la misma fila ni columna.
     * 
     * @param esCero La matriz booleana que indica celdas con valor cero
     * @return La asignacion con el maximo de ceros posible
     */
    private boolean[][] obtenerMejorAsignacion(boolean[][] esCero) {
        int n = esCero.length;

        boolean[][] esCeroAsignado = new boolean[n][n];
        boolean[] filaCubierta = new boolean[n];
        boolean[] colCubierta = new boolean[n];

        int[] mejorCountCubiertos = {0};
        boolean[][] mejorEsCeroAsignado = new boolean[n][n];
        obtenerMejorAsignacionRecursivo(esCero, filaCubierta, colCubierta, 0,
            esCeroAsignado, mejorCountCubiertos, mejorEsCeroAsignado, 0);
        return mejorEsCeroAsignado;
    }
    
    /**
     * Obtiene el numero minimo de lineas para cubrir celdas con valor cero.
     * 
     * @param esCero La matriz booleana que indica celdas con valor cero
     * @param esCeroAsignado La matriz booleana de asignacion marcada
     * @param filaCubierta Array de booleanos indicando filas cubiertas
     * @param colCubierta Array de booleanos indicando columnas cubiertas
     * @return El numero minimo de lineas necesarias para cubrir los ceros
     */
    private int obtenerMinNumLineas(boolean[][] esCero, boolean[][] esCeroAsignado, boolean[] filaCubierta, boolean[] colCubierta) {
        int n = esCero.length;
        boolean[] filaMarcada = new boolean[n];
        boolean[] colMarcada = new boolean[n];
        
        // c) Marcamos las filas sin asignacion
        for (int i = 0; i < n; ++i) { 
            boolean contieneCeroAsignado = false;
            for (int j = 0; j < n && !contieneCeroAsignado; ++j) {
                if (esCeroAsignado[i][j]) contieneCeroAsignado = true;
            }
            if (!contieneCeroAsignado) filaMarcada[i] = true;
        }

        boolean existeCambio = true;
        while (existeCambio) {
            existeCambio = false;
            // d) Marcamos las columnas que tienen algun zero en alguna fila marcada
            for (int j = 0; j < n; ++j) {
                boolean contieneCeroEnFilaMarcada = false;
                for (int i = 0; i < n && !contieneCeroEnFilaMarcada; ++i) {
                    if (esCero[i][j] && filaMarcada[i]) contieneCeroEnFilaMarcada = true;
                }
                if (contieneCeroEnFilaMarcada && !colMarcada[j]) {
                    colMarcada[j] = true;
                    existeCambio = true;
                }
            }
    
            // e) Marcamos las filas que tienen su zero asignado en alguna columna marcada
            for (int i = 0; i < n; ++i) {
                if (filaMarcada[i]) continue;
                for (int j = 0; j < n && !filaMarcada[i]; ++j) {
                    if (colMarcada[j] && esCeroAsignado[i][j] && !filaMarcada[i]) {
                        filaMarcada[i] = true;
                        existeCambio = true;
                    }
                }
            }
        }

        int count = 0;
        Arrays.fill(filaCubierta, false);
        Arrays.fill(colCubierta, false);
        for (int i = 0; i < n; ++i) {
            if (colMarcada[i]) {
                ++count;
                colCubierta[i] = true;
            }
            if (!filaMarcada[i]) {
                ++count;
                filaCubierta[i] = true;
            }
        }
        return count;
    }

    /**
     * Obtiene el minimo valor no cubierto en la matriz.
     * 
     * @param M La matriz de entrada
     * @param filaCubierta Array de booleanos indicando filas cubiertas
     * @param colCubierta Array de booleanos indicando columnas cubiertas
     * @return El valor minimo no cubierto en la matriz
     */
    private double obtenerMinimoNoCubierto(double[][] M, boolean[] filaCubierta,
    boolean[] colCubierta) {
        int n = M.length;
        double minimo = Double.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!filaCubierta[i] && !colCubierta[j] && M[i][j] < minimo)
                    minimo = M[i][j];
            }
        } 
        return minimo;
    }

    /**
     * Suma una vez el minimo a las celdas cubiertas, y dos veces a las celdas
     * doblemente cubiertas. A continuacion, resta el minimo a todas las celdas.
     * 
     * @param M La matriz de entrada
     * @param filaCubierta Array de booleanos indicando filas cubiertas
     * @param colCubierta Array de booleanos indicando columnas cubiertas
     * @param minimo El valor minimo a ser sumado y restado
     */
    private void sumarYRestarMinimoCubiertos(double[][] M, boolean[] filaCubierta,
    boolean[] colCubierta, double minimo) {
        int n = M.length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (filaCubierta[i]) {
                    M[i][j] += minimo;
                }
                if (colCubierta[j]) {
                    M[i][j] += minimo;
                }
                M[i][j] -= minimo;
            }
        }
    }

    /**
     * Calcula el costo minimo dada una matriz y su correspondiente asignacion.
     * 
     * @param M La matriz de costos
     * @param esMarcado La asignacion marcada en la matriz
     * @return El coste de la asignacion
     */
    private double obtenerCosteDeAsignacion(double[][] M, boolean[][] esMarcado) {
        double coste = 0.0;
        for (int i = 0; i < M.length; ++i) {
            for (int j = 0; j < M[i].length; ++j) {
                if (esMarcado[i][j]) coste += M[i][j];
            }
        }
        return coste;
    }
}
