package Entidades;

import Tablero.*;

public class Banana extends Comida{
	public Banana() {
		incrementarTamaño = 4;
		puntaje = 50;
		//Falta Imagen
	}
	
	public void accept(Visitor v) {
		v.chocar(this);
	}

}
