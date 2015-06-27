package condicionesPreexistentes;

import java.util.List;

import queComemos.CondicionPreexistenteI;
import queComemos.Receta;
import queComemos.Usuario;

public class Diabetico implements CondicionPreexistenteI {
	
	private String nombre;
	private List<String> comidasProhibidas;
	
	
	
	
	public Diabetico(String nombre, List<String> comidasProhibidas) {
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

	public List<String> getComidasProhibidas() {
		return comidasProhibidas;
	}

	public void setComidasProhibidas(List<String> comidasProhibidas) {
		this.comidasProhibidas = comidasProhibidas;
	}

	
	
	
	

	public boolean subSanaCondicion(Usuario usuario) {
		return usuario.getRutina() == "Activa" && usuario.getPeso() <= 70.0;

	}

	public boolean subSanaCondicionBuilder(String rutina, Double peso, List<String> comidas){
		return (rutina== "Activa" && peso <= 70.0);
	}

	
	public boolean esAptaReceta(Receta receta){
		
		return !(receta.buscaIngrediente("azucar").getCantidad() >100.0 || receta.buscaIngrediente("Azucar").getCantidad() >100.0 );
	}

	

}