package builder;

import queComemos.CondicionPreexistenteI;

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
