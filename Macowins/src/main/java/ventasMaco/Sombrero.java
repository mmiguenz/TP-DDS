package ventasMaco;

public class Sombrero extends Prenda {
	
	private double coefMetrosexualidad;
	
	public Sombrero(double precioBase,Origen origen,double coefMetrosexualidad)
	{
		super(precioBase,origen);
		this.coefMetrosexualidad=coefMetrosexualidad;
		
		
	}
	
	
	public double precioOriginal()
	{
		return super.precioOriginal() + (this.coefMetrosexualidad * 1);
		
		
	}

}
