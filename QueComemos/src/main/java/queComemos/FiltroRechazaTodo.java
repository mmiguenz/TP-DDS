package queComemos;

import java.util.ArrayList;
import java.util.List;

public class FiltroRechazaTodo implements FiltroI {

	@Override
	public List<Receta> filtrar(List<Receta> recetas, Usuario usr) {
		return new ArrayList<Receta>();
	}

}
