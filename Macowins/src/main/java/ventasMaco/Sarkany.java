package ventasMaco;

public class Sarkany extends Marca {
	
	public double coefDeLaMarca(Prenda prenda)
	{
		return prenda.getPrecioBase() >500?  1.35 : 1.10;
		
		
		
		
	}

}
