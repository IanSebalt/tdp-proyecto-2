package Entidades;

import Tablero.*;

public class PowerUp2 extends PowerUp{
	
	public PowerUp2() {
		incrementarTamaño = 3;
		puntaje = 75;
		estetica = "/imagenes/cuerpo_rojo.png";
		img = "/imagenes/PowerUp_2.png";
	}
	
	public void accept(VisitorEntidad v) {
		v.chocar(this);
	}
	
	public String getImg() {
		return img;
	}
}