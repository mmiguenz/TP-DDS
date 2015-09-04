package queComemos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import observadores.ObserverPorHora;
import observadores.ObserverPorSexo;
import observadores.ObserverVecesConsultada;
import observadores.ObserverVegetariano;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.frba.dds.tpAnual.queComemos.GustosSobreAlimentos;
import ar.edu.utn.frba.dds.tpAnual.queComemos.recetas.Recetario;
import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.Diabetico;
import condicionesPreexistentes.Hipertenso;
import condicionesPreexistentes.Vegano;
import builder.CeliacoBuilder;
import builder.CondicionBuilder;
import builder.DiabeticoBuilder;
import builder.HipertensoBuilder;
import builder.NexoConElUsuario;
import builder.UsuarioBuilder;
import builder.VeganoBuilder;

public class TestUsuarioBuilder{
	private CondicionPreexistenteI diabetico;
	private CondicionPreexistenteI celiaco;
	private CondicionPreexistenteI vegano;
	private CondicionPreexistenteI hipertenso;
	private List<CondicionPreexistenteI> condiciones;
	private GustosSobreAlimentos preferenciaParaVegano;
	
	@Before
	public void setUp() throws Exception {
//		Recetario.inicializar();
		NexoConElUsuario nexo= new NexoConElUsuario();
		
		DiabeticoBuilder diabeticobuilder= new DiabeticoBuilder();
		nexo.setCondicion(diabeticobuilder);
		diabetico = nexo.crear();
		Recetario.inadecuados.add(diabetico);
		
		CeliacoBuilder celiacobuilder= new CeliacoBuilder();
		nexo.setCondicion(celiacobuilder);
		celiaco = nexo.crear();
		Recetario.inadecuados.add(celiaco);
		
		VeganoBuilder veganobuilder= new VeganoBuilder();
		nexo.setCondicion(veganobuilder);
		vegano = nexo.crear();
		Recetario.inadecuados.add(vegano);
		
		HipertensoBuilder hipertensobuilder= new HipertensoBuilder();
		nexo.setCondicion(hipertensobuilder);
		hipertenso = nexo.crear();
		Recetario.inadecuados.add(hipertenso);
		
		condiciones= new ArrayList<CondicionPreexistenteI>();
		
		List<String>comidasParaVegano= new ArrayList<String>();
		comidasParaVegano.add("frutas");
		preferenciaParaVegano = new GustosSobreAlimentos(comidasParaVegano,new ArrayList<String>());
	}

	@Test
	public void testDiabeticoNoSubsanaCondicion() {
		
		condiciones.add(diabetico);
		Usuario usuario = new Usuario(1,"Matias","Hombre",LocalDate.parse("1994-05-15"),80.0,1.9,"Activa",null,condiciones,null);
		assertFalse(usuario.sigueRutinaSaludable());
		
	}
	
	@Test
	public void testDiabeticoSubsanaCondicion() {
		
		condiciones.add(diabetico);
		Usuario usuario = new Usuario(1,"Matias","Hombre",LocalDate.parse("1994-05-15"),60.0,1.9,"Activa",null,condiciones,null);
		assertTrue(usuario.sigueRutinaSaludable());
		
	}
	
	@Test
	public void testCeliacoSubsanaCondicion() {
		
		condiciones.add(celiaco);
		Usuario usuario = new Usuario(1,"Matias","Hombre",LocalDate.parse("1994-05-15"),80.0,1.9,"Activa",null,condiciones,null);
		assertTrue(usuario.sigueRutinaSaludable());
		
	}
	
	@Test
	public void testHipertensoNoSubsanaCondicion() {
		
		condiciones.add(hipertenso);
		Usuario usuario = new Usuario(1,"Matias","Hombre",LocalDate.parse("1994-05-15"),80.0,1.9,"Activa",null,condiciones,null);
		assertFalse(usuario.sigueRutinaSaludable());
		
	}
	
	@Test
	public void testHipertensoSubsanaCondicion() {
		
		condiciones.add(hipertenso);
		Usuario usuario = new Usuario(1,"Matias","Hombre",LocalDate.parse("1994-05-15"),80.0,1.9,"Intensiva",null,condiciones,null);
		assertTrue(usuario.sigueRutinaSaludable());
		
	}
	
	@Test
	public void testVeganoNoSubsanaCondicion() {
		
		condiciones.add(vegano);
		Usuario usuario = new Usuario(1,"Matias","Hombre",LocalDate.parse("1994-05-15"),80.0,1.9,"Activa",null,condiciones,null);
		assertFalse(usuario.sigueRutinaSaludable());
		
	}
	
	@Test
	public void testVeganoSubsanaCondicion() {
		
		condiciones.add(vegano);
		Usuario usuario = new Usuario(1,"Matias","Hombre",LocalDate.parse("1994-05-15"),80.0,1.9,"Intensiva",preferenciaParaVegano,condiciones,null);
		assertTrue(usuario.sigueRutinaSaludable());
		
	}
	
	@Test
	public void testVeganoYDiabeticoSubsanaCondicion() {
		
		condiciones.add(vegano);
		condiciones.add(diabetico);
		Usuario usuario = new Usuario(1,"Matias","Hombre",LocalDate.parse("1994-05-15"),69.0,1.9,"Activa",preferenciaParaVegano,condiciones,null);
		assertTrue(usuario.sigueRutinaSaludable());
		
	}
	
	@Test
	public void testVeganoYDiabeticoNoSubsanaCondicion() {
		
		condiciones.add(vegano);
		condiciones.add(diabetico);
		Usuario usuario = new Usuario(1,"Matias","Hombre",LocalDate.parse("1994-05-15"),69.0,1.9,"Leve",preferenciaParaVegano,condiciones,null);
		assertFalse(usuario.sigueRutinaSaludable());
		
	}
	
	@Test
	public void testVeganoYDiabeticoValida() {
		
		condiciones.add(vegano);
		condiciones.add(diabetico);
		Usuario usuario = new Usuario(1,"Matias","Hombre",LocalDate.parse("1994-05-15"),69.0,1.9,"Activa",preferenciaParaVegano,condiciones,null);
		assertTrue(usuario.validar());
		
	}
	
	@Test
	public void testVeganoYDiabeticoNoValida() {
		
		condiciones.add(vegano);
		condiciones.add(diabetico);
		Usuario usuario = new Usuario(1,"Matias","Hombre",LocalDate.parse("1994-05-15"),69.0,1.9,"Nada",preferenciaParaVegano,condiciones,null);
		assertFalse(usuario.validar());
		
	}
	
	@Test
	public void testPueveVerRecetaParaVegano(){
		
		List<String> comidasQueGustaUsrSaludable = new ArrayList<String>();
		comidasQueGustaUsrSaludable.add("frutas");
		comidasQueGustaUsrSaludable.add("Pasta");
		
		List<String> comidasQueDisgustaUsr = new ArrayList<String>();
		comidasQueDisgustaUsr.add("Carne");
		
		GustosSobreAlimentos preferencia=new GustosSobreAlimentos(
				comidasQueGustaUsrSaludable, comidasQueDisgustaUsr);
		
		condiciones.add(vegano);
				
		Usuario usuario =new Usuario(2,"pedro","Hombre",LocalDate.parse("1990-01-01"),60.0,1.70,
				"Leve",preferencia,condiciones,null);
		
		Recetario.inadecuados=new ArrayList<CondicionPreexistenteI>();		
		Recetario.inadecuados.add(diabetico);
		Recetario.inadecuados.add(celiaco);
		Recetario.inadecuados.add(vegano);
		Recetario.inadecuados.add(hipertenso);
				
		Ingrediente sal= new Ingrediente("Sal","grs",10);
		Ingrediente papas= new Ingrediente("Papa","kg",3);
		Ingrediente mayonesa= new Ingrediente("Mayonesa","grs",100);
		Ingrediente azucar= new Ingrediente("Azucar","grs",150);
		
		List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		ingredientes.add(azucar);
		ingredientes.add(papas);
		
		List<Ingrediente>condimentos = new ArrayList<Ingrediente>();
		condimentos.add(mayonesa);
		condimentos.add(sal);
		
		List<String> instrucciones = new ArrayList<String>();
		
		Preparacion preparacion=new Preparacion(ingredientes, condimentos, instrucciones);
		
		String dificultad="Dificil";
		String temporada="Verano";
		
		List<Receta> subRecetas=new ArrayList<Receta>();
		List<CondicionPreexistenteI> inadecuados = new ArrayList<CondicionPreexistenteI>();
		
		Receta receta = new Receta("Ensalada",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		
		List<Receta> misRecetas=new ArrayList<Receta>();
		misRecetas.add(receta);
		usuario.setMisRecetas(misRecetas);
		
		assertTrue(usuario.puedeVer(receta));		
						
	}
	
	@Test
	public void testNoPueveVerRecetaParaVeganoyDiabetico(){
		
		List<String> comidasQueGustaUsrSaludable = new ArrayList<String>();
		comidasQueGustaUsrSaludable.add("frutas");
		comidasQueGustaUsrSaludable.add("Pasta");
		
		List<String> comidasQueDisgustaUsr = new ArrayList<String>();
		comidasQueDisgustaUsr.add("Carne");
		
		GustosSobreAlimentos preferencia=new GustosSobreAlimentos(
				comidasQueGustaUsrSaludable, comidasQueDisgustaUsr);
		
		condiciones.add(vegano);
		condiciones.add(diabetico);
				
		Usuario usuario =new Usuario(2,"pedro","Hombre",LocalDate.parse("1990-01-01"),60.0,1.70,
				"Activa",preferencia,condiciones,null);
		
		Recetario.inadecuados=new ArrayList<CondicionPreexistenteI>();		
		Recetario.inadecuados.add(diabetico);
		Recetario.inadecuados.add(celiaco);
		Recetario.inadecuados.add(vegano);
		Recetario.inadecuados.add(hipertenso);
				
		Ingrediente sal= new Ingrediente("Sal","grs",10);
		Ingrediente papas= new Ingrediente("Papa","kg",3);
		Ingrediente mayonesa= new Ingrediente("Mayonesa","grs",100);
		Ingrediente azucar= new Ingrediente("Azucar","grs",150);
		
		List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		ingredientes.add(azucar);
		ingredientes.add(papas);
		
		List<Ingrediente>condimentos = new ArrayList<Ingrediente>();
		condimentos.add(mayonesa);
		condimentos.add(sal);
		
		List<String> instrucciones = new ArrayList<String>();
		
		Preparacion preparacion=new Preparacion(ingredientes, condimentos, instrucciones);
		
		String dificultad="Dificil";
		String temporada="Verano";
		
		List<Receta> subRecetas=new ArrayList<Receta>();
		List<CondicionPreexistenteI> inadecuados = new ArrayList<CondicionPreexistenteI>();
		
		Receta receta = new Receta("Ensalada",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		
		List<Receta> misRecetas=new ArrayList<Receta>();
		misRecetas.add(receta);
		usuario.setMisRecetas(misRecetas);
		
		assertFalse(usuario.puedeVer(receta));		
						
	}
	//Creando el usuario con UsuarioBuilder
	@Test
	public void testConusuarioBuilderVeganoYDiabeticoValida() throws Exception {
		
		UsuarioBuilder builder= new UsuarioBuilder();
		builder.setUsuarioID(1);
		builder.setNombre("Matias");
		builder.setSexo("Hombre");
		builder.setFechaDeNacimiento("1994-05-15");
		builder.setPeso(69.0);
		builder.setEstatura(1.7);
		builder.setRutina("Activa");
		builder.setComidasQueGusta("frutas");
		condiciones.add(vegano);
		condiciones.add(diabetico);
		builder.setCondicionesPreexistentes(condiciones);
				
		Usuario usuario=builder.crearUsuario();
		assertTrue(usuario.validar());		
	}
	
	/*@Test
	public void testConusuarioBuilderVeganoYDiabeticoNoValida() throws Exception {
		
		UsuarioBuilder builder= new UsuarioBuilder();
		builder.setUsuarioID(1);
		builder.setNombre("Matias");
		builder.setSexo("Hombre");
		builder.setFechaDeNacimiento("1994-05-15");
		builder.setPeso(69.0);
		builder.setEstatura(1.7);
		builder.setRutina("Leve");
		builder.setComidasQueGusta("frutas");
		condiciones.add(vegano);
		condiciones.add(diabetico);
		builder.setCondicionesPreexistentes(condiciones);
				
		Usuario usuario=builder.crearUsuario();
	}*/
	
	//¿como deberia hacer para que este ultimo test (testConusuarioBuilderVeganoYDiabeticoNoValida) corra en verde?
	
}