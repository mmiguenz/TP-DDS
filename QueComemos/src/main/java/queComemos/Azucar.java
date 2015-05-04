package queComemos;

public class Azucar extends Condimento {
	

	
	public Azucar(double cantidad,String medida)
	{
		super("Azucar",cantidad,medida);
		
		
		
	}
	
	public String  esInadecuadoPara()
	{
		return cantidad>100 ?"Diabeticos":"Nadie";
		
		
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
