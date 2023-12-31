package domain;

import java.util.*;

/**
 * Clase AlgoritmoSimulatedAnnealing
 * Clase que utiliza el algoritmo Simulated Annealing para crear una
 * distribución de teclado
 * 
 * @author Rubén Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 */

public class AlgoritmoSimulatedAnnealing implements Algoritmo {

    /**
     * Mapa que guarda la frecuencia de los bigramas
     */
    private Map<String, Integer> bigramasConFrecuencia;

    /**
     * Array de las posiciones del teclado
     */
    private Posicion[] posiciones;

    /**
     * Matriz que contiene la distancia entre posiciones
     */
    private double[][] distancias;

    /**
     * Array que guarda los caractéres a utilizar
     */
    private char[] caracteres;

    /**
     * Número de caractéres
     */
    private int numCaracteres;

    /**
     * Número de filas de la distribución
     */
    private int rows;

    /**
     * Número de columnas de la distribución
     */
    private int cols;

    /**
     * Coste de la mejor distribución hasta el momento
     */
    private double mejorCosteTotal;

    /**
     * Mapa que guarda la distribución para luego convertirla en la matriz a
     * retornar
     */
    private Map<Character, Integer> mejorDistribucion;

    /**
     * Generador de números aleatorios
     */
    private Random rand;

    /**
     * Constructor por defecto que inicializa la clase con una semilla aleatoria
     * para el generador.
     */
    public AlgoritmoSimulatedAnnealing() {
        rand = new Random();
    }

    /**
     * Constructor que permite especificar una semilla para el generador aleatorio.
     * 
     * @param seedValue Valor de la semilla para el generador aleatorio.
     */
    public AlgoritmoSimulatedAnnealing(long seedValue) {
        rand = new Random(seedValue);
    }

    /**
     * Función que llama a los métodos necesarios para generar una distribución del
     * teclado
     * 
     * @param alfabeto              Alfabeto del que se extraen los caractéres
     * @param bigramasConFrecuencia Mapa que guarda la frecuencia de los bigramas
     * @return Matriz de caractéres con la distribución optimizada (no
     *         necesariamente la mejor) del teclado
     */
    @Override
    public Character[][] generarDistribucion(Alfabeto alfabeto, Map<String, Integer> bigramasConFrecuencia) {
        Character[][] distribucion;
        if (alfabeto.getCaracteres().size() > 1) {
            this.bigramasConFrecuencia = bigramasConFrecuencia;
            inicializar(alfabeto);
            distribucion = new Character[rows][cols];
            for (Map.Entry<Character, Integer> entry : mejorDistribucion.entrySet()) {
                char c = entry.getKey();
                Posicion p = posiciones[entry.getValue()];
                distribucion[p.fila][p.col] = c;
            }
        } else {
            distribucion = new Character[1][10];
            distribucion[0][0] = alfabeto.getCaracteres().get(0);
        }
        return distribucion;
    }

    /**
     * Procesa los diferentes parámetros de la entrada y llama a la función que
     * genera la distribución
     * 
     * @param alfabeto Alfabeto del que se extraen los caractéres
     */
    private void inicializar(Alfabeto alfabeto) {
        numCaracteres = alfabeto.getCaracteres().size();
        inicializarPosiciones();
        inicializarDistancias();
        inicializarCaracteres(alfabeto);
        mejorCosteTotal = Double.MAX_VALUE;
        mejorDistribucion = new HashMap<>();
        SimulatedAnnealing();
    }

    /**
     * Calcula una distribución bastante optimizada (pero no necesariamente la
     * mejor)
     * para el teclado usando el algoritmo de búsqueda local "Simulated Annealing"
     */
    private void SimulatedAnnealing() {
        // cada posición representa a donde iría el símbolo en la configuración final.
        // Posición 0, valor 3 significa
        // que el caracter 0 va en la posición 3 del teclado
        int[] mejorActual = new int[numCaracteres];
        // Inicializamos la distribución a una completa en un orden cualquiera
        for (int i = 0; i < numCaracteres; ++i) {
            mejorActual[i] = i;
        }

        double costeActual = 0.0;
        costeActual = calculoCoste(mejorActual);
        if (costeActual < mejorCosteTotal)
            mejorCosteTotal = costeActual;

        int[] valActual = new int[numCaracteres];

        // Inicializamos los valores de temperatura e iteraciones por valor de
        // temperatura
        double temperatura = 100;
        int iteraciones = 10000;
        while (temperatura > 1.0) {
            for (int i = 0; i < iteraciones; ++i) {
                costeActual = 0.0;

                System.arraycopy(mejorActual, 0, valActual, 0, numCaracteres);

                int caracterRandom1 = rand.nextInt(numCaracteres);
                int caracterRandom2 = rand.nextInt(numCaracteres);

                while (caracterRandom2 == caracterRandom1) {
                    caracterRandom2 = rand.nextInt(numCaracteres);
                }

                int aux = mejorActual[caracterRandom1];

                valActual[caracterRandom1] = mejorActual[caracterRandom2];
                valActual[caracterRandom2] = aux;

                costeActual = calculoCoste(valActual);

                if (costeActual < mejorCosteTotal) {
                    mejorCosteTotal = costeActual;
                    System.arraycopy(valActual, 0, mejorActual, 0, numCaracteres);
                } else {
                    double probabilidad = Math.pow(Math.E, (mejorCosteTotal - costeActual) / temperatura);
                    if (probabilidad > rand.nextDouble()) {
                        mejorCosteTotal = costeActual;
                        System.arraycopy(valActual, 0, mejorActual, 0, numCaracteres);
                    }
                }
            }
            temperatura *= 0.9;
        }
        for (int i = 0; i < numCaracteres; ++i) {
            mejorDistribucion.put(caracteres[i], mejorActual[i]);
        }
    }

    /**
     * Calcula el coste de la distribución actual
     * 
     * @param valActual Matriz de enteros donde cada una guarda la posición en la
     *                  que se encontraría
     *                  su respectivo carácter
     * @return El coste de la distribución
     */
    private double calculoCoste(int[] valActual) {
        double costeActual = 0.0;
        for (int i = 0; i < numCaracteres; ++i) {
            for (int j = i + 1; j < numCaracteres; ++j) {
                costeActual += calcularCosteEntreDosCaracteres(caracteres[i], valActual[i], caracteres[j],
                        valActual[j]);
            }
        }
        return costeActual;
    }

    /**
     * Inicializa el array de posiciones y calcula cuantas filas y columnas
     * requerirá la distribución
     */
    private void inicializarPosiciones() {
        posiciones = new Posicion[numCaracteres];
        if (numCaracteres <= 2) {
            rows = 1;
            cols = numCaracteres;
        } else if (numCaracteres <= 6) {
            rows = 2;
            cols = (int) Math.ceil((double) numCaracteres / (double) rows);
        } else {
            rows = 3;
            cols = (int) Math.ceil((double) numCaracteres / (double) rows);
        }
        Posicion p = new Posicion(0, 0);
        for (int i = 0; i < numCaracteres; ++i) {
            posiciones[i] = new Posicion(p);
            if (p.col < cols - 1)
                ++p.col;
            else {
                ++p.fila;
                p.col = 0;
            }
        }
    }

    /**
     * Inicializa la matriz de distancias entre posiciones.
     *
     */
    private void inicializarDistancias() {
        int n = posiciones.length;
        distancias = new double[n][n];
        for (int i = 0; i < n; ++i) {
            Posicion p1 = posiciones[i];
            for (int j = 0; j < n; ++j) {
                Posicion p2 = posiciones[j];
                distancias[i][j] = p1.euclidianDistanceTo(p2);
            }
        }
    }

    /**
     * Inicializa el array de caractéres
     * 
     * @param alf Alfabeto del cual se extraen los caractéres
     */
    private void inicializarCaracteres(Alfabeto alf) {
        caracteres = new char[numCaracteres];
        ArrayList<Character> car = alf.getCaracteres();
        for (int i = 0; i < numCaracteres; ++i) {
            caracteres[i] = car.get(i);
        }
    }

    /**
     * Calcula el coste entre dos posiciones, con símbolos distintos
     * 
     * @param c1        Símbolo 1
     * @param posIndex1 Posición símbolo 1
     * @param c2        Símbolo 2
     * @param posIndex2 Posición símbolo 2
     * @return Coste entre las posiciones
     */
    private double calcularCosteEntreDosCaracteres(char c1, int posIndex1, char c2, int posIndex2) {
        double distancia = distancias[posIndex1][posIndex2];
        String key = calcularKey(c1, c2);
        double frec = (double) bigramasConFrecuencia.get(key);
        return frec * distancia;
    }

    /**
     * Calcula la clave para la frecuencia de bigramas.
     * 
     * @param c1 Caracter 1.
     * @param c2 Caracter 2.
     * @return Clave para la frecuencia de bigramas.
     */
    private String calcularKey(char c1, char c2) {
        if (bigramasConFrecuencia.containsKey("" + c1 + c2))
            return "" + c1 + c2;
        return "" + c2 + c1;
    }

    /**
     * Obtiene el coste total de la mejor distribución generada por el algoritmo.
     * 
     * @return El coste total de la mejor distribución.
     */
    public double getMejorCosteTotal() {
        return this.mejorCosteTotal;
    }
}
