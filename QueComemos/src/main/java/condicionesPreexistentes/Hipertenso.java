package condicionesPreexistentes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.*;

import receta.Receta;
import usuario.Usuario;
@Entity
@DiscriminatorValue("hipertenso")
public class Hipertenso extends CondicionPreexistente {
	
	private List<String>comidasProhibidas;
	
	public Hipertenso(Long id, String nombre, List<String> comidasQueDisgusta) {
		super(id, nombre);
		
		comidasProhibidas= new ArrayList<String>();
		comidasProhibidas.add("sal");
		// TODO Auto-generated constructor stub
	}




	public boolean subSanaCondicion(Usuario usuario) {

		return usuario.getRutina().equals("Intensiva");
	}



	public boolean esAptaReceta(Receta receta){
		return ! comidasProhibidas.stream().anyMatch(comida -> receta.contiene(comida));
	}




}