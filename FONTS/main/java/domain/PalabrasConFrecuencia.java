package domain;

import java.util.Map;

import exceptions.FrecuenciaIncorrectaExcepcion;

import java.util.LinkedHashMap;

/**
 * Clase PalabrasConFrecuencia
 * Representa una colección de palabras y sus frecuencias.
 * 
 * @author Jianing Xu (jianing.xu@estudiantat.upc.edu)
 */
public class PalabrasConFrecuencia {
    /** Mapa que contiene una coleccion de palabras y sus frecuencias */
    private Map<String, Integer> map;

    // ----- Constructoras -----

    /**
     * Construye una nueva instancia de PalabrasConFrecuencia con texto vacío y un
     * mapa de frecuencias vacío.
     */
    public PalabrasConFrecuencia() {
        this.map = new LinkedHashMap<String, Integer>();
    }

    /**
     * Construye una nueva instancia de PalabrasConFrecuencia utilizando el texto
     * proporcionado.
     *
     * @param input El texto de entrada en el formato "palabra frecuencia\n"
     *              que se repite.
     */
    public PalabrasConFrecuencia(String input) throws FrecuenciaIncorrectaExcepcion {

        String[] palabrasSeparadas = input.split(" ");
        int words = palabrasSeparadas.length;

        if (words % 2 == 1 && !input.isEmpty())
            throw new FrecuenciaIncorrectaExcepcion(
                    "El formato de palabras con frecuencia no es correcto, debe ser palabras seguidas de un espacio y su número de frecuencia.");
        try {
            this.map = convertStringToMap(input);
        } catch (NumberFormatException e) {
            throw new FrecuenciaIncorrectaExcepcion(
                    "El formato de palabras con frecuencia no es correcto, debe ser palabras seguida de un espacio y su número de frecuencia.");
        } catch (IllegalArgumentException i) {
            throw new FrecuenciaIncorrectaExcepcion(i.getMessage());
        }
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
        if (this.map.isEmpty())
            return "";
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : this.map.entrySet()) {
            result.append(entry.getKey()).append(" ").append(entry.getValue()).append(" ");
        }
        String res = result.substring(0, result.length() - 1);
        return res;
    }

    // ----- Utilidades -----

    /**
     * Convierte una cadena en el formato "<palabra> <frecuencia>\n" en un TreeMap
     * de pares palabra-frecuencia.
     *
     * @param input La cadena de entrada que se va a convertir en el formato
     *              "<palabra> <frecuencia>\n".
     * @return Un TreeMap que contiene pares de palabra y frecuencia.
     */
    private static Map<String, Integer> convertStringToMap(String input) {
        Map<String, Integer> resultMap = new LinkedHashMap<String, Integer>();

        String[] lines = input.split(" ");

        if (lines.length > 1) {
            for (int i = 0; i < lines.length; i += 2) {
                String key = lines[i];
                if (key.trim().isEmpty())
                    throw new IllegalArgumentException("Una palabra no puede ser vacia.");
                int value = Integer.parseInt(lines[i + 1]);
                resultMap.put(key, value);
            }
        }

        return resultMap;
    }
}