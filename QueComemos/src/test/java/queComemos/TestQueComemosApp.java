package queComemos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestQueComemosApp {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		QueComemosApp.inicializar();
		
		assertEquals(QueComemosApp.inadecuados.size(),0);
		
	}

}
