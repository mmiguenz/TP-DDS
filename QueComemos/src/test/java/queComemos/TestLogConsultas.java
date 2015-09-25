package queComemos;

import static org.junit.Assert.*;
import interfaces.CondicionPreexistenteI;
import interfaces.LoggerI;
import interfaces.ObservadorI;
import interfaces.TareaI;

import java.util.ArrayList;
import java.util.List;

import logConsultas.ConsultaMock;
import logConsultas.LoggerMock;
import observadores.ObservadorDeConsultas;
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
import tareas.LoguearConsultas;
import usuario.Usuario;

public class TestLogConsultas {
	
	private GestorDeTareas gestorTareas;

	@Before
	public void setUp() throws Exception {

		Recetario.observadores = new ArrayList<ObservadorI>();
		Recetario.consultas =new ArrayList<Consulta>();
		Recetario.recetas =new ArrayList<Receta>();
		RepoUsuarios.inadecuados = new ArrayList<CondicionPreexistenteI>();

		Recetario.observadores.add(new ObservadorDeConsultas());
		
		RepoUsuarios.inadecuados = new ArrayList<CondicionPreexistenteI>();
		Recetario.consultasLogueadas = new ArrayList<Consulta>();
		Recetario.recetas = new ArrayList<Receta>();
		Recetario.consultas = new ArrayList<Consulta>();
		
		gestorTareas  = GestorDeTareas.getInstance();
		
		LoggerI  logger = new LoggerMock();
		TareaI loguearConsultas = new LoguearConsultas(logger);
		gestorTareas.agregarTarea(loguearConsultas);
	}

	@Test
	public void test() {
	
		UsuarioBuilder constructor = new UsuarioParaAprobacionDeSolicitudes("juancito");
		Usuario usuario = constructor.crearUsuario();
		
		ConsultaMock consulta = new ConsultaMock(usuario); 
		
		consulta.consultarRecetas();
		
		gestorTareas.procesarTareasPendientes();
		
		assertEquals(Recetario.consultasLogueadas.size(), 1);
		
	
	}

}
