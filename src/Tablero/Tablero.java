package Tablero;

import java.util.concurrent.ThreadLocalRandom;
import Entidades.*;

import Juego.Juego;

public class Tablero implements VisitorBloque{
	
	protected int comida;
	
	protected int powerUp;
	
	protected Bloque matriz [][];
	
	protected Serpiente miSerpiente;
	
	protected Juego miJuego;
	
	protected int cantFilas;
	
	protected int cantColumnas;
	
	protected boolean generarAlimento;
	
	protected boolean generarPowerUp;
	
	public Tablero(Juego mj, int cf, int cC) {
		this.cantFilas = cf;
		this.cantColumnas = cC;
		this.comida = 0;
		this.powerUp = 0;
		this.miJuego = mj;
		this.matriz = new Bloque[cantColumnas][cantFilas];
		//El tablero se crea con las cuatro paredes principales que limítan el área de juego,
		for(int i = 0; i < cantColumnas; i++) {
			matriz[0][i] = new BloqueIntransitable(0, i);
			matriz[cantFilas - 1][i] = new BloqueIntransitable(cantFilas -1, i);
		}
		for(int i = 1; i < cantFilas - 1; i++) {
			matriz[i][0] = new BloqueIntransitable(i, 0);
			matriz[i][cantColumnas - 1] = new BloqueIntransitable(i, cantColumnas - 1);
		}
		for(int i = 1; i < cantFilas - 1; i++)
			for(int j = 1; j < cantColumnas - 1; j++)
				matriz[i][j] = new BloqueTransitable(i, j);
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
			matriz[ubicacionX][ubicacionY] = new BloqueIntransitable(ubicacionX,ubicacionY);
		}
	}
	
	public boolean visitarBloque(BloqueTransitable b) {
		if(generarAlimento) {
			int randomAlim = ThreadLocalRandom.current().nextInt(1, 6);
			if(randomAlim == 1)
				b.establecerEntidad(new Banana());
			else if (randomAlim == 2)
					  b.establecerEntidad(new Manzana());
				 else if(randomAlim == 3)
					 	  b.establecerEntidad(new Naranja());
				 	  else if(randomAlim == 4)
				 		  	   b.establecerEntidad(new Pera());
				 	  	   else b.establecerEntidad(new Sandia()); 
			generarAlimento = false;
		}
		if(generarPowerUp) {
			int randomPowerUp = ThreadLocalRandom.current().nextInt(1, 4);
			if(randomPowerUp == 1)
				b.establecerEntidad(new PowerUp1());
			else if(randomPowerUp == 2)
					 b.establecerEntidad(new PowerUp2());
				 else b.establecerEntidad(new PowerUp3());
			generarPowerUp = false;
		}
		return true;
	}
	
	public boolean visitarBloque(BloqueIntransitable b) {
		return false;
	}
	
	public void generarAlimento() {
		int randomX = ThreadLocalRandom.current().nextInt(1, cantColumnas - 1);
		int randomY = ThreadLocalRandom.current().nextInt(1, cantFilas - 1);
		generarAlimento = true;
		while(!matriz[randomX][randomY].accept(this)) {
			randomX = ThreadLocalRandom.current().nextInt(1, cantColumnas - 1);
			randomY = ThreadLocalRandom.current().nextInt(1, cantFilas - 1);
		}
		
	}
	
	public void generarPowerUp() {
		int randomX = ThreadLocalRandom.current().nextInt(1, cantColumnas - 1);
		int randomY = ThreadLocalRandom.current().nextInt(1, cantFilas - 1);
		generarPowerUp = true;
		while(!matriz[randomX][randomY].accept(this)) {
			randomX = ThreadLocalRandom.current().nextInt(1, cantColumnas - 1);
			randomY = ThreadLocalRandom.current().nextInt(1, cantFilas - 1);
		}
	}
	
	/**
	 * Método que actualiza un bloque transitable a uno intransitable.
	 * @param cord - coordenada del bloque a actualizar.
	 */
	public void actualizarBloque(Coordenada cord) {
		if(matriz[cord.getX()][cord.getY()].accept(this))
			matriz[cord.getX()][cord.getY()] = new BloqueIntransitable(cord.getX(),cord.getY());
		miJuego.actualizarVentana(cord, matriz[cord.getX()][cord.getY()].getBloqueGrafico().getImagen());
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
	
	public boolean visitarBloqueMover(BloqueIntransitable b) {
		miJuego.gameOver();
		return false;
	}
	
	public boolean visitarBloqueMover(BloqueTransitable b) {
		return true;
	}
	
	
	
	public boolean mePuedoMover(Bloque b, char direccion) {
		boolean toReturn = true;
		if(direccion == 'd') {
			Coordenada coord = b.getCoord();
			coord.setY(coord.getY() - 1);
			toReturn = (matriz[coord.getX()][coord.getY()].acceptMover(this));
		} else if(direccion == 'u') {
			Coordenada coord = b.getCoord();
			coord.setY(coord.getY() + 1);
			toReturn = (matriz[coord.getX()][coord.getY()].acceptMover(this));
		} else if(direccion == 'l') {
			Coordenada coord = b.getCoord();
			coord.setX(coord.getX() - 1);
			toReturn = (matriz[coord.getX()][coord.getY()].acceptMover(this));
		} else if(direccion == 'l') {
			Coordenada coord = b.getCoord();
			coord.setX(coord.getX() + 1);
			toReturn = (matriz[coord.getX()][coord.getY()].acceptMover(this));
		}
		return toReturn;
	}
	
	public void crearBloqueTransitable(Coordenada c) {
		matriz[c.getX()][c.getY()] = new BloqueTransitable(c.getX(), c.getY());
	}
}
