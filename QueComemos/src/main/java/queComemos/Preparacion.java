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
		return  (ingredientes.stream().anyMatch((ingrediente -> ingrediente.contiene(nombreIngrediente))))
				//.filter(ingrediente -> ingrediente.contiene(nombreIngrediente))).count() >0
				|| (condimentos.stream().anyMatch((condimento -> condimento.contiene(nombreIngrediente))));
				  //  .filter(condimento -> condimento.contiene(nombreIngrediente))).count() >0;
		
		
	}
	
	
	public Ingrediente buscaIngrediente(String ingre)
	{
		if (ingredientes.stream().anyMatch(ingrediente -> ingrediente.getNombre().equals(ingre)))
		{
			return this.buscarPorNombre(ingre, ingredientes);
			
		} else
			
		{
			return this.buscarPorNombre(ingre, condimentos);
			
		}
			
			
			
		
		
	}
	
	public boolean validar()
	{
		return ! ingredientes.isEmpty();
		
		
	}
	
	



private Ingrediente buscarPorNombre(String nombre,Set<Ingrediente> ingredientes)
{
	for (Ingrediente ingrediente : ingredientes)
	{
		
		if (ingrediente.getNombre().equals(nombre))
		{
			
			return ingrediente;
			
		}
		
	}
	
	return new Ingrediente("","",.0);
	
}

}
