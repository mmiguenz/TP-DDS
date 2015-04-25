package ventasMaco;

public class Zapato extends Prenda {
	
	private double talle ;
	
	public Zapato(double precioBase,Origen origen, double talle)	{
		
		super(precioBase,origen);
		this.talle = talle ;
	}
	
	
	public double precioOriginal() 
	{
		return  super.precioOriginal() + (5 * this.talle);
		
		
		
	}
	
	

}
