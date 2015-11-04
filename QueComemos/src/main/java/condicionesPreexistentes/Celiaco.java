package condicionesPreexistentes;

import java.util.List;

import javax.persistence.DiscriminatorValue;

import receta.Receta;
import usuario.Usuario;
@DiscriminatorValue("Celiaco")
public class Celiaco extends CondicionPreexistente {



	public Celiaco(Long id, String nombre, List<String> comidasQueDisgusta) {
		super(id, nombre, comidasQueDisgusta);
		// TODO Auto-generated constructor stub
	}



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