package Entidades;

import Tablero.*;

public class PowerUp3 extends PowerUp{
	
	public PowerUp3() {
		incrementarTamaño = 3;
		puntaje = 75;
		estetica = "/imagenes/cuerpo_amarillo.png";
		img = "/imagenes/PowerUp_3.png";
	}
	
	public void accept(VisitorEntidad v) {
		v.chocar(this);
	}
	
	public String getImg() {
		return img;
	}
}