import java.util.Collection;
import java.util.ArrayList;
import java.util.Date;


// clase negocio, esta es la que practicamente va a resolver todo el ejercicio


public class Negocio
{
	private Collection<Venta> ventas; 
	
	// Constructur, cuando lo instancio, ya instancio la coleccion
	public Negocio (){
		ventas= new ArrayList<Venta>();
		
		
	}
	
	
		
	public void agregarVenta(Venta venta){
		
		ventas.add(venta);
				
		
	}
	
	
	 /* el metodo vender esta definido dos veces (sobrecargado) a prosito para testiar
	  que anden bien los filtros del reporte. el primer metodo vender asigna la fecha del momento de la venta.
	  El otro , te permite setiarsela por parametro. Asi depues se pueden probar bien el reporte de ventas.
	*/
	public void vender(Prenda articulo, int cantidad){
		Venta v = new Venta(articulo,cantidad,new Date());
		agregarVenta(v); 
				
	}
	
	public void vender(Prenda articulo, int cantidad, Date f){
		
		
		Venta v = new Venta(articulo, cantidad,  f );
		agregarVenta(v);
		
	}
	
	
	/* este metodo solo lo cree para ir testiando que ande bien.
	 * como todavia no habia programado la parte del reporte queria ir viendo como se iva cargando la coleccion de ventas.
	 * muestro solo las fechas, porque no le defini atributo tipo de prenda a los articulos . Lo unico mostrable eran las fechas de
	 * las ventas . */
	public void mostrarVentas(){
		
		int i=0 ;
		for(Venta venta:ventas){
			
			System.out.println(venta);
			
			ventas.remove(i);
			i++;
			
		}
		System.out.println("\nCantidad de Ventas: "+i+" ventas");
		
	}
	
	
	

	/* Esto lo hice siguiendo la logica que plantea el ejercicio.
	 * se puede discutir si esta calculando bien o mal . O  como realmente lo deberia hacer.
	 * Pero lo bueno que es facil de modificar y que se vea el cambio  */
	public double calcularPrecioFinal(Prenda prenda){
			
				
		return (prenda.getPrecioBase() + Prenda.getValorX() ) * prenda.getOrigen().getTasaImp();
		
		
		
	}
	
	 /* Aca hago el verdadero reporte, el que pide el ejercicio. */
	public Reporte registroDeVentas(Date f){
	
		
		Collection<Venta> ventasR = filtrarPorFecha(ventas,f );
		double acumVentas=0;
		
		
		for (Venta venta:ventasR){
			acumVentas += totalVendido(venta);			
			
		}
		
		Reporte reporte = new Reporte(acumVentas,ventasR); 
	
		return reporte;		
	
	
	
		
		
}	
	
	
	private double  totalVendido(Venta venta ){
		
		return venta.getCantidad()  * calcularPrecioFinal(venta.getArt());
		
	}
	
	
	 
	private Collection<Venta> filtrarPorFecha(Collection<Venta> ventas, Date f){
		
		 Collection<Venta> ventasF = new ArrayList<Venta>();
		
		for (Venta venta:ventas){
	
			if (  comparaFecha(venta.getFecha(),f)){
				
				ventasF.add(venta);
				
			}			
			
		}
		
		return ventasF;

	}
		
		
	
	@SuppressWarnings("deprecation")
	private boolean comparaFecha(Date f1, Date f2 ){
		
		
		return (f1.getYear() ==f2.getYear())&& (f1.getMonth()==f2.getMonth()) && (f1.getDay() == f2.getDay());  
		
	}
		

				

	
}
	




