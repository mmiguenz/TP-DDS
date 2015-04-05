import java.util.Date;

/*  Como comente un poco en la clase detalle, implemente la clase ventas de manera de que cada venta 
 * esta compuesta por una unica prenda y la cantidad 
 *  */ 


public class Venta
{
	private Date fecha; 
	private Prenda articulo;
	private int cantidad;
	
	
	//getters
	public Date getFecha(){
		
		return this.fecha;
		
	}
	
	public Prenda getArt(){
		
		return this.articulo; 
	}
	
	public int getCantidad(){
		
		return this.cantidad;
	}
	
	//Setters
	
	public void setArt(Prenda art){
		
		this.articulo=art;
	}
	
	public void setCant(int cant){
		
		this.cantidad=cant;		
	}
	
	public void setFecha(Date f){
		
		this.fecha=f;
	}
	
	//Constructor
	
	public  Venta(Prenda art, int cant, Date f ){
		
		setArt(art);
		setCant(cant);
		setFecha(f);
		
	}
	
	public String toString (){
		
		
		
		return this.fecha.toString();
		
	
		
	}
	
	
	
	
}
