package Entidades;

import Tablero.*;

public class PowerUp1 extends PowerUp{
	
	public PowerUp1() {
		incrementarTamaño = 3;
		puntaje = 50;
		estetica = "/imagenes/cuerpo_celeste.png";
		img = "/imagenes/PowerUp_1.png";
	}
	
	public void accept(VisitorEntidad v) {
		v.chocar(this);		
	}
	
	public String getImg() {
		return img;
	}
}
