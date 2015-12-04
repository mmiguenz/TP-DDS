package repositorios;


import interfaces.ObservadorI;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import receta.Preparacion;
import receta.Receta;
import usuario.Usuario;

public class Recetario implements WithGlobalEntityManager {
	
	
	private static Recetario recetario;
	 
	public  List<ObservadorI>observadores;
	public  List<Usuario> veganosConsultandoRecetasDificiles;
	public  List<Consulta> consultasLogueadas;
	
	
	public static Recetario getInstance()
	{
		if(recetario!=null)
		{
			return recetario;
		}else {
			
			recetario = new Recetario();
			return recetario;
		}
		
		
	}
	
	public Recetario()
	{
		observadores= new ArrayList<ObservadorI>();
		veganosConsultandoRecetasDificiles = new ArrayList<Usuario>();
		consultasLogueadas = new ArrayList<Consulta>();
		
		
	}
	
	
	public  Receta modificarRecetaPublica (String nombre, String nuevoNombre,Double calorias, Preparacion preparacion,List<Receta>subRecetas,String dificultad)
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



	private  Receta buscarRecetaPorNombre (String nombre){
		
		List<Receta> recetas = listarTodas();
		
		
		for (Receta receta : recetas ){
			if (receta.getNombre().equals(nombre)){
				return receta;
			}
		}
		return null;
	}

	private  Receta clonarReceta(Receta receta)
	{

		Receta recetaClon = new Receta (null, receta.getNombre(),receta.getCalorias(),receta.getPreparacion(),receta.getDificultad(),receta.getTemporada(),receta.getSubRecetas(),receta.getInadecuados());
		return recetaClon;

	}


	  
	  public  List<Receta> listarTodas()
	  {
		  List<Receta> listaRecetas = new ArrayList<Receta>();
		  List<Receta> recetas = entityManager().createQuery("from Receta", Receta.class).getResultList();
		  
		  RepoRecetasAd repoRecetasExterno = new RepoRecetasAd();
		  listaRecetas.addAll(repoRecetasExterno.traerTodasRecetasExternas());
		  listaRecetas.addAll(recetas);
		  
		  return listaRecetas;

	  }

	public  Long obtenerCantidadDeRecetasConsultadas(LocalDateTime horaConsultaDesde, LocalDateTime horaConsultaHasta) {
		
		
		
		
						
		return   listarConsultas().stream()
				.filter(unaConsulta -> unaConsulta.estaEnRangoHorario(horaConsultaDesde,horaConsultaHasta))
				.mapToLong(consulta -> consulta.cantidadRecetasResultado())
				.sum();
				
				
	}

	public  Receta obtenerRecetaMasConsultada() {
		
		
		
		List<Receta> recetasConsultadas = new ArrayList<>();
		listarConsultas().forEach(consulta ->recetasConsultadas.addAll(consulta.obtenerResultadoConsulta()));
		
		
		
		 Comparator<Receta> comparaRecetas =   (unareceta , otraReceta)  -> Long.compare(Collections.frequency(recetasConsultadas,unareceta ), Collections.frequency(recetasConsultadas,otraReceta )) ;
		 
		 Optional<Receta> recetaMasConsultada = recetasConsultadas.stream().max(comparaRecetas);
		 
		 if(recetaMasConsultada.isPresent())
			 return  recetaMasConsultada.get();
		
		 
		return null;
		
	}

	public  Receta obtenerRecetaMasConsultadaPorHombres() {
		 
		
		
		 List<Receta> recetasConsultadas = new ArrayList<>();
		 listarConsultas().stream()
		 		  .filter(consulta -> consulta.getUsr().getSexo().equals("masculino"))
		 		  .forEach(consulta ->recetasConsultadas.addAll(consulta.obtenerResultadoConsulta()));
		 
						 
		 Comparator<Receta> comparaRecetas =   (unareceta , otraReceta)  -> Long.compare(Collections.frequency(recetasConsultadas,unareceta ), Collections.frequency(recetasConsultadas,otraReceta )) ;
		 
		 Optional<Receta> recetaMasConsultada = recetasConsultadas.stream().max(comparaRecetas);
		 
		 if(recetaMasConsultada.isPresent())
			 return  recetaMasConsultada.get();
		
		 
		return null;
		
	}
	
	public  Receta obtenerRecetaMasConsultadaPorMujeres() {
		 
		
		
		 List<Receta> recetasConsultadas = new ArrayList<>();
		 listarConsultas().stream()
		 		  .filter(consulta -> consulta.getUsr().getSexo().equals("femenino"))
		 		  .forEach(consulta ->recetasConsultadas.addAll(consulta.obtenerResultadoConsulta()));
		 
						 
		 Comparator<Receta> comparaRecetas =   (unareceta , otraReceta)  -> Long.compare(Collections.frequency(recetasConsultadas,unareceta ), Collections.frequency(recetasConsultadas,otraReceta )) ;
		 
		 Optional<Receta> recetaMasConsultada = recetasConsultadas.stream().max(comparaRecetas);
		 
		 if(recetaMasConsultada.isPresent())
			 return  recetaMasConsultada.get();
		
		 
		return null;
		
	}

	public void agregarReceta(Receta receta) {
		
		entityManager().persist(receta);
		
		
	}

	public void agregarConsulta(Consulta unaConsulta) {
		
		entityManager().persist(unaConsulta);
		
	}

	public List<Consulta> listarConsultas() {
		
		
		
		return  entityManager().createQuery("from Consulta",Consulta.class).getResultList();
		
	}

	public List<Receta> listaRecetasUsuario() {
		
	
		
		List<Receta> recetasFavoritas = RepoUsuarios.getInstance().usuarioSession.getFavoritas();
		
	
	 
		
		
		return recetasFavoritas;
	}

	public Receta obtenerReceta(long recetaID) {

			return 		entityManager().find(Receta.class, recetaID);
		
	}
	
	

	  
	
	  
	  
	  


	  
}
