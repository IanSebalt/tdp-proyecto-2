package Tablero;

import java.util.concurrent.ThreadLocalRandom;
import Entidades.*;

import Juego.Juego;

public class Tablero{
	
	protected int comida;
	
	protected int powerUp;
	
	protected Bloque matriz [][];
	
	protected Juego miJuego;
	
	protected int cantFilas;
	
	protected int cantColumnas;
	
	public Tablero(Juego mj, int cf, int cC) {
		this.cantFilas = cf;
		this.cantColumnas = cC;
		this.comida = 0;
		this.powerUp = 0;
		this.miJuego = mj;
		this.matriz = new Bloque[cantColumnas][cantFilas];
		//El tablero se crea con las cuatro paredes principales que limítan el área de juego,
		for(int i = 0; i < cantColumnas; i++) {
			matriz[0][i] = new Bloque(0, i, false);
			matriz[cantFilas - 1][i] = new Bloque(cantFilas -1, i, false);
		}
		for(int i = 1; i < cantFilas - 1; i++) {
			matriz[i][0] = new Bloque(i, 0, false);
			matriz[i][cantColumnas - 1] = new Bloque(i, cantColumnas - 1, false);
		}
		for(int i = 1; i < cantFilas - 1; i++)
			for(int j = 1; j < cantColumnas - 1; j++)
				matriz[i][j] = new Bloque(i, j, true);
	}
	
	/**
	 * Método para establecer las comidas que se generarán en el tablero.
	 * @param com - comidas a generar.
	 */
	public void establecerComida(int com) {
		comida = com;		
	}
	
	/**
	 * Método para establecer los powerups que se generarán en el tablero.
	 * @param pow - powerups a generar.
	 */
	public void establecerPowerUp(int pow) {
		powerUp = pow;
	}
	
	/**
	 * Método que genera las paredes con las coordenadas recibidas por parámetro.
	 * @param coor - coordenas de las paredes a generar.
	 */
	public void generarParedes(Coordenada coor []) {
		int ubicacionX, ubicacionY;
		for(Coordenada cord : coor) {
			ubicacionX = cord.getX();
			ubicacionY = cord.getY();
			matriz[ubicacionX][ubicacionY] = new Bloque(ubicacionX,ubicacionY, false);
		}
	}
	
	public void generarAlimento() {
		int randomX = ThreadLocalRandom.current().nextInt(1, cantColumnas - 1);
		int randomY = ThreadLocalRandom.current().nextInt(1, cantFilas - 1);
		while(!matriz[randomX][randomY].getTransitable()) {
			randomX = ThreadLocalRandom.current().nextInt(1, cantColumnas - 1);
			randomY = ThreadLocalRandom.current().nextInt(1, cantFilas - 1);
		}
		int randomA = ThreadLocalRandom.current().nextInt(1, 6);
		Entidad e;
		if(randomA == 1)
			e = new Banana();
		else if(randomA == 2)
				 e = new Manzana();
			 else if(randomA == 3)
				 	  e = new Naranja();
			 	  else if(randomA == 4)
			 		  	   e = new Pera();
			 	  	   else e = new Sandia(); 
		matriz[randomX][randomY].setEntidad(e);
		miJuego.actualizarVentana(new Coordenada(randomX, randomY));
	}
	
	public void generarPowerUp() {
		int randomX = ThreadLocalRandom.current().nextInt(1, cantColumnas - 1);
		int randomY = ThreadLocalRandom.current().nextInt(1, cantFilas - 1);
		while(!matriz[randomX][randomY].getTransitable()) {
			randomX = ThreadLocalRandom.current().nextInt(1, cantColumnas - 1);
			randomY = ThreadLocalRandom.current().nextInt(1, cantFilas - 1);
		}
		int randomA = ThreadLocalRandom.current().nextInt(1, 3);
		Entidad e;
		if(randomA == 1)
			e = new PowerUp1();
		else if(randomA == 2)
				 e = new PowerUp2();
			 else e = new PowerUp3();
		matriz[randomX][randomY].setEntidad(e);
		matriz[randomX][randomY].getBloqueGrafico().cambiarImagen(e.getImg());
	}
	
	/**
	 * Método que actualiza un bloque transitable a uno intransitable.
	 * @param cord - coordenada del bloque a actualizar.
	 */
	public void actualizarBloque(Coordenada cord) {
		if(matriz[cord.getX()][cord.getY()].getTransitable())
			matriz[cord.getX()][cord.getY()] = new Bloque(cord.getX(),cord.getY(), false);
		miJuego.actualizarVentana(cord);
	}
	
	/**
	 * Método que actualiza el puntaje en el juego
	 * @param punt - puntaje a sumar.
	 */
	public void IncrementarPuntaje(int punt) {
		miJuego.IncrementarPuntaje(punt);
	}
	
	/**
	 * Método que retorna la matriz del tablero
	 * @return matriz de bloques del tablero.
	 */
	public Bloque[][] getMatriz(){
		return matriz;
	}
	
	/**
	 * Método que retorna la cantidad de alimentos restante en el tablero.
	 * @return cantidad de alimentos restante.
	 */
	public int getAlimento() {
		return comida;
	}
	
	/**
	 * Método que retorna la cantidad de powerups restante en el tablero
	 * @return cantidad de powerups restante.
	 */
	public int getPowerUp() {
		return powerUp;
	}
	
	public boolean mePuedoMover(Bloque b, char direccion) {
		boolean toReturn = true;
		Coordenada coord = new Coordenada(b.getCoord().getX(), b.getCoord().getY());
		if(direccion == 'l') {
			coord.setY(coord.getY() - 1);
			toReturn = (matriz[coord.getX()][coord.getY()].getTransitable());
		} else if(direccion == 'r') {
			coord.setY(coord.getY() + 1);
			toReturn = (matriz[coord.getX()][coord.getY()].getTransitable());
		} else if(direccion == 'u') {
			coord.setX(coord.getX() - 1);
			toReturn = (matriz[coord.getX()][coord.getY()].getTransitable());
		} else if(direccion == 'd') {
			coord.setX(coord.getX() + 1);
			toReturn = (matriz[coord.getX()][coord.getY()].getTransitable());
		}
		if(!toReturn)
			miJuego.gameOver();
		return toReturn;
	}
	
	public void crearBloqueTransitable(Coordenada c) {
		matriz[c.getX()][c.getY()] = new Bloque(c.getX(), c.getY(), true);
	}
	
	public Bloque getBloque(Coordenada c) {
		return matriz[c.getX()][c.getY()];
	}
	
	public void setBloque(Coordenada c, Bloque b) {
		matriz[c.getX()][c.getY()] = b;
		miJuego.actualizarVentana(c);
	}
	
	public void intercambiarBloque(Coordenada c1, Coordenada c2) {
		Bloque aux = null;
		aux = matriz[c1.getX()][c1.getY()];
		System.out.println(matriz[c1.getX()][c1.getY()].getBloqueGrafico().getImagen());
		matriz[c1.getX()][c1.getY()] = matriz[c2.getX()][c2.getY()];
		matriz[c1.getX()][c1.getY()].getCoord().setX(c1.getX());
		matriz[c1.getX()][c1.getY()].getCoord().setY(c1.getY());
		matriz[c2.getX()][c2.getY()] = aux;
		matriz[c2.getX()][c2.getY()].getCoord().setX(c2.getX());
		matriz[c2.getX()][c2.getY()].getCoord().setY(c2.getY());
		System.out.println(matriz[c1.getX()][c1.getY()].getBloqueGrafico().getImagen());
		miJuego.actualizarVentana(c1);
		miJuego.actualizarVentana(c2);
	}
	
	public Entidad hayEntidad(Coordenada cord) {
		return matriz[cord.getX()][cord.getY()].getEntidad();
	}
}
