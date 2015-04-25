package ventasMaco;

public class Saco extends Prenda {
	
	private double cantBotones;

	public Saco(double precioBase,Origen origen,double cantBotones){
		
		super(precioBase,origen);
		this.cantBotones = cantBotones;
	}
	
	public double precioOriginal()
	
	{
		return super.precioOriginal() + (cantBotones * 10);
		
	}


}


