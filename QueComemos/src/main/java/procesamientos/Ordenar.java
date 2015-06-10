package procesamientos;

import java.util.List;

import queComemos.CriterioOrdenamientoI;
import queComemos.ProcesamientoI;
import queComemos.Receta;

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
