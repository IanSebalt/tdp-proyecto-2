package Tablero;

public interface VisitorBloque {
	public boolean visitarBloque(BloqueTransitable b);
	public boolean visitarBloque(BloqueIntransitable b);
	public boolean visitarBloqueMover(BloqueIntransitable b);
	public boolean visitarBloqueMover(BloqueTransitable b);
}
