package queComemos;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.frba.dds.tpAnual.queComemos.recetas.Recetario;

public class TestQueComemosApp {

	Recetario recetario;
	
	@Before
	public void setUp() throws Exception {
		recetario = new Recetario();
		recetario.inadecuados = new ArrayList<CondicionPreexistenteI>();
	}

	@Test
	public void test() {
//		QueComemosApp.inicializar();
		
		assertEquals(recetario.inadecuados.size(),0);
		
	}

}
