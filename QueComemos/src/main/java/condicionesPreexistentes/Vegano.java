package condicionesPreexistentes;

import interfaces.CondicionPreexistenteI;

import java.util.ArrayList;
import java.util.List;

import receta.Receta;
import usuario.Usuario;

public class Vegano implements CondicionPreexistenteI {

	private String  nombre;
	private List<String> comidasProhibidas;
	
	
	public Vegano(String nombre, List<String> comidasProhibidas) {
		this.nombre=nombre;
		this.comidasProhibidas=comidasProhibidas;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public List<String> getComidasProhibidas() {
		return comidasProhibidas;
	}

	
	public void setComidasProhibidas(List<String> comidasProhibidas) {
		this.comidasProhibidas = comidasProhibidas;
	}

	@Override
	public boolean subSanaCondicion(Usuario usuario) {
		return usuario.getPreferenciaAlimenticia().getComidasQueGusta()
				.stream().filter(unaComida -> unaComida == "frutas").count() > 0;

	}

	public boolean subSanaCondicionBuilder(String rutina, Double peso, List<String> comidas){
		return (comidas.stream().anyMatch(c->c.equals("frutas")));
	}

	
	@Override
	public boolean esAptaReceta(Receta receta){
		return !( comidasProhibidas.stream().anyMatch(comida -> receta.contiene(comida)));
	}

	
}
