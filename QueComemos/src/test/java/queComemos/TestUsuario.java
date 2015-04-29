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

}
