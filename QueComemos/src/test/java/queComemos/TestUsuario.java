package queComemos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import observadores.ObserverVecesConsultada;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.frba.dds.tpAnual.queComemos.GustosSobreAlimentos;
import ar.edu.utn.frba.dds.tpAnual.queComemos.recetas.Recetario;
import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.Diabetico;
import condicionesPreexistentes.Hipertenso;
import condicionesPreexistentes.Vegano;

public class TestUsuario {

	private Usuario usuario;
	private Receta receta;
	private Preparacion preparacion;
	private GustosSobreAlimentos preferenciaAlimenticiaSaludable;
	private GustosSobreAlimentos preferenciaAlimenticiaNoSaludable;
	private List<String> comidasProhibidas;

	@Before
	public void setUp() throws Exception {
		
		List<String> comidasQueGustaUsrSaludable = new ArrayList<String>();
		List<String> comidasQueGustaUsrNoSaludable = new ArrayList<String>();
		List<String> comidasQueDisgustaUsr = new ArrayList<String>();

		comidasQueGustaUsrSaludable.add("Fruta");
		comidasQueGustaUsrSaludable.add("Carne");
		comidasQueGustaUsrSaludable.add("Pasta");

		comidasQueGustaUsrNoSaludable.add("Fritos");
		comidasQueGustaUsrNoSaludable.add("Snacks");

		
		
		comidasQueDisgustaUsr.add("Verduras");

		preferenciaAlimenticiaSaludable = new GustosSobreAlimentos(
				comidasQueGustaUsrSaludable, comidasQueDisgustaUsr);
		preferenciaAlimenticiaNoSaludable = new GustosSobreAlimentos(
				comidasQueGustaUsrNoSaludable, comidasQueDisgustaUsr);

		comidasProhibidas = new ArrayList<String>();
		comidasProhibidas.add("Pan");
		
		
		List<String>	instrucciones = new ArrayList<String>();
		List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		List<Ingrediente>condimentos = new ArrayList<Ingrediente>();
		List<Receta> subRecetas=new ArrayList<Receta>();
		
		List<CondicionPreexistenteI> inadecuados=new ArrayList<CondicionPreexistenteI>();
		List<String>comidasProhibidas= new ArrayList<String>();
		List<String>comidasProhibidasH= new ArrayList<String>();
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
			 
			  receta = new Receta("PolloConPapas", 50.0,preparacion,dificultad,temporada,subRecetas,inadecuados);

	}
	
	
	
	@Test
	public void testIMCPesoyEstaturaPositiva() {

		usuario = new Usuario(5,"Matias", "Masculino",
				LocalDate.parse("1994-08-05"), 70.0, 1.75, "Leve", null, null,
				null);
		assertEquals(22.85, usuario.indiceMasaCorporal(), 0.1);

	}

	@Test
	public void testIMCPPesoYEstaturaF() {

		usuario = new Usuario(3,"Juan", "Masculino",
				LocalDate.parse("1998-08-05"), 80.0, 1.75, "Leve", null, null,
				null);
		assertEquals(26.12, usuario.indiceMasaCorporal(), 0.1);

	}

	@Test
	public void testIMCPeso66Estatura172() {

		usuario = new Usuario(2,"Juan", "Masculino",
				LocalDate.parse("1994-10-05"), 66.0, 1.75, "Intensivo", null,
				null, null);
		assertEquals(21.55, usuario.indiceMasaCorporal(), 0.1);

	}

	@Test
	public void testIMCPesoYEstaturaAB() {

		usuario = new Usuario(1,"Alejandro", "Masculino",
				LocalDate.parse("1994-10-05"), 74.0, 1.82, "Mediano", null,
				null, null);
		assertEquals(22.40, usuario.indiceMasaCorporal(), 0.1);

	}

	@Test
	public void testUsuarioQueSigueRutinaSaludableCumpleIMCYCondiocionesPreexistentes() {

		List<CondicionPreexistenteI> condicionesSaludables = new ArrayList<CondicionPreexistenteI>();

		usuario = new Usuario(8,"Alejandro", "Masculino",
				LocalDate.parse("1994-10-05"), 74.0, 1.82, "Mediano",
				preferenciaAlimenticiaSaludable, condicionesSaludables, null);
		assertTrue(usuario.sigueRutinaSaludable());

	}

	@Test
	public void testUsuarioQueSigueRutinaSaludableNoCumpleIMCYSiCondiocionesPreexistentes() {

		List<CondicionPreexistenteI> condicionesSaludables = new ArrayList<CondicionPreexistenteI>();

		usuario = new Usuario(8,"Alejandro", "Masculino",
				LocalDate.parse("1994-10-05"), 150.0, 1.82, "Mediano",
				preferenciaAlimenticiaSaludable, condicionesSaludables, null);
		assertFalse(usuario.sigueRutinaSaludable());

	}

	@Test
	public void testUsuarioQueSigueRutinaSaludableCumpleIMCYSubsanaCondiocionesPreexistentes() {

		List<CondicionPreexistenteI> condiciones = new ArrayList<CondicionPreexistenteI>();
		condiciones.add(new Celiaco("Celiaco", comidasProhibidas));
		condiciones.add(new Diabetico("Diabetico", comidasProhibidas));

		usuario = new Usuario(9,"Alejandro", "Masculino",
				LocalDate.parse("1994-10-05"), 69.0, 1.82, "Activa",
				preferenciaAlimenticiaSaludable, condiciones, null);
		assertTrue(usuario.sigueRutinaSaludable());

	}
	

	@Test
	public void testUsuarioDiabeticoNoSigueRutinaSaludable() {
		List<CondicionPreexistenteI> condiciones = new ArrayList<CondicionPreexistenteI>();
		condiciones.add(new Diabetico("Diabetico", comidasProhibidas));
		usuario = new Usuario(43,"Pedro", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				preferenciaAlimenticiaNoSaludable, condiciones, null);
		assertFalse(usuario.sigueRutinaSaludable());

	}

	@Test
	public void testUsuarioDiabeticoNoValida() {
		List<CondicionPreexistenteI> condiciones = new ArrayList<CondicionPreexistenteI>();
		condiciones.add(new Diabetico("Diabetico", comidasProhibidas));
		usuario = new Usuario(32,"Pedro", null, LocalDate.parse("1990-01-01"),
				60.0, 1.7, "Leve", preferenciaAlimenticiaNoSaludable,
				condiciones, null);
		assertFalse(usuario.validaCondicionesPreexistentes(usuario));

	}

	@Test
	public void testUsuarioDiabeticoSiValida() {
		List<CondicionPreexistenteI> condiciones = new ArrayList<CondicionPreexistenteI>();
		condiciones.add(new Diabetico("Diabetico", comidasProhibidas));
		usuario = new Usuario(30,"Pedro", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				preferenciaAlimenticiaNoSaludable, condiciones, null);
		assertFalse(usuario.validaCondicionesPreexistentes(usuario));

	}

	

	public void testUsuarioCeliacoValidaConPreferenciaAlimenticiaNoSaludable() {
		List<CondicionPreexistenteI> condiciones = new ArrayList<CondicionPreexistenteI>();

		condiciones.add(new Celiaco("Celiaco", comidasProhibidas));
		usuario = new Usuario(1,"Pedro", null, LocalDate.parse("1990-01-01"),
				60.0, 1.7, "Leve", preferenciaAlimenticiaNoSaludable,
				condiciones, null);
		assertTrue(usuario.validaCondicionesPreexistentes(usuario));

	}
	
	@Test
	public void testUsuarioCeliacoValidaConPreferenciaAlimenticiaSaludable() {
		List<CondicionPreexistenteI> condiciones = new ArrayList<CondicionPreexistenteI>();
		condiciones.add(new Celiaco("Celiaco", comidasProhibidas));
		usuario = new Usuario(4,"Pedro", null, LocalDate.parse("1990-01-01"),
				60.0, 1.7, "Leve", preferenciaAlimenticiaSaludable, condiciones, null);
		assertTrue(usuario.validaCondicionesPreexistentes(usuario));
		
	}

	
	@Test
	public void testUsuarioVeganoNoValida() {
		List<CondicionPreexistenteI> condiciones = new ArrayList<CondicionPreexistenteI>();
		condiciones.add(new Vegano("Vegano", comidasProhibidas));
		usuario = new Usuario(3,"Pedro", null, LocalDate.parse("1990-01-01"),
				60.0, 1.7, "Leve", preferenciaAlimenticiaNoSaludable,
				condiciones, null);
		assertFalse(usuario.validaCondicionesPreexistentes(usuario));

	}

	@Test
	public void testUsuarioVeganoSiValida() {
		List<CondicionPreexistenteI> condiciones = new ArrayList<CondicionPreexistenteI>();
		condiciones.add(new Vegano("Vegano", comidasProhibidas));
		GustosSobreAlimentos prefe = new GustosSobreAlimentos(new ArrayList<String>(),new ArrayList<String>());
		usuario = new Usuario(1,"Pedro", null, LocalDate.parse("1990-01-01"),
				60.0, 1.7, "Leve", prefe, condiciones, null);
		assertFalse(usuario.validaCondicionesPreexistentes(usuario));

	}

	@Test
	public void testUsuarioHipertensoNoValida() {
		List<CondicionPreexistenteI> condiciones = new ArrayList<CondicionPreexistenteI>();
		condiciones.add(new Hipertenso("Hipertenso", comidasProhibidas));
		usuario = new Usuario(1,"Pedro", null, LocalDate.parse("1990-01-01"),
				60.0, 1.7, "Leve", null, condiciones, null);
		assertFalse(usuario.validaCondicionesPreexistentes(usuario));

	}

	@Test
	public void testUsuarioHipertensoSiValida() {
		List<CondicionPreexistenteI> condiciones = new ArrayList<CondicionPreexistenteI>();
		condiciones.add(new Hipertenso("Hipertenso", comidasProhibidas));
		usuario = new Usuario(1,"Pedro", null, LocalDate.parse("1990-01-01"),
				60.0, 1.7, "Leve", preferenciaAlimenticiaNoSaludable,
				condiciones, null);
		assertFalse(usuario.validaCondicionesPreexistentes(usuario));

	}

	@Test
	public void testUsuarioSinFechaDeNacimiento() {
		usuario = new Usuario(1,"Pedro", "Masculino",
				LocalDate.parse("2016-01-01"), 60.0, 1.7, "Leve",
				preferenciaAlimenticiaNoSaludable, null, null);
		assertFalse(usuario.validar());

	}
	
	@Test
	public void testAgregarReceta()
	{

//		Recetario.inadecuados=new ArrayList<CondicionPreexistenteI>();
				
		Usuario usr = new Usuario (1,"juan","masculino",LocalDate.parse("2016-01-01"), 60.0, 1.7, "Leve",
					preferenciaAlimenticiaNoSaludable,new ArrayList<CondicionPreexistenteI>(),new ArrayList<Receta>());
		
		
			
		
		usr.agregarReceta(receta);
		
		assertTrue(usr.getMisRecetas().size()>0);
		
	}
		
		
		@Test
		public void testModificarRecetaPublica()
		{
			
	
			
			
			Usuario usr = new Usuario (2,"juan","masculino",LocalDate.parse("2016-01-01"), 60.0, 1.7, "Leve",
						preferenciaAlimenticiaNoSaludable,new ArrayList<CondicionPreexistenteI>(),new ArrayList<Receta>());
			
					
		
			
			Recetario.recetas.add(receta);
			
			usr.modificaUnaRecetaPublica("PolloConPapas", "SoloPollo",null,null,null,null);
			assertTrue(usr.getMisRecetas().size()>0);
			assertTrue(usr.getMisRecetas().stream().anyMatch(rec -> rec.getNombre().equals("SoloPollo")));
			assertTrue(Recetario.recetas.stream().anyMatch(orec -> orec.getNombre().equals("PolloConPapas")));
			
			
		}
		
		
		@Test
		public void testMarcarReceta()
		
		{
					
			
			Usuario usr = new Usuario (3,"juan","masculino",LocalDate.parse("2016-01-01"), 60.0, 1.7, "Leve",
						preferenciaAlimenticiaNoSaludable,new ArrayList<CondicionPreexistenteI>(),new ArrayList<Receta>());
					
				
			
			usr.marcarComoFavorita(receta);
			
			assertTrue(usr.getFavoritas().contains(receta));
		}
		

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

