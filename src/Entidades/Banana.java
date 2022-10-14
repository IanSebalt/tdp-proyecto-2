package Entidades;

import Tablero.*;

public class Banana extends Comida{
	public Banana() {
		incrementarTamaño = 4;
		puntaje = 50;
		img = "/imagenes/banana.png";
	}
	
	public void accept(VisitorEntidad v) {
		v.chocar(this);
	}	
}
