package queComemos;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestPreferenciaAlimenticia {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConstructor() {
		
		List<String> gustos = new ArrayList<>();
		List<String> disgustos = new ArrayList<>();
		
		gustos.add("carne");
		gustos.add("pollo");
		disgustos.add("espinaca");
		disgustos.add("zanahoria");
		
		
		PreferenciaAlimenticia pr = new PreferenciaAlimenticia(gustos,disgustos);
		
		assertFalse(pr.getComidasQueDisgusta().contains("carne"));
		
		
		
		
		
	}
	
	@Test
	public void testConstructor2() {
		
		List<String> gustos = new ArrayList<>();
		List<String> disgustos = new ArrayList<>();
		
		gustos.add("carne");
		gustos.add("pollo");
		disgustos.add("espinaca");
		disgustos.add("zanahoria");
		
		
		PreferenciaAlimenticia pr = new PreferenciaAlimenticia(gustos,disgustos);
		
		assertTrue(pr.getComidasQueGusta().contains("pollo"));
		
		
		
		
		
	}
	
	@Test
	public void testLeGustaAlguna() {
		
		List<String> gustos = new ArrayList<>();
		List<String> disgustos = new ArrayList<>();
		List<String> comidas = new ArrayList<>();
		
		gustos.add("carne");
		gustos.add("pollo");
		disgustos.add("espinaca");
		disgustos.add("zanahoria");
		
		comidas.add("pollo");
		comidas.add("pescado");
		comidas.add("tomate");
		
		
		PreferenciaAlimenticia pr = new PreferenciaAlimenticia(gustos,disgustos);
		
		assertTrue(pr.leGustaAlguna(comidas));
		
		
		
		
		
	}
	
	
	
	

}
