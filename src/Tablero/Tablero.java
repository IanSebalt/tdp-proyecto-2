package Tablero;

import Juego.Juego;

public class Tablero {
	
	private int comida;
	
	private int powerUp;
	
	private Bloque matriz [][];
	
	private Juego miJuego;
	
	public Tablero(Juego mj) {
		comida = 0;
		powerUp = 0;
		this.miJuego = mj;
		//TODO: matriz
	}
	
	public void establecerComida(int com) {
		comida = com;		
	}
	
	public void establecerPowerUp(int pow) {
		powerUp = pow;
	}
	
	public void generarParedes(coordenadas coor []) {
		//TODO: implementar
	}
	
	public void generarAlimento() {
		//TODO: implementar
	}
	
	public void actualizarBloque(int x, int y) {
		//TODO: implementar
	}
}
