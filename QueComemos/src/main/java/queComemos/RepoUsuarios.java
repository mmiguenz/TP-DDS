package queComemos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepoUsuarios {
	
	public static List<Usuario> usuarios= new ArrayList<Usuario>();
	public static List<Grupo> grupos = new ArrayList<Grupo>();
	
	
	public static void add(Usuario usr){
		
		usuarios.add(usr);
				
		
	}
	
	  public static List<Grupo> buscarGruposDeUsuario(Usuario usr)
	  {
		  List<Grupo> gruposDeUsuario = new ArrayList<Grupo>();
		  
		if (!(grupos == null || grupos.isEmpty())) {

			for (Grupo grupo : grupos) {
				if (grupo.getUsuarios().contains(usr))
					gruposDeUsuario.add(grupo);
			}
		}
		  
		  return gruposDeUsuario;
		  
	  }
	
	
	public static void remove(Usuario usr)
	{
				
		usuarios.remove(usr);
		
	}
	
	
	public static void update(Usuario usr )
	{
		 long c = usuarios.stream().filter(usuario -> usuario.getUsuarioID().equals(usr.getUsuarioID())).count();
		
		Usuario usrOriginal =  usuarios.stream().filter(usuario -> usuario.getUsuarioID().equals(usr.getUsuarioID())).collect(Collectors.toList()).get(0);
		
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
		
		
	}


	public static Usuario get(Usuario usr)
	{
		
		return usuarios.stream().filter(usuario -> usuario.getNombre().equals(usr.getNombre())).collect(Collectors.toList()).get(0);
		
		
		
	}
	
	
	
	public static List<Usuario> list(Usuario usr )
	{
		if(usr.getCondicionesPreexistentes() == null || usr.getCondicionesPreexistentes().isEmpty())
		{
		
			return usuarios.stream().filter(usuario -> usuario.getNombre().equals(usr.getNombre())).collect(Collectors.toList());
						
			
			
		} else {
			
					
			
			return usuarios.stream().filter(usuario -> usuario.getNombre().equals(usr.getNombre()) && usuario.getCondicionesPreexistentes().containsAll(usr.getCondicionesPreexistentes())).collect(Collectors.toList());
		
		
		
		
				}
	
	}

	
	

}

