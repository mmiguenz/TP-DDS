package procesamientos;

import interfaces.CriterioOrdenamientoI;
import interfaces.ProcesamientoI;

import java.util.List;

import receta.Receta;

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
