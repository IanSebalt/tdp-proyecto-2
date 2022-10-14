package Juego;

import java.io.Serializable;

public class Ranking implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	protected Jugador rank [];	

	public Ranking(int top) {
		rank = new Jugador [top];
		for(int i=0;i<top;i++)
			rank[i] = new Jugador("-");
	}
	
	public void insertarJugador(Jugador j) {
		Jugador mod = null; 
		for(int i = 0;i<rank.length;i++) {
			if(rank[i].getPuntos()<j.getPuntos()) {
				mod = rank[i];
				rank[i] = j;
				j = mod;
			}
		}
	}
	
	public Jugador[] ranking() {
		return rank;
	}	
	
}
