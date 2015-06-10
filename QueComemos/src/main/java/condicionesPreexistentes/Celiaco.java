package condicionesPreexistentes;

import java.util.List;

import queComemos.CondicionPreexistenteI;
import queComemos.Receta;
import queComemos.Usuario;

public class Celiaco implements CondicionPreexistenteI {

	public Celiaco(String nombre, List<String> comidasProhibidas) {
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
	private String nombre;
	private List<String> comidasProhibidas;

	public boolean subSanaCondicion(Usuario usuario) {

		return true;
	}

	

	public boolean validar(Usuario usr) {
		return true;

	}
	public boolean esAptaReceta(Receta receta){
		
		return true;
	}



	
}