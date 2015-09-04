package ar.edu.utn.frba.dds.tpAnual.queComemos.recetas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import queComemos.CondicionPreexistenteI;
import queComemos.ConsultaRepoExtAd;
import queComemos.FiltroI;
import queComemos.Grupo;
import queComemos.Preparacion;
import queComemos.ProcesamientoI;
import queComemos.Receta;
import queComemos.RepoRecetasAd;
import queComemos.RepoUsuarios;
import queComemos.Usuario;

public class Recetario {

	public static List<Receta> recetas = new ArrayList<Receta>();
	public static List<CondicionPreexistenteI> inadecuados = new ArrayList<CondicionPreexistenteI>();
	private static RepoUsuarios gruposDeUsuarios;
	
	public void Recetario(){

	}

	public static void setGruposDeUsuarios(RepoUsuarios gruposDeUsuarios) {
		Recetario.gruposDeUsuarios = gruposDeUsuarios;
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


	public static boolean puedeSugerir(Receta receta , Usuario usr)
	{
		return usr.esAdecuadaLaReceta(receta);
	}

	public static boolean puedeSugerir(Receta receta ,Grupo grupo)
	{
		return grupo.puedeSugerir(receta);
	}
	
	  public static List<Receta> consultarRecetas(Usuario usr,List<FiltroI> filtros)
	  {
		  List<Receta> consultaResul = mostrarRecetasAccesiblesPorUsuario(usr);
		  
		  for (FiltroI filtro : filtros)
		  {
			  consultaResul = filtro.filtrar(consultaResul,usr);
		  }
		  consultaResul.forEach(rec->rec.notificarObs(usr));
		  return consultaResul;
	  }
	  
	  public static List<Receta> consultarRecetas(Usuario usr,List<FiltroI> filtros, ProcesamientoI procesamiento)
	  {
		  List<Receta> consultaResul = mostrarRecetasAccesiblesPorUsuario(usr);
		  
		  for (FiltroI filtro : filtros)
		  {
			  consultaResul = filtro.filtrar(consultaResul,usr);
			  
			  
		  }
		  
		  consultaResul=procesamiento.procesar(consultaResul);
		  
		  consultaResul.forEach(rec->rec.notificarObs(usr));

		  
		  return consultaResul;
		  
		  
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
	  
	  public static List<Receta> mostrarRecetasAccesiblesPorUsuario(Usuario usr)
	  {
		  
		  Set<Receta> resultado = new HashSet<Receta>();
		  
		  resultado.addAll(recetas);
		  resultado.addAll(usr.getMisRecetas());
		  List<Grupo> gruposDeUsuario = gruposDeUsuarios.buscarGruposDeUsuario(usr);
		  
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

}
