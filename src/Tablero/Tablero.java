package Tablero;

import Juego.Juego;

public class Tablero {
	
	protected int comida;
	
	protected int powerUp;
	
	protected Bloque matriz [][];
	
	protected Serpiente miSerpiente;
	
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
		for(int i=0;i<cantFilas;i++) {
			for(int j=0;j<cantColumnas;j++)
				matriz[i][j] = new BloqueIntransitable(i,j);
			i = cantFilas-2;
		}
		for(int j=0;j<cantFilas;j++) {
			for(int i=1;i<cantColumnas-1;i++)
				matriz[i][j] = new BloqueIntransitable(i,j);
			j = cantColumnas-2;
		}
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
	
	public void generarAlimento() {
		//TODO: implementar
	}
	
	public void generarPowerUp() {
		//TODO: implementar
	}
	
	public void actualizarBloque(int x, int y) {
		//TODO: implementar
	}
}
