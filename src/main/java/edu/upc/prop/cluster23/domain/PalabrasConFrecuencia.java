package edu.upc.prop.cluster23.domain;

import java.util.Map;
import java.util.LinkedHashMap;

/** Clase PalabrasConFrecuencia
 *  Representa una colección de palabras y sus frecuencias.
 *  @author Jianing Xu (jianing.xu@estudiantat.upc.edu)
 */
public class PalabrasConFrecuencia {
    /** Mapa que contiene una coleccion de palabras y sus frecuencias */
    private Map<String, Integer> map;

    // ----- Constructoras -----

    /** Construye una nueva instancia de PalabrasConFrecuencia con texto vacío y un mapa de frecuencias vacío. */
    public PalabrasConFrecuencia() {
        this.map = new LinkedHashMap<String, Integer>();
    }

    /**
     * Construye una nueva instancia de PalabrasConFrecuencia utilizando el texto proporcionado.
     *
     * @param input El texto de entrada en el formato "<palabra> <frecuencia>\n"
     * que se repite.
     */
    public PalabrasConFrecuencia(String input) {
        this.map = convertStringToMap(input);
    }

    // ----- Modificadoras -----

    /**
     * Cambia el mapa de frecuencias.
     *
     * @param input El nuevo texto de entrada.
     */
    public void cambiarMap(String input) {
        this.map = convertStringToMap(input);
    }

    // ----- Getters -----

    /**
     * Devuelve el mapa de frecuencias que contiene pares de palabras y frecuencias.
     *
     * @return El mapa de frecuencias.
     */
    public Map<String, Integer> getMap() {
        return this.map;
    }

    /**
     * Devuelve el texto en el que se basa.
     *
     * @return El texto en el que se basa.
     */
	@Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : this.map.entrySet()) {
            result.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }
        return result.toString();
    }    
    
    // ----- Utilidades -----
    
    /**
     * Convierte una cadena en el formato "<palabra> <frecuencia>\n" en un TreeMap de pares palabra-frecuencia.
     *
     * @param input La cadena de entrada que se va a convertir en el formato "<palabra> <frecuencia>\n".
     * @return Un TreeMap que contiene pares de palabra y frecuencia.
     */
    private static Map<String, Integer> convertStringToMap(String input) {
        Map<String, Integer> resultMap = new LinkedHashMap<String, Integer>();
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