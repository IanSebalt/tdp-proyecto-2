package Entidades;

import Tablero.*;

public class Naranja extends Comida{
	public Naranja() {
		incrementarTamaño = 2;
		puntaje = 75;
		//Falta Imagen
	}
	
	public void accept(Visitor v) {
		v.chocar(this);
	}
}