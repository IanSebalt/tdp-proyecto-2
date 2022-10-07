package Tablero;

import java.util.LinkedList;

public class Serpiente {
	protected char direccion;
	protected int tamaño;
	protected LinkedList<Bloque> cuerpo;
	
	public int mover(char direccion) {
		//TODO: Implementar.
		return 0;
	}
	
	public Bloque cabeza() {
		return cuerpo.getFirst();
	}
}
