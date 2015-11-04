package condicionesPreexistentes;

import java.util.List;

import javax.persistence.DiscriminatorValue;

import receta.Receta;
import usuario.Usuario;
@DiscriminatorValue("Hipertenso")
public class Hipertenso extends CondicionPreexistente {
	
	public Hipertenso(Long id, String nombre, List<String> comidasQueDisgusta) {
		super(id, nombre, comidasQueDisgusta);
		// TODO Auto-generated constructor stub
	}




	public boolean subSanaCondicion(Usuario usuario) {

		return usuario.getRutina().equals("Intensiva");
	}



	public boolean esAptaReceta(Receta receta){
		return ! comidasProhibidas.stream().anyMatch(comida -> receta.contiene(comida));
	}




}