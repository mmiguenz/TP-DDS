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
	
	
	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}



	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}



	public List<Condimento> getCondimentos() {
		return condimentos;
	}



	public void setCondimentos(List<Condimento> condimentos) {
		this.condimentos = condimentos;
	}



	public List<String> getExplicacion() {
		return explicacion;
	}



	public void setExplicacion(List<String> explicacion) {
		this.explicacion = explicacion;
	}



	public boolean isOk()
	{
	
		return ingredientes.size() >0;
	}
}
