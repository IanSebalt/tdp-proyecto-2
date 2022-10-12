package Entidades;

import Tablero.*;

public class Pera extends Comida{
	public Pera() {
		incrementarTamaño = 1;
		puntaje = 35;
		img = "/imagenes/pera";
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