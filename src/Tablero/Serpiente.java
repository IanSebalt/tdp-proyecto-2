package Tablero;

import java.util.Iterator;
import java.util.LinkedList;

import Entidades.Banana;
import Entidades.Manzana;
import Entidades.Naranja;
import Entidades.Pera;
import Entidades.PowerUp1;
import Entidades.PowerUp2;
import Entidades.PowerUp3;
import Entidades.Sandia;
import GUI.BloqueGrafico;

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
	
	public void mover(char d) {
		if( (d != direccion) || (direccion == 'u' && d != 'd') || (direccion == 'l' && d != 'r') || (direccion == 'r' && d != 'l') || direccion == 'u' && d != 'd' ) {
			if(miTablero.mePuedoMover(cabeza(), d)) {
				direccion = d;
				Bloque cabezaAnt = cabeza();
				Bloque nuevaCab = cuerpo.removeLast();
				cuerpo.addFirst(nuevaCab);
				miTablero.crearBloqueTransitable(nuevaCab.getCoord());
				if(d == 'd') {
					nuevaCab.getCoord().setY(cabezaAnt.getCoord().getY() - 1);
					nuevaCab.getCoord().setX(cabezaAnt.getCoord().getX());
					BloqueGrafico bg = nuevaCab.getBloqueGrafico();
					nuevaCab.setBloqueGrafico(cabezaAnt.getBloqueGrafico());
					cabezaAnt.setBloqueGrafico(bg);
				} else if(d == 'u') {
					nuevaCab.getCoord().setY(cabezaAnt.getCoord().getY() + 1);
					nuevaCab.getCoord().setX(cabezaAnt.getCoord().getX());
					BloqueGrafico bg = nuevaCab.getBloqueGrafico();
					nuevaCab.setBloqueGrafico(cabezaAnt.getBloqueGrafico());
					cabezaAnt.setBloqueGrafico(bg);
				} else if(d == 'l') {
					nuevaCab.getCoord().setY(cabezaAnt.getCoord().getY());
					nuevaCab.getCoord().setX(cabezaAnt.getCoord().getX() - 1);
					BloqueGrafico bg = nuevaCab.getBloqueGrafico();
					nuevaCab.setBloqueGrafico(cabezaAnt.getBloqueGrafico());
					cabezaAnt.setBloqueGrafico(bg);
				} else {
					nuevaCab.getCoord().setY(cabezaAnt.getCoord().getY());
					nuevaCab.getCoord().setX(cabezaAnt.getCoord().getX() + 1);
					BloqueGrafico bg = nuevaCab.getBloqueGrafico();
					nuevaCab.setBloqueGrafico(cabezaAnt.getBloqueGrafico());
					cabezaAnt.setBloqueGrafico(bg);
				}				
			}
		}		
	}
	public Bloque cabeza() {
		return cuerpo.getFirst();
	}

	@Override
	public void chocar(PowerUp1 p) {
		miTablero.IncrementarPuntaje(p.getPuntaje());
		BloqueGrafico cabezaGraf = cabeza().getBloqueGrafico();
		String[] arr = p.modificarEstetica();
		cabezaGraf.cambiarImagen(arr[0]);
		Iterator<Bloque> it = cuerpo.iterator();
		while(it.hasNext()) {
			Bloque aux = it.next();
			aux.getBloqueGrafico().cambiarImagen(arr[1]);
		}		
		//Falta aumentar tamaño	
	}

	@Override
	public void chocar(PowerUp2 p) {
		miTablero.IncrementarPuntaje(p.getPuntaje());
		BloqueGrafico cabezaGraf = cabeza().getBloqueGrafico();
		String[] arr = p.modificarEstetica();
		cabezaGraf.cambiarImagen(arr[0]);
		Iterator<Bloque> it = cuerpo.iterator();
		while(it.hasNext()) {
			Bloque aux = it.next();
			aux.getBloqueGrafico().cambiarImagen(arr[1]);
		}
		//Falta aumentar tamaño		
	}

	@Override
	public void chocar(PowerUp3 p) {
		miTablero.IncrementarPuntaje(p.getPuntaje());
		BloqueGrafico cabezaGraf = cabeza().getBloqueGrafico();
		String[] arr = p.modificarEstetica();
		cabezaGraf.cambiarImagen(arr[0]);
		Iterator<Bloque> it = cuerpo.iterator();
		while(it.hasNext()) {
			Bloque aux = it.next();
			aux.getBloqueGrafico().cambiarImagen(arr[1]);
		}
		//Falta aumentar tamaño		
	}

	@Override
	public void chocar(Pera a) {
		miTablero.IncrementarPuntaje(a.getPuntaje());
		//Falta aumentar tamaño	
	}

	@Override
	public void chocar(Manzana a) {
		miTablero.IncrementarPuntaje(a.getPuntaje());
		//Falta aumentar tamaño	
	}

	@Override
	public void chocar(Banana a) {
		miTablero.IncrementarPuntaje(a.getPuntaje());
		//Falta aumentar tamaño	
	}

	@Override
	public void chocar(Naranja a) {
		miTablero.IncrementarPuntaje(a.getPuntaje());
		//Falta aumentar tamaño	
	}

	@Override
	public void chocar(Sandia a) {
		miTablero.IncrementarPuntaje(a.getPuntaje());
		//Falta aumentar tamaño	
	}

	@Override
	public void chocar(Serpiente ser) {
				
	}
	
}
