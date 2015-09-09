package procesamientos;

import interfaces.ProcesamientoI;

import java.util.ArrayList;
import java.util.List;

import receta.Receta;

public class ConsiderarSiEsPar implements ProcesamientoI {

	@Override
	public List<Receta> procesar(List<Receta> recetas) {
		
		List<Receta> resultado = new ArrayList<>() ;
	 for (int i = 0 ; i <  recetas.size(); i++ )
	 {
		 if(i % 2 == 0)
			 resultado.add(recetas.get(i));
		 
		 
		 
	 }
	 
	 return resultado ;
		
		
			
	}
	
	

}
