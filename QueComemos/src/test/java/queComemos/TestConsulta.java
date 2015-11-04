package queComemos;

import static org.junit.Assert.*;
import interfaces.FiltroI;
import interfaces.ObservadorI;
import interfaces.ProcesamientoI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import condicionesPreexistentes.CondicionPreexistente;
import builderUsuario.UsuarioBuilder;
import builderUsuario.UsuarioMasGenerico;
import filtros.FiltroGustoDeUsuario;
import filtros.FiltroPreparacionCara;
import procesamientos.TomarDiezPrimeros;
import receta.Receta;
import repositorios.Consulta;
import repositorios.Recetario;
import repositorios.RepoUsuarios;
import usuario.Usuario;


public class TestConsulta {
	
	private Usuario usr;
	private  List<FiltroI> filtros;
	private ProcesamientoI procesamiento;
	

	@Before
	public void setUp() throws Exception {
		
		RepoUsuarios.getInstance().inadecuados= new ArrayList<CondicionPreexistente>();
		
		
		filtros = new ArrayList<FiltroI>();
		
		
		FiltroI unFiltro = new FiltroGustoDeUsuario();
		FiltroI otroFiltro = new FiltroPreparacionCara();
		
		procesamiento = new TomarDiezPrimeros();
		
		
		filtros.add(unFiltro);
		filtros.add(otroFiltro);
		
	


	UsuarioBuilder constructorDeUsuario = new UsuarioMasGenerico();
	constructorDeUsuario.nombre("Pepito");
	constructorDeUsuario.fechaNacimiento(LocalDate.parse("1994-05-06"));
	constructorDeUsuario.peso(10.0);
	constructorDeUsuario.estatura(20.5);
	constructorDeUsuario.rutina("Intensa");
		
	 usr  =  constructorDeUsuario.crearUsuario();
			

	
	

		
		
		
	}

	@Test
	public void testRealizarConsulta() {
		
				
		Consulta consulta = new Consulta(usr);
		
		consulta.agregarFiltro(filtros.get(0));
		consulta.agregarFiltro(filtros.get(1));
		consulta.establecerProcesamientoPosterior(procesamiento);
		
		
		consulta.consultarRecetas();
		
			
		assertTrue(consulta.obtenerResultadoConsulta().size() <= 10);
		
	}

}
