package Tablero;

import Entidades.*;

public interface VisitorEntidad {
	public void chocar(PowerUp1 p);
	public void chocar(PowerUp2 p);
	public void chocar(PowerUp3 p);
	public void chocar(Pera a);
	public void chocar(Manzana a);
	public void chocar(Banana a);
	public void chocar(Naranja a);
	public void chocar(Sandia a);
	public void chocar(Serpiente ser);
}
