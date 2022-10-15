package Tablero;

import Entidades.Entidad;
import GUI.*;

public  class Bloque {
	protected Coordenada cord;
	protected BloqueGrafico miBloqueGrafico;
	protected boolean transitable;
	protected Entidad miEntidad;
	
	public Bloque(int x, int y, boolean t) {
		cord = new Coordenada(x, y);
		transitable = t;
		miBloqueGrafico = new BloqueGrafico();
		if(transitable)
			miBloqueGrafico.setImagen("/imagenes/cuadverde.png");
		else 
			miBloqueGrafico.setImagen("/imagenes/cuerpo_negro.png");
		miEntidad = null;
	}
	
	public BloqueGrafico getBloqueGrafico() {
		return miBloqueGrafico;
	}
	
	public Coordenada getCoord() {
		return cord;
	}
	
	public void setBloqueGrafico(BloqueGrafico bg) {
		miBloqueGrafico = bg;
	}
	
	public void setTransitable(boolean t) {
		transitable = t;
	}
	
	public boolean getTransitable() {
		return transitable;
	}
	
	public void setEntidad(Entidad e) {
		miEntidad = e;
		if(e != null)
			miBloqueGrafico.setImagen(e.getImg());
		else miBloqueGrafico.setImagen("/imagenes/cuadverde.png");
		
	}
	
	public Entidad getEntidad() {
		return miEntidad;
	}
}
