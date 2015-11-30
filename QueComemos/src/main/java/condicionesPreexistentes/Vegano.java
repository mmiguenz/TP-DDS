package condicionesPreexistentes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;

import receta.Receta;
import usuario.Usuario;
@Entity
@DiscriminatorValue("vegano")
public class Vegano extends CondicionPreexistente {

	@Transient
	private final List<String>comidasProhibidas=new ArrayList<String>();
	
	public Vegano(Long id, String nombre) {
		super(id, nombre);
		
		
		comidasProhibidas.add("carne");
		comidasProhibidas.add("pollo");
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



	public Vegano()
	{
		super();
		
	}

	
}
