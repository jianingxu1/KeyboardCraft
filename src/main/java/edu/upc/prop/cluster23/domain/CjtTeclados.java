package edu.upc.prop.cluster23.domain;

import java.util.HashMap;


// clase creada por Rubén Catalán Rua
public class CjtTeclados {
    private HashMap<String, Teclado> conjunto;

    public CjtTeclados() {
        conjunto = new HashMap<String, Teclado>();
    }

    public void crearTeclado(String nombre, char[][] mat) {

        if (!conjunto.containsKey(nombre)) conjunto.put(nombre, new Teclado(nombre, mat));
    }

    public void eliminarTeclado(String nombre) {
        conjunto.remove(nombre);
    }

    public int size() {
        return conjunto.size();
    }

    public void intercambiarTeclasTeclado(String nombre, int i1, int j1, int i2, int j2) {
        conjunto.get(nombre).intercambiarTeclas(i1, j1, i2, j2);
    }

    public void setDistribucionTeclado(String nombre, char[][] dist) {
        conjunto.get(nombre).setDistribucion(dist);
    }

    public boolean existeTeclado(String nombre) {
        return conjunto.containsKey(nombre);
    }

    public char[][] getTeclado(String nombre) {
        return conjunto.get(nombre).getTeclado();
    }
}

