package queComemos;

import java.util.Set;

public interface FiltroI {
	
	public Set<Receta> filtrar (Set<Receta> recetas,Usuario usr);

}
