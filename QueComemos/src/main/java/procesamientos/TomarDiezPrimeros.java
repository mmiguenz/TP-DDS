package procesamientos;

import interfaces.ProcesamientoI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import receta.Receta;

public class TomarDiezPrimeros implements ProcesamientoI {

	@Override
	public List<Receta> procesar(List<Receta> recetas) {
	 
		Iterator<Receta> it =  recetas.iterator();
		List<Receta> resul = new ArrayList<Receta>();
			for (int i = 0 ; i < 10 ; i ++)
			{
				if (it.hasNext())
				resul.add(it.next());
				
				
			}
		return resul;	
	}

}
