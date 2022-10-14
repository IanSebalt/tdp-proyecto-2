package Juego;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import GUI.Ventana;
import ManejoArchivos.manejoArchivo;
import Tablero.*;

public class Juego {
	
	protected int puntajeActual;
	protected int nivelActual;
	protected Reloj miReloj;
	protected Jugador miJugador;
	protected Ventana miVentana;
	protected Tablero miTablero;
	protected Serpiente miSerpiente;
	protected Thread t1;
	
	public Juego(Ventana v) {
		miReloj = new Reloj(this);
		miVentana = v;
		puntajeActual = 0;
		miJugador = null;
		miSerpiente = null;
		nivelActual = 1;
	}
	
	/**
	 * Método que inicia el juego creando el tablero con un ancho y largo recibido por parámetro
	 * y genera el primer nivel.
	 * @param largo - largo del tablero.
	 * @param ancho - ancho del tablero.
	 */
	public void iniciarJuego(int largo, int ancho) {
		miTablero = new Tablero(this, largo, ancho);
		generarNivel(nivelActual);
	}
	
	public void gameOver() {
		miVentana.terminarPartida();
	}
	
	public void girar(char dir) {
		char dirac = miSerpiente.getDireccion();
		if((dirac == 'u' && dir != 'd') || (dirac == 'l' && dir != 'r') || (dirac == 'd' && dir != 'u') || (dirac == 'r' && dir != 'l'))
			miSerpiente.setDireccion(dir);
	}
	
	public void mover() {
		miSerpiente.mover(miSerpiente.getDireccion());
	}
	
	public void generarConsumible(){
		miTablero.generarAlimento();
	}
	
	
	/**
	 * Método que crea jugador con el nombre recibido por la ventana y que se le asigna la puntuación lograda.
	 */
	public void crearJugador() {
		String nombre = miVentana.pedirNombre();
		miJugador = new Jugador(nombre);
		miJugador.sumarPuntos(puntajeActual);
	}
	
	/**
	 * Método que modifica el ranking de mejores jugadores y coloca al jugador recibido por parámetro
	 * en el top si supera a alguno de los jugadores del ranking.
	 * @param j - jugador nuevo a insertar en el ranking.
	 */
	private void modificarRanking(Jugador j) {
		File ranking = new File("Ranking.txt");
		manejoArchivo manejo = new manejoArchivo();
		if(ranking.exists()&&ranking.length()>0) {
			try {
				Ranking rank = manejo.leer(ranking.getPath());
				rank.insertarJugador(j);
				manejo.escribir(rank, ranking.getPath());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				ranking.createNewFile();
				Ranking rank = new Ranking(5);
				rank.insertarJugador(j);
				manejo.escribir(rank, ranking.getPath());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Método que genera el nivel leyendo un archivo de texto y pasandole los valores al tablero para la
	 * generación de las paredes, alimentos y powerUps.
	 * @param nivel - nivel de dificultad del juego.
	 */
	public void generarNivel(int nivel) {
		this.nivelActual = nivel;
		Coordenada paredes [] = new Coordenada[50];
		int powerUps = 0;
		int Alimentos = 0;
		int contador = 0;
		File level = new File("Nivel"+ nivel + ".txt");
		try {
			if(!level.createNewFile()&&level.length()==0)
				crearTextoNivel(level.getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		String linea = "";
		Scanner scan;
		String arreglo_Ignore [] = {"Nivel","PowerUps=","Comidas=","Paredes="};
		int cursor = 0;
		int cantElementos = 0;
		BufferedReader lector = null;
		try {
			lector = new BufferedReader(new InputStreamReader(new FileInputStream(level.getPath())));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			while((linea=lector.readLine())!=null) {
				scan = new Scanner(linea.split(arreglo_Ignore[cursor])[1]);
				cursor++;
				while(scan.hasNextInt())
					if(contador==1) 
						powerUps = scan.nextInt();				
					else
						if(contador==2)
							Alimentos = scan.nextInt();
						else
							if(contador>2) {
								paredes[cantElementos] = new Coordenada((scan.nextInt()),1);
								if(scan.hasNext())
									paredes[cantElementos].setY(scan.nextInt());
							cantElementos++;
							}
				contador++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Coordenada achicarArreglo [] = new Coordenada [cantElementos];
		for(int i=0; i<cantElementos;i++)
			achicarArreglo[i] = paredes[i];
		paredes = achicarArreglo;
		miTablero.establecerComida(Alimentos);
		miTablero.establecerPowerUp(powerUps);
		miTablero.generarParedes(paredes);
		int randomX = ThreadLocalRandom.current().nextInt(1, miTablero.getMatriz().length);
		int randomY = ThreadLocalRandom.current().nextInt(1, miTablero.getMatriz()[0].length);
		while(!(miTablero.getMatriz()[randomX][randomY].getTransitable() && miTablero.getMatriz()[randomX][randomY - 1].getTransitable() && miTablero.getMatriz()[randomX][randomY - 2].getTransitable())) {
			randomX = ThreadLocalRandom.current().nextInt(1, miTablero.getMatriz().length);
			randomY = ThreadLocalRandom.current().nextInt(1, miTablero.getMatriz()[0].length);
		}
		Coordenada [] ini = new Coordenada[3];
		for(int i = 0; i < 3; i++) 
			ini[i] = new Coordenada(randomX, randomY - i);
		miSerpiente = new Serpiente(miTablero, ini);
	}
	
	
	/**
	 * Método que retorna un arreglo con el ranking de los mejores jugadores en cuanto a su puntuación.
	 * @return los mejores jugadores.
	 */
	public Jugador[] darRanking() {
		manejoArchivo manejo = new manejoArchivo();
		File ranking = new File("Ranking.txt");
		Ranking rank = new Ranking(5);
		Jugador [] top5 = null;		
		if(ranking.exists()&&ranking.length()>0)
			try {
				rank = manejo.leer(ranking.getPath());
				top5 = rank.ranking();
			} catch (Exception e) {
				e.printStackTrace();
			}
		else
			try {
				ranking.createNewFile();
				manejo.escribir(new Ranking(5), ranking.getPath());
				top5 = manejo.leer(ranking.getPath()).ranking();
			} catch (Exception e) {
				e.printStackTrace();
			}	
		return top5;
	}
	
	/**
	 * Método que cambia el nivel una vez terminado el actual, en caso de que se jugaron todos
	 * los niveles termina el juego.
	 * @param nivelActual - nivel actual de juego.
	 */
	public void cambiarNivel(int nivelActual) {
		if(nivelActual==5)
			gameOver();
		else
			generarNivel(nivelActual+1);
	}
	
	/**
	 * Método que actualiza el gráfico de la ventana.
	 * @param cord - coordenadas del bloque a actualizar.
	 * @param img - nueva imagen para actualizar el bloque.
	 */
	public void actualizarVentana(Coordenada cord) {
		miVentana.actualizarGrafica(cord);
	}
	
	/**
	 * Método que actualiza el puntaje actual
	 * @param punt - puntos a sumarle al puntaje actual.
	 */
	public void IncrementarPuntaje(int punt) {
		puntajeActual += punt;
	}
	
	/**
	 * Método que retorna la matriz de bloques de tablero.
	 * @return una matriz de bloques.
	 */
	public Bloque[][] getTablero(){
		return miTablero.getMatriz();
	}
	
	//Verificar que sucede cuando no hay mas comidas almacenadas pero algunas se encuentras dentro del tablero sin consumirse.
	public boolean checkConsumibles() {
		return miTablero.getAlimento()>0 || miTablero.getPowerUp()>0;
	}
	
	/**
	 * Método que retorna el nivel actual del juego
	 * @return nivel actual del juego.
	 */
	public int getNivel() {
		return nivelActual;
	}
	
	/**
	 * Método que crea el archivo de texto del nivel ingresado para ser modificado.	
	 * @param path - nombre del archivo a escribir.
	 * @param lvl - nivel de juego.
	 */
	private void crearTextoNivel(String path) {
		System.out.println("LLego");
		File nivel = new File(path);
		try {
			FileWriter writer = new FileWriter(nivel);
			BufferedWriter escritor = new BufferedWriter(writer);
			PrintWriter pw = new PrintWriter(escritor);
			pw.write("Nivel "+nivelActual+":");
			pw.write("\nPowerUps= 0");
			pw.write("\nComidas= 0");
			pw.write("\nParedes= 0");
			pw.close();
			escritor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public int getPuntaje() {
		return puntajeActual;
	}
	
	public void start() {
		t1 = new Thread(miReloj);
		t1.start();
	}
	
	public void finish() {
		miReloj.finish();
	}
}
