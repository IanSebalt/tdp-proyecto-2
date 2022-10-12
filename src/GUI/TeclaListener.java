package GUI;

import java.awt.event.*;

public class TeclaListener implements KeyListener{
	protected Ventana miVentana;
	
	public TeclaListener(Ventana v) {
		miVentana = v;
	}
	
	public void keyTyped(KeyEvent e) {
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			miVentana.moverAbajo();
		if(e.getKeyCode() == KeyEvent.VK_UP)
			miVentana.moverArriba();
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			miVentana.moverDerecha();
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			miVentana.moverIzquierda();
	}
	
	public void keyReleased(KeyEvent e) {
	}
}
