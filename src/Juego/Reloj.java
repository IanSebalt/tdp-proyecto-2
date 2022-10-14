package Juego;

public class Reloj implements Runnable{
	
	protected int segundos;
	
	protected Juego miJuego;
	
	public Reloj(Juego mJ) {
		miJuego = mJ;
		segundos = 0;
	}
	
	public void run() {
		while(true){
			try {
				Thread.sleep(1000);
				segundos++;
				actualizarTablero();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void actualizarTablero() {
		miJuego.mover();
		if(miJuego.checkConsumibles()==false)
			miJuego.cambiarNivel();
	}
	
	public void actualizarTiempo() {
		//TODO: Implementar.
	}
}
