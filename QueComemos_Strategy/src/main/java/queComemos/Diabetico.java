package queComemos;

import java.util.Set;

public class Diabetico implements CondicionPreexistenteI {
	
	private String nombre;
	private Set<String> comidasProhibidas;
	
	
	
	
	public Diabetico(String nombre, Set<String> comidasProhibidas) {
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
		return usuario.getRutina() == "Activa" && usuario.getPeso() <= 70.0;

	}



	
	public boolean esAptaReceta(Receta receta){
		
		return !(receta.buscaIngrediente("azucar").getCantidad() >100.0  );
	}

	

}