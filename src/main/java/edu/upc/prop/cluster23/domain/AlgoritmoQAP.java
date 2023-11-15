package edu.upc.prop.cluster23.domain;

import java.util.*;

public class AlgoritmoQAP implements Algoritmo {
    private Map<String, Integer> frecuenciasCjts;
    private Posicion[] posiciones;
    private double[][] distancias;
    private char[] caracteres;
    int n;
    int rows;
    int cols;
    private Map<Character, Integer> simbolosEmplazados;
    private double mejorCosteTotal;
    private Map<Character, Integer> mejorDistribucion;
    char[][] distribucion;

    @Override
    public char[][] generarDistribucion(Alfabeto alf, PalabrasConFrecuencia palabras, Texto texto) {
        procesarInput(alf, palabras, texto);
        // branchAndBound(0, 0.0);
        
        // distribucion = new char[rows][cols];
        // for (Map.Entry<Character, Integer> entry : mejorDistribucion.entrySet()) {
        //     char c = entry.getKey();
        //     Posicion p = posiciones[entry.getValue()];
        //     distribucion[p.fila][p.col] = c;
        // }

        char[][] distribucion = new char[3][10];
        Arrays.fill(distribucion[0],'-');
        Arrays.fill(distribucion[1],'-');
        Arrays.fill(distribucion[2],'-');
        return distribucion;
    }


    private void procesarInput(Alfabeto alf, PalabrasConFrecuencia palabras, Texto texto) {
        n = alf.getCaracteres().size();
        inicializarFrecuenciasCjts(alf, palabras, texto);
        inicializarPosiciones();
        inicializarDistancias();
        inicializarCaracteres(alf);
        simbolosEmplazados = new HashMap<>();
        mejorCosteTotal = Double.MAX_VALUE;
        mejorDistribucion = new HashMap<>();
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
    
    private void branchAndBound(int posIndex, double costeActual) {
        // Lazy implementation
        if (posIndex == n) {
            if (costeActual < mejorCosteTotal) {
                mejorDistribucion = new HashMap<>(simbolosEmplazados);
                mejorCosteTotal = costeActual;
            } 
        }

        for (int i = 0; i < n; ++i) {
            char c = caracteres[i];
            if (simbolosEmplazados.containsKey(c)) continue;

            // calcular coste actual poniendo el simbolo c en p
            double newCosteACtual = costeActual + calcularCosteRealDeEmplazar(c, posIndex);
            // importante poner despues de calcularCosteRealDeEmplazar
            simbolosEmplazados.put(c, posIndex);

            // actualizar posicion y hacer branch
            branchAndBound(posIndex + 1, newCosteACtual);

            simbolosEmplazados.remove(c);
        }
    }

    private double calcularCosteEntreDosCaracteres(char c1, int posIndex1, char c2, int posIndex2) {
        double distancia = distancias[posIndex1][posIndex2];
        String key = calcularKey(c1, c2);
        double frec = (double) frecuenciasCjts.get(key);
        return frec*distancia;
    }
    
    // Calcula el coste de poner una simbolo c en la posicion respecto a
    // todos los simbolos ya emplazados
    private double calcularCosteRealDeEmplazar(char c, int posIndex) {
        double sum = 0.0;
        for (Map.Entry<Character, Integer> entry : simbolosEmplazados.entrySet()) {
            char c1 = entry.getKey();
            int posIndex1 = entry.getValue();
            sum += calcularCosteEntreDosCaracteres(c, posIndex, c1, posIndex1);
        }
        return sum;
    }

    private String calcularKey(char c1, char c2) {
        if (c1 < c2) return "" + c1 + c2;
        return "" + c2 + c1;
    }
}
