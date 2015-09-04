package queComemos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import observadores.ObserverPorHora;

import org.junit.Before;
import org.junit.Test;

import procesamientos.TomarDiezPrimeros;
import filtros.FiltroGustoDeUsuario;
import filtros.FiltroPreparacionCara;

public class TestMonitoreoDeConsultas {
	
	
	private Consulta consultaMasCulina;
	private Consulta consultaFememina;
	@Before
	public void setUp() throws Exception {
		
	Usuario	usrHombre  = new Usuario(0,"","Masculino",LocalDate.parse("2100-01-01"),0.0,0.0,"",null,new ArrayList<CondicionPreexistenteI>(),new ArrayList<Receta>());
	Usuario usrMujer  = new Usuario(0,"","Femenino",LocalDate.parse("2100-01-01"),0.0,0.0,"",null,new ArrayList<CondicionPreexistenteI>(),new ArrayList<Receta>());
		
		
		

		List<FiltroI>filtros = new ArrayList<FiltroI>();
	
				
		
		FiltroI filtroGustoDeUsuario = new FiltroGustoDeUsuario();
		FiltroI filtroPreparacionCara = new FiltroPreparacionCara();
		
		ProcesamientoI procesamiento = new TomarDiezPrimeros();
		
		
	
		
		QueComemosApp.inicializar();
		
		
		
		consultaMasCulina = new Consulta(usrHombre);
		consultaMasCulina.agregarFiltro(filtroGustoDeUsuario);
		consultaMasCulina.establecerProcesamientoPosterior(procesamiento);
		
		
		consultaFememina = new Consulta(usrMujer);		
		consultaFememina.agregarFiltro(filtroPreparacionCara);
		consultaFememina.establecerProcesamientoPosterior(procesamiento);
		
		
				
		

			
		
	}

	@Test
	public void testMonitoreoConsultasPorHora() {
		
		ObservadorI obsPorHora = new ObserverPorHora();		
		GestorDeConsultas.getInstance().agregarObservador(obsPorHora);
		
		consultaMasCulina.consultarRecetas();
		Integer cantidadConsultadas = consultaMasCulina.cantidadRecetasResultado();
		Integer hora = LocalDateTime.now().getHour();	
		
		
		Estadistica estadistica  = GestorDeConsultas.getInstance().getEstadistica();
		
		

		
		assertTrue(estadistica.obtenerRecetasPorHora(hora) == cantidadConsultadas);
	
	
	}

}
