package queComemos;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestPreparacion {
	
	List<String> instrucciones;
	List<Ingrediente> ingredientes;
	List<Ingrediente> condimentos;

	@Before
	public void setUp() throws Exception {
		
		instrucciones = new ArrayList<String>();
		ingredientes = new ArrayList<Ingrediente>();
		condimentos = new ArrayList<Ingrediente>();
		
		instrucciones.add("Preparar");
		instrucciones.add("Revolver");
		instrucciones.add("Hornear");
		
		Ingrediente sal= new Ingrediente("Sal","grs",100);
		Ingrediente carne= new Ingrediente("Carne","kg",2);
		Ingrediente papas= new Ingrediente("papa","kg",3);
		Ingrediente mayonesa= new Ingrediente("Mayonesa","grs",100);
		
		ingredientes.add(carne);
		ingredientes.add(papas);
		condimentos.add(mayonesa);
		condimentos.add(sal);
		
			
		
		
		
	}

	@Test
	public void testContieneSal() {
		
		Preparacion preparacion = new Preparacion(ingredientes,condimentos,instrucciones);
		assertTrue(preparacion.contiene("Sal"));
		
		
	}
	
	
	@Test
	public void testNoContieneLimon() {
		
		Preparacion preparacion = new Preparacion(ingredientes,condimentos,instrucciones);
		assertFalse(preparacion.contiene("Limon"));
		
		
	}
	
	
	
	@Test
	public void testContieneCarne() {
		
		Preparacion preparacion = new Preparacion(ingredientes,condimentos,instrucciones);
		assertTrue(preparacion.contiene("Carne"));
		
		
	}
	
	
	
	@Test
	public void testNoContienePollo() {
		
		Preparacion preparacion = new Preparacion(ingredientes,condimentos,instrucciones);
		assertFalse(preparacion.contiene("Pollo"));
		
		
	}
	
	

}
