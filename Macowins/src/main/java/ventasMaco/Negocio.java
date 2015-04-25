package ventasMaco;

import java.util.*;
import java.time.LocalDate;


public class Negocio {
	
	private List<Venta> ventas;
	

	public Negocio(){
		
		ventas = new ArrayList<Venta>();
		
		
	}
	

	
	public Collection<Venta> getVentas() {
		return ventas;
	}



	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}



	public void vender(Venta venta){
		
		ventas.add(venta);
	}
	
	

	
	
	public  double calcularGananciasTotalesPorFecha(LocalDate fecha){

		return  ventas.stream()
				.filter( venta -> venta.getFecha().equals(fecha))
				.map(Venta::total)
				.reduce(0.0,(a,b) -> a + b ) ;
		
	
		
	}
	
	
	
	
		
	
	
}
