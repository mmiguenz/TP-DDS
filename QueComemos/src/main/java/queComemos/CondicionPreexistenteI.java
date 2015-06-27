package queComemos;

import java.util.List;

public interface CondicionPreexistenteI {

	public abstract boolean subSanaCondicion(Usuario usuario);
	public abstract boolean esAptaReceta(Receta receta);
	public boolean subSanaCondicionBuilder(String rutina, Double peso, List<String> comidas);
	public String getNombre();
	

}