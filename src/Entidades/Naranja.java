package Entidades;

import Tablero.*;

public class Naranja extends Comida{
	public Naranja() {
		incrementarTamaño = 2;
		puntaje = 75;
		img = "/imagenes/naranja";
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