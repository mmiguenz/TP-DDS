package VentasMaco;

public abstract  class Prenda {
	
	private double precioBase;
	private Origen origen;
	private static double valorX;
	
	
	
	public Prenda (double precioBase, Origen Origen){
		
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
		return valorX;
	}


	public static void setValorX(double valorX) {
		Prenda.valorX = valorX;
	}
	
	
	
	public double precioFinal(){
		
		return (valorX + precioBase ) * origen.getTasaImportacion();
		
	}

}
