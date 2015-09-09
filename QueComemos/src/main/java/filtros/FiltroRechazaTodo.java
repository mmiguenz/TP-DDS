package filtros;

import interfaces.FiltroI;

import java.util.ArrayList;
import java.util.List;

import receta.Receta;
import usuario.Usuario;

public class FiltroRechazaTodo implements FiltroI {

	@Override
	public List<Receta> filtrar(List<Receta> recetas, Usuario usr) {
		return new ArrayList<Receta>();
	}

}
