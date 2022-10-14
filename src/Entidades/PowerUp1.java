package Entidades;

import Tablero.*;

public class PowerUp1 extends PowerUp{
	
	public PowerUp1() {
		incrementarTamaño = 3;
		puntaje = 50;
		arrEstetica = new String[2];
		arrEstetica[0] = "Directorio1";
		arrEstetica[1] = "Directorio2";			
	}
	
	public void accept(VisitorEntidad v) {
		v.chocar(this);		
	}
	
}
