package queComemos;

import static org.junit.Assert.*;
import interfaces.LoggerI;
import interfaces.ObservadorI;
import interfaces.TareaI;

import java.util.ArrayList;
import java.util.List;


import logConsultas.LoggerMock;
import observadores.ObservadorDeConsultas;
import observadores.ObservadorParaElEnvioDeMails;

import org.junit.Before;
import org.junit.Test;

import condicionesPreexistentes.CondicionPreexistente;
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

		

		Recetario.getInstance().observadores.add(new ObservadorDeConsultas());
	
		gestorTareas  = GestorDeTareas.getInstance();
		
		LoggerI  logger = new LoggerMock();
		TareaI loguearConsultas = new LoguearConsultas(logger);
		gestorTareas.agregarTarea(loguearConsultas);
	}

	@Test
	public void test() {
	
		UsuarioBuilder constructor = new UsuarioParaAprobacionDeSolicitudes("juancito");
		Usuario usuario = constructor.crearUsuario();
		
		Consulta consulta = new Consulta(usuario); 
		
		consulta.consultarRecetas();
		
		gestorTareas.procesarTareasPendientes();
		
		assertEquals(Recetario.getInstance().consultasLogueadas.size(), 0);
		
	
	}

}
