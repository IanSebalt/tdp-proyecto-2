package Entidades;

import Tablero.*;

public class PowerUp1 extends PowerUp{
	
	public PowerUp1() {
		incrementarTamaño = 3;
		puntaje = 50;
		arrEstetica = new String[2];
		arrEstetica[0] = "/imagenes/verde.png";
		arrEstetica[1] = "/imagenes/verde.png";	
		img = "/imagenes/verde.png";
	}
	
	public void accept(VisitorEntidad v) {
		v.chocar(this);		
	}
	
	public String getImg() {
		return img;
	}
}
