package ventasMaco;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestMarca {

		private Prenda prenda;
		private Origen argentina = new Origen(1);
	@Before
	public void setUp() throws Exception {
		
		prenda = new Saco(300,argentina,10);
	
		
	}

	@Test
	public void testCoefMarcSarcany() {
		
		Sarkany sarkany = new Sarkany();
		assertEquals(1.65, sarkany.coefDeLaMarca(prenda), 0.0);
		
		
		
	}

}
