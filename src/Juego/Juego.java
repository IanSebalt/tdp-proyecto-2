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
	
	
	/**
	 * Método crear jugador con el nombre recibido por la ventana y se le asigna una puntuación inicial.
	 */
	public void crearJugador() {
		String nombre = miVentana.pedirNombre();
		miJugador = new Jugador(nombre);
		miJugador.sumarPuntos(puntajeActual);
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
