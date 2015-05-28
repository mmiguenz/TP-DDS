package queComemos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Grupo {

	private String nombre;
	private Set<Usuario> usuarios;
	private PreferenciaAlimenticia preferenciaAlimenticia;
	

	public Grupo(String nombre, Set<Usuario> usuario,
			PreferenciaAlimenticia preferenciaAlimenticia) {
		this.setNombre(nombre);
		this.setUsuarios(usuario);
		this.setPreferenciaAlimenticia(preferenciaAlimenticia);
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> user) {
		this.usuarios=user;
	}
	
	public void agregarUsuario(Usuario user){
		this.usuarios.add(user);
	}
	
	
	public PreferenciaAlimenticia getPreferenciaAlimenticia() {
		return preferenciaAlimenticia;
	}

	public void setPreferenciaAlimenticia(
			PreferenciaAlimenticia preferenciaAlimenticia) {
		this.preferenciaAlimenticia = preferenciaAlimenticia;
	}
	
	//falta validar que ningun elemento de las preferencias alimenticias del grupo coincida con las preferencias de cada usuario
	/*
	public boolean validar(PreferenciaAlimenticia preferencia){
		preferencia.getComidasQueDisgusta().forEach();
		//usuarios.forEach((Usuario usuario)->usuario.getPreferenciaAlimenticia().getComidasQueDisgusta());
	}
	*/
		
		

}