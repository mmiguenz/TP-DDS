package builder;

import java.util.ArrayList;
import java.util.List;

import queComemos.CondicionPreexistenteI;
import condicionesPreexistentes.Celiaco;

public class CeliacoBuilder extends CondicionBuilder{
	public String nombre;
	public List<String> comidasProhibidas;
	
	public CeliacoBuilder(){
		this.nombre="Celiaco";
		List<String> comidas= new ArrayList<String>();
		comidas.add("Harina");
		this.comidasProhibidas=comidas;
	}
	
	public CondicionPreexistenteI crear(){
		return(new Celiaco(this.nombre, this.comidasProhibidas));
	}

}
