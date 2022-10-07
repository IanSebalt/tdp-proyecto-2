package Tablero;

import Entidades.*;

public class BloqueTransitable extends Bloque{
	protected Entidad entidad;
	
	public BloqueTransitable(int x, int y) {
		super.x = x;
		super.y = y;
	}
	
	public void accept(Visitor v) {
		//TODO: Implementar el funcionamiento total.
	}
	
	public void visitarEntidad(Visitor v) {
		//TODO: Implementar el funcionamiento total.
	}
}
