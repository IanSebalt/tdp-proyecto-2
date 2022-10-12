package Entidades;

import Tablero.*;

public class Manzana extends Comida{
	public Manzana() {
		incrementarTamaño = 3;
		puntaje = 25;
		img = "/imagenes/manzana";
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
