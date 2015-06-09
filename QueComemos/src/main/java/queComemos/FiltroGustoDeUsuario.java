package queComemos;

import java.util.Set;
import java.util.stream.Collectors;

public class FiltroGustoDeUsuario implements FiltroI {

	@Override
	public Set<Receta> filtrar(Set<Receta> recetas, Usuario usr) {
	
		//recetas.removeIf(receta -> ! usr.leGusta(receta));
		return recetas.stream().filter(receta-> usr.leGusta(receta)).collect(Collectors.toSet());
		
	}

	
}
