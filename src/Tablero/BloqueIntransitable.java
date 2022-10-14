package Tablero;

public class BloqueIntransitable extends Bloque{
	public BloqueIntransitable(int x, int y) {
		super.cord = new Coordenada(x, y);
	}
	
	public boolean accept(VisitorBloque v) {
		return v.visitarBloque(this);
	}
	
	public boolean acceptMover(VisitorBloque v) {
		return v.visitarBloqueMover(this);
	}
}
