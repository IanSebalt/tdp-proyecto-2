package Entidades;

import Tablero.*;

public class PowerUp1 extends PowerUp{
	
	public PowerUp1() {
		incrementarTamaño = 3;
		puntaje = 50;
		//FALTA IMAGEN
		
	}
	public String modificarEstetica() {
		return //Parte del arreglo de imagenes
	}
	
	public void accept(Visitor v) {
		v.chocar(this);		
	}
}
