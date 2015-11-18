package condicionesPreexistentes;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import receta.Receta;
import usuario.Usuario;
@Entity
@DiscriminatorValue("diabetico")
public class Diabetico extends CondicionPreexistente {
	
		
	

	public Diabetico(Long id, String nombre) {
		super(id, nombre);
		// TODO Auto-generated constructor stub
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