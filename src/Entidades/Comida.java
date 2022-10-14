package Entidades;

public abstract class Comida extends Entidad{
	protected int incrementarTamaño;
	protected int puntaje;
	
	public int getIncrementarTamaño() {
		return this.incrementarTamaño;
	}

	public int getPuntaje() {
		return this.puntaje;
	}
	
	public String getImg() {
		return this.img;
	}
}
