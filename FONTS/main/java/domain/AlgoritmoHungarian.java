package domain;

import java.util.Arrays;

/**
 * Clase AlgoritmoHungarian
 * Representa un algoritmo para resolver el problema de asignacion utilizando el
 * metodo Hungarian Algorithm. Se utiliza para encontrar el coste de la
 * asignacion optima entre dos conjuntos de elementos, obteniendo el coste
 * minimo.
 * 
 * @author Jianing Xu (jianing.xu@estudiantat.upc.edu)
 */
public class AlgoritmoHungarian {
    public AlgoritmoHungarian() {
    };

    /**
     * Ejecuta el algoritmo hungaro en la matriz de entrada para encontrar el
     * coste de la asignacion optima.
     * 
     * @param matrizCostes La matriz de entrada que representa los costes de
     *                     asignacion
     * @return El coste minimo de la asignacion optima
     */
    public double ejecutar(double[][] matrizCostes) {
        double[][] M = clonarMatriz(matrizCostes);
        preprocesarMatriz(M);
        boolean[][] asignacionOptima = calcularAsignacionOptima(M);
        return obtenerCosteDeAsignacion(matrizCostes, asignacionOptima);
    }

    /**
     * Crea y devuelve una copia exacta de la matriz proporcionada.
     * 
     * @param matrizOriginal La matriz que se va a clonar.
     * @return Una nueva matriz que es una copia exacta de la matriz original.
     */
    private double[][] clonarMatriz(double[][] matrizOriginal) {
        int length = matrizOriginal.length;
        double[][] copiaMatriz = new double[length][length];
        for (int i = 0; i < length; ++i) {
            copiaMatriz[i] = matrizOriginal[i].clone();
        }
        return copiaMatriz;
    }

    /**
     * Resta el valor minimo de cada fila de la matriz dada de todos los elementos
     * de esa fila.
     * Utilizado para normalizar la matriz en el algoritmo hungaro.
     * 
     * @param matriz La matriz a la que se le restara el minimo de cada fila.
     */
    private void restarMinimoDeCadaFilaEnMatriz(double[][] matriz) {
        int length = matriz.length;
        for (int i = 0; i < length; ++i) {
            double min = Double.MAX_VALUE;
            for (int j = 0; j < length; ++j) {
                min = Math.min(matriz[i][j], min);
            }
            for (int j = 0; j < length; ++j) {
                matriz[i][j] -= min;
            }
        }
    }

    /**
     * Resta el valor minimo de cada columna de la matriz dada de todos los
     * elementos de esa columna.
     * Utilizado para normalizar la matriz en el algoritmo hungaro despues de restar
     * los minimos de las filas.
     * 
     * @param matriz La matriz a la que se le restara el minimo de cada columna.
     */
    private void restarMinimoDeCadaColumnaEnMatriz(double[][] matriz) {
        int length = matriz.length;
        for (int j = 0; j < length; ++j) {
            double min = Double.MAX_VALUE;
            for (int i = 0; i < length; ++i) {
                min = Math.min(matriz[i][j], min);
            }
            for (int i = 0; i < length; ++i) {
                matriz[i][j] -= min;
            }
        }
    }

    /**
     * Realiza el preprocesamiento de la matriz para prepararla para el algoritmo.
     * 
     * @param matriz La matriz de entrada a ser preprocesada
     */
    private void preprocesarMatriz(double[][] matriz) {
        restarMinimoDeCadaFilaEnMatriz(matriz);
        restarMinimoDeCadaColumnaEnMatriz(matriz);
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
     * Funcion recursiva que obtiene la asignacion maxima de ceros tales que no
     * estan en la misma fila ni columna.
     *
     * @param esCero              La matriz booleana que indica celdas con valor
     *                            cero
     * @param filaCubierta        Array de booleanos indicando filas con un cero
     *                            asignado
     * @param colCubierta         Array de booleanos indicando columnas con un cero
     *                            asignado
     * @param countCubiertos      Contador de celdas cubiertas
     * @param esCeroAsignado      La matriz booleana de asignacion marcada
     * @param mejorCountCubiertos Mejor contador de celdas cubiertas
     * @param mejorEsCeroAsignado Mejor matriz booleana de asignacion marcada
     * @param fila                Indice de fila actual para la recursion
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
        if (mejorCountCubiertos[0] == n)
            return;
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

        int[] mejorCountCubiertos = { 0 };
        boolean[][] mejorEsCeroAsignado = new boolean[n][n];
        obtenerMejorAsignacionRecursivo(esCero, filaCubierta, colCubierta, 0,
                esCeroAsignado, mejorCountCubiertos, mejorEsCeroAsignado, 0);
        return mejorEsCeroAsignado;
    }

    /**
     * Marca las filas de la matriz que no tienen un cero asignado.
     * 
     * @param matriz La matriz en la que se marcaran las filas.
     * @return Un array de booleanos donde cada elemento representa si una fila esta
     *         marcada.
     */
    private void marcarFilasSinCeroAsignado(boolean[][] esCeroAsignado, boolean[] filaMarcada) {
        int n = esCeroAsignado.length;
        for (int i = 0; i < n; ++i) {
            boolean contieneCeroAsignado = false;
            for (int j = 0; j < n && !contieneCeroAsignado; ++j) {
                if (esCeroAsignado[i][j])
                    contieneCeroAsignado = true;
            }
            if (!contieneCeroAsignado)
                filaMarcada[i] = true;
        }
    }

    /**
     * Marca las columnas que tienen un cero en cualquier fila marcada.
     * 
     * @param matriz        La matriz en la que se marcaran las columnas.
     * @param filasMarcadas Array de booleanos indicando las filas marcadas.
     * @return Un array de booleanos donde cada elemento representa si una columna
     *         esta marcada.
     */
    private void marcarColumnasConCeroEnFilaMarcada(boolean[][] esCero, boolean[] esFilaMarcada, boolean[] esColMarcada,
            boolean[] existeCambio) {
        int n = esCero.length;
        for (int j = 0; j < n; ++j) {
            boolean contieneCeroEnFilaMarcada = false;
            for (int i = 0; i < n && !contieneCeroEnFilaMarcada; ++i) {
                if (esCero[i][j] && esFilaMarcada[i])
                    contieneCeroEnFilaMarcada = true;
            }
            if (contieneCeroEnFilaMarcada && !esColMarcada[j]) {
                esColMarcada[j] = true;
                existeCambio[0] = true;
            }
        }
    }

    /**
     * Marca las filas que tienen un cero asignado en cualquier columna marcada.
     * 
     * @param matriz           La matriz en la que se marcaran las filas.
     * @param columnasMarcadas Array de booleanos indicando las columnas marcadas.
     * @return Un array de booleanos donde cada elemento representa si una fila esta
     *         marcada.
     */
    private void marcarFilasConCeroAsignadoEnColumnaMarcada(boolean[][] esCeroAsignado, boolean[] esFilaMarcada,
            boolean[] esColMarcada, boolean[] existeCambio) {
        int n = esCeroAsignado.length;
        for (int i = 0; i < n; ++i) {
            if (esFilaMarcada[i])
                continue;
            for (int j = 0; j < n && !esFilaMarcada[i]; ++j) {
                if (esColMarcada[j] && esCeroAsignado[i][j] && !esFilaMarcada[i]) {
                    esFilaMarcada[i] = true;
                    existeCambio[0] = true;
                }
            }
        }
    }

    /**
     * Calcula el numero minimo de lineas (filas o columnas) necesarias para cubrir
     * todos los ceros en una matriz.
     * 
     * @param matriz La matriz sobre la cual se realizara el calculo.
     * @return El numero minimo de lineas necesarias para cubrir todos los ceros.
     */

    private int calcularLineasUsadasParaCubrirCeros(boolean[] filaCubierta, boolean[] colCubierta,
            boolean[] esFilaMarcada, boolean[] esColMarcada) {
        int n = filaCubierta.length;
        int numLineas = 0;
        Arrays.fill(filaCubierta, false);
        Arrays.fill(colCubierta, false);
        for (int i = 0; i < n; ++i) {
            if (esColMarcada[i]) {
                ++numLineas;
                colCubierta[i] = true;
            }
            if (!esFilaMarcada[i]) {
                ++numLineas;
                filaCubierta[i] = true;
            }
        }
        return numLineas;
    }

    /**
     * Obtiene el numero minimo de lineas para cubrir celdas con valor cero.
     * 
     * @param esCero         La matriz booleana que indica celdas con valor cero
     * @param esCeroAsignado La matriz booleana de asignacion marcada
     * @param filaCubierta   Array de booleanos indicando filas cubiertas
     * @param colCubierta    Array de booleanos indicando columnas cubiertas
     * @return El numero minimo de lineas necesarias para cubrir los ceros
     */
    private int calcularMinNumLineasParaCubrirCeros(boolean[][] esCero, boolean[][] esCeroAsignado,
            boolean[] filaCubierta,
            boolean[] colCubierta) {
        int n = esCero.length;
        boolean[] esFilaMarcada = new boolean[n];
        boolean[] esColMarcada = new boolean[n];

        marcarFilasSinCeroAsignado(esCeroAsignado, esFilaMarcada);

        boolean[] existeCambio = { true };
        while (existeCambio[0]) {
            existeCambio[0] = false;
            marcarColumnasConCeroEnFilaMarcada(esCero, esFilaMarcada, esColMarcada, existeCambio);
            marcarFilasConCeroAsignadoEnColumnaMarcada(esCeroAsignado, esFilaMarcada, esColMarcada, existeCambio);
        }
        return calcularLineasUsadasParaCubrirCeros(filaCubierta, colCubierta, esFilaMarcada, esColMarcada);
    }

    /**
     * Obtiene el minimo valor no cubierto en la matriz.
     * 
     * @param M            La matriz de entrada
     * @param filaCubierta Array de booleanos indicando filas cubiertas
     * @param colCubierta  Array de booleanos indicando columnas cubiertas
     * @return El valor minimo no cubierto en la matriz
     */
    private double calcularElementoMinimoNoCubierto(double[][] M, boolean[] filaCubierta,
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
     * @param M            La matriz de entrada
     * @param filaCubierta Array de booleanos indicando filas cubiertas
     * @param colCubierta  Array de booleanos indicando columnas cubiertas
     * @param minimo       El valor minimo a ser sumado y restado
     */
    private void sumarYRestarMinimoAElementosCubiertos(double[][] M, boolean[] filaCubierta,
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
     * Calcula la asignacion optima basada en la matriz procesada, minimizando el
     * costo total o maximizando el beneficio total.
     * Este es el resultado final del algoritmo hungaro.
     * 
     * @param matriz La matriz que ha sido procesada por el algoritmo hungaro.
     * @return Un array que representa la asignacion optima de los elementos.
     */
    private boolean[][] calcularAsignacionOptima(double[][] matriz) {
        int n = matriz.length;
        boolean[][] esCero = obtenerMatrizCero(matriz);
        boolean[][] esCeroMarcado = obtenerMejorAsignacion(esCero);
        boolean[] filaCubierta = new boolean[n];
        boolean[] colCubierta = new boolean[n];
        int numLineas = calcularMinNumLineasParaCubrirCeros(esCero, esCeroMarcado, filaCubierta, colCubierta);

        while (numLineas != n) {
            double minimo = calcularElementoMinimoNoCubierto(matriz, filaCubierta, colCubierta);
            sumarYRestarMinimoAElementosCubiertos(matriz, filaCubierta, colCubierta, minimo);
            esCero = obtenerMatrizCero(matriz);
            esCeroMarcado = obtenerMejorAsignacion(esCero);
            numLineas = calcularMinNumLineasParaCubrirCeros(esCero, esCeroMarcado, filaCubierta, colCubierta);
        }
        return esCeroMarcado;
    }

    /**
     * Calcula el coste minimo dada una matriz y su correspondiente asignacion.
     * 
     * @param matriz    La matriz de costes
     * @param esMarcado La asignacion marcada en la matriz
     * @return El coste de la asignacion
     */
    private double obtenerCosteDeAsignacion(double[][] matriz, boolean[][] esMarcado) {
        double costeDeAsignacion = 0.0;
        for (int i = 0; i < matriz.length; ++i) {
            for (int j = 0; j < matriz[i].length; ++j) {
                if (esMarcado[i][j])
                    costeDeAsignacion += matriz[i][j];
            }
        }
        return costeDeAsignacion;
    }

}
