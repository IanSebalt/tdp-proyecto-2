package Tablero;

import java.util.Iterator;
import java.util.LinkedList;

import Entidades.Banana;
import Entidades.Comida;
import Entidades.Manzana;
import Entidades.Naranja;
import Entidades.Pera;
import Entidades.PowerUp;
import Entidades.PowerUp1;
import Entidades.PowerUp2;
import Entidades.PowerUp3;
import Entidades.Sandia;
import GUI.BloqueGrafico;

public class Serpiente implements VisitorEntidad{
	
	protected char direccion;

	protected LinkedList<Bloque> cuerpo;
	
	protected Tablero miTablero;
	
	protected int aCrecer;
	
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
            Bloque bloqueNuevo = new Bloque(corX,corY, false);
			cuerpo.addLast(new Bloque(corX,corY, false));
			miTablero.setBloque(coordenadas, bloqueNuevo);
		}
		aCrecer = 0;
	}
	
	public void mover(char d) {
		if( (d != direccion) || (direccion == 'u' && d != 'd') || (direccion == 'l' && d != 'r') || (direccion == 'r' && d != 'l') || direccion == 'u' && d != 'd' ) {
			if(aCrecer == 0) {
				moverSinCrecimiento(d);
			} else {
				moverCreciendo(d);
			}
		}		
	}
	
	public void moverCreciendo(char d) {
		if(miTablero.mePuedoMover(cabeza(), d)) {
			direccion = d;
			if(d == 'd') {
				Coordenada nuevaCoord = new Coordenada(cabeza().getCoord().getX(), cabeza().getCoord().getY() - 1);
				Bloque nuevaCab = miTablero.getBloque(nuevaCoord);
				BloqueGrafico aux = cuerpo.getLast().getBloqueGrafico();
				nuevaCab.setBloqueGrafico(cabeza().getBloqueGrafico());
				cabeza().setBloqueGrafico(aux);
				miTablero.setBloque(nuevaCab.getCoord(), nuevaCab);
				cuerpo.addFirst(nuevaCab);
			} else if(d == 'l') {
				Coordenada nuevaCoord = new Coordenada(cabeza().getCoord().getX() - 1, cabeza().getCoord().getY());
				Bloque nuevaCab = miTablero.getBloque(nuevaCoord);
				BloqueGrafico aux = cuerpo.getLast().getBloqueGrafico();
				nuevaCab.setBloqueGrafico(cabeza().getBloqueGrafico());
				cabeza().setBloqueGrafico(aux);
				miTablero.setBloque(nuevaCab.getCoord(), nuevaCab);
			} else if(d == 'r') {
				Coordenada nuevaCoord = new Coordenada(cabeza().getCoord().getX() + 1, cabeza().getCoord().getY());
				Bloque nuevaCab = miTablero.getBloque(nuevaCoord);
				BloqueGrafico aux = cuerpo.getLast().getBloqueGrafico();
				nuevaCab.setBloqueGrafico(cabeza().getBloqueGrafico());
				cabeza().setBloqueGrafico(aux);
				miTablero.setBloque(nuevaCab.getCoord(), nuevaCab);
			} else if(d == 'u') {
				Coordenada nuevaCoord = new Coordenada(cabeza().getCoord().getX(), cabeza().getCoord().getY() + 1);
				Bloque nuevaCab = miTablero.getBloque(nuevaCoord);
				BloqueGrafico aux = cuerpo.getLast().getBloqueGrafico();
				nuevaCab.setBloqueGrafico(cabeza().getBloqueGrafico());
				cabeza().setBloqueGrafico(aux);
				miTablero.setBloque(nuevaCab.getCoord(), nuevaCab);
			}
			aCrecer--;			
		}
	}
	
	public void moverSinCrecimiento(char d) {		
		if(miTablero.mePuedoMover(cabeza(), d)) {
			direccion = d;
			Bloque cabezaAnt = cabeza();
			Bloque nuevaCab = cuerpo.removeLast();
			cuerpo.addFirst(nuevaCab);
			Coordenada aux = nuevaCab.getCoord();
			if(d == 'd') {
				nuevaCab.getCoord().setY(cabezaAnt.getCoord().getY() - 1);
				nuevaCab.getCoord().setX(cabezaAnt.getCoord().getX());
			} else if(d == 'u') {
				nuevaCab.getCoord().setY(cabezaAnt.getCoord().getY() + 1);
				nuevaCab.getCoord().setX(cabezaAnt.getCoord().getX());
			} else if(d == 'l') {
				nuevaCab.getCoord().setY(cabezaAnt.getCoord().getY());
				nuevaCab.getCoord().setX(cabezaAnt.getCoord().getX() - 1);
			} else {
				nuevaCab.getCoord().setY(cabezaAnt.getCoord().getY());
				nuevaCab.getCoord().setX(cabezaAnt.getCoord().getX() + 1);
			}
			miTablero.intercambiarBloque(aux, nuevaCab.getCoord());
			BloqueGrafico bg = nuevaCab.getBloqueGrafico();
			nuevaCab.setBloqueGrafico(cabezaAnt.getBloqueGrafico());
			cabezaAnt.setBloqueGrafico(bg);
		}				
	}
	
	public Bloque cabeza() {
		return cuerpo.getFirst();
	}

	@Override
	public void chocar(PowerUp p) {
		miTablero.IncrementarPuntaje(p.getPuntaje());
		BloqueGrafico cabezaGraf = cabeza().getBloqueGrafico();
		String[] arr = p.modificarEstetica();
		cabezaGraf.cambiarImagen(arr[0]);
		Iterator<Bloque> it = cuerpo.iterator();
		it.next();
		while(it.hasNext()) {
			Bloque aux = it.next();
			aux.getBloqueGrafico().cambiarImagen(arr[1]);
		}	
	}	
	
	public void setDireccion(char c) {
		direccion = c;
	}
	
	public char getDireccion() {
		return direccion;
	}

	@Override
	public void chocar(Comida c) {
		miTablero.IncrementarPuntaje(c.getPuntaje());
		aCrecer = c.getIncrementarTamaño();	
	}

	
}
