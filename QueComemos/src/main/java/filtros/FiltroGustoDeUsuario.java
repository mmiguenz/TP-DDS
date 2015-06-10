package filtros;

import java.util.List;
import java.util.stream.Collectors;

import queComemos.FiltroI;
import queComemos.Receta;
import queComemos.Usuario;

public class FiltroGustoDeUsuario implements FiltroI {

	@Override
	public List<Receta> filtrar(List<Receta> recetas, Usuario usr) {
	
		//recetas.removeIf(receta -> ! usr.leGusta(receta));
		return recetas.stream().filter(receta-> usr.leGusta(receta)).collect(Collectors.toList());
		
	}

	
}
