package queComemos;

import java.util.Set;

public interface RepoUsuarios {
	
	public void add(Usuario user);
	
	public void remove(Usuario user);
	
	public void update(Usuario user);
	
	public Usuario get(String nombre);
	
	public Set<Usuario> list(Usuario user);

}