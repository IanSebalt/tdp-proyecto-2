package Entidades;

import Tablero.*;

public class Sandia extends Comida{
	public Sandia() {
		incrementarTamaño = 5;
		puntaje = 70;
		img = "/imagenes/sandia";
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