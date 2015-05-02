package queComemos;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestUsuario {

	private Usuario usuario;

	@Test
	public void testIMCPesoyEstaturaPositiva() {
		
		usuario = new Usuario(83.0,1.88);
		assertEquals(23.48, usuario.indiceMasaCorporal(), 0.1);
				
	}

	@Test
	public void testIMCPPesoYEstaturaF()
	{
		usuario = new Usuario(73.4, 1.74);
		assertEquals(24.24, usuario.indiceMasaCorporal(), 0.01);
	}



	@Test
	public void testIMCPeso66Estatura172()
	{
		usuario = new Usuario(66.0,1.72);
		assertEquals(22.30,usuario.indiceMasaCorporal(),0.1);
		
		
		
	}
	
	
	@Test
	public void testIMCPesoYEstaturaAB()
	{
		usuario = new Usuario(64.0,1.69);
		assertEquals(22.40, usuario.indiceMasaCorporal(),0.1);
	}
	
}