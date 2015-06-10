package filtros;

import java.util.ArrayList;
import java.util.List;

import queComemos.FiltroI;
import queComemos.Receta;
import queComemos.Usuario;

public class FiltroRechazaTodo implements FiltroI {

	@Override
	public List<Receta> filtrar(List<Receta> recetas, Usuario usr) {
		return new ArrayList<Receta>();
	}

}
