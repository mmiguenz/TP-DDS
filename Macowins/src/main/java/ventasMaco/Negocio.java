package VentasMaco;

import java.util.Collection;
import java.util.ArrayList;
import java.time.LocalDate;


public class Negocio {
	
	private Collection<Venta> ventas;
	

	public Negocio(){
		
		ventas = new ArrayList<Venta>();
		
		
	}
	

	
	public Collection<Venta> getVentas() {
		return ventas;
	}



	public void setVentas(Collection<Venta> ventas) {
		this.ventas = ventas;
	}



	public void vender(Venta venta){
		
		ventas.add(venta);
	}
	
	
	private double totalPorVenta(Venta venta){
		
		return  venta.total();
		
		
	}
	
	
	public  double calcularGananciasTotales(LocalDate fecha){
		
		double acum=0;
		
		for (Venta venta: ventas){
			
			if (venta.getFecha().equals(fecha)){
				
				acum+= totalPorVenta(venta);
				
			}
			
		}
		
			
		return acum;
		
		
		
	}
	
}
