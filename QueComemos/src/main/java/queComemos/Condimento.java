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




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public double getCantidad() {
		return cantidad;
	}




	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}




	public String getMedida() {
		return medida;
	}




	public void setMedida(String medida) {
		this.medida = medida;
	}



}





