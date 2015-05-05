package queComemos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class Receta {
	
	private String nombre;
	private double calorias;
	private Preparacion preparacion ;
	private String dificultad;
	private String temporada;
	
	

	public Receta(String nombre,double calorias, Preparacion preparacion,String dificultad)
	{
		try{
			if (! preparacion.isOk())
			
				throw new RuntimeException("Debe ingresar al menos un Ingrediente");
			
				
			if (calorias < 10 || calorias >5000)
				
				throw new RuntimeException("Las calorias deben estar en un rango de 10-5000");
				
								
			this.nombre=nombre;
			this.preparacion=preparacion;
			this.dificultad=dificultad;

				
			
			
			
		}
		
		catch (Exception ex){
			
			
		}
		
			
		
	}
	
	public boolean contiene(String nombreIngrediente)
	{
		return  preparacion.contiene(nombreIngrediente);
		
		
		
		
	}
	
	public boolean contieneAlguna(List<String> comidas )
	{
		for (String comida : comidas)
		{
			
			if (this.contiene(comida))
				return true;
			
		}
		
		return false;
		
	}
	
	
	public boolean esADecuadaPara(Usuario usuario)
	{
		
		  return  usuario.getCondicionesPreexistentes().stream().map(condicion -> condicion.getComidasProhibidas())
		  					.filter(comidasProhibidas -> this.contieneAlguna((List<String>) comidasProhibidas)).count() > 0;
		  					
		
		
	}
	
	
	
	
	
	
}
