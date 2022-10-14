package Entidades;

import Tablero.VisitorEntidad;

public abstract class PowerUp extends Entidad{
	protected int incrementarTamaño;
	protected int puntaje;
	protected String[] arrEstetica;
	
	abstract public void accept(VisitorEntidad v);

	public String[] modificarEstetica() {
		return arrEstetica; 
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
