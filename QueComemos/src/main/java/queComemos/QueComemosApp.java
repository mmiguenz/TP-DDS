package queComemos;

import java.util.HashSet;
import java.util.Set;

public class QueComemosApp {
	
	public static Set<Usuario> usuarios ; 
	public static Set<Receta> recetas;
	public static Set<CondicionPreexistenteI> inadecuados;


	@SuppressWarnings("unchecked")
	public static Set<CondicionPreexistenteI> calcularInadecuadosParaReceta(Receta receta)
	{
		
		for (CondicionPreexistenteI inadecuado : inadecuados)
		{
			if (!  inadecuado.esAptaReceta(receta))
			{
				inadecuados.add(inadecuado);
				
			}
			
			
		}
		
		return inadecuados ;
		
	}

	public static Receta modificarRecetaPublica (String nombre, String nuevoNombre)
	{

	  Receta recetaAModificar = buscarRecetaPorNombre(nombre);
	  recetaAModificar.setNombre(nuevoNombre);
	  return recetaAModificar;
	  
	}

	  
	  
	  private static Receta buscarRecetaPorNombre (String nombre){
		  for (Receta receta : recetas ){
			  if (receta.getNombre().equals(nombre)){
				  return receta;
			  }
		  }
	  return null;
	  }
}

