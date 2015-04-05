/* Esta es la clase abstracta prenda. 
 * las variables aca  y algunos otros lados tienen esa notacion que esta copada usar.
 * Es cuestion de gustos nomas, despeus me hinche las pelotas y la deje de usar. 
 * las 3 primeras letras de los identificadores hacen referencia a lo que contienen las variables, 
 * sirve bastante  cuando le pegas una ojeada al codigo .
 * Es cuestion de ponerse de acuerdo y usar esta notacion o no . */ 
public abstract  class Prenda
{
	private double dblPrecioBase;
	private Origen objOrigen;
	private static  double dblValorX;
			
	
	public static void setValorX(double valorx){
		
		dblValorX=valorx;
	}
	
	public static double getValorX(){
		
		return dblValorX;
	}
	
	public double getPrecioBase(){
		
		return this.dblPrecioBase;
	}
	
	public Origen getOrigen(){
		
		return this.objOrigen;
	}
	
	
	
	public void setPrecioBase(double precioBase){
		
		this.dblPrecioBase = precioBase ;
		
	}
	
	public void setOrigen(Origen origen){
		 
		this.objOrigen=origen;
	}
	

	
	public Prenda(double precioBase, Origen origen ) {
		
		setOrigen(origen);
		setPrecioBase(precioBase);
				
		
		
	}
}
