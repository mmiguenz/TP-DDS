package queComemos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestCondimento {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConstructorCondimento() {
		Condimento condimento = new Condimento("Sal",100,"grs");
		
		assertTrue(condimento.getCantidad()==100 && condimento.getMedida()=="grs" && condimento.getNombre()=="Sal");
		
	}

}
