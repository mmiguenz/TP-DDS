package queComemos;

import java.util.Set;

public class Hipertenso implements CondicionPreexistenteI {
	
	private String nombre;
	private Set<String>comidasProhibidas;
	


	public Hipertenso(String nombre, Set<String> comidasProhibidas) {
		super();
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


	public boolean subSanaCondicion(Usuario usuario) {

		return usuario.getRutina() == "Intensiva";
	}


	public boolean esAptaReceta(Receta receta){
		return ! comidasProhibidas.stream().anyMatch(comida -> receta.contiene(comida));
	}




}