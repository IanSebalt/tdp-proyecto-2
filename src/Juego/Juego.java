package Juego;

import GUI.Ventana;
import Tablero.*;

public class Juego {
	
	protected int puntajeActual;
	protected Reloj miReloj;
	protected Jugador miJugador;
	protected Ventana miVentana;
	protected Tablero miTablero;
	
	public Juego(Ventana v, Reloj r) {
		miReloj = r;
		miVentana = v;
		puntajeActual = 0;
		miJugador = null;
	}
	
	public void iniciarJuego(int largo, int ancho) {
		miTablero = new Tablero(this, largo, ancho);
		generarNivel(1);
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
	 * Método crear jugador con el nombre recibido por la ventana y que se le asigna una puntuación inicial.
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
		Coordenada paredes [] = null;
		int powerUps = 0;
		int Alimentos = 0;
		//TODO: Implementar carga nivel mediante la lectura de un archivo de texto.
		miTablero.establecerComida(Alimentos);
		miTablero.establecerPowerUp(powerUps);
		miTablero.generarParedes(paredes);
	}
	
	public Jugador[] darRanking() {
		//TODO: Implementar.
		return new Jugador[0];
	}
	
	public void cambiarNivel() {
		//TODO: Implementar.
	}
	
	public void actualizarVentana(Coordenada cord, String img) {
		miVentana.actualizarGrafica(cord, img);
	}
	
	public void IncrementarPuntaje(int punt) {
		puntajeActual += punt;
	}
	
	public Bloque[][] getTablero(){
		return miTablero.getMatriz();
	}
}
