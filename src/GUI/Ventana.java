package GUI;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import Tablero.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Juego.*;
import Tablero.Coordenada;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.AbstractAction;
import java.awt.event.*;

public class Ventana {
	
	protected JFrame frame;
	protected Juego miJuego;
	protected JButton btnVolver;
	protected JPanel panelJuego;
	protected JPanel panelMenu;
	protected JPanel panelMain;
	protected Coordenada posAct;
	protected JLabel[][] labels;
	protected static final int largo = 32;
	protected static final int ancho = 32;
	protected static final int pixelAncho = 26;
	protected static final int pixelLargo = 19;
	
	//PRUEBA
	protected Reloj re;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Ventana() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setFocusable(true);
		frame.addKeyListener(new TeclaListener(this));
		JFrame temp = new JFrame();
		temp.pack();
		Insets insets = temp.getInsets();
		temp = null;
		frame.setBounds(0, 0, ancho * pixelAncho + insets.right + insets.left, largo * pixelLargo + insets.top + insets.bottom);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panelMain = new JPanel();
		panelMain.setBounds(0, 0, ancho * pixelAncho, largo * pixelLargo);
		frame.getContentPane().add(panelMain);
		panelMain.setLayout(new CardLayout(0, 0));
		
		panelMenu = new JPanel();
		panelMain.add(panelMenu);
		panelMenu.setLayout(null);
		panelMenu.setBounds(0, 0, ancho * pixelAncho, largo * pixelLargo);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcionJugar();
			}
		});
		btnNewButton.setBounds(365, 221, 89, 23);
		panelMenu.add(btnNewButton);
		
		panelJuego = new JPanel();
		panelJuego.setBounds(0, 0, ancho * pixelAncho, largo * pixelLargo);
		panelMain.add(panelJuego);
		panelJuego.setLayout(new GridLayout(ancho, largo, 0, 0));
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcionVolver();
			}
		});
	}
	
	public void iniciarNivel() {
		//miJuego = new Juego(this);
		//miJuego.iniciarJuego(largo, ancho);
		//Bloque[][] tablero = miJuego.getTablero();
		Coordenada posIni = new Coordenada(15, 15);
		labels = new JLabel[largo][ancho];
		for(int i = 0; i < largo; i++)
			for(int j = 0; j < ancho; j++) {
				JLabel label = new JLabel();
				labels[i][j] = label;
				ImageIcon img = new ImageIcon(getClass().getResource(miJuego.getTablero()[i][j].getBloqueGrafico().getImagen()));
				label.setSize(pixelAncho, pixelLargo);
				reDimensionar(label, img);
				label.setIcon(img);
				panelJuego.add(label);
			}
		posAct = posIni;
	}
	
	private void funcionJugar() {
		panelMain.removeAll();
		iniciarNivel();
		panelMain.add(panelJuego);
		panelMain.repaint();
		panelMain.revalidate();
	}
	
	private void funcionVolver() {
		panelMenu.removeAll();
		panelMenu.add(panelJuego);
		panelMenu.repaint();
		panelMenu.revalidate();
	}
	
	public String pedirNombre() {
		//TODO: Implementar.
		return "";
	}
	
	public void mostrarRanking() {
		//TODO: Implementar.
	}
	
	public void abrirMenu() {
		//TODO: Implementar.
	}
	
	public void actualizarGrafica(Coordenada cord, String img) {
		//TODO: Implementar.
	}
	
	private void reDimensionar(JLabel label, ImageIcon grafico) {
		Image image = grafico.getImage();
		if(image != null) {
			Image newimg = image.getScaledInstance(label.getWidth(), label.getHeight(), java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			label.repaint();
		}
	}
	
	public void moverArriba() {
		//TODO: Implementar.
	}
	
	public void moverAbajo() {
		//TODO: Implementar.
	}
	
	public void moverDerecha() {
		//TODO: Implementar.
	}
	
	public void moverIzquierda() {
		//TODO: Implementar.
	}
}
