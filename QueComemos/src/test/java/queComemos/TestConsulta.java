package queComemos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import filtros.FiltroGustoDeUsuario;
import filtros.FiltroPreparacionCara;
import procesamientos.TomarDiezPrimeros;
import builder.UsuarioBuilder;

public class TestConsulta {
	
	private Usuario usr;
	private  List<FiltroI> filtros;
	private ProcesamientoI procesamiento;
	

	@Before
	public void setUp() throws Exception {
	

	filtros = new ArrayList<FiltroI>();
	
	
	usr  = new Usuario(0,"","",LocalDate.parse("2100-01-01"),0.0,0.0,"",null,new ArrayList<CondicionPreexistenteI>(),new ArrayList<Receta>());		
			
	
	FiltroI unFiltro = new FiltroGustoDeUsuario();
	FiltroI otroFiltro = new FiltroPreparacionCara();
	
	procesamiento = new TomarDiezPrimeros();
	
	
	filtros.add(unFiltro);
	filtros.add(otroFiltro);
	
	QueComemosApp.inicializar();
	
	

		
		
		
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
