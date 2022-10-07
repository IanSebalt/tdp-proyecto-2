package Juego;

import GUI.Ventana;

public class Juego {
	protected int puntajeActual;
	protected Reloj miReloj;
	protected Jugador miJugador;
	protected Ventana miVentana;
	
	public Juego(Ventana v, Reloj r) {
		miReloj = r;
		miVentana = v;
		puntajeActual = 0;
		miJugador = null;
	}
	
	public void gameOver() {
		//TODO: Implementar.
	}
	
	public void girar(char dir) {
		//TODO: Implementar.
	}
	
	public void mover() {
		//TODO: Implementar.
	}
	
	public void crearJugador() {
		//TODO: Implementar.
	}
	
	private void modificarRanking(Jugador j) {
		//TODO: Implementar.
	}
	
	public void generarNivel(int nivel) {
		//TODO: Implementar.
	}
	
	public Jugador[] darRanking() {
		//TODO: Implementar.
		return new Jugador[0];
	}
	
	public void cambiarNivel() {
		//TODO: Implementar.
	}
}
