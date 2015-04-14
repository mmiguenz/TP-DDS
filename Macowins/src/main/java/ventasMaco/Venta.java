package VentasMaco;

import java.time.LocalDate;

public class Venta {
	
	private LocalDate  fecha ;
	private Prenda prenda;
	private int cantidad ;
	
	
	public Venta(LocalDate fecha,Prenda prenda, int cantidad){
		
		setFecha(fecha);
		setPrenda(prenda);
		setCantidad(cantidad);
		
	}


	public LocalDate getFecha() {
		return fecha;
	}


	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


	public Prenda getPrenda() {
		return prenda;
	}


	public void setPrenda(Prenda prenda) {
		this.prenda = prenda;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	public double total(){
		
		return cantidad * prenda.precioFinal();
	}
	
	

}
