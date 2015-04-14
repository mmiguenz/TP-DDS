package VentasMaco;

import static org.junit.Assert.*;
import java.time.*;

import org.junit.Test;

public class testVender {

	@Test
	public void test() {
		
		LocalDate fecha =  LocalDate.now();
		Negocio negocio = new Negocio() ;
		Origen argentina = new Origen(1);
		Saco saco = new Saco(300,argentina) ;
		Venta venta = new Venta(fecha,saco,2);
		
		
		
		negocio.vender(venta);
		assertTrue(negocio.getVentas().size() > 0 );
		
		
		
		
		
		
	}

}
