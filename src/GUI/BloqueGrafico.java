package GUI;

import javax.swing.ImageIcon;

public class BloqueGrafico {
	
	protected String imagen;
	
	protected ImageIcon grafico;
	
	public BloqueGrafico() {
		this.grafico = new ImageIcon();
		imagen = null;
	}
	
	public void cambiarImagen(String im) {
		this.imagen = im;
		ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(imagen));
		this.grafico.setImage(imageIcon.getImage());
	}
	
	public String getImagen() {
		return imagen;
	}
	
	public void actualizar() {
		//TODO: Implementar.
	}
}
