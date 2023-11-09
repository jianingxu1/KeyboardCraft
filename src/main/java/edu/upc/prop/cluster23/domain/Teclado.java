package edu.upc.prop.cluster23.domain;

// clase creada por Rubén Catalán Rua
public class Teclado {
	
	/** Atributos **/

	private String nombre;

	private char[][] distribucion;


	/** Constructora **/
	public Teclado(String nombre, char[][] dist) {
		this.nombre = nombre;
		this.distribucion = dist;
	}
	
	/** Métodos públicos **/

	/**
	 * Devuelve la matriz con la distribución del teclado
	 * @return distribucion
	 */

	public char [][] getTeclado() {
		return distribucion;
	}

	/**
	 * Gira la posición (i,j) de dos teclas del teclado
	 * @param i1 fila tecla 1
	 * @param j1 columna tecla 1
	 * @param i2 fila tecla 2
	 * @param j2 columna tecla 2
	 */

	public void swap_teclas(int i1, int j1, int i2, int j2) {
		char temp = distribucion[i2][j2];

		distribucion[i2][j2] = distribucion[i1][j1];
		distribucion[i1][j1] = temp;
	}

	public void actualizarDist(char[][] dist) {
		this.distribucion = dist;
	}
	
	/** Métodos redefinidos **/
	@Override
	public String toString() {
		return nombre;
	}

}
