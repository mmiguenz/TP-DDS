package ventasMaco;

public class Pantalon extends Prenda {
	
	private double cm2TelaUsada;
	
	public Pantalon(double precioBase,Origen origen,double cm2TelaUsada){
		
		super(precioBase,origen);
		this.cm2TelaUsada =cm2TelaUsada; 
		
	}
	
	
	public double precioOriginal(){
				
		return  super.precioOriginal() + this.cm2TelaUsada * 1  ;
	}

}
