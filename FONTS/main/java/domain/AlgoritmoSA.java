package domain;

import java.util.*;

/**
 * Clase AlgoritmoSA
 * Clase que utiliza el algoritmo Simulated Annealing para crear una
 * distribución de teclado
 * 
 * @author Rubén Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 */

public class AlgoritmoSA implements Algoritmo {

    /**
     * Mapa que guarda la frecuencia de los bigramas
     */
    private Map<String, Integer> frecuenciasCjts;

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
    private int n;

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
     * Matriz con la mejor distribución del teclado
     */
    private char[][] distribucion;

    /**
     * Mapa que guarda la distribución para luego convertirla en la matriz a retornar
     */
    private Map<Character, Integer> mejorDistribucion;


    /**
     * Función que llama a los métodos necesarios para generar una distribución del teclado
     * @param alf Alfabeto del que se extraen los caractéres
     * @param palabras lista de palabras con frecuencia para el cálculo de frecuencia de bigramas
     * @param texto contiene un String utilizado en el cálculo de frecuencias de bigramas
     * @return Matriz de caractéres con la distribución optimizada (no necesariamente la mejor) del teclado
     */
    @Override
    public char[][] generarDistribucion(Alfabeto alf, PalabrasConFrecuencia palabras, Texto texto) {

        if (alf.getCaracteres().size() > 1) {
            procesarInput(alf, palabras, texto);
            distribucion = new char[rows][cols];
            for (Map.Entry<Character, Integer> entry : mejorDistribucion.entrySet()) {
                char c = entry.getKey();
                Posicion p = posiciones[entry.getValue()];
                distribucion[p.fila][p.col] = c;
            }
        }

        else {
            distribucion = new char[1][10];
            distribucion[0][0] = alf.getCaracteres().get(0);
        }

        return distribucion;
    }

    /**
     * Procesa los diferentes parámetros de la entrada y llama a la función que genera la distribución
     * @param alf Alfabeto del que se extraen los caractéres
     * @param palabras lista de palabras con frecuencia para el cálculo de frecuencia de bigramas
     * @param texto contiene un String utilizado en el cálculo de frecuencias de bigramas
     */

    private void procesarInput(Alfabeto alf, PalabrasConFrecuencia palabras, Texto texto) {
        n = alf.getCaracteres().size();
        inicializarFrecuenciasCjts(alf, palabras, texto);
        inicializarPosiciones();
        inicializarDistancias();
        inicializarCaracteres(alf);
        mejorCosteTotal = Double.MAX_VALUE;
        mejorDistribucion = new HashMap<>();

        SimulatedAnnealing();
    }

    /**
     * Calcula una distribución bastante optimizada (pero no necesariamente la mejor)
     * para el teclado usando el algoritmo de búsqueda local "Simulated Annealing"
     */

    private void SimulatedAnnealing() {

        // por ahora, sol inicial en orden alfabético

        // cada posición representa a donde iría el símbolo en la configuración final.
        // Posición 0, valor 3 significa
        // que el caracter 0 va en la posición 3 del teclado
        int[] mejorActual = new int[n];

        for (int i = 0; i < n; ++i) {
            mejorActual[i] = i;
        }

        double costeActual = 0.0;

        costeActual = calculoCoste(mejorActual);

        if (costeActual < mejorCosteTotal)
            mejorCosteTotal = costeActual;

        // valores de temperatura e iteraciones por valor de temperatura

        double T = 100;

        int iters = 10000;

        int[] valActual = new int[n];

        Random rand = new Random();

        while (T > 1.0) {
            for (int i = 0; i < iters; ++i) {
                costeActual = 0.0;

                System.arraycopy(mejorActual, 0, valActual, 0, n);

                int a = rand.nextInt(n);

                int b = rand.nextInt(n);

                while (b == a) {
                    b = rand.nextInt(n);
                }

                int temp = mejorActual[a];

                valActual[a] = mejorActual[b];

                valActual[b] = temp;

                costeActual = calculoCoste(valActual);

                if (costeActual < mejorCosteTotal) {
                    mejorCosteTotal = costeActual;
                    System.arraycopy(valActual, 0, mejorActual, 0, n);
                } else {
                    double prob = Math.pow(Math.E, (mejorCosteTotal - costeActual) / T);

                    if (prob > Math.random()) {
                        mejorCosteTotal = costeActual;
                        System.arraycopy(valActual, 0, mejorActual, 0, n);
                    }
                }
            }

            T *= 0.9;
        }
        for (int i = 0; i < n; ++i) {
            mejorDistribucion.put(caracteres[i], mejorActual[i]);
        }
    }

    /**
     * Calcula el coste de la distribución actual
     * @param valActual Matriz de enteros donde cada una guarda la posición en la que se encontraría
     *                  su respectivo carácter
     * @return El coste de la distribución
     */

    private double calculoCoste(int[] valActual) {
        double costeActual = 0.0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                costeActual += calcularCosteEntreDosCaracteres(caracteres[i], valActual[i], caracteres[j],
                        valActual[j]);
            }
        }
        return costeActual;
    }

    /**
     * Obtiene las frecuencias de los bigramas en el texto.
     *
     * @param alf      Alfabeto a utilizar.
     * @param palabras Palabras con sus frecuencias.
     * @param texto    Texto para analizar.
     */

    private void inicializarFrecuenciasCjts(Alfabeto alf, PalabrasConFrecuencia palabras, Texto texto) {
        // Process the input parameters (alf, palabras, texto) and generate
        // frecuenciasCjts
        frecuenciasCjts = new HashMap<>();

        int total = 0; // total de caractéres

        ArrayList<Character> alfChar = new ArrayList<Character>();
        alfChar = alf.getCaracteres();

        for (int i = 0; i < alfChar.size(); ++i) {
            for (int j = i + 1; j < alfChar.size(); ++j) {
                frecuenciasCjts.put("" + alfChar.get(i) + alfChar.get(j), 0);
            }
        }
        String Texto = texto.getTexto();
        String TextoN = Texto.replaceAll("\\s", "");

        for (int i = 0; i < Texto.length() - 1; ++i) {
            String a = "" + Texto.charAt(i) + Texto.charAt(i + 1);
            if (frecuenciasCjts.containsKey(a))
                frecuenciasCjts.put(a, frecuenciasCjts.get(a) + 1);
            else {
                StringBuffer b = new StringBuffer(a);
                b.reverse();
                String c = b.toString();
                if (frecuenciasCjts.containsKey(c))
                    frecuenciasCjts.put(c, frecuenciasCjts.get(c) + 1);
            }
        }

        total = TextoN.length() - 1 - (Texto.length() - TextoN.length());

        Map<String, Integer> ListaPal = new LinkedHashMap<String, Integer>();

        ListaPal = palabras.getMap();

        for (Map.Entry<String, Integer> entry : ListaPal.entrySet()) {
            String pal = entry.getKey();
            Integer num = entry.getValue();
            for (int j = 0; j < pal.length() - 1; ++j) {
                String a = "" + pal.charAt(j) + pal.charAt(j + 1);
                if (frecuenciasCjts.containsKey(a))
                    frecuenciasCjts.put(a, frecuenciasCjts.get(a) + num);
                else {
                    StringBuffer b = new StringBuffer(a);
                    b.reverse();
                    String c = b.toString();
                    if (frecuenciasCjts.containsKey(c))
                        frecuenciasCjts.put(c, frecuenciasCjts.get(c) + num);
                }
            }
            total += (pal.length() - 1) * num;
        }
    }

    /**
     * Inicializa el array de posiciones y calcula cuantas filas y columans requerirá la distribución
     */

    private void inicializarPosiciones() {
        posiciones = new Posicion[n];
        if (n <= 2) {
            rows = 1;
            cols = n;
        } else if (n <= 6) {
            rows = 2;
            cols = (int) Math.ceil((double) n / (double) rows);
        } else {
            rows = 3;
            cols = (int) Math.ceil((double) n / (double) rows);
        }
        Posicion p = new Posicion(0, 0);
        for (int i = 0; i < n; ++i) {
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
     * @param alf Alfabeto del cual se extraen los caractéres
     */

    private void inicializarCaracteres(Alfabeto alf) {
        caracteres = new char[n];
        ArrayList<Character> car = alf.getCaracteres();
        for (int i = 0; i < n; ++i) {
            caracteres[i] = car.get(i);
        }
    }

    /**
     * Calcula el coste entre dos posiciones, con símbolos distintos
     * @param c1 Símbolo 1
     * @param posIndex1 Posición símbolo 1
     * @param c2 Símbolo 2
     * @param posIndex2 Posición símbolo 2
     * @return Coste entre las posiciones
     */

    private double calcularCosteEntreDosCaracteres(char c1, int posIndex1, char c2, int posIndex2) {
        double distancia = distancias[posIndex1][posIndex2];
        String key = calcularKey(c1, c2);
        double frec = (double) frecuenciasCjts.get(key);
        return frec * distancia;
    }

    /**
     * Calcula la clave para la frecuencia de bigramas.
     *@param c1              Caracter 1.
     * @param c2              Caracter 2.
     * @return Clave para la frecuencia de bigramas.
     */

    private String calcularKey(char c1, char c2) {
        if (frecuenciasCjts.containsKey("" + c1 + c2))
            return "" + c1 + c2;
        return "" + c2 + c1;
    }
}
