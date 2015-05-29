package queComemos;

import java.util.HashSet;
import java.util.Set;

public class FiltroPreparacionCara implements FiltroI {

	@Override
	public Set<Receta> filtrar(Set<Receta> recetas, Usuario usr) {
		Set<String> comidasCaras  = new HashSet<String>();
		comidasCaras.add("lechon");
		comidasCaras.add("lomo");
		comidasCaras.add("salmon");
		comidasCaras.add("alcaparras");
		
		
		recetas.removeIf(receta -> receta.contieneAlguna(comidasCaras));
		return recetas ;
		
		
	}
	
	

}
