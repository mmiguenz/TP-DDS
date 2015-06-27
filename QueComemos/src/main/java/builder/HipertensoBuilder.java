package builder;

import java.util.ArrayList;
import java.util.List;

import queComemos.CondicionPreexistenteI;
import condicionesPreexistentes.Hipertenso;

public class HipertensoBuilder extends CondicionBuilder{
	public String nombre;
	public List<String> comidasProhibidas;
	
	public HipertensoBuilder(){
		this.nombre="Hipertenso";
		List<String> comidas= new ArrayList<String>();
		comidas.add("Sal");
		comidas.add("Caldo");
		this.comidasProhibidas=comidas;
	}
	
	public CondicionPreexistenteI crear(){
		return(new Hipertenso(this.nombre, this.comidasProhibidas));
	}
	
}