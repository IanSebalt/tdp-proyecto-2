package Tablero;

import java.util.LinkedList;

import Entidades.Banana;
import Entidades.Manzana;
import Entidades.Naranja;
import Entidades.Pera;
import Entidades.PowerUp1;
import Entidades.PowerUp2;
import Entidades.PowerUp3;
import Entidades.Sandia;

public class Serpiente implements VisitorEntidad{
	
	protected char direccion;

	protected LinkedList<Bloque> cuerpo;
	
	protected Tablero miTablero;
	
	/**
	 * El constructor serpiente inicializa trablero y crea la criatura en las coordenadas pasadas por parámetro.
	 * @param mt - tablero del juego.
	 * @param coor - coordenadas de la ubicación de la serpiente en el tablero.
	 */
	public Serpiente(Tablero mt, Coordenada coor []) {
		this.miTablero = mt;
		this.cuerpo = new LinkedList<Bloque>();
		for (Coordenada coordenadas : coor) {
			int corX = coordenadas.getX();
			int corY = coordenadas.getY();
			cuerpo.addLast(new BloqueIntransitable(corX,corY));
		}
		
	}
	
	public int mover(char direccion) {
		//TODO: Implementar.
		return 0;
	}
	
	public Bloque cabeza() {
		return cuerpo.getFirst();
	}

	@Override
	public void chocar(PowerUp1 p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chocar(PowerUp2 p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chocar(PowerUp3 p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chocar(Pera a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chocar(Manzana a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chocar(Banana a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chocar(Naranja a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chocar(Sandia a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chocar(Serpiente ser) {
		// TODO Auto-generated method stub
		
	}
}
