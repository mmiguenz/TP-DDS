package queComemos;

public  class Condimento {
	
	private String nombre ;
	private double cantidad;
	private String medida;



	
	
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





