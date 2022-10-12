package Entidades;

import Tablero.*;

public class Banana extends Comida{
	public Banana() {
		incrementarTamaño = 4;
		puntaje = 50;
		img = "/imagenes/banana";
	}
	
	public void accept(VisitorEntidad v) {
		v.chocar(this);
	}
	
	public int getIncrementarTamaño() {
		return this.incrementarTamaño;
	}

	public int getPuntaje() {
		return this.puntaje;
	}
	
	public String getImg() {
		return this.img;
	}
}
