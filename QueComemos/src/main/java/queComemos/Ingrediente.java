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


	
	
	
	public String getMedida() {
		return medida;
	}


	public void setMedida(String medida) {
		this.medida = medida;
	}


	public double getCantidad() {
		return cantidad;
	}


	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}


	public Receta getSubReceta() {
		return subReceta;
	}


	public void setSubReceta(Receta subReceta) {
		this.subReceta = subReceta;
	}


	public boolean contiene(String nombreIngrediente)
	{
		if (subReceta ==null)
				return nombre ==nombreIngrediente;
		else
			return subReceta.contiene(nombreIngrediente);
		
		
	}
	
	
	

}

