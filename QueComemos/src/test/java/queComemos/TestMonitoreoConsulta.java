package queComemos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import interfaces.CondicionPreexistenteI;
import interfaces.ObservadorI;
import observadores.ObservadorDeConsultas;
import observadores.ObservadorVeganoConsultaRecetaDificil;

import org.junit.Before;
import org.junit.Test;

import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.Diabetico;
import condicionesPreexistentes.Hipertenso;
import condicionesPreexistentes.Vegano;
import receta.Receta;
import repositorios.Consulta;
import repositorios.Recetario;
import usuario.GustosSobreAlimentos;
import usuario.Usuario;

public class TestMonitoreoConsulta {
	
	private Usuario usrHombre;
	private Usuario usrMujer;
	

	@Before
	public void setUp() throws Exception {
		
	Recetario.observadores = new ArrayList<ObservadorI>();
	Recetario.inadecuados =new ArrayList<CondicionPreexistenteI>();
	Recetario.recetas= new ArrayList<Receta>();
	Recetario.consultas = new ArrayList<Consulta>();
	Recetario.veganosConsultandoRecetasDificiles = new ArrayList<Usuario>();
	
	
	
	CondicionPreexistenteI	 celiaco = new Celiaco("celiaco",new ArrayList<String>());
	CondicionPreexistenteI hipertenso= new Hipertenso("hipertenso",new ArrayList<String>());
	CondicionPreexistenteI vegano = new Vegano("vegano",new ArrayList<String>());
	CondicionPreexistenteI diabetico =  new Diabetico("Diabetico",new ArrayList<String>());
	 
	 
	 Recetario.inadecuados.add(celiaco);
	 Recetario.inadecuados.add(hipertenso);
	 Recetario.inadecuados.add(vegano);
	 Recetario.inadecuados.add(diabetico);
	 
	 
	 GustosSobreAlimentos preferencia = new GustosSobreAlimentos(new ArrayList<String>(),new ArrayList<String>());
	 
	 List<CondicionPreexistenteI> condiciones = new ArrayList<>();
	 
		condiciones.add(vegano);
		condiciones.add(hipertenso);
	 
	 
	  usrHombre = new Usuario(1,"martin", "masculino", LocalDate.parse("1994-05-06"), 10.0, 20.5, "Intensa", preferencia, condiciones, new ArrayList<Receta>());
	  usrMujer = new Usuario(1,"Juana", "femenino", LocalDate.parse("1994-05-06"), 10.0, 20.5, "Intensa", preferencia, condiciones, new ArrayList<Receta>());
	 	 	
		
	}

	@Test
	public void testMonitorearRecetasPorHora() {
			

		 ObservadorI monitorConsulta = new ObservadorDeConsultas();
		 Recetario.observadores.add(monitorConsulta);
		 
		 
		 Consulta consulta  = new Consulta(usrHombre);
		 
		 consulta.consultarRecetas();
		 
		 LocalDateTime horaConsultaDesde = LocalDateTime.now();
		 LocalDateTime horaConsultaHasta = LocalDateTime.now().plusHours(1);
		 
		 
		 Long  cantidadConsultas = Recetario.obtenerCantidadDeRecetasConsultadas(horaConsultaDesde,horaConsultaHasta);
		 
		
		 assertTrue(cantidadConsultas == consulta.cantidadRecetasResultado());
		
		
		
	}
	
	@Test
	public void testMonitorearRecetasRecetaMasConsultada() {
		

		 ObservadorI monitorHoraConsulta = new ObservadorDeConsultas();
		 Recetario.observadores.add(monitorHoraConsulta);
		 
		 
		 Consulta consulta  = new Consulta(usrHombre);
		 
		 consulta.consultarRecetas();
		 
	 
		 Receta  recetaMasConsultada = Recetario.obtenerRecetaMasConsultada();
		 
		
		 assertTrue(recetaMasConsultada.getNombre().equals("ensalada caesar"));
		
		
		
	}
	
	@Test
	public void testMonitorearRecetasRecetaMasConsultadaPorHombres() {
		

		 ObservadorI monitorHoraConsulta = new ObservadorDeConsultas();
		 Recetario.observadores.add(monitorHoraConsulta);
		 
		 
		 Consulta consulta  = new Consulta(usrHombre);
		 
		 consulta.consultarRecetas();
		 
	 
		 Receta  recetaMasConsultada = Recetario.obtenerRecetaMasConsultadaPorHombres();
		 
		
		 assertTrue(recetaMasConsultada.getNombre().equals("ensalada caesar"));
		
		
		
	}
	
	
	@Test
	public void testMonitorearRecetasRecetaMasConsultadaPorMujeres() {
		

		 ObservadorI monitorHoraConsulta = new ObservadorDeConsultas();
		 Recetario.observadores.add(monitorHoraConsulta);
		 
		 
		 Consulta consulta  = new Consulta(usrMujer);
		 
		 consulta.consultarRecetas();
		 
	 
		 Receta  recetaMasConsultada = Recetario.obtenerRecetaMasConsultadaPorMujeres();
		 
		
		 assertTrue(recetaMasConsultada.getNombre().equals("ensalada caesar"));
		
		
		
	}
	
	
	
	@Test
	public void testMonitorearRecetasDificilesConsultadasPorVeganos() {
		

		 ObservadorI monitorHoraConsulta = new ObservadorDeConsultas();
		 ObservadorI monitorVeganoConsultadoDificil = new ObservadorVeganoConsultaRecetaDificil();
		 
		 Recetario.observadores.add(monitorHoraConsulta);
		 Recetario.observadores.add(monitorVeganoConsultadoDificil);
		 
		 
		 Consulta consulta  = new Consulta(usrHombre);
		 
		 consulta.consultarRecetas();
		 
	 
		 List<Usuario>  veganosConsultadoDificiles = Recetario.veganosConsultandoRecetasDificiles;
		 
		
		 assertTrue(veganosConsultadoDificiles.size() >=1);
		
		
		
	}
	
	
	
	
	
	
	
	
	

}
