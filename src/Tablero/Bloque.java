package Tablero;

import GUI.*;

public abstract class Bloque {
	protected Coordenada cord;
	protected BloqueGrafico miBloqueGrafico;
	
	public abstract boolean accept(VisitorBloque v);
	
	public BloqueGrafico getBloqueGrafico() {
		return miBloqueGrafico;
	}
	
	public Coordenada getCoord() {
		return cord;
	}
	
	public void setBloqueGrafico(BloqueGrafico bg) {
		miBloqueGrafico = bg;
	}

	public abstract boolean acceptMover(VisitorBloque v);
}
