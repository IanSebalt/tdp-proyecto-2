package Juego;

import java.io.Serializable;

public class Jugador implements Serializable{
	
	private static final long serialVersionUID = 1L;

	protected int puntos;
	
	protected String nombre;
	
	public Jugador(String nom) {
		nombre = nom;
		puntos = 0;
	}
	
	public void sumarPuntos(int punt) {
		puntos += punt;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getPuntos() {
		return puntos;
	}
}
