package builderReceta;

import receta.Ingrediente;
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
		
		nuevaReceta.getPreparacion().getCondimentos().add(new Ingrediente(nombre,medida,cantidad));
		
		
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
		return 
				new Receta(nuevaReceta.getNombre(), nuevaReceta.getCalorias(), nuevaReceta.getPreparacion(), nuevaReceta.getDificultad(), nuevaReceta.getTemporada(), nuevaReceta.getSubRecetas(), nuevaReceta.getInadecuados());
		
	}
	
}
