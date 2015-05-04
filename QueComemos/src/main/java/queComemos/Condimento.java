package queComemos;

public  class Condimento {
	protected String nombre ;
	protected double cantidad;
	protected String medida;



	
	
	public Condimento(String nombre, double cantidad, String medida)
	{
		this.nombre=nombre;
		this.cantidad=cantidad;
		this.medida=medida;
		
		
	}

	public String inadecuadoPara()
	{
		return "Nadie";
		
		
		
		
	}


}





