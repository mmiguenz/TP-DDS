package queComemos;

import java.util.Set;

public class Preparacion {

	private Set<Ingrediente> ingredientes;
	private Set<Ingrediente> condimentos;
	private Set<String> explicacion;
	
	
	
	public Preparacion(Set<Ingrediente> ingredientes, Set<Ingrediente> condimentos,Set<String> explicacion)
	{
		
		this.ingredientes=ingredientes;
		this.explicacion=explicacion;
		this.condimentos =condimentos;
			
		
		
	}
	
	
	



	public Set<Ingrediente> getIngredientes() {
		return ingredientes;
	}



	public void setIngredientes(Set<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}



	public Set<Ingrediente> getCondimentos() {
		return condimentos;
	}



	public void setCondimentos(Set<Ingrediente> condimentos) {
		this.condimentos = condimentos;
	}



	public Set<String> getExplicacion() {
		return explicacion;
	}



	public void setExplicacion(Set<String> explicacion) {
		this.explicacion = explicacion;
	}
	

	public boolean contiene(String nombreIngrediente)
	{
		return  (ingredientes.stream()
				.filter(ingrediente -> ingrediente.contiene(nombreIngrediente))).count() >0
				|| (condimentos.stream()
				    .filter(condimento -> condimento.contiene(nombreIngrediente))).count() >0;
		
		
	}
	
	public boolean validar()
	{
		return ingredientes.size() >0;
		
		
	}
	
	
}