package builder;

import java.util.ArrayList;
import java.util.List;

import queComemos.CondicionPreexistenteI;
import condicionesPreexistentes.Diabetico;


public class DiabeticoBuilder extends CondicionBuilder{
	
	public String nombre;
	public List<String> comidasProhibidas;
	
	public DiabeticoBuilder(){
		this.nombre="Diabetico";
		List<String> comidas= new ArrayList<String>();
		comidas.add("Azucar");
		this.comidasProhibidas=comidas;
	}
	
	public CondicionPreexistenteI crear(){
		return( new Diabetico(this.nombre, this.comidasProhibidas));
	}
	
}