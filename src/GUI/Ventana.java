package GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Tablero.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Juego.*;
import Tablero.Coordenada;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class Ventana {
	
	protected JFrame frame;
	protected Juego miJuego;
	protected JButton btnVolver;
	protected JPanel panelJuego;
	protected JPanel panelMenu;
	protected JPanel panelMain;
	protected JLabel[][] labels;
	protected String jugadorActual;
	protected JPanel panelRanking;
	protected JTextArea textoRanking;
	protected Thread t1;
	protected JLabel puntaje;
    protected JLabel tiempo;
    protected int cronometro;
	protected static final int largo = 32;
	protected static final int ancho = 32;
	protected static final int pixelAncho = 26;
	protected static final int pixelLargo = 19;
	
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
		miJuego = new Juego(this);
		frame = new JFrame();
		frame.setResizable(false);
		frame.setFocusable(true);
		ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/Snaketree.png"));
		frame.setIconImage(icon.getImage());
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
		panelMenu.setBounds(0, 0, ancho * pixelAncho, largo * pixelLargo);
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.setBounds(374, 215, 89, 23);
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miJuego.crearJugador();
				funcionJugar();
			}
		});
		btnJugar.setBackground(new Color(0, 250, 0));
		panelMenu.setLayout(null);
		
		panelRanking = new JPanel();
		panelRanking.setBackground(new Color(0, 128, 0));
		panelRanking.setBounds(585, 11, 196, 85);
		panelMenu.add(panelRanking);
		panelRanking.setLayout(null);
		
		textoRanking = new JTextArea();
		textoRanking.setBackground(new Color(34, 139, 34));
		textoRanking.setEditable(false);
		textoRanking.setBounds(0, 0, 196, 85);
		panelRanking.add(textoRanking);
		panelMenu.add(btnJugar);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 832, 608);
		ImageIcon background = new ImageIcon(getClass().getResource("/imagenes/battleback1.png"));
		lblBackground.setIcon(background);
		panelMenu.add(lblBackground);
		mostrarRanking();
		panelJuego = new JPanel();
		panelJuego.setBounds(0, 0, ancho * pixelAncho, largo * pixelLargo);
		panelMain.add(panelJuego);
		panelJuego.setLayout(new GridLayout(ancho, largo, 0, 0));
	}
	
	public void iniciarNivel() {
        miJuego.iniciarJuego(largo, ancho);
        Bloque[][] tablero = miJuego.getTablero();
        labels = new JLabel[largo][ancho];
        for(int i = 0; i < largo; i++)
            for(int j = 0; j < ancho; j++) {
                if((i != 0 || j != 0) && (i != 0 || j != 1)) {
                    JLabel label = new JLabel();
                    labels[i][j] = label;
                    ImageIcon img = new ImageIcon(getClass().getResource(tablero[i][j].getBloqueGrafico().getImagen()));
                    label.setSize(pixelAncho, pixelLargo);
                    reDimensionar(label, img);
                    label.setIcon(img);
                    panelJuego.add(label);
                }
                else {
                    labels[0][0] = tiempo;
                    labels[0][1] = puntaje;
                }
            }
        //Damos tiempo para la carga del juego.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1 = new Thread(miJuego.getReloj());
        t1.start();
        miJuego.generarConsumible();

    }
	
	private void funcionJugar() {
        panelMain.removeAll();
        cronometro = 0;
        tiempo = new JLabel();
        puntaje = new JLabel();
        panelJuego.add(tiempo);
        panelJuego.add(puntaje);
        puntaje.setFont(new Font("Serif", Font.PLAIN, 9));
        tiempo.setFont(new Font("Serif", Font.PLAIN, 9));
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cronometro++;
                tiempo.setText(formatearTiempo(cronometro));
                tiempo.repaint();
                puntaje.setText("P:"+miJuego.getPuntaje());
            }
        });
        timer.start();
        tiempo.setSize(pixelAncho, pixelLargo);
        iniciarNivel();
        labels[0][0].repaint();
        labels[0][1].repaint();
        panelMain.add(panelJuego);
        panelMain.repaint();
        panelMain.revalidate();
    }
    
    private String formatearTiempo(int crono) {
        int minutos = (crono / 60) % 60;
        int segundos = crono % 60;
        return minutos+":"+segundos;
    }
	
	public String pedirNombre() {
		String nombre = JOptionPane.showInputDialog(frame, "Ingresa tu nombre (máx. 30): ", null);
		if(nombre.length() == 0 || nombre.length() > 30)
			nombre = JOptionPane.showInputDialog(frame, "Ingresa un nombre válido (máx. 30): ", null);
		return nombre;
	}
	
	public void mostrarRanking() {
		Jugador[] jugadores = miJuego.darRanking();
		int count = 0;
		for(Jugador j : jugadores) {
			count++;
			textoRanking.append("TOP "+count+": "+j.getNombre()+" "+ j.getPuntos() +"\n");
		}
	}
	
	public void abrirMenu() {
		panelMain.removeAll();
		panelMain.add(panelMenu);
		panelMain.repaint();
		panelMain.revalidate();
	}
	
	public void actualizarGrafica(Coordenada cord) {
		if(cord != null && cord.getX() > 0 && cord.getX() <= largo && cord.getY() > 0 && cord.getY() <= ancho) {
			ImageIcon img = new ImageIcon(getClass().getResource(miJuego.getTablero()[cord.getX()][cord.getY()].getBloqueGrafico().getImagen()));
			reDimensionar(labels[cord.getX()][cord.getY()], img);
			labels[cord.getX()][cord.getY()].setIcon(img);
			labels[cord.getX()][cord.getY()].repaint();
			panelJuego.repaint();
		}
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
		miJuego.girar('u');
	}
	
	public void moverAbajo() {
		miJuego.girar('d');
	}
	
	public void moverDerecha() {
		miJuego.girar('r');
	}
	
	public void moverIzquierda() {
		miJuego.girar('l');
	}
	
	public void terminarPartida() {
		panelJuego.removeAll();
		miJuego.getReloj().finish();
		abrirMenu();
		JOptionPane.showMessageDialog(null, "¡Termino la partida! Obtuviste "+miJuego.getPuntaje()+" puntos.", "Partida finalizada.", JOptionPane.INFORMATION_MESSAGE);
		textoRanking.setText(null);
		mostrarRanking();
	}

	public void refrescar() {
		for(int i = 0; i < labels.length; i++)
			for(int j = 0; j < labels[0].length; j++) 
				actualizarGrafica(new Coordenada(i, j));
	}
	
	public void cambioDeNivel() {
		JOptionPane.showMessageDialog(null, "¡Terminaste el nivel! El siguiente nivel es el: "+miJuego.getNivel(), "Nivel finalizado.", JOptionPane.INFORMATION_MESSAGE);
	}
}
