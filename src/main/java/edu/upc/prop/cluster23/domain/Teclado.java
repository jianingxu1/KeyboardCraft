package edu.upc.prop.cluster23.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/** Ejemplo de una clase de Dominio. **/
public class Teclado {
	
	/** Atributos **/

	private String nombre;

	/** Constructora **/
	public Teclado(String nombre) {
		this.nombre = nombre;
	}
	
	/** Métodos públicos **/
	
	
	/** Métodos redefinidos **/
	@Override
	public String toString() {
		return nombre;
	}

}
