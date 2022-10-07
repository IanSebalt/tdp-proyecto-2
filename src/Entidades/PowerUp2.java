package Entidades;

public class PowerUp2 extends PowerUp{
	
	public PowerUp2() {
		incrementarTamaño = 3;
		puntaje = 75;
		//FALTA IMAGEN
		
	}
	public void accept(Visitor v) {
		v.chocar(this);
	}
	public String modificarEstetica() {
		return //Parte del arreglo de imagenes
	}
}