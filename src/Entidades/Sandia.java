package Entidades;

import Tablero.*;

public class Sandia extends Comida{
	public Sandia() {
		incrementarTamaño = 5;
		puntaje = 70;
		img = "/imagenes/sandia.png";
	}
	
	public void accept(VisitorEntidad v) {
		v.chocar(this);
	}
}