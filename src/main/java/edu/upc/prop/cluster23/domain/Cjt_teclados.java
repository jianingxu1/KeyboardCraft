package edu.upc.prop.cluster23.domain;

import java.util.HashMap;


// clase creada por Rubén Catalán Rua
public class Cjt_teclados {
    private HashMap<String, Teclado> conjunto;

    public Cjt_teclados() {
        conjunto = new HashMap<String, Teclado>();
    }

    public void crearTeclado(String nombre, Alfabeto alf, ListaPalabras LP, Texto T) {
        conjunto.put(nombre, new Teclado(nombre, alf, LP, T));
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

    public String getAlfabetoId(String nombre) {
        return conjunto.get(nombre).getAlfabetoId();
    }
    public String getListaId(String nombre) {
        return conjunto.get(nombre).getListaId();
    }
    public String getTextoId(String nombre) {
        return conjunto.get(nombre).getTextoId();
    }

    public void generarDist(String nombre, int alg) {
        conjunto.get(nombre).generarDistribucion(alg);
    }
}

