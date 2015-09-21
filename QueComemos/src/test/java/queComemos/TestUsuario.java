package queComemos;

import static org.junit.Assert.*;
import interfaces.CondicionPreexistenteI;
import interfaces.ObservadorI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import builderReceta.RecetaBuilder;
import builderReceta.RecetaGenerica;
import builderUsuario.UsuarioBuilder;
import builderUsuario.UsuarioMasGenerico;
import builderUsuario.UsuarioSinValidacion;
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
		
		
		RecetaBuilder constructorReceta = new RecetaGenerica();
		constructorReceta.nombre("PolloConPapas");
		constructorReceta.calorias(50.);
		constructorReceta.instruccionesAgregar("Preparar");
		constructorReceta.instruccionesAgregar("Revolver");
		constructorReceta.instruccionesAgregar("Hornear");
		constructorReceta.ingredienteAgregar("Azucar", "grs", 150.);
		constructorReceta.ingredienteAgregar("papa", "kg", 3.);
		constructorReceta.ingredienteAgregar("Carne", "grs", 2.);
		constructorReceta.condimentoAgregar("Sal", "grs", 100.);
		constructorReceta.condimentoAgregar("Mayonesa", "grs", 100.);
		constructorReceta.temporada("Verano");
		constructorReceta.dificultad("Baja");
		
			
		  receta = constructorReceta.crearReceta();
			  
			  
			  

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
		public void testNoValidarUsuario()
		
		{
					
			
			Usuario usr = new Usuario (null,null,null,null, null, null, null,
					null,new ArrayList<CondicionPreexistenteI>(),new ArrayList<Receta>());
			
			usr.setNombre(usr.getNombre());
			

			usr.setEstatura(usr.getPeso());
			usr.setPeso(usr.getEstatura());
			usr.setSexo(usr.getSexo());
					
				
			
			assertFalse(usr.validar());
		
		}
		
		
		
		
		@Test
		public void testEsAdecuadaRecetaParaUsuario()
		
		{
					
			

			UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
				
			
			
			Usuario usr = constructorDeUsuario.crearUsuario();
					
			
			
				
			
			assertTrue(usr.esAdecuadaLaReceta(receta));
		
		}
		

		
		
		@Test
		public void testSigueRutinaSaludable()
		
		{
					
			

			UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
				
			constructorDeUsuario.peso(40.);
			constructorDeUsuario.estatura(1.3);
		
			
			Usuario usr = constructorDeUsuario.crearUsuario();
			
				
			
			assertTrue(usr.sigueRutinaSaludable());
		
		}
		


		@Test
		public void testNoPuedeVerReceta()
		
		{
					
			

			UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
				
			
			
			Usuario usr = constructorDeUsuario.crearUsuario();
					
			
			
				
			
			assertFalse(usr.puedeVer(receta));
		
		}
		
		@Test
		public void testpuedeVerRecetaEsPublica()
		
		{
					
			

			UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
				
			
			
			Usuario usr = constructorDeUsuario.crearUsuario();
					
			Recetario.recetas.add(receta);
			
				
			
			assertTrue(usr.puedeVer(receta));
		
		}
		
		@Test
		public void testpuedeVerRecetaEsPropia()
		
		{
					
			

			UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
				
			
			
			Usuario usr = constructorDeUsuario.crearUsuario();
					
		  usr.getMisRecetas().add(receta);
			
				
			
			assertTrue(usr.puedeVer(receta));
		
		}
		
		
		@Test
		public void testLeGustaReceta()
		
		{
					
			

			UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
				
			
			
			Usuario usr = constructorDeUsuario.crearUsuario();
		
				
			
			assertTrue(usr.leGusta(receta));
		
		}
		
		@Test
		public void testCrearUsuario()
		
		{
					
			

			UsuarioBuilder constructorDeUsuario = new UsuarioMasGenerico();
			constructorDeUsuario.nombre("juancito");
			constructorDeUsuario.peso(40.);
			constructorDeUsuario.estatura(1.4);
			constructorDeUsuario.fechaNacimiento(LocalDate.now().minusYears(1));
			constructorDeUsuario.esDiabetico();
			constructorDeUsuario.rutina("Activa");
						
			Usuario usr = constructorDeUsuario.crearUsuario();
		
				
			
			assertTrue(usr.validar());
		
		}
		
		
		
		
		
		
		
		

		
		
		

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

