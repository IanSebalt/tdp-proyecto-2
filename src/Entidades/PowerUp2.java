package Entidades;

import Tablero.*;

public class PowerUp2 extends PowerUp{
	
	public PowerUp2() {
		incrementarTamaño = 3;
		puntaje = 75;
		arrEstetica = new String[2];
		arrEstetica[0] = "/imagenes/rojito.png";
		arrEstetica[1] = "/imagenes/rojito.png";
		img = "/imagenes/rojito.png";
	}
	
	public void accept(VisitorEntidad v) {
		v.chocar(this);
	}
	
	public String getImg() {
		return img;
	}
}