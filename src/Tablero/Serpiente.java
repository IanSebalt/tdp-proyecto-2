package Tablero;

import java.util.Iterator;
import java.util.LinkedList;

import Entidades.Banana;
import Entidades.Comida;
import Entidades.Entidad;
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
			cuerpo.addLast(bloqueNuevo);
			miTablero.getMatriz()[corX][corY] = bloqueNuevo;
		}
		cuerpo.getFirst().getBloqueGrafico().setImagen("/imagenes/cuerpo_negro.png");
		aCrecer = 0;
		direccion = 'r';
	}
	
	public synchronized void mover(char d) {
		if((direccion == 'u' && d != 'd') || (direccion == 'l' && d != 'r') || (direccion == 'r' && d != 'l') || direccion == 'd' && d != 'u' ) {
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
			Entidad e = null;
			if(d == 'l') {
				Coordenada nuevaCoord = new Coordenada(cabeza().getCoord().getX(), cabeza().getCoord().getY() - 1);
				Bloque nuevaCab = new Bloque(nuevaCoord.getX(), nuevaCoord.getY(), false);
				BloqueGrafico aux = cabeza().getBloqueGrafico();
				e = miTablero.hayEntidad(nuevaCoord);
				nuevaCab.setBloqueGrafico(aux);
				miTablero.setBloque(nuevaCab.getCoord(), nuevaCab);
				interactuarEntidad(e, miTablero.getBloque(nuevaCoord));
				cuerpo.addFirst(nuevaCab);
			} else if(d == 'u') {
				Coordenada nuevaCoord = new Coordenada(cabeza().getCoord().getX() - 1, cabeza().getCoord().getY());
				Bloque nuevaCab = new Bloque(nuevaCoord.getX(), nuevaCoord.getY(), false);
				BloqueGrafico aux = cabeza().getBloqueGrafico();	
				e = miTablero.hayEntidad(nuevaCoord);
				nuevaCab.setBloqueGrafico(aux);
				miTablero.setBloque(nuevaCab.getCoord(), nuevaCab);				
				interactuarEntidad(e, miTablero.getBloque(nuevaCoord));
				cuerpo.addFirst(nuevaCab);
			} else if(d == 'd') {
				Coordenada nuevaCoord = new Coordenada(cabeza().getCoord().getX() + 1, cabeza().getCoord().getY());
				Bloque nuevaCab = new Bloque(nuevaCoord.getX(), nuevaCoord.getY(), false);
				BloqueGrafico aux = cabeza().getBloqueGrafico();
				e = miTablero.hayEntidad(nuevaCoord);
				nuevaCab.setBloqueGrafico(aux);
				miTablero.setBloque(nuevaCab.getCoord(), nuevaCab);		
				interactuarEntidad(e, miTablero.getBloque(nuevaCoord));
				cuerpo.addFirst(nuevaCab);
			} else if(d == 'r') {
				Coordenada nuevaCoord = new Coordenada(cabeza().getCoord().getX(), cabeza().getCoord().getY() + 1);
				Bloque nuevaCab = new Bloque(nuevaCoord.getX(), nuevaCoord.getY(), false);
				BloqueGrafico aux = cabeza().getBloqueGrafico();	
				e = miTablero.hayEntidad(nuevaCoord);
				nuevaCab.setBloqueGrafico(aux);
				miTablero.setBloque(nuevaCab.getCoord(), nuevaCab);		
				interactuarEntidad(e, miTablero.getBloque(nuevaCoord));
				cuerpo.addFirst(nuevaCab);
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
			Entidad e = null;
			Coordenada aux = new Coordenada(nuevaCab.getCoord().getX(), nuevaCab.getCoord().getY());
			
			if(d == 'l') {
				nuevaCab.getCoord().setY(cabezaAnt.getCoord().getY() - 1);
				nuevaCab.getCoord().setX(cabezaAnt.getCoord().getX());
				e = miTablero.hayEntidad(nuevaCab.getCoord());
				interactuarEntidad(e, miTablero.getBloque(nuevaCab.getCoord()));
			} else if(d == 'r') {
				nuevaCab.getCoord().setY(cabezaAnt.getCoord().getY() + 1);
				nuevaCab.getCoord().setX(cabezaAnt.getCoord().getX());
				e = miTablero.hayEntidad(nuevaCab.getCoord());
				interactuarEntidad(e, miTablero.getBloque(nuevaCab.getCoord()));
			} else if(d == 'u') {
				nuevaCab.getCoord().setY(cabezaAnt.getCoord().getY());
				nuevaCab.getCoord().setX(cabezaAnt.getCoord().getX() - 1);
				e = miTablero.hayEntidad(nuevaCab.getCoord());
				interactuarEntidad(e, miTablero.getBloque(nuevaCab.getCoord()));
			} else {
				nuevaCab.getCoord().setY(cabezaAnt.getCoord().getY());
				nuevaCab.getCoord().setX(cabezaAnt.getCoord().getX() + 1);
				e = miTablero.hayEntidad(nuevaCab.getCoord());
				interactuarEntidad(e, miTablero.getBloque(nuevaCab.getCoord()));
			}
			nuevaCab.setBloqueGrafico(cabezaAnt.getBloqueGrafico());
			miTablero.intercambiarBloque(aux, nuevaCab.getCoord());
		}				
	}
	
	public Bloque cabeza() {
		return cuerpo.getFirst();
	}

	@Override
	public void chocar(PowerUp p) {
		miTablero.IncrementarPuntaje(p.getPuntaje());
		String str = p.modificarEstetica();
		miTablero.setBloque(cabeza().getCoord(), cabeza());
		miTablero.decrementarPowerUp();
		Iterator<Bloque> it = cuerpo.iterator();
		while(it.hasNext()) {
			Bloque aux = it.next();
			aux.getBloqueGrafico().setImagen(str);
			miTablero.setBloque(aux.getCoord(), aux);
		}
		aCrecer += p.getIncrementarTamaño();
		miTablero.generarPowerUp();
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
		miTablero.decrementarComida();
		aCrecer += c.getIncrementarTamaño();
		miTablero.generarAlimento();
	}
	
	public void interactuarEntidad(Entidad e, Bloque b) {
		if(e != null) {
			e.accept(this);
			miTablero.setBloque(b.getCoord(), new Bloque(b.getCoord().getX(), b.getCoord().getY(), true));
		}
	}
	
	
}
