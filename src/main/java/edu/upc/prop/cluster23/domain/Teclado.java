package edu.upc.prop.cluster23.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


// clase creada por Rubén Catalán Rua
public class Teclado {
	
	/** Atributos **/

	private String nombre;

	private char[][] distribucion;
	private Alfabeto alfabeto;
	private ListaPalabras listaPalabras;
	private Texto texto;
	static QAP QAPalg = new QAP();
	static ALG2 alg2 = new ALG2();

	/** Constructora **/
	public Teclado(String nombre, Alfabeto alfabeto, ListaPalabras listaPalabras, Texto texto) {
		this.nombre = nombre;
		this.alfabeto = alfabeto;
		this.listaPalabras = listaPalabras;
		this.texto = texto;
	}
	
	/** Métodos públicos **/

	public char [][] getTeclado() {
		return distribucion;
	}

	public void swap_teclas(int i1, int j1, int i2, int j2) {
		char temp = distribucion[i2][j2];

		distribucion[i2][j2] = distribucion[i1][j1];
		distribucion[i1][j1] = temp;
	}

	public void generarDistribucion(int algo) {
		if (algo > 2 || algo < 1) System.out.println("Valor inválido.");
		else if (algo == 1) distribucion = QAPalg.generarDistribucion(alfabeto, listaPalabras, texto);
		else distribucion = alg2.generarDistribucion(alfabeto, listaPalabras, texto);
	}

	public String getAlfabetoId() {
		return alfabeto.getId();
	}
	public String getListaId() {
		return listaPalabras.getId();
	}
	public String getTextoId() {
		return texto.getId();
	}
	
	/** Métodos redefinidos **/
	@Override
	public String toString() {
		return nombre;
	}

}
