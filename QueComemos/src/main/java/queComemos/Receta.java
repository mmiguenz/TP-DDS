package queComemos;

import java.util.ArrayList;
import java.util.List;


public class Receta {
	
	private String nombre;
	private List<Ingrediente> ingredientes;
	private List<Condimento> condimentos;
	private List<String> inadecuadoPara;
	private List<String> explicacion;
	private String dificultad;
	private String temporada;
	private Double totalCalorias;
	

	public Receta(String nombre,List<Ingrediente> ingredientes,List<Condimento> condimentos, List<String> explicacion,String dificultad,Double totalCalorias)
	{
		try{
			if (ingredientes.stream().count()==0)
			
				throw new RuntimeException("Debe ingresar al menos un Ingrediente");
			
				
			if (totalCalorias>10 && totalCalorias <5000)
				
				throw new RuntimeException("Las calorias deben estar en un rango de 10-5000");
				
								
			this.nombre=nombre;
			this.ingredientes=ingredientes;
			this.condimentos=condimentos;
			this.explicacion=explicacion;
			this.dificultad=dificultad;
			this.totalCalorias=totalCalorias;
			this.inadecuadoPara=calcularRecomendaciones(ingredientes,condimentos);
				
			
			
			
		}
		
		catch (Exception ex){
			
			
		}
		
			
		
	}
	
	public boolean contiene(String nombreIngrediente)
	{
		return (ingredientes.stream()
							.filter(ingrediente -> ingrediente.contiene(nombreIngrediente))).count() >0;
		
	}
	
	
	@SuppressWarnings("unchecked")
	private List<String> calcularRecomendaciones(List<Ingrediente> ingredientes,List<Condimento> condimentos)
	{
		List<String> recomendaciones= new ArrayList<String>();
		
		recomendaciones= (List<String>) ingredientes.stream().map(ingrediente->ingrediente.inadecuadoPara());
		recomendaciones.addAll((List<String>) condimentos.stream().map(condimento->condimento.inadecuadoPara()));
		
		return recomendaciones;
		
		
		
	}
	
	
}
