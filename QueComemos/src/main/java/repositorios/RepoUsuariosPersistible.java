package repositorios;

import java.util.ArrayList;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import usuario.Usuario;


public class RepoUsuariosPersistible implements WithGlobalEntityManager {
	
	private static RepoUsuariosPersistible repo;
	
	
	public static RepoUsuariosPersistible getInstance()
	{
		if(repo == null)
		{
			return new RepoUsuariosPersistible();
			
		}else 
		{
			return repo;
			
		}
		
		
			
		
	}
	
	
	public Usuario getUsuario(Long usuarioID)
	{
		return entityManager().find(Usuario.class, usuarioID);
		
		
	}

	
	public void agregarUsuario(Usuario usuario)
	{
		entityManager().persist(usuario);
		
	}
	
	
}
