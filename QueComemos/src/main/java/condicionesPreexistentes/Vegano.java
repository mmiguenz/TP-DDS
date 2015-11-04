package condicionesPreexistentes;




import java.util.List;

import javax.persistence.DiscriminatorValue;

import receta.Receta;
import usuario.Usuario;
@DiscriminatorValue("Vegano")
public class Vegano extends CondicionPreexistente {

	public Vegano(Long id, String nombre, List<String> comidasQueDisgusta) {
		super(id, nombre, comidasQueDisgusta);
		// TODO Auto-generated constructor stub
	}




	@Override
	public boolean subSanaCondicion(Usuario usuario) {
		return usuario.getPreferenciaAlimenticia().getComidasQueGusta()
				.stream().filter(unaComida -> unaComida.equals("frutas")).count() > 0;

	}


	@Override
	public boolean esAptaReceta(Receta receta){
		return !( comidasProhibidas.stream().anyMatch(comida -> receta.contiene(comida)));
	}

	
}
