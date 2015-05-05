package queComemos;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestPreparacion {
	
	private List<Ingrediente> ingredientes;
	private List<Condimento> condimentos;
	private List<String> explicacion;
	

	@Before
	public void setUp() throws Exception {
		
		Ingrediente ingrediente = new Ingrediente("pollo","kg",1,null);
		ingredientes = new ArrayList<Ingrediente>();
		ingredientes.add(ingrediente);
		
		Condimento condimento = new Condimento("Sal",100,"grs");
		condimentos= new ArrayList<Condimento>();
		condimentos.add(condimento);
				
		explicacion = new ArrayList<String>();
		explicacion.add("desmenuzar");
		explicacion.add("salar");
		explicacion.add("cocinar en horno 1 hora");
		
		
		
		
		
		
	}

	@Test
	public void testConstructorPreparacion() {
		
		Preparacion preparacion = new Preparacion(ingredientes,condimentos,explicacion);
		assertTrue(preparacion.getIngredientes().size()>0 && preparacion.getCondimentos().size()>0 && preparacion.getExplicacion().size()>0);
		
			
		
			
	
	}
	
	
	@Test
	public void testContienePolloEnLaPreparacion()
	
	{
		Preparacion preparacion = new Preparacion(ingredientes,condimentos,explicacion);
		assertTrue(preparacion.contiene("pollo"));
		
		
		
		
	}
	
	@Test
	public void testNoContieneCarneEnLaPreparacion()
	{
		Preparacion preparacion = new Preparacion(ingredientes,condimentos,explicacion);
		assertFalse(preparacion.contiene("carne"));
		
		
	}

}
