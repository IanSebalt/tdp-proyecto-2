package Juego;

public class Reloj implements Runnable{
	
	protected int segundos;
	protected boolean continuar;
	protected Juego miJuego;
	
	public Reloj(Juego mJ) {
		miJuego = mJ;
		segundos = 0;
		continuar = true;
	}
	
	/**
	 * Método run que mantiene un nuevo hilo para que la serpiente este en constante movimiento,
	 */
	public void run() {
		while(continuar){
			try {
				Thread.sleep(200);
				segundos++;
				actualizarTablero();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Método que mueve a la serpiente en la direccíon a la que se dirige.
	 */
	public void actualizarTablero() {
		miJuego.mover();
		if(miJuego.checkConsumibles()==false)
			miJuego.cambiarNivel(miJuego.getNivel());
	}
	
	public void actualizarTiempo() {
		//TODO: Implementar.
	}
	
	public void finish() {
		continuar = false;
	}
}
