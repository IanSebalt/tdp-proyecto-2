package Entidades;

import Tablero.*;

public class PowerUp3 extends PowerUp{
	
	public PowerUp3() {
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
}