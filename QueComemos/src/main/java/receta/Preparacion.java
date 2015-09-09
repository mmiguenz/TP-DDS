package receta;

import java.util.List;

public class Preparacion {

	private List<Ingrediente> ingredientes;
	private List<Ingrediente> condimentos;
	private List<String> explicacion;
	
	
	
	public Preparacion(List<Ingrediente> ingredientes, List<Ingrediente> condimentos,List<String> explicacion)
	{
		
		this.ingredientes=ingredientes;
		this.explicacion=explicacion;
		this.condimentos =condimentos;
			
		
		
	}
	
	
	



	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}



	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}



	public List<Ingrediente> getCondimentos() {
		return condimentos;
	}



	public void setCondimentos(List<Ingrediente> condimentos) {
		this.condimentos = condimentos;
	}



	public List<String> getExplicacion() {
		return explicacion;
	}



	public void setExplicacion(List<String> explicacion) {
		this.explicacion = explicacion;
	}
	

	public boolean contiene(String nombreIngrediente)
	{
		return  (ingredientes.stream().anyMatch((ingrediente -> ingrediente.contiene(nombreIngrediente))))
				//.filter(ingrediente -> ingrediente.contiene(nombreIngrediente))).count() >0
				|| (condimentos.stream().anyMatch((condimento -> condimento.contiene(nombreIngrediente))));
				  //  .filter(condimento -> condimento.contiene(nombreIngrediente))).count() >0;
		
		
	}
	
	
	public boolean leGusta(List<String> cosasQueLeGustan){
		
		return (cosasQueLeGustan.stream().anyMatch(cosas->this.contiene(cosas)));
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
	
	



private Ingrediente buscarPorNombre(String nombre,List<Ingrediente> ingredientes)
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
