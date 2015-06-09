package queComemos;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class QueComemosApp {
	
	public static Set<Usuario> usuarios ; 
	public static Set<Receta> recetas;
	public static Set<CondicionPreexistenteI> inadecuados;
	public static Set<Grupo> grupos;
	


	
	public static void inicializar()
	
	{
		usuarios = new HashSet<Usuario>();
		recetas = new HashSet<Receta>();
		inadecuados = new HashSet<CondicionPreexistenteI>();
		
		grupos = new HashSet<Grupo>();
		
		
	}
	
	@SuppressWarnings("unchecked")
	public static Set<CondicionPreexistenteI> calcularInadecuadosParaReceta(Receta receta)
	{
			
		  return  inadecuados.stream().filter(inadecuado->! inadecuado.esAptaReceta(receta))
				    .collect(Collectors.toSet());
	}
		
		
	

	public static Receta modificarRecetaPublica (String nombre, String nuevoNombre,Double calorias, Preparacion preparacion,Set<Receta>subRecetas,String dificultad)
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
	  
	  
	  public static boolean puedeSugerir(Receta receta , Usuario usr)
	  {
		  return usr.esAdecuadaLaReceta(receta);
		  
		  
	  }
	  
	  public static boolean puedeSugerir(Receta receta ,Grupo grupo)
	  {
		  return grupo.puedeSugerir(receta);
		  
		  
	  }
	  
	  
	  public static Set<Receta> mostrarRecetasAccesiblesPorUsuario(Usuario usr)
	  {
		  
		  Set<Receta> resultado = new HashSet<Receta>();
		  
		  resultado.addAll(recetas);
		  resultado.addAll(usr.getMisRecetas());
		  Set<Grupo> gruposDeUsuario = buscarGruposDeUsuario(usr);
		  
		  for (Grupo grupo : gruposDeUsuario )
		  {
			  for (Usuario usuario: grupo.getUsuarios())
			  {
				  resultado.addAll(usuario.getMisRecetas());
				  
				  
			  }
			  
			  
		  }
		  
		  return resultado ;
		  
		  
		  
		  
	  }
	  
	  
	  private static Set<Grupo> buscarGruposDeUsuario(Usuario usr)
	  {
		  Set<Grupo> gruposDeUsuario = new HashSet<Grupo>();
		  
		 
		  for (Grupo grupo : grupos )
		  {
			  if (grupo.getUsuarios().contains(usr))
				  gruposDeUsuario.add(grupo);
			  
		  }
		  
		  return gruposDeUsuario;
		  
	  }
	  
	  
	  public static Set<Receta> consultarRecetas(Usuario usr,Set<FiltroI> filtros)
	  {
		  Set<Receta> consultaResul =QueComemosApp.mostrarRecetasAccesiblesPorUsuario(usr);
		  
		  for (FiltroI filtro : filtros)
		  {
			  consultaResul = filtro.filtrar(consultaResul,usr);
			  
			  
		  }
		  
		  return consultaResul;
		  
		  
	  }
	  
	  
	  
}

