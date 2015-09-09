package repositorios;

import interfaces.CondicionPreexistenteI;
import interfaces.FiltroI;
import interfaces.ProcesamientoI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import receta.Preparacion;
import receta.Receta;
import usuario.Grupo;
import usuario.Usuario;

public class Recetario {

	public static List<Receta> recetas = new ArrayList<Receta>();
	public static List<CondicionPreexistenteI> inadecuados = new ArrayList<CondicionPreexistenteI>();
	
	

	
	public static Receta modificarRecetaPublica (String nombre, String nuevoNombre,Double calorias, Preparacion preparacion,List<Receta>subRecetas,String dificultad)
	{

		Receta recetaPublica = buscarRecetaPorNombre(nombre);
		Receta recetaAModificar = clonarReceta(recetaPublica);
		if (nuevoNombre !=null)
			recetaAModificar.setNombre(nuevoNombre);
		if (calorias != null)
			recetaAModificar.setCalorias(calorias);
		if (preparacion != null)
			recetaAModificar.setPreparacion(preparacion);
		if  (subRecetas != null)
			recetaAModificar.setSubRecetas(subRecetas);
		if (dificultad != null )
			recetaAModificar.setDificultad(dificultad);



		recetaAModificar.calcularInadecuados() ; 

		return recetaAModificar;

	}

	public static List<CondicionPreexistenteI> calcularInadecuadosParaReceta(Receta receta)
	{

		return  inadecuados.stream().filter(inadecuado->! inadecuado.esAptaReceta(receta))
				.collect(Collectors.toList());
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


	  
	  public static List<Receta> listarTodas()
	  {
		  List<Receta> listaRecetas = new ArrayList<Receta>();
		  		  
		  
		  RepoRecetasAd repoRecetasExterno = new RepoRecetasAd();
		  listaRecetas.addAll(repoRecetasExterno.traerTodasRecetasExternas());
		  listaRecetas.addAll(recetas);
		  
		  return listaRecetas;

	  }
	  
	  
}
