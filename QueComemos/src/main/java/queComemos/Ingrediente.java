package queComemos;

public   class Ingrediente  {

		private String nombre ;
		private String medida;
		private double cantidad;
	
	public Ingrediente(String nombre,String medida,double cantidad)
	{
		this.nombre=nombre;
		this.medida=medida;
		this.cantidad=cantidad;
		
		
		
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







	public boolean contiene(String nombreIngrediente)
	{
	
				return nombre ==nombreIngrediente;
	
	
		
		
	}
	
	
	

}

