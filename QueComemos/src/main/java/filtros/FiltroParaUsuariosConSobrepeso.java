package filtros;

import interfaces.FiltroI;

import java.util.List;
import java.util.stream.Collectors;

import receta.Receta;
import usuario.Usuario;

public class FiltroParaUsuariosConSobrepeso implements FiltroI {

	@Override
	public List<Receta> filtrar(List<Receta> recetas,Usuario usr) {
		
		if (usr.indiceMasaCorporal()> 50) // lo implemeto asi ya que no queda claro bajo que condiciones esta en sobrepeso.
		//recetas.removeIf(receta -> receta.getCalorias()>500  );
			return 	recetas.stream().filter(receta->receta.getCalorias()<500).collect(Collectors.toList());
		else 
			return recetas;
		
	}
	
	

}
