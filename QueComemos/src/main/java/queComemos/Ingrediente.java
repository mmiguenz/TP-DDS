package queComemos;

public   class Ingrediente {

	private String nombre;
	private String medida;
	private double cantidad;
	private Receta subReceta;

	
	
	public Ingrediente(String nombre,String medida,double cantidad, Receta receta)
	{
		this.nombre=nombre;
		this.medida=medida;
		this.cantidad=cantidad;
		this.subReceta=receta;
		
		
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Receta getTipoIngrediente() {
		return subReceta;
	}

	public void setTipoIngrediente(Receta tipoIngrediente) {
		this.subReceta = tipoIngrediente;
	}
	
	
	public boolean contiene(String nombreIngrediente)
	{
		if (subReceta ==null)
				return nombre ==nombreIngrediente;
		else
			return subReceta.contiene(nombreIngrediente);
		
		
	}
	
	
	public String inadecuadoPara()
	{
		
		return "Nadie";
	}

}

