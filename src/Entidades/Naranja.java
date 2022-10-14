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
	
}