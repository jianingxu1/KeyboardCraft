package edu.upc.prop.cluster23.domain;

import java.util.HashMap;


// clase creada por Rubén Catalán Rua
public class Cjt_teclados {
    private HashMap<String, Teclado> conjunto;


    public Cjt_teclados() {
        conjunto = new HashMap<String, Teclado>();
    }

    public void crearTeclado(String nombre, char[][] mat) {
        conjunto.put(nombre, new Teclado(nombre, mat));
    }

    public void borrarTeclado(String nombre) {
        conjunto.remove(nombre);
    }

    public boolean existe(String nombre) {
        return conjunto.containsKey(nombre);
    }

    public char[][] getTeclado(String nombre) {
        return conjunto.get(nombre).getTeclado();
    }

    public void swap_teclas(String nombre, int i1, int j1, int i2, int j2) {
        conjunto.get(nombre).swap_teclas(i1,j1,i2,j2);
    }

    public void actualizarDist(String nombre, char[][] dist) {
        conjunto.get(nombre).actualizarDist(dist);
    }
}

