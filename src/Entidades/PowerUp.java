package Entidades;

public abstract class PowerUp extends Entidad{
	protected int incrementarTamaño;
	protected int puntaje;
	protected String[] arrEstetica;
	
	abstract public String[] modificarEstetica();
}
