package interfaces;

import java.util.List;

import receta.Receta;
import usuario.Usuario;

public interface FiltroI {
	
	public List<Receta> filtrar (List<Receta> recetas,Usuario usr);

}
