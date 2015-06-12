package queComemos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class QueComemosApp {
	
	public static List<Usuario> usuarios ; 
	public static List<Receta> recetas;
	public static List<CondicionPreexistenteI> inadecuados;
	public static List<Grupo> grupos;
	public static ObservadorDeRecetas observadorDeRecetas;
	
	


	
	public static void inicializar()
	
	{
		usuarios = new ArrayList<Usuario>();
		recetas = new ArrayList<Receta>();
		inadecuados = new ArrayList<CondicionPreexistenteI>();
		
		grupos = new ArrayList<Grupo>();
	
		
	}
	
	@SuppressWarnings("unchecked")
	public static List<CondicionPreexistenteI> calcularInadecuadosParaReceta(Receta receta)
	{
			
		  return  inadecuados.stream().filter(inadecuado->! inadecuado.esAptaReceta(receta))
				    .collect(Collectors.toList());
	}
		
		
	

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

	  
	  
	  private static Receta buscarRecetaPorNombre (String nombre){
		  for (Receta receta : recetas ){
			  if (receta.getNombre().equals(nombre)){
				  observadorDeRecetas.notificar(receta);
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
	  
	  
	  public static List<Receta> mostrarRecetasAccesiblesPorUsuario(Usuario usr)
	  {
		  
		  Set<Receta> resultado = new HashSet<Receta>();
		  
		  resultado.addAll(recetas);
		  resultado.addAll(usr.getMisRecetas());
		  List<Grupo> gruposDeUsuario = buscarGruposDeUsuario(usr);
		  
		  for (Grupo grupo : gruposDeUsuario )
		  {
			  for (Usuario usuario: grupo.getUsuarios())
			  {
				  resultado.addAll(usuario.getMisRecetas());
				  
				  
			  }
			  
			  
		  }
		  
		  try {
		  
		  RepoRecetasAd repoRecetasExterno = new RepoRecetasAd();
		 
		  resultado.addAll(repoRecetasExterno.traerTodasRecetasExternas());
		  
		 
		  return resultado.stream().collect(Collectors.toList()) ;
		  
		  }
		  
		  catch (RuntimeException ex)
		  {
			  
			  ex.printStackTrace();
			  return resultado.stream().collect(Collectors.toList()) ;
			  
		  }
		  
		  
		  
	  }
	  
	  
	  private static List<Grupo> buscarGruposDeUsuario(Usuario usr)
	  {
		  List<Grupo> gruposDeUsuario = new ArrayList<Grupo>();
		  
		 
		  for (Grupo grupo : grupos )
		  {
			  if (grupo.getUsuarios().contains(usr))
				  gruposDeUsuario.add(grupo);
			  
		  }
		  
		  return gruposDeUsuario;
		  
	  }
	  
	  
	  public static List<Receta> consultarRecetas(Usuario usr,List<FiltroI> filtros)
	  {
		  List<Receta> consultaResul =QueComemosApp.mostrarRecetasAccesiblesPorUsuario(usr);
		  
		  for (FiltroI filtro : filtros)
		  {
			  consultaResul = filtro.filtrar(consultaResul,usr);
			  
			  
		  }
		  
		  
		  observadorDeRecetas.notificar(consultaResul);
		  return consultaResul;
		  
		  
	  }
	  
	  public static List<Receta> consultarRecetas(Usuario usr,List<FiltroI> filtros, ProcesamientoI procesamiento)
	  {
		  List<Receta> consultaResul =QueComemosApp.mostrarRecetasAccesiblesPorUsuario(usr);
		  
		  for (FiltroI filtro : filtros)
		  {
			  consultaResul = filtro.filtrar(consultaResul,usr);
			  
			  
		  }
		  
		  observadorDeRecetas.notificar(consultaResul);
		  return procesamiento.procesar(consultaResul);
		  
		  
	  }
	  
	  
	  public static List<Receta> consultarEnRepoExterno(ConsultaRepoExtAd consulta )
	  {
		  try {
			  
		  
		  RepoRecetasAd repo  = new RepoRecetasAd();
		   return repo.consultaEnRepoExterno(consulta);
		  }
		  catch (RuntimeException ex)
		  {
			  ex.printStackTrace();
			  return null ;
			  
			  
		  }
		  
		  
	  }
	  
	  
	  public Receta recetaMasConsultada(){
		  int max=0;
		  String nombreMasConsultada="null";
		  for (Receta receta : this.recetas)
		  {
			  if(receta.getcantidadDeConsultas()>max){
				  max=receta.getcantidadDeConsultas();
				  nombreMasConsultada=receta.getNombre();				  
			  }			  
		  }
		  return (buscarRecetaPorNombre(nombreMasConsultada));
	  }
	  	
	  
	  
	  
}

