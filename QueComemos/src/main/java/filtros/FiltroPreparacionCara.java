package filtros;

import interfaces.FiltroI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import receta.Receta;
import usuario.Usuario;

public class FiltroPreparacionCara implements FiltroI {

	@Override
	public List<Receta> filtrar(List<Receta> recetas, Usuario usr) {
		List<String> comidasCaras  = new ArrayList<String>();
		comidasCaras.add("lechon");
		comidasCaras.add("lomo");
		comidasCaras.add("salmon");
		comidasCaras.add("alcaparras");
		
		
		//recetas.removeIf(receta -> receta.contieneAlguna(comidasCaras));

    return 	recetas.stream().filter(receta -> !receta.contieneAlguna(comidasCaras)).collect(Collectors.toList());
     

	}
	
	

}
