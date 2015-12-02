package builderReceta;

import java.util.ArrayList;
import java.util.List;

import receta.Condimento;
import receta.Ingrediente;
import receta.Preparacion;
import receta.Receta;

public  abstract class RecetaBuilder {
	
	private Receta nuevaReceta ; 
	
	
	public RecetaBuilder()
	{
		nuevaReceta = Receta.crearReceta();
		
		
		
	}
	
	
	public void ingredienteAgregar(String nombre, String medida, Double cantidad)
	{
		
		nuevaReceta.getPreparacion().getIngredientes().add(new Ingrediente(nombre,medida,cantidad));
		
		
	}
	
	public void condimentoAgregar(String nombre, String medida, Double cantidad)
	{
		
		nuevaReceta.getPreparacion().getCondimentos().add(new Condimento(nombre,medida,cantidad));
		
		
	}
	
	
	public void instruccionesAgregar(String instruccion)
	{
		nuevaReceta.getPreparacion().getExplicacion().add(instruccion);
			
	}
	
	public void nombre(String nombre )
	{
		nuevaReceta.setNombre(nombre);
		
	}
	
	public void calorias(Double calorias)
	{
		nuevaReceta.setCalorias(calorias);
		
	}
	
	public void temporada(String temporada)
	{
		nuevaReceta.setTemporada(temporada);
		
	}
	
	
	public void dificultad(String dificultad)
	{
		nuevaReceta.setDificultad(dificultad);
		
	}
	
	
	public void subRecetaAgregar(Receta unaSubreceta)
	{
		nuevaReceta.getSubRecetas().add(unaSubreceta);
		
	}
	
	
	public Receta crearReceta()
	{
		nuevaReceta.calcularInadecuados();
		
		List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		List<Condimento> condimentos = new ArrayList<Condimento>();
		List<String> explicacion = new ArrayList<String>();
		
		ingredientes.addAll(nuevaReceta.getPreparacion().getIngredientes());		
		condimentos.addAll(nuevaReceta.getPreparacion().getCondimentos());
		explicacion.addAll(nuevaReceta.getPreparacion().getExplicacion());
		
		Preparacion preparacion = new Preparacion(null,ingredientes,condimentos,explicacion);
				
		
		return 
				new Receta(null, nuevaReceta.getNombre(), nuevaReceta.getCalorias(), preparacion, nuevaReceta.getDificultad(), nuevaReceta.getTemporada(), nuevaReceta.getSubRecetas(), nuevaReceta.getInadecuados());
		
	}
	
}
