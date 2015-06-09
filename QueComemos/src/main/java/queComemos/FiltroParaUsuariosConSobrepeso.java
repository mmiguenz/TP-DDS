package queComemos;

import java.util.Set;

import java.util.stream.Collectors;

public class FiltroParaUsuariosConSobrepeso implements FiltroI {

	@Override
	public Set<Receta> filtrar(Set<Receta> recetas,Usuario usr) {
		
		if (usr.indiceMasaCorporal()> 50) // lo implemeto asi ya que no queda claro bajo que condiciones esta en sobrepeso.
		//recetas.removeIf(receta -> receta.getCalorias()>500  );
			return 	recetas.stream().filter(receta->receta.getCalorias()<500).collect(Collectors.toSet());
		else 
			return recetas;
		
	}
	
	

}
