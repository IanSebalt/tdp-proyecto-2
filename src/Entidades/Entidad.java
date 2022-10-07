package Entidades;

public abstract class Entidad {
	protected String img;
	
	abstract public void accept(Visitor v);
}
