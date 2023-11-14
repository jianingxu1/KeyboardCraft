package edu.upc.prop.cluster23.domain;

import java.util.*;

public class AlgoritmoQAP implements Algoritmo {
    private Map<String, Integer> frecuenciasCjts;
    private Posicion[] posiciones;

    private class Posicion {
        int fila;
        int col;
        Posicion(int fila, int col) {
            this.fila = fila;
            this.col = col;
        }
        Posicion(Posicion p) {
            this.fila = p.fila;
            this.col = p.col;
        }
        public double euclidianDistanceTo(Posicion p) {
            double deltaX = (double)(this.fila - p.fila);
            double deltaY = (double)(this.col - p.col);
            return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        }
    }

    @Override
    public char[][] generarDistribucion(Alfabeto alf, PalabrasConFrecuencia palabras, Texto texto) {
        procesarInput(alf, palabras, texto);
        char[][] chars = new char[3][10];
        Arrays.fill(chars[0],'-');
        Arrays.fill(chars[1],'-');
        Arrays.fill(chars[2],'-');
        return chars;
    }


    private void procesarInput(Alfabeto alf, PalabrasConFrecuencia palabras, Texto texto) {
        inicializarFrecuenciasCjts(alf, palabras, texto);
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

    public void inicializarPosiciones(int n) {
        posiciones = new Posicion[n];
        int rows, cols;
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
}
