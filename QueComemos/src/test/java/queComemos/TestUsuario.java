package queComemos;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class TestUsuario {

	private Usuario usuario;

	@Test
	public void testIMCPesoyEstaturaPositiva() {
		

		ComplexionFisica datosFisicos = new ComplexionFisica(83.0,1.88);
		DatosBasicosUsuario datosBasicos = new DatosBasicosUsuario("juan","Masculino", LocalDate.parse("2005-04-03"));
		
		usuario = new Usuario(datosBasicos,datosFisicos,null,null);
		assertEquals(23.48, usuario.indiceMasaCorporal(), 0.1);
				
	}

	@Test
	public void testIMCPPesoYEstaturaF()
	{
		
		ComplexionFisica datosFisicos = new ComplexionFisica(73.4, 1.74);
		DatosBasicosUsuario datosBasicos = new DatosBasicosUsuario("jose","Masculino", LocalDate.parse("2008-12-03"));
		
		usuario = new Usuario(datosBasicos,datosFisicos,null,null);
		assertEquals(24.24, usuario.indiceMasaCorporal(), 0.1);
				
	
	}



	@Test
	public void testIMCPeso66Estatura172()
	{
		
		ComplexionFisica datosFisicos = new ComplexionFisica(66.0,1.72);
		DatosBasicosUsuario datosBasicos = new DatosBasicosUsuario("Hernan","Masculino", LocalDate.parse("2008-08-03"));
		
		usuario = new Usuario(datosBasicos,datosFisicos,null,null);
		assertEquals(22.30, usuario.indiceMasaCorporal(), 0.1);
		
	
		
		
		
	}
	
	
	@Test
	public void testIMCPesoYEstaturaAB()
	{
		
		ComplexionFisica datosFisicos = new ComplexionFisica(64.0,1.69);
		DatosBasicosUsuario datosBasicos = new DatosBasicosUsuario("joaquin","Masculino", LocalDate.parse("1997-08-03"));
		
		usuario = new Usuario(datosBasicos,datosFisicos,null,null);
		assertEquals(22.40, usuario.indiceMasaCorporal(), 0.1);
		
	}
	
}