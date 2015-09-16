package queComemos;

import static org.junit.Assert.*;
import interfaces.CondicionPreexistenteI;
import interfaces.ObservadorI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import builderUsuario.UsuarioBuilder;
import receta.Ingrediente;
import receta.Preparacion;
import receta.Receta;
import repositorios.Recetario;
import usuario.GustosSobreAlimentos;
import usuario.Usuario;
import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.Diabetico;
import condicionesPreexistentes.Hipertenso;
import condicionesPreexistentes.Vegano;

public class TestUsuario {

	private Usuario usuario;
	private Receta receta;
	private Preparacion preparacion;
	private GustosSobreAlimentos preferenciaAlimenticiaNoSaludable;
	private List<String> comidasProhibidas;

	@Before
	public void setUp() throws Exception {
		
		Recetario.inadecuados= new ArrayList<CondicionPreexistenteI>();
		Recetario.recetas = new ArrayList<Receta>();
		Recetario.observadores = new ArrayList<ObservadorI>();

		comidasProhibidas = new ArrayList<String>();
		comidasProhibidas.add("Pan");
		
		
		List<String>	instrucciones = new ArrayList<String>();
		List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		List<Ingrediente>condimentos = new ArrayList<Ingrediente>();
		List<Receta> subRecetas=new ArrayList<Receta>();
		
		
			
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
			 
			  receta = new Receta("PolloConPapas", 50.0,preparacion,dificultad,temporada,subRecetas,null);
			  
			  

	}
	
	
	
	@Test
	public void testIMCPesoyEstaturaPositiva() {

		UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
		constructorDeUsuario.nombre("Matias");
		constructorDeUsuario.sexo("Masculino");
		constructorDeUsuario.peso(70.0);
		constructorDeUsuario.estatura(1.75);
		
		
		
		usuario = constructorDeUsuario.crearUsuario();
				
		assertEquals(22.85, usuario.indiceMasaCorporal(), 0.1);

	}

	@Test
	public void testIMCPPesoYEstaturaF() {
		
		
		UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
		constructorDeUsuario.nombre("Matias");
		constructorDeUsuario.sexo("Masculino");
		constructorDeUsuario.peso(80.0);
		constructorDeUsuario.estatura(1.75);
		
		
		
		usuario = constructorDeUsuario.crearUsuario();
				

		assertEquals(26.12, usuario.indiceMasaCorporal(), 0.1);

	}

	@Test
	public void testIMCPeso66Estatura172() {

		
		UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
		constructorDeUsuario.nombre("Matias");
		constructorDeUsuario.sexo("Masculino");
		constructorDeUsuario.peso(66.0);
		constructorDeUsuario.estatura(1.75);
		
		
		
		usuario = constructorDeUsuario.crearUsuario();
				
		assertEquals(21.55, usuario.indiceMasaCorporal(), 0.1);

	}

	@Test
	public void testIMCPesoYEstaturaAB() {

		UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
		constructorDeUsuario.nombre("Matias");
		constructorDeUsuario.sexo("Masculino");
		constructorDeUsuario.peso(74.0);
		constructorDeUsuario.estatura(1.82);
		
		
		
		usuario = constructorDeUsuario.crearUsuario();
				


		assertEquals(22.40, usuario.indiceMasaCorporal(), 0.1);

	}

	@Test
	public void testUsuarioQueSigueRutinaSaludableCumpleIMCYCondiocionesPreexistentes() {
		
		
		
		UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
		constructorDeUsuario.nombre("Matias");
		constructorDeUsuario.sexo("Masculino");
		constructorDeUsuario.peso(74.0);
		constructorDeUsuario.estatura(1.82);
		constructorDeUsuario.rutina("Mediano");
		constructorDeUsuario.leGusta("Carne");
		constructorDeUsuario.leGusta("Fruta");
		constructorDeUsuario.leGusta("Pasta");	
		
		
		usuario = constructorDeUsuario.crearUsuario();
				
		
		
		assertTrue(usuario.sigueRutinaSaludable());

	}

	@Test
	public void testUsuarioQueSigueRutinaSaludableNoCumpleIMCYSiCondiocionesPreexistentes() {

		
		
		UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
		constructorDeUsuario.nombre("Matias");
		constructorDeUsuario.sexo("Masculino");
		constructorDeUsuario.peso(150.0);
		constructorDeUsuario.estatura(1.82);
		constructorDeUsuario.rutina("Mediano");
		constructorDeUsuario.leGusta("Carne");
		constructorDeUsuario.leGusta("Fruta");
		constructorDeUsuario.leGusta("Pasta");	
		
		
		usuario = constructorDeUsuario.crearUsuario();
				
		

		assertFalse(usuario.sigueRutinaSaludable());

	}

	@Test
	public void testUsuarioQueSigueRutinaSaludableCumpleIMCYSubsanaCondiocionesPreexistentes() {

	

		UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
		constructorDeUsuario.nombre("Matias");
		constructorDeUsuario.sexo("Masculino");
		constructorDeUsuario.peso(69.0);
		constructorDeUsuario.estatura(1.82);
		constructorDeUsuario.rutina("Activa");
		constructorDeUsuario.leGusta("Carne");
		constructorDeUsuario.leGusta("Fruta");
		constructorDeUsuario.leGusta("Pasta");	
		constructorDeUsuario.esCeliaco();
		constructorDeUsuario.esDiabetico();
		
		
		
		usuario = constructorDeUsuario.crearUsuario();

		assertTrue(usuario.sigueRutinaSaludable());

	}
	
	
	@Test
	public void testUsuarioQueSigueRutinaSaludableSinCondicionesPreexistentes() {
		
		
		UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
		constructorDeUsuario.nombre("Matias");
		constructorDeUsuario.sexo("Masculino");
		constructorDeUsuario.peso(69.0);
		constructorDeUsuario.estatura(1.82);
		constructorDeUsuario.rutina("Activa");
		constructorDeUsuario.leGusta("Carne");
		constructorDeUsuario.leGusta("Fruta");
		constructorDeUsuario.leGusta("Pasta");	
		constructorDeUsuario.esCeliaco();
		constructorDeUsuario.esDiabetico();
		
		
		
		usuario = constructorDeUsuario.crearUsuario();

		assertTrue(usuario.sigueRutinaSaludable());
		



	}
	

	

	@Test
	public void testUsuarioDiabeticoNoSigueRutinaSaludable() {
		
		
		
		UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
		constructorDeUsuario.nombre("Matias");
		constructorDeUsuario.sexo("Masculino");
		constructorDeUsuario.peso(60.0);
		constructorDeUsuario.estatura(1.7);
		constructorDeUsuario.rutina("Leve");
		constructorDeUsuario.leGusta("Fritos");
		constructorDeUsuario.leGusta("Snacks");
		constructorDeUsuario.esDiabetico();
		
		
		
		usuario = constructorDeUsuario.crearUsuario();


		assertFalse(usuario.sigueRutinaSaludable());

	}

	@Test
	public void testUsuarioDiabeticoNoValida() {
		
		
		UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
		constructorDeUsuario.nombre("Matias");
		constructorDeUsuario.sexo("Masculino");
		constructorDeUsuario.peso(60.0);
		constructorDeUsuario.estatura(1.7);
		constructorDeUsuario.rutina("Leve");
		constructorDeUsuario.leGusta("Fritos");
		constructorDeUsuario.leGusta("Snacks");
		constructorDeUsuario.esDiabetico();
		
		
		
		usuario = constructorDeUsuario.crearUsuario();
		
	
		assertFalse(usuario.validaCondicionesPreexistentes(usuario));

	}

	@Test
	public void testUsuarioDiabeticoSiValida() {
		
		UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
		constructorDeUsuario.nombre("Matias");
		constructorDeUsuario.sexo("Masculino");
		constructorDeUsuario.peso(60.0);
		constructorDeUsuario.estatura(1.7);
		constructorDeUsuario.rutina("Leve");
		constructorDeUsuario.leGusta("Fritos");
		constructorDeUsuario.leGusta("Snacks");
		constructorDeUsuario.esDiabetico();
		
		
		
		usuario = constructorDeUsuario.crearUsuario();
		
		
		assertFalse(usuario.validaCondicionesPreexistentes(usuario));

	}

	

	public void testUsuarioCeliacoValidaConPreferenciaAlimenticiaNoSaludable() {
		
		UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
		constructorDeUsuario.nombre("Matias");
		constructorDeUsuario.sexo("Masculino");
		constructorDeUsuario.peso(60.0);
		constructorDeUsuario.estatura(1.7);
		constructorDeUsuario.rutina("Leve");
		constructorDeUsuario.leGusta("Fritos");
		constructorDeUsuario.leGusta("Snacks");
		constructorDeUsuario.esCeliaco();
		
		
		
		usuario = constructorDeUsuario.crearUsuario();
		
	
		assertTrue(usuario.validaCondicionesPreexistentes(usuario));

	}
	
	@Test
	public void testUsuarioCeliacoValidaConPreferenciaAlimenticiaSaludable() {
		
		UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
		constructorDeUsuario.nombre("Matias");
		constructorDeUsuario.sexo("Masculino");
		constructorDeUsuario.peso(60.0);
		constructorDeUsuario.estatura(1.7);
		constructorDeUsuario.rutina("Carne");
		constructorDeUsuario.leGusta("Pasta");
		constructorDeUsuario.leGusta("Fruta");
		constructorDeUsuario.esCeliaco();
		
		
		
		usuario = constructorDeUsuario.crearUsuario();

		assertTrue(usuario.validaCondicionesPreexistentes(usuario));
		
	}

	
	@Test
	public void testUsuarioVeganoNoValida() {
		
		UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
		constructorDeUsuario.nombre("Matias");
		constructorDeUsuario.sexo("Masculino");
		constructorDeUsuario.peso(60.0);
		constructorDeUsuario.estatura(1.7);
		constructorDeUsuario.rutina("Leve");
		constructorDeUsuario.leGusta("Fritos");
		constructorDeUsuario.leGusta("Snacks");
		constructorDeUsuario.esVegano();
		
		
		
		usuario = constructorDeUsuario.crearUsuario();
		
		assertFalse(usuario.validaCondicionesPreexistentes(usuario));

	}

	@Test
	public void testUsuarioVeganoSiValida() {
		
		
		UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
		constructorDeUsuario.nombre("Matias");
		constructorDeUsuario.sexo("Masculino");
		constructorDeUsuario.peso(60.0);
		constructorDeUsuario.estatura(1.7);
		constructorDeUsuario.rutina("Leve");
		constructorDeUsuario.esVegano();
		
		
		
		usuario = constructorDeUsuario.crearUsuario();
		assertFalse(usuario.validaCondicionesPreexistentes(usuario));

	}

	@Test
	public void testUsuarioHipertensoNoValida() {
		
		
		UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
		constructorDeUsuario.nombre("Matias");
		constructorDeUsuario.sexo("Masculino");
		constructorDeUsuario.peso(60.0);
		constructorDeUsuario.estatura(1.7);
		constructorDeUsuario.rutina("Leve");
		constructorDeUsuario.esHipertenso();
		
		
		
		usuario = constructorDeUsuario.crearUsuario();

		assertFalse(usuario.validaCondicionesPreexistentes(usuario));

	}

	@Test
	public void testUsuarioHipertensoSiValida() {
		
		
		UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
		constructorDeUsuario.nombre("Matias");
		constructorDeUsuario.sexo("Masculino");
		constructorDeUsuario.peso(60.0);
		constructorDeUsuario.estatura(1.7);
		constructorDeUsuario.rutina("Leve");
		constructorDeUsuario.leGusta("Fritos");
		constructorDeUsuario.leGusta("Snacks");
		constructorDeUsuario.esHipertenso();
		
		
		
		usuario = constructorDeUsuario.crearUsuario();
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

		UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
			
		
		
		Usuario usr = constructorDeUsuario.crearUsuario();
				
	
		
			
		
		usr.agregarReceta(receta);
		
		assertTrue(usr.getMisRecetas().size()>0);
		
	}
		
		
		@Test
		public void testModificarRecetaPublica()
		{
			
	
			

			UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
				
			
			
			Usuario usr = constructorDeUsuario.crearUsuario();
					
					
		
			
			Recetario.recetas.add(receta);
			
			usr.modificaUnaRecetaPublica("PolloConPapas", "SoloPollo",null,null,null,null);
			assertTrue(usr.getMisRecetas().size()>0);
			assertTrue(usr.getMisRecetas().stream().anyMatch(rec -> rec.getNombre().equals("SoloPollo")));
			assertTrue(Recetario.recetas.stream().anyMatch(orec -> orec.getNombre().equals("PolloConPapas")));
			
			
		}
		
		
		@Test
		public void testMarcarReceta()
		
		{
					
			

			UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
				
			
			
			Usuario usr = constructorDeUsuario.crearUsuario();
					
				
			
			usr.marcarComoFavorita(receta);
			
			assertTrue(usr.getFavoritas().contains(receta));
		}
		
		
		@Test
		public void testValidarUsuario()
		
		{
					
			
			Usuario usr = new Usuario (3,"juancito","masculino",LocalDate.parse("2014-01-01"), 60.0, 1.7, "Leve",
						preferenciaAlimenticiaNoSaludable,new ArrayList<CondicionPreexistenteI>(),new ArrayList<Receta>());
					
				
			
			assertTrue(usr.validar());
		
		}
		
		
		
		@Test
		public void testEsAdecuadaRecetaParaUsuario()
		
		{
					
			

			UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
				
			
			
			Usuario usr = constructorDeUsuario.crearUsuario();
					
			
			
				
			
			assertTrue(usr.esAdecuadaLaReceta(receta));
		
		}
		

		
		

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

