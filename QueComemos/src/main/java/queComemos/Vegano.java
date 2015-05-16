package queComemos;

import java.util.HashSet;
import java.util.Set;

public class Vegano implements CondicionPreexistenteI {

	private String  nombre;
	private Set<String> comidasProhibidas;
	
	
	public Vegano(String nombre, Set<String> comidasProhibidas) {
		this.nombre=nombre;
		this.comidasProhibidas=comidasProhibidas;

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

	@Override
	public boolean subSanaCondicion(Usuario usuario) {
		return usuario.getPreferenciaAlimenticia().getComidasQueGusta()
				.stream().filter(unaComida -> unaComida == "frutas").count() > 0;

	}


	/*public boolean validar(Usuario usr) {
		usr.getPreferenciaAlimenticia().getComidasQueGusta().stream().anyMatch(comida ->comidasProhibidas.contains(comida));
	}*/
	
	@Override
	public boolean esAptaReceta(Receta receta){
		return !( comidasProhibidas.stream().anyMatch(comida -> receta.contiene(comida)));
	}

	
}
