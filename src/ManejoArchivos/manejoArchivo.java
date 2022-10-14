package ManejoArchivos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Juego.Ranking;

public class manejoArchivo {
	
	public Ranking leer(String archivo) throws Exception {
	    FileInputStream file = new FileInputStream(archivo);
	    ObjectInputStream in = new ObjectInputStream(file);
	    Ranking rank = (Ranking) in.readObject();
	    in.close();
	    file.close();
	    return rank;
	}
	
	public void escribir(Ranking rank, String archivo) throws Exception {
		FileOutputStream file = new FileOutputStream(archivo);
	    ObjectOutputStream out = new ObjectOutputStream(file);
	    out.writeObject(rank);
	    out.close();
	    file.close();
	}
}
