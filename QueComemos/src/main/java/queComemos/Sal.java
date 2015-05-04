package queComemos;

public class Sal  extends Condimento{
	
	private double cantidad;
	private String medida;
	
	
	public Sal(String nombre, double cantidad,String medida)
	{
		super(nombre,cantidad,medida);
		this.cantidad=cantidad;
		this.medida=medida;
		
	}
	
	public String esInadecuadoPara()
	{
		return "Hipertensos";
		
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
