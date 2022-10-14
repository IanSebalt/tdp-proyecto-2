package Entidades;

import Tablero.*;

public abstract class Entidad {
	protected String img;
	
	abstract public void accept(VisitorEntidad v);
	
	abstract public String getImg();
}
