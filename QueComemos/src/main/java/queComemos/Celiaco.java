package queComemos;

import java.util.Set;

public class Celiaco implements CondicionPreexistenteI {

	public Celiaco(String nombre, Set<String> comidasProhibidas) {
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
	private String nombre;
	private Set<String> comidasProhibidas;

	public boolean subSanaCondicion(Usuario usuario) {

		return true;
	}

	

	public boolean validar(Usuario usr) {
		return true;

	}
	public boolean esAptaReceta(Receta receta){
		//return usr.esAptaRecetaCeliaco(receta);
		return true;
	}



	
}