package condicionesPreexistentes;



import javax.persistence.DiscriminatorValue;
import javax.persistence.*;

import receta.Receta;
import usuario.Usuario;
@Entity
@DiscriminatorValue("celiaco")
public class Celiaco extends CondicionPreexistente {



	public Celiaco(Long id, String nombre) {
		super(id, nombre);
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


	public Celiaco()
	{
		super();
		
	}



	
}