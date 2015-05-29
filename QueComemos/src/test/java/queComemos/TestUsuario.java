package queComemos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class TestUsuario {

	private Usuario usuario;
	private Receta receta;
	private Preparacion preparacion;
	private PreferenciaAlimenticia preferenciaAlimenticiaSaludable;
	private PreferenciaAlimenticia preferenciaAlimenticiaNoSaludable;
	private Set<String> comidasProhibidas;

	@Before
	public void setUp() throws Exception {
		
		QueComemosApp.inicializar();
		Set<String> comidasQueGustaUsrSaludable = new HashSet<String>();
		Set<String> comidasQueGustaUsrNoSaludable = new HashSet<String>();
		Set<String> comidasQueDisgustaUsr = new HashSet<String>();

		comidasQueGustaUsrSaludable.add("Fruta");
		comidasQueGustaUsrSaludable.add("Carne");
		comidasQueGustaUsrSaludable.add("Pasta");

		comidasQueGustaUsrNoSaludable.add("Fritos");
		comidasQueGustaUsrNoSaludable.add("Snacks");

		
		
		comidasQueDisgustaUsr.add("Verduras");

		preferenciaAlimenticiaSaludable = new PreferenciaAlimenticia(
				comidasQueGustaUsrSaludable, comidasQueDisgustaUsr);
		preferenciaAlimenticiaNoSaludable = new PreferenciaAlimenticia(
				comidasQueGustaUsrNoSaludable, comidasQueDisgustaUsr);

		comidasProhibidas = new HashSet<String>();
		comidasProhibidas.add("Pan");

	}
	
	
	
	@Test
	public void testIMCPesoyEstaturaPositiva() {

		usuario = new Usuario("Matias", "Masculino",
				LocalDate.parse("1994-08-05"), 70.0, 1.75, "Leve", null, null,
				null);
		assertEquals(22.85, usuario.indiceMasaCorporal(), 0.1);

	}

	@Test
	public void testIMCPPesoYEstaturaF() {

		usuario = new Usuario("Juan", "Masculino",
				LocalDate.parse("1998-08-05"), 80.0, 1.75, "Leve", null, null,
				null);
		assertEquals(26.12, usuario.indiceMasaCorporal(), 0.1);

	}

	@Test
	public void testIMCPeso66Estatura172() {

		usuario = new Usuario("Juan", "Masculino",
				LocalDate.parse("1994-10-05"), 66.0, 1.75, "Intensivo", null,
				null, null);
		assertEquals(21.55, usuario.indiceMasaCorporal(), 0.1);

	}

	@Test
	public void testIMCPesoYEstaturaAB() {

		usuario = new Usuario("Alejandro", "Masculino",
				LocalDate.parse("1994-10-05"), 74.0, 1.82, "Mediano", null,
				null, null);
		assertEquals(22.40, usuario.indiceMasaCorporal(), 0.1);

	}

	@Test
	public void testUsuarioQueSigueRutinaSaludableCumpleIMCYCondiocionesPreexistentes() {

		Set<CondicionPreexistenteI> condicionesSaludables = new HashSet<CondicionPreexistenteI>();

		usuario = new Usuario("Alejandro", "Masculino",
				LocalDate.parse("1994-10-05"), 74.0, 1.82, "Mediano",
				preferenciaAlimenticiaSaludable, condicionesSaludables, null);
		assertTrue(usuario.sigueRutinaSaludable());

	}

	@Test
	public void testUsuarioQueSigueRutinaSaludableNoCumpleIMCYSiCondiocionesPreexistentes() {

		Set<CondicionPreexistenteI> condicionesSaludables = new HashSet<CondicionPreexistenteI>();

		usuario = new Usuario("Alejandro", "Masculino",
				LocalDate.parse("1994-10-05"), 150.0, 1.82, "Mediano",
				preferenciaAlimenticiaSaludable, condicionesSaludables, null);
		assertFalse(usuario.sigueRutinaSaludable());

	}

	@Test
	public void testUsuarioQueSigueRutinaSaludableCumpleIMCYSubsanaCondiocionesPreexistentes() {

		Set<CondicionPreexistenteI> condiciones = new HashSet<CondicionPreexistenteI>();
		condiciones.add(new Celiaco("Celiaco", comidasProhibidas));
		condiciones.add(new Diabetico("Diabetico", comidasProhibidas));

		usuario = new Usuario("Alejandro", "Masculino",
				LocalDate.parse("1994-10-05"), 69.0, 1.82, "Activa",
				preferenciaAlimenticiaSaludable, condiciones, null);
		assertTrue(usuario.sigueRutinaSaludable());

	}
	

	@Test
	public void testUsuarioDiabeticoNoSigueRutinaSaludable() {
		Set<CondicionPreexistenteI> condiciones = new HashSet<CondicionPreexistenteI>();
		condiciones.add(new Diabetico("Diabetico", comidasProhibidas));
		usuario = new Usuario("Pedro", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				preferenciaAlimenticiaNoSaludable, condiciones, null);
		assertFalse(usuario.sigueRutinaSaludable());

	}

	@Test
	public void testUsuarioDiabeticoNoValida() {
		Set<CondicionPreexistenteI> condiciones = new HashSet<CondicionPreexistenteI>();
		condiciones.add(new Diabetico("Diabetico", comidasProhibidas));
		usuario = new Usuario("Pedro", null, LocalDate.parse("1990-01-01"),
				60.0, 1.7, "Leve", preferenciaAlimenticiaNoSaludable,
				condiciones, null);
		assertFalse(usuario.validaCondicionesPreexistentes(usuario));

	}

	@Test
	public void testUsuarioDiabeticoSiValida() {
		Set<CondicionPreexistenteI> condiciones = new HashSet<CondicionPreexistenteI>();
		condiciones.add(new Diabetico("Diabetico", comidasProhibidas));
		usuario = new Usuario("Pedro", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				preferenciaAlimenticiaNoSaludable, condiciones, null);
		assertFalse(usuario.validaCondicionesPreexistentes(usuario));

	}

	

	public void testUsuarioCeliacoValidaConPreferenciaAlimenticiaNoSaludable() {
		Set<CondicionPreexistenteI> condiciones = new HashSet<CondicionPreexistenteI>();

		condiciones.add(new Celiaco("Celiaco", comidasProhibidas));
		usuario = new Usuario("Pedro", null, LocalDate.parse("1990-01-01"),
				60.0, 1.7, "Leve", preferenciaAlimenticiaNoSaludable,
				condiciones, null);
		assertTrue(usuario.validaCondicionesPreexistentes(usuario));

	}
	
	@Test
	public void testUsuarioCeliacoValidaConPreferenciaAlimenticiaSaludable() {
		Set<CondicionPreexistenteI> condiciones = new HashSet<CondicionPreexistenteI>();
		condiciones.add(new Celiaco("Celiaco", comidasProhibidas));
		usuario = new Usuario("Pedro", null, LocalDate.parse("1990-01-01"),
				60.0, 1.7, "Leve", preferenciaAlimenticiaSaludable, condiciones, null);
		assertTrue(usuario.validaCondicionesPreexistentes(usuario));
		
	}

	
	@Test
	public void testUsuarioVeganoNoValida() {
		Set<CondicionPreexistenteI> condiciones = new HashSet<CondicionPreexistenteI>();
		condiciones.add(new Vegano("Vegano", comidasProhibidas));
		usuario = new Usuario("Pedro", null, LocalDate.parse("1990-01-01"),
				60.0, 1.7, "Leve", preferenciaAlimenticiaNoSaludable,
				condiciones, null);
		assertFalse(usuario.validaCondicionesPreexistentes(usuario));

	}

	@Test
	public void testUsuarioVeganoSiValida() {
		Set<CondicionPreexistenteI> condiciones = new HashSet<CondicionPreexistenteI>();
		condiciones.add(new Vegano("Vegano", comidasProhibidas));
		PreferenciaAlimenticia prefe = new PreferenciaAlimenticia(new HashSet<String>(),new HashSet<String>());
		usuario = new Usuario("Pedro", null, LocalDate.parse("1990-01-01"),
				60.0, 1.7, "Leve", prefe, condiciones, null);
		assertFalse(usuario.validaCondicionesPreexistentes(usuario));

	}

	@Test
	public void testUsuarioHipertensoNoValida() {
		Set<CondicionPreexistenteI> condiciones = new HashSet<CondicionPreexistenteI>();
		condiciones.add(new Hipertenso("Hipertenso", comidasProhibidas));
		usuario = new Usuario("Pedro", null, LocalDate.parse("1990-01-01"),
				60.0, 1.7, "Leve", null, condiciones, null);
		assertFalse(usuario.validaCondicionesPreexistentes(usuario));

	}

	@Test
	public void testUsuarioHipertensoSiValida() {
		Set<CondicionPreexistenteI> condiciones = new HashSet<CondicionPreexistenteI>();
		condiciones.add(new Hipertenso("Hipertenso", comidasProhibidas));
		usuario = new Usuario("Pedro", null, LocalDate.parse("1990-01-01"),
				60.0, 1.7, "Leve", preferenciaAlimenticiaNoSaludable,
				condiciones, null);
		assertFalse(usuario.validaCondicionesPreexistentes(usuario));

	}

	@Test
	public void testUsuarioSinFechaDeNacimiento() {
		usuario = new Usuario("Pedro", "Masculino",
				LocalDate.parse("2016-01-01"), 60.0, 1.7, "Leve",
				preferenciaAlimenticiaNoSaludable, null, null);
		assertFalse(usuario.validar());

	}
	
	@Test
	public void testAgregarReceta()
	{

		Set<String>	instrucciones = new HashSet<String>();
		Set<Ingrediente> ingredientes = new HashSet<Ingrediente>();
		Set<Ingrediente>condimentos = new HashSet<Ingrediente>();
		Set<Receta> subRecetas=new HashSet<Receta>();
		
		Set<CondicionPreexistenteI> inadecuados=new HashSet<CondicionPreexistenteI>();
		Set<String>comidasProhibidas= new HashSet<String>();
		Set<String>comidasProhibidasH= new HashSet<String>();
		comidasProhibidasH.add("caldo");
		
		
		Celiaco celiaco = new Celiaco("celiaco",comidasProhibidas);
		Vegano vegano = new Vegano("vegano",comidasProhibidas);
		Hipertenso hipertenso = new Hipertenso("hipertenso",comidasProhibidasH);
		Diabetico diabetico = new Diabetico("diabetico",comidasProhibidas);
		
		
		
			
			instrucciones.add("Preparar");
			instrucciones.add("Revolver");
			instrucciones.add("Hornear");
			
			Ingrediente sal= new Ingrediente("Sal","grs",100);
			Ingrediente carne= new Ingrediente("Carne","kg",2);
			Ingrediente papas= new Ingrediente("papa","kg",3);
			Ingrediente mayonesa= new Ingrediente("Mayonesa","grs",100);
			Ingrediente azucar= new Ingrediente("Azucar","grs",150);
			
			ingredientes.add(azucar);
			ingredientes.add(carne);
			ingredientes.add(papas);
			condimentos.add(mayonesa);
			condimentos.add(sal);
		
		     preparacion = new Preparacion(ingredientes,condimentos,instrucciones);
			 String dificultad = "Baja";
			 String temporada= "Verano";
		
				QueComemosApp.inadecuados=new HashSet<CondicionPreexistenteI>();
				
		Usuario usr = new Usuario ("juan","masculino",LocalDate.parse("2016-01-01"), 60.0, 1.7, "Leve",
					preferenciaAlimenticiaNoSaludable,new HashSet<CondicionPreexistenteI>(),new HashSet<Receta>());
		
		
		
		Receta receta = new Receta("PolloConPapas", 50.0,  preparacion,dificultad,temporada,subRecetas,inadecuados);
		
		usr.agregarReceta(receta);
		
		assertTrue(usr.getMisRecetas().size()>0);
		
	}
		
		
		@Test
		public void testModificarRecetaPublica()
		{
			
			QueComemosApp.inadecuados=new HashSet<CondicionPreexistenteI>();
			Set<String>	instrucciones = new HashSet<String>();
			Set<Ingrediente> ingredientes = new HashSet<Ingrediente>();
			Set<Ingrediente>condimentos = new HashSet<Ingrediente>();
			Set<Receta> subRecetas=new HashSet<Receta>();
			
			Set<CondicionPreexistenteI> inadecuados=new HashSet<CondicionPreexistenteI>();
			Set<String>comidasProhibidas= new HashSet<String>();
			Set<String>comidasProhibidasH= new HashSet<String>();
			comidasProhibidasH.add("caldo");
			
			
			Celiaco celiaco = new Celiaco("celiaco",comidasProhibidas);
			Vegano vegano = new Vegano("vegano",comidasProhibidas);
			Hipertenso hipertenso = new Hipertenso("hipertenso",comidasProhibidasH);
			Diabetico diabetico = new Diabetico("diabetico",comidasProhibidas);
			
			
			
				
				instrucciones.add("Preparar");
				instrucciones.add("Revolver");
				instrucciones.add("Hornear");
				
				Ingrediente sal= new Ingrediente("Sal","grs",100);
				Ingrediente carne= new Ingrediente("Carne","kg",2);
				Ingrediente papas= new Ingrediente("papa","kg",3);
				Ingrediente mayonesa= new Ingrediente("Mayonesa","grs",100);
				Ingrediente azucar= new Ingrediente("Azucar","grs",150);
				
				ingredientes.add(azucar);
				ingredientes.add(carne);
				ingredientes.add(papas);
				condimentos.add(mayonesa);
				condimentos.add(sal);
			
			     preparacion = new Preparacion(ingredientes,condimentos,instrucciones);
				 String dificultad = "Baja";
				 String temporada= "Verano";
			
			
			Usuario usr = new Usuario ("juan","masculino",LocalDate.parse("2016-01-01"), 60.0, 1.7, "Leve",
						preferenciaAlimenticiaNoSaludable,new HashSet<CondicionPreexistenteI>(),new HashSet<Receta>());
			
			
			
			Receta receta = new Receta("PolloConPapas", 50.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		
			
			QueComemosApp.recetas.add(receta);
			
			usr.modificaUnaRecetaPublica("PolloConPapas", "SoloPollo",null,null,null,null);
			assertTrue(usr.getMisRecetas().size()>0);
			assertTrue(usr.getMisRecetas().stream().anyMatch(rec -> rec.getNombre().equals("SoloPollo")));
			assertTrue(QueComemosApp.recetas.stream().anyMatch(orec -> orec.getNombre().equals("PolloConPapas")));
			
			
		}
		
		
		@Test
		public void testMarcarReceta()
		
		{
			Set<String>	instrucciones = new HashSet<String>();
			Set<Ingrediente> ingredientes = new HashSet<Ingrediente>();
			Set<Ingrediente>condimentos = new HashSet<Ingrediente>();
			Set<Receta> subRecetas=new HashSet<Receta>();
			
			Set<CondicionPreexistenteI> inadecuados=new HashSet<CondicionPreexistenteI>();
			Set<String>comidasProhibidas= new HashSet<String>();
			Set<String>comidasProhibidasH= new HashSet<String>();
			comidasProhibidasH.add("caldo");
			
			
			Celiaco celiaco = new Celiaco("celiaco",comidasProhibidas);
			Vegano vegano = new Vegano("vegano",comidasProhibidas);
			Hipertenso hipertenso = new Hipertenso("hipertenso",comidasProhibidasH);
			Diabetico diabetico = new Diabetico("diabetico",comidasProhibidas);
			
			
			
				
				instrucciones.add("Preparar");
				instrucciones.add("Revolver");
				instrucciones.add("Hornear");
				
				Ingrediente sal= new Ingrediente("Sal","grs",100);
				Ingrediente carne= new Ingrediente("Carne","kg",2);
				Ingrediente papas= new Ingrediente("papa","kg",3);
				Ingrediente mayonesa= new Ingrediente("Mayonesa","grs",100);
				Ingrediente azucar= new Ingrediente("Azucar","grs",150);
				
				ingredientes.add(azucar);
				ingredientes.add(carne);
				ingredientes.add(papas);
				condimentos.add(mayonesa);
				condimentos.add(sal);
			
			     preparacion = new Preparacion(ingredientes,condimentos,instrucciones);
				 String dificultad = "Baja";
				 String temporada= "Verano";
			
			
			Usuario usr = new Usuario ("juan","masculino",LocalDate.parse("2016-01-01"), 60.0, 1.7, "Leve",
						preferenciaAlimenticiaNoSaludable,new HashSet<CondicionPreexistenteI>(),new HashSet<Receta>());
			
			
			
			Receta receta = new Receta("PolloConPapas", 50.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
			
			usr.marcarComoFavorita(receta);
			
			assertTrue(usr.getFavoritas().contains(receta));
		
			
		}
		

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

