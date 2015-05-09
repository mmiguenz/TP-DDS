package queComemos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class TestUsuario {


	private Usuario usuario;
	private PreferenciaAlimenticia  preferenciaAlimenticiaSaludable;
	private PreferenciaAlimenticia  preferenciaAlimenticiaNoSaludable;
	private Set<String> comidasProhibidas;
	
	
	
	@Before
	
	public void setUp() throws Exception {
				
		
		Set<String> comidasQueGustaUsrSaludable = new HashSet<String>();
		Set<String> comidasQueGustaUsrNoSaludable = new HashSet<String>();
		Set<String> comidasQueDisgustaUsr = new HashSet<String>();
		
		comidasQueGustaUsrSaludable.add("Fruta");
		comidasQueGustaUsrSaludable.add("Carne");
		comidasQueGustaUsrSaludable.add("Pasta");
		
		comidasQueGustaUsrNoSaludable.add("Fritos");
		comidasQueGustaUsrNoSaludable.add("Snacks");
		
		comidasQueDisgustaUsr.add("Verduras");
		
		preferenciaAlimenticiaSaludable = new PreferenciaAlimenticia(comidasQueGustaUsrSaludable,comidasQueDisgustaUsr);
		preferenciaAlimenticiaNoSaludable = new PreferenciaAlimenticia(comidasQueGustaUsrNoSaludable,comidasQueDisgustaUsr);
		
		comidasProhibidas = new HashSet<String>();
		comidasProhibidas.add("Pan");
		
	}

	@Test
	public void testIMCPesoyEstaturaPositiva() {
		
		
		

		
		
		usuario = new Usuario("Matias","Masculino",LocalDate.parse("1994-08-05"),70.0,1.75,"Leve",null,null,null);
		assertEquals(22.85, usuario.indiceMasaCorporal(), 0.1);
				
	}

	@Test
	public void testIMCPPesoYEstaturaF()
	{
		
		
		
		usuario = new Usuario("Juan","Masculino",LocalDate.parse("1998-08-05"),80.0,1.75,"Leve",null,null,null);
		assertEquals(26.12, usuario.indiceMasaCorporal(), 0.1);
				
	
	}



	@Test
	public void testIMCPeso66Estatura172()
	{
		
		
		
		usuario = new Usuario("Juan","Masculino",LocalDate.parse("1994-10-05"),66.0,1.75,"Intensivo",null,null,null);
		assertEquals(21.55, usuario.indiceMasaCorporal(), 0.1);
		
	
		
		
		
	}
	
	
	@Test
	public void testIMCPesoYEstaturaAB()
	{
		
		
		
		usuario = new Usuario("Alejandro","Masculino",LocalDate.parse("1994-10-05"),74.0,1.82,"Mediano",null,null,null);
		assertEquals(22.40, usuario.indiceMasaCorporal(), 0.1);
		
	}
	
	
	@Test
	public void testUsuarioQueSigueRutinaSaludableCumpleIMCYCondiocionesPreexistentes()
	{
		
		Set<CondicionPreexistente> condicionesSaludables= new HashSet<CondicionPreexistente>();
		
		usuario = new Usuario("Alejandro","Masculino",LocalDate.parse("1994-10-05"),74.0,1.82,"Mediano",preferenciaAlimenticiaSaludable,condicionesSaludables,null);
		assertTrue(usuario.sigueRutinaSaludable());
		
		
	}
	
	@Test
	public void testUsuarioQueSigueRutinaSaludableNoCumpleIMCYSiCondiocionesPreexistentes()
	{
		
		Set<CondicionPreexistente> condicionesSaludables= new HashSet<CondicionPreexistente>();
		
		usuario = new Usuario("Alejandro","Masculino",LocalDate.parse("1994-10-05"),150.0,1.82,"Mediano",preferenciaAlimenticiaSaludable,condicionesSaludables,null);
		assertFalse(usuario.sigueRutinaSaludable());
		
		
	}
	
	
	@Test
	public void testUsuarioQueSigueRutinaSaludableCumpleIMCYSubsanaCondiocionesPreexistentes()
	{
		
		Set<CondicionPreexistente> condiciones= new HashSet<CondicionPreexistente>();
		condiciones.add(new Celiaco("Celiaco",comidasProhibidas));
		condiciones.add(new Diabetico("Diabetico",comidasProhibidas));
		
		
		usuario = new Usuario("Alejandro","Masculino",LocalDate.parse("1994-10-05"),69.0,1.82,"Activa",preferenciaAlimenticiaSaludable,condiciones,null);
		assertTrue(usuario.sigueRutinaSaludable());
		
		
	}
	
	@Test
	public void testUsuarioDiabeticoNoSigueRutinaSaludable(){
		Set<CondicionPreexistente> condiciones= new HashSet<CondicionPreexistente>();
		condiciones.add(new Diabetico("Diabetico",comidasProhibidas));
		usuario= new Usuario("Pedro", "Masculino", LocalDate.parse("1990-01-01"),60.0,1.7, "Leve",preferenciaAlimenticiaNoSaludable, condiciones, null);
		assertFalse(usuario.sigueRutinaSaludable());

	}
	
	@Test
	public void testUsuarioSinFechaDeNacimiento(){
		usuario= new Usuario("Pedro", "Masculino", LocalDate.parse("2016-01-01"),60.0,1.7, "Leve",preferenciaAlimenticiaNoSaludable, null, null);
		assertFalse(usuario.validar());

	}
	
	
	

}
