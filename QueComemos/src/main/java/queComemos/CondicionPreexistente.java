package queComemos;

import java.util.Set;

public class CondicionPreexistente {
	
	private String nombre; 
	private Set<String> comidasProhibidas;

	
	
	public CondicionPreexistente(String nombre, Set<String> comidasProhibidas) {

		this.nombre = nombre;
		this.comidasProhibidas = comidasProhibidas;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Set<String> getComidasProhibidas() {
		return comidasProhibidas;
	}
	public void setComidasProhibidas(Set<String> comidasProhibidas) {
		this.comidasProhibidas = comidasProhibidas;
	}
	
	

}
