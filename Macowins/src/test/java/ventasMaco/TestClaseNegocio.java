package ventasMaco;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class TestClaseNegocio {

	private Negocio negocio;
	private Venta venta1;
	private Venta venta2;
	private Venta venta3;
	private LocalDate fechaActual;
	private LocalDate fechaNoActual;
	
	
	
	@Before
	public void setUp() throws Exception {
		
		 fechaActual =  LocalDate.now();
		fechaNoActual = LocalDate.of(2015, 04, 22);
		
		 negocio = new Negocio() ;
		
		Origen argentina = new Origen(1);
		Origen francia = new Origen(35);
		
		Saco saco = new Saco(300,argentina) ;
		Camisa camisa= new Camisa(200,francia);
		Pantalon pantalon = new Pantalon(200,argentina);
		
		 venta1 = new Venta(fechaActual,saco,2);
		 venta2 =  new Venta(fechaActual,camisa,3);
		 venta3= new Venta(fechaNoActual, pantalon,1);
		
		
		
	}

	@Test
	public void testHacerQueElNegocioRealizeUnaVenta() {
		
		negocio.vender(venta1);
		
		assertTrue(negocio.getVentas().size() == 1);
		
				
		
	}
	
	
	
	@Test
	public void testCalcularGananciasTotalesPorFecha(){
		
		negocio.vender(venta1);
		negocio.vender(venta2);
		negocio.vender(venta3);
		
		
		
		
		assertEquals( 200 ,negocio.calcularGananciasTotalesPorFecha(fechaNoActual),0.0001);
		
		
	}
	
	

}
