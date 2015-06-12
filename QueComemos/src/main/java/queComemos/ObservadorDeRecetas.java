package queComemos;

import java.util.List;
import java.util.Set;

public class ObservadorDeRecetas implements Observers{

	public void notificar(Receta receta){
		receta.teConsulte();
	}
	
	public void notificar(List<Receta> recetas){
		recetas.forEach((Receta receta)->receta.teConsulte());
	}
	
}
