package queComemos;

import static org.junit.Assert.*;
import interfaces.CondicionPreexistenteI;
import interfaces.ObservadorI;

import java.util.ArrayList;
import java.util.List;

import observadores.ObservadorMarcaComoFavoritas;
import observadores.ObservadorParaElEnvioDeMails;

import org.junit.Before;
import org.junit.Test;

import builderUsuario.UsuarioBuilder;
import builderUsuario.UsuarioParaAprobacionDeSolicitudes;
import receta.Receta;
import repositorios.Consulta;
import repositorios.Recetario;
import repositorios.RepoUsuarios;
import tareas.GestorDeTareas;
import usuario.Usuario;

public class TestProcesarTareas {
	
	
	
	@Before
	public void setUp() throws Exception {
		
		List<String> usuariosParaNotificarViaMail = new ArrayList<>();
		usuariosParaNotificarViaMail.add("jContardo");
		ObservadorI observaSiElUsuarioNecesitaMail = new ObservadorParaElEnvioDeMails(usuariosParaNotificarViaMail); 
		Recetario.observadores = new ArrayList<ObservadorI>();
		Recetario.consultas =new ArrayList<Consulta>();
		Recetario.recetas =new ArrayList<Receta>();
		
		RepoUsuarios.inadecuados = new ArrayList<CondicionPreexistenteI>();
						
		
		Recetario.observadores.add(observaSiElUsuarioNecesitaMail);
		
		

		
	}

	@Test
	public void testEnvioDeMailAUsuarioSinRealizar() {
	
		
		UsuarioBuilder constructorDeUsuario  = new  UsuarioParaAprobacionDeSolicitudes("jContardo");
		Usuario unUsuario =  constructorDeUsuario.crearUsuario();
		
		constructorDeUsuario  = new  UsuarioParaAprobacionDeSolicitudes("juancito");
		Usuario otroUsuario =  constructorDeUsuario.crearUsuario();
		
		
		

		
		Consulta consulta  = new Consulta(unUsuario);
		Consulta otraConsulta  = new Consulta(otroUsuario);
		consulta.consultarRecetas();
		otraConsulta.consultarRecetas();
		
		assertEquals(GestorDeTareas.getInstance().getTareas().size(),1);
		
		
	
	}
	
	@Test
	public void testEnvioDeMailAUsuarioRealizado() {
	
		
		UsuarioBuilder constructorDeUsuario  = new  UsuarioParaAprobacionDeSolicitudes("jContardo");
		Usuario unUsuario =  constructorDeUsuario.crearUsuario();
		
		constructorDeUsuario  = new  UsuarioParaAprobacionDeSolicitudes("juancito");
		Usuario otroUsuario =  constructorDeUsuario.crearUsuario();
		
		
		

		
		Consulta consulta  = new Consulta(unUsuario);
		Consulta otraConsulta  = new Consulta(otroUsuario);
		consulta.consultarRecetas();
		otraConsulta.consultarRecetas();
		
		GestorDeTareas.getInstance().procesarTareasPendientes();
		
		assertEquals(GestorDeTareas.getInstance().getTareas().size(),0);
		
		
	
	}
	
	@Test
	public void testMarcarComoFavoritas() {
	
		
		Recetario.observadores.add(new ObservadorMarcaComoFavoritas());
		
		UsuarioBuilder constructorDeUsuario  = new  UsuarioParaAprobacionDeSolicitudes("jContardo");
		constructorDeUsuario.marcaFavoritas(true);
		
		Usuario unUsuario =  constructorDeUsuario.crearUsuario();

		
		
		
		Consulta consulta  = new Consulta(unUsuario);
		consulta.consultarRecetas();
		
				
		GestorDeTareas.getInstance().procesarTareasPendientes();
		
		assertTrue(consulta.cantidadRecetasResultado() ==  unUsuario.getFavoritas().stream().count());
		
		
	
	}
	
	@Test
	public void testNoMarcarComoFavoritas() {
	
		
		Recetario.observadores.add(new ObservadorMarcaComoFavoritas());
		
		UsuarioBuilder constructorDeUsuario  = new  UsuarioParaAprobacionDeSolicitudes("jContardo");
		constructorDeUsuario.marcaFavoritas(false);
		
		Usuario unUsuario =  constructorDeUsuario.crearUsuario();

		
		
		
		Consulta consulta  = new Consulta(unUsuario);
		consulta.consultarRecetas();
		
				
		GestorDeTareas.getInstance().procesarTareasPendientes();
		
		assertTrue(unUsuario.getFavoritas().stream().count() == 0);
		
	
	}


}
