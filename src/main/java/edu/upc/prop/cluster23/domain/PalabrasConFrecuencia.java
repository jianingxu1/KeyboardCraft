package edu.upc.prop.cluster23.domain;

import java.util.TreeMap;

/** Clase PalabrasConFrecuencia
 *  Representa una colección de palabras y sus frecuencias.
 *  @author Jianing Xu (jianing.xu@estudiantat.upc.edu)
 */
public class PalabrasConFrecuencia {
    /** El texto de entrada en el que se basa. */
    private String texto;

    /** Mapa de Palabra a frecuencias */
    private TreeMap<String, Integer> map;

    // ----- Constructoras -----

    /** Construye una nueva instancia de PalabrasConFrecuencia con texto vacío y un mapa de frecuencias vacío. */
    public PalabrasConFrecuencia() {
        this.texto = new String();
        this.map = new TreeMap<String, Integer>();
    }

    /**
     * Construye una nueva instancia de PalabrasConFrecuencia utilizando el texto proporcionado.
     *
     * @param texto El texto de entrada en el formato "<palabra> <frecuencia>\n"
     * que se repite.
     */
    public PalabrasConFrecuencia(String texto) {
        this.texto = texto;
        this.map = convertStringToTreeMap(texto);
    }

    // ----- Modificadoras -----

    /**
     * Cambia el texto de entrada y actualiza el mapa de frecuencias en consecuencia.
     *
     * @param texto El nuevo texto de entrada.
     */
    public void cambiarTexto(String texto) {
        this.texto = texto;
        this.map = convertStringToTreeMap(texto);
    }

    // ----- Getters -----

    /**
     * Devuelve el mapa de frecuencias que contiene pares de palabras y frecuencias.
     *
     * @return El mapa de frecuencias.
     */
    public TreeMap<String, Integer> getMap() {
        return this.map;
    }

    /**
     * Devuelve el texto de entrada.
     *
     * @return El texto de entrada.
     */
    public String getTexto() {
        return this.texto;
    }

    // ----- Utilidades -----

    /**
     * Convierte una cadena en el formato "<palabra> <frecuencia>\n" en un TreeMap de pares palabra-frecuencia.
     *
     * @param input La cadena de entrada que se va a convertir en el formato "<palabra> <frecuencia>\n".
     * @return Un TreeMap que contiene pares de palabra y frecuencia.
     */
    private static TreeMap<String, Integer> convertStringToTreeMap(String input) {
        TreeMap<String, Integer> resultMap = new TreeMap<>();
        String[] lines = input.split("\n");
        for (String line : lines) {
            String[] parts = line.split(" ");
            if (parts.length == 2) {
                String key = parts[0];
                int value = Integer.parseInt(parts[1]);
                resultMap.put(key, value);
            }
        }
        return resultMap;
    }
}
