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
	
	public void establecerComida(int com) {
		comida = com;		
	}
	
	public void establecerPowerUp(int pow) {
		powerUp = pow;
	}
	
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
	
	public void actualizarBloque(Coordenada cord) {
		if(matriz[cord.getX()][cord.getY()].accept(this))
			matriz[cord.getX()][cord.getY()] = new BloqueIntransitable(cord.getX(),cord.getY());
		miJuego.actualizarVentana(cord, matriz[cord.getX()][cord.getY()].getBloqueGrafico().getImagen());
	}
	
	public void IncrementarPuntaje(int punt) {
		miJuego.IncrementarPuntaje(punt);
	}
	
	public Bloque[][] getMatriz(){
		return matriz;
	}
}
