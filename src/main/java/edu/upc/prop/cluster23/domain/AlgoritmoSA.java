package edu.upc.prop.cluster23.domain;

import java.util.*;

/**
 * Clase AlgoritmoSA
 * Clase que utiliza el algoritmo Simulated Annealing para crear una distribución de teclado
 * @author ruben.catalan(ruben.catalan@estudiantat.upc.edu)
 */

public class AlgoritmoSA implements Algoritmo {
    private Map<String, Integer> frecuenciasCjts;
    private Posicion[] posiciones;
    private double[][] distancias;
    private char[] caracteres;
    int n;
    int rows;
    int cols;
    private double mejorCosteTotal;
    private Map<Character, Integer> mejorDistribucion;
    char[][] distribucion;

    @Override
    public char[][] generarDistribucion(Alfabeto alf, PalabrasConFrecuencia palabras, Texto texto) {
        procesarInput(alf, palabras, texto);
        // branchAndBound(0, 0.0);
        
        distribucion = new char[rows][cols];
        for (Map.Entry<Character, Integer> entry : mejorDistribucion.entrySet()) {
            char c = entry.getKey();
            Posicion p = posiciones[entry.getValue()];
            distribucion[p.fila][p.col] = c;
        }
        return distribucion;
    }


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

    private void SimulatedAnnealing() {

        // por ahora, sol inicial en orden alfabético

        // cada posición representa a donde iría el símbolo en la configuración final. Posición 0, valor 3 significa
        // que el caracter 0 va en la posición 3 del teclado
        int[] mejorActual = new int[n];

        for (int i = 0; i < n; ++i) {
            mejorActual[i] = i;
        }

        double costeActual = 0.0;

        costeActual = calculoCoste(mejorActual);

        if (costeActual < mejorCosteTotal) mejorCosteTotal = costeActual;

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
                }
                else {
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
        System.out.println(mejorCosteTotal);
    }

    private double calculoCoste(int[] valActual) {
        double costeActual = 0.0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                costeActual += calcularCosteEntreDosCaracteres(caracteres[i], valActual[i], caracteres[j], valActual[j]);
            }
        }
        return costeActual;
    }

    private void inicializarFrecuenciasCjts(Alfabeto alf, PalabrasConFrecuencia palabras, Texto texto) {
        // Process the input parameters (alf, palabras, texto) and generate frecuenciasCjts
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
        String TextoN = Texto.replaceAll("\\s","");

        for (int i = 0; i < Texto.length() - 1; ++i) {
            String a = "" + Texto.charAt(i) + Texto.charAt(i+1);
            if (frecuenciasCjts.containsKey(a)) frecuenciasCjts.put(a, frecuenciasCjts.get(a) + 1);
            else {
                StringBuffer b = new StringBuffer(a);
                b.reverse();
                String c = b.toString();
                if (frecuenciasCjts.containsKey(c)) frecuenciasCjts.put(c, frecuenciasCjts.get(c) + 1);
            }
        }

        total = TextoN.length() - 1 - (Texto.length() - TextoN.length());

        Map<String,Integer> ListaPal = new LinkedHashMap<String, Integer>();

        ListaPal = palabras.getMap();

        for (Map.Entry<String, Integer> entry : ListaPal.entrySet()) {
            String pal = entry.getKey();
            Integer num = entry.getValue();
            for (int j = 0; j < pal.length() - 1; ++j) {
                String a = "" + pal.charAt(j) + pal.charAt(j + 1);
                if (frecuenciasCjts.containsKey(a)) frecuenciasCjts.put(a, frecuenciasCjts.get(a) + num);
                else {
                    StringBuffer b = new StringBuffer(a);
                    b.reverse();
                    String c = b.toString();
                    if (frecuenciasCjts.containsKey(c)) frecuenciasCjts.put(c, frecuenciasCjts.get(c) + num);
                }
            }
            total += (pal.length() - 1) * num;
        }
    }

    public void inicializarPosiciones() {
        posiciones = new Posicion[n];
        if (n <= 2) {
            rows = 1;
            cols = n;
        }
        else if (n <= 6) {
            rows = 2;
            cols = (int) Math.ceil((double) n / (double)rows);
        }
        else {
            rows = 3;
            cols = (int) Math.ceil((double) n / (double)rows);
        }
        Posicion p = new Posicion(0, 0);
        for (int i = 0; i < n; ++i) {
            posiciones[i] = new Posicion(p);
            if (p.col < cols - 1) ++p.col;
            else {
                ++p.fila;
                p.col = 0;
            }
        }
    }

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

    private void inicializarCaracteres(Alfabeto alf) {
        caracteres = new char[n];
        ArrayList<Character> car = alf.getCaracteres();
        for (int i = 0; i < n; ++i) {
            caracteres[i] =  car.get(i);
        }
    }


    private double calcularCosteEntreDosCaracteres(char c1, int posIndex1, char c2, int posIndex2) {
        double distancia = distancias[posIndex1][posIndex2];
        String key = calcularKey(c1, c2);
        double frec = (double) frecuenciasCjts.get(key);
        return frec*distancia;
    }

    private String calcularKey(char c1, char c2) {
        if (frecuenciasCjts.containsKey("" + c1 + c2)) return "" + c1 + c2;
        return "" + c2 + c1;
    }
}
