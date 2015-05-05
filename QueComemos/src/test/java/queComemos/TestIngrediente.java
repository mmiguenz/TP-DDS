package queComemos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestIngrediente {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConstructorIngrediente() {
		
		Ingrediente ingrediente = new Ingrediente("pollo","kg",1,null);
		
		assertTrue(ingrediente.getNombre()=="pollo" && ingrediente.getCantidad() ==1 && ingrediente.getMedida()=="kg");
		
		
		
	}
	
	
	@Test
	public void TestMetodoContienePolloConRecetaNull()
	{
		Ingrediente ingrediente = new Ingrediente("pollo","kg",1,null);
		
		assertTrue(ingrediente.contiene("pollo"));
		
		
		
	}
	
	@Test
	public void TestMetodoNoContienePolloConRecetaNull()
	{
		Ingrediente ingrediente = new Ingrediente("carne","kg",1,null);
		
		assertFalse(ingrediente.contiene("pollo"));
		
		
		
	}
	
	
	


}
