package Entidades;

import Tablero.*;

public class Pera extends Comida{
	public Pera() {
		incrementarTamaño = 1;
		puntaje = 35;
		//Falta Imagen
	}
	
	public void accept(VisitorEntidad v) {
		v.chocar(this);
	}
}