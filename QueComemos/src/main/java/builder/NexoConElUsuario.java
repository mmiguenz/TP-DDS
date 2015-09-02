package builder;

import java.util.List;

import queComemos.CondicionPreexistenteI;
import queComemos.GustosSobreAlimentos;

public class NexoConElUsuario {
	private CondicionBuilder condicion;
	
	public NexoConElUsuario(){
	}
	
	public void setCondicion(CondicionBuilder cond){
		this.condicion=cond;
	}
	
	public CondicionPreexistenteI crear(){
		return (condicion.crear());
	}

}
