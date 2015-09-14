package repositorios;

import interfaces.CondicionPreexistenteI;
import interfaces.FiltroI;
import interfaces.ObservadorI;
import interfaces.ProcesamientoI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import receta.Preparacion;
import receta.Receta;
import usuario.Grupo;
import usuario.Usuario;

public class Recetario {

	public static List<Receta> recetas;
	public static List<CondicionPreexistenteI> inadecuados ;
	public static List<ObservadorI>observadores;
	public static List<Consulta> consultas;
	
	

	
	public static Receta modificarRecetaPublica (String nombre, String nuevoNombre,Double calorias, Preparacion preparacion,List<Receta>subRecetas,String dificultad)
	{

		Receta recetaPublica = buscarRecetaPorNombre(nombre);
		
		if (recetaPublica != null )
		{
			
		
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
		
		return null;

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

	public static Long obtenerCantidadDeRecetasConsultadas(LocalDateTime horaConsultaDesde, LocalDateTime horaConsultaHasta) {
		
				
		return   consultas.stream()
				.filter(unaConsulta -> unaConsulta.estaEnRangoHorario(horaConsultaDesde,horaConsultaHasta))
				.mapToLong(consulta -> consulta.cantidadRecetasResultado())
				.sum();
				
				
	}

	public static Receta obtenerRecetaMasConsultada() {
		
		
		List<Receta> recetasConsultadas = new ArrayList<>();
		 consultas.forEach(consulta ->recetasConsultadas.addAll(consulta.obtenerResultadoConsulta()));
		
		
		 Comparator<Receta> comparaRecetas =   (unareceta , otraReceta)  -> Long.compare(Collections.frequency(recetasConsultadas,unareceta ), Collections.frequency(recetasConsultadas,otraReceta )) ;
		 
		 Optional<Receta> recetaMasConsultada = recetasConsultadas.stream().max(comparaRecetas);
		 
		 if(recetaMasConsultada.isPresent())
			 return  recetaMasConsultada.get();
		
		 
		return null;
		
	}
	  
	  
	  


	  
}
