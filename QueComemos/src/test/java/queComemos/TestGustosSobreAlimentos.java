package queComemos;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestGustosSobreAlimentos {

	List<String> alimentosAceptados, alimentosRechazados, listaComidas;
	GustosSobreAlimentos gustosAlimenticios;
	
	@Before
	public void setUp() throws Exception {
		alimentosAceptados = new ArrayList<>();
		alimentosRechazados = new ArrayList<>();
		listaComidas = new ArrayList<>();
		
		alimentosAceptados.add("carne");
		alimentosAceptados.add("pollo");
		alimentosRechazados.add("espinaca");
		alimentosRechazados.add("zanahoria");
		
		listaComidas.add("pescado");
		listaComidas.add("tomate");
		
		gustosAlimenticios = new GustosSobreAlimentos(alimentosAceptados, alimentosRechazados);
	}

	@Test
	public void testLeGustaUnAlimentoQueNoTieneEnLaListaDeAlimentosAceptadosNoPasa() {
		assertFalse(gustosAlimenticios.leGusta("Lechuga"));
	}
	
	@Test
	public void testLeGustaUnAlimentoQueTieneEnLaListaDeAlimentosAceptadosPasa() {
		assertTrue(gustosAlimenticios.leGusta("carne"));
	}
	
	@Test
	public void testLeGustaAlgunAlimentoDeUnaListaQueNoEstanEnLaListaDeAlimentosAceptadosNoPasa() {
		assertFalse(gustosAlimenticios.leGustanLasComidas(listaComidas));
	}
	
	@Test
	public void testLeGustanTodosLosAlimentoDeUnaListaEnLaQueNoTodosEstanEnLaListaDeAlimentosAceptadosNoPasa() {
		listaComidas.add("carne");
		assertFalse(gustosAlimenticios.leGustanLasComidas(listaComidas));
	}
	
	@Test
	public void testLeGustanTodosLosAlimentoDeUnaListaEnLaQueTodosEstanEnLaListaDeAlimentosAceptadosPasa() {
		listaComidas.clear();
		listaComidas.add("carne");
		listaComidas.add("pollo");
		assertTrue(gustosAlimenticios.leGustanLasComidas(listaComidas));
	}
}
