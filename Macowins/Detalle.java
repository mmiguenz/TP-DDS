/* Esta clase, no juega de nada . 
 * El lo ideal seria definir al objecto ventas, que contenga una coleccion de detalles en donde cada detalle sea lo que 
 * represento en este codigo. Pero como se me empezo a complicar manejar las colecciones, defini la clase ventas de una forma mas sencilla.
 * y deje esta a medio armar por las dudas . Lo que hace implementar esta clase es que en cada venta, pueda vender mas de una
 * prenda.  */


public class Detalle
{
	private Prenda articulo;
	private int cantidad ;

	public void setArticulo(Prenda art){
		
		this.articulo=art;
		
	}
	
	
	public void setCantidad(int cant){
		
		this.cantidad =cant ;
		
	}
	
	
	
	public Prenda getArticulo(){
		
		return this.articulo ;
		
	}
	
	public int getCantidad(){
		
		return this.cantidad;
		
	}
	
	
	public Detalle(Prenda articulo, int cantidad){
		
		setArticulo(articulo);
		setCantidad(cantidad);
		
		
	}

}

