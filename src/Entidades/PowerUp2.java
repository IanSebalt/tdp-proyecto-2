package Entidades;

import Tablero.*;

public class PowerUp2 extends PowerUp{
	
	public PowerUp2() {
		incrementarTamaño = 3;
		puntaje = 75;
		arrEstetica = new String[2];
		arrEstetica[0] = "Directorio1";
		arrEstetica[1] = "Directorio2";
		
	}
	
	public void accept(VisitorEntidad v) {
		v.chocar(this);
	}
	
	public String[] modificarEstetica() {
		return arrEstetica;
	}
	
	public int getPuntaje() {
		return puntaje;
	}
	
	public int getTamañoAIncrementar() {
		return incrementarTamaño;
	}

	public String getImg() {
		return img;
	}
}