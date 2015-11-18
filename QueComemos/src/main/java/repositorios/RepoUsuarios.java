package repositorios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.CondicionPreexistente;
import receta.Receta;
import usuario.Grupo;
import usuario.Usuario;

public class RepoUsuarios implements WithGlobalEntityManager  {
	
	
	public static RepoUsuarios repoUsuarios;
	
	public  List<CondicionPreexistente> inadecuados ;
	
	
	
	public static RepoUsuarios getInstance()
	{
		
		if(repoUsuarios != null)
		{
			return repoUsuarios;		
		}else {
			
			repoUsuarios= new RepoUsuarios();
			return repoUsuarios;
		}
		
	}
	
	
	public RepoUsuarios()
	{
		//inadecuados =entityManager().createQuery("from CondicionPreexistente").getResultList();
		CondicionPreexistente celiacoo = entityManager().find(Celiaco.class,1);
		Celiaco celiaco = entityManager().find(Celiaco.class,1);
		
				
		
		
	}
	
	
	public CondicionPreexistente obtenerCondicion(String nombreCondicion)
	{
		
		return inadecuados.stream().filter(condicion -> condicion.nombre.equals(nombreCondicion)).findFirst().get();
		
	}
	
	
	public  void add(Usuario usr){
		
		entityManager().persist(usr);
				
		
	}
	
	  private   List<Grupo> buscarGruposDeUsuario(Usuario usr)
	  {
		  
		  List<Grupo> grupos = listarGrupos();
		  List<Grupo> gruposDeUsuario = new ArrayList<Grupo>();
		  
		  
		  
		if (!(grupos == null || grupos.isEmpty())) {

			for (Grupo grupo : grupos) {
				if (grupo.getUsuarios().contains(usr))
					gruposDeUsuario.add(grupo);
			}
		}
		  
		  return gruposDeUsuario;
		  
	  }
	
	
	public  void remove(Usuario usr)
	{
				
		//usuarios.remove(usr);
		
	}
	
	
	public  void update(Usuario usr )
	{

		
		Usuario usrOriginal =  listarTodos().stream().filter(usuario -> usuario.getUsuarioID().equals(usr.getUsuarioID())).collect(Collectors.toList()).get(0);
		
		usrOriginal.setNombre(usr.getNombre());
		usrOriginal.setCondicionesPreexistentes(usr.getCondicionesPreexistentes());
		usrOriginal.setEstatura(usr.getEstatura());
		usrOriginal.setFavoritas(usr.getFavoritas());
		usrOriginal.setFechaNacimiento(usr.getFechaNacimiento());
		usrOriginal.setMisRecetas(usr.getMisRecetas());
		usrOriginal.setPeso(usr.getPeso());
		usrOriginal.setPreferenciaAlimenticia(usr.getPreferenciaAlimenticia());
		usrOriginal.setRutina(usr.getRutina());
		usrOriginal.setSexo(usr.getSexo());
		
		entityManager().persist(usrOriginal);
		
		
	}


	public  Usuario get(Usuario usr)
	{
		
		return listarTodos().stream().filter(usuario -> usuario.getNombre().equals(usr.getNombre())).collect(Collectors.toList()).get(0);
		
				
	}
		
	
	public  List<Usuario> list(Usuario usr )
	{
		if(usr.getCondicionesPreexistentes() == null || usr.getCondicionesPreexistentes().isEmpty())
		{
		
			return listarTodos().stream().filter(usuario -> usuario.getNombre().equals(usr.getNombre())).collect(Collectors.toList());
						
			
			
		} else {		
					
			
			return listarTodos().stream().filter(usuario -> usuario.getNombre().equals(usr.getNombre()) && usuario.getCondicionesPreexistentes().containsAll(usr.getCondicionesPreexistentes())).collect(Collectors.toList());
		
			
				}
	
	}
	
	
	 public  List<Receta> mostrarRecetasAccesiblesPorUsuario(Usuario usr)
	  {
		  
		  Set<Receta> resultado = new HashSet<Receta>();
		  
		  resultado.addAll(Recetario.getInstance().listarTodas());
		  resultado.addAll(usr.getMisRecetas());
			
		  List<Grupo> gruposDeUsuario =  buscarGruposDeUsuario(usr);
		  
		  for (Grupo grupo : gruposDeUsuario )
		  {
			  for (Usuario usuario: grupo.getUsuarios())
			  {
				  resultado.addAll(usuario.getMisRecetas());
			  }
		  }
		  
		 	  
		  return resultado.stream().collect(Collectors.toList()) ;
		 
		  
		 
	  }
	 
	 
	 public  List<CondicionPreexistente> calcularInadecuadosParaReceta(Receta receta)
		{

			return  inadecuados.stream().filter(inadecuado->! inadecuado.esAptaReceta(receta))
					.collect(Collectors.toList());
		}


	 public List<Usuario> listarTodos()
	 {
		 return entityManager().createQuery("from Usuario",Usuario.class).getResultList();
		 
		 
	 }

	public List<Grupo> listarGrupos()
	{
		return entityManager().createQuery("from Grupo", Grupo.class).getResultList();
		
		
	}
	

}

