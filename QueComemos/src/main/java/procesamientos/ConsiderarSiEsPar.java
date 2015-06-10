package procesamientos;

import java.util.ArrayList;
import java.util.List;

import queComemos.ProcesamientoI;
import queComemos.Receta;

public class ConsiderarSiEsPar implements ProcesamientoI {

	@Override
	public List<Receta> procesar(List<Receta> recetas) {
		
		if ((recetas.size() / 2) == 0)
			return recetas;
		else 
			return  new ArrayList<Receta>(); 
			
	}
	
	

}
