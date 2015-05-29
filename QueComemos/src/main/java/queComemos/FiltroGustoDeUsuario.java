package queComemos;

import java.util.Set;

public class FiltroGustoDeUsuario implements FiltroI {

	@Override
	public Set<Receta> filtrar(Set<Receta> recetas, Usuario usr) {
	
		recetas.removeIf(receta -> ! usr.leGusta(receta));
		return recetas;
	}

	
}
