package queComemos;

import java.util.Set;

public class Celiaco extends CondicionPreexistente {

	public Celiaco(String nombre, Set<String> comidasProhibidas) {
		super(nombre, comidasProhibidas);
	
	}

	
	public boolean subSanaCondicion(Usuario usuario) {
		
		return  true ;
	}
	
	public static boolean esRecomendable(double calorias,Receta subReceta, Preparacion preparacion)
	{
		
		return true ; 
		

}

}