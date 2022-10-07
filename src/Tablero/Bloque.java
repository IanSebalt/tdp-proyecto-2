package Tablero;

import GUI.*;

public abstract class Bloque {
	protected int x;
	protected int y;
	protected BloqueGrafico miBloqueGrafico;
	
	public abstract void accept(Visitor v);
}
