package queComemos;

import java.util.List;

public class Ordenar implements ProcesamientoI {
	
	private CriterioOrdenamientoI criterio;

	public Ordenar(CriterioOrdenamientoI criterio) {
		this.criterio= criterio ; 
	}

	@Override
	public List<Receta> procesar(List<Receta> recetas) {
		
		return criterio.ordernar(recetas);
	}
	

}
