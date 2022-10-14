package Tablero;

import Entidades.*;

public interface VisitorEntidad {
	public void chocar(PowerUp p);
	public void chocar(Comida c);
}
