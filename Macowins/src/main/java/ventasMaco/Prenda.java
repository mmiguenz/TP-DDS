package ventasMaco;

public abstract  class Prenda {
	
	private double precioBase;
	private Origen origen;
	private static double  ValorX=0;
	
	
	
	public Prenda (double precioBase, Origen origen){
		
		setOrigen(origen);
		setPrecioBase(precioBase);
		
	}


	public double getPrecioBase() {
		return precioBase;
	}


	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}


	public Origen getOrigen() {
		return origen;
	}


	public void setOrigen(Origen origen) {
		this.origen = origen;
	}


	public static double getValorX() {
		return ValorX;
	}


	public static void setValorX(double valorX) {
		Prenda.ValorX = valorX;
	}
	
	
	
	public double precioFinal(){
		
		return (ValorX + precioBase ) * origen.getTasaImportacion();
		
	}

}
