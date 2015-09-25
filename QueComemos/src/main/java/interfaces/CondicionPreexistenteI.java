package interfaces;


import receta.Receta;
import usuario.Usuario;

public interface CondicionPreexistenteI {

	public abstract boolean subSanaCondicion(Usuario usuario);
	public abstract boolean esAptaReceta(Receta receta);
	public String getNombre();
	

}