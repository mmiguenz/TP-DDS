package procesamientos;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import queComemos.CriterioOrdenamientoI;
import queComemos.Receta;

public class OrdenarPorNombre implements CriterioOrdenamientoI {
	
	

		@Override
		public List<Receta> ordernar(List<Receta> recetas) {
			
			Comparator<Receta> porNombre= (r1,r2) -> r1.getNombre().compareTo( r2.getNombre());
			
		return	recetas.stream().sorted(porNombre).collect(Collectors.toList());
			
		}
		
	}


