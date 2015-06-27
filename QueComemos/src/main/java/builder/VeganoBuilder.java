package builder;

import java.util.ArrayList;
import java.util.List;

import queComemos.CondicionPreexistenteI;
import condicionesPreexistentes.Vegano;

public class VeganoBuilder extends CondicionBuilder{
	public String nombre;
	public List<String> comidasProhibidas;
	
	public VeganoBuilder(){
		this.nombre="Vegano";
		List<String> comidas= new ArrayList<String>();
		comidas.add("Pollo");
		comidas.add("Carne");
		comidas.add("Chori");
		comidas.add("Chivito");
		this.comidasProhibidas=comidas;
	}
	
	public CondicionPreexistenteI crear(){
		return(new Vegano(this.nombre, this.comidasProhibidas));
	}
	
}