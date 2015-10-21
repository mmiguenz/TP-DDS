package condicionesPreexistentes;

import java.util.List;

import javax.persistence.DiscriminatorValue;

import receta.Receta;
import usuario.Usuario;
@DiscriminatorValue("Hipertenso")
public class Hipertenso extends CondicionPreexistente {
	
	private String nombre;
	private List<String>comidasProhibidas;
	


	public Hipertenso(String nombre, List<String> comidasProhibidas) {
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

		return usuario.getRutina().equals("Intensiva");
	}



	public boolean esAptaReceta(Receta receta){
		return ! comidasProhibidas.stream().anyMatch(comida -> receta.contiene(comida));
	}




}