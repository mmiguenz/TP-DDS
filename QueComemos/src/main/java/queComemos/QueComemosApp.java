package queComemos;

import java.util.HashSet;
import java.util.Set;

public class QueComemosApp {
	
	public static Set<Usuario> usuarios ; 
	public static Set<Receta> recetas= new HashSet<Receta>();
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

	  Receta recetaPublica = buscarRecetaPorNombre(nombre);
	  Receta recetaAModificar = clonarReceta(recetaPublica);
	  if (!recetaPublica.getNombre().equals(nuevoNombre) && nuevoNombre !=null)
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
	  
	  private static Receta clonarReceta(Receta receta)
	  {
		 
		  Receta recetaClon = new Receta (receta.getNombre(),receta.getCalorias(),receta.getPreparacion(),receta.getDificultad(),receta.getTemporada(),receta.getSubRecetas(),receta.getInadecuados());
		  return recetaClon;
		  
	  }
	  
	  
}

