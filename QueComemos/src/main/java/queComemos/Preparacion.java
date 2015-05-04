package queComemos;

import java.util.List;

public class Preparacion {

	private List<Ingrediente> ingredientes;
	private List<Condimento> condimentos;
	private List<String> explicacion;
	
	
	
	public Preparacion(List<Ingrediente> ingredientes,List<Condimento> condimentos,List<String> explicacion)
	{
		
		this.ingredientes=ingredientes;
		this.condimentos=condimentos;
		this.explicacion=explicacion;
		
		
		
		
	}
	
	
	
	public boolean contiene(String nombreIngrediente)
	{
		return  (ingredientes.stream()
				.filter(ingrediente -> ingrediente.contiene(nombreIngrediente))).count() >0;
		
		
	}
	
	
	public boolean isOk()
	{
	
		return ingredientes.size() >0;
	}
}
