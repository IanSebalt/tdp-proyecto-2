package Juego;

import java.io.Serializable;

public class Jugador implements Serializable{
	protected int puntos;
	protected String nombre;
	
	//TODO: Serializacion.
	
	public Jugador(String nom) {
		nombre = nom;
	}
	
	public void sumarPuntos(int punt) {
		puntos += punt;
	}
}
