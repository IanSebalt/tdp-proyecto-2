package Entidades;

public class Manzana extends Comida{
	public Manzana() {
		incrementarTamaño = 3;
		puntaje = 25;
		//Falta Imagen
	}
	
	public void accept(Visitor v) {
		v.chocar(this);
	}
}
