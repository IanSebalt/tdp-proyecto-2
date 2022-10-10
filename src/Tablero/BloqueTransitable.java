package Tablero;

import Entidades.*;

public class BloqueTransitable extends Bloque{
	protected Entidad entidad;
	
	public BloqueTransitable(int x, int y) {
		super.cord = new Coordenada(x, y);
	}
	
	public boolean accept(VisitorBloque v) {
		return v.visitarBloque(this);
	}
	
	public void visitarEntidad(Visitor v) {
		//TODO: Implementar el funcionamiento total.
	}
	
	public void establecerEntidad(Entidad e) {
		entidad = e;
	}
}
