package edu.upc.prop.cluster23.domain;

import java.util.*;

public class AlgoritmoQAP implements Algoritmo {
    @Override
    public char[][] generarDistribucion(Alfabeto alf, PalabrasConFrecuencia palabras, Texto texto) {
        Map<String, Integer> frecuenciasCjts = procesarInput(alf, palabras, texto);

        char[][] chars = new char[3][10];

        Arrays.fill(chars[0],'-');
        Arrays.fill(chars[1],'-');
        Arrays.fill(chars[2],'-');

        /*
        // Implement the QAP algorithm using frecuenciasCjts

        // We should start off by creating a greedy solution to the problem
        // To reduce the computational cost of the algorithm
        LinkedHashMap<String, Integer> sortedFreqs = new LinkedHashMap<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : frecuenciasCjts.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list, Collections.reverseOrder());
        for (int num : list) {
            for (Map.Entry<String, Integer> entry : frecuenciasCjts.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedFreqs.put(entry.getKey(), num);
                }
            }
        }

        // We have now sorted the values of the list, we'll traverse through
        // them until we've written all characters in the keyboard

        int i = 1, j = 14;

        // Having built a greedy solution, we have now eliminated various branches
        // We now use the algorithm

        float cost = 99999;

        ArrayList<Character> alfChar = new ArrayList<Character>();
        Iterator<Character> it = alfChar.iterator();

        while (it.hasNext()) {

        }

        */

        return chars;
    }


    private Map<String, Integer> procesarInput(Alfabeto alf, PalabrasConFrecuencia palabras, Texto texto) {
        // Process the input parameters (alf, palabras, texto) and generate frecuenciasCjts
        Map<String, Integer> freqs = new HashMap<>();

        int total = 0; // total de caractéres

        ArrayList<Character> alfChar = new ArrayList<Character>();
        alfChar = alf.getCaracteres();

        for (int i = 0; i < alfChar.size(); ++i) {
            for (int j = i + 1; j < alfChar.size(); ++j) {
                freqs.put("" + alfChar.get(i) + alfChar.get(j), 0);
            }
        }
        String Texto = texto.getTexto();
        String TextoN = Texto.replaceAll("\\s","");

        for (int i = 0; i < Texto.length() - 1; ++i) {
            String a = "" + Texto.charAt(i) + Texto.charAt(i+1);
            if (freqs.containsKey(a)) freqs.put(a, freqs.get(a) + 1);
            else {
                StringBuffer b = new StringBuffer(a);
                b.reverse();
                String c = b.toString();
                if (freqs.containsKey(c)) freqs.put(c, freqs.get(c) + 1);
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
                if (freqs.containsKey(a)) freqs.put(a, freqs.get(a) + num);
                else {
                    StringBuffer b = new StringBuffer(a);
                    b.reverse();
                    String c = b.toString();
                    if (freqs.containsKey(c)) freqs.put(c, freqs.get(c) + 1);
                }
            }
            total += (pal.length() - 1) * num;
        }
        return freqs;
    }
}
