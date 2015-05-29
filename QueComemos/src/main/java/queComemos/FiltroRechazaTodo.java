package queComemos;

import java.util.HashSet;
import java.util.Set;

public class FiltroRechazaTodo implements FiltroI {

	@Override
	public Set<Receta> filtrar(Set<Receta> recetas, Usuario usr) {
		return new HashSet<Receta>();
	}

}
