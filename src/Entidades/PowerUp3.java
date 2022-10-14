package Entidades;

import Tablero.*;

public class PowerUp3 extends PowerUp{
	
	public PowerUp3() {
		incrementarTamaño = 3;
		puntaje = 75;
		arrEstetica = new String[2];
		arrEstetica[0] = "/imagenes/azul.png";
		arrEstetica[1] = "/imagenes/azul.png";
		img = "/imagenes/azul.png";
	}
	
	public void accept(VisitorEntidad v) {
		v.chocar(this);
	}
	
	public String getImg() {
		return img;
	}
}