package queComemos;

import java.util.Set;

public class Vegano extends CondicionPreexistente {

	public Vegano(String nombre, Set<String> comidasProhibidas) {
		super(nombre, comidasProhibidas);
		
	}
	
	
	public boolean subSanaCondicion(Usuario usuario)
	{
		return usuario.getPreferenciaAlimenticia().getComidasQueGusta().stream().filter(unaComida -> unaComida=="frutas").count() >0;
		
		
	}
	
	public static boolean esRecomendable(Receta subReceta,Preparacion preparacion)
	{
		if (subReceta ==null)
			return !((preparacion.contiene("pollo")) || (preparacion.contiene("carne")) || (preparacion.contiene("chivito")) || (preparacion.contiene("chori"))) ;
		else
			return !((preparacion.contiene("pollo")) || (preparacion.contiene("carne")) || (preparacion.contiene("chivito")) || (preparacion.contiene("chori"))) 
					|| !((subReceta.contiene("pollo")) || (subReceta.contiene("carne")) || (subReceta.contiene("chivito")) || (subReceta.contiene("chori"))); 
		

		
	

}
	
}
