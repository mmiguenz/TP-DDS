package ventasMaco;

public class Sarkany extends Marca {
	
	public double coefDeLaMarca(Prenda prenda)
	{
		return prenda.getPrecioBase() >500?  prenda.precioOriginal() * 1.35 : prenda.precioOriginal() * 1.10;
		
		
		
		
	}

}
