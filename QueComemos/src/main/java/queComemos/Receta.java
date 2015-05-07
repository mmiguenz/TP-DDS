package queComemos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;


public class Receta {
	
	private String nombre;
	private double calorias;
	private Preparacion preparacion ;
	private String dificultad;
	private String temporada;
	private Receta subReceta;
	private Set<String> inadecuados;
	
	
	

	public Receta(String nombre,double calorias, Preparacion preparacion,String dificultad, String temporada,Receta subReceta)
	{
		try{
			
				
								
			this.nombre=nombre;
			this.calorias= calorias;
			this.preparacion=preparacion;
			this.dificultad=dificultad;
			this.temporada=temporada;
			this.subReceta = subReceta;
			this.inadecuados = calcularInadecuados(subReceta,preparacion);

				
			
			
			
		}
		
		catch (Exception ex){
			
			
		}
		
			
		
	}
	

	public boolean contiene(String nombreIngrediente)
	{
		if (subReceta == null)
			return   preparacion.contiene(nombreIngrediente);
		else
			return   preparacion.contiene(nombreIngrediente) || subReceta.contiene(nombreIngrediente);
		
		
		
		
	}
	
	public boolean contieneAlguna(List<String> comidas )
	{
		
		
		return comidas.stream().filter(comida -> this.contiene(comida)).count() >0;
		
		
		
		/*for (String comida : comidas)
		{
			
			if (this.contiene(comida))
				return true;
			
		}
		
		return false*/
		
	}
	
	/*
	public boolean esADecuadaPara(Usuario usuario)
	{
		
		  return  usuario.getCondicionesPreexistentes().stream().map(condicion -> condicion.getComidasProhibidas())
		  					.filter(comidasProhibidas -> this.contieneAlguna((List<String>) comidasProhibidas)).count() > 0;
		  					
		
		
	}*/
	
	
	
	private Set<String> calcularInadecuados(Receta subReceta,
			Preparacion preparacion) {
	
		Set<String> inadecuados = (Set<String>) new ArrayList<String>();
		
				
		if (!(Hipertenso.esRecomendable(subReceta,preparacion)))
			inadecuados.add("Hipertensos");
		
		
		if (!(Diabetico.esRecomendable(calorias,subReceta, preparacion)))
			inadecuados.add("Diabetico");
			
		if (!(Celiaco.esRecomendable(calorias,subReceta, preparacion)))
			inadecuados.add("Celiaco");
		
		if  (!(Vegano.esRecomendable(subReceta,preparacion)))
			inadecuados.add("Vegano");
			
		
		return inadecuados;
	}

	
	
}
