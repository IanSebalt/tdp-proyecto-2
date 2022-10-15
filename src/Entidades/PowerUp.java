package Entidades;

import Tablero.VisitorEntidad;

public abstract class PowerUp extends Entidad{
	protected int incrementarTamaño;
	protected int puntaje;
	protected String estetica;
	
	abstract public void accept(VisitorEntidad v);

	public String modificarEstetica() {
		return estetica; 
	}
	
	public int getPuntaje() {
		return puntaje;
	}
	
	public String getImg() {
		return img;
	}
	
	public int getIncrementarTamaño() {
		return incrementarTamaño;
	}
}
