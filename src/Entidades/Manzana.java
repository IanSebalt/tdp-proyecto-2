package Entidades;

import Tablero.*;

public class Manzana extends Comida{
	public Manzana() {
		incrementarTamaño = 3;
		puntaje = 25;
		//Falta Imagen
	}
	
	public void accept(VisitorEntidad v) {
		v.chocar(this);
	}
}
