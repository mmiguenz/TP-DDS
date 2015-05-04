package queComemos;

import java.util.ArrayList;
import java.util.List;


public class Receta {
	
	private String nombre;
	private InfNutricional informacionNutricional;
	private Preparacion preparacion ;
	private String dificultad;
	private String temporada;
	
	

	public Receta(String nombre,InfNutricional informacionNutricional, Preparacion preparacion,String dificultad)
	{
		try{
			if (! preparacion.isOk())
			
				throw new RuntimeException("Debe ingresar al menos un Ingrediente");
			
				
			if (! informacionNutricional.isOk())
				
				throw new RuntimeException("Las calorias deben estar en un rango de 10-5000");
				
								
			this.nombre=nombre;
			this.preparacion=preparacion;
			this.informacionNutricional=informacionNutricional;
			this.dificultad=dificultad;

				
			
			
			
		}
		
		catch (Exception ex){
			
			
		}
		
			
		
	}
	
	public boolean contiene(String nombreIngrediente)
	{
		return  preparacion.contiene(nombreIngrediente);
		
		
		
		
	}
	
	
}
